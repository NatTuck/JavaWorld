/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/FromResource.java                                     *
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

/** Represents an image from a JAR resource (an Image file within
 *    the currently executing JAR).  In order to avoid package
 *    relative path problems, the absolute path of the image
 *    within the JAR should be used.
 */
public class FromResource extends FromFile{
    
    /** Create an Image from the given file name within a JAR. */
    public FromResource(String f){
        try{
            if(loaded.containsKey(f)){
                this.img = loaded.get(f);
            }else{
                this.img = ImageIO.read(getClass().getResourceAsStream(f));
                loaded.put(f, this.img);
            }
            this.init(this.img);
        }catch(java.io.IOException e){
            throw new RuntimeException("Error Loading JAR Image resource: \""+f+"\"");
        }catch(IllegalArgumentException e){
            throw new RuntimeException("JAR Image resource: \""+f+"\" Not Found!");
        }
    }
    /** Store File/Resource Images, to avoid multiple loads, shadows
     *    <tt>FromFile.loaded</tt> so that resources and files come
     *    from different name-spaces. */
    protected static Hashtable<String,BufferedImage> loaded = new Hashtable<String,BufferedImage>();
}



