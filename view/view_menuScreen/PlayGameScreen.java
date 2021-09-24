package view_menuScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import constants.ImageDialog;
import constants.ImageUser;
import model_level.LevelData;
import model_listCards.IListCardsAdapter;
import model_listCards.IObserverModel;
import model_listCards.IObsevableModel;
import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;
import view_card.IListCardsView;
import view_card.ListCardsView;
import view_dialog.YouLoseDialog;
import view_dialog.YouWinDialog;
import view_support.ButtonImage;
import view_support.DrawTitle;
import view_support.LimitTimePanel;
import view_user.LabelUser;
import view_user.RankByLevel;
import view_user.TopUsers;

public class PlayGameScreen extends ScreenMenuItemAbstract
		implements IObserverView, IDisplayScreen, IObserverModel, IUserObserver, IScreenMenuItem {

	private static IScreenMenuItem uniqueInstance;
	private IListCardsView listCardsView;
	private LevelData levelData;
	private JPanel jp;
	private JPanel jpUser, jpScore, jpLevel;
	private DrawTitle dtScoreTitle, dtLevelTitle, dtScoreNum, dtLevelNum;
	private LabelUser labelUser;

	// of high scorez
	private TopUsers topUsers;
	private RankByLevel rankByLevel;
	private ListUsers listUsers;
	private IListCardsAdapter listCards;
	private final int heightJp = 50;
	private final int widthRankByLevel = 200;
	// check first to play
	private boolean checkOnce = true;
	// button hide and appear of top rank
	private JButton jbHide, jbAppear;
	private JPanel jpHiddenOfTopRank;
	private boolean hidden = true;

	// dialog
	private YouWinDialog cdb;
	private boolean isWin;
	private YouLoseDialog yld;
	private boolean isLose;

	// time line
	private LimitTimePanel ltp;
	private final int heightLtp = 20;

	// of user
	private int score;
	private int level;
	private User user;
	private Image avatar;

	// go to menu button
	private ButtonImage jbGoToMenu;

	private PlayGameScreen(IObservableView screenData, IObsevableModel modelData, IUserObservable userData) {
		super(screenData, modelData, userData);

		modelData.registerObserver(this);
		userData.registerObserver(this);
		screenData.registerObserver(this);

		display();
	}

	// Singleton pattern
	public static IScreenMenuItem getInstance(IObservableView screenData, IObsevableModel modelData,
			IUserObservable userData) {
		if (uniqueInstance == null) {
			uniqueInstance = new PlayGameScreen(screenData, modelData, userData);
		}
		return uniqueInstance;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		// jp
		setLayout(null);

		jp = new JPanel(null);
		add(jp);
		jp.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// JPANEL USER
		user = new User("VoDinh", "Dep Trai");
		ButtonImage di = new ButtonImage(avatar);
		labelUser = new LabelUser(user, di);
		jp.add(labelUser);
		// labelUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// JPANEL SCORE
		jpScore = new JPanel(null);
		// set Title score
		dtScoreTitle = new DrawTitle("Score", true);
		dtScoreTitle.setFont(new Font("Castellar", Font.BOLD, 20));

		// set num score
		dtScoreNum = new DrawTitle(0 + "", true);
		dtScoreNum.setFont(new Font("Castellar", Font.PLAIN, 20));

		// add
		jp.add(jpScore);
		jpScore.add(dtScoreTitle);
		jpScore.add(dtScoreNum);
		// jpScore.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// JPANEL LEVEL
		jpLevel = new JPanel(null);

		// set Tilte level
		dtLevelTitle = new DrawTitle("Level", true);
		dtLevelTitle.setFont(new Font("Castellar", Font.BOLD, 20));

		// set num level
		dtLevelNum = new DrawTitle(user.getLevel() + "", true);
		dtLevelNum.setFont(new Font("Castellar", Font.PLAIN, 20));

		// add
		jp.add(jpLevel);
		jpLevel.add(dtLevelTitle);
		jpLevel.add(dtLevelNum);
		// jpLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// top rank
		topUsers = new TopUsers(this.userData);
		rankByLevel = new RankByLevel(level, topUsers, userData);
		add(rankByLevel);
		// rankByLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// game play
		listCardsView = new ListCardsView(modelData);
		add(listCardsView.getThisPanel());
		// listCardsView.getThisPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK));

		ltp = new LimitTimePanel();
		add(ltp);

		cdb = new YouWinDialog();
		// add(cdb);
		cdb.setVisible(false);

		yld = new YouLoseDialog();
		// add(yld);
		yld.setVisible(false);

		// go to menu button
		jbGoToMenu = new ButtonImage(ImageDialog.RETURN);
		jbGoToMenu.setActionCommand("Play Game");
		jp.add(jbGoToMenu);

		// hide and appear of top rank
		jpHiddenOfTopRank = new JPanel(null);
		jbHide = new JButton("Hide");
		jbAppear = new JButton("Appear");
		jpHiddenOfTopRank.add(jbAppear);
		jpHiddenOfTopRank.add(jbHide);
		add(jpHiddenOfTopRank);
	}

	@Override
	public void update(User user, int nowScore, int nowLevel) {
		// TODO Auto-generated method stub
		this.score = nowScore;
		this.level = nowLevel;
		this.user = user;

		// repaint jpanel USER
		Image avatar;
		int k = 0;
		if (user != null) {
			if (!user.getPathAvatar().isBlank()) {
				avatar = new ImageIcon(user.getPathAvatar()).getImage();
			} else {
				k = (int) (Math.random() * 5);
				avatar = new ImageIcon(ImageUser.AVATAR[k]).getImage();
			}
			// change lableUser in view
			labelUser.getUserName().setTitle(user.getUserName());

			labelUser.getScore().setTitle(user.getScore() + "");
			labelUser.getLevel().setTitle(user.getLevel() + "");

			labelUser.getAvatar().setImage(avatar);
			user.setPathAvatar(ImageUser.AVATAR[k] + "");
			labelUser.repaint();
		}

		dtScoreNum.setTitle(nowScore + "");
		dtLevelNum.setTitle(nowLevel + "");
		//
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		jp.setBounds(0, 0, getWidth(), heightJp);

		// USER
		labelUser.setBounds(0, 0, (int) (jp.getWidth() * (1 / 3.0)), jp.getHeight());

		// SCORE
		double lengthJpRest = jp.getWidth() - (jp.getWidth() * (1 / 3.0));
		jpScore.setBounds((int) (jp.getWidth() * (1 / 3.0)), 0, (int) (lengthJpRest * (2 / 5.0)), jp.getHeight());
		dtScoreTitle.setBounds(0, 0, jpScore.getWidth(), jpScore.getHeight() / 2);
		dtScoreNum.setBounds(0, jpScore.getHeight() / 2, jpScore.getWidth(), jpScore.getHeight() / 2);

		// LEVEL
		double posEndOfJpScore = (int) (lengthJpRest * (2 / 5.0)) + (int) (jp.getWidth() * (1 / 3.0));
		jpLevel.setBounds((int) posEndOfJpScore, 0, (int) (lengthJpRest * (2 / 5.0)), jp.getHeight());
		dtLevelTitle.setBounds(0, 0, jpLevel.getWidth(), jpLevel.getHeight() / 2);
		dtLevelNum.setBounds(0, jpLevel.getHeight() / 2, jpLevel.getWidth(), jpLevel.getHeight() / 2);

		// Button go to menu
		double posEndOfJpLevel = posEndOfJpScore + (lengthJpRest * (2 / 5.0));
		double wOfGoToMenu = jp.getWidth() - posEndOfJpLevel;
		jbGoToMenu.setBounds((int) (wOfGoToMenu / 2 - jp.getHeight() / 2 + posEndOfJpLevel), 0, jp.getHeight(),
				jp.getHeight());

		// top rank
		if (!hidden) {
			rankByLevel.setBounds(3, heightJp + 20, widthRankByLevel - 3, this.getHeight() - heightJp - 20);
			// fix error scroll of top rank
			if (checkOnce) {
				int wscol = rankByLevel.getTopUsers().getScrollPane().getWidth();
				int hscol = rankByLevel.getTopUsers().getScrollPane().getHeight();
				rankByLevel.getTopUsers().getJtTop().setBounds(0, 0, wscol, hscol);
				checkOnce = false;
			}
		}//else rankByLevel.setVisible(false);
		
		// HIDEN AND APPEAR TOP RANK
		jpHiddenOfTopRank.setBounds(3, heightJp, widthRankByLevel, 20);
		jbHide.setBounds(0, 0, jpHiddenOfTopRank.getWidth() / 2, jpHiddenOfTopRank.getHeight());
		jbAppear.setBounds(jpHiddenOfTopRank.getWidth() / 2, 0, jpHiddenOfTopRank.getWidth() / 2,
				jpHiddenOfTopRank.getHeight());

		// game play
		if(!hidden)
		listCardsView.getThisPanel().setBounds(widthRankByLevel + 5, heightJp + heightLtp,
				this.getWidth() - widthRankByLevel - 5, this.getHeight() - heightJp - heightLtp);

		else
			listCardsView.getThisPanel().setBounds(5, heightJp + heightLtp,
					this.getWidth() -5, this.getHeight() - heightJp - heightLtp);
		
		ltp.setBounds(widthRankByLevel + 5, heightJp, this.getWidth() - widthRankByLevel - 5, heightLtp);
		repaint();
	}

	public void setCheckOnce(boolean checkOnce) {
		this.checkOnce = checkOnce;
	}

	public RankByLevel getRankByLevel() {
		return rankByLevel;
	}

	public void setRankByLevel(RankByLevel rankByLevel) {
		this.rankByLevel = rankByLevel;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String nameScreen() {
		// TODO Auto-generated method stub
		return "Play Game";
	}

	public JButton getJbGoToMenu() {
		return jbGoToMenu;
	}

	@Override
	public JPanel getScreen() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void update(IListCardsAdapter listCards) {
		// TODO Auto-generated method stub
		this.listCards = listCards;
	}

	@Override
	public void update(ListUsers listUsers, int level) {
		// TODO Auto-generated method stub
		this.listUsers = listUsers;
	}

	public YouWinDialog getCdb() {
		return cdb;
	}

	public YouLoseDialog getYld() {
		return yld;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
		// win dialog vs lose dialog is same the size
		if (isWin) {
			cdb.setVisible(true);
		}

	}

	public boolean isLose() {
		return isLose;
	}

	public void setLose(boolean isLose) {
		this.isLose = isLose;
		if (isLose) {
			yld.setVisible(true);
		}
	}

	public LimitTimePanel getLtp() {
		return ltp;
	}

	public IListCardsView getListCardsView() {
		return listCardsView;
	}

	public void setListCardsView(IListCardsView listCardsView) {
		this.listCardsView = listCardsView;
	}

	public JButton getJbHide() {
		return jbHide;
	}

	public JButton getJbAppear() {
		return jbAppear;
	}

}
