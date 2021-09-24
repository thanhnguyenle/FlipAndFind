package model_user;

public interface IUserObservable {
	public void registerObserver(IUserObserver o);

	public void unregisterObserver(IUserObserver o);

	public void notifyObserver();
}
