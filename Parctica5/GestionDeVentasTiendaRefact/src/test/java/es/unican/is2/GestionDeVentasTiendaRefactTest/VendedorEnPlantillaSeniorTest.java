package es.unican.is2.GestionDeVentasTiendaRefactTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is.GestionDeVentasTiendaRefact.VendedorEnPlantillaSenior;

class VendedorEnPlantillaSeniorTest {
	
	private static VendedorEnPlantillaSenior sutSenior;

	@BeforeEach
	void setUp() throws Exception {
		sutSenior = new VendedorEnPlantillaSenior("Pepe", "2", "222222222A");
	}
	
	// No se realiza test sobre el constructor porque se comprueba ya en VendedorEnPlantillaTest

	@Test
	void testCalcularComisionSenior() {
		assertEquals(0.3, sutSenior.calcularComisionSenior(30.0));
	}

}
