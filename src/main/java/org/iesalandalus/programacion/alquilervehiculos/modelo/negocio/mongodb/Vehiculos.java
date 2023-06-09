package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.result.DeleteResult;

public class Vehiculos implements IVehiculos {

	private static final String COLECCION = "vehiculos";
	private String MARCA = "marca";
	private String MODELO = "modelo";
	private String MATRICULA = "matricula";
	private String TURISMO = "turismo";
	private String AUTOBUS = "autobus";
	private String FURGONETA = "furgoneta";

	private MongoCollection<Document> coleccionVehiculos;
	private static final Vehiculos instancia = new Vehiculos();

	private Vehiculos() {
	}

	static Vehiculos getInstancia() {
		return instancia;
	}

	@Override
	public void comenzar() {
		coleccionVehiculos = MongoDB.getBD().getCollection(COLECCION);
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();
	}

	Vehiculo getVehiculo(Document documento) {
		Vehiculo vehiculo = null;
		if (documento != null) {
			vehiculo = new Turismo(documento.getString(MARCA), documento.getString(MODELO),
					documento.getInteger(vehiculo, 1111), documento.getString(MATRICULA));
		}
		return vehiculo;
	}

	Document getDocumento(Vehiculo vehiculo) {
		Document documento = null;
		if (vehiculo != null) {
			String marca = vehiculo.getMarca();
			String modelo = vehiculo.getModelo();
			String matricula = vehiculo.getMatricula();
			documento = new Document().append(MARCA, marca).append(MODELO, modelo).append(MATRICULA, matricula);
		}
		return documento;
	}

	private Bson getCriterioBusqueda(Vehiculo vehiculo) {
		return eq(MATRICULA, vehiculo.getMatricula());
	}

	@Override
	public List<Vehiculo> get() {
		List<Vehiculo> vehiculos = new ArrayList<>();
		for (Document documento : coleccionVehiculos.find()) {
			vehiculos.add(getVehiculo(documento));
		}
		return vehiculos;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehiculo nulo.");
		}
		FindOneAndReplaceOptions opciones = new FindOneAndReplaceOptions().upsert(true);
		Document resultado = coleccionVehiculos.findOneAndReplace(getCriterioBusqueda(vehiculo), getDocumento(vehiculo),
				opciones);
		if (resultado != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehiculo con esa matricula.");
		}

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		return getVehiculo(coleccionVehiculos.find(getCriterioBusqueda(vehiculo)).first());

	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehiculo nulo.");
		}
		DeleteResult resultado = coleccionVehiculos.deleteOne(getCriterioBusqueda(vehiculo));
		if (resultado.getDeletedCount() == 0) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehiculo con esa matricula.");
		}
	}

}
