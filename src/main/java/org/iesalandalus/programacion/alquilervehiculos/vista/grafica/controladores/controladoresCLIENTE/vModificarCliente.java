package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vModificarCliente extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfDNIaModificar;

	@FXML
	private TextField tfNuevoNombre;

	@FXML
	private TextField tfNuevoTelefono;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIaModificar.clear();
		tfNuevoNombre.clear();
		tfNuevoTelefono.clear();

		this.getEscenario().close();

	}

	@FXML
	void modificarCliente(ActionEvent event) {

		try {

			VistaGrafica.getInstancia().getControlador().modificar(
					new Cliente("Nombre", tfDNIaModificar.getText(), "666666666"), tfNuevoNombre.getText(),
					tfNuevoTelefono.getText());

			Dialogos.mostrarDialogoInformacion("Modificado", "Cliente modificado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}
