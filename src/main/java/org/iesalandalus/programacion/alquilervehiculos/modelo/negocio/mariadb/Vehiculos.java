package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

public class Vehiculos implements IVehiculos {

	private String MARCA = "marca";
	private String MODELO = "modelo";
	private String MATRICULA = "matricula";
	private String TURISMO = "turismo";
	private String AUTOBUS = "autobus";
	private String FURGONETA = "furgoneta";
	private static final String ERROR = "ERROR: ";

	private Connection conexion;
	private static final Vehiculos instancia = new Vehiculos();

	private Vehiculos() {
	}

	static Vehiculos getInstancia() {
		return instancia;
	}

	@Override
	public void comenzar() {
		conexion = MariaDB.getConexion();
	}

	@Override
	public void terminar() {
		MariaDB.cerrarConexion();
	}

	private Vehiculo getVehiculo(ResultSet fila) throws SQLException {
		String marca = fila.getString(MARCA);
		String modelo = fila.getString(MODELO);
		String matricula = fila.getString(MATRICULA);
		return Vehiculo.copiar(new Turismo(marca, modelo, 1111, matricula));
	}

	private void prepararSentencia(PreparedStatement sentencia, Vehiculo vehiculo) throws SQLException {
		sentencia.setString(1, vehiculo.getMarca());
		sentencia.setString(2, vehiculo.getModelo());
		sentencia.setString(3, vehiculo.getMatricula());

		if (vehiculo instanceof Autobus) {

			sentencia.setString(4, AUTOBUS);
			sentencia.setString(5, null);
			sentencia.setInt(6, ((Autobus) vehiculo).getPlazas());
			sentencia.setString(7, null);

		}

		if (vehiculo instanceof Furgoneta) {

			sentencia.setString(4, FURGONETA);
			sentencia.setString(5, null);
			sentencia.setInt(6, ((Furgoneta) vehiculo).getPlazas());
			sentencia.setInt(7, ((Furgoneta) vehiculo).getPma());

		}

		if (vehiculo instanceof Turismo) {

			sentencia.setString(4, TURISMO);
			sentencia.setInt(5, ((Turismo) vehiculo).getCilindrada());
			sentencia.setInt(6, (Integer) null);
			sentencia.setInt(7, (Integer) null);

		}
	}

	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> vehiculos = new ArrayList<>();
		try (Statement sentencia = conexion.createStatement()) {
			ResultSet filas = sentencia.executeQuery("select * from vehiculos");
			while (filas.next()) {
				vehiculos.add(getVehiculo(filas));
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(ERROR + e.getMessage());
		}
		return vehiculos;

	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		}
		try (PreparedStatement sentencia = conexion
				.prepareStatement("insert into vehiculos values (?, ?, ?, ?, ?, ?, ?)")) {
			prepararSentencia(sentencia, vehiculo);
			sentencia.execute();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matricula.");
		} catch (SQLException e) {
			throw new IllegalArgumentException(ERROR + e.getMessage());
		}

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehiculo nulo.");
		}
		try (PreparedStatement sentencia = conexion.prepareStatement("select * from vehiculos where matricula = ? ")) {
			sentencia.setString(1, vehiculo.getMatricula());
			ResultSet filas = sentencia.executeQuery();
			vehiculo = filas.first() ? getVehiculo(filas) : null;
		} catch (SQLException e) {
			throw new IllegalArgumentException(ERROR + e.getMessage());
		}
		return vehiculo;
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		}
		try (PreparedStatement sentencia = conexion.prepareStatement("delete from vehiculos where matricula = ?")) {
			sentencia.setString(1, vehiculo.getMatricula());
			int filas = sentencia.executeUpdate();
			if (filas == 0) {
				throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matricula.");
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(ERROR + e.getMessage());
		}
	}

}
