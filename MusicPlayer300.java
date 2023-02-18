//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Music player 300
// Course:   CS 300 Fall 2022
//
// Author:   Chaitanya Sharma
// Email:    csharma4@wisc.edu
// Lecturer: Mouna Kacem
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * Music player 300 class
 * @author Chaitanya
 */
public class MusicPlayer300 extends Object {
    private Playlist playlist; //The current playlist of Songs

    private boolean filterPlay; // Whether the current playback mode should be filtered by artist

    private String filterArtist;//The artist to play if filterPlay is true; should be null otherwise

    /**
     * Constructor for the Music player 300 class
     */
    public MusicPlayer300() {
        playlist = new Playlist();
        filterPlay = false;
    }

    /**
     * Stops any song that is playing and clears out the playlist
     */
    public void clear() {
        for (int i = 0; i < playlist.size(); i++) { // loops through the playlist
            Song remove = playlist.dequeue(); // saves and removes the first song
            remove.stop(); // stops the playback
        }
    }

    /**
     * Loads a playlist from a provided file
     *
     * @param file file to load from
     * @throws FileNotFoundException if the playlist cannot be loaded
     */
    public void loadPlaylist(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] info = scanner.nextLine().split(",");
            String path = "audio/" + info[2];
            Song song = new Song(info[0], info[1], path); // song to load
            out.println("Loading " + song.getTitle());

            int size = playlist.size();
            playlist.enqueue(song); // load song

            if (playlist.size() == size) {
                out.println("x"); // if the song is not loaded
            }
        }
    }

    /**
     * Loads a single song to the end of the playlist given the title, artist, and filepath.
     *
     * @param title    title of the song
     * @param artist   song's artist
     * @param filepath full file path
     * @throws IllegalArgumentException if the song file cannot be read
     */
    public void loadOneSong(String title, String artist, String filepath) throws IllegalArgumentException {
        try {
            Song song = new Song(title, artist, filepath);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to load song.");
        }
        Song song = new Song(title, artist, filepath);
        playlist.enqueue(song); // load song
    }

    /**
     * Provides a string representation of all songs in the current playlist
     *
     * @return a string representation of all songs in the current playlist
     */
    public String printPlaylist() {
        return playlist.toString();
    }

    /**
     * Creates and returns the menu of options for the interactive console program.
     *
     * @return the formatted menu String
     */
    public String getMenu() {
        String output = ""; // fill with given strings from the write-up
        output += "Enter one of the following options:\n[A <filename>] to enqueue a new song file to the end of this playlist\n";
        output += "[F <filename>] to load a new playlist from the given file\n[L] to list all songs in the current playlist\n";
        output += "[P] to start playing ALL songs in the playlist from the beginning\n[P -t <Title>] to play all songs in the playlist starting from <Title>\n";
        output += "[P -a <Artist>] to start playing only the songs in the playlist by Artist\n[N] to play the next song\n";
        output += "[Q] to stop playing music and quit the program\n";

        return output;
    }

    /**
     * Stops playback of the current song (if one is playing) and advances to the next song in the playlist.
     *
     * @throws IllegalStateException if the playlist is null or empty, or becomes empty at any time during this method
     */
    public void playNextSong() throws IllegalStateException {
        if (playlist == null || playlist.isEmpty()) {
            throw new IllegalStateException("Playlist empty"); // if playlist empty
        }
        if (playlist.peek().isPlaying()) {
            playlist.peek().stop(); //stops the current song
        }
        playlist.dequeue();//removes it from the queue
        if (playlist == null || playlist.isEmpty()) {
            throw new IllegalStateException("Playlist empty");
        }
        if (filterPlay) {
            while (!(playlist.peek().getArtist().equals(filterArtist))) { //dequeues all songs without the given artist
                if (playlist == null || playlist.isEmpty()) {
                    throw new IllegalStateException("Playlist empty");
                }
                playlist.dequeue();
            if (playlist == null || playlist.isEmpty()) {
                throw new IllegalStateException("Playlist empty");
            }
        }
            playlist.peek().play();//plays the new current song
        } else {
            playlist.peek().play();
        }
    }

    /**
     * Plays songs from the given title or just the given artist
     * @param title title of the song
     * @throws IllegalStateException if the playlist is null or empty
     */
    private void playTitle(String title) throws IllegalStateException{
        if(playlist == null||playlist.isEmpty()){
            throw new IllegalStateException("Playlist empty"); // checks if playlist is empty
        }
        while (!playlist.peek().getTitle().equals(title)) {
            playlist.dequeue();
            if (playlist.isEmpty() || playlist == null) {
                throw new IllegalStateException();
            }
        }
        playlist.peek().play();
        playNextSong();
    }

    /**
     * Interactive method to display the MusicPlayer300 menu and get keyboard input from the user.
     * @param scanner scanner object
     */
    public void runMusicPlayer300(Scanner scanner){
        System.out.println(getMenu());
        System.out.print("> ");
        String input = scanner.nextLine();
        try{
            if(input.equals("L")){ // if user wants to print playlist
                if (printPlaylist().isEmpty()) {
                    System.out.println("Playlist empty");
                }else {
                    System.out.println(printPlaylist());
                }
            } else if(input.equals("N")){ // if user wants to play next song
                playNextSong();
            } else if(input.equals("Q")){ // if user wants to quit
                clear();
                System.out.println("Goodbye!");
            } else if(input.charAt(0) == 'A'){ // if user wants to add a song
                String[] aIn = input.split(" ");

                System.out.print("Title: ");
                String title = scanner.nextLine(); // title input
                System.out.print("Artist: ");
                String artist = scanner.nextLine(); // artist input
                String file = "audio/"+aIn[1]; // file from the input

                loadOneSong(title,artist,file); // loads new song
                runMusicPlayer300(scanner);
            } else if(input.charAt(0) == 'F'){ // if user wants to load a new playlist
                String[] fIn = input.split(" ");
                try {
                    loadPlaylist(new File(fIn[1])); // loads new playlist according to input
                } catch (FileNotFoundException e) {
                    System.out.println("Invalid file");
                }
            } else if(input.equals("P")){
                playlist.peek().play();
            }else if(input.charAt(0) == 'P') {
                String[] pIn = input.split(" ");
                if(pIn[1].charAt(1) == 't'){ // uses helper method to play songs after a particular title
                    playTitle(pIn[2]);

                }
                if(pIn[1].charAt(1) == 'a'){
                    filterPlay = true;
                    filterArtist = pIn[2]; // sets artist from the extracted string
                    playNextSong();
                    filterPlay = false;
                }
            }
            else{
                System.out.println("I don't know how to do that.");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Song not loaded");
        } catch (IllegalStateException e) {
            System.out.println("No remaining songs");
        }
        runMusicPlayer300(scanner);
    }

}
