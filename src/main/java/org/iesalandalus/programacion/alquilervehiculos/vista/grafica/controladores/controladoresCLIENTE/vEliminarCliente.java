package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vEliminarCliente extends Controlador {

	@FXML
	private Button btAceptarEliminar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfDNIClienteEliminar;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIClienteEliminar.clear();

		this.getEscenario().close();

	}

	@FXML
	void eliminarClienteDNI(ActionEvent event) throws OperationNotSupportedException {

		try {

			VistaGrafica.getInstancia().getControlador()
					.borrar(new Cliente("Nombre", tfDNIClienteEliminar.getText(), "666666666"));

			Dialogos.mostrarDialogoInformacion("Eliminado", "Cliente eliminado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}