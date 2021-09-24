package model_listCards;

import java.util.List;

public class ListCardsAdapter implements IListCardsAdapter {
	private IListCards listCards;

	public ListCardsAdapter(IListCards listCards) {
		super();
		this.listCards = listCards;
	}

	@Override
	public int getNumCards() {
		// TODO Auto-generated method stub
		return listCards.getNumCards();
	}

	@Override
	public void createListCards(int num) {
		// TODO Auto-generated method stub
		listCards.createListCards(num);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		listCards.clear();
	}

	@Override
	public boolean addCards(ICardAdapter card) {
		// TODO Auto-generated method stub
		return listCards.addCards(card);
	}

	@Override
	public ICardAdapter[] getCard(int id) {
		// TODO Auto-generated method stub
		return listCards.getCard(id);
	}

	@Override
	public int[] getPositonOfIdCard(int id) {
		// TODO Auto-generated method stub
		return listCards.getPositonOfIdCard(id);
	}

	@Override
	public void removeCards(int id) {
		// TODO Auto-generated method stub
		listCards.removeCards(id);
	}

	@Override
	public boolean changeId(int id, int newId) {
		// TODO Auto-generated method stub
		return listCards.changeId(id, newId);
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		listCards.shuffle();
	}

	@Override
	public List<ICardAdapter> getListCards() {
		// TODO Auto-generated method stub
		return listCards.getListCards();
	}

	@Override
	public boolean checkAllHidden() {
		// TODO Auto-generated method stub
		return listCards.checkAllHidden();
	}

	@Override
	public void faceUpAll() {
		// TODO Auto-generated method stub
		listCards.faceUpAll();
	}

	@Override
	public void faceDownAll() {
		// TODO Auto-generated method stub
		listCards.faceDownAll();
	}

	@Override
	public void resetAll() {
		// TODO Auto-generated method stub
		listCards.resetAll();
	}

	@Override
	public void setListCards(List<ICardAdapter> listCard) {
		// TODO Auto-generated method stub
		listCards.setListCards(listCard);
	}
	
	
}
