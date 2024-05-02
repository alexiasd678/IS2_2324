package es.unican.is.GestionDeVentasTiendaRefact;

public class VendedorEnPlantillaJunior extends VendedorEnPlantilla{
	public VendedorEnPlantillaJunior(String nombre, String id, String dni) {		// WMC +1
		super(nombre, id, dni);
	}
	
	@Override
	public double calcularComision(double importe) {		// WMC +1
        return importe * 0.005;
    }
}
