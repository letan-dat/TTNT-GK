package model;

import view.BoardView;

public class EatBox implements IEatBox {
	private Model_Interface game;
	private BoardView view;

	public EatBox(Model_Interface game) {
		super();
		this.game = game;
		this.view = game.getView();
	}

	@Override
	public void eat(int index, boolean direction, boolean turn) {
		// TODO Auto-generated method stub
		recursiveEat(index, direction, turn);
		if (game.isOver()) {
			game.gameOver();
		} else {
			game.turnOver(turn);
		}
	}

	@Override
	public void recursiveEat(int index, boolean direction, boolean turn) {
		// TODO Auto-generated method stub

		// Xem đây là lượt của người chơi nào để khi ăn được thì cập nhật điểm cho người đó
		int i = turn ? 0 : 1;

		if (direction == false) {
			if ((index != 0 || index != 6) && game.getListBox().get(index).getNumberStone() == 0) {
				if (index == 0) // Trường hợp khi đến cuối bàn cờ thì gán vị trí giả để tiếp tục xử lý
					index = 12; // Khi đi sang trái vị trí 0 là vị trí cuối cùng nên chuyển sang 12 để đi ô tiếp
								// theo
				index = index - 1;

				// Ăn quân
				// Lấy giá trị ô bị ăn
				// Cập nhật giá trị ô bị ăn về 0
				// Thêm số quân đó vào điểm của người chơi tương ứng
				// Cập nhật trên View

				if (game.getListBox().get(index).getNumberStone() != 0) {
					int soldier = game.getListBox().get(index).getNumberStone();
					game.getListBox().get(index).setNumberStone(0);
					game.getListPlayer().get(i).setScore(soldier + game.getListPlayer().get(i).getScore());

					// update trang thai tren BoardView

					try {
						view.draw(index, turn);
						view.updateStone(index, game.getListBox().get(index).getNumberStone());
						view.updateScore(game.getListPlayer().get(i).getScore(), turn);
						Thread.sleep(500);
						view.reDraw(index);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Eat A");
					}

					//

					if (index == 0) {
						index = 12; // Trường hợp đặt biệt nên gán vị trí giả để xử lý tiếp tục
					}
					// Nếu ô tiếp theo rỗng và ô tiếp theo nữa có thể ăn thì thực hiện việc xét ăn ô tiếp theo
					if (game.getListBox().get(index - 1).getNumberStone() == 0 && (index - 1 != 0 || index - 1 != 6)) {
						recursiveEat(index - 1, direction, turn);
					}
				}
			}
		} else {
			// Tương tự ở trên nhưng đổi chiều
			if ((index != 0 || index != 6) && game.getListBox().get(index).getNumberStone() == 0) {
				if (index == 11) {
					index = -1;
				}
				index = index + 1;
				if (game.getListBox().get(index).getNumberStone() != 0) {
					int soldier = game.getListBox().get(index).getNumberStone();
					game.getListBox().get(index).setNumberStone(0);
					game.getListPlayer().get(i).setScore(soldier + game.getListPlayer().get(i).getScore());

					// update trang thai tren BoardView

					try {
						view.draw(index, turn);
						view.updateStone(index, game.getListBox().get(index).getNumberStone());
						view.updateScore(game.getListPlayer().get(i).getScore(), turn);
						Thread.sleep(500);
						view.reDraw(index);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Eat B");
					}

					//

					if (index == 11) {
						index = -1;
					}
					if (game.getListBox().get(index + 1).getNumberStone() == 0 && (index + 1 != 6 || index + 1 != 12)) {
						recursiveEat(index + 1, direction, turn);
					}
				}
			}
		}

	}

}
