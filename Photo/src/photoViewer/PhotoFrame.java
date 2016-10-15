package photoViewer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
//import javax.swing.filechooser.FileNameExtensionFilter;

/*
Introduction: In this assignment you will create a Java program that takes the place of a physical photo album.

Assignment: Write a stand alone Java program that can be used to both view and maintain entries in a digital photo album.
 Your solution should have two modes: (1) a browse mode, and (2) a maintenance mode. 
 While in browse mode the user should be able to move forward or backwards through a list of pictures.
 Each picture should include an optional description and date when the photo was taken. While in 
 the maintenance mode the user should be able to update an existing image, update the data associated 
 with an image, delete an image, and add new images.

More specifically your program should provide the following features:

1. The user should be able to move forward, backward or jump to a specific picture.
2. The user interface (UI) should display the number of the current picture as well as total number of pictures.
3. In browse mode it shouldn't be possible to change date or description.
4. Data should be stored externally so that changes persistent between runs.


 */
public class PhotoFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JButton next = new JButton("NEXT >");
	JButton prev = new JButton("< PREV");
	int count = 1;
	String update = "1";
	JLabel counter = new JLabel(update);
	JMenu browse = new JMenu("Browse");
	JMenuItem maintain = new JMenuItem("Maintain");
	JMenuItem select = new JMenuItem("Select Photo");
	JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
	JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
	JTextArea info = new JTextArea(5, 30);
	JTextField date = new JTextField(1);
	Maintain maint = new Maintain();
	Browse brow = new Browse();
	Container middle = new JPanel();
	Container imageLabel = null;
	Model model = new Model();

	public PhotoFrame() {

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Photo Viewer");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		save.addActionListener(this);
		menu.add(save);
		exitMenuItem.addActionListener(this);
		menu.add(exitMenuItem);
		JMenu view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);
		menuBar.add(view);
		view.add(browse);
		maintain.addActionListener(e -> maint.MaintAction());
		view.add(maintain);
		select.addActionListener(e -> brow.BrowseAction());
		browse.add(select);

		Container image = getContentPane();
		Container bottom = Box.createVerticalBox();
		Container lmiddle = Box.createVerticalBox();
		Container description = Box.createVerticalBox();
		
		imageLabel = model.Model();
		image.add(imageLabel);
		
		JLabel infoLabel = new JLabel("Description");
		info.setEditable(false);
		date.setEditable(false);
		date.setColumns(10);
		
		JLabel dateLabel = new JLabel("Date");
		info.setText("<insert description here>");
		date.setText("<dd/mm/yyyy");
		
		description.add(Box.createHorizontalStrut(50));
		description.add(info);
		description.add(date);
		description.add(Box.createHorizontalStrut(50));
		lmiddle.add(infoLabel);
		lmiddle.add(Box.createVerticalStrut(60));
		lmiddle.add(dateLabel);
		
		middle.add(lmiddle, BorderLayout.WEST);
		middle.add(description, BorderLayout.EAST);
		middle.add(maint.Maintain());

		Box buttonBox = Box.createHorizontalBox();
		next.addActionListener(this);
		prev.addActionListener(this);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(counter);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(new JLabel("of 5"));
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(prev);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(next);
		Border titledBorder1 = BorderFactory.createTitledBorder("");
		buttonBox.setBorder(titledBorder1);
		bottom.add(middle);
		bottom.add(buttonBox);
		image.add(bottom, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == next) {
			if (count < 5) {
				count++;
				counter.setText(Integer.toString(count));
				if (count == 2) {
					prev.setEnabled(true);
				}
				System.out.println(count);
			}
			System.out.println("next");
		} else if (evt.getSource() == prev) {
			if (count > 1) {
				count--;
				counter.setText(Integer.toString(count));
				if (count == 4) {
					next.setEnabled(true);
				}
				System.out.println(count);
			}
			System.out.println("prev");
		} else if (evt.getSource() == save) {
			System.out.println("save");
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Save Changes and Quit?", "Question", JOptionPane.INFORMATION_MESSAGE);
		} else if (evt.getSource() == exitMenuItem) {
			System.exit(0);
		}
		if (count <= 1) {
			prev.setEnabled(false);
		} else if (count >= 5) {
			next.setEnabled(false);
		}
	}
}