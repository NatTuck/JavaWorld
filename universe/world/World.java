/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/world/World.java                                   *
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

import universe.Package;
import universe.world.base.*;
import image.Image;
import image.Scene;

/**
 * <style type='text/css'><!--
 *    .def{ color: #000000; }
 *    .com{ font-style: italic; color: #880000; }
 *    .keyw{ font-weight: bold; color: #000088; }
 *    .num{ color: #00AA00; }
 *    .str{ color: #CC00AB; }
 *    .prim{ color: #0000FF; }
 *    .func{ color: #BB7733; }
 *    img.example{ padding-left: 50px; padding-bottom: 30px; }
 *    table.events td{ padding-bottom: 20px; }
 *    table.events{ padding-left: 30px; padding-bottom: 20px; }
 *  --></style>
 *  
 *  A Class representing a World and the related methods for drawing
 *  the world and handling various events connected to a {@link
 *  universe.Universe Universe}.  The class is parameterized by the
 *  type of messages (which must <tt class='keyw'>extend</tt> {@link
 *  java.io.Serializable Serializable}) that are passed throughout the
 *  Universe.
 *    
 * <p> In order to implement a functioning World you must
 *  <i>extend</i> this class, and implement an {@link
 *  universe.world.World#onDraw onDraw} method.  Other handler methods
 *  ({@link universe.world.World#tickRate tickRate}, {@link
 *  universe.world.World#onTick onTick}, {@link
 *  universe.world.World#onMouse onMouse}, {@link
 *  universe.world.World#onKey onKey}, {@link
 *  universe.world.World#onRelease onRelease}, {@link
 *  universe.world.World#stopWhen stopWhen}, and {@link
 *  universe.world.World#lastScene lastScene}) are optional, and can
 *  be overridden to add new functionality.  </p>
 *
 * <p> Most handler methods return a {@link universe.Package Package},
 *  which contains the updated <tt>World</tt>, and a possible message
 *  to the connected <tt>Universe</tt>.  In order to interact with the
 *  <tt>Universe</tt> and other <tt>World</tt>s, implementations must
 *  override the {@link universe.world.World#onReceive onReceive}
 *  method that handles messages from the <tt>Universe</tt>.  </p>
 *
 * <p>
 *  See also {@link world.World world.World} for similar documentation on individual methods.
 * </p>
 *      
 * <p>
 * <h3>Extending World</h3>
 * <blockquote>
 * Below is a simple example of a <code>World</code> that adds a new point at each mouse click.  The world
 * contains a {@link image.Scene Scene} and a new {@link image.Circle Circle} is placed for each
 * <code class='str'>"button-down"</code> event received. 
 * 
 * 
 * <pre>   
 * <span class="keyw">import</span> image.*;
 * <span class="keyw">import</span> universe.Package;
 * <span class="keyw">import</span> universe.world.*;
 * 
 * <span class="com">// Mouse World/Universe point/click Test</span>
 * <span class="keyw">public</span> <span class="keyw">class</span> MousePointsWorld <span class="keyw">extends</span> World&lt;None&gt;{
 *     <span class="keyw">public</span> <span class="keyw">static</span> <span class="keyw">void</span> <span class="func">main</span>(<span class="prim">String</span>[] s){
 *         <span class="keyw">new</span> <span class="func">MousePointsWorld</span>(<span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">400</span>,<span class="num">400</span>))
 *               .<span class="func">bigBang</span>();
 *     }
 *     <span class="com">// The inner Scene</span>
 *     Scene scene;
 *     <span class="com">// Create a new World with the given Scene</span>
 *     <span class="func">MousePointsWorld</span>(Scene scene){
 *         <span class="keyw">this</span>.scene = scene;    
 *     }
 *     <span class="com">// Draw by returning the inner Scene</span>
 *     <span class="keyw">public</span> Scene <span class="func">onDraw</span>(){ <span class="keyw">return</span> <span class="keyw">this</span>.scene; }
 *     <span class="com">// On a mouse click add a circle to the inner Scene</span>
 *     <span class="keyw">public</span> Package&lt;None&gt; <span class="func">onMouse</span>(<span class="keyw">int</span> x, <span class="keyw">int</span> y, <span class="prim">String</span> me){
 *         <span class="keyw">if</span>(!me.<span class="func">equals</span>(<span class="str">"button-down"</span>))
 *             <span class="keyw">return</span> <span class="keyw">new</span> Package&lt;None&gt;(<span class="keyw">this</span>);
 *         <span class="keyw">else</span>
 *             <span class="keyw">return</span> <span class="keyw">new</span> Package&lt;None&gt;(
 *                     <span class="keyw">new</span> <span class="func">MousePointsWorld</span>(
 *                             <span class="keyw">this</span>.scene.<span class="func">placeImage</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"solid"</span>,<span class="str">"red"</span>)
 *                                       .<span class="func">overlay</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"outline"</span>, <span class="str">"blue"</span>)), x, y)));
 *     }
 * }
 * </pre>
 * 
 * After a few mouse clicks, the window will look something like this:<br/><br/>
 * 
 *   <img class="example" src="test/mouse-points.png" />
 * </blockquote>
 * </p>    
 */
