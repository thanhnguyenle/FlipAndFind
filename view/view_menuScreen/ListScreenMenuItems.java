package view_menuScreen;

import java.util.ArrayList;
import java.util.List;

public class ListScreenMenuItems implements IListScreenMenuItems{
	private List<IScreenMenuItem> listScreen;
	
	public ListScreenMenuItems() {
		// TODO Auto-generated constructor stub
		listScreen = new ArrayList<>();
	}
	
	@Override
	public IScreenMenuItem getScreen(String screenName) {
		// TODO Auto-generated method stub
		for(IScreenMenuItem mi:listScreen) {
			if(mi.nameScreen().equals(screenName)) {
				return mi;
			}
		}
		return null;
	}

	@Override
	public IScreenMenuItem getScreen(int index) {
		// TODO Auto-generated method stub
		return listScreen.get(index);
	}

	@Override
	public boolean addScreen(IScreenMenuItem screenView) {
		// TODO Auto-generated method stub
		return listScreen.add(screenView);
	}

	@Override
	public void removeScreen(IScreenMenuItem screenView) {
		// TODO Auto-generated method stub
		listScreen.remove(screenView);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		listScreen.clear();
	}

	@Override
	public List<IScreenMenuItem> getListScreen() {
		// TODO Auto-generated method stub
		return listScreen;
	}


	
}
