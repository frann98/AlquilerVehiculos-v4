package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.LanzadorPrincipal;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.VEstadisticas;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.VtablaAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.vBuscarAlquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.VdevolverPorAlquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.vEliminarAlquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresALQUILER.vInsertarAlquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.VdevolverPorCliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.VtablaClientes;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.vBuscarCliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.vEliminarCliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.vInsertarCliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresCLIENTE.vModificarCliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO.VdevolverPorVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO.VtablaVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO.vBuscarVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO.vEliminarVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.controladoresVEHICULO.vInsertarVehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuPrincipal extends Controlador {

	@FXML
	private Button btBuscarAlquiler;

	@FXML
	private Button btBuscarCliente;

	@FXML
	private Button btBuscarVehiculo;

	@FXML
	private Button btDevolverAlquiler;

	@FXML
	private Button btDevolverPorCliente;

	@FXML
	private Button btDevolverPorVehiculo;

	@FXML
	private Button btEliminarAlquiler;

	@FXML
	private Button btEliminarCliente;

	@FXML
	private Button btEliminarVehiculo;

	@FXML
	private Button btEstadisticasMensuales;

	@FXML
	private Button btInsertCliente;

	@FXML
	private Button btInsertarAlquiler;

	@FXML
	private Button btInsertarVehiculo;

	@FXML
	private Button btListarAlquiler;

	@FXML
	private Button btListarClientes;

	@FXML
	private Button btListarVehiculo;

	@FXML
	private Button btModificarCliente;

	@FXML
	private ImageView ivPerson;

	@FXML
	void buscarAlquiler(ActionEvent event) {

		vBuscarAlquiler vba = (vBuscarAlquiler) Controladores.get("vistas/vistasALQUILER/vBuscarAlquiler.fxml",
				"Buscar alquiler", null);

		vba.getEscenario().show();

	}

	@FXML
	void buscarCliente(ActionEvent event) {

		vBuscarCliente vbc = (vBuscarCliente) Controladores.get("vistas/vistasCLIENTE/vBuscarCliente.fxml",
				"Buscar cliente", null);

		vbc.getEscenario().show();

	}

	@FXML
	void buscarVehiculo(ActionEvent event) {

		vBuscarVehiculo vbv = (vBuscarVehiculo) Controladores.get("vistas/vistasVEHICULO/vBuscarVehiculo.fxml",
				"Buscar vehiculo", null);

		vbv.getEscenario().show();

	}

	@FXML
	void devolverAlquiler(ActionEvent event) {

		VdevolverPorAlquiler vda = (VdevolverPorAlquiler) Controladores
				.get("vistas/vistasALQUILER/vDevolverPorAlquiler.fxml", "Devolver alquiler", null);

		vda.inicializar();
		vda.getEscenario().show();

	}

	@FXML
	void devolverPorCliente(ActionEvent event) {

		VdevolverPorCliente vdc = (VdevolverPorCliente) Controladores
				.get("vistas/vistasCLIENTE/VdevolverPorCliente.fxml", "Devolver alquiler por cliente", null);

		vdc.getEscenario().show();

	}

	@FXML
	void devolverPorVehiculo(ActionEvent event) {

		VdevolverPorVehiculo vdv = (VdevolverPorVehiculo) Controladores
				.get("vistas/vistasVEHICULO/VdevolverPorVehiculo.fxml", "Devolver alquiler por cliente", null);

		vdv.getEscenario().show();

	}

	@FXML
	void eliminarAlquiler(ActionEvent event) {

		Dialogos.mostrarDialogoConfirmacion("¿Eliminar alquiler?", "Se eliminarán todos los datos del alquiler",
				getEscenario());

		vEliminarAlquiler vea = (vEliminarAlquiler) Controladores.get("vistas/vistasALQUILER/vEliminarAlquiler.fxml",
				"Eliminar alquiler", null);

		vea.getEscenario().show();

	}

	@FXML
	void eliminarCliente(ActionEvent event) {

		Dialogos.mostrarDialogoConfirmacion("¿Eliminar cliente?",
				"Se eliminarán todos los datos del cliente, incluidos sus alquileres", getEscenario());

		vEliminarCliente vec = (vEliminarCliente) Controladores.get("vistas/vistasCLIENTE/vEliminarCliente.fxml",
				"Eliminar cliente", null);

		vec.getEscenario().show();

	}

	@FXML
	void eliminarVehiculo(ActionEvent event) {

		Dialogos.mostrarDialogoConfirmacion("¿Eliminar vehiculo?",
				"Se eliminarán todos los datos del vehiculo, incluidos sus alquileres", getEscenario());

		vEliminarVehiculo vev = (vEliminarVehiculo) Controladores.get("vistas/vistasVEHICULO/vEliminarVehiculo.fxml",
				"Eliminar vehiculo", null);

		vev.getEscenario().show();

	}

	@FXML
	void insertarAlquiler(ActionEvent event) {

		vInsertarAlquiler via = (vInsertarAlquiler) Controladores.get("vistas/vistasALQUILER/vInsertarAlquiler.fxml",
				"Insertar nuevo alquiler", null);

		via.getEscenario().show();

	}

	@FXML
	void insertarCliente(ActionEvent event) {

		vInsertarCliente vic = (vInsertarCliente) Controladores.get("vistas/vistasCLIENTE/vInsertarCliente.fxml",
				"Insertar nuevo cliente", null);

		vic.getEscenario().show();

	}

	@FXML
	void insertarVehiculo(ActionEvent event) {

		vInsertarVehiculo viv = (vInsertarVehiculo) Controladores.get("vistas/vistasVEHICULO/vInsertarVehiculo.fxml",
				"Insertar nuevo vehiculo", null);

		viv.inicializar();

		viv.getEscenario().show();

	}

	@FXML
	void listarAlquiler(ActionEvent event) {

		VtablaAlquileres vta = (VtablaAlquileres) Controladores.get("vistas/vistasALQUILER/VtablaAlquileres.fxml",
				"Listado alquileres", null);

		vta.inicializar();
		vta.getEscenario().show();

	}

	@FXML
	void listarCliente(ActionEvent event) {

		VtablaClientes vtc = (VtablaClientes) Controladores.get("vistas/vistasCLIENTE/VtablaClientes.fxml",
				"Listado clientes", null);

		vtc.inicializar();
		vtc.getEscenario().show();

	}

	@FXML
	void listarVehiculos(ActionEvent event) {

		VtablaVehiculos vtv = (VtablaVehiculos) Controladores.get("vistas/vistasVEHICULO/VtablaVehiculos.fxml",
				"Listado vehiculos", null);

		vtv.inicializar();
		vtv.getEscenario().show();

	}

	@FXML
	void modificarCliente(ActionEvent event) {

		vModificarCliente vmc = (vModificarCliente) Controladores.get("vistas/vistasCLIENTE/vModificarCliente.fxml",
				"Modificar cliente", null);

		vmc.getEscenario().show();

	}

	@FXML
	void mostrarEstadisticasMensuales(ActionEvent event) {

		VEstadisticas vte = (VEstadisticas) Controladores.get("vistas/vistasALQUILER/VEstadisticas.fxml",
				"Seleccionar mes", null);

		vte.inicializar();
		vte.getEscenario().show();

	}

	@FXML
	void mostrarInfoAutor(ActionEvent event) {

		AcercaDe ad = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Informacion del autor", null);

		ad.getEscenario().show();

	}

	@FXML
	void salir(ActionEvent event) {

		LanzadorPrincipal.finalizar();

	}

}
