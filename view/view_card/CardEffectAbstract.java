package view_card;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class  CardEffectAbstract{
	protected java.awt.Image image;
	protected Color color;
	protected int sizeBorder;
	
	protected CardEffectAbstract(Image image, Color color, int sizeBorder) {
		super();
		this.image = image;
		this.color = color;
		this.sizeBorder = sizeBorder;
	}
	
	//Constructors empty
	protected CardEffectAbstract() {
		// TODO Auto-generated constructor stub
	}
	
	// convert image to buffered image
	public BufferedImage toBufferedImage(Image image) {
		// TODO Auto-generated method stub
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
}
