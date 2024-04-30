package es.unican.is2.GestionDeVentasTiendaRefactTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is.GestionDeVentasTiendaRefact.VendedorEnPlantillaJunior;

class VendedorEnPlantillaJuniorTest {
	
	private static VendedorEnPlantillaJunior sutJunior;
	
	@BeforeEach
	public void setUp(){
		sutJunior = new VendedorEnPlantillaJunior("Ana", "1", "11111111A");
	}
	
	//no se realiza test de constructor porque se comprueba en VendedorEnPlantillaTest

	@Test
	void testCalcularComisionJunior() {
		
		assertEquals(0.15, sutJunior.calcularComisionJunior(30.0));
	}

}
