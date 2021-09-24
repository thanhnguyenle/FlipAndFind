package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import view_dialog.YouWinDialog;
import view_menuScreen.IMainScreenView;
import view_menuScreen.PlayGameScreen;
import view_menuScreen.ScreenData;

public class ActionLevelUp extends ActionDialogAbstract {
	private YouWinDialog ywd;

	public ActionLevelUp(ModelDataListCard model, LevelData levelData, IMainScreenView mainMenu, ScreenData screenData,
			UsersData usersData, IDialogGame dialog, ActionFlipCard flipCard, ActionLimitTime limitTime) {
		super(model, levelData, mainMenu, screenData, usersData, dialog, flipCard, limitTime);
		this.ywd = (YouWinDialog) dialog;

		addActionForButton();
		setCursorWhenHoverButton();
	}

	public void setCursorWhenHoverButton() {
		ywd.getDiNext().setCursor(new Cursor(Cursor.HAND_CURSOR));
		ywd.getDiReturn().setCursor(new Cursor(Cursor.HAND_CURSOR));
		ywd.getDiHome().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void addActionForButton() {
		ywd.getDiNext().addActionListener(actionNext());
		ywd.getDiNext().addKeyListener(actionKeyForNext());
		ywd.getDiReturn().addActionListener(actionAgain());
		ywd.getDiHome().addActionListener(actionHome());
		ywd.setFocusable(true);
		ywd.requestFocusInWindow();
	}

	public KeyListener actionKeyForNext() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					levelUp();
				}
			}
		};
	}

	public ActionListener actionNext() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				levelUp();
			}
		};
	}

	public void levelUp() {
		// get listUser and user
		listUsers = usersData.getListUsers();
		User user = screenData.getUser();
		listUsers.removeUser(user);

		score = flipCard.getScore();
		
		// get level
		int lev = screenData.getNowLevel();
		int l = lev + 1;
		levelData.setLevel(l);
		IListCardsAdapter ilca = levelData.getListCardsLevel();

		// set limit time
		limitTime.setLimitTime(levelData.getTimeLimit());
		limitTime.setTimeRun(levelData.getTimeLimit());
		limitTime.getJpb().setValue(0);

		// set inform user
		if (user.getLevel() < l) {
			user.setLevel(l);
		}
		user.setScore(user.getScore() + score);

		// set listtop
		listUsers.addUser(user);
		List<User> newListUsers = listUsers.sortByScore();
		listUsers.setUsers(newListUsers);
		usersData.setUsersData(listUsers, l);

		// save to memory temp
		if (!memoryTemp.contains(listCardsView.getIListCards()))
			memoryTemp.push(listCardsView.getIListCards());
		listCardsView.setFaceDownAll();

		model.setData(ilca);

		// set listCardsView new to model
		listCardsView.repaintListCards();

		// add action for new list card
		for (ICardView cv : listCardsView.getListCards()) {
			MouseAdapter action = flipCard.flip(cv.getCard(), cv);
			cv.getThisCardJPanel().addMouseListener(action);
		}

		// next level
		ywd.setVisible(false);
		listCardsView.getThisPanel().setVisible(true);
		pgs.setWin(false);

		// set score = 0
		flipCard.setScore(0);
		score = 0;
		screenData.setDataScreens(user, score, l);
	}

	@Override
	public ActionListener actionHome() {
		// TODO Auto-generated method stub
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limitTime.getTimer().stop();
				levelUp();
				pgs.close();
				mainMenu.open();
			}
		};
	}

}
