/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/test/ShowServer.java                               *
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

import universe.*;
import image.*;

/** Universe/Parameterized BigBang OnKey test */
public class ShowServer extends Universe<String>{
    /**  */
    public static void main(String[] s){
        new ShowServer("none").universe();
    }
    
    String key;
    
    ShowServer(String key){
        this.key = key;
    }
    
    /**  */
    public Scene onDraw(){
        return new EmptyScene(400, 300)
           .placeImage(new Text(this.key, 40, "blue"), 200, 150);
    }
    /**  */
    public Bundle<String> onMsg(IWorld w, String m){
        System.out.println("Message: "+m);
        return new Bundle<String>(new ShowServer(m),
                new Mail<String>(w, "*("+m+")*"));
    }
    /**  */
    public Bundle<String> onNew(IWorld w){
        System.out.println("New!!");
        return new Bundle<String>(this);
    }
    /**  */
    public Bundle<String> onDisconnect(IWorld w){
        System.out.println("Disconnect!!");
        return new Bundle<String>(this);
    }
}
