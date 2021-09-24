package model_level;

import model_listCards.IListCardsAdapter;
import model_listCards.ListCards;
import model_listCards.ListCardsAdapter;

public class HighLevel implements ILevel{
	private IListCardsAdapter listCards;
	private int level;
	private int limitTime;
	
	public HighLevel() {
		// TODO Auto-generated constructor stub
		
		//default
		listCards = new ListCardsAdapter(new ListCards(6));
		level = 6;
		limitTime = 5;
	}
	
	@Override
	public IListCardsAdapter createListCardsLevel(int level) {
		// TODO Auto-generated method stub
		switch (level) {
		case 6: {
			listCards = new ListCardsAdapter(new ListCards(3));
			limitTime=14;
			break;
		}
		case 7:
		{
			listCards = new ListCardsAdapter(new ListCards(4));
			limitTime=20;
			break;
		}
		case 8: 
		case 9:
		case 10:
		{
			listCards = new ListCardsAdapter(new ListCards(5));
			limitTime=20;
		}
		}
		return listCards;
	}

	@Override
	public int getLimitTime() {
		// TODO Auto-generated method stub
		return this.limitTime;
	}

}
