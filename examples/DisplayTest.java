/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./examples/DisplayTest.java                                   *
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

import world.World;
import image.*;

public class DisplayTest{
    public static void main(String[] args){
        //World.display(new DisplayTest().simpleImage());
        //World.display(new DisplayTest().circleStart());
        //World.display(new DisplayTest().spiralStart());
        World.display(new DisplayTest().treeStart());
    }
    
    // Create a simple Star in the empty Scene 
    Image simpleImage(){
        return new EmptyScene(200, 200)
                     .placeImage(new Overlay(new Star(50, 30, 10, "outline", "black"),
                                             new Star(50, 30, 10, "solid", "green")),
                                 100, 100);
    }
    // The next size... shrinks the circles
    double nextSize(double size){
        return size*2/3;
    }
    // Start off the spiral with the right angle, 
    Scene circleStart(){
        return this.circles(100, 60, new EmptyScene(440, 160));
    }
    // Create a scene by drawing decreasing circles into the given Scene
    Scene circles(double x, double size, Scene scn){
        if(size < 1){
            return scn;
        }else{
            return this.circles(x+size+nextSize(size), nextSize(size), scn)
                       .placeImage(new Circle(size, "outline", "blue"), x, 80);
        }
    }
    
    // Start off the spiral with a decent x/y/angle 
    Scene spiralStart(){
        return this.spiral(100, 80, -Math.PI/10, 60, new EmptyScene(400, 160));
    }
    
    // Create a shrinking spiral at the given x, y, angle, and size 
    //    in the given Scene 
    Scene spiral(double x, double y, double ang, double size, Scene scn){
        if(size < 1){
            return scn;
        }else{
            return this.spiral(x+(size+nextSize(size))*Math.cos(ang),
                               y+(size+nextSize(size))*Math.sin(ang),
                               ang+Math.PI/10, nextSize(size), scn)
                       .placeImage(new Circle(size, "outline", "blue"), x, y);
        }
    }
    
    // Add a line of the given len from x/y in the direction of ang to the given Scene
    Scene putLine(double x, double y, double ang, double len, String color, Scene scn){
        return scn.addLine(x, y,x+len*Math.cos(ang), y+len*Math.sin(ang), color);
    }

    // Start off the tree
    Scene treeStart(){
        return this.tree(40, 160, 0, 150, new EmptyScene(300, 300));
    }

    // Create a shrinking spiral at the given x, y, angle, and size 
    //    in the given Scene 
    Scene tree(double x, double y, double ang, double len, Scene scn){
        if(len <= 3){
            return this.putLine(x, y, ang, len, "green", scn);
        }else{
            return this.putLine(x, y, ang, len, "brown",
                       this.tree(x + len/3 * Math.cos(ang),
                                 y + len/3 * Math.sin(ang),
                                 ang + Math.PI/6, 2*len/3,
                                 this.tree(x + 2*len/3 * Math.cos(ang),
                                           y + 2*len/3 * Math.sin(ang),
                                           ang - Math.PI/6, 2*len/3, scn)));
        }
    }
}
