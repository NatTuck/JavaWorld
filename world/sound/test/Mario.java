/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/Mario.java                                 *
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

package world.sound.test; 

import image.*;
import world.sound.*;
import world.sound.tunes.*;
import java.util.*;

/** Simple class that demonstrates playing a melody... a simpler
 *    version of the classic Nintendo soundtrack */
public class Mario extends SoundWorld{

    /** When created... reset the iterator */
    Mario(){
        for(int p : this.themeInts){
            this.theme.add(new Note(p, 1));
        }
        this.reset();
    }
    
    /** Medium tick-rate */
    public double tickRate(){ return 0.2; }
    /** Draw the beat number in the center of the Scene */
    public Scene onDraw(){
        return new EmptyScene(100, 100)
                .placeImage(new Text(""+num, 20, "black"), 50, 60);
    }
    /** Loop if needed, move to the next note, and increase the number. */
    public void onTick(){
        if(!this.notes.hasNext())
            this.reset();
        this.num++;
        Note n = this.notes.next();
        if(n.getPitch() != 0)
            this.tickTunes.addNote(ORGAN, n);
    }
    /** Reset the iterator to the beginning of the Notes */
    void reset(){
        this.notes = this.theme.iterator();
    }
    /** Counting the beats... */
    int num;
    /** The rest of the notes to be played */
    Iterator<Note> notes;
    /** The underlying notes.  We use the Chord constructor to generate a
     *    list of notes from a list of integers (0 means no note, others
     *    come from the {@link world.sound.tunes.SoundConstants SoundConstants}
     *    interface. */
    ArrayList<Note> theme = new ArrayList<Note>();
    List<Integer> themeInts = Arrays.asList( 
            0,0,0,0,
            NoteE,
            NoteE,0,
            NoteE,0,
            NoteC,
            NoteE,0,
            NoteG,0,0,0,
            NoteDownG,0,0,0,
            
            NoteC,0,0,
            NoteDownG,0,0,
            NoteDownE,0,0,
            NoteDownA,0,
            NoteDownB,0,
            NoteDownAp,0,
            NoteDownA,
            NoteDownG,
            NoteE,0,
            NoteG,
            NoteA,0,
            NoteF,
            NoteG,0,
            NoteE,0,
            NoteC,0,
            NoteD,
            NoteDownB,0,
            
            NoteC,0,0,
            NoteDownG,0,0,
            NoteDownE,0,0,
            NoteDownA,0,
            NoteDownB,0,
            NoteDownAp,0,
            NoteDownA,
            NoteDownG,
            NoteE,0,
            NoteG,
            NoteA,0,
            NoteF,
            NoteG,0,
            NoteE,0,
            NoteC,0,
            NoteD,
            NoteDownB,0,0,0,0,0);

    /** Simple Main method (that Java runs) to start the animation/sound */
    public static void main(String[] argv){
        // Create the world and start the animation
        new Mario().bigBang();
    }
}

