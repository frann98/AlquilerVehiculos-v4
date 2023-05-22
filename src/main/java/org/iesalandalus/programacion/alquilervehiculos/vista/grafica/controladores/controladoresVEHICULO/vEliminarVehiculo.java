package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vEliminarVehiculo extends Controlador {

	@FXML
	private Button btAceptarEliminar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfMatriculaVehiculoEliminar;

	@FXML
	void cancelar(ActionEvent event) {

		this.getEscenario().close();

	}

	@FXML
	void eliminarVehiculoPorMatricula(ActionEvent event) {

		try {

			VistaGrafica.getInstancia().getControlador()
					.borrar(new Turismo("Marca", "Modelo", 1111, tfMatriculaVehiculoEliminar.getText()));

			Dialogos.mostrarDialogoInformacion("Eliminado", "Vehiculo eliminado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}