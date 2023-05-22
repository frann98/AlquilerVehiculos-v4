package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VdevolverPorVehiculo extends Controlador {

	@FXML
	private Button btAceptarYdevolver;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private TextField tfMatriculaVehiculoDevolver;

	@FXML
	void cancelar(ActionEvent event) {

		tfMatriculaVehiculoDevolver.clear();

		this.getEscenario().close();

	}

	@FXML
	void devolverAlquilerPorVehiculo(ActionEvent event) {

		try {

			VistaGrafica.getInstancia().getControlador().devolver(
					new Turismo("Marca", "Modelo", 1500, tfMatriculaVehiculoDevolver.getText()),
					dpFechaDevolucion.getValue());

			Dialogos.mostrarDialogoInformacion("Devolución realizada",
					"Se ha realizado la devolución del alquiler de este vehiculo", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}