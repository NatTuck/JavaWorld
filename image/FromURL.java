/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/FromURL.java                                          *
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

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import java.net.URL;

/** Represents an image from a URL.  The image is loaded on when created. */
public class FromURL extends FromFile{
    
    /** Create an Image from the given URL address */
    public FromURL(String url){
        try{
            if(loaded.containsKey(url)){
                this.img = loaded.get(url);
            }else{
                this.img = ImageIO.read(new URL(url));
                loaded.put(url, this.img);
            }
            this.init(this.img);
        }catch(java.io.IOException e){
            throw new RuntimeException("Error Loading URL Image: \""+url+
                    "\"\n"+e.getMessage());
        }
    }
    /** Store URL Images, to avoid multiple loads, shadows
     *    <tt>FromFile.loaded</tt> so that URLs and files come
     *    from different name-spaces. */
    protected static Hashtable<String,BufferedImage> loaded = new Hashtable<String,BufferedImage>();
}



