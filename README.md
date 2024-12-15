
---

# MusicStore Project

A terminal-based music management system that allows users to store, manage, and organize their songs and playlists.

## Features

- **Store Songs**: Add songs by specifying their exact file path and name.
- **Create Playlists**: Organize your songs into playlists.
- **Delete Songs**: Delete songs by providing their exact name with the `.mp3` extension.
- **Delete Playlists**: Remove playlists by specifying their name.
- **Search Songs**: Search for songs by their exact name.
- **Move Songs**: Move songs between playlists by specifying the song's name.

All operations are done via the terminal, with a simple menu interface.

## Terminal Interface

When you run the program, the terminal will display the following options:

```
1. List all songs
2. Search for a song
3. Add a song
4. Delete a song
5. Create a playlist
6. Delete a playlist
7. Move song to playlist
8. Exit
```

You can select an option by entering the corresponding number. Below are the actions you can perform based on each option.

## Actions

### 1. **List All Songs**
Lists all the songs stored in the system.

### 2. **Search for a Song**
Search for a song by its exact name (with the `.mp3` extension). If found, it will display the song details.

Example:
```
song_name.mp3
```

### 3. **Add a Song**
To add a song, you will be prompted to enter the exact path to the song and its name.

Example:
```
Enter song path: /user/music/rock/song_name.mp3
```

### 4. **Delete a Song**
To delete a song, you must enter the exact song name, including the `.mp3` extension.

Example:
```
Enter song name to delete: song_name.mp3
```

### 5. **Create a Playlist**
Create a new playlist by entering the desired playlist name.

Example:
```
Enter playlist name: My Rock Playlist
```

### 6. **Delete a Playlist**
To delete a playlist, specify only the playlist name (no `.mp3`).

Example:
```
Enter playlist name to delete: My Rock Playlist
```

### 7. **Move Song to Playlist**
To move a song to a specific playlist, enter the exact song name (with the `.mp3` extension) and the playlist name.

Example:
```
Enter song name: song_name.mp3
Enter playlist name: My Rock Playlist
```

### 8. **Exit**
Exit the application.

## Testing

All functions have been thoroughly tested, and the system works as expected.

## Requirements

- Python 3.x or higher is required to run the project.
- Access to a terminal interface for interacting with the program.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---