public abstract class World<Msg extends Serializable>{

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
    
    /** Return a visualization of this <tt>World</tt> as a {@link image.Scene Scene}.
     *    See {@link image.EmptyScene}, {@link image.Scene#placeImage(Image, int, int)}, and
     *    {@link image.Scene#addLine(int, int, int, int, String)} for documentation on
     *    constructing <tt>Scene</tt>s */
    public abstract Scene onDraw();
    
    /** Return the tick rate for this World in <i>seconds</i>.  For example,
     *  <span class='num'>0.5</span> means two <i>ticks</i> per second. The
     *  rate is only accessed when bigBang() is initially called and the
     *  window is created. */
    public double tickRate(){ return DEFAULT_TICK_RATE; }
    
    /** Produce a Package, containing a (possibly) new World and a
     *  possible Message, based on the Tick of the clock.  This method is
     *  called to get the <i>next world</i> on each clock tick.*/
    public Package<Msg> onTick(){
        return new Package<Msg>(this);
    }
    
    /** Produce a Package, containing a (possibly) new World and a
     *  possible Message, when a mouse event is triggered.  <tt>x</tt>
     *  and <tt>y</tt> are the location of the event in the window,
     *  and <tt>event</tt> is a <tt>String</tt> that describes what
     *  kind of event occurred.
     * 
     * <p>
     *   <b>Possible Mouse Events</b>
     * <table class='events'>
     *    <tr><td style="text-align:right"><tt class='str'>"button-down"</tt> : </td>
     *        <td>The user <i>presses</i> a mouse button in the World window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"button-up"</tt> : </td>
     *        <td>The user <i>releases</i> a mouse button in the World window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"move"</tt> : </td>
     *        <td>The user <i>moves</i> the mouse in the World window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"drag"</tt> : </td>
     *        <td>The user <i>holds</i> a mouse button and <i>moves</i> the mouse in the World window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"enter"</tt> : </td>
     *        <td>The user moves the mouse <i>in-to</i> the World window</td></tr>
     *    <tr><td style="text-align:right"><tt class='str'>"leave"</tt> : </td>
     *        <td>The user moves the mouse <i>out-of</i> the World window</td></tr>
     * </table>
     * </p>
     */
    public Package<Msg> onMouse(int x, int y, String me){
        return new Package<Msg>(this); }
    
    /** Produce a Package, containing a (possibly) new World and a
     *  possible Message, when a key is pressed. The given
     *  <tt>event</tt> is a <tt>String</tt> that describes what key
     *  was pressed.
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
     * represents the key pressed. For example, Pressing the <i>B</i>
     * key on the keyboard generates <tt class='str'>"b"</tt> as an
     * event.  If the shift key is held while pressing <i>B</i> then
     * <tt class='str'>"B"</tt> is generated.  </p>
     */
    public Package<Msg> onKey(String ke){
        return new Package<Msg>(this);    
    }

