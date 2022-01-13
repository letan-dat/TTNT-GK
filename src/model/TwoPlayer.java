package model;

import view.BoardView;

public class TwoPlayer extends Game {
	public TwoPlayer(String s1, String s2, BoardView view) {
		super(view);
		this.listPlayer.add(new Player(s1, 0));
		this.listPlayer.add(new Player(s2, 0));
	}

	@Override
	public void turnOver(boolean turn) {
		// TODO Auto-generated method stub
		if (turn == TURNPLAYER_1) {
			if (checkBox(TURNPLAYER_2)) {
				addChess(TURNPLAYER_2);
			}
			view.turnOver(TURNPLAYER_2);
			// them xu ly
		} else {
			if (checkBox(TURNPLAYER_1)) {
				addChess(TURNPLAYER_1);
			}
			view.turnOver(TURNPLAYER_1);
			// them xu ly
		}
	}

}
