package model_level;

import model_listCards.IListCardsAdapter;
import model_listCards.ListCards;
import model_listCards.ListCardsAdapter;

public class ExpertLevel implements ILevel {
	private IListCardsAdapter listCards;
	private int level;
	private int limitTime;

	public ExpertLevel() {
		// TODO Auto-generated constructor stub

		// default
		level = 20;
		limitTime = 15;
		listCards = new ListCardsAdapter(new ListCards(6));
	}

	@Override
	public IListCardsAdapter createListCardsLevel(int level) {
		// TODO Auto-generated method stub
		switch (level) {
		case 21: {
			listCards = new ListCardsAdapter(new ListCards(6));
			limitTime = 25;
			break;
		}
		case 22: {
			listCards = new ListCardsAdapter(new ListCards(8));
			limitTime = 32;
			break;
		}
		case 23:
		case 24:
		case 25:
		case 26:
		case 27: {
			listCards = new ListCardsAdapter(new ListCards(9));
			limitTime = 32;
			break;
		}
		case 28:
		case 29:
		case 30:
		case 31:
		case 32: {
			listCards = new ListCardsAdapter(new ListCards(10));
			limitTime = 32;
			break;
		}
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
		case 40: {
			listCards = new ListCardsAdapter(new ListCards(12));
			limitTime = 40;
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
