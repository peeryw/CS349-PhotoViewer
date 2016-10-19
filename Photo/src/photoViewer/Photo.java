package photoViewer;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Photo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	public ImageIcon image;
    public String description;
    public String date;

    public Photo(ImageIcon img, String desc, String DATE){
        image = img;
        description = desc;
        date = DATE;
    }
    
    public void editPhotoData(String desc, String DATE){
    	description = desc;
    	date = DATE;
    }

}
