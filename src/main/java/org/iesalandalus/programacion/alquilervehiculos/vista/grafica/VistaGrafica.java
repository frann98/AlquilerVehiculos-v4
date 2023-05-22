package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;

public class VistaGrafica extends Vista {

// Este controlador no se usa en esta misma clase, pero sí en muchas otras
	@SuppressWarnings("unused")
	private Controlador controladorMaestro;

	private static final VistaGrafica instancia = new VistaGrafica();

	private VistaGrafica() {

	}

	public static VistaGrafica getInstancia() {

		return instancia;

	}

	@Override
	public void comenzar() {
		LanzadorPrincipal.comenzar();

	}

	@Override
	public void terminar() {
		System.out.println("Adios!");

	}

	protected void mostrarEstadisticasMensualesTipoVehiculo() {

		Map<TipoVehiculo, Integer> stats = inicializarEstadisticas();

		System.out.println("En el mes dado, se alquilaron: " + stats.get(TipoVehiculo.TURISMO) + " turismos");
		System.out.println(" " + stats.get(TipoVehiculo.FURGONETA) + " furgonetas");
		System.out.println(" y " + stats.get(TipoVehiculo.AUTOBUS) + " autobuses");

	}

	public Map<TipoVehiculo, Integer> inicializarEstadisticas() {

		LocalDate mes = Consola.leerMes();

		List<Alquiler> listaAlquileres = controladorMaestro.getAlquileres();

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
