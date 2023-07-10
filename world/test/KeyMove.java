/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/KeyMove.java                                     *
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

package world.test;
import image.*;
import world.BigBang;

/** OnKey Test */
public class KeyMove{
    public static void main(String[] s){
        new BigBang(new Posn(200,200))
             .onDraw(new DrawPosn())
             .onKey(new Key())
             .bigBang();
    }
}
/** OnKey Handler */
class Key{
    Posn apply(Posn w, String ke){
        if(ke.equals("up"))return w.move(0, -10);
        else if(ke.equals("down"))return w.move(0, 10);
        else if(ke.equals("left"))return w.move(-10, 0);
        else if(ke.equals("right"))return w.move(10, 0);
        return w;
    }
}
/** OnDraw Handler */
class DrawPosn{
    Scene apply(Posn w){
        return w.draw(new EmptyScene(400,400));
    }
}
