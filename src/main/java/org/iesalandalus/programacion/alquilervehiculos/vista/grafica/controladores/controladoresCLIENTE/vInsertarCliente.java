package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vInsertarCliente extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfDNI;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNI.clear();
		tfNombre.clear();
		tfTelefono.clear();

		this.getEscenario().close();

	}

	@FXML
	void insertarNuevoCliente(ActionEvent event) {

		try {

			Cliente nue = new Cliente(tfNombre.getText(), tfDNI.getText(), tfTelefono.getText());

			VistaGrafica.getInstancia().getControlador().insertar(nue);

			Dialogos.mostrarDialogoInformacion("Insertado", "Cliente insertado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}
