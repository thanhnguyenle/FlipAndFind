package view_menuScreen;

import javax.swing.JPanel;

import model_listCards.IObsevableModel;
import model_user.IUserObservable;
import model_user.IUserObserver;
import model_user.ListUsers;
import model_user.User;

public class AboutScreen extends ScreenMenuItemAbstract implements IObserverView,IUserObserver{
	private static IScreenMenuItem uniqueInstance;

	private AboutScreen(IObservableView screenData, IObsevableModel modelData, IUserObservable userData) {
		super(screenData, modelData, userData);
		// TODO Auto-generated constructor stub
		screenData.registerObserver(this);
		userData.registerObserver(this);
	}

	// Singleton pattern
		public static IScreenMenuItem getInstance(IObservableView screenData,IObsevableModel modelData, IUserObservable userData) {
			if (uniqueInstance == null) {
				uniqueInstance = new AboutScreen(screenData, modelData,userData);
			}
			return uniqueInstance;
		}

	@Override
	public JPanel getScreen() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void update(User user, int nowScore, int nowLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String nameScreen() {
		// TODO Auto-generated method stub
		return "About";
	}




	@Override
	public void update(ListUsers listUsers,int level) {
		// TODO Auto-generated method stub
		
	}

	
}
