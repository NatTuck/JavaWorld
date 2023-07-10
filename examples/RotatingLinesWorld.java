/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./examples/RotatingLinesWorld.java                            *
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

package examples;

import image.*;
import world.World;

/** World OnTick and Line Test */
public class RotatingLinesWorld extends World{
    
    public static void main(String[] s){
        new RotatingLinesWorld(5.0).bigBang();
    }
    
    /** The current base angle of the lines */
    double ang;
    
    /** Construct a World with the given angle */
    RotatingLinesWorld(double ang){ this.ang = ang; }
    
    /** Draw two lines at the current base angle */
    public Scene onDraw(){
        Image over = new Line(Math.cos(this.ang)*80,
                              Math.sin(this.ang)*80, "blue")
                      .overlay(new Line(Math.cos(this.ang*2)*100,
                                        Math.sin(this.ang*2)*100, "red"));
        return new EmptyScene(300, 300).placeImage(over, 150, 150);
    }
    
    /** On Tick update the angle */
    public RotatingLinesWorld onTick(){
        if(this.ang > 2*Math.PI)
            return new RotatingLinesWorld(0);
        else
            return new RotatingLinesWorld(this.ang+Math.PI/180);
    }
}
