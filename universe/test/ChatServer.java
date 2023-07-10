/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/test/ChatServer.java                               *
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

/** Universe ChatServer test.  Implements a Simple Server that broadcasts
 *    messages (strings) to all connected clients. */
public class ChatServer extends Universe<String>{
    /** Main method.  Starts the server/universe */
    public static void main(String[] s){
        new ChatServer().universe();
    }

    // List of currently connected clients
    List<IWorld> clients;
    
    /** Construct a ChatServer with the empty list of clients */
    ChatServer(){
        this(new Empty<IWorld>());
    }
    /** Construct a ChatServer with the given list of clients */
    ChatServer(List<IWorld> clients){
        this.clients = clients;
    } 
    /** Nothing to draw */
    public Scene onDraw(){ return new EmptyScene(400, 300); }
    
    /** When a message arrives, broadcast it to all connected clients */
    public Bundle<String> onMsg(IWorld w, String m){
        return new Bundle<String>(this,
                this.clients.map(new AddMail(m)),
                List.<IWorld>create());
    }
    /** When a new client arrives, add it to the current list */
    public Bundle<String> onNew(IWorld w){
        return new Bundle<String>(new ChatServer(this.clients.push(w)),
                this.clients.map(new AddMail(w.name()+" arrived")));
    }
    /** When a client disconnects, remove the world and send a message */
    public Bundle<String> onDisconnect(IWorld w){
            List<IWorld> nclients = this.clients.remove(w);
            return new Bundle<String>(new ChatServer(nclients),
                    nclients.map(new AddMail(w.name()+" disconnected")),
                    List.<IWorld>create(w));
    }
}

/** Helper class to convert a list of worlds into a list of Mail */
class AddMail extends List.Map<IWorld,Mail<String>>{
    String str;
    AddMail(String str){ this.str = str; }
    /** Create a Mail message for the given IWorld */
    public Mail<String> map(IWorld w){
        return new Mail<String>(w, this.str);
    }
}

