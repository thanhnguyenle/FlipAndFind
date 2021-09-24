package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import constants.PathAudioGame;
import model_user.ListUsers;
import model_user.User;
import model_user.UsersData;
import view_menuScreen.IMainScreenView;
import view_menuScreen.ScreenData;
import view_user.UserInform;

public class ActionUserInform {
	private UserInform userInform;
	private ScreenData screenData;
	private UsersData usersData;
	private ListUsers listUsers;
	private IMainScreenView mainMenuScreen;
	private AudioAction audioActionBoardGame;
	private AudioAction audioActionClick;
	private AudioAction audioOfUserInform;
	
	public ActionUserInform(UserInform userInform, IMainScreenView mainMenuScreen, ScreenData screenData,
			UsersData usersData, AudioAction audioActionBoardGame) {
		super();
		this.userInform = userInform;
		this.screenData = screenData;
		this.usersData = usersData;
		this.mainMenuScreen = mainMenuScreen;
		this.audioActionBoardGame = audioActionBoardGame;
		this.listUsers = usersData.getListUsers();
		// open userInform
		mainMenuScreen.getDiUser().addActionListener(openUserInform());

		// close userInform
		userInform.getJbReturn().addActionListener(closeUserInform());

		// audioClick
		AudioHandle ah = new AudioHandle();
		audioActionClick = new AudioAction(ah);
		
		//audio of userInform
		AudioHandle ah2 = new AudioHandle();
		audioOfUserInform  = new AudioAction(ah2);

		// add action button save
		userInform.getJbSave().addActionListener(actionSave());

		// add action button jcbUsers
		userInform.getJcbUsers().addActionListener(actionJcbUser());
	}

	private ActionListener openUserInform() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// open panel userInform
				userInform.setVisible(true);

				// turn off audio of main menu
				audioActionBoardGame.stop();
				
				//turn on audio of userInform
				audioOfUserInform.setAudio(PathAudioGame.USERINFORM,true,false,10);
			}
		};
	}

	private ActionListener closeUserInform() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//turn off audio 
				audioOfUserInform.stop();
				
				// close panel userInform
				userInform.setVisible(false);

				// audio click button
				audioActionClick.setAudio(PathAudioGame.SELECT,false,false,10);

				// open main menu
				mainMenuScreen.open();

				// open audio main menu if had
				audioActionBoardGame.setAudio(PathAudioGame.BOARDGAME,true,mainMenuScreen.isMuteAudio(),10);

				// repaint main menu
				mainMenuScreen.getScreen().repaint();
			}
		};
	}

	// SAVE
	private ActionListener actionSave() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String note = "";
				
				//get value that you want change
				String name = userInform.getJtfName().getText();
				
				// check age
				int age = 0;
				boolean checkAge = true;
				try {
					age = Integer.parseInt(userInform.getJtfAge().getText());
				} catch (Exception e2) {
					// TODO: handle exception
					checkAge = false;
				}
				String newCountry = userInform.getJcbCountries()
						.getItemAt(userInform.getJcbCountries().getSelectedIndex());
				
				String newLanguage = userInform.getJcbLanguages()
						.getItemAt(userInform.getJcbLanguages().getSelectedIndex());
				
				//check condition 
				JOptionPane jop = new JOptionPane();
				if (name.isEmpty()) {
					jop.showConfirmDialog(jop, "Name is empty", "ERROR", JOptionPane.CANCEL_OPTION);
				} else if (!checkAge) {
					jop.showConfirmDialog(jop, "Age is error", "ERROR", JOptionPane.CANCEL_OPTION);
				} else {
					// save name and save to jcomboBox
					User userCurrent = userInform.getUser();

					// note
					note = "Change user \""+userCurrent.getUserName()+"\":\n"+(name.equals(userCurrent.getUserName()) ? ""
							: "Name:     "+userCurrent.getUserName() + " -> " + name + "\n")
							+ (age == userCurrent.getAge() ? "" :"Age:      "+ userCurrent.getAge() + " -> " + age + "\n")
							+ (newCountry.equals(userCurrent.getCountries()) ? ""
									: "Country:  "+userCurrent.getCountries() + " -> " + newCountry + "\n")
							+ (newLanguage.equals(userCurrent.getLanguages()) ? ""
									: "Language: "+userCurrent.getLanguages() + " -> " + newLanguage + "\n");

					// start change all
					userCurrent.setUserName(name);
					userCurrent.setAge(age);

					// set country
					userCurrent.setCountries(newCountry);

					// set language
					userCurrent.setLanguages(newLanguage);

					// set on observer
					usersData.setUsersData(listUsers, userCurrent.getLevel());
					screenData.setDataScreens(userCurrent, userCurrent.getScore(), userCurrent.getLevel());

					//confirm dialog for note after save
					jop.showConfirmDialog(jop, note, "SAVE SUCCESSFULL", JOptionPane.CANCEL_OPTION);
				}
			}
		};
	}

	// JCOMBOBOX USER
	private ActionListener actionJcbUser() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox comboBox = (JComboBox) e.getSource();
				Object selected = comboBox.getSelectedItem();
				if (selected != null)
					for (User user : listUsers.getUsers())
						if (selected.toString().equals(user.getUserName())) {
							screenData.setDataScreens(user, user.getScore(), user.getLevel());
							break;
						}
			}
		};
	}
}
