package model_level;

import model_listCards.IListCardsAdapter;
import model_listCards.ListCards;
import model_listCards.ListCardsAdapter;

public class AdvanceLevel implements ILevel{
	private IListCardsAdapter listCards;
	private int level;
	private int limitTime;
	
	public AdvanceLevel() {
		// TODO Auto-generated constructor stub
		
		//default
		listCards = new ListCardsAdapter(new ListCards(6));
		level = 11;
	}

	@Override
	public IListCardsAdapter createListCardsLevel(int level) {
		// TODO Auto-generated method stub
		switch (level) {
		case 11: {
			listCards = new ListCardsAdapter(new ListCards(6));
			limitTime=20;
			break;
		}
		case 12:
		{
			listCards = new ListCardsAdapter(new ListCards(8));
			limitTime=20;
			break;
		}
		case 13: 
		case 14:
		case 15:
		case 16:
		case 17: 
		case 18:
		case 19:
		case 20:
		{
			listCards = new ListCardsAdapter(new ListCards(9));
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
