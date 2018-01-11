package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import labyrinthe.Creuseur;
import labyrinthe.Grille;

public class CreuseurTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testPoser() {
		Grille grilleDeTest = new Grille(5, 5);
		grilleDeTest.remplirGrille(0,0,4,4, 0);
		Creuseur monCreuseur = new Creuseur (1,1,0,1,grilleDeTest);
		monCreuseur.poser(3, 2);
		assertEquals(3, monCreuseur.getX());
		assertEquals(2, monCreuseur.getY());
	}

	// Dans une grille de 5 x 5 contenant un bord de 1, le creuseur ne peut tourner que trois fois
	@Test
	public void testCreuser() {
		Grille grilleDeTest = new Grille(5, 5);
		grilleDeTest.remplirGrille(0,0,4,4, 0);
		Creuseur monCreuseur = new Creuseur (1,1,0,1,grilleDeTest);
		grilleDeTest.setValeur(1, 1, 1);
		assertEquals(true, monCreuseur.creuser());
		assertEquals(true, monCreuseur.creuser());
		assertEquals(true, monCreuseur.creuser());
		assertEquals(false, monCreuseur.creuser());
		
	}

}
