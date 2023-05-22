package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vBuscarVehiculo extends Controlador {

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfMatriculaVehiculoBuscar;

	@FXML
	void cancelar(ActionEvent event) {

		tfMatriculaVehiculoBuscar.clear();

		this.getEscenario().close();

	}

	@FXML
	void buscarVehiculoPorMatricula(ActionEvent event) {

		try {

			Turismo buscado = new Turismo("Marca", "Modelo", 1111, tfMatriculaVehiculoBuscar.getText());

			if (VistaGrafica.getInstancia().getControlador().buscar(buscado).getMarca().equals("Marca")) {
				Dialogos.mostrarDialogoInformacion("Vehiculo no encontrado",
						"No se ha encontrado ningún Vehiculo con la matricula introducida", getEscenario());
			} else {
				Dialogos.mostrarDialogoInformacion("Vehiculo encontrado",
						VistaGrafica.getInstancia().getControlador().buscar(buscado).toString(), getEscenario());
			}

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}