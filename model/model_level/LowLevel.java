package model_level;

import model_listCards.IListCardsAdapter;
import model_listCards.ListCards;
import model_listCards.ListCardsAdapter;
import model_listCards.ModelDataListCard;

public class LowLevel implements ILevel {

	private IListCardsAdapter listCards;
	private int level;
	private int limitTime;

	public LowLevel() {
		super();

		// default
		level = 1;
		listCards = new ListCardsAdapter(new ListCards(1));
	}

	@Override
	public IListCardsAdapter createListCardsLevel(int level) {
		// TODO Auto-generated method stub
		switch (level) {
		case 1: {
			listCards = new ListCardsAdapter(new ListCards(1));
			limitTime = 5;
			break;
		}
		case 2: {
			listCards = new ListCardsAdapter(new ListCards(2));
			limitTime = 10;
			break;
		}
		case 3:
		case 4:
		case 5: {
			listCards = new ListCardsAdapter(new ListCards(3));
			limitTime = 16;
			break;
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
