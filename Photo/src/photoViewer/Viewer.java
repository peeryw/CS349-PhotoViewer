package photoViewer;


	
	import java.awt.BorderLayout;
	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;

	import javax.swing.JFrame;
	import javax.swing.SwingUtilities;

	public class Viewer {
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					PhotoFrame photo = new PhotoFrame();
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
