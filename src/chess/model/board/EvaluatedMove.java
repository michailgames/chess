package chess.model.board;

public class EvaluatedMove {
	
	private Move move;
	private int score;

	public EvaluatedMove(Move move, int score) {
		this.move = move;
		this.score = score;
	}
	
	public Move getMove() {
		return move;
	}

	public int getScore() {
		return score;
	}
}
