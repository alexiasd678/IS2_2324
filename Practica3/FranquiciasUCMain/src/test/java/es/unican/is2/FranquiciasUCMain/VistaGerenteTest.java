package es.unican.is2.FranquiciasUCMain;

import static org.junit.jupiter.api.Assertions.*;

import org.fest.swing.fixture.*;
import org.junit.jupiter.api.*;

import es.unican.is2.FranquiciasUCBusiness.GestionEmpleados;
import es.unican.is2.FranquiciasUCBusiness.GestionTiendas;
import es.unican.is2.FranquiciasUCDAO.EmpleadosDAO;
import es.unican.is2.FranquiciasUCDAO.TiendasDAO;
import es.unican.is2.FranquiciasUCGUI.VistaGerente;

public class VistaGerenteTest {

	private FrameFixture demo;

    private TiendasDAO tiendas = new TiendasDAO();
    
    private EmpleadosDAO empleados = new EmpleadosDAO();

    private GestionEmpleados gestionEmpleados = new GestionEmpleados(tiendas, empleados);

    private GestionTiendas gestionTiendas = new GestionTiendas(tiendas);
    
    @BeforeEach
    public void setUp() {
        // Creamos la ventana de VistaGerente y la asociamos con FrameFixture
        VistaGerente vistaGerente = new VistaGerente(gestionTiendas, gestionEmpleados);
        demo = new FrameFixture(vistaGerente);
        vistaGerente.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void testCapaPersistencia() {
    	
    	//CASOS PRUEBA VALIDA 
    	
        // Comprobamos que el bot�n tiene el texto deseado
        demo.button("btnBuscar").requireText("Buscar");

        // Escribimos en el campo de texto
        demo.textBox("txtDireccionTienda").setText("");
        demo.textBox("txtTotalContribuyente").setText("");
        demo.textBox("txtNombreTienda").setText("Tienda A");

        // Pulsamos el bot�n
        demo.button("btnBuscar").click();

        // Comprobamos el texto en el campo de texto de direcci�n de tienda
        demo.textBox("txtDireccionTienda").requireText("Direcci�n A");

        // Comprobamos el texto en el campo de texto de total de sueldos
        demo.textBox("txtTotalContribuyente").requireText("4362.5");

        // Comprobamos los elementos en la lista de empleados
        assertEquals("Juan Perez", demo.list("listNombreEmpleados").valueAt(0));
        assertEquals("Maria Rodriguez", demo.list("listNombreEmpleados").valueAt(1));
        assertEquals("Luis Mart�nez", demo.list("listNombreEmpleados").valueAt(2));
        
        // Escribimos en el campo de texto
        demo.textBox("txtDireccionTienda").setText("");
        demo.textBox("txtTotalContribuyente").setText("");
        demo.textBox("txtNombreTienda").setText("Tienda B");
        
        // Pulsamos el bot�n
        demo.button("btnBuscar").click();
        
        // Comprobamos el texto en el campo de texto de direcci�n de tienda
        demo.textBox("txtDireccionTienda").requireText("Direcci�n B");
        
        // Comprobamos el texto en el campo de texto de total de sueldos
        demo.textBox("txtTotalContribuyente").requireText("2100.0");
        
        // Comprobamos los elementos en la lista de empleados
        assertEquals("Luc�a Ib��ez", demo.list("listNombreEmpleados").valueAt(0));
        
        // Escribimos en el campo de texto
        demo.textBox("txtDireccionTienda").setText("");
        demo.textBox("txtTotalContribuyente").setText("");
        demo.textBox("txtNombreTienda").setText("Tienda C");
        
        // Pulsamos el bot�n
        demo.button("btnBuscar").click();
        
        // Comprobamos el texto en el campo de texto de direcci�n de tienda
        demo.textBox("txtDireccionTienda").requireText("Direcci�n C");
        
        // Comprobamos el texto en el campo de texto de total de sueldos
        demo.textBox("txtTotalContribuyente").requireText("0.0");
        
        // Comprobamos los elementos en la lista de empleados
        assertEquals(0, demo.list("listNombreEmpleados").target.getModel().getSize());
        
        //CASOS PRUEBA NO VALIDA
        demo.textBox("txtDireccionTienda").setText("");
        demo.textBox("txtTotalContribuyente").setText("");
        demo.textBox("txtNombreTienda").setText(null);
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
        demo.textBox("txtTotalContribuyente").requireText("");
        
        demo.textBox("txtDireccionTienda").setText("");
        demo.textBox("txtTotalContribuyente").setText("");
        demo.textBox("txtNombreTienda").setText("Tienda D");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
        demo.textBox("txtTotalContribuyente").requireText("");
    }
}
