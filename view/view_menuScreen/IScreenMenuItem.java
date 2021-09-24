package view_menuScreen;

import javax.swing.JPanel;

public interface IScreenMenuItem {
	//name screen
	public String nameScreen();
	
	//open the screen
	public void open();
	
	//return main menu
	public void close();
	
	//get a screen 
	public JPanel getScreen();
	
}

