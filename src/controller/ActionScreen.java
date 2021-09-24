package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import constants.PathAudioGame;
import view_menuScreen.IListScreenMenuItems;
import view_menuScreen.IMainScreenView;
import view_menuScreen.IScreenMenuItem;
import view_menuScreen.PlayGameScreen;
import view_support.ButtonImage;
import view_support.DrawTitle;
import view_user.RankByLevel;

public class ActionScreen {
	private IMainScreenView mainMenu;
	private Map<ButtonImage, DrawTitle> listButtons;
	private IListScreenMenuItems listScreenMenuItems;
	private IScreenMenuItem screen;
	private PlayGameScreen pgs;
	private AudioAction audioAction;

	// action hidden rank table
	private JButton jbHide, jbAppear;
	private RankByLevel rbl;

	public ActionScreen(IMainScreenView mainMenu, AudioAction audioAction) {
		super();
		this.mainMenu = mainMenu;
		this.audioAction = audioAction;

		listButtons = mainMenu.getJbMap();
		listScreenMenuItems = mainMenu.listScreenMenuItems();

		for (ButtonImage di : listButtons.keySet()) {
			di.addActionListener(openScreen());
		}

		// get listsCardView from PlayGameScreen
		pgs = (PlayGameScreen) mainMenu.listScreenMenuItems().getScreen("Play Game");
		pgs.getJbGoToMenu().addActionListener(closeScreen());
		jbHide = pgs.getJbHide();
		jbAppear = pgs.getJbAppear();
		jbHide.addActionListener(hiddenRankTable());
		jbAppear.addActionListener(hiddenRankTable());
	}

	public ActionListener hiddenRankTable() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rbl = pgs.getRankByLevel();
				if (e.getSource() == jbHide) {
					pgs.setHidden(true);
					rbl.setVisible(false);
				} else if (e.getSource() == jbAppear) {
					rbl.setVisible(true);
					pgs.setHidden(false);
					pgs.setCheckOnce(true);
				}
			}
		};
	}

	// action open screen
	public ActionListener openScreen() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				audioAction.stop();

				// click button
				audioAction.setAudio(PathAudioGame.SELECT,false,false,10);
				//audioAction.start(false);

				screen = listScreenMenuItems.getScreen(e.getActionCommand());
				screen.open();
			
				screen.getScreen().repaint();
				mainMenu.close();

			}
		};
	}

	// action close screen
	public ActionListener closeScreen() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//set audio click 
				audioAction.setAudio(PathAudioGame.SELECT,false,false,10);
				
				//get sub-screen specified
				screen = listScreenMenuItems.getScreen(e.getActionCommand());
				
				//turn off sub-screen
				screen.close();
				
				//open main menu
				mainMenu.open();
				
				//turn on audio of main menu
				audioAction.setAudio(PathAudioGame.BOARDGAME,true,mainMenu.isMuteAudio(),10);
				
				mainMenu.getScreen().repaint();

			}
		};
	}

	public KeyAdapter actionKey() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					screen.close();
					mainMenu.open();
				}
			}
		};
	}

}
