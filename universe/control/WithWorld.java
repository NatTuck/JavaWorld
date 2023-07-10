/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/control/WithWorld.java                             *
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

import universe.base.Server.WorldImp;
import universe.base.Server.WorldShell;

public class WithWorld extends Message{
    private static final long serialVersionUID = 1;
    Message forward;
    WorldShell world;

    public WithWorld(Message m, WorldShell w){
        super(m.from());
        this.forward = m;
        this.world = w;
    }
    public boolean isTransfer(){ return this.forward.isTransfer(); }
    public boolean isConnect(){ return this.forward.isConnect(); }
    public boolean isDisconnect(){ return this.forward.isDisconnect(); }
    public WorldShell getWShell(){ return this.world; }
    public WorldImp getWImp(){ return (WorldImp)this.world; }
    public <R> R payload(){ return this.forward.<R>payload(); }
}

