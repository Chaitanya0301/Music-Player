//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Song Node
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
 * The song node class
 * @author Chaitanya
 */
public class SongNode extends Object{

    private Song song; // The Song object in this node
    private SongNode next; //The next SongNode in this queue

    /**
     * Constructs a single SongNode containing the given data, not linked to any other SongNodes
     * @param data data - the Song for this node
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data) throws IllegalArgumentException{
        if(data == null){
            throw new IllegalArgumentException("DATA NULL");
        }
        this.song = data;
    }

    /**
     * Constructs a single SongNode containing the given data, linked to the specified SongNode
     * @param data the Song for this node
     * @param next the next node in the queue
     * @throws IllegalArgumentException if data is null
     */
    public SongNode(Song data,SongNode next) throws IllegalArgumentException{
        if(data == null){
            throw new IllegalArgumentException("DATA NULL");
        }
        this.song = data;
        this.next = next;
    }

    /**
     * Accessor method for the song
     * @return the Song in this node
     */
    public Song getSong(){
        return song;
    }

    /**
     * Accessor method for the next node in the queue
     * @return the SongNode following this one, if any
     */
    public SongNode getNext(){
        return next;
    }

    /**
     * Changes the value of this SongNode's next data field to the given value
     * @param next the SongNode to follow this one; may be null
     */
    public void setNext(SongNode next){
        this.next = next; // sets new value for the next node
    }
}
