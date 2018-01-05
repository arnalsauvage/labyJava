package test;

import static org.junit.Assert.*;

import labyrinthe.Grille;
import org.junit.Test;


public class GrilleTest {
	
	@Test
	public void testGrilleIntInt() {
		Grille grilleDeTest = new Grille(5,5);
		
		assertEquals(grilleDeTest.getX(),5);
	}

	@Test
	public void testGrilleIntIntIntArrayArray() {
		int [][] grille = {{2,2},{2,2},{2,2}};
		Grille grilleDeTest = new Grille(3, 2, grille);
		
		assertEquals(grilleDeTest.getX(),3);
		assertEquals(grilleDeTest.getY(),2);
	}

	@Test
	public void testSetDimensions() {
		int [][] grille = {{2,2},{2,2},{2,2}};
		Grille grilleDeTest = new Grille(3, 2, grille);
		grilleDeTest.setDimensions(2, 2);
		assertEquals(grilleDeTest.getX(),2);
		assertEquals(grilleDeTest.getY(),2);
	}

	@Test
	public void testRemplirGrille() {
		int [][] grille = {{2,2},{2,2},{2,2}};
		Grille grilleDeTest = new Grille(3, 2, grille);
		grilleDeTest.remplirGrille(0,0, 2,1, 0);
		
		grilleDeTest.remplirGrille(1,1, 2,1, 8);
		assertEquals(grilleDeTest.getXY(0, 0), 0);
		assertEquals(grilleDeTest.getXY(0, 1), 0);
		assertEquals(grilleDeTest.getXY(1, 0), 0);
		assertEquals(grilleDeTest.getXY(1, 1), 8);
		assertEquals(grilleDeTest.getXY(2, 0), 0);
		assertEquals(grilleDeTest.getXY(2, 1), 8);
		
		}

	@Test
	public void testPointDansGrille() {
		int [][] grille = {{2,2},{2,2},{2,2}};
		Grille grilleDeTest = new Grille(3, 2, grille);

		assertEquals(true, grilleDeTest.pointDansGrille(1, 1));
		assertEquals(false, grilleDeTest.pointDansGrille(3, 1));
	}

	@Test
	public void testElementAuHAsard() {
		int [][] grille = {{2,2},{2,2},{2,2}};
		Grille grilleDeTest = new Grille(3, 2, grille);
		assertNull(grilleDeTest.elementAuHAsard(1));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
		assertNotNull( grilleDeTest.elementAuHAsard(2));
	}

}
