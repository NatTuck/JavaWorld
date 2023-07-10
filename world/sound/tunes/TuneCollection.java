/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/tunes/TuneCollection.java                       *
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

/**
 * A collection of Notes/Tunes to be played on MIDI instruments, together
 *    with the {@link world.sound.tunes.MusicBox} on which the Notes
 *    will be played.
 * 
 * Based in part on a class originally designed by Viera K. Proulx. 
 */
public class TuneCollection implements SoundConstants{

    /** The list of Tunes to be played on the next 'tick'. */
    protected ArrayList<Tune> tunes;

    /** the music box that plays the tunes in this Collection. */
    protected MusicBox musicBox;

    /**  Create a TunCollection associated with the given MusicBox. */
    public TuneCollection(MusicBox musicBox){
        this.tunes = new ArrayList<Tune>();
        this.musicBox = musicBox;
        this.initTunes();
    }

    /** Initialize all the Channels to empty Tunes. */
    public void initTunes(){
        for(int i = 0; i < 16; i++)
            this.tunes.add(new Tune(i));
    }

    /** Add all the Tunes/Chords form the given TuneCollection to this one. */
    public void add(TuneCollection tb){
        for(int i = 0; i < 16; i++)
            this.tunes.get(i).addChord(tb.tunes.get(i).chord.copy());
    }

    /** Add the Note represented by the given Pitch to the given Channel in
     *    this Collection. */
    public void addNote(int channel, int pitch){    
        this.addNote(channel, new Note(pitch));
    }

    /** Add the Note represented by the given String to the given Channel in
     *    this Collection. */
    public void addNote(int channel, String note){    
        this.addNote(channel, new Note(note));
    }

    /** Add a all given Tunes/Chords (Iterable) to this Collection. */
    public void addTunes(Iterable<Tune> tunes){
        for(Tune tune: tunes)
            this.addTune(tune);
    }

    /** Add a given Tune/Chord to this Collection. */
    public void addTune(Tune tune){
        this.addChord(tune.channel, tune.chord);
    }

    /** Add the given Note to the given Channel in this Collection. */
    public void addNote(int channel, Note note){    
        this.tunes.get(channel).addNote(note);
    }

    /** Add the given Chord to the given Channel in this Collection. */
    public void addChord(int channel, Chord chord){    
        this.tunes.get(channel).addChord(chord.copy());
    }

    /** Stop playing notes and clear the Collection. */
    public void clear(){
        this.musicBox.playOff(this.tunes);
        this.clearTunes();
    }


    /** Clear all chords in this Collection */
    public void clearTunes(){
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            tune.clearChord();
        }
    }

    /** Stop playing the Notes/Chords when their duration has expired. */
    public void nextBeat(){
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            // Reduce durations and get the Notes to be stopped
            Chord stopPlay = tune.chord.nextBeat();

            // Stop playing those Notes
            this.musicBox.stopTune(new Tune(tune.channel, stopPlay));

            // Remove the silent notes from the Tune/Chord
            tune.removeSilent();
        } 
    }

    /** Start playing all Tunes in this Collection */
    public void playTunes(){
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            this.musicBox.playTune(tune);
        }
    }

    /** Make a deep copy of this TuneCollection */
    public TuneCollection copy(){
        TuneCollection newCopy = new TuneCollection(this.musicBox);
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            newCopy.addChord(tune.channel, tune.chord.copy());
        }
        return newCopy;
    }

    /** Computes the total number of Notes in this TuneCollection */
    public int size(){
        int count = 0;
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            count += tune.size();
        }
        return count;
    }

    /** Does this TunCollection contain the given Pitch on the given Channel? */
    public boolean contains(int channel, int pitch){
        return contains(channel, new Note(pitch));
    }

    /** Does this TunCollection contain the given Note on the given Channel? */
    public boolean contains(int channel, Note note){
        for(int i = 0; i < this.tunes.size(); i++){
            Tune tune = this.tunes.get(i);
            if (tune.channel == channel && tune.chord.containsNote(note))
                return true;
        }
        return false;
    }

    /** Produce a String representation of this TunCollection. */
    public String toString(){
        String res = "TuneCollection(" + this.size() + ")\n   ";
        for(Tune t : this.tunes)
            res += t + "\n   ";
        return res;
    }
}

