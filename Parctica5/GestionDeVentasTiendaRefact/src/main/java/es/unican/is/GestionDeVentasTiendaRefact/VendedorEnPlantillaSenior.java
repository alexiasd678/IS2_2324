package es.unican.is.GestionDeVentasTiendaRefact;

public class VendedorEnPlantillaSenior extends VendedorEnPlantilla{
	public VendedorEnPlantillaSenior(String nombre, String id, String dni) {
		super(nombre, id, dni);
	}

	public double calcularComisionSenior(double importe) {
        return importe * 0.01;
    }
}
