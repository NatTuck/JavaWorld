/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Ellipse.java                                          *
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
 * Represents an Image of an Ellipse.
 * 
 * <pre>
 *    <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 * <img class="example" src="test/images/ellipse-1.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/ellipse-2.png" />
 * <br />
 * 
 */
public class Ellipse extends Rectangle{
    
    /** Create a Ellipse Image with (double) width and height, mode and color */
    public Ellipse(double width, double height, String mode, String color){
        super(width,height,mode,color);
    }
    /** Create a Ellipse Image with (int) width and height, mode and color */
    public Ellipse(int width, int height, String mode, String color){
        super(width,height,mode,color);
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.color);
        if(this.mode == OUTLINE)
            g2.draw(new Ellipse2D.Double(x-this.pinholeX,y-this.pinholeY,this.width,this.height));
        else
            g2.fill(new Ellipse2D.Double(x-this.pinholeX,y-this.pinholeY,this.width,this.height));
    }
}
