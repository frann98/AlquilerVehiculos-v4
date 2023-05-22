package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class VdevolverPorAlquiler extends Controlador {

	@FXML
	private Button btAceptarYdevolver;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private Label lblDNIoMatricula;

	@FXML
	private Label lblFechaAlquiler;

	@FXML
	private RadioButton rbCliente;

	@FXML
	private RadioButton rbVehiculo;

	@FXML
	private TextField tfDNIoMatriculaDevolver;

	@FXML
	void cancelar(ActionEvent event) {

		tfDNIoMatriculaDevolver.clear();

		this.getEscenario().close();

	}

	public void inicializar() {

		dpFechaDevolucion.setVisible(false);
		lblDNIoMatricula.setVisible(false);
		lblFechaAlquiler.setVisible(false);
		tfDNIoMatriculaDevolver.setVisible(false);

		ToggleGroup DNIoMatricula = new ToggleGroup();

		rbCliente.setToggleGroup(DNIoMatricula);
		rbVehiculo.setToggleGroup(DNIoMatricula);

		rbCliente.setOnAction(event -> {

			lblDNIoMatricula.setText("DNI del cliente a devolver:");
			dpFechaDevolucion.setVisible(true);
			lblDNIoMatricula.setVisible(true);
			lblFechaAlquiler.setVisible(true);
			tfDNIoMatriculaDevolver.setVisible(true);

		});

		rbVehiculo.setOnAction(event -> {

			lblDNIoMatricula.setText("Matricula del vehiculo a devolver:");
			dpFechaDevolucion.setVisible(true);
			lblDNIoMatricula.setVisible(true);
			lblFechaAlquiler.setVisible(true);
			tfDNIoMatriculaDevolver.setVisible(true);

		});

	}

	@FXML
	void devolverAlquiler(ActionEvent event) {

		try {

			if (tfDNIoMatriculaDevolver.getText().matches("\\d{8}[A-Z]")) {

				VistaGrafica.getInstancia().getControlador().devolver(
						new Cliente("Nombrecliente", tfDNIoMatriculaDevolver.getText(), "666666666"),
						dpFechaDevolucion.getValue());

			}

			if (tfDNIoMatriculaDevolver.getText().matches("^\\d{4}[BCDFGHJKLMNÑPQRSTVWXYZ]{3}")) {

				VistaGrafica.getInstancia().getControlador().devolver(
						new Turismo("Marca", "Modelo", 1500, tfDNIoMatriculaDevolver.getText()),
						dpFechaDevolucion.getValue());

			}

			Dialogos.mostrarDialogoInformacion("Devolución realizada",
					"Se ha realizado la devolución del alquiler de este cliente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}