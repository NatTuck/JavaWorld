/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/tunes/Note.java                                 *
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

/** Represents a single note which includes a pitch and duration.  Notes may
 *    be created/converted to and from a String representation that includes
 *    the <tt>name</tt>, <tt>octave</tt>, <tt>modifier</tt>, and
 *    <tt>duration</tt>, where: 
 *   <ul>
 *     <li> Name is one of <i class='str'>A</i>, <i class='str'>B</i>,
 *     <i class='str'>C</i>, <i class='str'>D</i>, <i class='str'>E</i>,
 *     <i class='str'>F</i>, or <i class='str'>G</i></li>
 *     <li> Octave is the keyboard octave: a single digit</li>
 *     <li> Modifier is one of:
 *         <ul>
 *           <li><i class='str'>n</i> : natural,</li> 
 *           <li><i class='str'>s</i> : sharp,</li> 
 *           <li><i class='str'>f</i> : flat</li>
 *         </ul>
 *     </li>
 *     <li> Duration is the number of <i>beats</i> in the range [0..16]</li>
 *   </ul>
 * 
 * <p>
 *    As an example, <tt class='str'>"C6n4"</tt> represents a C natural, in
 *    the 6th octave (Note 72), that will play for 4 beats
 * </p>
 *   
 *  Based in part on a class originally designed by Viera K. Proulx. 
 */
public class Note{
    /** The default velocity of Notes */
    static int DEFAULT_VELOCITY = 60;

    /** The <code>MIDI</code> pitch of this Note. */
    private int pitch;

    /** The duration of this Note. Typically a maximum of 16 beats for
     *    a whole note in a 4/4 measure (though this depends on the tempo) */
    private int duration;

    /** A letter that represents the name of this Note ('A'..'G') */
    private char noteName;

    /** This Note's modifier: 's' = sharp, 'f' = flat, 'n' = natural */
    private char modifier;

    /** The <code>MIDI</code> octave on a piano keyboard (0 - 8) */
    private int octave;

    /** The velocity (volume) of this note. */
    private int velocity;
    
    /** The distance of Notes from the note 'A'. */
    private static int[] OFFSETS = new int[]{0, 2, 3, 5, 7, 8, 10};

    /** The names of notes; lower case letters denote a 'sharp' modifier.
     *    The first 'E' Note, as in "E0", is MIDI note 0 which is unused. */
    private static String OFFSET_NAMES = "EFfGgAaBCcDd";

    /** Create a Note with the given MIDI pitch, with a default duration
     *    of 1 and a default velocity. */
    public Note(int pitch){
        this(pitch, 1);
    }
    
    /** Create a Note with the given MIDI pitch and duration, and
     *    the default velocity. */
    public Note(int pitch, int duration){
        this(pitch, duration, DEFAULT_VELOCITY);
    }  
    
    /** Create a Note with the given MIDI pitch, duration, and velocity. */
    public Note(int pitch, int duration, int velocity){
        this.pitch = Note.pitch(pitch);
        this.duration = Note.duration(duration);
        this.velocity = velocity;
        
        if(pitch == 0){
            this.octave = 0;
            this.noteName = 0;
            this.modifier = 'n';
        }else{
            this.octave = Note.pitchToOctave(pitch);
            this.noteName = OFFSET_NAMES.charAt((pitch + 8) % 12);
            if(Character.isLowerCase(this.noteName)){
                this.modifier = 's';
                this.noteName = Character.toUpperCase(this.noteName);
            }else
                this.modifier = 'n';
        }
    }  

    /** Create a Note from the description given as a String, and the
     *    default velocity. */
    public Note(String snote){
        this(snote, DEFAULT_VELOCITY);
    }
        
