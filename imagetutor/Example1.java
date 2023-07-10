/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./imagetutor/Example1.java                                    *
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

package imagetutor;

import image.*;
import world.*;

/** Example #1 for Image tutorial */
public class Example1{
    Example1(){}

    Scene mt = new EmptyScene(100, 100);
    Image circ = new Circle(20, "outline", "blue");
    Image sqr = new Square(40, "solid", "palegreen");
    Scene scn = mt.placeImage(new OverlayXY(circ, -20, 20, sqr), 50, 50);

    public static void main(String[] args){
        World.display(new Example1().scn);
    }
}

