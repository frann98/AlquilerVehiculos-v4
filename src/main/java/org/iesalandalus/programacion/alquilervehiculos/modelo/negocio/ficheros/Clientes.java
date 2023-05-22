package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes, Serializable {

	private File FICHERO_CLIENTES = new File("datos" + File.separator + "clientes.xml");

	private String RAIZ = "clientes";
	private String CLIENTE = "cliente";
	private String NOMBRE = "nombre";
	private String DNI = "dni";
	private String TELEFONO = "telefono";

	private List<Cliente> coleccionClientes = new ArrayList<>();

	private static Clientes instancia = new Clientes();

	private Clientes() {

		coleccionClientes = new ArrayList<>();

	}

	static Clientes getInstancia() {

		return instancia;

	}

	@Override
	public void comenzar() {

		// Leer xml y pasarlo a dom
		leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_CLIENTES));

	}

	private void leerDom(Document documentoXML) {

		// Toma todos los nodos cliente y los procesa para agregarlos a la coleccion
		NodeList clientes = documentoXML.getElementsByTagName(CLIENTE);
		// llama a GetCliente en cada nodo
		for (int i = 0; i < clientes.getLength(); i++) {
			Node cliente = clientes.item(i);
			if (cliente.getNodeType() == Node.ELEMENT_NODE) {
				instancia.coleccionClientes.add(getCliente((Element) cliente));

			}
		}
		System.out.println("Lista de objetos escrita correctamente.");

	}

	private Cliente getCliente(Element elemento) {

		// convierte los elementos(nodos) en cliente
		String nombre = (elemento).getAttribute(NOMBRE);
		String dni = (elemento).getAttribute(DNI);
		String telefono = (elemento).getAttribute(TELEFONO);

		return new Cliente(nombre, dni, telefono);

	}

	@Override
	public void terminar() {

		// pasar la coleccion al fichero

		// llama a crear dom

		// con utilidades le decimos de escribir

		Document dom = crearDom();

		UtilidadesXml.escribirXmlAFichero(dom, FICHERO_CLIENTES);

	}

	private Document crearDom() {

		// crear el dom que contiene los nodos cliente de la coleccion

		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
			for (Cliente cliente : instancia.coleccionClientes) {
				Element elementoCliente = getElemento(documentoXml, cliente);
				documentoXml.getDocumentElement().appendChild(elementoCliente);
			}
		}
		return documentoXml;

	}

	private Element getElemento(Document documentoXml, Cliente cliente) {

		Element elementoCliente = documentoXml.createElement(CLIENTE);
		elementoCliente.setAttribute(DNI, cliente.getDni());
		elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
		elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
		return elementoCliente;

	}

	@Override
	public List<Cliente> get() {

		List<Cliente> copia = new ArrayList<>();

		for (int i = 0; i < instancia.coleccionClientes.size(); i++) {

			copia.add(instancia.coleccionClientes.get(i));
		}

		return copia;

	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (instancia.coleccionClientes.contains(cliente)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");

		}

		instancia.coleccionClientes.add(cliente);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		int indice = instancia.coleccionClientes.indexOf(cliente);

		if (indice == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");

		}

		Cliente cli = instancia.coleccionClientes.get(indice);

		if (nombre != null && !nombre.isBlank()) {

			cli.setNombre(nombre);

		}
		if (telefono != null && !telefono.isBlank()) {

			cli.setTelefono(telefono);

		}

	}

	@Override
	public Cliente buscar(Cliente cliente) {

		int indice = instancia.coleccionClientes.indexOf(cliente);
		Cliente buscado = null;

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

		if (indice != -1) {

			buscado = instancia.coleccionClientes.get(indice);

		}
		return buscado;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		if (!instancia.coleccionClientes.contains(cliente)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");

		}

		instancia.coleccionClientes.remove(cliente);
	}

}
