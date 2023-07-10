/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Line.java                                             *
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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.*;

import world.Posn;


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
 * Class representing a Line from (0,0) to the given (X,Y).
 *
 * <pre>
 *    <span class='keyw'>new</span> Line(<span class='num'>30</span>, <span class='num'>30</span>, <span class='str'>"black"</span>)</pre>
 * <img class="example" src="test/images/line-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Line(<span class='num'>-30</span>, <span class='num'>20</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/line-2.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Line(<span class='num'>30</span>, <span class='num'>-20</span>, <span class='str'>"red"</span>)</pre>
 * <img class="example" src="test/images/line-3.png" />
 * <br />
 *
 */
public class Line extends Image{
    double x;
    double y;
    Color color;

    /** Create a Line from (0,0) to doubles (X,Y) of the given color */
    public Line(double x, double y, String color){
        super(Math.abs(x/2),Math.abs(y/2));
        this.x = Math.abs(x);
        // Make sure Y is the only possible negative...
        if(x*y >= 0){
            this.y = Math.abs(y);
        }else{
            this.y = -Math.abs(y);
        }
        this.color = color(color);
    }
    /** Create a Line from (0,0) to ints (X,Y) of the given color */
    public Line(int x, int y, String color){
        this((double)x, y,color);
    }
    /** Create a Line from (0,0) to doubles (X,Y) of the given color */
    public Line(Posn p, String color){
        this(p.x,p.y,color);
    }    
    /** Draw this image into a Graphics */
    public void paint(Graphics g, int xx, int yy){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.color);
        // If Y is negative, we flip the line...
        if(this.y >= 0)
            g2.draw(new Line2D.Double(xx-this.pinholeX,yy-this.pinholeY,xx+this.pinholeX,yy+this.pinholeY));
        else
            g2.draw(new Line2D.Double(xx-this.pinholeX,yy+this.pinholeY,xx+this.pinholeX,yy-this.pinholeY));
    }
    /** Return the width of this Line Image */
    public int width(){ return (int)(Math.abs(this.x)+1); }
    /** Return the height of this Line Image */
    public int height(){ return (int)(Math.abs(this.y)+1); }
}

