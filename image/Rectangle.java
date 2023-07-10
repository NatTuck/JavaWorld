/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Rectangle.java                                        *
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
import java.awt.geom.*;

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
 * Represents an Image of a Rectangle.
 *
 * <pre>
 *    <span class='keyw'>new</span> Rectangle(<span class='num'>40</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 * <img class="example" src="test/images/rectangle-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/rectangle-2.png" />
 * <br />
 *  
 */
public class Rectangle extends Image{
    double width;
    double height;
    int mode;
    Color color;
    
    /** Create a Rectangle Image with (double) width and height, mode and color */
    public Rectangle(double width, double height, String mode, String color){
        this(width,height,mode(mode),color(color));
    }
    /** Create a Rectangle Image with (int) width and height, mode and color */
    public Rectangle(int width, int height, String mode, String color){
        this(width,height,mode(mode),color(color));
    }
    /** Create a Rectangle with converted mode and color */
    private Rectangle(double width, double height, int mode, Color color){
        super(width/2, height/2);
        this.width = width;
        this.height = height;
        this.mode = mode;
        this.color = color;
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.color);
        if(this.mode == OUTLINE)
            g2.draw(new Rectangle2D.Double(x-this.width/2,y-this.height/2,this.width-1,this.height-1));
        else g2.fill(new Rectangle2D.Double(x-this.width/2,y-this.height/2,this.width-1,this.height-1));
    }
    /** Return the Width of this Image */
    public int width(){ return (int)this.width; }
    /** Return the Height of this Image */
    public int height(){ return (int)this.height; }
}
