package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);

		factoriaFuenteDatos.crear();

	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		Cliente copia = new Cliente(cliente);

		getClientes().insertar(copia);

	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		Vehiculo copia = Vehiculo.copiar(vehiculo);

		getVehiculos().insertar(copia);

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}

		Cliente cliente = (getClientes().buscar(alquiler.getCliente()));

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}

		Vehiculo vehiculo = (getVehiculos().buscar(alquiler.getVehiculo()));

		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehiculo del alquiler.");
		}

		alquiler = new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler());

		getAlquileres().insertar(alquiler);

	}

	@Override
	public Cliente buscar(Cliente cliente) {

		Cliente buscado = new Cliente(cliente);

		if (getClientes().buscar(cliente) != null) {
			buscado = getClientes().buscar(cliente);
		}

		return buscado;

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		Vehiculo buscado = Vehiculo.copiar(vehiculo);

		if (getVehiculos().buscar(vehiculo) != null) {
			buscado = getVehiculos().buscar(vehiculo);
		}

		return buscado;

	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		Alquiler buscado = new Alquiler(alquiler);
		if (getAlquileres().buscar(alquiler) != null) {
			buscado = getAlquileres().buscar(alquiler);
		}
		return buscado;

	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		getClientes().modificar(cliente, nombre, telefono);

	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (getAlquileres().get(cliente) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}

		getAlquileres().devolver(cliente, fechaDevolucion);

	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (getAlquileres().get(vehiculo) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}

		getAlquileres().devolver(vehiculo, fechaDevolucion);

	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {

		// borramos los alquileres del cliente

		List<Alquiler> alquileresCliente = getAlquileres().get(cliente);

		for (Alquiler element : alquileresCliente) {

			getAlquileres().borrar(element);

		}

		// borramos el cliente de los registros

		getClientes().borrar(cliente);

	}

	@Override
	public void borrar(Vehiculo turismo) throws OperationNotSupportedException {

		// borramos los alquileres del turismo

		List<Alquiler> alquileresTurismo = getAlquileres().get(turismo);

		for (Alquiler element : alquileresTurismo) {

			getAlquileres().borrar(element);

		}

		// borramos el cliente de los registros

		getVehiculos().borrar(turismo);

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		// borramos el alquiler de los registros

		getAlquileres().borrar(alquiler);

	}

	@Override
	public List<Cliente> getListaClientes() {

		List<Cliente> copiaProfunda = new ArrayList<>();

		List<Cliente> copia = getClientes().get();

		for (int i = 0; i < copia.size(); i++) {

			copiaProfunda.add(new Cliente(copia.get(i)));

		}

		return copiaProfunda;

	}

	@Override
	public List<Vehiculo> getListaVehiculos() {

		List<Vehiculo> copiaProfunda = new ArrayList<>();

		List<Vehiculo> copia = getVehiculos().get();

		for (int i = 0; i < copia.size(); i++) {

			copiaProfunda.add(Vehiculo.copiar(copia.get(i)));

		}

		return copiaProfunda;

	}

	@Override
	public List<Alquiler> getListaAlquileres() {

		List<Alquiler> copiaProfunda = new ArrayList<>();

		List<Alquiler> copia = getAlquileres().get();

		for (int i = 0; i < copia.size(); i++) {

			copiaProfunda.add(new Alquiler(copia.get(i)));

		}

		return copiaProfunda;

	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {

		List<Alquiler> copiaProfunda = new ArrayList<>();

		List<Alquiler> copia = getAlquileres().get(cliente);

		for (int i = 0; i < copia.size(); i++) {

			copiaProfunda.add(new Alquiler(copia.get(i)));

		}

		return copiaProfunda;

	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {

		List<Alquiler> copiaProfunda = new ArrayList<>();

		List<Alquiler> copia = getAlquileres().get(vehiculo);

		for (int i = 0; i < copia.size(); i++) {

			copiaProfunda.add(new Alquiler(copia.get(i)));

		}

		return copiaProfunda;

	}

}
