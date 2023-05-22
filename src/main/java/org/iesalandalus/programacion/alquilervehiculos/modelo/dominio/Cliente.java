package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {

	// Declaramos los atributos y las ER

	// Utilizo esta expresión más compleja para una mayor seguridad
	private static final String ER_NOMBRE = "^[A-Z][a-z]*|[A-Z][a-z]*\\s[A-Z][a-z]*|[A-Z][a-z]*\s[A-Z][a-z]*\s[A-Z][a-z]*";
	private static final String ER_DNI = "\\d{8}[A-Z]";
	private static final String ER_TELEFONO = "^[679]\\d{8}$";

	private String nombre;
	private String dni;
	private String telefono;

	// Creamos los constructores

	public Cliente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);

	}

	public Cliente(Cliente cliente) {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}

		nombre = (cliente.getNombre());
		dni = (cliente.getDni());
		telefono = (cliente.getTelefono());

	}

	// Generamos getters y setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!nombre.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		this.nombre = nombre;

	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
		}

		this.dni = dni;

	}

	private boolean comprobarLetraDni(String dni) {

		int numDNI = Integer.parseInt(dni.substring(0, 8));
		String letraDNI = dni.substring(8, 9);
		int resto;
		String miLetra;
		String[] asignacionLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
				"Q", "V", "H", "L", "C", "K", "E" };

		resto = numDNI % 23;

		miLetra = asignacionLetra[resto];

		return (miLetra.equals(letraDNI));

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}

		this.telefono = telefono;

	}

	public static Cliente getClienteConDni(String dni) {

		return new Cliente("Nombre Apellido", dni, "666555444");

	}

	// Generamos hashCode, Equals y toString

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono);
	}

}
