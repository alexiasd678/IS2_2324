package es.unican.is.GestionDeVentasTiendaRefact;
public abstract class VendedorEnPlantilla extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni) {		// WMC +1
		super(nombre, id, dni);
	}
	
	public abstract double calcularComision(double importe);
	
	@Override
	public void anhadeVenta(double importe) {
	    setTotalVentas(getTotalVentas() + importe);
	    setComision(getComision() + calcularComision(importe));
	}
}
