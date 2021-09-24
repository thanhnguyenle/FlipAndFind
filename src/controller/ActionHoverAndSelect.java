package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingWorker;

import constants.ImageButton;
import constants.PathAudioGame;
import model_listCards.ICardAdapter;
import model_listCards.ModelDataListCard;
import view_card.ICardView;
import view_card.IListCardsView;
import view_dialog.YouLoseDialog;
import view_dialog.YouWinDialog;
import view_menuScreen.IListScreenMenuItems;
import view_menuScreen.IMainScreenView;
import view_menuScreen.PlayGameScreen;
import view_support.ButtonImage;
import view_support.DrawTitle;

public class ActionHoverAndSelect {
	private ModelDataListCard model;
	private IListCardsView listsCardView;
	private IMainScreenView mainMenuScreen;
	private IListScreenMenuItems listScreenMenuItems;
	private Map<ButtonImage, DrawTitle> buttonInMenu;
	private YouWinDialog cdb;
	private YouLoseDialog yld;
	private ButtonImage diReturnOfWin, diNextOfWin, diHomeOfWin, diHomeOfLose, diReturnOfLose;
	private AudioAction audioAction;

	public ActionHoverAndSelect(ModelDataListCard model, IMainScreenView mainMenuScreen, AudioAction audioAction) {
		super();
		this.model = model;
		this.mainMenuScreen = mainMenuScreen;
		this.audioAction = audioAction;

		// get buttonInMenu to mainMenu
		buttonInMenu = mainMenuScreen.getJbMap();

		// add action for button in main menu
		addActionForButtonInMenu();

		// get listScreenMenuItems from mainMenuScreen
		listScreenMenuItems = mainMenuScreen.listScreenMenuItems();

		// get listsCardView from PlayGameScreen
		PlayGameScreen pgs = (PlayGameScreen) listScreenMenuItems.getScreen("Play Game");
		listsCardView = pgs.getListCardsView();
		this.cdb = pgs.getCdb();
		this.yld = pgs.getYld();

		// add action card
		addActionForCard();

		// add action for button of dialog box
		addActionForDialog();

	}

	// ADD ACTION FOR DIALOG
	public void addActionForDialog() {
		// for YouWinDialog
		diNextOfWin = cdb.getDiNext();
		diReturnOfWin = cdb.getDiReturn();
		diHomeOfWin = cdb.getDiHome();

		// for YouLoseDialog
		diReturnOfLose = yld.getDiReturn();
		diHomeOfLose = yld.getDiHome();

		// add action
		ButtonImage[] arrDi = { diNextOfWin, diReturnOfWin, diHomeOfWin, diReturnOfLose, diHomeOfLose };
		for (ButtonImage di : arrDi) {
			MouseAdapter action = actionForButtonOfConfirmDialog(di);
			di.addMouseListener(action);
			di.addMouseMotionListener(action);
		}

	}

	// ADD ACTION CARD
	public void addActionForCard() {
		for (ICardView cv : listsCardView.getListCards()) {
			MouseAdapter action = actionHoveredAndSelectedCard(cv.getCard(), cv);
			cv.getThisCardJPanel().addMouseListener(action);
			cv.getThisCardJPanel().addMouseMotionListener(action);
		}
	}

	// ADD ACTION BUTTON IN MENU
	public void addActionForButtonInMenu() {
		for (Entry<ButtonImage, DrawTitle> button : buttonInMenu.entrySet()) {
			ButtonImage di = button.getKey();
			MouseAdapter action = actionButtonInMenu(di);
			di.addMouseListener(action);
			di.addMouseMotionListener(action);
		}

		// action
		ButtonImage diSound = mainMenuScreen.getDiSound();
		MouseAdapter actionSound = actionButtonInMenu(diSound);
		diSound.addMouseListener(actionSound);
		diSound.addMouseMotionListener(actionSound);
		diSound.addActionListener(actionSound(diSound));

		ButtonImage diUser = mainMenuScreen.getDiUser();
		MouseAdapter actionUser = actionButtonInMenu(diUser);
		diUser.addMouseListener(actionUser);
		diUser.addMouseMotionListener(actionUser);
	}

	// ACTION CARD
	public MouseAdapter actionHoveredAndSelectedCard(ICardAdapter card, ICardView cv) {
		return new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				card.setHovered(true);
				cv.getThisCardJPanel().repaint();
				cv.getThisCardJPanel().setCursor(new Cursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				card.setHovered(false);
				cv.getThisCardJPanel().repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				card.setSelected(true);
				cv.getThisCardJPanel().repaint();
				// cv.getThisCardJPanel().setCursor(new Cursor(Cursor.WAIT_CURSOR));
			}
		};
	}

	// ACTION BUTTON IN MENU
	public MouseAdapter actionButtonInMenu(ButtonImage di) {
		return new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				di.setHover(true);
				// repaint win
				cdb.repaint();
				yld.repaint();
				di.repaint();

				// diNext
				di.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				di.setHover(false);
				di.repaint();
			}

		};
	}

	// CLICK BUTTON SOUND
	public ActionListener actionSound(ButtonImage di) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!mainMenuScreen.isMuteAudio()) {
					//stop audio 
					audioAction.stop();
					
					//set turn off sound button
					di.setImage(ImageButton.MUSICMUTE);
					
					//set turn off for audio
					mainMenuScreen.setMuteAudio(true);
				}else {
					//turn on music of main menu
					mainMenuScreen.setMuteAudio(false);
					
					//set turn on sound button
					di.setImage(ImageButton.MUSIC);
					
					//audio play in background
					audioAction.setAudio(PathAudioGame.BOARDGAME,true,mainMenuScreen.isMuteAudio(),10);
					
				
				}
			}
		};
	}

	public MouseAdapter actionForButtonOfConfirmDialog(ButtonImage di) {
		return actionButtonInMenu(di);
	}
}
