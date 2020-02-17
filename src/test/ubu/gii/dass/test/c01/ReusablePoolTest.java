/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author María Ojeda y Daniel Mellado
 *
 */
public class ReusablePoolTest {
	
	public final static int TAMANO = 3;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		consistenciaReusablePool();
	}
	
	/**
	 * Método que mantiene la consistencia de la clase ReusablePool para las pruebas.
	 * 
	 * Vacia la pila de los elementos que pueda tener y la vuelve a llenar.
	 * 
	 * @throws java.lang.Exception
	 */
	private void consistenciaReusablePool() throws Exception{
		
		ReusablePool instancia = ReusablePool.getInstance();
		try {
			
			for(int i = 0; i < TAMANO; i++) {
				Reusable r = instancia.acquireReusable();
			}
				
		}catch(NotFreeInstanceException e) {
			
			for(int i = 0; i < TAMANO; i++) {
				instancia.releaseReusable(new Reusable());
			}
		}
	}
 
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool instancia1 = ReusablePool.getInstance();
		try {
			Reusable r = instancia1.acquireReusable();
		} catch (NotFreeInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ReusablePool instancia2 = ReusablePool.getInstance();

		assert (instancia1 == instancia2);
	
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		
		ReusablePool instancia = ReusablePool.getInstance();
		
		try {
			
			for(int i = 0; i <= TAMANO; i++) {
				Reusable r = instancia.acquireReusable();
			}
			
			assertTrue(false);
			
		}catch(NotFreeInstanceException e) {
			assertTrue(true);		
		}
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws DuplicatedInstanceException 
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws DuplicatedInstanceException, NotFreeInstanceException {
		ReusablePool instancia3 = ReusablePool.getInstance();
		//Obtenemos un objeto Reusable
		Reusable r = instancia3.acquireReusable();
		try {
			//Comprobamos que no se puede introducir duplicado
			instancia3.releaseReusable(r);
			instancia3.releaseReusable(r);
			
		}catch(DuplicatedInstanceException e) {
			assertTrue(true);		
		}
	}

}
