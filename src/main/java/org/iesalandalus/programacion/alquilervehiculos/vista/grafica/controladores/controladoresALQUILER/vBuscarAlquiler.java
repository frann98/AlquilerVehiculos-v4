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

public class vBuscarAlquiler extends Controlador {

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private TextField tfDniAlquilerBuscar;

	@FXML
	private TextField tfMatriculaAlquilerBuscar;

	@FXML
	void cancelar(ActionEvent event) {

		tfDniAlquilerBuscar.clear();
		tfMatriculaAlquilerBuscar.clear();

		this.getEscenario().close();

	}

	@FXML
	void buscarAlquilerMatriculaFecha(ActionEvent event) {

		try {

			Alquiler buscado = new Alquiler(new Cliente("Nombre", tfDniAlquilerBuscar.getText(), "666666666"),
					new Turismo("Marca", "Modelo", 1111, tfMatriculaAlquilerBuscar.getText()),
					dpFechaAlquiler.getValue());

			if (VistaGrafica.getInstancia().getControlador().buscar(buscado).getCliente().getNombre()
					.equals(buscado.getCliente().getNombre())) {
				Dialogos.mostrarDialogoInformacion("Alquiler no encontrado",
						"No se ha encontrado ningún alquiler con los datos introducidos", getEscenario());
			} else {
				Dialogos.mostrarDialogoInformacion("Alquiler encontrado",
						VistaGrafica.getInstancia().getControlador().buscar(buscado).toString(), getEscenario());
			}

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());

		}

	}

}
