package dk.easv.BE;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;

import java.util.List;

public class SongClass {
    private String Title;
    private SimpleStringProperty  Artist;
    private SimpleStringProperty  Category;
    private SimpleDoubleProperty  Time;

    private String File;



    public SongClass() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = new String(title);
    }

    public String getArtist() {
        return Artist.get();
    }

    public void setArtist(String artist) {
        this.Artist =new SimpleStringProperty(artist);
    }

    public String getCategory() {
        return Category.get();
    }

    public void setCategory(String category) {
        this.Category =new SimpleStringProperty(category);
    }

    public double getTime() {
        return Time.get();
    }

    public void setTime(double time) {
        this.Time = new SimpleDoubleProperty(time);
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
       this.File= new String(file);
    }
}
