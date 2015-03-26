package chess.model;

/**
 * Projekt: Szachy
 * Test klasy pola
 * Micha³ Rapacz
 * 2015-03-26
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void whenTwoFieldsHaveSameCoordinates_EqualsReturnsTrue() {
		Field f1 = new Field(4, 4);
		Field f2 = new Field(4, 4);
		assertTrue(f1.equals(f2));
	}

	@Test
	public void whenTwoFieldsHaveDifferentCoordinates_EqualsReturnsFalse() {
		Field f1 = new Field(4, 3);
		Field f2 = new Field(4, 4);
		assertFalse(f1.equals(f2));
	}
}
