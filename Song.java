//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song
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

/**
 * Song class which creates the main song object
 * @author Chaitanya
 */
public class Song extends Object {
    private AudioUtility audioClip;
    // This song's AudioUtility interface to javax.sound.sampled
    private String artist;
    //The artist of this song
    private int duration;
    //The duration of this song in number of seconds
    private String title;
    //The title of this song


    /**
     * Initializes all instance data fields according to the provided values
     * @param title of the song
     * @param artist of the song
     * @param filepath of the song file
     * @throws IllegalArgumentException if the file cannot be read
     */
    public Song(String title, String artist, String filepath)throws IllegalArgumentException{
        try {
            audioClip = new AudioUtility(filepath); // initializes the audio clip
        } catch (Exception e) { // catch block for any runtime exceptions
            throw new IllegalArgumentException("Invalid file");
        }
        this.title = title;
        this.artist = artist;
        this.duration = audioClip.getClipLength();
    }

    /**
     * Tests whether this song is currently playing using the AudioUtility
     * @return true if the song is playing, false otherwise
     */
    public boolean isPlaying(){
        return audioClip.isRunning();
    }

    /**
     * Accessor for the title
     * @return the title of this song
     */
    public String getTitle(){
        return title;
    }
    /**
     * Accessor for the artist
     * @return the artist of this song
     */
    public String getArtist(){
        return artist;
    }

    /**
     * Uses the AudioUtility to start playback of this song,
     * reopening the clip for playback if necessary
     */
    public void play(){
        if(!(audioClip.isReadyToPlay())){
            audioClip.reopenClip(); // reopens the clip if it's already done
        }
        audioClip.startClip();
        System.out.println("Playing " + this.toString());
    }

    /**
     * Uses the AudioUtility to stop playback of this song
     */
    public void stop(){
        audioClip.stopClip(); // stops the playing clip
    }
    @Override
    /**
     * Creates and returns a string representation of this Song
     *
     * @returns a formatted string representation of this Song
     */
    public String toString(){
        String to = "\"" + getTitle() + "\" ";
        int minute = duration/60; // calculates minute time
        int second = duration%60;// calculates left over seconds

        to += "(" + minute + ":" + second + ") by " + getArtist(); // concat the remaining string

        return to;
    }


}
