/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/RotatingLines.java                               *
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

/** OnTick and Line Test */
public class RotatingLines{
    public static void main(String[] s){
        new BigBang(5.0)
             .onDraw(new Lines())
             .onTick(new LineTick())
             .bigBang();
    }
}
/** On Draw */
class Lines{
    Scene apply(double w){
        Image over = new Line(Math.cos(w)*80,Math.sin(w)*80, "blue")
                        .overlay(new Line(Math.cos(w*2)*100,Math.sin(w*2)*100, "red"));
        return new EmptyScene(300, 300).placeImage(over, 150, 150);
    }
}
/** On Tick update the angle */
class LineTick{
    double apply(double w){
        if(w > 2*Math.PI) return 0;
        else return w+Math.PI/180;
    }
}
