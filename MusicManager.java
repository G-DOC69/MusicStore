import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicManager {

    private final String musicDirectory;

    public MusicManager(String musicDirectory) {
        this.musicDirectory = musicDirectory;
        File directory = new File(musicDirectory);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Could not create the music directory.");
        }
    }

    // List all .mp3 files, including those in playlists
    public List<Song> listSongs() {
        List<Song> allSongs = new ArrayList<>();
        listSongsInDirectory(new File(musicDirectory), allSongs);
        return allSongs;
    }

    // Recursive method to list all songs in a directory and its subdirectories
    private void listSongsInDirectory(File directory, List<Song> songList) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                listSongsInDirectory(file, songList); // Recursively check subdirectories
            } else if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
                songList.add(new Song(file.getName(), file.getAbsolutePath()));
            }
        }
    }

    // Add a song to the music directory
    public boolean addSong(String sourcePath, String newSongName) {
        File sourceFile = new File(sourcePath.trim());
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            System.out.println("Source file does not exist: " + sourcePath);
            return false;
        }

        // Append .mp3 if not already present
        if (!newSongName.toLowerCase().endsWith(".mp3")) {
            newSongName += ".mp3";
        }

        File destinationFile = new File(musicDirectory, newSongName);
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            System.out.println("Song added successfully: " + newSongName);
            return true;
        } catch (IOException e) {
            System.out.println("Error while adding song: " + e.getMessage());
            return false;
        }
    }

    // Remove a song from the directory (checks main directory and playlists)
    public boolean removeSong(String songName) {
        File song = findSong(songName);
        if (song != null && song.isFile()) {
            if (song.delete()) {
                System.out.println("Song removed successfully: " + songName);
                return true;
            } else {
                System.out.println("Failed to remove song: " + songName);
                return false;
            }
        } else {
            System.out.println("Song not found: " + songName);
            return false;
        }
    }

    // Find a song in the main directory or playlists
    private File findSong(String songName) {
        List<Song> allSongs = listSongs();
        for (Song song : allSongs) {
            if (song.getName().equalsIgnoreCase(songName)) {
                return new File(song.getPath());
            }
        }
        return null;
    }

    // Create a playlist (folder)
    public boolean createPlaylist(String playlistName) {
        File playlist = new File(musicDirectory, playlistName);
        if (playlist.exists()) {
            System.out.println("Playlist already exists: " + playlistName);
            return false;
        }

        if (playlist.mkdir()) {
            System.out.println("Playlist created successfully: " + playlistName);
            return true;
        } else {
            System.out.println("Failed to create playlist: " + playlistName);
            return false;
        }
    }

    // Move a song to a playlist
    public boolean moveSongToPlaylist(String songName, String playlistName) {
        File song = findSong(songName);
        File playlist = new File(musicDirectory, playlistName);

        if (song == null) {
            System.out.println("Song not found: " + songName);
            return false;
        }

        if (!playlist.exists() || !playlist.isDirectory()) {
            System.out.println("Playlist not found: " + playlistName);
            return false;
        }

        File newLocation = new File(playlist, song.getName());
        if (song.renameTo(newLocation)) {
            System.out.println("Song moved successfully to playlist: " + playlistName);
            return true;
        } else {
            System.out.println("Failed to move song to playlist: " + playlistName);
            return false;
        }
    }

    // Delete a playlist and its contents
    public boolean deletePlaylist(String playlistName) {
        File playlist = new File(musicDirectory, playlistName);

        if (!playlist.exists() || !playlist.isDirectory()) {
            System.out.println("Playlist not found: " + playlistName);
            return false;
        }

        if (deleteDirectoryRecursively(playlist)) {
            System.out.println("Playlist deleted successfully: " + playlistName);
            return true;
        } else {
            System.out.println("Failed to delete playlist: " + playlistName);
            return false;
        }
    }

    // Helper method to delete a directory recursively
    private boolean deleteDirectoryRecursively(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryRecursively(file);
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }
}
