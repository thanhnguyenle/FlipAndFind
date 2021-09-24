package model_level;

import model_listCards.IListCardsAdapter;

public interface ILevel {
	public int getLimitTime();
	
	public IListCardsAdapter createListCardsLevel(int level);
}
