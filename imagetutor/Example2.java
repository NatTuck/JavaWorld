/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./imagetutor/Example2.java                                    *
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

package imagetutor;

import image.*;
import world.*;

/** Example #2 for Image tutorial */
public class Example2{
    Example2(){}

    // Create a Scene with nested stars of decreasing size
    Scene stars(int size){
        if(size <= 4){
            return new EmptyScene(200, 200);
        }else{
            return this.stars(size-15)
            .placeImage(new Star(size, "outline", "red"), 100, 100);
        }
    }

    Scene scn = this.stars(100);

    public static void main(String[] args){
        World.display(new Example2().scn);
    }
}

