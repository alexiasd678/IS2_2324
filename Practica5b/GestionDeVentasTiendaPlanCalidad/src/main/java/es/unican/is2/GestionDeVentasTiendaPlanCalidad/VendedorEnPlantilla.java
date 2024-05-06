package es.unican.is2.GestionDeVentasTiendaPlanCalidad;
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
	
	public abstract double calcularComision(double importe);	// WMC +1
	
	@Override
	public void anhadeVenta(double importe) {		// WMC +1
	    setTotalVentas(getTotalVentas() + importe);
	    setComision(getComision() + calcularComision(importe));
	}
}
