package es.unican.is.GestionDeVentasTiendaRefact;

public class VendedorEnPlantillaJunior extends VendedorEnPlantilla{
	public VendedorEnPlantillaJunior(String nombre, String id, String dni) {
		super(nombre, id, dni);
	}
	
	@Override
	public double calcularComision(double importe) {
        return importe * 0.005;
    }
}
