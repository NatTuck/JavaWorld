/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/RegularPolygon.java                                   *
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
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Arrays;

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
 * Represents an Image of a Regular Polygon, e.g., a Square, Pentagon, Octagon, etc.
 *     
 * <pre>
 *    <span class='keyw'>new</span> RegularPolygon(<span class='num'>50</span>, <span class='num'>3</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/polygon-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> RegularPolygon(<span class='num'>40</span>, <span class='num'>4</span>, <span class='str'>"outline"</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/polygon-2.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> RegularPolygon(<span class='num'>20</span>, <span class='num'>8</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/polygon-3.png" />
 * <br />
 *
 */
public class RegularPolygon extends Image{
    protected int width;
    protected int height;
    protected int mode;
    protected Color color;
    protected Polygon poly;
    
    /** Create a Regular Polygon Image with (double) radius, sides, mode and color */
    public RegularPolygon(double radius, int sides, String mode, String color){
        this(round(radius),sides,mode,color);
    }
    /** Create a Regular Polygon Image with (int) radius, sides, skip, mode and color */
    public RegularPolygon(int radius, int sides, String mode, String color){
        this(radius,sides,mode(mode),color(color));
    }
    /** Create a Regular Polygon Star with converted mode and color */
    protected RegularPolygon(int radius, int sides, int mode, Color color){
        this(make(radius, sides), mode, color);
    }
    private static Polygon make(int radius, int sides){
        Polygon poly = new Polygon();
        double first = (sides%2==0)?Math.PI/sides:3*Math.PI/2,
               mult = 2*Math.PI/sides;
        for(int i = 0; i < sides; i++){
            poly.addPoint(round(radius*Math.cos(first+i*mult)),
                          round(radius*Math.sin(first+i*mult)));
        }
        return poly;
    }
    /** Create a Regular Polygon Star with converted mode and color */
    protected RegularPolygon(Polygon poly, int mode, Color color){
        super(0,0);
        this.mode = mode;
        this.color = color;
        this.poly = poly;
        java.awt.Rectangle r = poly.getBounds();
        this.width = r.width;
        this.height = r.height;
        this.pinholeX = this.width/2;
        this.pinholeY = this.height/2;
        poly.translate(-(r.x+(r.width+r.x))/2,
                -(r.y+(r.height+r.y))/2);
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        g.setColor(this.color);
        Polygon t = new Polygon(Arrays.copyOf(this.poly.xpoints, this.poly.npoints),
                Arrays.copyOf(this.poly.ypoints, this.poly.npoints), this.poly.npoints);
        t.translate(x, y);
        if(this.mode == OUTLINE)g.drawPolygon(t);
        else g.fillPolygon(t);
    }
    /** Return the Width of this Image */
    public int width(){ return this.width; }
    /** Return the Height of this Image */
    public int height(){ return this.height; }
}
