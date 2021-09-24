package view_card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import constants.ImageCard;
import model_listCards.ICardAdapter;

public class CardView extends JPanel implements ICardView {
	private ICardAdapter card;
	private Image frontCard;
	private Image backCard;
	private Image frontNoColor;
	private Image backNoColor;
	private Image cardTemp;
	private LockCard lockButton;
	private ICardEffect hovered, selected;

	// Constructor empty
	public CardView(ICardAdapter card) {
		// TODO Auto-generated constructor stub
		this(card, null);
	}

	public CardView(ICardAdapter card, Image frontCard) {
		// TODO Auto-generated constructor stub
		this.card = card;
		this.frontCard = frontCard;
		this.backCard = ImageCard.BACKCARD_1;
		lockButton = new LockCard(frontCard);
		hovered = new HoveredCard();
		selected = new SelectedCard();
		setIgnoreRepaint(true);
		this.frontNoColor = lockButton.decorImage();
	}

	@Override
	public synchronized void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if (isEnabled()) {

			// draw front card
			if (card.isFaceUp()) {
				// g.drawImage(frontCard, 0, 0, getWidth(), getHeight(), this);
				cardTemp = frontCard;
			}

			// draw back card
			else if (card.isFaceDown()) {
				// g.drawImage(backCard, 0, 0, getWidth(), getHeight(), this);
				cardTemp = backCard;
			}

			// draw border if isHovered
			if (card.isHovered())
				decorCard(hovered, Color.CYAN, cardTemp);

			// draw boder is if selected
			if (card.isSelected() && !card.isFaceDown())
				decorCard(selected, Color.RED, cardTemp);

			// DRAW
			g.drawImage(cardTemp, 0, 0, getWidth(), getHeight(), this);
		}
		// draw front card no color
		if (card.isHidden()) {
			g.drawImage(frontNoColor, 0, 0, getWidth(), getHeight(), this);
			setEnabled(false);
		}

	}

	public void decorCard(ICardEffect iie, Color color, Image cardTemp) {
		this.cardTemp = iie.decorCard(cardTemp, color);
		// backCard = iie.decorCard(backCard,color,sideBorder);
		// this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
	}

	@Override
	public ICardAdapter getCard() {
		// TODO Auto-generated method stub
		return card;
	}

	@Override
	public void setCard(ICardAdapter card) {
		// TODO Auto-generated method stub
		this.card = card;
	}

	@Override
	public Image getFrontCard() {
		// TODO Auto-generated method stub
		return frontCard;
	}

	@Override
	public void setFrontCard(Image image) {
		// TODO Auto-generated method stub
		this.frontCard = image;
	}

	@Override
	public Image getBackCard() {
		// TODO Auto-generated method stub
		return backCard;
	}

	@Override
	public void setBackCard(Image image) {
		// TODO Auto-generated method stub
		this.backCard = image;
	}

	public Image getFrontNoColor() {
		return frontNoColor;
	}

	public void setFrontNoColor(Image frontNoColor) {
		this.frontNoColor = frontNoColor;
	}

	public Image getBackNoColor() {
		return backNoColor;
	}

	public void setBackNoColor(Image backNoColor) {
		this.backNoColor = backNoColor;
	}

	@Override
	public JPanel getThisCardJPanel() {
		// TODO Auto-generated method stub
		return this;
	}

}
