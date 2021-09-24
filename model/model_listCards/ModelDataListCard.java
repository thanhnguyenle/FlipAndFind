package model_listCards;

import java.util.ArrayList;
import java.util.List;

import model_level.LevelData;

public class ModelDataListCard implements IObsevableModel {
	private List<IObserverModel> observersModel;
	private IListCardsAdapter listCards;
	
	public ModelDataListCard() {
		// TODO Auto-generated constructor stub
		observersModel = new ArrayList<>();
	}

	@Override
	public void registerObserver(IObserverModel o) {
		// TODO Auto-generated method stub
		observersModel.add(o);
	}

	@Override
	public void unregisterObserver(IObserverModel o) {
		// TODO Auto-generated method stub
		int i = observersModel.indexOf(o);
		if (i >= 0)
			observersModel.remove(i);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(int i = 0;i<observersModel.size();i++) {
			IObserverModel observer = (IObserverModel) observersModel.get(i);
			observer.update(listCards);
		}
	}
	public void dataChanged() {
		notifyObserver();
	}
	
	public void setData(IListCardsAdapter listCards) {
		this.listCards = listCards;
		dataChanged();
	}

	@Override
	public IListCardsAdapter getListCards() {
		// TODO Auto-generated method stub
		return this.listCards;
	}

}
