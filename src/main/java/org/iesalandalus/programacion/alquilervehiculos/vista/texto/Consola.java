package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

@SuppressWarnings("unused")
public class Consola {

	private static final String PATRON_FECHA = "31/12/2000";
	private static final String PATRON_MES = "12/2000";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {

		System.out.println(mensaje);

		String[] subrayado = new String[mensaje.length()];
		for (int i = 0; i < subrayado.length; i++) {
			subrayado[i] = "-";
			System.out.print(subrayado[i]);
		}

		System.out.println("");

	}

	public static void mostrarMenu() {
		mostrarCabecera("GESTIÓN DE ALQUILERES DE VEHICULOS");

		for (int i = 0; i < 19; i++) {

			System.out.println((i) + " .- " + Accion.values()[i].toString());

		}

	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);

		return Entrada.cadena();
	}

	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);

		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje) {
		System.out.print(mensaje);

		LocalDate fecha;
		String fechaCadena = Entrada.cadena();

		if (fechaCadena.length() == 7) {
			fechaCadena = "01/" + fechaCadena;
		}

		// Capturar excepción al transformar

		try {
			fecha = LocalDate.parse(fechaCadena, FORMATO_FECHA);
		} catch (Exception e) {
			throw new IllegalArgumentException("ERROR: Fallo al convertir fecha");
		}

		return fecha;

	}

	public static LocalDate leerMes() {

		return leerFecha("Introduzca el mes deseado(De esta forma: " + PATRON_MES + ")");

	}

	public static Accion elegirAccion() {
		int acc = 0;

		try {

			acc = leerEntero("Elija su acción:");

		} catch (Exception e) {

			System.out.println("Valor de acción inválido");
		}

		return Accion.get(acc);
	}

	public static Cliente leerCliente() {

		return new Cliente(leerNombre(), leerCadena("Introduzca el dni:"), leerTelefono());

	}

	public static Cliente leerClienteDni() {

		return Cliente.getClienteConDni(leerCadena("Introduzca el dni:"));

	}

	public static String leerNombre() {

		return leerCadena("Introduzca el nombre: ");

	}

	public static String leerTelefono() {

		return leerCadena("Introduzca el telefono: ");

	}

	public static Vehiculo leerVehiculo() {

		Vehiculo vehiculo = null;

		String tipo = leerCadena("Escriba el tipo de vehiculo: ").toLowerCase();

		if (tipo.equals("turismo")) {

			vehiculo = new Turismo(leerCadena("Introduzca marca:"), leerCadena("Introduzca modelo:"),
					leerEntero("Introduzca cilindrada:"), leerCadena("Introduzca matricula:"));

		}

		if (tipo.equals("autobus")) {

			vehiculo = new Autobus(leerCadena("Introduzca marca:"), leerCadena("Introduzca modelo:"),
					leerEntero("Introduzca plazas:"), leerCadena("Introduzca matricula:"));

		}

		if (tipo.equals("furgoneta")) {

			vehiculo = new Furgoneta(leerCadena("Introduzca marca:"), leerCadena("Introduzca modelo:"),
					leerEntero("Introduzca peso máximo autorizado:"), leerEntero("Introduzca plazas:"),
					leerCadena("Introduzca matricula:"));

		}

		return vehiculo;

	}

	public static Vehiculo leerVehiculoMatricula() {

		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduzca matricula:"));

	}

	public static Alquiler leerAlquiler() {

		return new Alquiler(leerClienteDni(), leerVehiculoMatricula(), leerFecha("Introduzca fecha de alquiler:"));

	}

	public static LocalDate leerFechaDevolucion() {

		return leerFecha("Introduzca fecha de devolución:");

	}

}
