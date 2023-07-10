/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./imagetutor/Example3.java                                    *
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

/** Represents a Chain */
interface IChain{
    // Place this IChain into the given Scene
    public Scene place(int x, Scene scn);
}
/** Represents a Link in a Chain */
class Link implements IChain{
    IChain next;

    Link(IChain next){
        this.next = next;    
    }

    // Place this Link into the given Scene
    public Scene place(int x, Scene scn){
        return this.next.place(x+25, scn)
        .placeImage(new Overlay(new Ellipse(28, 13, "outline", "gray"),
                new Ellipse(30, 15, "outline", "black")),
                x, 50);
    }
}
/** Represents the end of a Chain */
class End implements IChain{
    End(){}

    // The end of the chain, return the Scene
    public Scene place(int x, Scene scn){
        return scn;
    }
}

/** Example #3 for Image tutorial */
public class Example3{
    Example3(){}

    IChain ch = new Link(new Link(new Link(
            new Link(new Link(new Link(new End()))))));
    Scene scn = this.ch.place(40, new EmptyScene(200, 100));

    public static void main(String[] args){
        World.display(new Example3().scn);
    }
}

