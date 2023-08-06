/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/Image.java                                            *
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
import java.awt.geom.AffineTransform;

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
 *  An abstract class representing an Image.  See subclasses for
 *    specific implementations.
 */
public abstract class Image {
    protected static int OUTLINE = 0;
    protected static int SOLID = 1;
    
    /** Pinhole for alignment to other images */
    protected double pinholeX, pinholeY;
    /** Only for subclasses */
    protected Image(double px, double py){
        this.pinholeX = px;
        this.pinholeY = py;
    }
    // Calculate Image size to the left/right/up/down of the pinhole
    protected double leftOfPin(){ return this.pinholeX; }
    protected double rightOfPin(){ return width()-this.pinholeX; }
    protected double upOfPin(){ return this.pinholeY; }
    protected double downOfPin(){ return height()-this.pinholeY; }
    
    /** Overlay the given Image over this Image */
    public Image overlay(Image top)
    { return new Overlay(top,this); }
    /** Overlay the given Images over this Image */
    public Image overlay(Image top, Image next, Image ... imgs){
        return new Overlay(new Overlay(top, make(next, imgs, 0)), this);
    }
    /** Overlay the given Image over this Image offset (x,y) */
    public Image overlayxy(Image top, int x, int y)
    { return overlayxy(top, (double)x, y); }
    /** Overlay the given Image over this Image offset (x,y) */
    public Image overlayxy(Image top, double x, double y)
    { return new OverlayXY(top,x,y,this); }
    /** Overlay the given Image over this Image offset Posn */
    public Image overlayxy(Image top, Posn p)
    { return overlayxy(top,p.x,p.y); }

    /** Overlay the given Image over this Image offset (x,y) */
    public Image overlayxy(int x, int y, Image top)
    { return overlayxy(top, x, y); }
    /** Overlay the given Image over this Image offset (x,y) */
    public Image overlayxy(double x, double y, Image top)
    { return overlayxy(top, x, y); }
    /** Overlay the given Image over this Image offset Posn */
    public Image overlayxy(Posn p, Image top)
    { return overlayxy(top,p.x,p.y); }
        
    /** Make a layered Image with top t and bottoms of imgs[i..] */
    private static Image make(Image t, Image[] imgs, int i){
        if(i == imgs.length)
            return t;
        return new Overlay(t, make(imgs[i], imgs, i+1));    
    }
    
    
    /** Draw this image into a Graphics */
    public abstract void paint(Graphics g, int x, int y);
    /** Calculate the width of this Image */
    public abstract int width();
    /** Calculate the height of this Image */
    public abstract int height();
    
    /** Convert this image into a Scene containing the image without a background */
    public Scene toScene(){
        return new MT(ceil(this.width()+2), ceil(this.height()+2)).placeImage(this, this.pinholeX+1, this.pinholeY+1);
    }
    /** Convert this image into a Scene containing the image with a white background */
    public Scene toWhiteScene(){
        return new White(ceil(this.width()+2), ceil(this.height()+2)).placeImage(this, this.pinholeX+1, this.pinholeY+1);
    }
    /** Represents a truly empty scene... */
    private static class MT extends EmptyScene{
        MT(int w, int h){ super(w,h); }
        /** Leave the graphics empty (translucent) */
        public void paint(Graphics g, int x, int y){}
    }
    /** Represents a truly empty scene... */
    private static class White extends EmptyScene{
        White(int w, int h){ super(w,h); }
        /** Fill with a white background */
        public void paint(Graphics g, int x, int y){
            g.setColor(Color.white);
            g.fillRect(x, y, this.width+1, this.height+1);
        }
    }
    /** Save this image to a file */
    public boolean toFile(String name){
        return toScene().toFile(name);
    }
    /** Save this image to a file */
    public boolean toWhiteFile(String name){
        return toWhiteScene().toFile(name);
    }
    
    /** Convert the mode string into an int representing Outline/Solid */
    protected static int mode(String s){
        if(s.toLowerCase().equals("outline")) return OUTLINE;
        if(s.toLowerCase().equals("solid")) return SOLID;
        throw new RuntimeException("Mode expected \"solid\" or \"outline\", got: \""+s+"\"");
    }

    /** Round the given Double to an Int */
    protected static int round(double d){
        return (int)Math.round(d);
    }
    /** Round the given Double to an Int */
    protected static int ceil(double d){
        return (int)Math.ceil(d);
    }

    /** Get the java.awt.Color represented by the given string */
    protected static Color color(String s){
        return ColorDatabase.color(s);
    }
    
    /** Return a raster (buffered) version of this Image */
    public RasterImage rasterize(){
        RasterImage img = new RasterImage(ceil(this.width()), ceil(this.height()));
        Graphics2D g = img.getGraphics();
        
        this.paint(g, round(this.pinholeX), round(this.pinholeY));
        return img;
    }
    /** Return a rotated version of this image by the given angle in degrees. */
    public Image rotate(int ang){
        return this.rotate((double)ang);
    }
    /** Return a rotated version of this image by the given angle in degrees.
     *  <p>
     *    Any Image may be rotated several times, though for efficiency
     *    (when possible) the rotated image should be saved, rather than
     *    recreated.
     *  </p>
     */
    public RasterImage rotate(double ang){
        ang = (ang + 360.0) % 360.0;
        int max = round(Math.max(Math.max(phDist(0,0),
                                          phDist(0,this.height())),
                                 Math.max(phDist(this.width(),0),
                                          phDist(this.width(),this.height()))));
        return this.transform(AffineTransform.getRotateInstance(Math.toRadians(-ang), max, max),
                max*2, max*2, max, max);
    }
    /** Return a version of this Image scaled by the given int multiplier.
    private RasterImage scale(int s){
        return this.scale((double)s);
    }
    /** Return a version of this Image scaled by the given int X and Y multipliers.
    private RasterImage scale(int sx, int sy){
        return this.scale((double)sx, sy);
    }
    /** Return a version of this Image scaled by the given double multiplier.
    private RasterImage scale(double s){
        return this.scale(s, s);
    }*/
    /** Return a version of this Image scaled by the given double X and Y multipliers. */
    private RasterImage scale(double sx, double sy){
        return this.transform(AffineTransform.getScaleInstance(sx, sy),
                Math.abs(sx*this.width()), Math.abs(sy*this.height()), this.width()/2.0/sx, this.height()/2.0/sy);
    }
    /** Return a version of this Image flipped horizontally (left to right). */
    public RasterImage flipHorizontal(){
        return scale(-1.0, 1.0);
    }
    /** Return a version of this Image flipped vertically (top to bottom). */
    public RasterImage flipVertical(){
        return scale(1.0, -1.0);
    }
    /** Return a transformed version of this Image */
    protected RasterImage transform(AffineTransform newtform, double nw, double nh, double cx, double cy){
        RasterImage img = new RasterImage(round(nw), round(nh));
        Graphics2D g = img.getGraphics();
        g.setTransform(newtform);
        this.rasterize().paint(g, round(cx), round(cy));
        return img;
    }
    /** Maximum distance from the Pinhole to the given XY of this Image */
    private double phDist(double x, double y){
        double dx = x - this.pinholeX,
               dy = y - this.pinholeY;
        return Math.sqrt((dx*dx)+(dy*dy)+2);
    }
}

