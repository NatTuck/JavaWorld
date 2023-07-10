/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Square.java                                           *
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
 * Represents an Image of a Square.
 *  
 * <pre>
 *    <span class='keyw'>new</span> Square(<span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"slateblue"</span>)</pre>
 * <img class="example" src="test/images/square-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Square(<span class='num'>50</span>, <span class='str'>"outline"</span>, <span class='str'>"darkmagenta"</span>)</pre>
 * <img class="example" src="test/images/square-2.png" />
 * <br />
 *   
 */
public class Square extends Rectangle{
    
    /** Create a Square Image with (double) size, mode and color */
    public Square(double size, String mode, String color){
        this(round(size),mode,color);
    }
    /** Create a Square Image with (int) size, mode and color */
    public Square(int size, String mode, String color){
        super(size,size,mode,color);
    }
}
