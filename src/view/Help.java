package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;

public class Help extends JFrame implements IFrame, IHelp {
	private JButton btnBack;
	private IMenu menu;

	public Help() {

		setSize(WIDTHJF, HEIGHTJF);
		setTitle("Ô Ăn Quan");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
		back();
	}

	public void init() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		JTextArea myTextArea = new JTextArea("- Trò chơi kết thúc khi hai ô quan hết dân \n"
				+ "- Di chuyển : chọn ô và hướng tương ứng trên bàn cờ \n" + "- Khi rải hết dân: \n"
				+ "   + Ô tiếp theo có dân thì tiếp tục lấy ô dân đó rải, gặp ô quan thì dừng \n"
				+ "   + Hai ô tiếp theo hết dân hoặc quan thì đổi lượt \n"
				+ "   + Ô tiếp theo hết dân và ô sau đó có dân thì được ăn, quan thì được ăn \n"
				+ "- Đến lượt mà hết dân thì lấy 5 dân của bản thân đã ăn trước đó rải vào phần sân của mình và di chuyển \n"
				+ "- Khi 'hết quan tàn dân' thì dân bên phần sân của người nào thì thuộc về người đó");
		myTextArea.setEditable(false);
		myTextArea.setForeground(Color.BLACK);
		myTextArea.setBackground(Color.WHITE);
		myTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
		myTextArea.setLineWrap(true);
		javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(myTextArea);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);

		btnBack = new JButton("OK");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnBack);
	}

	@Override
	public void back() {
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				menu = new Menu();
				menu.setVisible(true);
			}
		});
	}

}
