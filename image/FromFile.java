/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/FromFile.java                                         *
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

/** Represents an Image from a file.  The given file name is used
 *    to load the image.  Resolved path names are cached, so images
 *    are only loaded once. */
public class FromFile extends RasterImage{
    /** Default constructor... must call init after using */
    protected FromFile(){
        super(1,1);
    }
    /** Create an Image from the given file name */
    public FromFile(String f){
        super(1,1);
        try{
            java.io.File file = new java.io.File(f);
            String abs = file.getCanonicalPath();
            if(loaded.containsKey(abs)){
                this.img = loaded.get(abs);
            }else{
                this.img = ImageIO.read(file);
                loaded.put(abs, this.img);
            }
            this.init(this.img);
        }catch(java.io.IOException e){
            throw new RuntimeException("Image File: \""+f+"\" Not Found!");
        }
    }
    /** Store File Images, to avoid multiple loads */
    protected static Hashtable<String,BufferedImage> loaded = new Hashtable<String,BufferedImage>();
}



