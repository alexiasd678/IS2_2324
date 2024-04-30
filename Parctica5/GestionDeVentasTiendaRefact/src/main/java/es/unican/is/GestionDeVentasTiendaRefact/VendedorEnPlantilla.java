package es.unican.is.GestionDeVentasTiendaRefact;
public class VendedorEnPlantilla extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni) {		// WMC +1
		super(nombre, id, dni);
	}
	
	@Override
	public boolean equals(Object obj) {		// WMC +1
		if (!(obj instanceof VendedorEnPlantilla)) 		// WMC +1 // CCog +1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDNI().equals(getDNI()));
	}
}
