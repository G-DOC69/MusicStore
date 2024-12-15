import java.util.List;
import java.util.Scanner;

public class MusicStore {
    private static final String MAIN_FOLDER = "music_store";
    private static final MusicManager musicManager = new MusicManager(MAIN_FOLDER);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to Music Store!\nChoose an option:");
            System.out.println("1. List all songs");
            System.out.println("2. Search for a song");
            System.out.println("3. Add a song");
            System.out.println("4. Delete a song");
            System.out.println("5. Create a playlist");
            System.out.println("6. Delete a playlist");
            System.out.println("7. Move song to playlist");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listAllSongs();
                    break;
                case 2:
                    searchSong(scanner);
                    break;
                case 3:
                    addSong(scanner);
                    break;
                case 4:
                    deleteSong(scanner);
                    break;
                case 5:
                    createPlaylist(scanner);
                    break;
                case 6:
                    deletePlaylist(scanner);
                    break;
                case 7:
                    moveSongToPlaylist(scanner);
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting Music Store. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void listAllSongs() {
        List<Song> songs = musicManager.listSongs();
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            System.out.println("Songs:");
            songs.forEach(song -> System.out.println("- " + song.getName()));
        }
    }

    private static void searchSong(Scanner scanner) {
        System.out.print("Enter song name to search: ");
        String query = scanner.nextLine().toLowerCase();
        List<Song> songs = musicManager.listSongs();
        boolean found = false;

        for (Song song : songs) {
            if (song.getName().toLowerCase().contains(query)) {
                System.out.println("Found: " + song.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching songs found.");
        }
    }

    private static void addSong(Scanner scanner) {
        System.out.print("Enter the full path of the song to add: ");
        String path = scanner.nextLine();
        System.out.print("Enter a name for the song: ");
        String name = scanner.nextLine();

        if (musicManager.addSong(name, path)) {
            System.out.println("Song added successfully.");
        } else {
            System.out.println("Failed to add the song.");
        }
    }

    private static void deleteSong(Scanner scanner) {
        System.out.print("Enter the name of the song to delete: ");
        String name = scanner.nextLine();

        if (musicManager.removeSong(name)) {
            System.out.println("Song deleted successfully.");
        } else {
            System.out.println("Failed to delete the song.");
        }
    }

    private static void createPlaylist(Scanner scanner) {
        System.out.print("Enter the name of the playlist to create: ");
        String name = scanner.nextLine();

        if (musicManager.createPlaylist(name)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create the playlist.");
        }
    }

    private static void deletePlaylist(Scanner scanner) {
        System.out.print("Enter the name of the playlist to delete: ");
        String name = scanner.nextLine();

        if (musicManager.deletePlaylist(name)) {
            System.out.println("Playlist deleted successfully.");
        } else {
            System.out.println("Failed to delete the playlist.");
        }
    }

    private static void moveSongToPlaylist(Scanner scanner) {
        System.out.print("Enter the name of the song to move: ");
        String songName = scanner.nextLine();
        System.out.print("Enter the name of the destination playlist: ");
        String playlistName = scanner.nextLine();

        if (musicManager.moveSongToPlaylist(songName, playlistName)) {
            System.out.println("Song moved successfully.");
        } else {
            System.out.println("Failed to move the song.");
        }
    }
}