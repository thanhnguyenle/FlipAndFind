package view_card;

import java.util.List;

import javax.swing.JPanel;

import model_listCards.IListCardsAdapter;

public interface IListCardsView {
	// number of card
	public int getNumCards();

	// create a list card
	public void createListCardsView();

	// get Card with the id
	public ICardView[] getCard(int id);

	// get position of a id card
	public int[] getPositonOfIdCard(int id);

	// remove cards
	public void removeAllCards();

	// change the id of a card to newId
	//public boolean changeId(int id, int newId);

	public IListCardsAdapter getIListCards() ;

	public void setListCards(IListCardsAdapter listCards) ;
	
	// shuffle list card
	public void shuffle();
	
	//set face down all
	public void setFaceDownAll();
	
	//repaint all card
	public void repaintListCards();
	
	// get list cards
	public List<ICardView> getListCards();
	
	//SET size mXn
	public void setNumCell(int row,int column);
	
	//set num cell default
	public void setNumCell();
	
	public JPanel getThisPanel();
}
