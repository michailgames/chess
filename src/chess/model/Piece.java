package chess.model;

public abstract class Piece {
	
	private final Color color;
	
	public Piece(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getUnicodeString() {
		if(color == Color.WHITE) {
			return getWhiteUnicodeString();
		} else {
			return getBlackUnicodeString();
		}
	}
	
	protected abstract String getWhiteUnicodeString();
	
	protected abstract String getBlackUnicodeString();

}
