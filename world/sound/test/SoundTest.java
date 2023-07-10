/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/test/SoundTest.java                             *
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

// Import the SoundWorld
import world.sound.*;
// The Tunes Library
import world.sound.tunes.*;
// Image/Scenes
import image.*;

/** Quick test of the sound library/world: A Bouncing ball with sound. */
public class SoundTest extends SoundWorld{

    // The position and Velocity...
    double x = 150;
    double y = 150;
    double vx = 8;
    double vy = 0;
    int volume = 40;
    
    
    /** Draw the Ball */
    public Scene onDraw(){
        return new EmptyScene(300,300)
             .placeImage(new Circle(10, "solid", "red"), this.x, this.y);
    }
    
    /** Reasonable tick rate */
    public double tickRate(){
        return 0.03;
    }
    
    /** On Tick, move/bounce the ball and add a Sound */
    public void onTick(){
        this.x += this.vx;
        this.y += this.vy;
        this.vy += 2;
        
        // Play the sound when we hit a wall
        if(this.x >= 290 || this.x <= 10 || this.y >= 290){
            this.tickTunes.addNote(PERCUSSION, new Note(41, 8, this.volume));
        }
        // Change direction if/when we are off the screen
        // Right side
        if(this.vx > 0 && this.x > 290){
            this.vx = -this.vx;
            this.x = 289;
        }
        // Left side
        if(this.vx < 0 && this.x < 10){
            this.vx = -this.vx;
            this.x = 11;
        }
        // Bottom
        if(this.vy > 0 && this.y >= 290){
            this.vy = -this.vy * 0.9;
            this.y = 289;
        }
    }
    
    public void onKey(String ke){
        if(ke.equals("up") && this.volume < 100){
            this.volume += 10;
        }else if(ke.equals("down") && this.volume > 0){
            this.volume -= 10;
        }
    }
    
    /** Main Method... Java Runnable */
    public static void main(String[] args){
        new SoundTest().bigBang();
    }
    

}

