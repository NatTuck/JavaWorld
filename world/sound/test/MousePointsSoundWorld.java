/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/MousePointsSoundWorld.java                 *
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

// Mouse SoundWorld point/click Test (with sounds)
public class MousePointsSoundWorld extends SoundWorld{
    // Simple Main Program
    public static void main(String[] args)
    { new MousePointsSoundWorld().bigBang(); }
    
    // The inner Scene
    Scene scene = new EmptyScene(200, 200);
    // The current pitch to be played
    int pitch = NoteDownC;

    // Create a new World
    MousePointsSoundWorld(){}
    
    // Draw by returning the inner Scene
    public Scene onDraw(){ return this.scene; }

    // On a mouse click add a circle to the inner Scene, increment the
    //    current pitch and play a short note
    public void onMouse(int x, int y, String me){
        if(me.equals("button-down")){
            this.pitch++;
            this.tickTunes.addNote(WOOD_BLOCK, new Note(this.pitch, 1));
            this.scene = this.scene.placeImage(
                             new Circle(20, "solid", "red")
                                   .overlay(new Circle(20, "outline", "black")), x, y);
        }
    }
}

