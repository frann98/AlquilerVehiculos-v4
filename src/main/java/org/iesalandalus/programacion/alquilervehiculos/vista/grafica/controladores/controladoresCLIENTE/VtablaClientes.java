package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
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

public class VtablaClientes extends Controlador {

	@FXML
	private Button btAceptar;

	@FXML
	private TableColumn<Cliente, String> tbcDNI;

	@FXML
	private TableColumn<Cliente, String> tbcNombre;

	@FXML
	private TableColumn<Cliente, String> tbcTelefono;

	@FXML
	private TableView<Cliente> tbvClientes;

	@FXML
	void aceptarYcerrar(ActionEvent event) {

		this.getEscenario().close();

	}

	private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

	public void inicializar() {

		listaClientes.clear();
		listaClientes = FXCollections.observableArrayList(VistaGrafica.getInstancia().getControlador().getClientes());

		tbcDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
		tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

		tbvClientes.setItems(listaClientes);

	}

}
