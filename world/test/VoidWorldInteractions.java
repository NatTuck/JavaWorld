/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/VoidWorldInteractions.java                       *
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

public class VoidWorldInteractions extends VoidWorld{
    // Simple Main Program
    public static void main(String[] args)
    { new VoidWorldInteractions().bigBang(); }


    public Scene onDraw() {
        return new EmptyScene(100, 100); // Gotta draw something...
    }

    public void onKey(String ke) {
        System.out.println("Pressed: " + ke);
    }   

    public void onRelease(String ke) {
        System.out.println("Released: " + ke);
    }
    
    public void onMouse(int x, int y, String me) {
        //System.out.println("Mouse: ("+x+", "+y+") \"" + me +"\"");
    }
}

