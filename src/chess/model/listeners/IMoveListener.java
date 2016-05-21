package chess.model.listeners;

import chess.model.board.Field;
import chess.model.players.Player;

public interface IMoveListener {

    public void reportNewMove(Player player, Field sourceField, Field targetField);
}
