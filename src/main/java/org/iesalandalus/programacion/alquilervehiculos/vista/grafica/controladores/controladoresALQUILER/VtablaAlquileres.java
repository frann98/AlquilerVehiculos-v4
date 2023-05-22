package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
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

public class VtablaAlquileres extends Controlador {

	@FXML
	private TableView<Alquiler> tbvAlquileres;

	@FXML
	private TableColumn<Alquiler, String> tbcDNIcliente;

	@FXML
	private TableColumn<Alquiler, String> tbcFechaAlquiler;

	@FXML
	private TableColumn<Alquiler, String> tbcFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, String> tbcMatriculaVehiculo;

	@FXML
	private Button btAceptar;

	@FXML
	void aceptarYcerrar(ActionEvent event) {

		this.getEscenario().close();

	}

	private ObservableList<Alquiler> listaAlquileres = FXCollections.observableArrayList();

	public void inicializar() {

		listaAlquileres.clear();
		listaAlquileres = FXCollections
				.observableArrayList(VistaGrafica.getInstancia().getControlador().getAlquileres());

		tbcDNIcliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tbcMatriculaVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
		tbcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
		tbcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));

		tbvAlquileres.setItems(listaAlquileres);
	}

}
