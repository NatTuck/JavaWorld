/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/control/Message.java                               *
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

package universe.control;

import java.io.Serializable;

/** Represents a Message between World and Universe */
public abstract class Message implements Serializable{
    private static final long serialVersionUID = 1;
    
    /** The ID Number of the World (should be randomly assigned) */
    protected long worldid;
    
    /** Create a Message from the given World/Name */
    protected Message(long id){ this.worldid = id; }
    
    /** Is this a Connect Message? */
    public boolean isConnect(){ return false; }
    /** Is this a Disconnect Message? */
    public boolean isDisconnect(){ return false; }
    /** Is this a Transfer Message? */
    public boolean isTransfer(){ return false; }
    
    /** Get the name of the World/sender */
    public long from(){ return this.worldid; }
    /** Get the message's payload/content */
    public <R> R payload(){ throw new RuntimeException("Message without Payload!"); }
}

