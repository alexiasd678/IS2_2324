package es.unican.is.GestionDeVentasTiendaRefact;
public class VendedorEnPracticas extends Vendedor {
	
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {		// WMC +1
		super(nombre, id, dni);
	}
	
	@Override
	public void anhadeVenta(double importe) {		// WMC +1
	    setTotalVentas(getTotalVentas() + importe);
	}
}
