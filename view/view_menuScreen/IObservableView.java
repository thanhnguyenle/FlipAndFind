package view_menuScreen;

public interface IObservableView {
	public void registerObserver(IObserverView ov);
	
	public void removeObserver(IObserverView ov);
	
	public void notifyObserver();
}
