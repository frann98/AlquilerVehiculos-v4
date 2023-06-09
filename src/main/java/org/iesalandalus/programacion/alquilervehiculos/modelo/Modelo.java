package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public abstract class Modelo {

	private IClientes clientes;
	private IVehiculos vehiculos;
	private IAlquileres alquileres;
	private IFuenteDatos fuenteDatos;

	protected Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {

		fuenteDatos = factoriaFuenteDatos.crear();
		setFuenteDatos(fuenteDatos);

		clientes = fuenteDatos.crearClientes();
		vehiculos = fuenteDatos.crearVehiculos();
		alquileres = fuenteDatos.crearAlquileres();

	}

	protected IClientes getClientes() {
		return clientes;
	}

	protected IVehiculos getVehiculos() {
		return vehiculos;
	}

	protected IAlquileres getAlquileres() {
		return alquileres;
	}

	protected void setFuenteDatos(IFuenteDatos fuenteDatos) {

		if (fuenteDatos == null) {
			throw new IllegalArgumentException("ERROR: La fuente de datos no puede ser nula");
		}

		this.fuenteDatos = fuenteDatos;

	}

	@SuppressWarnings("unused")
	public void comenzar() {

		clientes.comenzar();
		vehiculos.comenzar();
		alquileres.comenzar();

	}

	public void terminar() {
		clientes.terminar();
		vehiculos.terminar();
		alquileres.terminar();
		// System.out.println("El modelo ha terminado");
	}

	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;

	public abstract void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;

	public abstract Cliente buscar(Cliente cliente);

	public abstract Vehiculo buscar(Vehiculo vehiculo);

	public abstract Alquiler buscar(Alquiler alquiler);

	public abstract void modificar(Cliente cliente, String nombre, String telefono)
			throws OperationNotSupportedException;

	public abstract void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	public abstract void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;

	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;

	public abstract void borrar(Vehiculo turismo) throws OperationNotSupportedException;

	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;

	public abstract List<Cliente> getListaClientes();

	public abstract List<Vehiculo> getListaVehiculos();

	public abstract List<Alquiler> getListaAlquileres();

	public abstract List<Alquiler> getListaAlquileres(Cliente cliente);

	public abstract List<Alquiler> getListaAlquileres(Vehiculo turismo);

}
