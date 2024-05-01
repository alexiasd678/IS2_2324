package es.unican.is.GestionDeVentasTiendaRefact;
public class VendedorEnPracticas extends Vendedor {
	
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {		
		super(nombre, id, dni);
	}
	
	@Override
	public void anhadeVenta(double importe) {
	    setTotalVentas(getTotalVentas() + importe);
	}
}
