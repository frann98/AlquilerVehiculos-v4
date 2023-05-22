package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VtablaVehiculos extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private TableView<Vehiculo> tbvVehiculos;

	@FXML
	private TableColumn<Vehiculo, String> tbcCilindrada;

	@FXML
	private TableColumn<Vehiculo, String> tbcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tbcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tbcModelo;

	@FXML
	private TableColumn<Vehiculo, String> tbcPMA;

	@FXML
	private TableColumn<Vehiculo, String> tbcPlazas;

	@FXML
	void aceptarYcerrar(ActionEvent event) {

		this.getEscenario().close();

	}

	private ObservableList<Vehiculo> listaVehiculos = FXCollections.observableArrayList();

	public void inicializar() {

		listaVehiculos.clear();
		listaVehiculos = FXCollections.observableArrayList(VistaGrafica.getInstancia().getControlador().getVehiculos());

		tbcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tbcCilindrada.setCellValueFactory(new PropertyValueFactory<>("cilindrada"));
		tbcPlazas.setCellValueFactory(new PropertyValueFactory<>("plazas"));
		tbcPMA.setCellValueFactory(new PropertyValueFactory<>("pma"));

		tbvVehiculos.setItems(listaVehiculos);
	}

}
