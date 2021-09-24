package model_listCards;

import java.util.List;

public interface IListCards{
	//number of card
	public int getNumCards();
	
	//create a list card
	public void createListCards(int num);
	
	//remove all 
	public void clear();
	
	//add a card
	public boolean addCards(ICardAdapter card);
	
	//get Card with the id
	public ICardAdapter[] getCard(int id);
	
	//get position of a id card
	public int[] getPositonOfIdCard(int id);
	
	//remove cards
	public void removeCards(int id);
	
	//change the id of a card to newId
	public boolean changeId(int id,int newId);
	
	//shuffle list card
	public void shuffle();
	
	//get list cards
	public List<ICardAdapter> getListCards();
	
	//check all hidden
	public boolean checkAllHidden();
	
	//faceUp all
	public void faceUpAll();
	
	//face down all
	public void faceDownAll();
	
	//reset all card
	public void resetAll();
	
	public void setListCards(List<ICardAdapter> listCard) ;
	

}
