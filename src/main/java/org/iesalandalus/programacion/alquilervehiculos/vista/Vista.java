package org.iesalandalus.programacion.alquilervehiculos.vista;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;

public abstract class Vista {

	private Controlador controlador;

	protected Vista() {
		super();
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {

		this.controlador = controlador;

	}

	public abstract void comenzar();

	public abstract void terminar();

}