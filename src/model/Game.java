package model;

import java.util.ArrayList;
import java.util.List;

import view.BoardView;

public abstract class Game implements Model_Interface {
	protected BoardView view;
	protected List<IBox> listBox = new ArrayList<>();
	protected List<IPlayer> listPlayer = new ArrayList<>();
	private IMove move;
	public final boolean TURNPLAYER_1 = true;
	public final boolean TURNPLAYER_2 = false;
	public final boolean DIRECTION_LEFT = false;
	public final boolean DIRECTION_RIGHT = true;

	public Game(BoardView view) {
		this.view = view;
		this.move = new Move(this);
		createBox();
	}

	public void createBox() {
		for (int i = 0; i < 12; i++) {
			IBox tempBox = new Box(i);
			listBox.add(tempBox);
		}

	}

	@Override
	public boolean isValid(int index, boolean turn) {
		// TODO Auto-generated method stub
		if (listBox.get(index).getNumberStone() == 0 || index == 0)
			return false;
		if (((turn == TURNPLAYER_1) && (index > 0 && index < 6))
				|| ((turn == TURNPLAYER_2) && (index > 6 && index < 12))) {
			return true;
		}
		return false;
	}

	@Override
	public void move(int index, boolean direction, boolean turn) {
		// TODO Auto-generated method stub
		if (isValid(index, turn)) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					move.move(index, direction, turn);
				}
			}).start();

		}
	}

	@Override
	public abstract void turnOver(boolean turn);

	@Override
	public boolean checkBox(boolean turn) {
		// TODO Auto-generated method stub

		if (turn) {
			for (int i = 1; i < 6; i++) {
				if (listBox.get(i).getNumberStone() != 0)
					return false;
			}
		} else {
			for (int i = 7; i < 12; i++) {
				if (listBox.get(i).getNumberStone() != 0)
					return false;
			}
		}

		return true;
	}

	@Override
	public void addChess(boolean turn) {
		// TODO Auto-generated method stub
		if (turn) {
			listPlayer.get(0).setScore(listPlayer.get(0).getScore() - 5);
			view.updateScore(listPlayer.get(0).getScore(), turn);
			for (int i = 1; i < 6; i++) {
				listBox.get(i).setNumberStone(1);
				view.updateStone(i, 1);
			}
		} else {
			listPlayer.get(1).setScore(listPlayer.get(1).getScore() - 5);
			view.updateScore(listPlayer.get(1).getScore(), turn);
			for (int i = 7; i < 12; i++) {
				listBox.get(i).setNumberStone(1);
				view.updateStone(i, 1);

			}
		}
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return listBox.get(0).getNumberStone() == 0 && listBox.get(6).getNumberStone() == 0;
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		for (int i = 1; i < 6; i++) {
			int score = listBox.get(i).getNumberStone();
			listBox.get(i).setNumberStone(0);
			listPlayer.get(0).setScore(listPlayer.get(0).getScore() + score);

		}
		for (int i = 7; i < 12; i++) {
			int score = listBox.get(i).getNumberStone();
			listBox.get(i).setNumberStone(0);
			listPlayer.get(1).setScore(listPlayer.get(1).getScore() + score);
		}
		view.gameOver(listPlayer);
	}

	public List<IBox> getListBox() {
		return listBox;
	}

	public List<IPlayer> getListPlayer() {
		return listPlayer;
	}

	@Override
	public BoardView getView() {
		return view;
	}

}
