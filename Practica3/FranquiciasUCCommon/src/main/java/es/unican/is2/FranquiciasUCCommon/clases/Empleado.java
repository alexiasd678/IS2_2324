package es.unican.is2.FranquiciasUCCommon.clases;



import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws FechaIncorrectaException {
		this.nombre = nombre;
		if(this.nombre == null) {
			throw new NullPointerException();
		}
		this.DNI=DNI;
		if(this.DNI == null) {
			throw new NullPointerException();
		}
		this.categoria=categoria;
		if(this.categoria == null) {
			throw new NullPointerException();
		}
		this.fechaContratacion=fechaContratacion;
		if(this.fechaContratacion.isAfter(LocalDate.now())) {
			throw new FechaIncorrectaException("La fecha de contratacion no debe ser posterior a hoy");
		}
		this.baja = false;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() throws FechaIncorrectaException {
		double sueldo = 0;
		
		Categoria categoria = getCategoria();
		LocalDate fechaContratacion = getFechaContratacion();
		if (categoria == null || fechaContratacion == null) {
			throw new NullPointerException();
		}
		
		if (categoria.equals(Categoria.ENCARGADO)){
			sueldo = 2000;
		} else if (categoria.equals(Categoria.VENDEDOR)){
			sueldo = 1500;
		} else {
			sueldo = 1000;
		}
		
		LocalDate fechaActual = LocalDate.now();
		if(fechaContratacion.isAfter(fechaActual)) {
			throw new FechaIncorrectaException("La fecha de contratacion no es"
					+ " valida, no debe ser mayor a la fecha actual");
		}
		
		if(fechaContratacion.isBefore(fechaActual.minusYears(20))){
			sueldo = sueldo + 200;
		}else if (fechaContratacion.isBefore(fechaActual.minusYears(10))) {
			sueldo = sueldo + 100;
		} else if (fechaContratacion.isBefore(fechaActual.minusYears(5))){
			sueldo = sueldo + 50;
		}
		
		if (getBaja()) {
			sueldo = sueldo * 0.75;
		}
		
		return sueldo;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
