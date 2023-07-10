/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/World.java                                            *
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

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

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
 *  the world and handling various events.  In order to implement a
 *  functioning World you must <i>extend</i> this class, and implement
 *  an {@link world.World#onDraw onDraw} method.  Other handler
 *  methods ({@link world.World#tickRate tickRate}, {@link
 *  world.World#onTick onTick}, {@link world.World#onMouse onMouse},
 *  {@link world.World#onKey onKey}, {@link world.World#onRelease
 *  onRelease}, {@link world.World#stopWhen stopWhen}, and {@link
 *  world.World#lastScene lastScene}) are optional, and can be
 *  overridden to add new functionality.
 *
 *  <p> See the individual methods for detailed documentation.  </p>
 * 
 * <p>
 * <h3>Displaying Images/Scenes</h3>
 * <blockquote>
 * Simple and generated images can be displayed in a Window using the <code class='keyw'>static</code>
 * method {@link world.World#display(Image)}.  
 * 
 * <pre>
 *     <span class="keyw">import</span><span class="def"> world.World;
 *     </span><span class="keyw">import</span><span class="def"> image.*;
 * 
 *     </span><span class="keyw">public</span><span class="def"> </span><span class="keyw">class</span><span class="def"> DisplayTest{
 *         </span><span class="keyw">public</span><span class="def"> </span><span class="keyw">static</span><span class="def"> </span><span class="keyw">void</span><span class="def"> </span><span class="func">main</span><span class="def">(</span><span class="prim">String</span><span class="def">[] args){
 *             World.</span><span class="func">display</span><span class="def">(</span><span class="keyw">new</span><span class="def"> </span><span class="func">EmptyScene</span><span class="def">(</span><span class="num">200</span><span class="def">, </span><span class="num">200</span><span class="def">)
 *                                .</span><span class="func">placeImage</span><span class="def">(</span><span class="keyw">new</span><span class="def"> </span><span class="func">Overlay</span><span class="def">(</span><span class="keyw">new</span><span class="def"> </span><span class="func">Star</span><span class="def">(</span><span class="num">50</span><span class="def">, </span><span class="num">30</span><span class="def">, </span><span class="num">10</span><span class="def">, </span><span class="str">"outline"</span><span class="def">, </span><span class="str">"black"</span><span class="def">),
 *                                                        </span><span class="keyw">new</span><span class="def"> </span><span class="func">Star</span><span class="def">(</span><span class="num">50</span><span class="def">, </span><span class="num">30</span><span class="def">, </span><span class="num">10</span><span class="def">, </span><span class="str">"solid"</span><span class="def">, </span><span class="str">"green"</span><span class="def">)),
 *                                            </span><span class="num">100</span><span class="def">, </span><span class="num">100</span><span class="def">));
 *         }
 *     }</span>
 * </pre>
 * 
 * When run, the program opens a window that looks something like this:<br/><br/>
 * 
 *   <img class="example" src="test/display.png" />
 * </blockquote>
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
 *        <span class="keyw">import</span> image.*;
 *        <span class="keyw">import</span> world.World;
 *        
 *        <span class="keyw">public</span> <span class="keyw">class</span> MousePointsWorld <span class="keyw">extends</span> World{
 *            <span class="com">// Simple Main Program</span>
 *            <span class="keyw">public</span> <span class="keyw">static</span> <span class="keyw">void</span> <span class="func">main</span>(<span class="prim">String</span>[] args){
 *                <span class="keyw">new</span> <span class="func">MousePointsWorld</span>( = <span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">200</span>, <span class="num">200</span>))
 *                     .<span class="func">bigBang</span>();
 *            }
 *            
 *            <span class="com">// The inner Scene</span>
 *            Scene scene;
 *        
 *            <span class="com">// Create a new World</span>
 *            <span class="func">MousePointsWorld</span>(Scene scene){
 *                 <span class="keyw">this</span>.scene = scene;
 *            }
 *            
 *            <span class="com">// Draw by returning the inner Scene</span>
 *            <span class="keyw">public</span> Scene <span class="func">onDraw</span>(){ <span class="keyw">return</span> <span class="keyw">this</span>.scene; }
 *        
 *            <span class="com">// On a mouse click add a circle to the inner Scene</span>
 *            <span class="keyw">public</span> <span>MouseWorld</span> <span class="func">onMouse</span>(<span class="keyw">int</span> x, <span class="keyw">int</span> y, <span class="prim">String</span> me){
 *                <span class="keyw">if</span>(me.<span class="func">equals</span>(<span class="str">"button-down"</span>)){
 *                    <span class="keyw">return</span> <span class="keyw">new</span> <span class="func">MousePointsWorld</span>(<span class="keyw">this</span>.scene.<span class="func">placeImage</span>(
 *                                     <span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"solid"</span>, <span class="str">"red"</span>)
 *                                           .<span class="func">overlay</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"outline"</span>, <span class="str">"black"</span>)), x, y));
 *                }
 *            }
 *        }
 * </pre>
 * 
 * After a few mouse clicks, the window will look something like this:<br/><br/>
 * 
 *   <img class="example" src="test/mouse-points.png" />
 * </blockquote>
 * </p>    
 */
public abstract class World{
    
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
    
    /** Produce a (possibly) new World based on the Tick of the clock.
     *  This method is called to get the <i>next world</i> on each
     *  clock tick.*/
    public World onTick(){ return this; }
    
    /** Produce a (possibly) new World when a mouse event is
     * triggered.  <tt>x</tt> and <tt>y</tt> are the location of the
     * event in the window, and <tt>event</tt> is a <tt>String</tt>
     * that describes what kind of event occurred.
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
    public World onMouse(int x, int y, String event){ return this; }
    
    /** Produce a (possibly) new World when a key is
     * pressed. The given <tt>event</tt> is a <tt>String</tt> that
     * describes what key was pressed.
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
    public World onKey(String event){ return this; }
    
    /** Produce a (possibly) new World when a key is released. The given <tt>event</tt>
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
     * represents the key <i>released</i>. For example, Pressing then
     * releasing the <i>B</i> key on the keyboard generates <tt
     * class='str'>"b"</tt> as an <tt>onKey</tt> event and again as an
     * <tt>onRelease</tt> event.  If the shift key is held while
     * pressing/releasing <i>B</i> then <tt class='str'>"B"</tt> is
     * generated.  </p>
     */
    public World onRelease(String event){ return this; }
    
    /** Determine if the World/interaction/animation should be
     * stopped.  Returning a value of <tt class='keyw'>true</tt>
     * discontinues all events (mouse, key, ticks) and causes {@link
     * world.World#lastScene} to be used to draw the final
     * <tt>Scene</tt>.
     */
    public boolean stopWhen(){ return false; }
    
    /** Returns the <tt>Scene</tt> that should be displayed when the
     * interaction/animation completes ({@link world.World#stopWhen}
     * returns <tt class='keyw'>true</tt>). */
    public Scene lastScene(){ return this.onDraw(); }
    
    /** Kick off the interaction/animation.  This method returns the final
     *    state of the world after the user closes the World window. */
    public World bigBang(){
        return (World)new BigBang(this)
            .onDraw(new WorldDraw())
            .onTick(new WorldTick(), tickRate())
            .onMouse(new WorldMouse())
            .onKey(new WorldKey())
            .onRelease(new WorldRelease())
            .stopWhen(new WorldStop())
            .lastScene(new WorldLast())
            .bigBang("World");
    }
    
    /** Wrapper for OnDraw callback */
    private static class WorldDraw{
        @SuppressWarnings("unused")
        Scene apply(World w){ return w.onDraw(); }
    }
    /** Wrapper for OnTick callback */
    private static class WorldTick{
        @SuppressWarnings("unused")
        World apply(World w){ return w.onTick(); }
    }
    /** Wrapper for OnMouse callback */
    private static class WorldMouse{
        @SuppressWarnings("unused")
        World apply(World w, int x, int y, String me)
        { return w.onMouse(x,y,me); }
    }
    /** Wrapper for OnKey callback */
    private static class WorldKey{
        @SuppressWarnings("unused")
        World apply(World w, String ke){ return w.onKey(ke); }
    }
    /** Wrapper for OnRelease callback */
    private static class WorldRelease{
        @SuppressWarnings("unused")
        World apply(World w, String ke){ return w.onRelease(ke); }
    }
    /** Wrapper for StopWhen callback */
    private static class WorldStop{
        @SuppressWarnings("unused")
        boolean apply(World w){ return w.stopWhen(); }
    }
    /** Wrapper for LastScene callback */
    private static class WorldLast{
        @SuppressWarnings("unused")
        Scene apply(World w){ return w.lastScene(); }
    }
    
    /** Gap left around the border of the Window */
    private static int SPACE = 5;
    
    /** Opens a new Window and displays the given {@link image.Image}/{@link image.Scene}, returns
     *    <tt class='keyw'>true</tt> once the window is closed. */
    public static boolean display(Image i){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}

        JDialog f = new JDialog((JFrame)null, "Display", true);
        Scene scn = i.toScene();
        f.setSize((int)(SPACE*2+Math.max(20, 14+scn.width())),
                (int)(Math.max(20, SPACE*2+31+scn.height())));
        
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.getContentPane().add(new SceneComponent(scn));
        f.setVisible(true);
        return true;
    }
    /** Component that displays and supports saving of an image */
    private static class SceneComponent extends JComponent implements MouseListener{
        private static final long serialVersionUID = 1L;

        Scene scn;
        public SceneComponent(Scene s){
            this.scn = s;
            this.addMouseListener(this);
        }
        
        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2.setColor(Color.white);
            g2.fillRect(0,0, this.getWidth(), this.getHeight());
            g2.clipRect(SPACE, SPACE, (int)(this.scn.width()), (int)(this.scn.height())); 
            this.scn.paint(g2,SPACE,SPACE);
        }
        
        /** Support saving screenshots... */
        private JPopupMenu popup = new JPopupMenu("World Options");
        {
            addItem(this.popup, "Save Image...", new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    doSaveAs(SceneComponent.this.scn);
                    synchronized(SceneComponent.this.popup){ SceneComponent.this.popup.notify(); }
                }
            });
            this.popup.addPopupMenuListener(new PopupMenuListener(){
                public void popupMenuCanceled(PopupMenuEvent arg0){
                    synchronized(SceneComponent.this.popup){ SceneComponent.this.popup.notify(); }
                }
                public void popupMenuWillBecomeVisible(PopupMenuEvent arg0){}
                public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0){}
            });
        }
        private static void addItem(JPopupMenu m, String s, ActionListener l){
            JMenuItem item = new JMenuItem(s);
            if(l != null)
                item.addActionListener(l);
            m.add(item);
        }
        /** Ask the user for a file... then save the image */
        private boolean doSaveAs(Scene scn){
            try{
                JFileChooser fc = new JFileChooser();
                int result = fc.showDialog(this, "SaveAs");
                // Should check for existence... 
                if(result == JFileChooser.APPROVE_OPTION){
                    scn.toFile(fc.getSelectedFile().getAbsolutePath());
                    return true;
                }
            }catch(Exception e){
                System.err.println("");
            }
            return false;
        }
        /** Show the popup if it is "triggered" */
        public void mousePressed(final MouseEvent e){
            if(e.isPopupTrigger()){
                final SceneComponent that = this;
                
                // Show the context Menu
                new Thread(){
                    public void run(){
                        SceneComponent.this.popup.show(that, e.getX(), e.getY());
                        try{
                            synchronized(SceneComponent.this.popup){
                                Thread.yield();
                                SceneComponent.this.popup.wait();
                            }
                        }catch(Exception ee){}
                    }
                }.start();
            }
        }
        /* Mouse click/move/event Methods */
        public void mouseClicked(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    }
}


