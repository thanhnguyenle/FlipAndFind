package view_menuScreen;

import model_user.User;

public interface IObserverView {
	public void update(User user, int nowScore, int nowLevel);
}
