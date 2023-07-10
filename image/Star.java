/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Star.java                                             *
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

import java.awt.Color;
import java.awt.Polygon;

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
 * Represents an Image of a Star. There are several constructors to support customization
 * with number of points, and inner/outer radius.
 * 
 * <pre>
 *    <span class='keyw'>new</span> Star(<span class='num'>40</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"gray"</span>)</pre>
 * <img class="example" src="test/images/star-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Star(<span class='num'>30</span>, <span class='num'>7</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/star-2.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Star(<span class='num'>40</span>, <span class='num'>30</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"cornflowerblue"</span>)</pre>
 * <img class="example" src="test/images/star-3.png" />
 * <br />
 * 
 */
public class Star extends RegularPolygon{
    private static double RadScale = 0.4;
    
    /** Create a Star Image with (double) radius, mode and color */
    public Star(double radius, String mode, String color){
        this(radius,radius*RadScale,5,mode,color);
    }
    /** Create a Star Image with (int) radius, mode and color */
    public Star(int radius, String mode, String color){
        this(radius,radius*RadScale,5,mode,color);
    }
    /** Create a Star Image with (double) radius, sides, mode and color */
    public Star(double radius, int sides, String mode, String color){
        this(radius,radius*RadScale,sides,mode,color);
    }
    /** Create a Star Image with (int) radius, sides, mode and color */
    public Star(int radius, int sides, String mode, String color){
        this(radius,radius*RadScale,sides,mode,color);
    }
    /** Create a Star Image with (double) radius, inner-radius, sides, mode and color */
    public Star(double radius, double innerRad, int sides, String mode, String color){
        this(round(radius),round(innerRad),sides,mode,color);
    }
    /** Create a Star Image with (int) radius, inner-radius, sides, mode and color */
    public Star(int radius, int innerRad, int sides, String mode, String color){
        this(radius,innerRad,sides,mode(mode),color(color));
    }
    /** Create a Star with converted mode and color */
    protected Star(int radius, int innerRad, int sides, int mode, Color color){
        super(make(radius, innerRad, sides), mode, color);
    }
    private static Polygon make(int radius, double inrad, int sides){
        Polygon poly = new Polygon();
        double first = 3*Math.PI/2,
               mult = Math.PI/sides;
        
        for(int i = 0; i < sides*2; i+=2){
            poly.addPoint(round(radius*Math.cos(first+i*mult)),
                    round(radius*Math.sin(first+i*mult)));
            poly.addPoint(round(inrad*Math.cos(first+(i+1)*mult)),
                    round(inrad*Math.sin(first+(i+1)*mult)));
        }
        
        return poly;
    } 
    
}
