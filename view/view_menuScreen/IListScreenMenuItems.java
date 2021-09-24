package view_menuScreen;

import java.util.List;

public interface IListScreenMenuItems {
	
	public IScreenMenuItem getScreen(String screenName);
	
	public IScreenMenuItem getScreen(int index);
	
	public boolean addScreen(IScreenMenuItem screenView);
	
	public void removeScreen(IScreenMenuItem screenView);
	
	public void clear();
	
	public List<IScreenMenuItem> getListScreen();
	
}
