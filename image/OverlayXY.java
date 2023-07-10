/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/OverlayXY.java                                        *
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

import java.awt.*;
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
 * Represents the Overlaying of two images after moving the bottom image by the given offset.
 *  
 * <pre>
 *    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
 *                <span class='num'>20</span>, <span class='num'>0</span>, <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>))</pre>
 * <img class="example" src="test/images/overlayxy-1.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
 *                <span class='num'>20</span>, <span class='num'>20</span>,
 *                <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>))</pre>
 * <img class="example" src="test/images/overlayxy-2.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
 *                <span class='num'>-20</span>, <span class='num'>-20</span>,
 *                <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>))</pre>
 * <img class="example" src="test/images/overlayxy-3.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> OverlayXY(
 *           <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
 *                 <span class='num'>10</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)),
 *                 <span class='num'>20</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>))</pre>
 * <img class="example" src="test/images/overlayxy-4.png" />
 * <br />
 * <br />
 *  
 * Image overlays are also available as a method on <code>Image</code>s.
 *
 * <pre>
 *    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)
 *        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>), <span class='num'>20</span>, <span class='num'>0</span>)</pre>
 * <img class="example" src="test/images/overlayxy-1.2.png" />
 * <br />
 *  
 * <pre>
 *    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>)
 *        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>20</span>, <span class='num'>20</span>)</pre>
 * <img class="example" src="test/images/overlayxy-2.2.png" />
 * <br />
 * 
 * <pre>
 *    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>)
 *        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>-20</span>, <span class='num'>-20</span>)</pre>
 *  <img class="example" src="test/images/overlayxy-3.2.png" />
 *  <br />
 *     
 * <pre>
 *    <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)
 *        .overlayxy(<span class='num'>20</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)
 *                               .overlayxy(<span class='num'>10</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>))</pre>
 * <img class="example" src="test/images/overlayxy-4.2.png" />
 * <br />
 *   
 */
public class OverlayXY extends Overlay{
    private double dx, dy;
    private double tx, ty;
    
    /** Construct an OverlayXY from the two images and the offset (X,Y) */
    public OverlayXY(Image top, int x, int y, Image bot){
        this(top,(double)x,y,bot);
    }   
    /** Construct an OverlayXY from the two images and the Posn offset */
    public OverlayXY(Image top, Posn p, Image bot){
        this(top,p.x,p.y,bot);
    }   
    /** Construct an OverlayXY from the two images and the offset (X,Y) */
    public OverlayXY(Image top, double x, double y, Image bot){
        super(top,bot,
                calcwidth(top,bot,x),
                calcheight(top,bot,y));
        this.dx = x;
        this.dy = y;
        // Where to draw the Top image...
        this.tx = -this.pinholeX+top.pinholeX;
        if(top.width() > bot.width()+this.dx)
            this.tx = this.pinholeX-top.pinholeX;
        //if(x < 0)this.tx += -x;
        
        this.ty = -this.pinholeY+top.pinholeY;
        if(top.height() > bot.height()+this.dy)
            this.ty = this.pinholeY-top.pinholeY;
        //if(y < 0)this.ty += -y;
        
    }
    
    private static double calcwidth(Image t, Image b, double dx){
        return Math.max(t.width(), b.width()+dx)-((dx < 0)?dx:0);
    }
    private static double calcheight(Image t, Image b, double dy){
        return Math.max(t.height(), b.height()+dy)-((dy < 0)?dy:0);
    }
    
    /** Draw this OverlayXY image into a Graphics */
    public void paint(Graphics g, int xx, int yy){
        this.bot.paint(g, round(xx+this.tx+this.dx+this.bot.pinholeX-this.top.pinholeX),
                round(yy+this.ty+this.dy+this.bot.pinholeY-this.top.pinholeY));
        this.top.paint(g, round(xx+this.tx), round(yy+this.ty));
    }
}

