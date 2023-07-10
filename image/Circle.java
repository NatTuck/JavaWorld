/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Circle.java                                           *
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

package image;

/** 
 * <style type='text/css'><!--
 *    .com{ font-style: italic; color: #880000; }
 *    .keyw{ font-weight: bold; color: #000088; }
 *    .num{ color: #00AA00; }
 *    .str{ color: #CC00AB; }
 *    .prim{ color: #0000FF; }
 *    img.example{ padding-left: 50px; padding-bottom: 30px; }
 *  --></style>
 *
 * A Class representing a Circle Image.  When a <code>Circle</code> image is placed in
 *   a <code>Scene</code> its center point is taken as its location.
 *   
 * <pre>
 *    <span class='keyw'>new</span> Circle(<span class='num'>30</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/circle-1.png" />
 * <br />
 * <pre>
 *    <span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/circle-2.png" />
 * <br />
 */
public class Circle extends Ellipse{
    
    /** Construct a circle Image of the given (double) radius, mode, and color */
    public Circle(double radius, String mode, String color){
        super(2*radius, 2*radius, mode, color);
    }
    /** Construct a circle Image of the given (int) radius, mode, and color */
    public Circle(int radius, String mode, String color){
        super(2*radius, 2*radius, mode, color);
    }
}
