package es.unican.is.GestionDeVentasTiendaRefact;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;
	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {	
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {		
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {	
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException {	
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {	
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {	
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {	
		Vendedor v = buscaVendedor(id);
		if (v == null) {	
			return false;
		}
		double comision = calculaComision(v, importe);
		v.anhadeVenta(importe);
		v.setComision(v.getComision()+comision);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {		
		cargaDatos();
		for (Vendedor v : lista) {	
			if (v.getId().equals(id)) {		
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException {		
		cargaDatos();
		return lista;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {  	
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {		
			if (v instanceof VendedorEnPracticas) {		
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) {	
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.tipo().equals(TipoVendedor.Junior))		
					junior.add(vp);
				else			
					senior.add(vp);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) {		
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.getDNI()
						+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getComision());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) {		
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v2.getNombre() + " Id: " + v2.getId() + " DNI: " + v2.getDNI()
						+ " TotalVentasMes: " + v2.getTotalVentas() + " TotalComision: "+ v2.getComision());
			}
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) {			
				VendedorEnPracticas v3 = (VendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDNI()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) {		
			throw new DataAccessException();

		} finally {
			if (out != null)		
				out.close();
		}
	}
	
	private double calculaComision(Vendedor vendedor, double importe) {
		if (vendedor instanceof VendedorEnPlantilla) {
			VendedorEnPlantilla v = (VendedorEnPlantilla) vendedor;
			switch (v.tipo()) {
			case Junior:
				return importe * 0.005;
			case Senior:
				return importe * 0.01;
			}
		}
		return 0;
	}
	
	private void cargaDatos() throws DataAccessException {
		lista = new LinkedList<Vendedor>();

		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) { 		
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Senior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Practicas")) {  	
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.Junior);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			while (in.hasNext()) {		
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {	
			throw new DataAccessException();
		} finally {
			if (in != null) {		
				in.close();
			}
		}
	}

}
