package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.MenuPrincipal;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.application.Application;
import javafx.stage.Stage;

public class LanzadorPrincipal extends Application {

	@Override
	public void start(Stage escenarioPrincipal) {

		try {

			MenuPrincipal mp = (MenuPrincipal) Controladores.get("vistas/MenuPrincipal.fxml",
					"Alquiler de Vehiculos v3.0", null);

			mp.getEscenario().show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void comenzar() {
		launch(LanzadorPrincipal.class);
	}

	public static void finalizar() {
		javafx.application.Platform.exit();
	}

}
