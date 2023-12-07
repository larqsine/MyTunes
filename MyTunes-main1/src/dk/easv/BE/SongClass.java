package dk.easv.BE;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SongClass {
    private String Title;
    private String  Artist;
    private String  Category;
    private Double Time;

    private String File;



    public SongClass(String title, String artist, String category, Double time, String file) {
       this.Title = title;
        this.Artist = artist;
        this.Category = category;
        this.Time = time;
        this.File = file;
    }

    public SongClass() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = new String(title);
    }

    public String getArtist() {
        return Artist;
    }
   public void setArtist(String artist){
        this.Artist=new String(artist);
   }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category=new String(category);
    }

    public Double getTime() {
        return Time;
    }

    public void setTime(double time) {
        this.Time = time;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
       this.File= new String(file);
    }
}
