/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/world/VoidWorld.java                               *
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

package universe.world;

import java.io.Serializable;

import universe.VoidPackage;
import universe.Package;
import image.Scene;

/**  A Class representing an imperative World and the related methods
 *  for drawing the world and handling various events connected to a
 *  {@link universe.Universe Universe}.  The class is parameterized by
 *  the type of messages (which must <tt class='keyw'>extend</tt>
 *  {@link java.io.Serializable Serializable}) that are passed
 *  throughout the Universe.
 *    
 * <p> In order to implement a functioning World you must
 *  <i>extend</i> this class, and implement an {@link
 *  universe.world.VoidWorld#onDraw onDraw} method.  Other handler
 *  methods ({@link universe.world.VoidWorld#tickRate tickRate},
 *  {@link universe.world.VoidWorld#onTick onTick}, {@link
 *  universe.world.VoidWorld#onMouse onMouse}, {@link
 *  universe.world.VoidWorld#onKey onKey}, {@link
 *  universe.world.VoidWorld#onRelease onRelease}, {@link
 *  universe.world.VoidWorld#stopWhen stopWhen}, and {@link
 *  universe.world.VoidWorld#lastScene lastScene}) are optional, and
 *  can be overridden to add new functionality. </p>
 *
 * <p> Most handler methods return a {@link universe.VoidPackage
 *  VoidPackage}, which contains a possible message to the connected
 *  <tt>Universe</tt>.  In order to interact with the
 *  <tt>Universe</tt> and other <tt>VoidWorld</tt>/<tt>World</tt>s,
 *  implementations must override the {@link
 *  universe.world.VoidWorld#onReceive onReceive} method that handles
 *  messages from the <tt>Universe</tt>.  </p>
 *
 * <p>
 *  See also {@link world.VoidWorld world.VoidWorld} for similar documentation on individual methods.
 * </p>
 *
 */

public abstract class VoidWorld<Msg extends Serializable>{
    /** Default Tick rate for the world: ~33 frames per second */
    public static double DEFAULT_TICK_RATE = BigBang.DEFAULT_TICK_RATE;

    /** Mouse down (button-down) event String */
    public static String MOUSE_DOWN = BigBang.MOUSE_DOWN;
    /** Mouse up (button-up) event String */
    public static String MOUSE_UP = BigBang.MOUSE_UP;
    /** Mouse window enter (enter) event String */
    public static String MOUSE_ENTER = BigBang.MOUSE_ENTER;
    /** Mouse window leave (leave) event String */
    public static String MOUSE_LEAVE = BigBang.MOUSE_LEAVE;
    /** Mouse motion (move) event String */
    public static String MOUSE_MOVE = BigBang.MOUSE_MOVE;
    /** Mouse down & move (drag) event String */
    public static String MOUSE_DRAG = BigBang.MOUSE_DRAG;   

    /** Key arrow-up event String */
    public static String KEY_ARROW_UP = BigBang.KEY_ARROW_UP;
    /** Key arrow-down event String */
    public static String KEY_ARROW_DOWN = BigBang.KEY_ARROW_RIGHT;
    /** Key arrow-left event String */
    public static String KEY_ARROW_LEFT = BigBang.KEY_ARROW_LEFT;
    /** Key arrow-right event String */
    public static String KEY_ARROW_RIGHT = BigBang.KEY_ARROW_RIGHT;
    
    /** Return a visualization of this <tt>VoidWorld</tt> as a {@link image.Scene Scene}.
     *    See {@link image.EmptyScene}, {@link image.Scene#placeImage(Image, int, int)}, and
     *    {@link image.Scene#addLine(int, int, int, int, String)} for documentation on
     *    constructing <tt>Scene</tt>s */
    public abstract Scene onDraw();
    
    /** Return the tick rate for this VoidWorld in <i>seconds</i>.  For example,
     *  <span class='num'>0.5</span> means two <i>ticks</i> per second. The
     *  rate is only accessed when bigBang() is initially called and the
     *  window is created. */
    public double tickRate(){ return DEFAULT_TICK_RATE; }
    
    /** Change this VoidWorld based on the Tick of the clock.  This
     *  method is called the update the VoidWorld on each clock
     *  tick. */
    public VoidPackage<Msg> onTick(){
        return new VoidPackage<Msg>();
    }
    
    /** Change this VoidWorld (and produce a <tt>VoidPackage</tt>
     * possibly containing a message) when a mouse event is triggered.
     * <tt>x</tt> and <tt>y</tt> are the location of the event in the
     * window, and <tt>event</tt> is a <tt>String</tt> that describes
     * what kind of event occurred.
     * 
     * <p>
     *   <b>Possible Mouse Events</b>
     * <table class='events'>
     *    <tr><td style="text-align:right"><tt class='str'>"button-down"</tt> : </td>
     *        <td>The user <i>presses</i> a mouse button in the VoidWorld window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"button-up"</tt> : </td>
     *        <td>The user <i>releases</i> a mouse button in the VoidWorld window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"move"</tt> : </td>
     *        <td>The user <i>moves</i> the mouse in the VoidWorld window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"drag"</tt> : </td>
     *        <td>The user <i>holds</i> a mouse button and <i>moves</i> the mouse in the VoidWorld window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"enter"</tt> : </td>
     *        <td>The user moves the mouse <i>in-to</i> the VoidWorld window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"leave"</tt> : </td>
     *        <td>The user moves the mouse <i>out-of</i> the VoidWorld window</td></tr>
     * </table>
     * </p>
     */
    public VoidPackage<Msg> onMouse(int x, int y, String me){
        return new VoidPackage<Msg>();
    }
    
