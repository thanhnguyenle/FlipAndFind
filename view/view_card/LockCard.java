package view_card;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class LockCard extends CardEffectAbstract  {
	private java.awt.Image image;

	public LockCard(java.awt.Image image) {
		super();
		this.image = image;
	}

	// draw image no color = lock 
	public Image decorImage() {
		// TODO Auto-generated method stub
		BufferedImage buffered = toBufferedImage(image);
		if (image == null)
			return null;
		for (int i = 0; i < buffered.getHeight(); i++) {
			for (int j = 0; j < buffered.getWidth(); j++) {
				Color imageColor = new Color(buffered.getRGB(j, i));
				int rgb = (int) (imageColor.getRed() * 0.299) + (int) (imageColor.getGreen() * 0.587)
						+ (int) (imageColor.getBlue() * 0.114);
				Color newColor = new Color(rgb, rgb, rgb);
				buffered.setRGB(j, i, newColor.getRGB());
			}
		}
		image = new ImageIcon(buffered).getImage();
		return image;
	}
}
