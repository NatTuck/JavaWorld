/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/StopTest.java                                    *
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
import world.VoidWorld;

/** Simple Test of World/display for documentation */
public class StopTest{
    
    public static class MousePointsWorld extends VoidWorld{
        public static void main(String[] args){
            new MousePointsWorld(new EmptyScene(200, 200)).bigBang();
        }
        
        int done = 10;
        Scene scene;
 
        MousePointsWorld(Scene scene){
            this.scene = scene;    
        }
 
        public Scene onDraw(){ return this.scene; }
 
        public void onMouse(int x, int y, String me){
            if(me.equals("button-down")){
                this.done--;
                this.scene = this.scene.placeImage(new Circle(20, "solid", "red")
                                                   .overlay(new Circle(20, "outline", "black")), x, y)
                                       .placeImage(new Rectangle(30, 30, "solid", "white")
                                                   .overlay(new Text(this.done+"", 20, "blue")), 30, 30);
            }
        }
        public boolean stopWhen(){ return this.done < 0; }
        public void onKey(String me){
            System.out.println("HERE!!");
        }
    }
}

