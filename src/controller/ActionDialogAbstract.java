package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Stack;

import model_level.LevelData;
import model_listCards.IListCardsAdapter;
import model_listCards.ModelDataListCard;
import model_user.ListUsers;
import model_user.User;
import model_user.UsersData;
import view_card.ICardView;
import view_card.IListCardsView;
import view_dialog.IDialogGame;
import view_menuScreen.IMainScreenView;
import view_menuScreen.PlayGameScreen;
import view_menuScreen.ScreenData;

public abstract class ActionDialogAbstract {
	// MODEL
	protected ModelDataListCard model;
	protected LevelData levelData;
	protected UsersData usersData;
	protected ListUsers listUsers;

	// ACTION
	protected ActionLimitTime limitTime;
	protected ActionFlipCard flipCard;

	// VIEW
	protected IMainScreenView mainMenu;
	protected ScreenData screenData;
	protected IDialogGame dialog;
	protected PlayGameScreen pgs;
	protected IListCardsView listCardsView;

	// field common
	protected Stack<IListCardsAdapter> memoryTemp;
	protected int score;

	protected ActionDialogAbstract(ModelDataListCard model, LevelData levelData, IMainScreenView mainMenu,
			ScreenData screenData, UsersData usersData, IDialogGame dialog, ActionFlipCard flipCard,
			ActionLimitTime limitTime) {
		super();
		this.model = model;
		this.levelData = levelData;
		this.mainMenu = mainMenu;
		this.screenData = screenData;
		this.usersData = usersData;
		this.dialog = dialog;
		this.flipCard = flipCard;
		this.limitTime = limitTime;

		memoryTemp = new Stack<>();

		// get PlayGameScreen from mainMenu
		pgs = (PlayGameScreen) mainMenu.listScreenMenuItems().getScreen("Play Game");
		listCardsView = pgs.getListCardsView();
	}

	public ActionListener actionAgain() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playAgain();
			}
		};
	}

	public abstract ActionListener actionHome();

	public abstract void addActionForButton();

	public abstract void setCursorWhenHoverButton();

	public void playAgain() {
		score = flipCard.getScore();

		// fix error null pointer if play again lv1
		if (!memoryTemp.contains(listCardsView.getIListCards()))
			memoryTemp.push(listCardsView.getIListCards());
		User user = screenData.getUser();

		// set inform user
		int l = user.getLevel();
		levelData.setLevel(l);
		levelData.getListCardsLevel();

		// set limit time
		limitTime.setLimitTime(levelData.getTimeLimit());
		limitTime.setTimeRun(levelData.getTimeLimit());
		limitTime.getJpb().setValue(0);
		flipCard.setGameStarted(false);

		// get listUser and user
		listUsers = usersData.getListUsers();
		User u = screenData.getUser();
		listUsers.removeUser(u);

		// set inform user
		user.setLevel(l);

		// set listtop
		listUsers.addUser(user);
		List<User> newListUsers=listUsers.sortByScore();
		listUsers.setUsers(newListUsers);
		usersData.setUsersData(listUsers, l);

		model.setData(memoryTemp.pop());
		listCardsView = pgs.getListCardsView();
		listCardsView.setFaceDownAll();
		listCardsView.shuffle();

		// add action for new list card
		for (ICardView cv : listCardsView.getListCards()) {
			MouseAdapter action = flipCard.flip(cv.getCard(), cv);
			cv.getThisCardJPanel().addMouseListener(action);
		}

		// next level
		pgs.setWin(false);
		pgs.setLose(false);
		dialog.getThisPanel().setVisible(false);
		listCardsView.getThisPanel().setVisible(true);
		

		// set score = 0
		flipCard.setScore(0);
		score = 0;
		screenData.setDataScreens(user, score, l);
	}
}