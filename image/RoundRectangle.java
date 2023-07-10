/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/RoundRectangle.java                                   *
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
import java.awt.geom.RoundRectangle2D;

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
 * Represents an Image of a Rectangle with rounded corners.
 * 
 * <pre>
 *    <span class='keyw'>new</span> RoundRectangle(<span class='num'>40</span>, <span class='num'>20</span>, <span class='num'>10</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 * <img class="example" src="test/images/rndrectangle-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> RoundRectangle(<span class='num'>20</span>, <span class='num'>40</span>, <span class='num'>10</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/rndrectangle-2.png" />
 * <br />
 */
public class RoundRectangle extends Rectangle{
    /* The radius of this RoundRectangle's corners */
    protected double rx, ry;
    
    /** Create a Round Rectangle with (double) width, height, and corner radius, mode and color */
    public RoundRectangle(double width, double height, double rxy, String mode, String color){
        this(width,height,rxy,rxy,mode,color);
    }
    /** Create a Round Rectangle Image with (int) width, height, and corner radius, mode and color */
    public RoundRectangle(int width, int height, int rxy, String mode, String color){
        this(width,height,rxy,rxy,mode,color);
    }
    /** Create a Round Rectangle Image with (int) width, height, and corner radius, mode and color */
    public RoundRectangle(int width, int height, int rx, int ry, String mode, String color){
        this((double)width,height,rx,ry,mode,color);
    }
    /** Create a Round Rectangle with (double) width, height, and corner radius, mode and color */
    public RoundRectangle(double width, double height, double rx, double ry, String mode, String color){
        super(width,height,mode,color);
        this.rx = rx;
        this.ry = ry;
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.color);
        if(this.mode == OUTLINE)
            g2.draw(new RoundRectangle2D.Double(x-this.pinholeX,y-this.pinholeY,this.width,this.height, this.rx, this.ry));
        else
            g2.fill(new RoundRectangle2D.Double(x-this.pinholeX,y-this.pinholeY,this.width,this.height, this.rx, this.ry));
    }
}
