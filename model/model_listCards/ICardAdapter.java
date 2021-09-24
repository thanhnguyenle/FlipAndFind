package model_listCards;

public interface ICardAdapter {
	// get id card
	public int getIdCard();

	// set id card
	public int setIdCard(int id);

	// is face up
	public boolean isFaceUp();

	// set face up
	public void setFaceUp();

	// is face down
	public boolean isFaceDown();

	// set face down
	public void setFaceDown();

	// is hidden
	public boolean isHidden();

	// set hidden
	public void setHidden(boolean value);

	// is hover
	public boolean isHovered();

	// set hover
	public void setHovered(boolean value);

	// is selected
	public boolean isSelected();

	// set selected
	public void setSelected(boolean value);
}
