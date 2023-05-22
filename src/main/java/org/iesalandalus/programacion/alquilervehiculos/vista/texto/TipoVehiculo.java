package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {

	TURISMO("turismo"), AUTOBUS("autobus"), FURGONETA("furgoneta");

	String nombre;

	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {

		return (ordinal >= 1 && ordinal <= TipoVehiculo.values().length);

	}

	public static TipoVehiculo get(int ordinal) {

		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("ERROR: El ordinal no es válido");
		}

		return TipoVehiculo.values()[ordinal];

	}

	public static TipoVehiculo get(Vehiculo vehiculo) {

		TipoVehiculo tipo = null;

		if (vehiculo instanceof Turismo) {
			tipo = TURISMO;
		}

		if (vehiculo instanceof Autobus) {
			tipo = AUTOBUS;
		}

		if (vehiculo instanceof Furgoneta) {
			tipo = FURGONETA;
		}

		return tipo;

	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
