package view_menuScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.ImageButton;
import model_listCards.IObsevableModel;
import model_listCards.ModelDataListCard;
import model_user.IUserObservable;
import model_user.UsersData;
import view_support.ButtonImage;
import view_support.DrawTitle;

public class MainMenuScreen extends JPanel implements IMainScreenView {
	private static IMainScreenView uniqueInstance;
	private IScreenMenuItem playGame, highScore, about, setting;
	private IListScreenMenuItems listScreenView;
	private Map<ButtonImage, DrawTitle> jbMap;
	private IObservableView screenData;
	private ButtonImage dib;
	private DrawTitle tib;
	private IObsevableModel modelData;
	private IUserObservable userData;
	private JPanel buttonMenus;
	private ButtonImage diSound, diUser;
	private boolean isMuteAudio = false;

	private MainMenuScreen(IObservableView screenData, IObsevableModel modelData, IUserObservable userData) {
		// TODO Auto-generated constructor stub
		this.screenData = screenData;
		this.modelData = modelData;
		this.userData = userData;

		listScreenView = new ListScreenMenuItems();

		// set layout = null
		setLayout(null);
		setSize(500, 500);

		// init button
		jbMap = new LinkedHashMap<>();

		// add screen and create list screen here
		createListScreen();

		buttonMenus = new JPanel(null);
		add(buttonMenus);
//		// prepare image button
		for (Entry<ButtonImage, DrawTitle> e : jbMap.entrySet()) {
			// add title
			tib = e.getValue();
			tib.setFont(new Font("NewellsHand", Font.BOLD, 20));
			tib.setOpaque(false);
			tib.setForeground(Color.WHITE);
			buttonMenus.add(tib);

			// add button
			dib = e.getKey();
			dib.setBorderPainted(false);
			dib.setContentAreaFilled(false);
			dib.setFocusPainted(false);
			dib.setOpaque(false);
			buttonMenus.add(dib);
		}

		// sound button
		diSound = new ButtonImage(ImageButton.MUSIC);
		diSound.setActionCommand("buttonSound");
		add(diSound);

		// user button
		diUser = new ButtonImage(ImageButton.USER);
		diUser.setActionCommand("buttonUser");
		add(diUser);

	}

	// Singleton pattern
	public static IMainScreenView getInstance(IObservableView screenData, IObsevableModel modelData,
			IUserObservable userData) {
		if (uniqueInstance == null) {
			uniqueInstance = new MainMenuScreen(screenData, modelData, userData);
		}
		return uniqueInstance;
	}

	// create class screen
	public void createListScreen() {
		listScreenView.clear();
		jbMap.clear();

		playGame = PlayGameScreen.getInstance(screenData, modelData, userData);
		addMenuItem("Play Game", ImageButton.IMAGE[1], playGame);
		playGame.close();

		highScore = HighScoreScreen.getInstance(screenData, modelData, userData);
		addMenuItem("High Score", ImageButton.IMAGE[2], highScore);
		highScore.close();

		setting = SettingScreen.getInstance(screenData, modelData, userData);
		addMenuItem("Setting", ImageButton.IMAGE[3], setting);
		setting.close();

		about = AboutScreen.getInstance(screenData, modelData, userData);
		addMenuItem("About", ImageButton.IMAGE[4], about);
		about.close();
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		getScreen().setVisible(true);
	}

	// turn off system
	@Override
	public void close() {
		// TODO Auto-generated method stub
		setVisible(false);
	}

	@Override
	public JPanel getScreen() {
		// TODO Auto-generated method stub
		return this;
	}

	// add 1 class to list as a menu item
	public boolean addMenuItem(String nameScreen, Image imageButton, IScreenMenuItem screenView) {
		if (listScreenView.addScreen(screenView)) {
			tib = new DrawTitle(nameScreen, true);
			dib = new ButtonImage(imageButton, 0, 0);
			dib.setActionCommand(nameScreen);
			jbMap.put(dib, tib);
			return true;
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		double wp = getWidth();
		double hp = getHeight();

		// set positon of buttonMenus
		// int widthOfButton = 200;
		buttonMenus.setBounds((int) (wp * (1 / 5.0)), (int) (hp * (1 / 5.0)), (int) (wp * (3 / 5.0)),
				(int) (hp * (3 / 5.0)));

		// define
		int i = 0;
		int wbm = buttonMenus.getWidth();
		int hbm = buttonMenus.getHeight();
		int khoangCachY = 20;
		int sizeButton = 200;
		int effectRate = 30;

		// code
		for (Entry<ButtonImage, DrawTitle> e : jbMap.entrySet()) {
			double wb, hb, x, y;

			if (e.getKey().isHover()) {
				wb = sizeButton + effectRate / 2;
				hb = (hbm / Double.valueOf(jbMap.size())) - khoangCachY + effectRate / 2;
				x = (wbm - sizeButton) / 2 - effectRate / 4;
				y = ((Double.valueOf(i) / jbMap.size()) * hbm) - effectRate / 4 + 10;
				e.getValue().setForeground(Color.BLUE);
			} else {
				wb = sizeButton;
				hb = (hbm / Double.valueOf(jbMap.size())) - khoangCachY;
				x = (wbm - sizeButton) / 2;
				y = ((Double.valueOf(i) / jbMap.size()) * hbm) + 10;
				e.getValue().setForeground(Color.WHITE);
			}
			e.getKey().setBounds((int) x, (int) y, (int) wb, (int) hb);
			e.getValue().setBounds((int) x, (int) y, (int) wb, (int) hb);

			i++;
		}

		int hoverEffect = 8;
		// set bound of sound
		int sizeOfSound = 50;
		if (!diSound.isHover())
			diSound.setBounds(getWidth() - sizeOfSound-5, 5, sizeOfSound, sizeOfSound);
		else
			diSound.setBounds(getWidth() - sizeOfSound - hoverEffect / 2 -5, 5 - hoverEffect / 2,
					sizeOfSound + hoverEffect, sizeOfSound + hoverEffect);
		// set bound of user button
		int sizeOfUser = 50;
		if (!diUser.isHover())
			diUser.setBounds(getWidth() - sizeOfUser -5, 5 + diSound.getHeight() + 10, sizeOfUser, sizeOfUser);
		else
			diUser.setBounds(getWidth() - sizeOfUser - hoverEffect / 2 -5, 5 + diSound.getHeight() + 10 - hoverEffect / 2,
					sizeOfUser + hoverEffect, sizeOfUser + hoverEffect);

		repaint();
	}

	public ButtonImage getDiSound() {
		return diSound;
	}

	@Override
	public Map<ButtonImage, DrawTitle> getJbMap() {
		// TODO Auto-generated method stub
		return jbMap;
	}

	@Override
	public IListScreenMenuItems listScreenMenuItems() {
		// TODO Auto-generated method stub
		return listScreenView;
	}

	@Override
	public ButtonImage getDiUser() {
		// TODO Auto-generated method stub
		return diUser;
	}
	
	public boolean isMuteAudio() {
		return isMuteAudio;
	}

	public void setMuteAudio(boolean isMuteAudio) {
		this.isMuteAudio = isMuteAudio;
	}
}
