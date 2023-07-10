/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/world/test/MousePointsWorld.java                   *
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
import universe.world.*;

/** Mouse World/Universe point/click Test */
public class MousePointsWorld extends World<None>{
    public static void main(String[] s){
        new MousePointsWorld(new EmptyScene(400,400))
              .bigBang();
    }
    /** The inner Scene */
    Scene scene;
    /** Create a new World with the given Scene */
    MousePointsWorld(Scene scene){
        this.scene = scene;    
    }
    /** Draw by returning the inner Scene */
    public Scene onDraw(){ return this.scene; }
    /** On a mouse click add a circle to the inner Scene */
    public Package<None> onMouse(int x, int y, String me){
        if(!me.equals("button-down"))
            return new Package<None>(this);
        else
            return new Package<None>(
                    new MousePointsWorld(
                            this.scene.placeImage(new Circle(20, "solid","red")
                                      .overlay(new Circle(20, "outline", "blue")), x, y)));
    }
}
