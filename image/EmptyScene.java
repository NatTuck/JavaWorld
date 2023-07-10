/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/EmptyScene.java                                       *
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
 * Represents the empty (blank) Scene (a cropped <code>Image</code>).
 * 
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>160</span>, <span class='num'>90</span>)</pre>
 * <img class="example" src="test/images/empty-scene.png" />
 * <br />
 *
 */
public class EmptyScene extends Scene{
    protected int width;
    protected int height;
    protected Color color;
    protected boolean clipped;
    
    /** Construct an EmptyScene of (Width x Height) */
    public EmptyScene(double width, double height){
        this(round(width), round(height));
    }
    /** Construct an EmptyScene of (Width x Height) */
    public EmptyScene(double width, double height, String color){
        this(round(width), round(height), color);
    }
    /** Construct an EmptyScene of (Width x Height) */
    public EmptyScene(int width, int height){
        this(width, height, "white");
        this.clipped = true;
    }
    /** Construct an EmptyScene of (Width x Height) */
    public EmptyScene(int width, int height, String color){
        this.width = width;
        this.height = height;
        this.color = Image.color(color);
        this.clipped = false;
    }
    /** Paint this Scene into the given graphics */
    public void paint(Graphics g, int x, int y){
        g.setColor(this.color);
        g.fillRect(x, y, this.width-1, this.height-1);
        if(this.clipped){
            g.setColor(Color.black);
            g.drawRect(x, y, this.width-1,this.height-1);
            g.setClip(x+1, y+1, this.width-2, this.height-2);
        }
    }
    
    protected double leftOfPin(){ return 0; }
    protected double rightOfPin(){ return this.width; }
    protected double upOfPin(){ return 0; }
    protected double downOfPin(){ return this.height; }
        
    /** Return the Width of this Scene/Image */
    public int width(){ return this.width; }
    
    /** Return the Width of this Scene/Image */
    public int height(){ return this.height; }
}

