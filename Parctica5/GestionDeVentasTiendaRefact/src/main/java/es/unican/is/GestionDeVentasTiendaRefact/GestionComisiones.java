package es.unican.is.GestionDeVentasTiendaRefact;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import fundamentos.Lectura;
import fundamentos.Mensaje;
import fundamentos.Menu;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {		// WMC +1
		// opciones del menu
		final int NUEVA_VENTA = 0;
		final int VENDEDOR_DEL_MES = 1;
		final int VENDEDORES = 2;

		// crea la tienda
		Tienda tienda = new Tienda("C:\\Users\\Usuario\\Documents\\UNICAN\\3º carrera\\2º cuatri\\Ingenieria de Software II\\IS2_2324\\Practica5\\GestionDeVentasTienda\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anhadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {		//WMC+1   //Ccog+1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {	//CCog +2
			case NUEVA_VENTA:		//WMC+1
				gestionarNuevVenta(tienda);
				break;

			case VENDEDOR_DEL_MES:			//WMC+1
				mostrarVendedorDelMes(tienda);
				break;

			case VENDEDORES:		//WMC+1
				mostrarVendedoresPorVentas(tienda);
				break;
			}
		}
	}

	private static void mostrarVendedoresPorVentas(Tienda tienda) {		//WMC+1
		try {
			List<Vendedor> vendedores = tienda.vendedores();
			vendedores.sort(Comparator.comparingDouble(Vendedor::getTotalVentas).reversed());
			StringBuilder mnsj = new StringBuilder();
			for (Vendedor v: vendedores) {			//WMC+1
				mnsj.append(v.getNombre()).append("( ").append(v.getId()).append(") ").append(v.getTotalVentas()).append("\n");
			}
			mensaje ("VENDEDORES", mnsj.toString());
		} catch (DataAccessException e) {		//WMC+1 //CCog +1
			mensaje ("ERROR", "No se pudo acceder a los datos");
		}
	}

	private static void mostrarVendedorDelMes(Tienda tienda) {		//WMC+1
		try {
			List<Vendedor> vendedores = tienda.vendedores();
			List<Vendedor> resultado = new LinkedList<>();
			double maxVentas = 0.0;
			for (Vendedor v: vendedores) {			//WMC+1
				if (v.getTotalVentas() > maxVentas) {		//WMC+1 //CCog +1
					maxVentas = v.getTotalVentas();
					resultado.clear();
					resultado.add(v);
				} else if (v.getTotalVentas() == maxVentas) {		//WMC+1 //CCog+1
					resultado.add(v);
				}
			}
			StringBuilder mnsj = new StringBuilder();
			for (Vendedor vn : resultado) {		//WMC+1
				mnsj.append(vn.getNombre()).append("\n");
			}
			mensaje("VENDEDOR DEL MES", mnsj.toString());
		}catch (DataAccessException e) {		//WMC+1 //CCog +1
			mensaje ("ERROR", "No se pudo acceder a los datos");
		}
	}

	private static void gestionarNuevVenta(Tienda tienda) {		//WMC+1 
		Lectura lect = new Lectura("Datos Venta");
		lect.creaEntrada("Importe", "");
		lect.esperaYCierra();
		String dni = lect.leeString("ID Vendedor");
		double importe = lect.leeDouble("Importe");
		try {
			Vendedor vendedor = tienda.buscaVendedor(dni);
	        if (vendedor != null) {		//WMC+1 //CCog +1
	            vendedor.anhadeVenta(importe);
	            mensaje("INFO", "Venta añadida correctamente");
	        } else {		//WMC+1
	            mensaje("ERROR", "El vendedor no existe");
	        }
	    } catch (DataAccessException e) {	//WMC+1 //CCog +1
	        mensaje("ERROR", "No se pudo guardar el cambio");
	    }
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {		//WMC+1 	
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
