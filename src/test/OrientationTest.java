package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import labyrinthe.Orientation;

public class OrientationTest {

	@Test
	public void testPivote() {
		Orientation orientation = new Orientation(2);
		orientation.pivote();
		assertEquals (3, orientation.getDirection());
		orientation.pivote();
		assertEquals (0, orientation.getDirection());
		orientation.pivote();
		assertEquals (1, orientation.getDirection());
		orientation.pivote();
		assertEquals (2, orientation.getDirection());
	}

	@Test
	public void testAntiPivote() {
		Orientation orientation = new Orientation(3);
		orientation.antiPivote();
		assertEquals (2, orientation.getDirection());
		orientation.antiPivote();
		assertEquals (1, orientation.getDirection());
		orientation.antiPivote();
		assertEquals (0, orientation.getDirection());
		orientation.antiPivote();
		assertEquals (3, orientation.getDirection());
	}

	@Test
	public void testVersLeHaut() {
		Orientation orientation = new Orientation(2);
		orientation.versLeHaut();
		assertEquals (0, orientation.getDirection());
		orientation.antiPivote();
		orientation.versLeHaut();
		assertEquals (0, orientation.getDirection());
		orientation.pivote();
		orientation.versLeHaut();
		assertEquals (0, orientation.getDirection());
	}

	@Test
	public void testVersLeBas() {
		Orientation orientation = new Orientation(0);
		orientation.versLeBas();
		assertEquals (2, orientation.getDirection());
		orientation.antiPivote();
		orientation.versLeBas();
		assertEquals (2, orientation.getDirection());
		orientation.pivote();
		orientation.versLeBas();
		assertEquals (2, orientation.getDirection());
	}

	@Test
	public void testAGauche() {
		Orientation orientation = new Orientation(1);
		orientation.aGauche();
		assertEquals (3, orientation.getDirection());
		orientation.antiPivote();
		orientation.aGauche();
		assertEquals (3, orientation.getDirection());
		orientation.pivote();
		orientation.aGauche();
		assertEquals (3, orientation.getDirection());
	}

	@Test
	public void testADroite() {
		Orientation orientation = new Orientation(3);
		orientation.aDroite();
		assertEquals (1, orientation.getDirection());
		orientation.antiPivote();
		orientation.aDroite();
		assertEquals (1, orientation.getDirection());
		orientation.pivote();
		orientation.aDroite();
		assertEquals (1, orientation.getDirection());
	}

	@Test
	public void testDemitour() {
		Orientation orientation = new Orientation(3);
		orientation.demitour();
		assertEquals (1, orientation.getDirection());
		orientation.pivote();
		orientation.demitour();
		assertEquals (0, orientation.getDirection());
		orientation.pivote();
		orientation.demitour();
		assertEquals (3, orientation.getDirection());
		orientation.pivote();
		orientation.demitour();
		assertEquals (2, orientation.getDirection());
	}

	@Test
	public void testGetX() {
		Orientation orientation = new Orientation(0);
		assertEquals (0, orientation.getX());
		orientation.pivote();
		assertEquals (1, orientation.getX());
		orientation.pivote();
		assertEquals (0, orientation.getX());
		orientation.pivote();
		assertEquals (-1, orientation.getX());
	}

	@Test
	public void testGetY() {
		Orientation orientation = new Orientation(1);
		assertEquals (0, orientation.getY());
		orientation.pivote();
		assertEquals (1, orientation.getY());
		orientation.pivote();
		assertEquals (0, orientation.getY());
		orientation.pivote();
		assertEquals (-1, orientation.getY());
	}

	@Test
	public void testSetDirection() {
		Orientation orientation = new Orientation(1);
		assertEquals (1, orientation.getDirection());
		orientation.setDirection(2);
		assertEquals (2, orientation.getDirection());
		orientation.setDirection(3);
		assertEquals (3, orientation.getDirection());
		orientation.setDirection(4);
		assertEquals (0, orientation.getDirection());
		orientation.setDirection(-1);
		assertEquals (3, orientation.getDirection());
		
	}

}
