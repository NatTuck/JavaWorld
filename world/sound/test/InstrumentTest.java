/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/InstrumentTest.java                        *
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

/** Initialize the channels (instruments) to different values */
public class InstrumentTest extends SoundWorld{
    public static void main(String[] args){
        new InstrumentTest().bigBang();
    }
    
    // The inner Scene
    Scene scene = new EmptyScene(200, 200);
    // The current pitch to be played
    int pitch = NoteDownC;
    // Current Channel (instrument)
    int channel = 0;
    
    InstrumentTest(){
        musicBox.initChannels(
                AcousticGrandPiano,
                Xylophone,
                PercussiveOrgan,
                Harmonica,
                ElectricGuitar_jazz,
                GuitarHarmonics,
                AcousticBass,
                SlapBass_1,
                Cello,
                PERCUSSION,
                Timpani,
                SynthStrings_1,
                Trumpet, 
                MutedTrumpet,
                TenorSax,
                Recorder);
    }
    
    /** Draw by returning the inner Scene */
    public Scene onDraw(){
        return this.scene
            .placeImage(new Text("Pitch: "+this.pitch, 20, "black"), 70, 70)
            .placeImage(new Text("Channel: "+this.channel, 20, "black"), 70, 100)
            .placeImage(new Text("Name: "+
                    INSTRUMENT_NAMES.get(this.musicBox.getProgram(this.channel)), 10, "black"), 70, 130);
    }

    /** Key Down... add the note and add to the down keys */
    public void onKey(String key){
        // Up/Down to change the channel (instrument)
        if(key.equals("up")){
            this.channel = (this.channel+1) % 16;
        }else if(key.equals("down")){
            this.channel = (this.channel+15) % 16;
        }else if(key.equals("left")){
            this.pitch--;
        }else if(key.equals("right")){
            this.pitch++;
        }else{
            this.tickTunes.addNote(this.channel, new Note(this.pitch, 4, 100));
        }
    }
}
