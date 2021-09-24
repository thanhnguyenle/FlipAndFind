package model_listCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCards implements IListCards {
	private int numCards;
	private List<ICardAdapter> listCard;

	// Constructor empty
	public ListCards() {
		// TODO Auto-generated constructor stub
		this(1);
	}

	// Constructors
	public ListCards(int numCards) {
		super();
		this.numCards = numCards;
		listCard = new ArrayList<>();

		// create list card
		createListCards(numCards);
		shuffle();
	}

	@Override
	public int getNumCards() {
		// TODO Auto-generated method stub
		return numCards;
	}

	@Override
	public void createListCards(int num) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num; i++) {
			// create card
			Card card1 = new Card(i);
			Card card2 = new Card(i);

			// convert card to card adapter
			listCard.add(new CardAdapter(card1));
			listCard.add(new CardAdapter(card2));
		}
		numCards = num;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		listCard.clear();
		numCards = 0;
	}

	@Override
	public boolean addCards(ICardAdapter card) {
		// TODO Auto-generated method stub
		if (!checkListContainId(card.getIdCard())) {
			listCard.add(card);
			listCard.add(card);
			numCards++;
			return true;
		} else {
			return false;
		}
	}

	// check if list contain id return true , else return false
	public boolean checkListContainId(int id) {
		for (int i = 0; i < listCard.size(); i++) {
			if (listCard.get(i).getIdCard() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ICardAdapter[] getCard(int id) {
		// TODO Auto-generated method stub
		ICardAdapter[] cards = new ICardAdapter[2];
		int[] positon = getPositonOfIdCard(id);
		cards[0] = listCard.get(positon[0]);
		cards[1] = listCard.get(positon[1]);
		return cards;
	}

	@Override
	public int[] getPositonOfIdCard(int id) {
		// TODO Auto-generated method stub
		int[] postion = new int[2];
		int k = 0;
		for (int i = 0; i < listCard.size(); i++) {
			if (listCard.get(i).getIdCard() == id) {
				postion[k] = i;
				k++;
			}
		}
		return postion;
	}

	@Override
	public void removeCards(int id) {
		// TODO Auto-generated method stub
		int[] positon = getPositonOfIdCard(id);
		listCard.remove(positon[0]);
		listCard.remove(positon[1]);
		numCards--;
	}

	@Override
	public boolean changeId(int id, int newId) {
		// TODO Auto-generated method stub
		if (!checkListContainId(id))
			return false;
		else if (checkListContainId(newId))
			return false;
		else {
			ICardAdapter[] iCard = getCard(id);
			iCard[0].setIdCard(newId);
			iCard[1].setIdCard(newId);
			return true;
		}
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle(listCard);
	}

	@Override
	public List<ICardAdapter> getListCards() {
		// TODO Auto-generated method stub
		return listCard;
	}

	@Override
	public boolean checkAllHidden() {
		// TODO Auto-generated method stub
		if(listCard.size()==0) return false;
		for(int i = 0;i<listCard.size();i++) {
			if(!listCard.get(i).isHidden()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void faceUpAll() {
		// TODO Auto-generated method stub
		for (ICardAdapter card : listCard) {
			if (!card.isHidden())
				card.isFaceUp();
		}
	}

	@Override
	public void faceDownAll() {
		// TODO Auto-generated method stub
		for (ICardAdapter card : listCard) {
			if (!card.isHidden())
				card.isFaceDown();
		}
	}

	@Override
	public void resetAll() {
		// TODO Auto-generated method stub
		for (ICardAdapter card : listCard) {
			card.setFaceDown();
			card.setHidden(false);
		}
	}

	public void setListCards(List<ICardAdapter> listCard) {
		this.listCard = listCard;
	}

}