    /** Create a Note from the description given as a String. The encoding
     *    is either 4 or 5 characters long and  includes
     *    the <tt>name</tt>, <tt>octave</tt>, <tt>modifier</tt>, and
     *    <tt>duration</tt>, where: 
     *   <ul>
     *     <li> Name is one of <i class='str'>A</i>, <i class='str'>B</i>,
     *     <i class='str'>C</i>, <i class='str'>D</i>, <i class='str'>E</i>,
     *     <i class='str'>F</i>, or <i class='str'>G</i></li>
     *     <li> Octave is the keyboard octave: a single digit</li>
     *     <li> Modifier is one of:
     *         <ul>
     *           <li><i class='str'>n</i> : natural,</li> 
     *           <li><i class='str'>s</i> : sharp,</li> 
     *           <li><i class='str'>f</i> : flat</li>
     *         </ul>
     *     </li>
     *     <li> Duration is the number of <i>beats</i> in the range [0..16]</li>
     *   </ul>
     * 
     * <p>
     *    As an example, <tt class='str'>"C6n4"</tt> represents a C natural, in
     *    the 6th octave (Note 72), that will play for 4 beats
     * </p> */
    public Note(String snote, int velocity){
        this.velocity = Note.velocity(velocity);
        this.noteName = Note.noteName(snote.charAt(0));
        this.modifier = Note.modifier(snote.charAt(2));
        this.octave = Note.octave(snote.charAt(1)-'0');
        this.duration = snote.charAt(3)-'0';
        if(snote.length() == 5)
            this.duration = 10 * this.duration + (snote.charAt(4)-'0');
        this.pitch = Note.computePitch(this.noteName, this.octave, this.modifier);
    }

    /** Create a Note by copying the given Note. */
    private Note(Note that){
        this(that.pitch, that.duration, that.velocity);
    }
    
    /** Make a copy of this Note. */
    public Note copy(){ return new Note(this); }

    /** Get the Pitch of this Note. */
    public int getPitch(){ return this.pitch; }

    /** Get the Duration of this Note. */
    public int getDuration(){ return this.duration; }
    
    /** Get the Velocity of this Note. */
    public int getVelocity(){ return this.velocity; }

    /** Does the given Note represent the same Note as this one? */
    public boolean sameNote(Note that){
        return (this.pitch == that.pitch &&
                this.duration == that.duration);
    }

    /** Has this note completed its duration? */
    public boolean isSilent(){ return this.duration == 0; }

    /** Decrement the duration of this Note. */
    public void nextBeat(){
        if (this.duration > 0)
            this.duration--;
        else
            this.duration = 0;
    }

    /** Increment the duration of this Note. */
    public void skipBeat(){ this.duration++; }

    /** Produce a Human readable String representation of this Note. */
    public String toString(){
        return ("Note(\"" + this.stringRep() + "\" pitch: " + this.pitch + 
                ", duration: " + this.duration + ", velocity: " + this.velocity+")");
    }

    /** Return the MIDI String representation of this Note. */
    private String stringRep(){
        return "" + this.noteName + this.octave + this.modifier + this.duration;
    }

    /** Adjust the given pitch to be in [0..128] */
    private static int pitch(int pitch){
        return Math.min(Math.max(pitch, 0), 128);
    }

    /** Adjust the given duration to be in [0..16] */
    private static int duration(int duration){
        return Math.min(Math.max(duration, 0), 16);
    }

    /** Adjust the given velocity to be in [0..100] */
    private static int velocity(int velocity){
        return Math.min(Math.max(velocity, 0), 100);
    }
    
    /** Make sure the given name is a valid Note name. */
    private static char noteName(char name){
        if (name == 'A' || name == 'B' || name == 'C' || name == 'D' ||
            name == 'E' || name == 'F' || name == 'G')
            return name;
        return 'A';
    }

    /** Make sure the given modifier is a valid Note modifier. */
    private static char modifier(char mod){
        if (mod == 'n' || mod == 's' || mod == 'f')
            return mod;
        return 'n';
    }

    /** Make sure the given octave is a valid Note octave. */
    private static int octave(int oct){
        return Math.min(Math.max(oct, 0), 8);
    }

    /** Compute the MIDI pitch of the given note name, octave, and modifier. */
    public static int computePitch(char noteName, int octave, char modifier){
        int base = OFFSETS[noteName - 'A'];
        int note = base + 12 * octave + 9;
        if(modifier == 's')
            return note + 1;
        else if (modifier == 'f')
            return note - 1;
        return note;
    }
    
    /** Convert a given Pitch number to it's Octave */
    private static int pitchToOctave(int pitch){
        if(pitch == 0)return 0;
        return (pitch - 8) / 12;
    }
}
