package view_user;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import view_support.DrawTitle;

public class RankByLevel extends JPanel implements IUserObserver {
	private int level;
	private TopUsers topUsers;
	private DrawTitle title;

	public RankByLevel(int level, TopUsers topUsers, IUserObservable usersData) {
		super();
		this.level = level;
		this.topUsers = topUsers;
		usersData.registerObserver(this);
		setLayout(null);

		add(topUsers);

		// title
		title = new DrawTitle("Rank level " + level, true);
		title.setFont(new Font("Bahnschrift SemiLight", Font.BOLD, 20));
		add(title);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		topUsers.setBounds(0, (int) (getHeight() * (1 / 10.0)), getWidth(), (int) (getHeight() * (9 / 10.0)));
		title.setBounds(0, 0, getWidth(), (int) (getHeight() * (1 / 10.0)));
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public TopUsers getTopUsers() {
		return topUsers;
	}

	public DrawTitle getTitle() {
		return title;
	}

	public void setTitle(DrawTitle title) {
		this.title = title;
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.level = level;
		title.setTitle("Rank");
	}

}
