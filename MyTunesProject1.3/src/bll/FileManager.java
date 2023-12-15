package bll;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {

    private String folderPath;
    private Map<String, String> songPath;

    public FileManager() {
        getThePathForSong();
    }

    public String getSong(String songName) {
        return songPath.get(songName);
    }

    public List<String> getThePathOfPlaylist(int index) {

        folderPath = null;

        switch (index) {
            case 0:
                folderPath = "Playlist1/";
                break;
            case 1:
                folderPath = "Playlist2/";
                break;
            case 2:
                folderPath = "Playlist3/";
                break;
            case 3:
                folderPath = "Playlist4/";
                break;
        }
        return getAllNamesFromPlaylist(folderPath);
    }

    public List<String> getAllNamesFromPlaylist(String folderPath) {

        List<String> fileNames = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        for(File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public void getThePathForSong() {
        songPath = new HashMap<>();
        songPath.put("Amazing.mp3", "Playlist1/Amazing.mp3");
        songPath.put("Same.mp3", "Playlist2/Same.mp3");
        songPath.put("Tree.mp3", "Playlist2/Tree.mp3");
        songPath.put("lofi.mp3", "Playlist3/lofi.mp3");
        songPath.put("paris.mp3", "Playlist3/paris.mp3");
        songPath.put("titan.mp3", "Playlist3/titan.mp3");
        songPath.put("grse.mp3", "Playlist4/grse.mp3");
    }
}
