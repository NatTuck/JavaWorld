/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/Universe.java                                      *
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

import image.Scene;

import java.io.Serializable;
import universe.base.*;


/** A Class representing a Universe (a collection of Worlds) of some type, and related methods
 *    and Function Objects for handling messages and events */
public abstract class Universe<Msg extends Serializable>{
    
    /** Makes Java/Eclipse Happy */
    private static final long serialVersionUID = 1;
    /** Default Tick rate for the world */
    public static double DEFAULT_TICK_RATE = 0.033;
    
    /** Empty Default Constructor */
    public Universe(){}
    
    public abstract Scene onDraw();
    
    public Bundle<Msg> onTick(){
        return new Bundle<Msg>(this);
    }
    /** Return the tick rate for this World.  This is only accessed at the
     *    initial Universe creation. */
    public double tickRate(){ return DEFAULT_TICK_RATE; }
    
    public Bundle<Msg> onNew(IWorld w){
        return new Bundle<Msg>(this);
    }
    public Bundle<Msg> onDisconnect(IWorld w){
        return new Bundle<Msg>(this);
    }
    public Bundle<Msg> onMsg(IWorld w, Msg msg){
        return new Bundle<Msg>(this);
    }
    
    public Universe<Msg> universe(){
        return new UniverseBase<Msg>(this) 
                 .onNew(new UnivNew())
                 .onDraw(new UnivDraw())
                 .onTick(new UnivTick(), this.tickRate())
                 .onMsg(new UnivMsg())
                 .onDisconnect(new UnivDis())
                 .universe();
    }
    /** Wrapper for OnDraw callback */
    private class UnivDraw implements OnDraw<Msg>{
        public Scene apply(Universe<Msg> u){ return u.onDraw(); }
    }
    /** Wrapper for OnMsg callback */
    private class UnivMsg implements OnMsg<Msg>{
        public Bundle<Msg> apply(Universe<Msg> u, IWorld w, Msg m){ return u.onMsg(w, m); }
    }
    /** Wrapper for OnTick callback */
    private class UnivTick implements OnTick<Msg>{
        public Bundle<Msg> apply(Universe<Msg> u){ return u.onTick(); }
    }
    /** Wrapper for OnNew callback */
    private class UnivNew implements OnNew<Msg>{
        public Bundle<Msg> apply(Universe<Msg> u, IWorld w)
        { return u.onNew(w); }
    }
    /** Wrapper for OnNew callback */
    private class UnivDis implements OnDisconnect<Msg>{
        public Bundle<Msg> apply(Universe<Msg> u, IWorld w)
        { return u.onDisconnect(w); }
    }
}

