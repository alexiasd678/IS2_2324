package es.unican.is2.GestionDeVentasTiendaRefactTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is.GestionDeVentasTiendaRefact.VendedorEnPracticas;


public class VendedorEnPracticasTest {
	
	private static VendedorEnPracticas sut;

	@BeforeEach
	public void setUp(){
		sut = new VendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDNI(), "11111111A");
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getComision());
	}
	
	@Test
	public void testSetT() {
		sut.setTotalVentas(100);
		assertEquals(100.0, sut.getTotalVentas());
		
		sut.setTotalVentas(230);
		assertEquals(230.0, sut.getTotalVentas());
		
		sut.setTotalVentas(0);
		assertEquals(0.0, sut.getTotalVentas());
	}
	
	@Test
	public void testSetC() {
		sut.setComision(100);
		assertEquals(100.0, sut.getComision());
		
		sut.setComision(230);
		assertEquals(230.0, sut.getComision());
		
		sut.setComision(0);
		assertEquals(0.0, sut.getComision());
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertEquals(200.0, sut.getTotalVentas());
		
		sut.anhadeVenta(300);
		assertEquals(500.0, sut.getTotalVentas());	
		
		sut.anhadeVenta(0);
		assertEquals(500.0, sut.getTotalVentas());
		
	}
	
	@Test
	public void testEquals() {
		VendedorEnPracticas igual = new VendedorEnPracticas("Ana", "1", "11111111A");
		VendedorEnPracticas distintoId = new VendedorEnPracticas("Ana", "2", "11111111A");
		VendedorEnPracticas distintoNombre = new VendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
		
		assertFalse(sut.equals(null));
		assertFalse(sut.equals("No soy un Vendedor"));
		assertTrue(sut.equals(sut));
		
		assertFalse(sut.equals(new Object()));
	}
	
}
