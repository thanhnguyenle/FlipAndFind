package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model_level.LevelData;
import model_listCards.ModelDataListCard;
import model_user.UsersData;
import view_dialog.IDialogGame;
import view_dialog.YouLoseDialog;
import view_menuScreen.IMainScreenView;
import view_menuScreen.ScreenData;

public class ActionYouLose extends ActionDialogAbstract {
	private YouLoseDialog yld;

	public ActionYouLose(ModelDataListCard model, LevelData levelData, IMainScreenView mainMenu, ScreenData screenData,
			UsersData usersData, IDialogGame dialog, ActionFlipCard flipCard, ActionLimitTime limitTime) {
		super(model, levelData, mainMenu, screenData, usersData, dialog, flipCard, limitTime);
		// TODO Auto-generated constructor stub
		this.yld = (YouLoseDialog) dialog;
		
		// add action to button
		addActionForButton();
		setCursorWhenHoverButton();
	}

	@Override
	public void addActionForButton() {
		// TODO Auto-generated method stub
		yld.getDiReturn().addActionListener(actionAgain());
		yld.getDiHome().addActionListener(actionHome());
	}

	@Override
	public ActionListener actionHome() {
		// TODO Auto-generated method stub
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playAgain();
				pgs.close();
				mainMenu.open();
				//turn off dialog
			}
		};
	}

	@Override
	public void setCursorWhenHoverButton() {
		// TODO Auto-generated method stub
		yld.getDiReturn().setCursor(new Cursor(Cursor.HAND_CURSOR));
		yld.getDiHome().setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
