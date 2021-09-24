package view_card;

import java.awt.Image;

import javax.swing.JPanel;

import model_listCards.ICardAdapter;

public interface ICardView {
	//get card
	public ICardAdapter getCard();
	
	//set card
	public void setCard(ICardAdapter card);
	
	// get front card
	public Image getFrontCard();

	// set front card
	public void setFrontCard(Image image);

	// get back card
	public Image getBackCard();

	// set back card
	public void setBackCard(Image image);
	
	//no color
	public Image getFrontNoColor() ;

	public void setFrontNoColor(Image frontNoColor) ;

	public Image getBackNoColor();

	public void setBackNoColor(Image backNoColor) ;

	//get this card button
	public JPanel getThisCardJPanel();
	
}
