/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/Package.java                                       *
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

package universe;

import universe.world.World;
import java.io.Serializable;

/** Represents the results of {@link universe.world.World World} functions with
 *    a possible message to the connected {@link universe.Universe Universe}. */
public class Package<Msg extends Serializable>{
    private World<Msg> world;
    private Msg msg;
    
    /** Construct a <tt>Package</tt> with the given state of the <tt>World</tt>. */
    public Package(World<Msg> w){
        this(w,null);
    }
    
    /** Construct a <tt>Package</tt> with the given state of the <tt>World</tt>
     *    and a message to the <tt>Universe</tt>. */
    public Package(World<Msg> w, Msg m){
        this.world = w;
        this.msg = m;
    }
    /** Access the current state of the <tt>World</tt> within this <tt>Package</tt> */
    public World<Msg> getWorld(){ return this.world; }
    /** Does this <tt>Package</tt> contain a message? */
    public boolean hasMsg(){ return this.msg != null; }
    /** Get the message associated with this <tt>Package</tt>/<tt>World</tt> */
    public Msg getMsg(){ return this.msg; }
}

