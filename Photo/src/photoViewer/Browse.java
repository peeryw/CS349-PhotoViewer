package photoViewer;

import java.awt.FileDialog;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

public class Browse extends ImageIcon implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	Vector<ImageIcon> photoAlbum = new Vector<ImageIcon>();
	ImageIcon image = new ImageIcon();
	Maintain m;
	JFrame f;
	String str;
	File fl;
	File[] flist;
	Model model;

	public  ImageIcon BrowseAction() {
		
		FileDialog fdial = new FileDialog(f, "Open Box", FileDialog.LOAD);
		fdial.setSize(300, 300);
		fdial.setLocationRelativeTo(null);
		fdial.setVisible(true);
		String str = fdial.getDirectory();
		fl = new File(str);
		flist = fl.listFiles();
		String name = fl.getAbsolutePath();
		System.out.println(fl);
		System.out.println(name);
		System.out.println(flist);
		image = new ImageIcon(name + ".jpg");
		System.out.println(image);
		// add new photo to vector
		// include description and date block
		return image;
	}

	public ImageIcon getPhoto() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
