package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public abstract class Vehiculo implements Serializable {

	// Utilizo esta expresión más compleja para una mayor seguridad
	private static final String ER_MARCA = "^[A-Z][a-z]*|[A-Z]*|^[A-Z][a-z]*\\s[A-Z][a-z]*|^[A-Z][a-z]*-[A-Z][a-z]*|^[A-Z][a-z]*[A-Z][a-z]*";
	private static final String ER_MATRICULA = "^\\d{4}[BCDFGHJKLMNÑPQRSTVWXYZ]{3}";
	protected String marca;
	protected String modelo;
	protected String matricula;

	protected Vehiculo(String marca, String modelo, String matricula) {

		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);

	}

	protected Vehiculo(Vehiculo vehiculo) {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
		}

		marca = (vehiculo.getMarca());
		modelo = (vehiculo.getModelo());
		matricula = (vehiculo.getMatricula());
	}

	public static Vehiculo copiar(Vehiculo vehiculo) {

		Vehiculo vehiculoCopiado = null;

		if (vehiculo instanceof Turismo turismo) {

			vehiculoCopiado = new Turismo(turismo);

		}
		if (vehiculo instanceof Autobus autobus) {

			vehiculoCopiado = new Autobus(autobus);

		}
		if (vehiculo instanceof Furgoneta furgoneta) {

			vehiculoCopiado = new Furgoneta(furgoneta);

		}
		return vehiculoCopiado;
	}

	public static Vehiculo getVehiculoConMatricula(String matricula) {

		return (new Turismo("Marca", "Modelo", 2500, matricula));

	}

	public String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {

		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}

		 // Esta comprobación me veo obligado a hacerla, ya que, aunque debería estar
			// cubierta por la siguiente, al intentar pasar los test, se nos dice lo
			// contrario

		if (marca.matches("") || !marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
		this.marca = marca;

	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {

		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (modelo.isBlank()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;

	}

	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) {

		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if (!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		this.matricula = matricula;

	}

	public abstract int getFactorPrecio();

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	// El método equals usa instanceof

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}