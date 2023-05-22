package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class VEstadisticas extends Controlador {

	private String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
			"Octubre", "Noviembre", "Diciembre", "Seleccionar" };

	private String mes = null;

	@FXML
	private Button btAceptar;

	@FXML
	private Button btCancelar;

	@FXML
	private ComboBox<String> cbMeses;

	@FXML
	void aceptarYmostrarEstadisticas(ActionEvent event) {

		Calendar now = Calendar.getInstance();
		int anyo = now.get(Calendar.YEAR);
		String anyoString = String.valueOf(anyo);

		String fechaCadena = "01/" + mes + "/" + anyoString;

		LocalDate fecha = LocalDate.parse(fechaCadena, DateTimeFormatter.ofPattern("dd/MM/uuuu"));

		List<Alquiler> listaAlquileres = VistaGrafica.getInstancia().getControlador().getAlquileres();

		int turismos = 0;
		int furgonetas = 0;
		int autobuses = 0;

		for (Alquiler listaAlquilere : listaAlquileres) {

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.TURISMO)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == fecha.getMonthValue()) {

				turismos++;

			}

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.FURGONETA)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == fecha.getMonthValue()) {

				furgonetas++;

			}

			if (TipoVehiculo.get(listaAlquilere.getVehiculo()).equals(TipoVehiculo.AUTOBUS)
					&& listaAlquilere.getFechaAlquiler().getMonthValue() == fecha.getMonthValue()) {

				autobuses++;

			}

		}

		String mensaje = "En el mes de " + cbMeses.getValue() + " se alquilaron: " + autobuses + " autobuses, "
				+ furgonetas + " furgonetas y " + turismos + " turismos";

		Dialogos.mostrarDialogoInformacion("Estadisticas", mensaje, getEscenario());

	}

	@FXML
	void cancelar(ActionEvent event) {

		cbMeses.setValue(null);
		this.getEscenario().close();

	}

	public void inicializar() {

		cbMeses.getItems().addAll(meses);
		cbMeses.setValue(meses[12]);

		cbMeses.setOnAction(event -> {

			switch (cbMeses.getValue()) {
			case "Enero":

				mes = "01";

				break;
			case "Febrero":

				mes = "02";

				break;
			case "Marzo":

				mes = "03";

				break;
			case "Abril":

				mes = "04";

				break;
			case "Mayo":

				mes = "05";

				break;
			case "Junio":

				mes = "06";

				break;
			case "Julio":

				mes = "07";

				break;
			case "Agosto":

				mes = "08";

				break;
			case "Septiembre":

				mes = "09";

				break;
			case "Octubre":

				mes = "10";

				break;
			case "Noviembre":

				mes = "11";

				break;
			case "Diciembre":

				mes = "12";

				break;

			default:
				// null
				break;
			}

		});
	}

}
