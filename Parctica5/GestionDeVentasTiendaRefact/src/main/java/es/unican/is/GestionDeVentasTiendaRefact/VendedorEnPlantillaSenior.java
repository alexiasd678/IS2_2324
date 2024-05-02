package es.unican.is.GestionDeVentasTiendaRefact;

public class VendedorEnPlantillaSenior extends VendedorEnPlantilla{
	public VendedorEnPlantillaSenior(String nombre, String id, String dni) {	// WMC +1
		super(nombre, id, dni);
	}

	@Override
	public double calcularComision(double importe) {		// WMC +1
        return importe * 0.01;
    }
}
