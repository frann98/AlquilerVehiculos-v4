package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Furgoneta extends Vehiculo {

	// Declaramos las ER y los atributos

	private static int FACTOR_PLAZAS = 1;
	private static int FACTOR_PMA = 100;
	private int plazas;
	private int pma;

	// Creamos los constructores

	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {

		super(marca, modelo, matricula);

		setPma(pma);
		setPlazas(plazas);

	}

	public Furgoneta(Furgoneta furgoneta) {

		super(furgoneta);

		if (furgoneta == null) {
			throw new NullPointerException("ERROR: No es posible copiar una furgoneta nula.");
		}

		pma = (furgoneta.getPma());
		plazas = (furgoneta.getPlazas());

	}

	// Generamos getters y setters

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if (plazas >= 2 && plazas <= 4) {
			this.plazas = plazas;
		} else {
			throw new IllegalArgumentException("ERROR: Las plazas no son correctas.");
		}
	}

	public int getPma() {
		return pma;
	}

	private void setPma(int pma) {
		if (pma > 100 && pma <= 10000) {
			this.pma = pma;
		} else {
			throw new IllegalArgumentException("ERROR: El PMA no es correcto.");
		}
	}

	@Override
	public int getFactorPrecio() {

		return ((pma / FACTOR_PMA) + (plazas * FACTOR_PLAZAS));
	}

	// Sobreescribimos hashCode, equals y toString

	@Override
	public String toString() {
		return String.format("%s %s (%d kg, %d plazas) - %s", getMarca(), getModelo(), pma, plazas, getMatricula());
	}

}
