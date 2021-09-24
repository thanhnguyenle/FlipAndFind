package view_support;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawTitle extends JPanel{
	private String title;
	private int xCoordinate;
	private int yCoordinate;
	private boolean centered;
	private int interval = 10;

	public DrawTitle(String title) {
		this(title, false);
	}
	
	public DrawTitle(String title, boolean centered) {
		super();
		this.title = title;
		this.centered = centered;
		
		setOpaque(false);
	}

	// tạo title
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (centered) {
			FontMetrics fm = g.getFontMetrics();
			int widthTitle = fm.stringWidth(title);
			int ascent = fm.getAscent();
			xCoordinate = getWidth() / 2 - widthTitle / 2;
			yCoordinate = getHeight() / 2 + ascent / 2;
		}
		g.drawString(title, xCoordinate, yCoordinate);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;

	}
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
}
