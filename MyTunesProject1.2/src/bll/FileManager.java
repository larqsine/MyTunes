package bll;

import java.io.File;
import java.util.*;

public class FileManager {
    private Map<String, String> songFilePaths;

    public FileManager() {
        songFilePaths = new HashMap<>();
        songFilePaths.put("[grse.mp3]", "Playlist1/grse.mp3");
        songFilePaths.put("[Same.mp3]", "Playlist2/Same.mp3");
        songFilePaths.put("[Tree.mp3]", "Playlist2/Tree.mp3");
        songFilePaths.put("[Amazing.mp3]", "Playlist3/Amazing.mp3");
        songFilePaths.put("[lofi.mp3]", "Playlist3/lofi.mp3");
        songFilePaths.put("[paris.mp3]", "Playlist3/paris.mp3");
        songFilePaths.put("[titan.mp3]", "Playlist4/titan.mp3");
    }

    public String getFilePathForSong(String songName) {
        return songFilePaths.get(songName);
    }

    public List<String> getFileNamesForIndex(int index) {
        String folderPath = null;

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
        return getAllFileNames(folderPath);
    }

    private List<String> getAllFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public String getSongNameForIndex(int index) {
        String nameOfSong = null;

        switch (index) {
            case 0:
                nameOfSong = "Titan.mp3";
                break;
            case 1:
                nameOfSong = "Paris.mp3";
                break;
            case 2:
                nameOfSong = "Lofi.mp3";
                break;
            case 3:
                nameOfSong = "Tree.mp3";
                break;
            case 4:
                nameOfSong = "Same.mp3";
                break;
            case 5:
                nameOfSong = "Amazing.mp3";
                break;
            case 6:
                nameOfSong = "grse.mp3";
                break;
        }
        return nameOfSong;
    }
}
