package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.clases.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.clases.Empleado;
import es.unican.is2.FranquiciasUCCommon.clases.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.interfaces.IEmpleadosDAO;
import es.unican.is2.FranquiciasUCCommon.interfaces.IGestionEmpleados;
import es.unican.is2.FranquiciasUCCommon.interfaces.ITiendasDAO;

public class GestionEmpleados implements IGestionEmpleados{

	public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
		// TODO Auto-generated constructor stub
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
		return null;
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
		return null;
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
		return false;
	}

	/**
	 * Retorna el empleado con el dni indicado
	 * @param dni
	 * @return El empleado con el dni indicado 
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado empleado(String dni) throws DataAccessException {
		return null;
	}
}
