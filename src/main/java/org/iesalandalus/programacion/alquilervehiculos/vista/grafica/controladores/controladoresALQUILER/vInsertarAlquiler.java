package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class vInsertarAlquiler extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfDNIcliente;

	@FXML
	private TextField tfMatriculaVehiculo;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIcliente.clear();
		tfMatriculaVehiculo.clear();

		this.getEscenario().close();

	}

	@FXML
	void insertarNuevoAlquiler(ActionEvent event) {

		try {

			Alquiler nue = new Alquiler(new Cliente("Nombre", tfDNIcliente.getText(), "666666666"),
					new Turismo("Marca", "Modelo", 1111, tfMatriculaVehiculo.getText()), dpFechaAlquiler.getValue());

			VistaGrafica.getInstancia().getControlador().insertar(nue);

			Dialogos.mostrarDialogoInformacion("Insertado", "Alquiler insertado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}
