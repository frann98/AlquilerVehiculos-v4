package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVista;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	public static void main(String[] args) {

		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		Vista vista = null;

		System.out.print("Introduzca la interfaz que desea usar (-vtexto o -vgrafica): ");

		String interfaz = Entrada.cadena();

		if (interfaz.equals("-vtexto")) {

			vista = FactoriaVista.TEXTO.crear();

		} else if (interfaz.equals("-vgrafica")) {

			vista = FactoriaVista.GRAFICA.crear();

		} else {

			System.out.println("Parámetro inválido, ejecutando vista predeterminada");

			vista = FactoriaVista.GRAFICA.crear();

		}

		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
		controlador.terminar();

	}
}
