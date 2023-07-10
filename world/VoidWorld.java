/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/VoidWorld.java                                        *
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

package world;

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
 *  A Class representing an imperative World and the related methods
 *  for drawing the world and handling various events.  In order to
 *  implement a functioning World you must <i>extend</i> this class,
 *  and implement an {@link world.VoidWorld#onDraw onDraw} method.
 *  Other handler methods ({@link world.VoidWorld#tickRate tickRate},
 *  {@link world.VoidWorld#onTick onTick}, {@link
 *  world.VoidWorld#onMouse onMouse}, {@link world.VoidWorld#onKey
 *  onKey}, {@link world.VoidWorld#onRelease onRelease}, {@link
 *  world.VoidWorld#stopWhen stopWhen}, and {@link
 *  world.VoidWorld#lastScene lastScene}) are optional, and can be
 *  overridden to add new functionality.  <p> See the individual
 *  methods for detailed documentation.  </p>
 * 
 * <p>
 * <h3>Extending VoidWorld</h3>
 * <blockquote>
 * Below is a simple example of a <code>VoidWorld</code> that adds a
 * new point at each mouse click.  The world contains a {@link
 * image.Scene Scene} and a new {@link image.Circle Circle} is placed
 * for each <code class='str'>"button-down"</code> event received.
 * 
 * <pre>   
 *        <span class="keyw">import</span> image.*;
 *        <span class="keyw">import</span> world.VoidWorld;
 *        
 *        <span class="keyw">public</span> <span class="keyw">class</span> MousePointsVoidWorld <span class="keyw">extends</span> VoidWorld{
 *            <span class="com">// Simple Main Program</span>
 *            <span class="keyw">public</span> <span class="keyw">static</span> <span class="keyw">void</span> <span class="func">main</span>(<span class="prim">String</span>[] args)
 *            { <span class="keyw">new</span> <span class="func">MousePointsVoidWorld</span>().<span class="func">bigBang</span>(); }
 *            
 *            <span class="com">// The inner Scene</span>
 *            Scene scene = <span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">200</span>, <span class="num">200</span>);
 *        
 *            <span class="com">// Create a new World</span>
 *            <span class="func">MousePointsVoidWorld</span>(){}
 *            
 *            <span class="com">// Draw by returning the inner Scene</span>
 *            <span class="keyw">public</span> Scene <span class="func">onDraw</span>(){ <span class="keyw">return</span> <span class="keyw">this</span>.scene; }
 *        
 *            <span class="com">// On a mouse click add a circle to the inner Scene</span>
 *            <span class="keyw">public</span> <span class="keyw">void</span> <span class="func">onMouse</span>(<span class="keyw">int</span> x, <span class="keyw">int</span> y, <span class="prim">String</span> me){
 *                <span class="keyw">if</span>(me.<span class="func">equals</span>(<span class="str">"button-down"</span>)){
 *                    <span class="keyw">this</span>.scene = <span class="keyw">this</span>.scene.<span class="func">placeImage</span>(
 *                                     <span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"solid"</span>, <span class="str">"red"</span>)
 *                                           .<span class="func">overlay</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"outline"</span>, <span class="str">"black"</span>)), x, y);
 *                }
 *            }
 *        }
 * </pre>
 * 
 * After a few mouse clicks, the window will look something like this:<br/><br/>
 * 
 *   <img class="example" src="test/void-mouse-points.png" />
 * </blockquote>
 * </p>    
 */
public abstract class VoidWorld{
    
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
     *  method is called to update the VoidWorld on each clock tick. */
    public void onTick(){ }
    
    /** Change this VoidWorld when a mouse event is triggered.
     * <tt>x</tt> and <tt>y</tt> are the location of the event in the window, and 
     * <tt>event</tt> is a <tt>String</tt> that describes what kind of event
     * occurred.
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
    public void onMouse(int x, int y, String event){ }
    
    /** Change this VoidWorld when a key event is
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
    public void onKey(String event){ }

    /** Change this VoidWorld when a key is <i>released</i>. The given <tt>event</tt>
     * is a <tt>String</tt> that describes which key was released.
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
    public void onRelease(String event){ }

    
    /** Determine if the World/interaction/animation should be
     * stopped.  Returning a value of <tt class='keyw'>true</tt>
     * discontinues all events (mouse, key, ticks) and causes {@link
     * world.VoidWorld#lastScene} to be used to draw the final
     * <tt>Scene</tt>. */
    public boolean stopWhen(){ return false; }
    
    /** Returns the <tt>Scene</tt> that should be displayed when the
     * interaction/animation completes ({@link world.VoidWorld#stopWhen}
     * returns <tt class='keyw'>true</tt>). */
    public Scene lastScene(){ return this.onDraw(); }
    
    /** Kick off the interaction/animation.  This method returns the final
     *    state of the world after the user closes the World window. */
    public VoidWorld bigBang(){
        return (VoidWorld)new BigBang(this)
            .onDraw(new WorldDraw())
            .onTick(new WorldTick(), tickRate())
            .onMouse(new WorldMouse())
            .onKey(new WorldKey())
            .onRelease(new WorldRelease())
            .stopWhen(new WorldStop())
            .lastScene(new WorldLast())
            .bigBang("VoidWorld");
    }
    
    /** Wrapper for OnDraw callback */
    private static class WorldDraw{
        @SuppressWarnings("unused")
        Scene apply(VoidWorld w){ return w.onDraw(); }
    }
    /** Wrapper for OnTick callback */
    private static class WorldTick{
        @SuppressWarnings("unused")
        VoidWorld apply(VoidWorld w){ w.onTick(); return w; }
    }
    /** Wrapper for OnMouse callback */
    private static class WorldMouse{
        @SuppressWarnings("unused")
        VoidWorld apply(VoidWorld w, int x, int y, String me)
        { w.onMouse(x,y,me); return w; }
    }
    /** Wrapper for OnKey callback */
    private static class WorldKey{
        @SuppressWarnings("unused")
        VoidWorld apply(VoidWorld w, String ke){ w.onKey(ke); return w; }
    }
    /** Wrapper for OnKey callback */
    private static class WorldRelease{
        @SuppressWarnings("unused")
        VoidWorld apply(VoidWorld w, String ke){ w.onRelease(ke); return w; }
    }
    /** Wrapper for StopWhen callback */
    private static class WorldStop{
        @SuppressWarnings("unused")
        boolean apply(VoidWorld w){ return w.stopWhen(); }
    }
    /** Wrapper for LastScene callback */
    private static class WorldLast{
        @SuppressWarnings("unused")
        Scene apply(VoidWorld w){ return w.lastScene(); }
    }
    /** Make sure that changes are redrawn every time */
    public boolean equals(Object o){ return false; }
}


