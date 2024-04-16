package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.clases.DataAccessException;
import es.unican.is2.FranquiciasUCCommon.clases.OperacionNoValidaException;
import es.unican.is2.FranquiciasUCCommon.clases.Tienda;
import es.unican.is2.FranquiciasUCCommon.interfaces.IGestionTiendas;
import es.unican.is2.FranquiciasUCCommon.interfaces.ITiendasDAO;

public class GestionTiendas implements IGestionTiendas{
	
	private ITiendasDAO tiendas;
	public GestionTiendas(ITiendasDAO tiendasDAO) {
		this.tiendas = tiendasDAO;
	}

	/**
	 * A�ade una nueva tienda
	 * @param t Tienda que se desea anhadir
	 * @return La tienda anhadida 
	 *         null si no se anhade porque ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		if (tiendas.tiendas().contains(t)){
			return null;
		}
		Tienda nuevaTienda = tiendas.crearTienda(t);
		return nuevaTienda;
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
		Tienda t = tienda(nombre);
		if (!tiendas.tiendas().contains(t)) {
			return null;
		}
		if (t.getEmpleados().isEmpty()) {
			throw new OperacionNoValidaException("La tienda no tiene empleados");
		}
		tiendas.eliminarTienda(t.getId());
		return t;
	}

	/**
	 * Retorna una tienda dado su nombre
	 * @param nombre Nombre de la tienda
	 * @return La tienda buscada
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Tienda tienda(String nombre) throws DataAccessException {
		Tienda t = tiendas.tiendaPorNombre(nombre);
		if (t == null) {
			return null;
		}
		return t;
	}
}
