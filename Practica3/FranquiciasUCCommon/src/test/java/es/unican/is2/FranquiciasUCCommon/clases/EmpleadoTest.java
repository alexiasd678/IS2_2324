package es.unican.is2.FranquiciasUCCommon.clases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

class EmpleadoTest {
	
	private Empleado empleado;
	
	@BeforeEach
	public void setUp() throws Exception {
		empleado = new Empleado();
	}
	
	@Test
	void testConstructorEmpleado() throws FechaIncorrectaException {
		LocalDate hoy = LocalDate.now();
		
		//CASOS DE PRUEBA NO VALIDOS
		
		assertThrows(NullPointerException.class, () -> new Empleado("77733982C", "Paco", null, hoy.minusDays(1)));
		assertThrows(NullPointerException.class, () -> new Empleado("77733982C", null, Categoria.VENDEDOR, hoy));
		assertThrows(NullPointerException.class, () -> new Empleado(null, "Paco", Categoria.VENDEDOR, hoy.minusDays(1)));
		assertThrows(FechaIncorrectaException.class, () -> new Empleado("77733982C", "Paco", Categoria.AUXILIAR, hoy.plusDays(1)));
		assertThrows(NullPointerException.class, () -> new Empleado("77733982C", "Paco", Categoria.ENCARGADO, null));
		
		//CASOS DE PRUEBA VALIDOS
		Empleado empleado1 = new Empleado("77733982C", "Paco", Categoria.ENCARGADO, hoy.minusDays(1));
		assertEquals("77733982C", empleado1.getDNI());
		assertEquals("Paco", empleado1.getNombre());
		assertEquals(Categoria.ENCARGADO, empleado1.getCategoria());
		assertEquals(hoy.minusDays(1), empleado1.getFechaContratacion());
		
		Empleado empleado2 = new Empleado("77733982C", "Paco", Categoria.VENDEDOR, hoy);
		assertEquals("77733982C", empleado2.getDNI());
		assertEquals("Paco", empleado2.getNombre());
		assertEquals(Categoria.VENDEDOR, empleado2.getCategoria());
		assertEquals(hoy, empleado2.getFechaContratacion());
		
		Empleado empleado3 = new Empleado("77733982C", "Paco", Categoria.AUXILIAR, hoy.minusDays(1));
		assertEquals("77733982C", empleado3.getDNI());
		assertEquals("Paco", empleado3.getNombre());
		assertEquals(Categoria.AUXILIAR, empleado3.getCategoria());
		assertEquals(hoy.minusDays(1), empleado3.getFechaContratacion());
	}
	
	@Test
	void testSueldoBruto() throws FechaIncorrectaException {
		LocalDate hoy = LocalDate.now();
		
		//CASOS DE PRUEBA NO VALIDOS
		
		empleado.setCategoria(null);
		empleado.setFechaContratacion(hoy);
		empleado.setBaja(true);
		assertThrows(NullPointerException.class, () -> empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(hoy.plusDays(1));
		empleado.setBaja(false);
		assertThrows(FechaIncorrectaException.class, () -> empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.VENDEDOR);
		empleado.setFechaContratacion(null);
		empleado.setBaja(true);
		assertThrows(NullPointerException.class, () -> empleado.sueldoBruto());
		
		//CASOS DE PRUEBA VALIDOS
		
		empleado.setCategoria(Categoria.ENCARGADO);
		empleado.setFechaContratacion(hoy);
		empleado.setBaja(true);
		assertEquals(1500, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(5).minusDays(1));
		empleado.setBaja(false);
		assertEquals(2050, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(10).minusDays(1));
		empleado.setBaja(true);
		assertEquals(1575, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(20).minusDays(1));
		empleado.setBaja(false);
		assertEquals(2200, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.VENDEDOR);
		empleado.setFechaContratacion(hoy.minusYears(2));
		empleado.setBaja(false);
		assertEquals(1500, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(7));
		empleado.setBaja(true);
		assertEquals(1162.5, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(15));
		empleado.setBaja(false);
		assertEquals(1600, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(25));
		empleado.setBaja(true);
		assertEquals(1275, empleado.sueldoBruto());
		
		empleado.setCategoria(Categoria.AUXILIAR);
		empleado.setFechaContratacion(hoy.minusYears(5));
		empleado.setBaja(true);
		assertEquals(750, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(10));
		empleado.setBaja(false);
		assertEquals(1050, empleado.sueldoBruto());
		
		empleado.setFechaContratacion(hoy.minusYears(20));
		empleado.setBaja(true);
		assertEquals(825, empleado.sueldoBruto());
	}

}
