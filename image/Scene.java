/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Scene.java                                            *
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
import java.io.File;
import javax.imageio.ImageIO;

import image.RasterImage;
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
 * An abstract Class representing a Scene (a cropped <code>Image</code>).  Images
 * can be placed on a <code>Scene</code> at a specific location, resulting in a
 * new <code>Scene</code>. 
 * 
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).placeImage(<span class='keyw'>new</span> Triangle(<span class='num'>32</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>24</span>, <span class='num'>24</span>)</pre>
 * <img class="example" src="test/images/scene-1.png" />
 * <br />
 *
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).placeImage(<span class='keyw'>new</span> Triangle(<span class='num'>64</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>24</span>, <span class='num'>24</span>)</pre>
 * <img class="example" src="test/images/scene-2.png" />
 * <br /> 
 * 
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).addLine(<span class='num'>0</span>, <span class='num'>0</span>, <span class='num'>48</span>, <span class='num'>48</span>, <span class='str'>"blue"</span>)</pre>
 * <img class="example" src="test/images/addline-1.png" />
 * <br/>
 * 
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).addLine(<span class='num'>4</span>, <span class='num'>24</span>, <span class='num'>44</span>, <span class='num'>24</span>, <span class='str'>"green"</span>)</pre>
 * <img class="example" src="test/images/addline-2.png" />
 * <br/>
 * 
 * <pre>
 *    <span class='keyw'>new</span> EmptyScene(<span class='num'>50</span>, <span class='num'>50</span>)
 *         .placeImage(<span class='keyw'>new</span> Overlay(<span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
 *                                 <span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"wheat"</span>)), <span class='num'>25</span>, <span class='num'>25</span>)
 *         .placeImage(<span class='keyw'>new</span> Circle(<span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"lightblue"</span>), <span class='num'>18</span>, <span class='num'>20</span>)
 *         .placeImage(<span class='keyw'>new</span> Rectangle(<span class='num'>10</span>, <span class='num'>3</span>, <span class='str'>"solid"</span>, <span class='str'>"lightblue"</span>), <span class='num'>33</span>, <span class='num'>20</span>)
 *         .placeImage(<span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>8</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>25</span>, <span class='num'>35</span>)</pre>
 * <img class="example" src="test/images/face.png" />
 * <br/>
 */
public abstract class Scene extends Image{
    
    protected Scene(){ super(0,0); }
    
    /** Draw the scene into a Graphics */
    public abstract void paint(Graphics g, int x, int y);
    
    /** Place another Image on top of this Scene at (x,y) */
    public Scene placeImage(Image i, double x, double y){
        return new Placed(i,x,y,this);    
    }
    /** Place another Image on top of this Scene at (x,y) */
    public Scene placeImage(Image i, int x, int y){ return new Placed(i,x,y,this); }
    /** Place another Image on top of this Scene at the given Posn */
    public Scene placeImage(Image i, Posn p){ return new Placed(i,p.x,p.y,this); }

    /** Add a line to this Scene from (x,y) to (xx, yy) */
    public Scene addLine(int x, int y, int xx, int yy, String color){
        return this.addLine((double)x, y, xx, yy, color);    
    }
    /** Add a line to this Scene from (x,y) to (xx, yy) */
    public Scene addLine(double x, double y, double xx, double yy, String color){
        return this.placeImage(new Line(xx-x, yy-y, color),
                       (xx+x)/2, (yy+y)/2);    
    }
    /** Add a line to this Scene from the first Posn to the second */
    public Scene addLine(Posn p1, Posn p2, String color){
        return this.addLine(p1.x, p1.y, p2.x, p2.y, color);    
    }
    
    /** Represents an Image Placed on top of a Scene at offset X/Y */
    private static class Placed extends Scene{
        private Image img;
        private double x;
        private double y;
        private Scene next;
                
        Placed(Image img, int x, int y, Scene next){
            this(img, (double)x, y, next);
        }
        Placed(Image img, double x, double y, Scene next){
            this.img = img;
            this.x = x;
            this.y = y;
            this.next = next;
        }
        /** Paint the next scene, then place the image on top */
        public void paint(Graphics g, int xx, int yy){
            this.next.paint(g,xx,yy);
            this.img.paint(g,round(xx+this.x),round(yy+this.y));
        }
        /** Calculate the width of the combined Scene/Image */
        public int width(){ return this.next.width(); }
        public int height(){ return this.next.height(); }
    }
    
    /** Save this Scene to a File */
    public boolean toFile(String name){
        try{
            RasterImage rast = this.rasterize();
            int dot = name.lastIndexOf('.');
            String ext = name.substring(dot+1);
            return ImageIO.write(rast.img, ext, new File(name));
        }catch(Exception e){
            System.err.println("Error writing Scene: ["+e.getClass().getSimpleName()+"] "+e.toString());
            e.printStackTrace();
            return false;
        }
    }
}

