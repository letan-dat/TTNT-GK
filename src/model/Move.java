package model;

import view.BoardView;

public class Move implements IMove {
	private Model_Interface game;
	private IEatBox eatBox;
	private BoardView view;

	public Move(Model_Interface game) {
		this.game = game;
		this.view = game.getView();
		this.eatBox = new EatBox(game);
	}

	@Override
	public void move(int index, boolean direction, boolean turn) {
		// TODO Auto-generated method stub
		if (index > 6 && index < 12) {
			direction = direction ? false : true;
		}
		recursiveMove(index, direction, turn);

	}

	@Override
	public void recursiveMove(int index, boolean direction, boolean turn) {
		// TODO Auto-generated method stub

		// Lấy số quân ở ô được chọn ra
		// Cập nhật số quân lại bằng 0

		int numberStone = game.getListBox().get(index).getNumberStone();
		game.getListBox().get(index).setNumberStone(0);

		// update trang thai tren BoardView

		try {
			view.draw(index, turn);
			view.updateRestStone(numberStone, turn);
			view.updateStone(index, game.getListBox().get(index).getNumberStone());
			Thread.sleep(500);
			view.reDraw(index);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Move A");
		}

		//

		// Xử lý theo hướng đi

		if (direction == false) {// Di chuyển sang trái
			while (numberStone != 0) {
				if (index == 0) {// Trường hợp đặt biệt, gán vị trí giả để xử lý
					index = 12;
				}
				// Vị trí ô hiện tại giảm 1 và số quân ở ô đó được cộng thêm 1
				// Số quân dùng để rải giảm đi 1
				index--;
				game.getListBox().get(index).setNumberStone(game.getListBox().get(index).getNumberStone() + 1);
				numberStone--;

				// update trang thai tren BoardView

				try {
					view.draw(index, turn);
					view.updateRestStone(numberStone, turn);
					view.updateStone(index, game.getListBox().get(index).getNumberStone());
					Thread.sleep(500);
					view.reDraw(index);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Move B");
				}

				//

				if (index == 0) {
					index = 12;
				}
				//
				if (numberStone == 0) {
						//TH1
					if (index == 1 || index == 7 || (game.getListBox().get(index - 1).getNumberStone() == 0
							&& game.getListBox().get(index - 2).getNumberStone() == 0)) {
						game.turnOver(turn);
					} 
						//TH2
					else if ((index - 1 != 0 || index - 1 != 6)
							&& game.getListBox().get(index - 1).getNumberStone() == 0) {
						eatBox.eat(index - 1, direction, turn);
					}	
						//TH3
					else if ((index - 1 != 0 || index - 1 != 6)
							&& game.getListBox().get(index - 1).getNumberStone() != 0) {
						recursiveMove(index - 1, direction, turn);
					}
				}
			}
		} else {
			while (numberStone != 0) {
				if (index == 11) {
					index = -1;
				}
				index++;
				game.getListBox().get(index).setNumberStone(game.getListBox().get(index).getNumberStone() + 1);
				numberStone--;

				// update trang thai tren BoardView

				try {
					view.draw(index, turn);
					view.updateRestStone(numberStone, turn);
					view.updateStone(index, game.getListBox().get(index).getNumberStone());
					Thread.sleep(500);
					view.reDraw(index);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Move C");
				}

				//

				if (index == 11) {
					index = -1;
				}
				if (numberStone == 0) {
					int temp = (index == 10) ? 0 : index + 2;
						//TH1
					if ((index == 5 || index == -1) || (game.getListBox().get(index + 1).getNumberStone() == 0
							&& game.getListBox().get(temp).getNumberStone() == 0)) {
						game.turnOver(turn);
					} 
						//TH2
					else if ((index + 1 != 0 || index + 1 != 6)
							&& game.getListBox().get(index + 1).getNumberStone() == 0) {
						eatBox.eat(index + 1, direction, turn);
					} 
						//TH3
					else if ((index + 1 != 0 || index + 1 != 6)
							&& game.getListBox().get(index + 1).getNumberStone() != 0) {
						recursiveMove(index + 1, direction, turn);
					}
				}
			}
		}

	}

}
