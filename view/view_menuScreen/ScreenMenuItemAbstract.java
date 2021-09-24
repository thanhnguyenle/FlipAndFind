package view_menuScreen;

import javax.swing.JPanel;

import model_listCards.IObsevableModel;
import model_user.IUserObservable;

public abstract class ScreenMenuItemAbstract extends JPanel implements IScreenMenuItem {
	protected IObservableView screenData;
	protected IObsevableModel modelData;
	protected IUserObservable userData;
	
	protected ScreenMenuItemAbstract(IObservableView screenData, IObsevableModel modelData,IUserObservable userData) {
		super();
		this.screenData = screenData;
		this.modelData = modelData;
		this.userData = userData;
	}
	@Override
	public void open() {
		// TODO Auto-generated method stub
		getScreen().setVisible(true);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		getScreen().setVisible(false);
	}
}
