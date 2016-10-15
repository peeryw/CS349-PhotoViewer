package photoViewer;

import java.io.Serializable;

public class Photo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private String imgLocation;
    private String description;
    private String date;

    public Photo(){  }

    public Photo(String img, String desc, String date){
        this.imgLocation = img;
        this.description = desc;
        this.date = date;
    }

    public String getImgPath(){return this.imgLocation;}
    public String getDescription(){return this.description;}
    public String getDate() {return date;}

    public void setImgPath(String path){this.imgLocation = path;}
    public void setDescription(String desc){this.description = desc;}
    public void setDate(String date){this.date = date;}

}
