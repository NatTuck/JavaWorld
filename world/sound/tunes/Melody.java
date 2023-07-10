/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/tunes/Melody.java                               *
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

/** Represents a collection of chords that can be played in sequence.
 * 
 *  Based in part on a class originally designed by Viera K. Proulx. */
public class Melody implements SoundConstants, Iterable<Chord>{

    /** The list of Chords to play */
    ArrayList<Chord> chords;

    /** Index for the current Chord to play */
    int current;

    /** The number of Chords in this melody */
    int size;

    /** Construct an melody with the given sequence of Chords. */
    public Melody(Chord ... chords){
        this(new ArrayList<Chord>(Arrays.asList(chords)));
    }

    /** Construct a melody from the given list of chords. */
    public Melody(ArrayList<Chord> chords){
        this.chords = chords;
        this.current = 0;
        this.size = this.chords.size();
    }

    /** Initialize the melody from a monotone sequence of notes */
    public Melody(Note ... notes){
        this(new ArrayList<Chord>());
        for(Note note : notes)
            this.chords.add(new Chord(note));
        this.size = this.chords.size();
    }

    /** Return an Iterator over the Chords in this Melody. */
    public Iterator<Chord> iterator(){
        return this.chords.iterator();
    }

}

