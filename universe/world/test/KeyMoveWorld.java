/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/world/test/KeyMoveWorld.java                       *
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

package universe.world.test;
import image.*;
import universe.Package;
import universe.world.World;
import universe.world.None;

/** World OnKey Test */
public class KeyMoveWorld extends World<None>{
    public static void main(String[] s){
        new KeyMoveWorld(new Posn(200,200))
             .bigBang();
    }
    /** The position of the dot */
    Posn p;
    /** Construct a new World with the given Dot */
    KeyMoveWorld(Posn p){ this.p = p; }
    /** Move the World by DX/DY */
    KeyMoveWorld move(int dx, int dy){
        return new KeyMoveWorld(this.p.move(dx,dy));
    }
    /** On an arrow key move the dot in the right direction */
    public Package<None> onKey(String ke){
        if(ke.equals("up"))return new Package<None>(this.move(0,-10));
        else if(ke.equals("down"))return new Package<None>(this.move(0, 10));
        else if(ke.equals("left"))return new Package<None>(this.move(-10, 0));
        else if(ke.equals("right"))return new Package<None>(this.move(10, 0));
        return new Package<None>(this);
    }
    /** Draw the dot into an EmptyScene */
    public Scene onDraw(){
        return this.p.draw(new EmptyScene(400,400));    
    }
}
/** Represents a 2D Position */
class Posn{
    /** The Coordinates of this Posn */
    int x,y;
    /** Construct a Posn from X/Y */
    Posn(int x, int y){ this.x = x; this.y = y; }
    /** Move this Posn */
    Posn move(int dx, int dy){
        return new Posn(this.x+dx, this.y+dy);
    }
    /** Draw a Dot at X/Y */
    Scene draw(Scene scn){
        return scn.placeImage(new Circle(10,"solid","red"), this.x, this.y);
    }
}

