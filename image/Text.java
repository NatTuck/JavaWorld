/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Text.java                                             *
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
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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
 * A Class representing an Image of a String.
 * 
 * <pre>
 *    <span class='keyw'>new</span> Text(<span class='str'>"Hello"</span>, <span class='num'>24</span>, <span class='str'>"olive"</span>)</pre>
 * <img class="example" src="test/images/text-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> Text(<span class='str'>"Goodbye"</span>, <span class='num'>36</span>, <span class='str'>"indigo"</span>)</pre>
 * <img class="example" src="test/images/text-2.png" />
 * <br />
 * 
 */
public class Text extends Image{
    static Graphics2D context;
    static{
        context = (Graphics2D)(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB).getGraphics());
        context.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        context.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
    }
    
    Font font;
    protected String str;
    protected int size;
    protected Color color;
    protected double height, width;
    
    /** Construct a text Image of the given String, size, and color */
    public Text(String str, int size, String color){
        super(0,0);
        this.str = str;
        this.size = size;
        this.color = color(color);
        this.font = new Font(Font.SERIF, Font.PLAIN, size);
        Rectangle2D rect = this.font.getStringBounds(str, context.getFontRenderContext());
        this.width = round(rect.getWidth());
        java.awt.font.LineMetrics lm = this.font.getLineMetrics(str, context.getFontRenderContext());
        this.height = Math.abs(lm.getAscent())+Math.abs(lm.getDescent());
        this.pinholeX = round(this.width/2);
        this.pinholeY = Math.abs(lm.getAscent());
        
    }
    /** Construct a text Image of the given String, size, and color */
    public Text(String str, double size, String color){
        this(str, round(size), color);
    }
    /** Paint this Image into the given graphics */
    public void paint(Graphics g, int x, int y){
        g.setColor(this.color);
        g.setFont(this.font);
        g.drawString(this.str, round(x-this.pinholeX), round(y));
    }
    /** Return the Width of this Image */
    public int width(){ return round(this.width); }
    /** Return the Height of this Image */
    public int height(){ return round(this.height); }
}
