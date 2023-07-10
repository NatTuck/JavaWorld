/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/BigBang.java                                          *
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

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import util.Util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import image.*;

/** A Class representing the creation of a World/System of some type, and the
 *    related methods and Function
 *    Objects (call-backs) for drawing the world and handling various events.  As
 *    handlers are installed, each is checked for a corresponding <tt>apply</tt>
 *    method with the appropriate signature.
 *    
 *  <p>The initial value of the World assigns a (minimum) <i>type</i>, which is
 *    used to search/check all of the handlers.  Functions that produce a world
 *    deserve special attention, since they may return a super-type of the
 *    initial World (e.g., initial <tt>EmptyScene</tt>, with an tick handler that
 *    returns a <tt>Scene</tt>).  The name and types of handlers are given in the
 *    table below:<br/><br/>
 *    
 *    <style>
 *       table.mine{ margin-left: 20px; border: 1px solid blue; }
 *       table.mine td, table.mine th{ padding-left:20px; padding-right:5px;  border: 1px solid blue; }
 *       td.event, th.event{ text-align: center; }
 *       .com { color: #AA240F; font-style: italic; }
 *       .keyw { color: #262680; font-weight: bold; }
 *       .useful { color: #1111C0; }
 *       .num { color: #00AA00; }
 *       .str { color: #00AA00; }
 *       .fun { color: #AA5500; }
 *    </style>
 *    <table class="mine">
 *      <tr><th class="event">Event Name</th><th>BigBang Method</th><th>Handler Signature</th><th>Required?</th><tr/>
 *      <tr><td class="event">OnDraw</td><td><tt><span class='fun'>onDraw</span>(<i>handler</i>)</tt></td><td><tt>Scene <span class='fun'>apply</span>(World w)</tt></td><td><b><i>yes</i></b></td><tr/>
 *      <tr><td class="event">OnTick</td><td><tt><span class='fun'>onTick</span>(<i>handler</i>)</tt> or<br/> <tt><span class='fun'>onTick</span>(<i>handler</i>, <span class="keyw">double</span>)</tt></td><td><tt>World <span class='fun'>apply</span>(World w)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnMouse</td><td><tt><span class='fun'>onMouse</span>(<i>handler</i>)</tt></td><td><tt>World <span class='fun'>apply</span>(World w, <span class="keyw">int</span> x, <span class="keyw">int</span> y, String what)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnKey</td><td><tt><span class='fun'>onKey</span>(<i>handler</i>)</tt></td><td><tt>World <span class='fun'>apply</span>(World w, String key)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnRelease</td><td><tt><span class='fun'>onRelease</span>(<i>handler</i>)</tt></td><td><tt>World <span class='fun'>apply</span>(World w, String key)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">StopWhen</td><td><tt><span class='fun'>stopWhen</span>(<i>handler</i>)</tt></td><td><tt><span class="keyw">boolean</span> <span class='fun'>apply</span>(World w)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">LastScene</td><td><tt><span class='fun'>lastScene</span>(<i>handler</i>)</tt></td><td><tt>Scene <span class='fun'>apply</span>(World w)</tt></td><td>no</td><tr/>
 *    </table><br/>
 *    
 *    If a matching method is not found when installing handlers, a RuntimeException is
 *    thrown, describing the offense.
 *    </p>
 */
public class BigBang{

    /** Default Tick rate for the world: ~33 frames per second */
    public static double DEFAULT_TICK_RATE = 0.03;

    /** Mouse down (button-down) event String */
    public static String MOUSE_DOWN = "button-down";
    /** Mouse up (button-up) event String */
    public static String MOUSE_UP = "button-up";
    /** Mouse window enter (enter) event String */
    public static String MOUSE_ENTER = "enter";
    /** Mouse window leave (leave) event String */
    public static String MOUSE_LEAVE = "leave";
    /** Mouse motion (move) event String */
    public static String MOUSE_MOVE = "move";
    /** Mouse down & move (drag) event String */
    public static String MOUSE_DRAG = "drag";   
    /** Key arrow-up event String */
    public static String KEY_ARROW_UP = "up";   
    /** Key arrow-down event String */
    public static String KEY_ARROW_DOWN = "down";   
    /** Key arrow-left event String */
    public static String KEY_ARROW_LEFT = "left";   
    /** Key arrow-right event String */
    public static String KEY_ARROW_RIGHT = "right";   
    /** Key escape event String */
    public static String KEY_ESCAPE = "escape";   

