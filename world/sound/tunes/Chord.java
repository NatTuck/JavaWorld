/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/tunes/Chord.java                                *
 *                                                                       *
 *   This file is part of JavaWorld.                                     *
 *                                                                       *
 *   JavaWorld is free software: you can redistribute it and/or          *
 *   modify it under the terms of the GNU General Public License         *
 *   as published by the Free Software Foundation, either version        *
 *   3 of the License, or (at your option) any later version.            *
 *                                                                       *
 *   JavaWorld is distributed in the hope that it will be useful,        *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the       *
 *   GNU General Public License for more details.                        *
 *                                                                       *
 *   You should have received a copy of the GNU General Public License   *
 *   along with JavaWorld.  If not, see <http://www.gnu.org/licenses/>.  *
 *                                                                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package world.sound.tunes;

import java.util.*;

/** A class to represent a collection of notes to be played
 *    on one instrument at the same time. Individual notes do
 *    not need to have the same duration.
 *    
 *  Based in part on a class originally designed by Viera K. Proulx. */
public class Chord{

    /** The default Note duration. */
    public static final int DEFAULT_DUR = 1;
    
    /** the list of notes in this chord */
    public ArrayList<Note> notes = new ArrayList<Note>();

    /** The default constructor */
    public Chord(){}

    /** Creates a Chord from a sequence of Pitches (Note numbers) */
    public Chord(int ... pitches){
        for(int pitch: pitches)
            this.addNote(pitch, DEFAULT_DUR);
    }

    /** Create a Chord from a sequence of Notes. */
    public Chord(Note ... notes){
        for(Note n : notes){
            this.addNote(n);
        }
    }

    /** Add a given note to this Chord. */
    public void addNote(Note n){
        this.notes.add(n.copy());
    }

    /** Add a Note with the given pitch and duration to this Chord. */
    public void addNote(int pitch, int duration){
        this.notes.add(new Note(pitch, duration));
    }

    /** Reduce all the Notes in this Chord by a single beat. Produces a
     *    Chord representing the list of notes that have stopped playing. */
    public Chord nextBeat(){
        Chord ch = new Chord();
        for(int i = 0; i < this.notes.size(); i++){
            Note n = this.notes.get(i);
            n.nextBeat();
            if(n.getDuration() <= 0)
                ch.addNote(n);
        }
        return ch;
    }

    /** Have all notes in this chord finish playing? */
    public boolean isSilent(){
        for(int i = 0; i < this.notes.size(); i++){
            Note n = this.notes.get(i);
            if(n.getDuration() > 0)
                return false;
        }
        return true;
    }

    /** Make a copy of this Chord by copying its Notes */
    public Chord copy(){
        Chord result = new Chord();
        for(int i = 0; i < this.notes.size(); i++){
            Note n = this.notes.get(i);
            result.addNote(n.copy());
        }
        return result;
    }


    /** A singleton Chord representing silence */
    public static final Chord noplay = new Chord();

    /** Get the number of Notes in this Chord. */
    public int size(){
        return this.notes.size();
    }

    /** Does this chord contain the given note? */
    public boolean containsNote(Note note){
        for(int i = 0; i < this.notes.size(); i++){
            Note n = this.notes.get(i);
            if(note.sameNote(n))
                return true;
        }
        return false;
    }
}
