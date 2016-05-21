package chess.model.board;

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
