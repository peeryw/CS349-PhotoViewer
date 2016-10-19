package photoViewer;

import java.awt.Container;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Model extends JLabel {

	private static final long serialVersionUID = 1L;
	
	ImageIcon image = new ImageIcon();
	Maintain m;
	JFrame f;
	String str;
	JFileChooser fc;

	public Container Model() {
		Container model = Box.createHorizontalBox();

		JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
		JScrollPane scrollPane = new JScrollPane(imageLabel);
		ImageIcon image = new ImageIcon("code mokey.jpg");
		imageLabel.setVisible(true);
		imageLabel.setIcon(image);
		model.add(scrollPane);
		return model;
	}
	public Container updatePhoto(){
		Container model = Box.createHorizontalBox();

		JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
		Border titledBorder = BorderFactory.createTitledBorder("");
		imageLabel.setBorder(titledBorder);
		JScrollPane scrollPane = new JScrollPane(imageLabel);
		ImageIcon image = new ImageIcon("code mokey.jpg");
		
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + fc.getSelectedFile().getName());
			image = new ImageIcon(fc.getSelectedFile().getName());
			System.out.println(image);
		}
		imageLabel.setVisible(true);
		imageLabel.setIcon(image);
		model.add(scrollPane);
		return model;
	}
	
}
