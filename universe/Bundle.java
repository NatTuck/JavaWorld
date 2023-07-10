/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/Bundle.java                                        *
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

import java.io.Serializable;

/** Represents the result of a Universe function where the universe is updated, 
 *    messages are sent to registered Worlds, and Worlds are marked for removal
 *    from the universe. */
public class Bundle<Msg extends Serializable>{
    /** The current Universe state */
    private Universe<Msg> univ;
    /** A list of mail messages to be sent to worlds */
    private List<Mail<Msg>> mails;
    /** A list of worlds to remove on the next tick */
    private List<IWorld> worlds;
    
    /** Create a Bundle without any Mail/Worlds to remove */
    public Bundle(Universe<Msg> univ){
        this(univ, new Empty<Mail<Msg>>(), new Empty<IWorld>());
    }
    /** Create a Bundle with Mail messages but no Worlds to remove */
    public Bundle(Universe<Msg> univ, Mail<Msg> mail){
        this(univ, List.<Mail<Msg>>create(mail), new Empty<IWorld>());
    }
    /** Create a Bundle with Mail messages but no Worlds to remove */
    public Bundle(Universe<Msg> univ, Mail<Msg> ... mails){
        this(univ, List.<Mail<Msg>>create(mails), new Empty<IWorld>());
    }
    /** Create a Bundle with a list of Mail messages but no Worlds to remove */
    public Bundle(Universe<Msg> univ, List<Mail<Msg>> mails){
        this(univ, mails, new Empty<IWorld>());
    }
    /** Create a Bundle with a list of Mail messages and ask that the given 
     *    Worlds be removed */
    public Bundle(Universe<Msg> univ, List<Mail<Msg>> mails, List<IWorld> worlds){
        // Current Universe
        this.univ = univ;
        // Messages to send...
        this.mails = mails;
        // Worlds to remove from the list...
        this.worlds = worlds;
    }
    /** Get the Universe state */
    public Universe<Msg> getUniverse(){ return this.univ; }
    /** Get the list of Mail messages */
    public List<Mail<Msg>> getMails(){ return this.mails; }
    /** Get the worlds to be removed */
    public List<IWorld> getWorlds(){ return this.worlds; }
}

