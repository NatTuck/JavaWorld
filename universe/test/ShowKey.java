/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/test/ShowKey.java                                  *
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

package universe.test;
import image.*;

import universe.world.*;
import universe.Package;

/** Universe/World OnKey test.  Sends the key string to the server, which
 *    responds by wrapping the given key in parentheses: "("+key+")"*/
public class ShowKey extends World<String>{
    
    public static void main(String[] s){
        new ShowKey("none")
            //.bigBang("127.0.0.1", "chadwick");
            .bigBang("riverpatrol.ccs.neu.edu", "chadwick");
    }

    String key;
    
    /**  */
    ShowKey(String key){
        this.key = key;
    }
    
    /**  */
    public Package<String> onKey(String ke){
        return new Package<String>(this, ke);
    }
    /**  */
    public Scene onDraw(){
        return new EmptyScene(400,400)
            .placeImage(new Text(this.key, 60, "red"), 200, 200);
    }
    /**  */
    public Package<String> onReceive(String msg){
        return new Package<String>(new ShowKey(msg));
    }
}