    private Object initial;
    private Class<?> worldType;
    private double time;
    
    // Handlers and their corresponding selected Method
    protected Object ondraw;
    protected Method ondrawM;
    protected Object ontick;
    protected Method ontickM;
    protected Object onmouse;
    protected Method onmouseM;
    protected Object onkey;
    protected Method onkeyM;
    protected Object onrelease;
    protected Method onreleaseM;
    protected Object stopwhen;
    protected Method stopwhenM;
    protected Object lastscene;
    protected Method lastsceneM;
    
    
    /** Create a new BigBang with a value of the initial World */
    public BigBang(Object initial){
        this(initial, initial.getClass(), 0.02,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null);
    }
    /** Install a Draw Handler into this BigBang.  The Draw handler
     *    requires an apply method [World -&gt; Scene], though the
     *    requirement is checked dynamically when this method is
     *    called. */
    public BigBang onDraw(Object ondraw){
        Method ondrawM = checkTypes(ondraw, new Class[]{this.worldType}, Scene.class, "OnDraw", false, false);
        return new BigBang(this.initial, this.worldType, this.time,
                ondraw, ondrawM, this.ontick, this.ontickM,
                this.onmouse, this.onmouseM, this.onkey, this.onkeyM, this.onrelease, this.onreleaseM,
                this.stopwhen, this.stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a Tick Handler at a tick rate of 1/20th of a second. */
    public BigBang onTick(Object ontick){
        return onTick(ontick, 0.05);
    }
    /** Install a Tick Handler into this BigBang at the given tick
     *    rate (per-seconds).  The Tick handler requires an apply
     *    method [World -&gt; World], though the requirement is
     *    checked dynamically when this method is called. */
    public BigBang onTick(Object ontick, double time){
        Method ontickM = checkTypes(ontick, new Class[]{this.worldType}, this.worldType, "OnTick", true, true);
        return new BigBang(this.initial, this.worldType, time,
                this.ondraw, this.ondrawM, ontick, ontickM,
                this.onmouse, this.onmouseM, this.onkey, this.onkeyM, this.onrelease, this.onreleaseM,
                this.stopwhen, this.stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a Mouse Handler into this BigBang.  The Mouse handler
     *    requires an apply method [World -&gt; World], though the
     *    requirement is checked dynamically when this method is
     *    called. */
    public BigBang onMouse(Object onmouse){
        Method onmouseM = checkTypes(onmouse, new Class[]{this.worldType, int.class, int.class, String.class}, this.worldType, "OnMouse", true, true);
        return new BigBang(this.initial, this.worldType, this.time,
                this.ondraw, this.ondrawM, this.ontick, this.ontickM,
                onmouse, onmouseM, this.onkey, this.onkeyM, this.onrelease, this.onreleaseM,
                this.stopwhen, this.stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a Key Handler into this BigBang.  The Key handler
     *    requires an apply method [World String -&gt; World], though
     *    the requirement is checked dynamically when this method is
     *    called. */
    public BigBang onKey(Object onkey){
        Method onkeyM = checkTypes(onkey, new Class[]{this.worldType, String.class}, this.worldType, "OnKey", true, true);
        return new BigBang(this.initial, this.worldType, this.time,
                this.ondraw, this.ondrawM, this.ontick, this.ontickM,
                this.onmouse, this.onmouseM, onkey, onkeyM, this.onrelease, this.onreleaseM,
                this.stopwhen, this.stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a Key Release Handler into this BigBang.  The Key
     *    Release handler requires an apply method [World String -&gt;
     *    World], though the requirement is checked dynamically when
     *    this method is called. */
    public BigBang onRelease(Object onrelease){
        Method onreleaseM = checkTypes(onrelease, new Class[]{this.worldType, String.class}, this.worldType, "OnRelease", true, true);
        return new BigBang(this.initial, this.worldType, this.time,
                this.ondraw, this.ondrawM, this.ontick, this.ontickM,
                this.onmouse, this.onmouseM, this.onkey, this.onkeyM, onrelease, onreleaseM,
                this.stopwhen, this.stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a StopWhen Handler into this BigBang.  The StopWhen
     *    handler requires an apply method [World -&gt; Boolean],
     *    though the requirement is checked dynamically when this
     *    method is called.  The StopWhen handler, if installed is
     *    call to determine whether or not the World/animation/events
     *    should be stopped.  When/if the handler returns true then
     *    all events stop being received and the LastScene handler is
     *    given a chance to draw the final World. */
    public BigBang stopWhen(Object stopwhen){
        Method stopwhenM = checkTypes(stopwhen, new Class[]{this.worldType}, Boolean.class, "StopWhen", true, false);
        return new BigBang(this.initial, this.worldType, this.time,
                this.ondraw, this.ondrawM, this.ontick, this.ontickM,
                this.onmouse, this.onmouseM, this.onkey, this.onkeyM, this.onrelease, this.onreleaseM,
                stopwhen, stopwhenM, this.lastscene, this.lastsceneM);
    }
    /** Install a LastScene Handler into this BigBang.  The LastScene
     *    handler requires an apply method [World -&gt; Scene], though
     *    the requirement is checked dynamically when this method is
     *    called.  After the animation is stopped (StopWhen) the final
     *    World is drawn using the LstScene Handler. */
    public BigBang lastScene(Object lastscene){
        Method lastsceneM = checkTypes(lastscene, new Class[]{this.worldType}, Scene.class, "LastScene", true, false);
        return new BigBang(this.initial, this.worldType, this.time,
                this.ondraw, this.ondrawM, this.ontick, this.ontickM,
                this.onmouse, this.onmouseM,
                this.onkey, this.onkeyM, this.onrelease, this.onreleaseM,
                this.stopwhen, this.stopwhenM, lastscene, lastsceneM);
    }
    
    // Private constructor...
    private BigBang(Object init, Class<?> worldT, double time, 
                    Object ondraw, Method ondrawM, Object ontick, Method ontickM,
                    Object onmouse, Method onmouseM, Object onkey, Method onkeyM,
                    Object onrelease, Method onreleaseM, Object stopwhen, Method stopwhenM,
                    Object lastscene, Method lastsceneM){
        this.initial = init;
        this.worldType = worldT;
        this.time = time;
        this.ondraw = ondraw;
        this.ondrawM = ondrawM;
        this.ontick = ontick;
        this.ontickM = ontickM;
        this.onmouse = onmouse;
        this.onmouseM = onmouseM;
        this.onkey = onkey;
        this.onkeyM = onkeyM;
        this.onrelease = onrelease;
        this.onreleaseM = onreleaseM;
        this.stopwhen = stopwhen;
        this.stopwhenM = stopwhenM;
        this.lastscene = lastscene;
        this.lastsceneM = lastsceneM;
    }    
    
    /** Gap left around the border of the Window */
    private static int SPACE = 5;
    /** Check/find a method compatible with the given types in the
     *    given function Object/Handler */
    private Method checkTypes(Object f, Class<?>[] args, Class<?> ret, String what, boolean nullable, boolean wret){
        Class<?> fClass = f.getClass();
        Method[] possibles = fClass.getDeclaredMethods();
        for(Method m : possibles){
            if(m.getName().equals(Util.funcObjMethName) && 
                    Util.subtypes(args, m.getParameterTypes())){
                if(Util.subtype(m.getReturnType(), ret))
                    return m;
                if(Util.subtype(ret, m.getReturnType()) &&
                   !m.getReturnType().equals(Object.class)){
                    this.worldType = m.getReturnType();
                    return m;
                }
            }
        }
        throw Util.exceptionDrop(2, "\n** Function Object ("+fClass.getSimpleName()+") used for "+
                what.toLowerCase()+" does not contain a method sutable for:\n     "+
                ret.getSimpleName()+" "+Util.funcObjMethName+"("+Util.argsString(args,0)+")");
    }
    
    /** Wrapper for the Tick Handler */
    private Object doOnTick(Object w) {
        return Util.applyFunc(this.ontick, this.ontickM, new Object[]{w});
    }
    /** Wrapper for the Mouse Handler */
    private Object doOnMouseEvent(Object w, int x, int y, String me) {
        return Util.applyFunc(this.onmouse, this.onmouseM, new Object[]{w,x-SPACE,y-SPACE,me});
    }
    /** Wrapper for the Key Handler */
    private Object doOnKeyEvent(Object w, String ke){
        if(ke.length() == 0)return w;
        return Util.applyFunc(this.onkey, this.onkeyM, new Object[]{w,ke});
    }
    /** Wrapper for the Key Release Handler */
    private Object doOnReleaseEvent(Object w, String ke){
        if(ke.length() == 0)return w;
        return Util.applyFunc(this.onrelease, this.onreleaseM, new Object[]{w,ke});
    }
    /** Wrapper for the Draw Handler */
    private Scene doOnDraw(Object w) {
        return (Scene)Util.applyFunc(this.ondraw, this.ondrawM, new Object[]{w});
    }
    /** Wrapper for the StopWhen Handler */
    private boolean doStopWhen(Object w) {
        if(this.stopwhen == null)return false;
        return (Boolean)Util.applyFunc(this.stopwhen, this.stopwhenM, new Object[]{w});
    }
    /** Wrapper for the LastScene Handler */
    private Scene doLastScene(Object w) {
        if(this.lastscene == null)return doOnDraw(w);
        return (Scene)Util.applyFunc(this.lastscene, this.lastsceneM, new Object[]{w});
    }
    
    /** Construct and run the animation/interaction system.  For the
     *    Swing version the method returns the final value of the
     *    World after the animation has completed.  The Window is
     *    opened as a Modal dialog, so control does not return to the
     *    bigband caller until the window is closed. */
    public Object bigBang(){
        return this.bigBang("BigBang");
    }
    
    /** Open a window and run the animation with the given title */
    public Object bigBang(String title){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}

        if(this.ondraw == null)
            throw new RuntimeException("No World Draw Handler");
        JDialog f = new JDialog((JFrame)null, title, true);
        Scene scn = doOnDraw(this.initial);
        f.setSize((int)(SPACE*2+Math.max(20, 14+scn.width())),
                (int)(Math.max(20, SPACE*2+31+scn.height())));
        
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        final Handler handler = new Handler(this,this.initial, scn,
                new BufferedImage((int)(scn.width()+2*SPACE), (int)(scn.height()+2*SPACE), BufferedImage.TYPE_INT_RGB), f);
        f.getContentPane().add(handler);
        f.setVisible(true);
        handler.run.cancel();
        return handler.w;        
    }
    /** Handles the nitty-gritty of world updates and interfacing with Swing */
    static class Handler extends javax.swing.JComponent
                         implements MouseListener,KeyListener,MouseMotionListener{
        private static final long serialVersionUID = 1L;
        BigBang world;
        Object w;
        Scene scnBuffer;
        BufferedImage buffer;
        Graphics2D graph;
        Timer run;
        TimerTask ticker;
        boolean isRunning = false;
        boolean isDone = false;
        
        /** Create a new Handler for all the World's events */
        Handler(BigBang world, Object ww, Scene scn, BufferedImage buff, JDialog dia){
            this.world = world;
            this.w = ww;

            this.scnBuffer = null;
            this.buffer = buff;
            this.graph = buff.createGraphics();
            this.graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.graph.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            this.run = new Timer();
            addMouseListener(this);
            if(world.onmouse != null){
                addMouseMotionListener(this);
            }
            if(world.onkey != null)
                dia.addKeyListener(this);
            this.isRunning = true;
            if(world.ontick != null){
                this.run.scheduleAtFixedRate(this.ticker = new TimerTask(){
                    public void run(){ tickAction(); }
                }, 200, (int)(world.time*1000));
            }
        }
        /** Swing uses a <tt>paint(Graphics)</tt> method to draw the
         *    component (Handler) into the window. */
        public void paint(java.awt.Graphics g){
            Scene curr;
            if(!this.isDone)
                curr = this.world.doOnDraw(this.w);
            else
                curr = this.world.doLastScene(this.w);
            
            if(curr != this.scnBuffer){
                this.scnBuffer = curr;
                this.graph.setColor(Color.white);
                this.graph.fillRect(0,0, this.getWidth(), this.getHeight());
                this.graph.clipRect(SPACE, SPACE, this.buffer.getWidth()-SPACE*2, this.buffer.getHeight()-SPACE*2);
                this.scnBuffer.paint(this.graph,SPACE,SPACE);
            }
            g.drawImage(this.buffer, 0, 0, null);
        }
        /** Rather than Swing timers, we use to java.util.Timer to
         *    provide compatibility with Android (i.e., so the code
         *    for both versions looks the same). */
        public void tickAction(){
            if(!this.isRunning || this.isDone)return;
            replace(this.world.doOnTick(this.w));
        }
        /** Support saving screenshots... */
        JPopupMenu popup = new JPopupMenu("World Options");
        {
            addItem(this.popup, "Save Image...", new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    doSaveAs(Handler.this.scnBuffer);
                    synchronized(Handler.this.popup){ Handler.this.popup.notify(); }
                }
            });
            addItem(this.popup, "Continue",  new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    synchronized(Handler.this.popup){ Handler.this.popup.notify(); }
                }
            });
            this.popup.addPopupMenuListener(new PopupMenuListener(){
                public void popupMenuCanceled(PopupMenuEvent arg0){
                    synchronized(Handler.this.popup){ Handler.this.popup.notify(); }
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
        boolean doSaveAs(Scene scn){
            try{
                JFileChooser fc = new JFileChooser();
                int result = fc.showDialog(this, "SaveAs");
                // Check for existence... 
                if(result == JFileChooser.APPROVE_OPTION){
                    this.scnBuffer.toFile(fc.getSelectedFile().getAbsolutePath());
                    return true;
                }
            }catch(Exception e){
                System.err.println("Exception: "+e);
            }
            return false;
        }
        public void mousePressed(final MouseEvent e){
            if(e.isPopupTrigger()){
                // Pause the simulation...
                final Handler that = this;
                final boolean temp = this.isRunning;
                this.isRunning = false;

                // Show the context Menu
                new Thread(){
                    public void run(){
                        Handler.this.popup.show(that, e.getX(), e.getY());
                        try{
                            synchronized(Handler.this.popup){
                                Thread.yield();
                                Handler.this.popup.wait();
                            }
                        }catch(Exception ee){}
                        // Restart the simulation if running...
                        that.isRunning = temp;
                    }
                }.start();
            }else{
                if(this.isRunning && !this.isDone)
                    replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_DOWN));
            }
        }
        /** Mouse click/move/event Methods */
        public void mouseClicked(MouseEvent e){}
        public void mouseEntered(MouseEvent e){ if(this.isRunning && !this.isDone)replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_ENTER)); }
        public void mouseExited(MouseEvent e){ if(this.isRunning && !this.isDone)replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_LEAVE)); }
        public void mouseReleased(MouseEvent e){ if(this.isRunning && !this.isDone)replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_UP)); }
        public void mouseDragged(MouseEvent e){ if(this.isRunning && !this.isDone)replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_DRAG)); }
        public void mouseMoved(MouseEvent e){ if(this.isRunning && !this.isDone)replace(this.world.doOnMouseEvent(this.w, e.getX(), e.getY(), MOUSE_MOVE)); }
        /** Keys are converted to strings to simplify handling */
        public void keyPressed(KeyEvent e){
            if(this.isRunning && !this.isDone)
                replace(this.world.doOnKeyEvent(this.w, convert(e.getKeyCode(), ""+e.getKeyChar())));    
        }
        public void keyReleased(KeyEvent e){
            if(this.isRunning && !this.isDone)
                replace(this.world.doOnReleaseEvent(this.w, convert(e.getKeyCode(), ""+e.getKeyChar())));
        }
        public void keyTyped(KeyEvent e){
            //if(isRunning && !isDone)replace(world.doOnKeyEvent(w, ""+e.getKeyChar()));    
        }
        
        private void replace(Object w){
            // This isn't enough when mutation is involved...
            if(!this.isRunning || this.isDone)return;
            
            if(this.isRunning && this.world.doStopWhen(w)){
                this.isRunning = false;
                this.isDone = true;
                this.run.cancel();
            }
            
            boolean change = !this.w.equals(w);
            this.w = w;
            if(change)repaint();
        }
        private String convert(int code, String ch){
            switch(code){
            case KeyEvent.VK_UP: return KEY_ARROW_UP;
            case KeyEvent.VK_DOWN: return KEY_ARROW_DOWN;
            case KeyEvent.VK_LEFT: return KEY_ARROW_LEFT;
            case KeyEvent.VK_RIGHT: return KEY_ARROW_RIGHT;
            case KeyEvent.VK_ESCAPE: return KEY_ESCAPE;
            default: return ch;
            }
        }

    }
}

