/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/Falling.java                                     *
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

/** Falling Balls Demo.  When the mouse is dragged, balls are created
 *     with a random upward velocity and then fall off the bottom of
 *     the screen */
public class Falling{
    public static void main(String[] s){
        new BigBang(new ELoB())
            .onDraw(new DrawPoints())
            .onMouse(new AddPoint())
            .onTick(new FallTick(), 0.04)
            .bigBang();
    }
}
/** Function class to add a new Ball on mouse drag */
class AddPoint extends Random{
    LoB apply(LoB w, int x, int y, String me){
        if(!me.equals("drag"))return w;
        return new CLoB(randBall(x,y), w);
    }
}
/** Function Class to draw the World */
class DrawPoints{
    Scene apply(LoB w){ return w.draw(new EmptyScene(400,400)); }
}
/** Function Class to move, gravitize, and filter the List of Balls */
class FallTick{
    LoB apply(LoB w){ return w.gravity().move().filter(); }
}
