//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Playlist
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
 * Playlist object class
 * @author Chaitanya
 */
public class Playlist extends Object implements QueueADT<Song>{
    private SongNode first;//The current first song in the queue

    private SongNode last;//The current last song in the queue

    private int numSongs; // The number of songs currently in the queue

    /**
     * Constructor for the playlist class
     */
    public Playlist(){
        this.first = null;
        this.last = null;
        numSongs = 0;
    }

    /**
     * Adds a new song to the end of the queue
     * @param element the song to add to the Playlist
     */
    @Override
    public void enqueue(Song element){
        SongNode elNode = new SongNode(element); // node with the parameter song
        if (first == null && last == null) {
            first = elNode;
        } else {
            last.setNext(elNode); // adds the node to the end
        }
        last = elNode;
        numSongs += 1; // inc size
    }

    /**
     * Removes the song from the beginning of the queue
     * @return the song that was removed from the queue, or null if the queue is empty
     */
    @Override
    public Song dequeue() {
        if (first == null && last == null) { // if list is empty
            return null;
        } else if(first.equals(last)){ // if only one song is in the playlist
            Song sample = first.getSong();
            first = null;
            last = null;
            numSongs -= 1;
            return sample;
        }
        Song sample = first.getSong(); //standard playlist with multiple songs
        first = first.getNext();
        last = null;
        numSongs -= 1;
        return sample;
    }

    /**
     * Returns the song at the front of the queue without removing it
     * @return the song that is at the front of the queue, or null if the queue is empty
     */
    @Override
    public Song peek() {
        return first.getSong();
    }

    /**
     * Returns true if and only if there are no songs in this queue
     * @return true if empty false otherwise
     */
    @Override
    public boolean isEmpty() {
        if(first == null){ // returns true if first element is null
            return true;
        }
        return false;
    }

    /**
     * Returns the number of songs in this queue
     * @return number of songs
     */
    @Override
    public int size() {
        return numSongs;
    }

    /**
     * Creates and returns a formatted string representation of this playlist
     * @return the string representation of this playlist
     */
    @Override
    public String toString(){
        SongNode pointer = first; // node tracker
        if(size() == 0){
            return "";
        }
        String to = "";
        int i = 0;
        while(i < size()){
            to+=pointer.getSong().toString()+"\n";
            pointer = pointer.getNext(); // current node after adding the toString of the previous one
            i+=1;
        }
        return to;
    }
}
