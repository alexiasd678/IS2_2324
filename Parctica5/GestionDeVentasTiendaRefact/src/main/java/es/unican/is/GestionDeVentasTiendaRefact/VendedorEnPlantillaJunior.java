package es.unican.is.GestionDeVentasTiendaRefact;

public class VendedorEnPlantillaJunior extends VendedorEnPlantilla{
	public VendedorEnPlantillaJunior(String nombre, String id, String dni) {
		super(nombre, id, dni);
	}

	public double calcularComisionJunior(double importe) {
        return importe * 0.005;
    }
}
