package chess.model.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
