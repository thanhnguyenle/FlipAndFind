package view_card;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class HoveredCard extends CardEffectAbstract implements ICardEffect {
	public HoveredCard() {
		super();
	}

	@Override
	public Image decorCard(java.awt.Image image, Color color) {
		// TODO Auto-generated method stub
//		int borderColor = color.getRGB(); // Red color
//		BufferedImage buffered = toBufferedImage(image);
//
//		for (int i = 0; i < buffered.getHeight(); i++) {
//			buffered.setRGB(0, i, borderColor);
//			buffered.setRGB(buffered.getWidth() - 1, i, borderColor);
//		}
//		for (int i = 0; i < buffered.getWidth(); i++) {
//			buffered.setRGB(i, 0, borderColor);
//			buffered.setRGB(i, buffered.getHeight() - 1, borderColor);
//		}
//		Image img = new ImageIcon(buffered).getImage();
//		return img;
		return image;
	}

	@Override
	public Image decorCard(Image image) {
		// TODO Auto-generated method stub
		Color color = Color.white;
		return decorCard(image, color);
	}
}
