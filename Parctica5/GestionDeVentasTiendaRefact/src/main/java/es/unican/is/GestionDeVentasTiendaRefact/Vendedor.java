package es.unican.is.GestionDeVentasTiendaRefact;
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
	
	public Vendedor(String nombre, String id, String dni) {		
		this.nombre = nombre;
		this.id = id;
		this.dni = dni;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {		
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() {		
		return id;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni
	 */
	public String getDNI() {		
		return dni;
	}
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getComision() {		
		return comision;
	}
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setComision(double value) {		
		this.comision = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) {		
		return totalVentas;
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {		
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public abstract void anhadeVenta(double importe);
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {  // Comprueba si es la misma instancia
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {  // Comprueba si los tipos son diferentes
            return false;
        }
        Vendedor other = (Vendedor) obj; // Conversión segura ya que se ha comprobado el tipo
        return id.equals(other.id) && dni.equals(other.dni);
    }
}
