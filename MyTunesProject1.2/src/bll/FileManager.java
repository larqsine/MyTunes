package bll;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
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
}
