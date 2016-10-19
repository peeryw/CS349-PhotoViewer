package photoViewer;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.*;

public class PhotoFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	int count;
	String update = "";
	ImageIcon currentImg;

	JMenu browse = new JMenu("Browse");
	JMenuItem maintain = new JMenuItem("Maintain");
	JMenuItem select = new JMenuItem("Select Photo");
	JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
	JTextArea info = new JTextArea(5, 20);
	JTextField CurrentNumbPhoto = null;

	DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
	JFormattedTextField date = new JFormattedTextField();

	JButton next = new JButton("NEXT >");
	JButton prev = new JButton("< PREV");

	Container middle = new JPanel();

	JLabel imageLabel = null;
	JLabel counter = new JLabel();
	
	
	
	Photo tempPhoto = null;
	SQLManager Pictures;
	static SQLManager My;
	Maintain maint = new Maintain();
	Browse brow = new Browse();

	public PhotoFrame(String title) {

		super(title);

		try {
			My = new SQLManager();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenu view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menu);
		menuBar.add(view);
		menu.add(exitMenuItem);
		exitMenuItem.addActionListener(this);

		view.add(browse);
		view.add(maintain);
		browse.add(select);
		maintain.addActionListener(e -> maint.MaintAction());
		select.addActionListener(e -> brow.BrowseAction());

		Container image = getContentPane();
		Container bottom = Box.createVerticalBox();
		Container lmiddle = Box.createVerticalBox();
		Container description = Box.createVerticalBox();

		imageLabel = new JLabel("");
		try {
			tempPhoto = Pictures.Getcurrentphoto();
			if (tempPhoto == null) {
				String DATE = new String("");
				imageLabel = new JLabel("");
				ImageIcon noPic = new ImageIcon("code mokey.jpg");
				imageLabel.setIcon(noPic);
				tempPhoto = new Photo(noPic, "", DATE);

			}
			else {
				imageLabel.setIcon(tempPhoto.image);
			}
		} catch (NullPointerException NULL) {
			System.out.println(NULL);
		}

		image.add(imageLabel);

		JLabel infoLabel = new JLabel("Description");
		info.setEditable(false);
		date.setEditable(false);
		date.setColumns(10);

		JLabel dateLabel = new JLabel("Date");
		info.setText("<insert description here>");
		date.setText("dd-mm-yyyy");

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
		CurrentNumbPhoto = new JTextField("");
		//CurrentNumbPhoto.setText(Integer.toString(Pictures.getTotalNumbPhoto()));
		
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(counter);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(CurrentNumbPhoto);
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
		
		try {
			count = Pictures.getTotalNumbPhoto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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