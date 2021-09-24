package mainGameFlipAndFind;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestGame {
	public static void main(String[] args) {
		//createAndShowGUI();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 new FrameFlipAndFind();
	}
}
