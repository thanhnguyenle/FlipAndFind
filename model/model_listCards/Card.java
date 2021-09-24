package model_listCards;

public class Card implements ICard {
	private int id;
	private boolean faceUp = false;
	private boolean faceDown = true;
	private boolean hidden;
	private boolean selected = false;
	private boolean hovered = false;

	// Constructor empty
	public Card() {
		this(0);
	}

	public Card(int id) {
		super();
		this.id = id;
	}

	@Override
	public int getIdCard() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public int setIdCard(int id) {
		// TODO Auto-generated method stub
		return this.id = id;
	}

	@Override
	public boolean isFaceUp() {
		// TODO Auto-generated method stub
		return faceUp;
	}

	@Override
	public void setFaceUp() {
		// TODO Auto-generated method stub
		faceDown = false;
		faceUp = true;
	}

	@Override
	public boolean isFaceDown() {
		// TODO Auto-generated method stub
		return faceDown;
	}

	@Override
	public void setFaceDown() {
		// TODO Auto-generated method stub
		faceUp = false;
		faceDown = true;
	}

	@Override
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return hidden;
	}

	@Override
	public void setHidden(boolean value) {
		// TODO Auto-generated method stub
		hidden = value;
	}

	@Override
	public boolean isHovered() {
		// TODO Auto-generated method stub
		return hovered;
	}

	@Override
	public void setHovered(boolean value) {
		// TODO Auto-generated method stub
		hovered = value;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public void setSelected(boolean value) {
		// TODO Auto-generated method stub
		selected = value;
	}

}
