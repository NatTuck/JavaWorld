/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/KeySoundWorld.java                         *
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
import world.sound.SoundWorld;
import world.sound.tunes.Note;
import java.util.*;

/** React to key events to play a simple keyboard using
 *    'a' through 'k' for white keys and
 *    'w'/'e'/'t'/'y'/'u' as black keys. */
public class KeySoundWorld extends SoundWorld{
    public static void main(String[] args){
        new KeySoundWorld().bigBang();
    }
    
    // Which Piano keys (note values) are currently pressed.
    //   Used for drawing the keys red instead of white/black 
    HashSet<Integer> down = new HashSet<Integer>();
    // Mapping of the keys to note offsets (in the current octave)
    HashMap<String, Integer> keys = new HashMap<String, Integer>();
    // Mapping of the drawing order to Note values 
    HashMap<Integer, Integer> draw = new HashMap<Integer, Integer>();
    // Base note... sets the octave
    int base = 36;
    // Use channel 1 to start, up/down keys change this
    int channel = 1;
    
    // Initialize all the Maps
    KeySoundWorld(){
        // Letter to note values 
        this.keys.put("a", 0);
        this.keys.put("w", 1);
        this.keys.put("s", 2);
        this.keys.put("e", 3);
        this.keys.put("d", 4);
        this.keys.put("f", 5);
        this.keys.put("t", 6);
        this.keys.put("g", 7);
        this.keys.put("y", 8);
        this.keys.put("h", 9);
        this.keys.put("u", 10);
        this.keys.put("j", 11);
        this.keys.put("k", 12);
        
        // Drawing order to note values (white keys first)
        this.draw.put(0,  0);
        this.draw.put(8,  1);
        this.draw.put(1,  2);
        this.draw.put(9,  3);
        this.draw.put(2,  4);
        this.draw.put(3,  5);
        this.draw.put(11, 6);
        this.draw.put(4,  7);
        this.draw.put(12, 8);
        this.draw.put(5,  9);
        this.draw.put(13, 10);
        this.draw.put(6,  11);
        this.draw.put(7,  12);
    }
    
    /** Draw the traditional piano keys */
    public Scene onDraw(){
        Scene acc = new EmptyScene(400, 200);
        // White keys first
        for(int i = 0; i < 8; i++)
            acc = acc.placeImage(new Rectangle(40, 120, mode(i), color(i)), 60+i*40, 100);
        
        // Black keys... skip "2" since there is a gap
        for(int i = 0; i < 6; i++){
            if(i != 2)
                acc = acc.placeImage(new Rectangle(30, 60, "solid", color(8+i)), 80+i*40, 60);
        }
        return acc;
    }
    /** Use the color red if the key is down */
    String color(int i){
        if(this.down.contains(this.draw.get(i)))
            return "red";
        return "black";
    }
    /** Paint solid if the key is down or it's a black key */
    String mode(int i){
        if(i >= 8 || this.down.contains(this.draw.get(i)))
            return "solid";
        return "outline";
    }
    
    /** Key Down... add the note and add to the down keys */
    public void onKey(String key){
        // One of the Piano keys
        if(this.keys.containsKey(key) && !down.contains(this.keys.get(key))){
            this.keyTunes.addNote(this.channel, new Note(this.base+this.keys.get(key), 8));
            this.down.add(this.keys.get(key));
        }
        // Up/Down to change the channel (instrument)
        if(key.equals("up")){
            this.channel++;
        }else if(key.equals("down")){
            this.channel--;
        }
    }
    /** Lift the key */
    public void onRelease(String key){
        if(this.keys.containsKey(key)){
            this.down.remove(this.keys.get(key));
        }
    }
}

