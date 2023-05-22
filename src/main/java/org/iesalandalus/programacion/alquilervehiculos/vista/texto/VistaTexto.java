package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	private Controlador controlador;

	@Override
	public void comenzar() {
		Accion op = null;
		Accion.setVista(this);

		do {

			Consola.mostrarMenu();

			op = Consola.elegirAccion();

			op.ejecutar();

		} while (op != Accion.SALIR);

	}

	@Override
	public void terminar() {
		System.out.println("Finalizado");

	}

	@Override
	public void setControlador(Controlador controlador) {

		if (controlador == null) {

			throw new NullPointerException("El controlador no puede ser nulo");

		}

		this.controlador = controlador;

	}

	@Override
	public Controlador getControlador() {
		return controlador;
	}

	public void insertarCliente() {

		try {

			controlador.insertar(Consola.leerCliente());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al insertar");
		}

		System.out.println("Cliente agregado correctamente");

	}

	public void insertarVehiculo() {

		try {

			controlador.insertar(Consola.leerVehiculo());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al insertar");
		}

		System.out.println("Vehiculo agregado correctamente");

	}

	public void insertarAlquiler() {

		try {
			controlador.insertar(Consola.leerAlquiler());
		} catch (OperationNotSupportedException e) {
			System.out.println("Error al insertar");
		}

		System.out.println("Alquiler agregado correctamente");

	}

	public void buscarCliente() {

		System.out.println(controlador.buscar(Consola.leerClienteDni()).toString());

		System.out.println("Cliente buscado correctamente");

	}

	public void buscarVehiculo() {

		System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()).toString());

		System.out.println("Vehiculo buscado correctamente");

	}

	public void buscarAlquiler() {

		System.out.println(controlador.buscar(Consola.leerAlquiler()).toString());

		System.out.println("Alquiler buscado correctamente");

	}

	public void modificarCliente() {

		try {

			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al modificar");
		}

		System.out.println("Cliente modificado correctamente");

	}

	public void devolverAlquilerCliente() {

		try {

			controlador.devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al devolver");
		}

		System.out.println("Alquiler devuelto correctamente");

	}

	public void devolverAlquilerVehiculo() {

		try {

			controlador.devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al devolver");
		}

		System.out.println("Alquiler devuelto correctamente");

	}

	public void borrarCliente() {

		try {

			controlador.borrar(Consola.leerClienteDni());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al borrar");
		}

		System.out.println("Cliente borrado correctamente");

	}

	public void borrarVehiculo() {

		try {

			controlador.borrar(Consola.leerVehiculoMatricula());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al borrar");
		}

		System.out.println("Vehiculo borrado correctamente");

	}

	public void borrarAlquiler() {

		try {

			controlador.borrar(Consola.leerAlquiler());

		} catch (OperationNotSupportedException e) {

			System.out.println("Error al borrar");
		}

		System.out.println("Alquiler borrado correctamente");

	}

	public void listarClientes() {

		List<Cliente> listaClientes = controlador.getClientes();
		listaClientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		System.out.println(listaClientes.toString());

		System.out.println("Listado de clientes finalizado");

	}

	public void listarVehiculos() {

		List<Vehiculo> listaVehiculos = controlador.getVehiculos();
		listaVehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
				.thenComparing(Vehiculo::getMatricula));

		System.out.println(listaVehiculos.toString());

		System.out.println("Listado de vehiculos finalizado");

	}

	public void listarAlquileres() {

		// Ordenar primero por fechaAlquiler y luego por Cliente

		List<Alquiler> listaAlquileres = controlador.getAlquileres();

		listaAlquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler));

		System.out.println(listaAlquileres.toString());

		System.out.println("Listado de alquileres finalizado");

	}

	public void listarAlquileresCliente() {

		System.out.println(controlador.getAlquileres(Consola.leerClienteDni()).toString());

		System.out.println("Listado de alquileres del cliente finalizado");

	}

	public void listarAlquileresVehiculo() {

		System.out.println(controlador.getAlquileres(Consola.leerVehiculoMatricula()).toString());

		System.out.println("Listado de alquileres del vehiculo finalizado");

	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {

		Map<TipoVehiculo, Integer> stats = inicializarEstadisticas();

		System.out.println("En el mes dado, se alquilaron: " + stats.get(TipoVehiculo.TURISMO) + " turismos");
		System.out.println(" " + stats.get(TipoVehiculo.FURGONETA) + " furgonetas");
		System.out.println(" y " + stats.get(TipoVehiculo.AUTOBUS) + " autobuses");

	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {

		LocalDate mes = Consola.leerMes();

		List<Alquiler> listaAlquileres = controlador.getAlquileres();

		Map<TipoVehiculo, Integer> stats = new HashMap<>();

		int turismos = 0;
		int furgonetas = 0;
		int autobuses = 0;

		stats.put(TipoVehiculo.TURISMO, turismos);
		stats.put(TipoVehiculo.FURGONETA, furgonetas);
		stats.put(TipoVehiculo.AUTOBUS, autobuses);

		for (Alquiler listaAlquilere : listaAlquileres) {

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.TURISMO)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == mes.getMonthValue()) {

				turismos++;

				stats.put(TipoVehiculo.TURISMO, turismos);

			}

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.FURGONETA)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == mes.getMonthValue()) {

				furgonetas++;

				stats.put(TipoVehiculo.FURGONETA, furgonetas);

			}

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.AUTOBUS)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == mes.getMonthValue()) {

				autobuses++;

				stats.put(TipoVehiculo.AUTOBUS, autobuses);

			}

		}

		return stats;

	}

}
