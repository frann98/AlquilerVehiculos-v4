package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo {

	// Declaramos las ER y los atributos

	private static int FACTOR_PLAZAS = 2;
	private int plazas;

	// Creamos los constructores

	public Autobus(String marca, String modelo, int plazas, String matricula) {

		super(marca, modelo, matricula);

		setPlazas(plazas);

	}

	public Autobus(Autobus autobus) {

		super(autobus);

		if (autobus == null) {
			throw new NullPointerException("ERROR: No es posible copiar un autobus nulo.");
		}

		plazas = (autobus.getPlazas());

	}

	// Generamos getters y setters

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if (plazas > 6 && plazas <= 100) {
			this.plazas = plazas;
		} else {
			throw new IllegalArgumentException("ERROR: Las plazas no son correctas.");
		}
	}

	@Override
	public int getFactorPrecio() {

		return plazas * FACTOR_PLAZAS;
	}

	// Sobreescribimos hashCode, equals y toString

	@Override
	public String toString() {
		return String.format("%s %s (%d plazas) - %s", getMarca(), getModelo(), plazas, getMatricula());
	}

}
