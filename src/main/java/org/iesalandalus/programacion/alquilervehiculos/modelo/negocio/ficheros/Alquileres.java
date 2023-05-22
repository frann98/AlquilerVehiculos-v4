package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres, Serializable {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	private File FICHERO_ALQUILERES = new File("datos" + File.separator + "alquileres.xml");

	private String RAIZ = "alquileres";
	private String ALQUILER = "alquiler";
	private String CLIENTE = "cliente";
	private String VEHICULO = "vehiculo";
	private String FECHA_ALQUILER = "fechaAlquiler";
	private String FECHA_DEVOLUCION = "fechaDevolucion";

	private List<Alquiler> coleccionAlquileres = new ArrayList<>();

	private static Alquileres instancia = new Alquileres();

	private Alquileres() {

		coleccionAlquileres = new ArrayList<>();

	}

	static Alquileres getInstancia() {

		return instancia;

	}

	@Override
	public void comenzar() {

		// Leer xml y pasarlo a dom
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES));

	}

	private void leerDom(Document documentoXML) {

		// Toma todos los nodos alquiler y los procesa para agregarlos a la coleccion
		NodeList alquileres = documentoXML.getElementsByTagName(ALQUILER);
		// llama a getAlquiler en cada nodo
		for (int i = 0; i < alquileres.getLength(); i++) {
			Node alquiler = alquileres.item(i);
			if (alquiler.getNodeType() == Node.ELEMENT_NODE) {
				instancia.coleccionAlquileres.add(getAlquiler((Element) alquiler));

			}
		}
		System.out.println("Lista de objetos escrita correctamente.");

	}

	private Alquiler getAlquiler(Element elemento) {

		Alquiler alquiler = null;

		// convierte los elementos(nodos) en alquiler
		String cliente = (elemento).getAttribute(CLIENTE);
		String fechaAlquiler = (elemento).getAttribute(FECHA_ALQUILER).formatted(FORMATO_FECHA);
		String vehiculo = (elemento).getAttribute(VEHICULO);
		String fechaDevolucion = "";
		if ((elemento).getAttribute(FECHA_DEVOLUCION) != null) {

			fechaDevolucion = (elemento).getAttribute(FECHA_DEVOLUCION).formatted(FORMATO_FECHA);
		}

		Cliente cli = Clientes.getInstancia().buscar(new Cliente("Nombre", cliente, "666666666"));

		Vehiculo veh = Vehiculos.getInstancia().buscar(new Turismo("Marca", "Modelo", 1111, vehiculo));

		LocalDate fechaAlq = LocalDate.parse(fechaAlquiler, FORMATO_FECHA);

		alquiler = new Alquiler(cli, veh, fechaAlq);

		LocalDate fechaDev = null;

		if (!fechaDevolucion.equals("")) {

			fechaDev = LocalDate.parse(fechaDevolucion, FORMATO_FECHA);

			try {
				alquiler.devolver(fechaDev);
			} catch (OperationNotSupportedException e) {
				e.printStackTrace();
			}
		}

		return alquiler;

	}

	@Override
	public void terminar() {

		// pasar la coleccion al fichero

		// llama a crear dom

		// con utilidades le decimos de escribir

		Document dom = crearDom();

		UtilidadesXml.escribirXmlAFichero(dom, FICHERO_ALQUILERES);

	}

	private Document crearDom() {

		// crear el dom que contiene los nodos cliente de la coleccion

		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Alquiler alquiler : instancia.coleccionAlquileres) {
				Element elementoAlquiler = getElemento(documentoXml, alquiler);
				documentoXml.getDocumentElement().appendChild(elementoAlquiler);
			}
		}
		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {

		Element elementoCliente = documentoXml.createElement(ALQUILER);
		elementoCliente.setAttribute("cliente", alquiler.getCliente().getDni());
		elementoCliente.setAttribute("fechaAlquiler", alquiler.getFechaAlquiler().format(FORMATO_FECHA));
		if (alquiler.getFechaDevolucion() != null) {

			elementoCliente.setAttribute("fechaDevolucion", alquiler.getFechaDevolucion().format(FORMATO_FECHA));
		}
		elementoCliente.setAttribute("vehiculo", alquiler.getVehiculo().getMatricula());
		return elementoCliente;

	}

	@Override
	public List<Alquiler> get() {

		List<Alquiler> copia = new ArrayList<>();

		for (int i = 0; i < instancia.coleccionAlquileres.size(); i++) {

			copia.add(instancia.coleccionAlquileres.get(i));
		}

		return copia;

	}

	@Override
	public List<Alquiler> get(Cliente cliente) {

		List<Alquiler> alquileresCliente = new ArrayList<>();

		for (int i = 0; i < instancia.coleccionAlquileres.size(); i++) {

			if (instancia.coleccionAlquileres.get(i).getCliente().equals(cliente)) {
				alquileresCliente.add(instancia.coleccionAlquileres.get(i));
			}

		}

		return alquileresCliente;

	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {

		List<Alquiler> alquileresVehiculo = new ArrayList<>();

		for (int i = 0; i < instancia.coleccionAlquileres.size(); i++) {

			if (instancia.coleccionAlquileres.get(i).getVehiculo().equals(vehiculo)) {
				alquileresVehiculo.add(instancia.coleccionAlquileres.get(i));
			}

		}

		return alquileresVehiculo;

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());

		instancia.coleccionAlquileres.add(alquiler);
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler element : instancia.coleccionAlquileres) {

			// Cliente con fecha nula

			if (element.getCliente().equals(cliente)
					&& element.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			}

			// Cliente con fecha posterior

			if (element.getCliente().equals(cliente)
					&& element.getFechaDevolucion() != null
					&& (element.getFechaDevolucion().isAfter(fechaAlquiler)
							|| element.getFechaDevolucion().isEqual(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
			}

			// Vehiculo con fecha nula

			if (element.getVehiculo().equals(vehiculo)
					&& element.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
			}

			// Vehiculo con fecha posterior

			if (element.getVehiculo().equals(vehiculo)
					&& element.getFechaDevolucion() != null
					&& (element.getFechaDevolucion().isAfter(fechaAlquiler)
							|| element.getFechaDevolucion().isEqual(fechaAlquiler))) {
				throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
			}
		}
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		Alquiler abierto = getAlquilerAbierto(cliente);

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		if (abierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");

		}

		abierto.devolver(fechaDevolucion);

	}

	@SuppressWarnings("rawtypes")
	private Alquiler getAlquilerAbierto(Cliente cliente) {

		for (Iterator it = instancia.coleccionAlquileres.iterator(); it.hasNext();) {
			Alquiler alquiler = (Alquiler) it.next();

			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {

				return alquiler;

			}

		}

		return null;
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		Alquiler abierto = getAlquilerAbierto(vehiculo);

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		if (abierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");

		}

		abierto.devolver(fechaDevolucion);

	}

	@SuppressWarnings("rawtypes")
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {

		for (Iterator it = instancia.coleccionAlquileres.iterator(); it.hasNext();) {
			Alquiler alquiler = (Alquiler) it.next();

			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {

				return alquiler;

			}

		}

		return null;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {

		int indice = instancia.coleccionAlquileres.indexOf(alquiler);
		Alquiler buscado = null;

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		if (indice != -1) {

			buscado = instancia.coleccionAlquileres.get(indice);

		}
		return buscado;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (!instancia.coleccionAlquileres.contains(alquiler)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");

		}

		instancia.coleccionAlquileres.remove(alquiler);
	}

}
