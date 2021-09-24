package view_menuScreen;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model_listCards.IObsevableModel;
import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;
import view_user.RankByLevel;
import view_user.TopUsers;

public class HighScoreScreen extends ScreenMenuItemAbstract implements IObserverView, IUserObserver {
	private static IScreenMenuItem uniqueInstance;
	private TopUsers topUsers;
	private RankByLevel rankByLevel;
	private ListUsers listUsers;
	private int level;
	private IUserObservable userData;
	//
	private JPanel pnlTittle;
	private JLabel lblTittle, lblBackground;
	private JButton btnExit = new JButton("Exit"), btnClear = new JButton("Clear");

	public HighScoreScreen(IObservableView screenData, IObsevableModel modelData, IUserObservable userData) {
		super(screenData, modelData, userData);
		// TODO Auto-generated constructor stub
		screenData.registerObserver(this);
		userData.registerObserver(this);
		this.userData = userData;
		display();
	}

	// Singleton pattern
	public static IScreenMenuItem getInstance(IObservableView screenData, IObsevableModel modelData,
			IUserObservable userData) {
		if (uniqueInstance == null) {
			uniqueInstance = new HighScoreScreen(screenData, modelData, userData);
		}
		return uniqueInstance;
	}

	@Override
	public JPanel getScreen() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void update(User user, int nowScore, int nowLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String nameScreen() {
		// TODO Auto-generated method stub
		return "High Score";
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.listUsers = listUsers;
		this.level = level;
	}

	private void display() {
		// top rank
		topUsers = new TopUsers(this.userData);
		rankByLevel = new RankByLevel(level, topUsers, userData);

		// rankByLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		tao va add background JLabel 
		lblBackground = new JLabel();
		lblTittle = new JLabel("Hight Scores");
		lblTittle.setFont(new Font("Serif", Font.PLAIN, 28));
		lblTittle.setHorizontalAlignment(JLabel.CENTER);
		lblBackground.add(lblTittle);


		lblBackground.add(rankByLevel);

//		btnExit: thoat khoi trang, btnClear: xoa het user va diem cua user
		btnExit = new JButton("Exit");
		btnClear = new JButton("Clear");
		lblBackground.add(btnExit);
		lblBackground.add(btnClear);
		add(lblBackground);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		// define
		int h, w;
		h = this.getHeight();
		w = this.getWidth();
		lblBackground.setBounds(0,0,w,h);
		lblTittle.setBounds((w - 600) / 2, 0, 200, 60);
		rankByLevel.setBounds((w - 1100) / 2, 150, 700, 500);
		btnExit.setBounds(w / 2 - 50, 700, 70, 40);
		btnClear.setBounds(w / 2 + 50, 700, 70, 40);
	}

}
