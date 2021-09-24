package view_menuScreen;

import java.util.Map;

import javax.swing.JPanel;

import view_support.ButtonImage;
import view_support.DrawTitle;

public interface IMainScreenView {
	//list screen mennu items
	 IListScreenMenuItems  listScreenMenuItems();
	
	//get button 
	 ButtonImage getDiSound();
	 ButtonImage getDiUser();
	
	//open the screen
	 void open();
	
	//return main menu
	 void close();
	
	//list button items
	 Map<ButtonImage, DrawTitle> getJbMap();
	
	//get a screen 
	 JPanel getScreen();
	 
	 //audio 
	 boolean isMuteAudio();
	 void setMuteAudio(boolean isMuteAudio);
	
	
}
