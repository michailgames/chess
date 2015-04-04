package chess.model.listeners;

import chess.model.board.Field;
import chess.model.players.Player;

/**
 * Projekt: Szachy
 * Interfejs odbieraj�cy nowy ruch
 * Micha� Rapacz
 * 2015-04-02
 */

public interface IMoveListener {

	public void reportNewMove(Player player, Field sourceField,
			Field targetField);
}
