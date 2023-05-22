package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Turismo extends Vehiculo {

	// Declaramos las ER y los atributos

	private final int FACTOR_CILINDRADA = 10;
	private int cilindrada;

	// Creamos los constructores

	public Turismo(String marca, String modelo, int cilindrada, String matricula) {

		super(marca, modelo, matricula);

		setCilindrada(cilindrada);

	}

	public Turismo(Turismo turismo) {

		super(turismo);

		if (turismo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un turismo nulo.");
		}

		cilindrada = (turismo.getCilindrada());

	}

	// Generamos getters y setters

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada > 0 && cilindrada <= 5000) {
			this.cilindrada = cilindrada;
		} else {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
	}

	@Override
	public int getFactorPrecio() {

		return cilindrada / FACTOR_CILINDRADA;
	}

	// Sobreescribimos toString

	@Override
	public String toString() {
		return String.format("%s %s (%d cc) - %s", getMarca(), getModelo(), cilindrada, getMatricula());
	}

}
