package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.clases.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.clases.Empleado;
import es.unican.is2.FranquiciasUCCommon.clases.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.clases.Tienda;
import es.unican.is2.FranquiciasUCCommon.interfaces.IEmpleadosDAO;
import es.unican.is2.FranquiciasUCCommon.interfaces.IGestionAltasBajas;
import es.unican.is2.FranquiciasUCCommon.interfaces.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.interfaces.ITiendasDAO;

public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas{
	
	private IEmpleadosDAO empleados;
	private ITiendasDAO tiendas;
	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		this.empleados = empleadosDAO;
		this.tiendas = tiendasDAO;
	}

	/**
	 * A�ade un nuevo empleado a una tienda
	 * @param e Empleado que se quiere a�adir
	 * @param nombre Nombre de la tienda
	 * @return El empleado a�adido 
	 *         null si no se anhade porque no existe la tienda
	 * @throws OperacionNoValidaException Si el empleado ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda t = tiendas.tiendaPorNombre(nombre);
		if (t == null) {
			return null;
		}
		Empleado empleado = empleados.crearEmpleado(e);
		if (empleado == null) {
			throw new OperacionNoValidaException("No se pueden crear dos empleados con el mismo dni");
		}
		t.getEmpleados().add(empleado);
		tiendas.modificarTienda(t);
		return empleado;
	}

	/**
	 * Elimina un empleado de una tienda
	 * @param dni DNI del empleado
	 * @param nombre Nombre de la tienda
	 * @return El empleado eliminado 
	 *         null si el empleado o la tienda no existen
	 * @throws OperacionNoValidaException Si el empleado no pertenece a la tienda indicada
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		Tienda t = tiendas.tiendaPorNombre(nombre);
		if (t == null) {
			return null;
		}
		if (t.buscaEmpleado(dni) == null) {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda indicada");
		}
		Empleado empleado = empleados.eliminarEmpleado(dni);
		t.getEmpleados().remove(empleado);
		if (empleado == null) {
			return null;
		}
		tiendas.modificarTienda(t);
		return empleado;
	}

	/**
	 * Traslada un empleado de una tienda a otra
	 * @param dni DNI del empleado
	 * @param actual Nombre de la tienda actual del empleado
	 * @param destino Nombre de la tienda destino del empleado
	 * @return true si se traslada al empleado exitosamente 
	 *         false si no se traslada porque el empleado o alguna de las tiendas no existen
	 * @throws OperacionNoValidaException Si el empleado existe pero no pertenece a la
	 *                             tienda actual
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		Empleado e = empleados.empleado(dni);
		if (e == null) {
			return false;
		}
		Tienda ta = tiendas.tiendaPorNombre(actual);
		if (ta == null) {
			return false;
		}
		Tienda td = tiendas.tiendaPorNombre(destino);
		if (td == null) {
			return false;
		}
		if (ta.buscaEmpleado(dni) == null) {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda actual");
		}
		if (td.getEmpleados().add(e) && ta.getEmpleados().remove(e)) {
			tiendas.modificarTienda(ta);
			tiendas.modificarTienda(td);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Retorna el empleado con el dni indicado
	 * @param dni
	 * @return El empleado con el dni indicado 
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado empleado(String dni) throws DataAccessException {
		Empleado e = empleados.empleado(dni);
		return e;
	}

	@Override
	/**
	 * Registrar la baja medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de baja 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean bajaMedica(String dni) throws DataAccessException {
		Empleado e = empleados.empleado(dni);
		if (e == null) {
			return false;
		}
		e.darDeBaja();
		empleados.modificarEmpleado(e);
		return true;
	}

	@Override
	/**
	 * Registrar el alta medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de alta 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean altaMedica(String dni) throws DataAccessException {
		Empleado e = empleados.empleado(dni);
		if (e == null) {
			return false;
		}
		e.darDeAlta();
		empleados.modificarEmpleado(e);
		return true;
	}
}
