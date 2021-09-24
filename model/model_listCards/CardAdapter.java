package model_listCards;

public class CardAdapter implements ICardAdapter{
	private ICard card;
	
	public CardAdapter(ICard card) {
		super();
		this.card = card;
	}

	@Override
	public int getIdCard() {
		// TODO Auto-generated method stub
		return card.getIdCard();
	}

	@Override
	public int setIdCard(int id) {
		// TODO Auto-generated method stub
		return card.setIdCard(id);
	}

	@Override
	public boolean isFaceUp() {
		// TODO Auto-generated method stub
		return card.isFaceUp();
	}

	@Override
	public void setFaceUp() {
		// TODO Auto-generated method stub
		card.setFaceUp();
	}

	@Override
	public boolean isFaceDown() {
		// TODO Auto-generated method stub
		return card.isFaceDown();
	}

	@Override
	public void setFaceDown() {
		// TODO Auto-generated method stub
		card.setFaceDown();
	}

	@Override
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return card.isHidden();
	}

	@Override
	public void setHidden(boolean value) {
		// TODO Auto-generated method stub
		card.setHidden(value);
	}

	@Override
	public boolean isHovered() {
		// TODO Auto-generated method stub
		return card.isHovered();
	}

	@Override
	public void setHovered(boolean value) {
		// TODO Auto-generated method stub
		card.setHovered(value);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return card.isSelected();
	}

	@Override
	public void setSelected(boolean value) {
		// TODO Auto-generated method stub
		card.setSelected(value);
	}

}
