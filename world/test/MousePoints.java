/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/MousePoints.java                                 *
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

/** Mouse point/click Test */
public class MousePoints{
    public static void main(String[] s){
        new BigBang(new EmptyScene(400,400))
              .onDraw(new IdScene())
              .onMouse(new MouseClicks())
              .bigBang();
    }
}
/** ID function for Scenes */
class IdScene{
    Scene apply(Scene w){ return w; }
}
/** Handle Mouse Clicks */
class MouseClicks{
    Scene apply(Scene w, int x, int y, String me){
        if(!me.equals("button-down"))
            return w;
        else
            return w.placeImage(new Circle(20, "solid","red")
                          .overlay(new Circle(20, "outline", "blue")), x, y);
    }
}
