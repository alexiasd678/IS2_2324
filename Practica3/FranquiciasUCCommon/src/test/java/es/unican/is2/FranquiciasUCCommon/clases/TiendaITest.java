package es.unican.is2.FranquiciasUCCommon.clases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TiendaITest {
	
	private Tienda tienda;
	
	@BeforeEach
	void setUp() throws Exception {
		tienda = new Tienda();
	}

	@Test
	void testConstructorTienda() {
		
		//CASOS DE PRUEBA NO VALIDOS
		assertThrows(NullPointerException.class, () -> new Tienda(null, "Calle La Cruz, 24, Santander"));
		assertThrows(NullPointerException.class, () -> new Tienda("Esther", null));
		
		//CASOS PRUEBA VALIDOS
		Tienda tienda1 = new Tienda("Esther", "Calle La Cruz, 24, Santander");
		assertEquals("Esther", tienda1.getNombre());
		assertEquals("Calle La Cruz, 24, Santander", tienda1.getDireccion());
	}
	
	@Test 
	void testGastoMensualSueldo() throws FechaIncorrectaException{
		
		LocalDate hoy = LocalDate.now();
		
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		Empleado empleado3 = new Empleado();
		
		empleado1.setCategoria(Categoria.ENCARGADO);
		empleado2.setCategoria(Categoria.VENDEDOR);
		empleado3.setCategoria(Categoria.AUXILIAR);
		
		empleado1.setFechaContratacion(hoy);
		empleado2.setFechaContratacion(hoy.minusYears(2));
		empleado3.setFechaContratacion(hoy.minusYears(5));
		
		empleado1.setBaja(true);
		empleado2.setBaja(false);
		empleado3.setBaja(true);
		
		assertEquals(0, tienda.gastoMensualSueldos());
		
		tienda.getEmpleados().add(empleado1);
		assertEquals(1500, tienda.gastoMensualSueldos());
		
		tienda.getEmpleados().add(empleado2);
		tienda.getEmpleados().add(empleado3);
		assertEquals(3750, tienda.gastoMensualSueldos());
		
	}

}
