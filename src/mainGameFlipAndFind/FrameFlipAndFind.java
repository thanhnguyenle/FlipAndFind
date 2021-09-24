package mainGameFlipAndFind;

import java.awt.Graphics;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import constants.PathAudioGame;
import controller.ActionDialogAbstract;
import controller.ActionFlipCard;
import controller.ActionHoverAndSelect;
import controller.ActionLevelUp;
import controller.ActionLimitTime;
import controller.ActionScreen;
import controller.ActionUserInform;
import controller.ActionYouLose;
import controller.AudioAction;
import controller.AudioHandle;
import model_InputManagement.ReadInformUserFromFileText;
import model_InputManagement.WriteInformUseToFileText;
import model_level.LevelData;
import model_listCards.IListCardsAdapter;
import model_listCards.ModelDataListCard;
import model_user.ListUsers;
import model_user.User;
import model_user.UsersData;
import view_menuScreen.IListScreenMenuItems;
import view_menuScreen.IMainScreenView;
import view_menuScreen.IScreenMenuItem;
import view_menuScreen.MainMenuScreen;
import view_menuScreen.PlayGameScreen;
import view_menuScreen.ScreenData;
import view_user.UserInform;

public class FrameFlipAndFind extends JFrame {
	// model
	private ModelDataListCard modelData;
	private ScreenData screenData;
	private UsersData usersData;
	private LevelData levelData;

	// view
	private IMainScreenView mainMenu;
	private List<IScreenMenuItem> listScreenMenuItem;
	private PlayGameScreen pgs;
	private UserInform userInform;

	// controller
	//move screen
	private ActionScreen actionButtonMenu;
	private ActionFlipCard actionCard;
	private ActionHoverAndSelect actionHoverAndSelect;
	//dialog win and lose
	private ActionDialogAbstract actionLevelUp;
	private ActionDialogAbstract actionYouLose;
	//limit time 
	private ActionLimitTime actionLimitTime;
	//audio
	private AudioAction audioAction;
	private AudioHandle audioHandle;
	//user inform
	private ActionUserInform actionUserInform;
	
	public FrameFlipAndFind() {
		// TODO Auto-generated constructor stub
		setLayout(null);

		// INITIAL
		modelData = new ModelDataListCard();
		screenData = new ScreenData();
		usersData = new UsersData();

		mainMenu = MainMenuScreen.getInstance(screenData, modelData, usersData);
		pgs = (PlayGameScreen) mainMenu.listScreenMenuItems().getScreen("Play Game");

		// GET LIST USER THAT SAVED
		// set list high score in playgame and highscore
		// get to default or database (file txt)
		ReadInformUserFromFileText r = new ReadInformUserFromFileText();
		WriteInformUseToFileText w = new WriteInformUseToFileText(usersData);
		List<User> users = r.readInformUser();
		ListUsers lu = new ListUsers(users);
		// set USER
		String lev = "thanh8";
		User userNow = lu.getUser(lev);
	
		// set level default
		levelData = new LevelData();
		levelData.setLevel(userNow.getLevel());
		IListCardsAdapter listCards = levelData.getListCardsLevel();
		modelData.setData(listCards);

		//user inform
		userInform = new UserInform(userNow, usersData, screenData);
	
		//update
		usersData.setUsersData(lu, userNow.getLevel());
		screenData.setDataScreens(userNow, 0, userNow.getLevel());
		
		// ACTION
		// audio action
		audioHandle = new AudioHandle();
		audioAction = new AudioAction(audioHandle);
		
		// action time limit
		actionLimitTime = new ActionLimitTime(pgs.getLtp(), levelData, mainMenu);

		// action for button in menu and card
		actionHoverAndSelect = new ActionHoverAndSelect(modelData, mainMenu,audioAction);

		// action button in menu
		actionButtonMenu = new ActionScreen(mainMenu,audioAction);

		// action for card
		actionCard = new ActionFlipCard(modelData, levelData, mainMenu, screenData, usersData, actionLimitTime);

		// action level up
		actionLevelUp = new ActionLevelUp(modelData, levelData, mainMenu, screenData, usersData, pgs.getCdb(),
				actionCard, actionLimitTime);

		// action you lose
		actionYouLose = new ActionYouLose(modelData, levelData, mainMenu, screenData, usersData, pgs.getYld(),
				actionCard, actionLimitTime);

		//action user inform
		actionUserInform = new ActionUserInform(userInform, mainMenu, screenData, usersData,audioAction);
		
		// HANDLE JFRAME
		// add jpanel to jframe
		add(mainMenu.getScreen());
		IListScreenMenuItems listScreenMenuItems = mainMenu.listScreenMenuItems();
		listScreenMenuItem = listScreenMenuItems.getListScreen();
		for (IScreenMenuItem screen : listScreenMenuItem) {
			add(screen.getScreen());
		}

		// set for jframe
		setLocation(300, 100);
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//play audio
		audioAction.setAudio(PathAudioGame.BOARDGAME,true,mainMenu.isMuteAudio(),10);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		mainMenu.getScreen().setBounds(0, 0, getWidth() - 20, getHeight() - 40);
		for (IScreenMenuItem screen : listScreenMenuItem) {
			screen.getScreen().setBounds(0, 5, getWidth() - 20, getHeight() - 40);
		}
	}

}
