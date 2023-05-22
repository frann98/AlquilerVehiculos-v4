package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VdevolverPorCliente extends Controlador {

	@FXML
	private Button btAceptarYdevolver;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private TextField tfDNIClienteDevolver;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIClienteDevolver.clear();

		this.getEscenario().close();

	}

	@FXML
	void devolverAlquilerPorCliente(ActionEvent event) {

		try {

			VistaGrafica.getInstancia().getControlador().devolver(
					new Cliente("Nombrecliente", tfDNIClienteDevolver.getText(), "666666666"),
					dpFechaDevolucion.getValue());

			Dialogos.mostrarDialogoInformacion("Devolución realizada",
					"Se ha realizado la devolución del alquiler de este cliente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}