package view_support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

import view_card.HoveredCard;
import view_card.ICardEffect;
import view_card.SelectedCard;

public class ButtonImage extends JButton {
	private java.awt.Image image;
	private int xCoordinate;
	private int yCoordinate;
	private boolean hover;
	private boolean selected;
	private ICardEffect effectHovered;
	private ICardEffect effectSelected;
	private Color colorHover;
	private Color colorSelect;

	public ButtonImage(Image image) {
		this(image, 0, 0);
	}

	public ButtonImage(Image image, int xCoordinate, int yCoordinate) {
		this.image = image;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		effectHovered = new HoveredCard();
		effectSelected = new SelectedCard();
		this.colorHover = Color.black;
		this.colorSelect = Color.black;

		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}

	public ButtonImage(Image image, Color colorHover, Color colorSelect) {
		this(image);
		this.colorHover = colorHover;
		this.colorSelect = colorSelect;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (image != null) {
			if (hover)
				image = effectHovered.decorCard(image, colorHover);
			if (selected)
				image = effectSelected.decorCard(image, colorSelect);
			g.drawImage(image, xCoordinate, yCoordinate, getWidth(), getHeight(), this);
		}
	}

	public java.awt.Image getImage() {
		return image;
	}

	public void setImage(java.awt.Image image) {
		this.image = image;
		repaint();
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
		repaint();
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
		repaint();
	}

	public boolean isHover() {
		return hover;
	}

	public void setHover(boolean isHover) {
		this.hover = isHover;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean isSelected) {
		this.selected = isSelected;
	}

	public Color getColorHover() {
		return colorHover;
	}

	public void setColorHover(Color colorHover) {
		this.colorHover = colorHover;
	}

	public Color getColorSelect() {
		return colorSelect;
	}

	public void setColorSelect(Color colorSelect) {
		this.colorSelect = colorSelect;
	}

}
