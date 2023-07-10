/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Triangle.java                                         *
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
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

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
 * Represents an Image of a Triangle.
 *
 * <pre>
 *    <span class='keyw'>new</span> Triangle(<span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"tan"</span>)</pre>
 * <img class="example" src="test/images/triangle-1.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> Triangle(<span class='num'>60</span>, <span class='str'>"outline"</span>, <span class='str'>"purple"</span>)</pre>
 * <img class="example" src="test/images/triangle-2.png" />
 * <br />
 *
 */
public class Triangle extends Image{
    double height;
    int mode;
    Color color;
    GeneralPath poly;
    
    /** Create a Triangle Image with (double) height, mode and color */
    public Triangle(double height, String mode, String color){
        this(height,mode(mode),color(color));
    }
    /** Create a Triangle Image with (int) height, mode and color */
    public Triangle(int height, String mode, String color){
        this(height,mode(mode),color(color));
    }
    /** Create a Rectangle with converted mode and color */
    private Triangle(double height, int mode, Color color){
        super(height/2, height/2);
        this.height = height;
        this.mode = mode;
        this.color = color;
        this.poly = new GeneralPath();
        this.poly.moveTo(0, -height/2);
        this.poly.lineTo(height/2, height/2);
        this.poly.lineTo(-height/2, height/2);
        this.poly.closePath();
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.color);
        Shape t = poly.createTransformedShape(AffineTransform.getTranslateInstance(x, y));
        if(this.mode == OUTLINE)
            g2.draw(t);
        else
            g2.fill(t);
    }
    /** Return the Width of this Image */
    public int width(){ return round(this.height); }
    /** Return the Height of this Image */
    public int height(){ return round(this.height); }
}