    /** Produce a Package, containing a (possibly) new World and a
     *  possible Message, when a key is <i>released</i>. The given
     *  <tt>event</tt> is a <tt>String</tt> that describes which key
     *  was released.
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
     * represents the key released. For example, Pressing then
     * releasing the <i>B</i> key on the keyboard generates <tt
     * class='str'>"b"</tt> as an <tt>onKey</tt> event and again as an
     * <tt>onRelease</tt> event.  If the shift key is held while
     * pressing/releasing <i>B</i> then <tt class='str'>"B"</tt> is
     * generated.  </p>
     */
    public Package<Msg> onRelease(String ke){
        return new Package<Msg>(this);    
    }

    /** Produce a Package, containing a (possibly) new World and a
     *  possible Message, when a message from the universe is
     *  received. */
    public Package<Msg> onReceive(Msg msg){
        return new Package<Msg>(this);    
    }
    
    /** Determine if the World/interaction/animation should be
     * stopped.  Returning a value of <tt class='keyw'>true</tt>
     * discontinues all events (mouse, key, ticks, messages) and
     * causes {@link universe.world.World#lastScene} to be used to
     * draw the final <tt>Scene</tt>.
     */
    public boolean stopWhen(){ return false; }
    
    /** Returns the <tt>Scene</tt> that should be displayed when the
     * interaction/animation completes ({@link universe.world.World#stopWhen}
     * returns <tt class='keyw'>true</tt>). */
    public Scene lastScene(){ return this.onDraw(); }
    
    /** Kick off the interaction/animation and connect to the given
     *    Universe server URL, under the given client name.  This
     *    method returns the final state of the world after the user
     *    closes the World window. */
    public World<Msg> bigBang(String server, String name){
        return new BigBang<Msg>(this)
            .onDraw(new WorldDraw())
            .onTick(new WorldTick(), tickRate())
            .onMouse(new WorldMouse())
            .onKey(new WorldKey())
            .onRelease(new WorldRelease())
            .onReceive(new WorldReceive())
            .stopWhen(new WorldStop())
            .lastScene(new WorldLast())
            .register(server)
            .name(name)
            .bigBang();
    }
    /** Kick off the interaction/animation without connecting to the
     *    Universe.  This method returns the final state of the world
     *    after the user closes the World window.  The Universe is not
     *    contacted, but the client is run locally*/
    public World<Msg> bigBang(){
        return new BigBang<Msg>(this)
            .onDraw(new WorldDraw())
            .onTick(new WorldTick(), tickRate())
            .onMouse(new WorldMouse())
            .onKey(new WorldKey())
            .onRelease(new WorldRelease())
            .stopWhen(new WorldStop())
            .lastScene(new WorldLast())
            .bigBang();
    }
    
    /** Wrapper for OnDraw callback */
    private class WorldDraw implements OnDraw{
        public Scene apply(World<?> w){ return w.onDraw(); }
    }
    /** Wrapper for OnTick callback */
    private class WorldTick implements OnTick<Msg>{
        public Package<Msg> apply(World<Msg> w){ return w.onTick(); }
    }
    /** Wrapper for OnMouse callback */
    private class WorldMouse implements OnMouse<Msg>{
        public Package<Msg> apply(World<Msg> w, int x, int y, String me)
        { return w.onMouse(x,y,me); }
    }
    /** Wrapper for OnKey callback */
    private class WorldKey implements OnKey<Msg>{
        public Package<Msg> apply(World<Msg> w, String ke){ return w.onKey(ke); }
    }
    /** Wrapper for OnKey callback */
    private class WorldRelease implements OnRelease<Msg>{
        public Package<Msg> apply(World<Msg> w, String ke){ return w.onRelease(ke); }
    }
    /** Wrapper for OnReceive callback */
    private class WorldReceive implements OnReceive<Msg>{
        public Package<Msg> apply(World<Msg> w, Msg msg){ return w.onReceive(msg); }
    }
    /** Wrapper for StopWhen callback */
    private class WorldStop implements StopWhen{
        public boolean apply(World<?> w){ return w.stopWhen(); }
    }
    /** Wrapper for LastScene callback */
    private class WorldLast implements LastScene{
        public Scene apply(World<?> w){ return w.lastScene(); }
    }
}


