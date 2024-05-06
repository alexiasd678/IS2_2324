package es.unican.is2.GestionDeVentasTiendaPlanCalidadTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.GestionDeVentasTiendaPlanCalidad.VendedorEnPlantilla;
import es.unican.is2.GestionDeVentasTiendaPlanCalidad.VendedorEnPlantillaJunior;
import es.unican.is2.GestionDeVentasTiendaPlanCalidad.VendedorEnPlantillaSenior;



public class VendedorEnPlantillaTest {
	
	private static VendedorEnPlantilla sutJunior;
	private static VendedorEnPlantilla sutSenior;

	
	@BeforeEach
	public void setUp(){
		sutJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
		sutSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDNI(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
		assertEquals(0.0, sutJunior.getTotalVentas());
		assertEquals(0.0, sutJunior.getComision());
		
		assertEquals(sutSenior.getId(), "2");
		assertEquals(sutSenior.getDNI(), "222222222A");
		assertEquals(sutSenior.getNombre(), "Pepe");
		assertEquals(0.0, sutSenior.getTotalVentas());
		assertEquals(0.0, sutSenior.getComision());
	}

	@Test
	public void testAnhadeVenta() {
	
		sutJunior.anhadeVenta(200);
		assertEquals(sutJunior.getTotalVentas(), 200, 0);
		sutJunior.anhadeVenta(300);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		sutJunior.anhadeVenta(0);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 300, 0);
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		sutSenior.anhadeVenta(0);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(sutSenior.getTotalVentas(), 4500, 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);	
		
	}
	
	@Test
	public void testSetComision() {
		
		sutJunior.setComision(2000);
		assertEquals(sutJunior.getComision(), 2000, 0);	
		sutJunior.setComision(4000);
		assertEquals(sutJunior.getComision(), 4000, 0);	
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);
		
		sutSenior.setComision(4500);
		assertEquals(sutSenior.getComision(), 4500, 0);		
		sutSenior.setComision(4000);
		assertEquals(sutSenior.getComision(), 4000, 0);
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantilla igualJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
		VendedorEnPlantilla distintoIdJunior = new VendedorEnPlantillaJunior("Ana", "2", "11111111A");
		VendedorEnPlantilla distintoDNIJunior = new VendedorEnPlantillaJunior("Ana", "1", "222222222A");
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
	
		assertFalse(sutJunior.equals(null));
		assertFalse(sutJunior.equals("No soy un Vendedor"));
		assertTrue(sutJunior.equals(sutJunior));

		
		assertFalse(sutJunior.equals(new Object()));
		
		VendedorEnPlantilla igualSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
		VendedorEnPlantilla distintoIdSenior = new VendedorEnPlantillaSenior("Pepe", "3", "222222222A");
		VendedorEnPlantilla distintoDNISenior = new VendedorEnPlantillaSenior("Pepe", "2", "33333333A");
		
		assertTrue(sutSenior.equals(igualSenior));
		assertFalse(sutSenior.equals(distintoIdSenior));
		assertFalse(sutSenior.equals(distintoDNISenior));
		
		assertFalse(sutSenior.equals(null));
		assertFalse(sutSenior.equals("No soy un Vendedor"));
		assertTrue(sutSenior.equals(sutSenior));

		
		assertFalse(sutSenior.equals(new Object()));
	}
	
	
	
}
