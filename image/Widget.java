/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Widget.java                                           *
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
import javax.swing.JComponent;

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
 * Represents a wrapper for Java Swing Components.
 */
public class Widget extends Image{
    protected double paddingX;
    protected double paddingY;
    protected JComponent inner;
    
    /** Create a Widget from the given JComponent with no padding. */
    public Widget(JComponent inner){
        this(inner, 0, 0);
    }
    
    /** Create a Widget from the given JComponent with the given X/Y paddings. */
    public Widget(JComponent inner, int paddingX, int paddingY){
        this(inner, (double)paddingX, paddingY);
    }
    
    /** Create a Widget from the given JComponent with the given X/Y paddings. */
    public Widget(JComponent inner, double paddingX, double paddingY){
        super(0, 0);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        inner.setSize(inner.getPreferredSize());
        this.inner = inner;
        this.pinholeX = paddingX+inner.getWidth()/2;
        this.pinholeY = paddingY+inner.getHeight()/2;
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        Graphics g2 = 
            g.create(round(x-this.pinholeX+this.paddingX),
                     round(y-this.pinholeY+this.paddingY),
                     inner.getWidth()+1, inner.getHeight()+1);
        inner.paint(g2);
        for(java.awt.Component c : inner.getComponents()){
            c.paint(g2);
        }
    }
    /** Return the Width of this Image */
    public int width(){ return (int)(this.inner.getWidth()+2*this.paddingX); }
    /** Return the Height of this Image */
    public int height(){ return (int)(this.inner.getHeight()+2*this.paddingY); }
}
