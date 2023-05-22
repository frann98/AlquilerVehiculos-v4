package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Vehiculos implements IVehiculos, Serializable {

	private File FICHERO_VEHICULOS = new File("datos" + File.separator + "vehiculos.xml");

	private String RAIZ = "vehiculos";
	private String VEHICULO = "vehiculo";
	private String MARCA = "marca";
	private String MODELO = "modelo";
	private String MATRICULA = "matricula";
	private String CILINDRADA = "cilindrada";
	private String PLAZAS = "plazas";
	private String PMA = "pma";
	private String TIPO = "tipo";
	private String TURISMO = "turismo";
	private String AUTOBUS = "autobus";
	private String FURGONETA = "furgoneta";

	private List<Vehiculo> coleccionVehiculos = new ArrayList<>();

	private static Vehiculos instancia = new Vehiculos();

	private Vehiculos() {

		coleccionVehiculos = new ArrayList<>();

	}

	static Vehiculos getInstancia() {

		return instancia;

	}

	@Override
	public void comenzar() {

		// Leer xml y pasarlo a dom
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_VEHICULOS));

	}

	private void leerDom(Document documentoXML) {

		// Toma todos los nodos vehiculo y los procesa para agregarlos a la coleccion
		NodeList vehiculos = documentoXML.getElementsByTagName(VEHICULO);
		// llama a getVehiculo en cada nodo
		for (int i = 0; i < vehiculos.getLength(); i++) {
			Node vehiculo = vehiculos.item(i);
			if (vehiculo.getNodeType() == Node.ELEMENT_NODE) {
				instancia.coleccionVehiculos.add(getVehiculo((Element) vehiculo));

			}
		}
		System.out.println("Lista de objetos escrita correctamente.");

	}

	private Vehiculo getVehiculo(Element elemento) {

		// convierte los elementos(nodos) en vehiculo
		Vehiculo elemental = null;
		String marca = (elemento).getAttribute(MARCA);
		String matricula = (elemento).getAttribute(MATRICULA);
		String modelo = (elemento).getAttribute(MODELO);
		String tipo = (elemento).getAttribute(TIPO);

		if (tipo.equals(TURISMO)) {

			String cilindrada = (elemento).getAttribute(CILINDRADA);
			elemental = new Turismo(marca, modelo, Integer.parseInt(cilindrada), matricula);
		}

		if (tipo.equals(AUTOBUS)) {

			String plazas = (elemento).getAttribute(PLAZAS);
			elemental = new Autobus(marca, modelo, Integer.parseInt(plazas), matricula);
		}

		if (tipo.equals(FURGONETA)) {

			String plazas = (elemento).getAttribute(PLAZAS);
			String pma = (elemento).getAttribute(PMA);
			elemental = new Furgoneta(marca, modelo, Integer.parseInt(pma), Integer.parseInt(plazas), matricula);
		}

		return elemental;

	}

	@Override
	public void terminar() {

		// pasar la coleccion al fichero

		// llama a crear dom

		// con utilidades le decimos de escribir

		Document dom = crearDom();

		UtilidadesXml.escribirXmlAFichero(dom, FICHERO_VEHICULOS);

	}

	private Document crearDom() {

		// crear el dom que contiene los nodos cliente de la coleccion

		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Vehiculo vehiculo : instancia.coleccionVehiculos) {
				Element elementoVehiculo = getElemento(documentoXml, vehiculo);
				documentoXml.getDocumentElement().appendChild(elementoVehiculo);
			}
		}
		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Vehiculo vehiculo) {

		String tipoV = TipoVehiculo.get(vehiculo).toString();

		Element elementoVehiculo = documentoXml.createElement(VEHICULO);
		elementoVehiculo.setAttribute(MARCA, vehiculo.getMarca());
		elementoVehiculo.setAttribute(MATRICULA, vehiculo.getMatricula());
		elementoVehiculo.setAttribute(MODELO, vehiculo.getModelo());
		elementoVehiculo.setAttribute(TIPO, tipoV);

		if (tipoV.equals(TURISMO)) {

			elementoVehiculo.setAttribute(CILINDRADA, String.valueOf(((Turismo) vehiculo).getCilindrada()));
		}

		if (tipoV.equals(AUTOBUS)) {

			elementoVehiculo.setAttribute(PLAZAS, String.valueOf(((Autobus) vehiculo).getPlazas()));
		}

		if (tipoV.equals(FURGONETA)) {

			elementoVehiculo.setAttribute(PLAZAS, String.valueOf(((Furgoneta) vehiculo).getPlazas()));
			elementoVehiculo.setAttribute(PMA, String.valueOf(((Furgoneta) vehiculo).getPma()));
		}

		return elementoVehiculo;

	}

	@Override
	public List<Vehiculo> get() {

		List<Vehiculo> copia = new ArrayList<>();

		for (int i = 0; i < instancia.coleccionVehiculos.size(); i++) {

			copia.add(instancia.coleccionVehiculos.get(i));
		}

		return copia;

	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}

		if (instancia.coleccionVehiculos.contains(vehiculo)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");

		}

		instancia.coleccionVehiculos.add(vehiculo);
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {

		int indice = instancia.coleccionVehiculos.indexOf(vehiculo);
		Vehiculo buscado = null;

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}

		if (indice != -1) {

			buscado = instancia.coleccionVehiculos.get(indice);

		}
		return buscado;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}

		if (!instancia.coleccionVehiculos.contains(vehiculo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");

		}

		instancia.coleccionVehiculos.remove(vehiculo);
	}

}
