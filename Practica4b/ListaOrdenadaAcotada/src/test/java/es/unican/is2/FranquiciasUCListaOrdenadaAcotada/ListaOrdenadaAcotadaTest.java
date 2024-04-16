package es.unican.is2.FranquiciasUCListaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import es.unican.is2.FranquiciasUC.ListaOrdenadaAcotada.ListaOrdenadaAcotada;


class ListaOrdenadaAcotadaTest {
	
	private ListaOrdenadaAcotada<String> lista;
	private ListaOrdenadaAcotada<String> lista1;

	@BeforeEach
	void setUp() throws Exception {
		lista = new ListaOrdenadaAcotada<String>(10);
	}
	
	@Test
	void getTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		String elemento7 = "elemento7";
		
		// Casos de prueba no validos
		lista.add(elemento1);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3));				
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5));
		
		lista.remove(0);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(3));
		
		// Casos de prueba validos
		lista.add(elemento1);
		assertEquals("elemento1", lista.get(0));
		
		lista.add(elemento2);
		lista.add(elemento3);
		lista.add(elemento4);
		lista.add(elemento5);
		lista.add(elemento6);
		lista.add(elemento7);
		assertEquals("elemento4", lista.get(3));
		assertEquals("elemento7", lista.get(6));
		
	}
	
	@Test 
	void addTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		String elemento7 = "elemento7"; String elemento8 = "elemento8";
		String elemento9 = "elemento8"; String elemento10 = "elmento10";
		String elemento11 = "elemento11";
		
		// Casos de prueba validos				
		lista.add(elemento1);
		assertEquals("elemento1", lista.get(0));
						
		lista.add(elemento2); lista.add(elemento3);
		lista.add(elemento4); lista.add(elemento5);
		lista.add(elemento6);
		assertEquals("elemento6", lista.get(5));
		
		lista.add(elemento7);
		assertEquals("elemento7", lista.get(6));
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> lista.add(null));
		
		lista.add(elemento8);
		lista.add(elemento9); lista.add(elemento10);
		assertThrows(IllegalStateException.class, () -> lista.add(elemento11));
	}
	
	@Test 
	void removeTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		String elemento7 = "elemento7"; String elemento8 = "elemento8";
		String elemento9 = "elemento8"; String elemento10 = "elmento10";
		
		// Casos de prueba no valida
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
		
		lista.add(elemento1);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-2));
		
		lista.add(elemento2); lista.add(elemento3);
		lista.add(elemento4); lista.add(elemento5);
		lista.add(elemento6);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(10));
		
		lista.add(elemento7); lista.add(elemento8);
		lista.add(elemento9); lista.add(elemento10);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(13));
		
		// Casos de prueba valida
		
		lista.remove(9); lista.remove(8);
		lista.remove(7); lista.remove(6);
		lista.remove(5); lista.remove(4);
		lista.remove(3); lista.remove(2);
		lista.remove(1); lista.remove(0);
		
		lista.add(elemento1);
		assertEquals("elemento1", lista.remove(0));
		
		lista.add(elemento1); lista.add(elemento2);
		lista.add(elemento3); lista.add(elemento4);
		assertEquals("elemento4", lista.remove(3));
		
		lista.add(elemento4); lista.add(elemento5);
		lista.add(elemento6); lista.add(elemento7);
		lista.add(elemento8); lista.add(elemento9);
		lista.add(elemento10);
		assertEquals("elemento7", lista.remove(6));
	}
	
	@Test
	void sizeTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		String elemento7 = "elemento7"; String elemento8 = "elemento8";
		String elemento9 = "elemento8"; String elemento10 = "elmento10";
		
		// Casos de prueba validos
		assertEquals(0, lista.size());
		
		lista.add(elemento1); lista.add(elemento2);
		lista.add(elemento3); lista.add(elemento4);
		lista.add(elemento5);
		assertEquals(5, lista.size());
		
		lista.add(elemento6); lista.add(elemento7);
		lista.add(elemento8); lista.add(elemento9);
		lista.add(elemento10);
		assertEquals(10, lista.size());
	}
	
	@Test 
	void clearTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		String elemento7 = "elemento7"; String elemento8 = "elemento8";
		String elemento9 = "elemento8"; String elemento10 = "elmento10";
		
		// Casos de prueba valida
		assertEquals(0, lista.size());
		
		lista.add(elemento1); lista.add(elemento2);
		lista.add(elemento3); lista.add(elemento4);
		lista.add(elemento5);
		lista.clear();
		assertEquals(0, lista.size());
		
		lista.add(elemento1); lista.add(elemento2);
		lista.add(elemento3); lista.add(elemento4);
		lista.add(elemento5); lista.add(elemento6); 
		lista.add(elemento7); lista.add(elemento8); 
		lista.add(elemento9); lista.add(elemento10);
		lista.clear();
		assertEquals(0, lista.size());
		
	}
	
	@Test
	void constructorTest() {
		
		String elemento1 = "elemento1"; String elemento2 = "elemento2";
		String elemento3 = "elemento3"; String elemento4 = "elemento4";
		String elemento5 = "elemento5"; String elemento6 = "elemento6";
		
		// Casos de prueba validos
		lista1 = new ListaOrdenadaAcotada<String>(5);
		assertDoesNotThrow(() ->	lista1.add(elemento1));
		assertDoesNotThrow(() ->	lista1.add(elemento2));
		assertDoesNotThrow(() ->	lista1.add(elemento3));
		assertDoesNotThrow(() ->	lista1.add(elemento4));
		assertDoesNotThrow(() ->	lista1.add(elemento5));
		
		assertThrows(IllegalStateException.class, () -> lista1.add(elemento6));
		
		// Casos de prueba no validos 
		assertThrows(NegativeArraySizeException.class, () -> lista1 = new ListaOrdenadaAcotada<String>(-1));
	}

}