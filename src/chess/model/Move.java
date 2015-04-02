package chess.model;

/**
 * Projekt: Szachy
 * Klasa pomocnicza reprezentuj¹ca wspó³rzêdne ruchu figury
 * Micha³ Rapacz
 * 2015--0
 */

public class Move {
	
	private final Field sourceField, targetField;

	public Move(Field sourceField, Field targetField) {
		this.sourceField = sourceField;
		this.targetField = targetField;
	}
	
	public Field getSourceField() {
		return sourceField;
	}
	
	public Field getTargetField() {
		return targetField;
	}

}
