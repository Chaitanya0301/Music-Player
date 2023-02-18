//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Music player tester
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
import java.util.Scanner;

/**
 * Tester class for the entire project
 * @author Chaitanya
 */
public class MusicPlayerTester {
    /**
     * main method of the class which runs all tests
     * @param args if any
     */
    public static void main(String[]args){
        System.out.println(testSongConstructor());
        System.out.println(testSongPlayback());
        System.out.println(testSongNode());
        System.out.println(testEnqueue());
        System.out.println(testDequeue());

        Scanner sc = new Scanner(System.in);
        MusicPlayer300 n = new MusicPlayer300();
        n.runMusicPlayer300(sc);
    }
    /**
     * Tester method for the constructor and other methods in the song class
     *
     * @return true if all requirements passed
     */
    public static boolean  testSongConstructor(){
        try {
            boolean test = false; // boolean variable to check if an exception is thrown or not
            try {
                Song fake = new Song("HMM", "Null", "audio/7.mid"); // invalid file
            } catch (IllegalArgumentException e) {
                test = true;
            }
            if (!test) {
                return false;
            }
            Song real = new Song("real", "AM", "audio/1.mid");
            AudioUtility tester = null; // tester audio with the same duration
            try {
                tester = new AudioUtility("audio/1.mid");
            } catch (Exception e) {
                System.out.println("Wrong tester");
            }
            if (!real.getTitle().equals("real") || !real.getArtist().equals("AM")){
                return false;
            }
            if (!(real.toString().equals("\"" + real.getTitle() + "\" " + "(" + tester.getClipLength() / 60 + ":"
                    + tester.getClipLength() % 60 + ") by " + real.getArtist()))) {
                return false; // checks the toString  along with other accessors
            }
        }catch(Exception e) {
            return false;
        }
            return true;
    }


    /**
     * This is the playback tester method
     *
     * @return true if all tests passed
     */
    public static boolean testSongPlayback(){
        Song real  = new Song("real", "AM", "audio/1.mid");
        real.play(); // plays the audio clip
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep error");
        }
        if(!(real.isPlaying())){
            return false;
        }
        return true; // if audio plays
    }

    /**
     * Tester method for the song node class
     * @return true if SongNode has no errors
     */
    public static boolean testSongNode(){
        Song song = new Song("song","artist", "audio/1.mid");;
        Song song1 = new Song("song1","artist1", "audio/2.mid");

        SongNode songN = new SongNode(song);
        SongNode songN1 = new SongNode(song1);

        if (songN.getSong() != song || songN.getNext() != null) {
            return false; // checks whether initialization was successful
        }
        songN.setNext(songN1);
        if (songN.getNext() != songN1 || songN.getSong() != song ||songN.getNext().getSong() != song1) {
                return false; // checks whether getNext works or not
            }
        SongNode newNode = new SongNode(song, songN1);
        if (newNode == null || newNode.getSong() != song || newNode.getNext() != songN1 || newNode.getNext().getSong() != song1) {
                return false; // checks the correctness of the second SongNode constructor.
            }
        return true;
    }


    /**
     * Tester for enqueue from playlist
     * @return true if no bugs present and false otherwise
     */
    public static boolean testEnqueue(){
        Playlist playlist = new Playlist();// test playlist
        Song song = new Song("test","AM","audio/2.mid"); // test song
        Song song1 = new Song("test1","AM","audio/1.mid"); // test song

        if(playlist.isEmpty() != true){
            return false; // checks the is Empty method
        }
        if(song != null) {
            playlist.enqueue(song);
            if (!(playlist.peek().equals(song)) || playlist.isEmpty() || playlist.size() != 1) {
                return false;
            }
        }
        if(song1 != null) {
            playlist.enqueue(song1);
            if (!(playlist.peek().equals(song)) ||playlist.isEmpty()|| playlist.size() != 2) { // checks if the song is not added to the front
                return false;
            }
        }
        return true;// works fine
    }
    /**
     * Tester for dequeue from playlist
     * @return true if no bugs present and false otherwise
     */
    public static boolean testDequeue() {
        try {
            Playlist playlist = new Playlist();// test playlist
            Song song = new Song("test", "AM", "audio/2.mid"); // test song
            Song song1 = new Song("test1", "AM", "audio/1.mid"); // test song

            if (song != null && song1 != null) { // enqueues both the songs to the playlist
                playlist.enqueue(song);
                playlist.enqueue(song1);
            }

            if (playlist.size() != 2 || playlist.peek() != song) { // checks if they were added
                return false;
            }

            Song dSong = playlist.dequeue();
            if (!dSong.toString().equals(song.toString()) || playlist.peek() != song1 || playlist.size() != 1) {
                return false; // checks if the correct song is dequeued
            }

            dSong = playlist.dequeue();
            if (!dSong.toString().equals(song1.toString()) || playlist.peek() != null || !playlist.isEmpty() || playlist.size() != 0) {
                return false; // checks if playlist is empty after removing last left song
            }
        } catch(Exception e) {
            System.out.println("Unknown error");
            return false;
        }
            return true;// works fine
    }
}
