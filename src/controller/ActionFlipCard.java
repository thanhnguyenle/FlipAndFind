package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.util.TimerTask;

import javax.swing.Timer;

import model_level.LevelData;
import model_listCards.ICardAdapter;
import model_listCards.IListCardsAdapter;
import model_listCards.ModelDataListCard;
import model_user.ListUsers;
import model_user.User;
import model_user.UsersData;
import view_card.ICardView;
import view_card.IListCardsView;
import view_dialog.YouWinDialog;
import view_menuScreen.IMainScreenView;
import view_menuScreen.PlayGameScreen;
import view_menuScreen.ScreenData;

public class ActionFlipCard {
	private ModelDataListCard model;
	private IMainScreenView mainMenu;
	private IListCardsView listCardsView;

	// time card waiting when click 2 card different
	private Timer time;
	private Timer timeStop;
	// time waiting of 1 card
	private java.util.Timer timeTask;

	private ICardView firstCard;
	private ICardView secondCard;
	private boolean clickFirst;
	private boolean clickSecond;
	private LevelData level;
	private ScreenData screenData;
	private UsersData usersData;
	private ListUsers listUsers;
	private int score = 0;
	private PlayGameScreen pgs;
	private boolean pressedReturn;
	private boolean pressedNext;
	private YouWinDialog cdb;
	private ActionLimitTime lt;
	private boolean gameStarted;

	public ActionFlipCard(ModelDataListCard model, LevelData levelData, IMainScreenView mainMenu, ScreenData screenData,
			UsersData usersData, ActionLimitTime lt) {
		super();
		this.model = model;
		this.mainMenu = mainMenu;
		this.level = levelData;
		this.screenData = screenData;
		this.usersData = usersData;
		this.lt = lt;

		// get listsCardView from PlayGameScreen
		pgs = (PlayGameScreen) mainMenu.listScreenMenuItems().getScreen("Play Game");
		listCardsView = pgs.getListCardsView();

		// confirmation
		cdb = pgs.getCdb();

		// add action
		addActionForCard();

	}

	public void addActionForCard() {
		for (ICardView cv : listCardsView.getListCards()) {
			MouseAdapter action = flip(cv.getCard(), cv);
			cv.getThisCardJPanel().addMouseListener(action);
		}
	}

	public MouseAdapter flip(ICardAdapter card, ICardView cv) {
		return new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (!clickFirst) {
					// timelimit
					if (!gameStarted) {
						getLt().getTimer().start();
						gameStarted = true;
					}

					// save cv to firstCard and set clickFirst = TRUE
					clickFirst = true;
					firstCard = cv;

					// SET CARD FACE UP
					card.setFaceUp();
					cv.getThisCardJPanel().repaint();

					// face down waiting that time out
					timeTask = new java.util.Timer();
					timeTask.schedule(faceDownWaiting(), 2000);

					// set card second is not same card first
				} else if (!clickSecond && firstCard != cv) {
					// STOP FACE DOWN
					timeTask.cancel();

					// save cv to secondCard and set clickSecond = true
					clickSecond = true;
					secondCard = cv;

					// SET CARD FACE UP
					card.setFaceUp();
					cv.getThisCardJPanel().repaint();

					// time waiting to memory card, before face down
					time = new Timer(400, effectCard(firstCard, secondCard));
					time.start();

					// set to click first = false and click second = false, start again
					againStart();

				}
			}
		};
	}

	// when 1 card face up time out
	public TimerTask faceDownWaiting() {
		return new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!clickSecond) {
					// SET CARD FACE DOWN and stop second click -> start again
					firstCard.getCard().setFaceDown();
					firstCard.getThisCardJPanel().repaint();
					clickFirst = false;
				}
			}
		};
	}

	// when there are 2 cards face up
	public ActionListener effectCard(ICardView firstCardView, ICardView secondCardView) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 2 card with the same id
				if (firstCardView.getCard().getIdCard() == secondCardView.getCard().getIdCard()) {
					firstCardView.getCard().setHidden(true);
					secondCardView.getCard().setHidden(true);

					// increase score
					increaseScore();
				} else {
					// 2 cards have id that different
					firstCardView.getCard().setFaceDown();
					secondCardView.getCard().setFaceDown();
				}
				firstCardView.getThisCardJPanel().repaint();
				secondCardView.getThisCardJPanel().repaint();

				// if winner
				if (isWinner()) {
					getLt().getTimer().stop();
					levelUp();
				}
				time.stop();
			}
		};
	}

//increase score and level
	public void increaseScore() {
		User user = screenData.getUser();
		//int l = model.getLevelData().getLevel();
		score += scoreByLevel(screenData.getNowLevel());
		screenData.setDataScreens(user, score, screenData.getNowLevel());
	}

//level up
	public void levelUp() {
		gameStarted = false;
		if (getLt().getJpb().getValue() > 0)
			// reset timer
			getLt().getJpb().setValue(0);
		getLt().getTimer().stop();
		// change screen
		listCardsView.getThisPanel().setVisible(false);
		cdb.setVisible(true);
		pgs.setWin(true);

	}

	// start again
	public void againStart() {
		clickFirst = false;
		clickSecond = false;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public boolean isWinner() {
		if (listCardsView.getIListCards().checkAllHidden())
			return true;
		return false;
	}

	// score
	public int scoreByLevel(int level) {
		if (level <= 5) {
			return 100;
		} else if (level <= 10)
			return 200;
		else if (level <= 20)
			return 400;
		else if (level <= 40)
			return 1000;
		return 0;
	}

	public IListCardsView getListsCardView() {
		return listCardsView;
	}

	public void setListsCardView(IListCardsView listsCardView) {
		this.listCardsView = listsCardView;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ActionLimitTime getLt() {
		return lt;
	}

}
