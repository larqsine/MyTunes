package dk.easv.BE;

public class SongClass {
    private String title;
    private String artist;
    private String category;
    private Double time;

    private String file;



    public SongClass(String title, String artist, String category, Double time, String file) {
       this.title = title;
        this.artist = artist;
        this.category = category;
        this.time = time;
        this.file = file;
    }

    public SongClass() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }
   public void setArtist(String artist){
        this.artist =artist;
   }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category =category;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
       this.file = file;
    }
}