    /** Change this VoidWorld (and produce a <tt>VoidPackage</tt>
     * possibly containing a message) when a key event is
     * triggered. The given <tt>event</tt> is a <tt>String</tt> that
     * describes which key was pressed.
     *
     * <p>
     *   <b>Special Key</b>
     * <table class='events'>
     *    <tr><td style="text-align:right"><tt class='str'>"up"</tt> : </td>
     *        <td>The user presses the <i>up-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"down"</tt> : </td>
     *        <td>The user presses the <i>down-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"left"</tt> : </td>
     *        <td>The user presses the <i>left-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"right"</tt> : </td>
     *        <td>The user presses the <i>right-arrow</i> key</td></tr>
     * </table>
     *
     * Other keys generate a single character <tt>String</tt> that
     * represents the key pressed. For example, Pressing the <i>B</i> key on
     * the keyboard generates <tt class='str'>"b"</tt> as an event.
     * If the shift key is held while pressing <i>B</i> then <tt
     * class='str'>"B"</tt> is generated.
     * </p>
     */
    public VoidPackage<Msg> onKey(String ke){
        return new VoidPackage<Msg>();    
    }

    /** Change this VoidWorld (and produce a <tt>VoidPackage</tt>
     * possibly containing a message) when a key is
     * <i>released</i>. The given <tt>event</tt> is a <tt>String</tt>
     * that describes which key was released.
     *
     * <p>
     *   <b>Special Keys</b>
     * <table class='events'>
     *    <tr><td style="text-align:right"><tt class='str'>"up"</tt> : </td>
     *        <td>The user presses the <i>up-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"down"</tt> : </td>
     *        <td>The user presses the <i>down-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"left"</tt> : </td>
     *        <td>The user presses the <i>left-arrow</i> key</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"right"</tt> : </td>
     *        <td>The user presses the <i>right-arrow</i> key</td></tr>
     * </table>
     *
     * Other keys generate a single character <tt>String</tt> that
     * represents the key released. For example, Pressing then releasing the <i>B</i> key on
     * the keyboard generates <tt class='str'>"b"</tt> as an <tt>onKey</tt> event and again
     * as an <tt>onRelease</tt> event.  If the shift key is held while pressing/releasing <i>B</i> then <tt
     * class='str'>"B"</tt> is generated.
     * </p>
     */
    public VoidPackage<Msg> onRelease(String ke){
        return new VoidPackage<Msg>();    
    }

    /** Change this VoidWorld (and produce a <tt>VoidPackage</tt>
     * possibly containing a message) when a message from the universe
     * is received. */
    public VoidPackage<Msg> onReceive(Msg msg){
        return new VoidPackage<Msg>();    
    }
    
    /** Determine if the World/interaction/animation should be
     * stopped.  Returning a value of <tt class='keyw'>true</tt>
     * discontinues all events (mouse, key, ticks) and causes {@link
     * universe.world.VoidWorld#lastScene} to be used to draw the final
     * <tt>Scene</tt>. */
    public boolean stopWhen(){ return false; }
    
    /** Returns the <tt>Scene</tt> that should be displayed when the
     * interaction/animation completes ({@link universe.world.VoidWorld#stopWhen}
     * returns <tt class='keyw'>true</tt>). */
    public Scene lastScene(){ return this.onDraw(); }
    
    /** Wraps a VoidWorld in a regular World to simplify event/message/handling */
    private class WorldWrap extends World<Msg>{
        public Scene onDraw(){ return VoidWorld.this.onDraw(); }
        public double tickRate(){ return VoidWorld.this.tickRate(); }
        public Package<Msg> onTick()
        { return new Package<Msg>(this, VoidWorld.this.onTick().getMsg()); }
        public Package<Msg> onMouse(int x, int y, String me)
        { return new Package<Msg>(this, VoidWorld.this.onMouse(x,y,me).getMsg()); }
        public Package<Msg> onKey(String ke)
        { return new Package<Msg>(this, VoidWorld.this.onKey(ke).getMsg()); }
        public Package<Msg> onRelease(String ke)
        { return new Package<Msg>(this, VoidWorld.this.onRelease(ke).getMsg()); }
        public Package<Msg> onReceive(Msg msg)
        { return new Package<Msg>(this, VoidWorld.this.onReceive(msg).getMsg()); }
        public boolean stopWhen(){ return VoidWorld.this.stopWhen(); }
        public Scene lastScene(){ return VoidWorld.this.lastScene(); }
        public boolean equals(Object o){ return false; }
    }
    
    /** Kick off the interaction/animation and connect to the given
     *    Universe server URL, under the given client name.  This
     *    method returns the final state of the world after the user
     *    closes the World window. */
    public VoidWorld<Msg> bigBang(String server, String name){
        new WorldWrap().bigBang(server, name);
        return this;
    }
    /** Kick off the interaction/animation without connecting to the
     *    Universe.  This method returns the final state of the world
     *    after the user closes the World window.  The Universe is not
     *    contacted, but the client is run locally. */
    public VoidWorld<Msg> bigBang(){
        new WorldWrap().bigBang();
        return this;
    }
}


