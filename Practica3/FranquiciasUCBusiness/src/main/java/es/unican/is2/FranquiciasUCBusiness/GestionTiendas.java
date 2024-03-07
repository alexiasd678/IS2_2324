package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.clases.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.clases.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.clases.Tienda;
import es.unican.is2.FranquiciasUCCommon.interfaces.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.interfaces.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas{

	public GestionTiendas(ITiendasDAO tiendasDAO) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * A�ade una nueva tienda
	 * @param t Tienda que se desea anhadir
	 * @return La tienda anhadida 
	 *         null si no se anhade porque ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		return null;
	}

	/**
	 * Elimina una tienda
	 * @param nombre Nombre de la tienda que se desea eliminar
	 * @return La tienda eliminada 
	 *         null si no se elimina porque no se encuentra
	 * @throws OperacionNoValidaException Si la tienda existe pero tiene empleados
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		return null;
	}

	/**
	 * Retorna una tienda dado su nombre
	 * @param nombre Nombre de la tienda
	 * @return La tienda buscada
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda tienda(String nombre) throws DataAccessException {
		return null;
	}
}
