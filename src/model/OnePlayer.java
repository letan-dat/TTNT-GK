package model;

import AI.AI;
import AI.AI_Interface;
import view.BoardView;

public class OnePlayer extends Game {
	private AI_Interface ai;
	private int level;

	public OnePlayer(String player_Name, BoardView view, int level) {
		super(view);
		this.listPlayer.add(new Player("Computer", 0));
		this.listPlayer.add(new Player(player_Name, 0));
		this.level = level;
	}

	@Override
	public void turnOver(boolean turn) {
		// TODO Auto-generated method stub
		if (turn == TURNPLAYER_1) {
			if (checkBox(TURNPLAYER_2)) {
				addChess(TURNPLAYER_2);
			}
			view.turnOver(TURNPLAYER_2);
		} else {
			if (checkBox(TURNPLAYER_1)) {
				addChess(TURNPLAYER_1);
			}
			view.turnOverComputer();
			ai = new AI(this);
			ai.computerMove(TURNPLAYER_1, level);

		}
	}

}
