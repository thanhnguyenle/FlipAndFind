package model_listCards;

public interface IObsevableModel {

	public IListCardsAdapter getListCards();

	public void registerObserver(IObserverModel o);

	public void unregisterObserver(IObserverModel o);

	public void notifyObserver();
}
