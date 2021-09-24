package view_user;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model_user.User;
import view_support.ButtonImage;
import view_support.DrawTitle;

public class LabelUser extends JPanel {
	private User user;
	private ButtonImage avatar;
	private DrawTitle userName;
	private DrawTitle score;
	private DrawTitle level;

	public LabelUser(User user, ButtonImage avatar) {
		super();
		this.user = user;
		this.avatar = avatar;
		this.userName = new DrawTitle(user.getUserName(),true);
		this.userName.setFont(new Font("Papyrus",Font.BOLD,20));
		this.score = new DrawTitle(user.getScore()+"",true);
		this.score.setFont(new Font("Papyrus",Font.BOLD,20));
		this.level = new DrawTitle(user.getRank()+"",true);
		this.level.setFont(new Font("Papyrus",Font.BOLD,20));

		// set panel
		setLayout(null);

		//
		avatar.setBounds(0, 0, (int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1)));
		userName.setBounds((int) (getWidth() * (1 / 4.0)), 0, (int) (getWidth() * (1 / 2.0)),
				(int) (getHeight() * (1 / 2.0)));
		score.setBounds((int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1 / 2.0)), (int) (getWidth() * (1 / 2.0)),
				(int) (getHeight() * (1 / 2.0)));
		level.setBounds((int) (getWidth() * (1 / 2.0)), 0, (int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1)));
		
		//add to panel
		add(avatar);
		add(userName);
		add(score);
		add(level);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		avatar.setBounds(0, 0, (int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1)));
		userName.setBounds((int) (getWidth() * (1 / 4.0)), 0, (int) (getWidth() * (1 / 2.0)),
				(int) (getHeight() * (1 / 2.0)));
		score.setBounds((int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1 / 2.0)), (int) (getWidth() * (1 / 2.0)),
				(int) (getHeight() * (1 / 2.0)));
		level.setBounds((int) (getWidth() * (3 / 4.0)), 0, (int) (getWidth() * (1 / 4.0)), (int) (getHeight() * (1)));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ButtonImage getAvatar() {
		return avatar;
	}

	public void setAvatar(ButtonImage avatar) {
		this.avatar = avatar;
	}

	public DrawTitle getUserName() {
		return userName;
	}

	public void setUserName(DrawTitle userName) {
		this.userName = userName;
	}

	public DrawTitle getScore() {
		return score;
	}

	public void setScore(DrawTitle score) {
		this.score = score;
	}

	public DrawTitle getLevel() {
		return level;
	}

	public void setLevel(DrawTitle level) {
		this.level = level;
	}

}
