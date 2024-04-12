package es.unican.is2.FranquiciasUCListaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;

class ListaOrdenadaAcotadaTest<E> {
	
	private ListaOrdenadaAcotada<E> lista;

	@BeforeEach
	void setUp() throws Exception {
		lista = new ListaOrdenadaAcotada<E>(10);
	}
	
	@Test
	void getTest() {
		
		E elemento1 = new E(); E elemento2 = new E();
		E elemento3 = new E(); E elemento4 = new E();
		E elemento5 = new E(); E elemento6 = new E();
		E elemento7 = new E();
		
		// Casos de prueba no validos
		lista.add(elemento1);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-3));				assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5));
		
		lista.remove(0);
		assertThrows(NullPointerException.class, () -> lista.get(3));
		
		// Casos de prueba validos
		lista.add(elemento1);
		assertEquals(elemento1, lista.get(0));
		
		lista.add(elemento2);
		lista.add(elemento3);
		lista.add(elemento4);
		lista.add(elemento5);
		lista.add(elemento6);
		lista.add(elemento7);
		assertEquals(elemento4, lista.get(3));
		assertEquals(elemento7, lista.get(6));
		
	}
	
	@Test 
	void addTest() {
		
		E elemento1 = new E(); E elemento2 = new E();
		E elemento3 = new E(); E elemento4 = new E();
		E elemento5 = new E(); E elemento6 = new E();
		E elemento7 = new E(); E elemento8 = new E();
		E elemento9 = new E(); E elemento10 = new E();
		E elemento11 = new E();
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> lista.add(null));
		
		lista.add(elemento1); lista.add(elemento2);
		lista.add(elemento3); lista.add(elemento4);
		lista.add(elemento5); lista.add(elemento6);
		lista.add(elemento7); lista.add(elemento8);
		lista.add(elemento9); lista.add(elemento10);
		assertThrows(IllegalStateException.class, () -> lista.add(elemento11));				assertThrows(IndexOutOfBoundsException.class, () -> lista.get(1));
				
		// Casos de prueba validos
		lista.remove(9); lista.remove(8);
		lista.remove(7); lista.remove(6);
		lista.remove(5); lista.remove(4);
		lista.remove(3); lista.remove(2);
		lista.remove(1); lista.remove(0);
		assertEquals(lista, lista.add(elemento1));
	}

}
