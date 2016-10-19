package photoViewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Viewer {
	static SQLManager My;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PhotoFrame photo = new PhotoFrame("Java Photo Viewer");
				photo.pack();
				photo.setLocationRelativeTo(null);
				photo.setLayout(new BorderLayout());
				photo.setVisible(true);
								
				photo.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
			}
		});
	}
}
