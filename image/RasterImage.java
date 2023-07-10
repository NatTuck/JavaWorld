/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/RasterImage.java                                      *
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

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.image.BufferedImage;

/** Represents a raster Image drawn into a Buffer.  Because rotating and
 *    scaling arbitrary shapes/images is difficult, we can rasterize, then
 *    apply effects.  It also allows us to merge images (i.e., overlays)
 *    into a single image to make drawing more efficient. */
public class RasterImage extends Image{
    protected BufferedImage img;
    protected int w;
    protected int h;
    
    /** Construct a RasterImage with the given width/height */
    public RasterImage(int w, int h){
        super(w, h);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0, 0, w, h);
        this.init(bi);
    }
    /** Initialize this FromFile with the given Bitmap */
    protected void init(BufferedImage img){
        this.img = img;
        this.w = img.getWidth();
        this.h = img.getHeight();
        this.pinholeX = this.w/2;
        this.pinholeY = this.h/2;
    }
    
    /** Draw this image into a Graphics */
    public void paint(Graphics g, int xx, int yy){
        g.drawImage(this.img,round(xx-this.pinholeX),round(yy-this.pinholeY),this.w,this.h,null);
    }
    /** Return the width of this Image */
    public int width(){ return this.w; }
    /** Return the height of this Image */
    public int height(){ return this.h; }
    
    /** Get the Graphics2D associated with this RasterImage */
    public Graphics2D getGraphics(){
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return g;
    }
    /** Get the <tt>java.awt.Color</tt> of the pixel at the given x/y */
    public Color getPixel(int x, int y){
        int rgb = img.getRGB(x, y);
        return ColorDatabase.makeAwtColor(rgb);
    }
    /** Get a <tt>String</tt> of the Color pixel at the given x/y. E.g.,
     *     <tt class='str'>"#00FF00"</tt> would be returned if the color
     *     of the pixel at the given x/y is blue. */
    public String getPixelAsString(int x, int y){
        int rgb = img.getRGB(x, y);
        return ColorDatabase.makeColor(rgb);
    }
    /** Set the pixel at the given x/y to the given String Color */
    public void setPixel(int x, int y, String color){
        this.setPixel(x, y, ColorDatabase.colorToARGB(color));
    }
    /** Set the pixel at the given x/y to the given {@link java.awt.Color} */
    public void setPixel(int x, int y, java.awt.Color color){
        this.setPixel(x, y, color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
    }
    /** Set the pixel at the given x/y to the given ARGB integer. */
    public void setPixel(int x, int y, int argb){
        this.img.setRGB(x, y, argb);
    }
    /** Set the pixel at the given x/y to the given RGB intensities.
     *    Intensities must be between 0 and 255, inclusive.*/
    public void setPixel(int x, int y, int red, int green, int blue){
        this.setPixel(x, y, 255, red, green, blue);
    }
    /** Set the pixel at the given x/y to the given ARGB intensities.
     *    Intensities must be between 0 and 255, inclusive.*/
    public void setPixel(int x, int y, int alpha, int red, int green, int blue){
        this.setPixel(x, y, ColorDatabase.colorToARGB(alpha, red, green, blue));
    }
    /** Set the pixel at the given x/y to the given RGB intensities.
     *    Intensities must be between 0 and 1.0, inclusive.*/
    public void setPixel(int x, int y, double red, double green, double blue){
        this.setPixel(x, y, 1.0, red, green, blue);
    }
    /** Set the pixel at the given x/y to the given ARGB intensities.
     *    Intensities must be between 0 and 1.0, inclusive.*/
    public void setPixel(int x, int y, double alpha, double red, double green, double blue){
        this.setPixel(x, y, (int)(alpha*255), (int)(red*255), (int)(green*255), (int)(blue*255));
    }
}

