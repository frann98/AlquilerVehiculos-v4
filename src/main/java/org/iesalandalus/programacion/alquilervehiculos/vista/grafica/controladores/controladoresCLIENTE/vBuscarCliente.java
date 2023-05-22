package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class vBuscarCliente extends Controlador {

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private TextField tfDNIClienteBuscar;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIClienteBuscar.clear();

		this.getEscenario().close();

	}

	@FXML
	void buscarClienteDNI(ActionEvent event) {

		try {

			Cliente buscado = new Cliente("Nombreinexistente", tfDNIClienteBuscar.getText(), "666666666");

			if (VistaGrafica.getInstancia().getControlador().buscar(buscado).getNombre().equals("Nombreinexistente")) {
				Dialogos.mostrarDialogoInformacion("Cliente no encontrado",
						"No se ha encontrado ningún cliente con el DNI introducido", getEscenario());
			} else {
				Dialogos.mostrarDialogoInformacion("Cliente encontrado",
						VistaGrafica.getInstancia().getControlador().buscar(buscado).toString(), getEscenario());
			}

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}