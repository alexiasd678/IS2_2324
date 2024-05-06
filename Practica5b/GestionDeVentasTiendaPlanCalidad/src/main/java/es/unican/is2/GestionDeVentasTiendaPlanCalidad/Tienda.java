package es.unican.is2.GestionDeVentasTiendaPlanCalidad;
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

	private List<Vendedor> lista = new LinkedList<>();
	private String direccion;
	private String nombre;
	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {	//WMC+1
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String direccion() {		//WMC+1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {	//WMC+1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException {	//WMC+1 
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {		//WMC+1 //CCog +1
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
	public boolean eliminaVendedor(String id) throws DataAccessException {	//WMC+1 
		Vendedor v = buscaVendedor(id);
		if (v == null) {	//WMC+1 //CCog +1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {		//WMC+1 
		cargaDatos();
		for (Vendedor v : lista) {		//WMC+1 
			if (v.getId().equals(id)) {			//WMC+1 //CCog +1
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
	public List<Vendedor> vendedores() throws DataAccessException {		//WMC+1 
		cargaDatos();
		return lista;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {  	//WMC+1 
		
		try (PrintWriter out = new PrintWriter(new FileWriter(datos))) {

			escribirInformacionBasica(out);
			escribirVendedores(out);
			
		} catch (IOException e) {		//WMC+1 //CCog +1
			throw new DataAccessException();

		}
	}
	
	private void escribirInformacionBasica(PrintWriter out) {	//WMC+1
	    out.println(nombre);
	    out.println(direccion);
	    out.println();
	}
	
	private void escribirVendedores(PrintWriter out) {	//WMC+1 
	    List<Vendedor> senior = new LinkedList<>();
	    List<Vendedor> junior = new LinkedList<>();
	    List<Vendedor> practicas = new LinkedList<>();

	    for (Vendedor v : lista) {	//WMC+1 
	        if (v instanceof VendedorEnPracticas) {	//WMC+1 //CCog +1
	            practicas.add(v);
	        } else if (v instanceof VendedorEnPlantillaJunior) {	//WMC+1 //CCog +1
	            junior.add(v);
	        } else if (v instanceof VendedorEnPlantillaSenior) {	//WMC+1 //CCog +1
	            senior.add(v);
	        }
	    }

	    escribirVendedoresPorTipo(out, "Senior", senior);
	    escribirVendedoresPorTipo(out, "Junior", junior);
	    escribirVendedoresPorTipo(out, "Practicas", practicas);
	}
	
	private void escribirVendedoresPorTipo(PrintWriter out, String tipo, List<Vendedor> vendedores) {	//WMC+1 
	    out.println(tipo);
	    for (Vendedor v : vendedores) {	//WMC+1 
	        if (v instanceof VendedorEnPlantilla) {		//WMC+1 //CCog +1
	            VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
	            out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.getDNI()
	                    + " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: "+ v1.getComision());
	        } else if (v instanceof VendedorEnPracticas) {		//WMC+1 //CCog +1
	            VendedorEnPracticas v1 = (VendedorEnPracticas) v;
	            out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.getDNI()
	                    + " TotalVentasMes: " + v1.getTotalVentas());
	        }
	    }
	}
	
	private void cargaDatos() throws DataAccessException {		//WMC+1 
		lista = new LinkedList<>();

		try (Scanner in = new Scanner(new FileReader(datos))){
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Junior")) { 		//WMC+1 //CCog +1
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantillaSenior(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Practicas")) {  		//WMC+1 //CCog +1
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				in.next();
				double totalComision = in.nextDouble();
				ven = new VendedorEnPlantillaJunior(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				ven.setComision(totalComision);
				lista.add(ven);
			}
			while (in.hasNext()) {			//WMC+1 //CCog +1
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
		} catch (FileNotFoundException e) {		//WMC+1 //CCog +1
			throw new DataAccessException();
		}
	}
		

}
