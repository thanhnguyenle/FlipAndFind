package view_menuScreen;

import java.util.ArrayList;
import java.util.List;

import model_user.ListUsers;
import model_user.User;

public class ScreenData implements IObservableView {
	// top user
	private List<IObserverView> observers;
	private User user;
	private int nowScore;
	private int nowLevel;

	public ScreenData() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(IObserverView ov) {
		// TODO Auto-generated method stub
		observers.add(ov);
	}

	@Override
	public void removeObserver(IObserverView ov) {
		// TODO Auto-generated method stub
		if (ov != null)
			observers.remove(ov);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for (int i = 0; i < observers.size(); i++) {
			IObserverView observerView = (IObserverView) observers.get(i);
			observerView.update(user, nowScore, nowLevel);
		}
	}

	public User getUser() {
		return user;
	}

	public int getNowScore() {
		return nowScore;
	}

	public int getNowLevel() {
		return nowLevel;
	}

	public void dataChanged() {
		notifyObserver();
	}

	public void setDataScreens(User user, int nowScore, int nowLevel) {
		this.user = user;
		this.nowScore = nowScore;
		this.nowLevel = nowLevel;
		dataChanged();
	}

}
