package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class vInsertarVehiculo extends Controlador {

	private String[] tipos = { "Autobus", "Furgoneta", "Turismo", "Seleccionar" };

	public void inicializar() {

		cbTipoVehiculo.getItems().addAll(tipos);
		cbTipoVehiculo.setValue(tipos[3]);

		lblPlazas.setVisible(false);
		lblCilindrada.setVisible(false);
		lblPMA.setVisible(false);

		tfCilindrada.setVisible(false);
		tfPlazas.setVisible(false);
		tfPMA.setVisible(false);

		cbTipoVehiculo.setOnAction(event -> {
			if (cbTipoVehiculo.getValue().equals(tipos[0])) {

				lblPlazas.setVisible(true);
				tfPlazas.setVisible(true);

				lblCilindrada.setVisible(false);
				tfCilindrada.setVisible(false);

				lblPMA.setVisible(false);
				tfPMA.setVisible(false);

			}
			if (cbTipoVehiculo.getValue().equals(tipos[1])) {

				lblPlazas.setVisible(true);
				tfPlazas.setVisible(true);

				lblPMA.setVisible(true);
				tfPMA.setVisible(true);

				lblCilindrada.setVisible(false);
				tfCilindrada.setVisible(false);

			}
			if (cbTipoVehiculo.getValue().equals(tipos[2])) {

				lblPlazas.setVisible(false);
				tfPlazas.setVisible(false);

				lblPMA.setVisible(false);
				tfPMA.setVisible(false);

				lblCilindrada.setVisible(true);
				tfCilindrada.setVisible(true);

			}

			if (cbTipoVehiculo.getValue().equals(tipos[3])) {

				lblPlazas.setVisible(false);
				tfPlazas.setVisible(false);

				lblPMA.setVisible(false);
				tfPMA.setVisible(false);

				lblCilindrada.setVisible(false);
				tfCilindrada.setVisible(false);

			}
		});

	}

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private ComboBox<String> cbTipoVehiculo;

	@FXML
	private Label lblCilindrada;

	@FXML
	private Label lblPMA;

	@FXML
	private Label lblPlazas;

	@FXML
	private TextField tfCilindrada;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfMatricula;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfPMA;

	@FXML
	private TextField tfPlazas;

	@FXML
	void cancelar(ActionEvent event) {

		this.getEscenario().close();

	}

	@FXML
	void insertarNuevoVehiculo(ActionEvent event) {

		try {

			Vehiculo v = Vehiculo.copiar(null);

			switch (cbTipoVehiculo.getValue()) {
			case "Autobus":
				v = new Autobus(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfPlazas.getText()),
						tfMatricula.getText());
				break;
			case "Furgoneta":
				v = new Furgoneta(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfPMA.getText()),
						Integer.parseInt(tfPlazas.getText()), tfMatricula.getText());
				break;
			case "Turismo":
				v = new Turismo(tfMarca.getText(), tfModelo.getText(), Integer.parseInt(tfCilindrada.getText()),
						tfMatricula.getText());
				break;
			default:
				// default

			}

			VistaGrafica.getInstancia().getControlador().insertar(v);

			Dialogos.mostrarDialogoInformacion("Insertado", "Vehiculo insertado correctamente", getEscenario());

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}
