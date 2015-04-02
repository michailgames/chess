package chess.model.interfaces;

import chess.model.Field;
import chess.model.Player;

/**
 * Projekt: Szachy
 * Interfejs odbieraj�cy nowy ruch
 * Micha� Rapacz
 * 2015-04-02
 */

public interface MoveListener {

	public void reportNewMove(Player player, Field sourceField,
			Field targetField);
}
