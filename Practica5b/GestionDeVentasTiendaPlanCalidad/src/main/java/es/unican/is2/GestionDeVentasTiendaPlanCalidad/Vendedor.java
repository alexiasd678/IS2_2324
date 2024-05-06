package es.unican.is2.GestionDeVentasTiendaPlanCalidad;
/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private double comision;
	private double totalVentas;
	private String dni;
	
	public Vendedor(String nombre, String id, String dni) {		//WMC+1	
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {		//WMC+1	
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() {		//WMC+1	
		return id;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni
	 */
	public String getDNI() {		//WMC+1	
		return dni;
	}
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getComision() {		//WMC+1	
		return comision;
	}
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setComision(double value) {		//WMC+1	
		this.comision = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) {		//WMC+1	
		return totalVentas;
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {		//WMC+1	
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public abstract void anhadeVenta(double importe);	//WMC+1	
	
	@Override
    public boolean equals(Object obj) {		//WMC+1	
        if (this == obj) {  // Comprueba si es la misma instancia		//WMC+1  //Ccog+1
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {  // Comprueba si los tipos son diferentes		//WMC+1  //Ccog+1
            return false;
        }
        Vendedor other = (Vendedor) obj; // Conversión segura ya que se ha comprobado el tipo
        return id.equals(other.id) && dni.equals(other.dni);
    }
}
