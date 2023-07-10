/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/world/BigBang.java                                 *
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

import javax.swing.*;

import universe.base.UniverseBase;
import universe.base.Server.WorldShell;
import universe.world.base.*;
import universe.control.*;
import universe.Package;
import java.net.*;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.event.*;
import image.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/** A Class representing the creation of a World/System that communicates
 *    by passing messages of some type (<tt>Msg</tt>), and the related methods 
 *    and Function-Objects (call-backs) for drawing the world and handling various
 *    events.  Handlers are parameterized so they are statically checked. 
 *    
 *  <p>The name and types of handlers are given in the table below:<br/><br/>
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
 *      <tr><td class="event">OnDraw</td><td><tt><span class='fun'>onDraw</span>(OnDraw)</tt></td><td><tt>Scene <span class='fun'>apply</span>(World&lt;Msg&gt; w)</tt></td><td><b><i>yes</i></b></td><tr/>
 *      <tr><td class="event">OnTick</td><td><tt><span class='fun'>onTick</span>(OnTick&lt;Msg&gt;)</tt> or<br/> <tt><span class='fun'>onTick</span>(OnTick&lt;Msg&gt;, <span class="keyw">double</span>)</tt></td><td><tt>World <span class='fun'>apply</span>(World&lt;Msg&gt; w)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnMouse</td><td><tt><span class='fun'>onMouse</span>(OnMouse&lt;Msg&gt;)</tt></td><td><tt>World&lt;Msg&gt; <span class='fun'>apply</span>(World&lt;Msg&gt; w, <span class="keyw">int</span> x, <span class="keyw">int</span> y, String what)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnKey</td><td><tt><span class='fun'>onKey</span>(OnKey&lt;Msg&gt;)</tt></td><td><tt>World&lt;Msg&gt; <span class='fun'>apply</span>(World&lt;Msg&gt; w, String key)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">OnRelease</td><td><tt><span class='fun'>onRelease</span>(OnRelease&lt;Msg&gt;)</tt></td><td><tt>World&lt;Msg&gt; <span class='fun'>apply</span>(World&lt;Msg&gt; w, String key)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">StopWhen</td><td><tt><span class='fun'>stopWhen</span>(StopWhen)</tt></td><td><tt><span class="keyw">boolean</span> <span class='fun'>apply</span>(World&lt;Msg&gt; w)</tt></td><td>no</td><tr/>
 *      <tr><td class="event">LastScene</td><td><tt><span class='fun'>lastScene</span>(LastScene)</tt></td><td><tt>Scene <span class='fun'>apply</span>(World&lt;Msg&gt; w)</tt></td><td>no</td><tr/>
 *    </table><br/>
 *    </p>
 */
public class BigBang<Msg extends Serializable>{
    static void p(String s){ System.err.println(s); }
    
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

    
    private World<Msg> initial;
    double time;
    
    // Handlers
    private OnDraw ondraw;
    private OnTick<Msg> ontick;
    private OnMouse<Msg> onmouse;
    private OnKey<Msg> onkey;
    private OnRelease<Msg> onrelease;
    private OnReceive<Msg> onreceive;
    private StopWhen stopwhen;
    private LastScene lastscene;
    private String server = "";
    private String name = "";
    
    
    public BigBang(World<Msg> initial){
        this(initial, 0.05, null, null, null, null,
                null, null, null, null, null, null);
    }
    /** Install a Draw Handler into this BigBang.  The Draw handler
     *    requires an apply method [World&lt;Msg&gt; -&gt; Scene],
     *    though the requirement is checked dynamically when this
     *    method is called. */
    public BigBang<Msg> onDraw(OnDraw ondraw){
        return new BigBang<Msg>(this.initial, this.time,
                ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a Tick Handler at a tick rate of 1/20th of a second. */
    public BigBang<Msg> onTick(OnTick<Msg> ontick){
        return onTick(ontick, 0.05);
    }
    /** Install a Tick Handler into this BigBang at the given tick
     *    rate (per-seconds).  The Tick handler requires an apply
     *    method [World&lt;Msg&gt; -&gt; World&lt;Msg&gt;], though the requirement is
     *    checked dynamically when this method is called. */
    public BigBang<Msg> onTick(OnTick<Msg> ontick, double time){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a Mouse Handler into this BigBang.  The Mouse handler
     *    requires an apply method [World&lt;Msg&gt; -&gt; World&lt;Msg&gt;], though the
     *    requirement is checked dynamically when this method is
     *    called. */
    public BigBang<Msg> onMouse(OnMouse<Msg> onmouse){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a Key Handler into this BigBang.  The Key handler
     *    requires an apply method [World&lt;Msg&gt; String -&gt; World&lt;Msg&gt;], though
     *    the requirement is checked dynamically when this method is
     *    called. */
    public BigBang<Msg> onKey(OnKey<Msg> onkey){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a Key Release Handler into this BigBang.  The Key
     *    Release handler requires an apply method [World&lt;Msg&gt; String -&gt;
     *    World&lt;Msg&gt;], though the requirement is checked dynamically when
     *    this method is called. */
    public BigBang<Msg> onRelease(OnRelease<Msg> onrelease){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    public BigBang<Msg> onReceive(OnReceive<Msg> onreceive){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                onreceive, this.stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a StopWhen Handler into this BigBang.  The StopWhen
     *    handler requires an apply method [World&lt;?&gt; -&gt; Boolean],
     *    though the requirement is checked dynamically when this
     *    method is called.  The StopWhen handler, if installed is
     *    call to determine whether or not the World/animation/events
     *    should be stopped.  When/if the handler returns true then
     *    all events stop being received and the LastScene handler is
     *    given a chance to draw the final World. */
    public BigBang<Msg> stopWhen(StopWhen stopwhen){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, stopwhen, this.lastscene, this.server, this.name);
    }
    /** Install a LastScene Handler into this BigBang.  The LastScene
     *    handler requires an apply method [World&lt;?&gt; -&gt; Scene], though
     *    the requirement is checked dynamically when this method is
     *    called.  After the animation is stopped (StopWhen) the final
     *    World is drawn using the LstScene Handler. */
    public BigBang<Msg> lastScene(LastScene lastscene){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, lastscene, this.server, this.name);
    }
    /** Install the name of the Universe server to connect to once
     *    {@link BigBang#bigBang bigBang} is called */
    public BigBang<Msg> register(String server){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, server, this.name);
    }
    /** Install the name of this client, to be used with the Universe server */
    public BigBang<Msg> name(String name){
        return new BigBang<Msg>(this.initial, this.time,
                this.ondraw, this.ontick, this.onmouse, this.onkey, this.onrelease,
                this.onreceive, this.stopwhen, this.lastscene, this.server, name);
    }
    
    // Private constructors...
    private BigBang(World<Msg> init, double time, 
            OnDraw ondraw, OnTick<Msg> ontick, OnMouse<Msg> onmouse,
            OnKey<Msg> onkey, OnRelease<Msg> onrelease, OnReceive<Msg> onreceive, StopWhen stopwhen,
            LastScene lastscene, String server, String name){
        this.initial = init;
        this.time = time;
        this.ondraw = ondraw;
        this.ontick = ontick;
        this.onmouse = onmouse;
        this.onkey = onkey;
        this.onrelease = onrelease;
        this.onreceive = onreceive;
        this.stopwhen = stopwhen;
        this.lastscene = lastscene;
        this.server = server;
        this.name = name;
    }    
    /** Wrapper for the Draw Handler */
    private Scene doOnDraw(World<Msg> w){
        return this.ondraw.apply(w);
    }
    /** Wrapper for the LastScene Handler */
    private Scene doLastScene(World<Msg> w){
        if(this.lastscene == null)return this.ondraw.apply(w);
        return this.lastscene.apply(w);
    }
    /** Wrapper for the Tick Handler */
    private Package<Msg> doOnTick(World<Msg> w){
        if(this.ontick == null)return null;
        return this.ontick.apply(w);
    }
    /** Wrapper for the Mouse Handler */
    private Package<Msg> doOnMouseEvent(World<Msg> w, int x, int y, String me){
        if(this.onmouse == null)return null;
        return this.onmouse.apply(w, x-BigBang.SPACE, y-BigBang.SPACE, me);
    }
    /** Wrapper for the KeyDown Handler */
    private Package<Msg> doOnKeyEvent(World<Msg> w, String ke){
        if(this.onkey == null || ke.length() == 0)return null;
        return this.onkey.apply(w, ke);
    }
    /** Wrapper for the KeyRelease Handler */
    private Package<Msg> doOnKeyRelease(World<Msg> w, String ke){
        if(this.onkey == null || ke.length() == 0)return null;
        return this.onrelease.apply(w, ke);
    }
    /** Wrapper for the StopWhen Handler */
    private boolean doStopWhen(World<Msg> w){
        if(this.stopwhen == null)return false;
        return this.stopwhen.apply(w);
    }    
    /** Wrapper for the Receive Handler */
    @SuppressWarnings("unchecked")
    private Package<Msg> doOnReceive(World<Msg> w, Object m){
        if(this.onreceive == null)return null;
        return this.onreceive.apply(w, (Msg)m);
    }
    /** Construct and run the animation/interaction system.  For the
     *    Swing version the method returns the final value of the
     *    World after the animation has completed.  The Windqow is
     *    opened as a Modal dialog, so control does not return to the
     *    bigband caller until the window is closed. */
    public World<Msg> bigBang(){
        return this.bigBang("Universe-World");
    }
    
    /** Open a window and run the animation with the given title */
    public World<Msg> bigBang(String title){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){}

        if(this.ondraw == null)
            throw new RuntimeException("No World Draw Handler");
        JDialog f = new JDialog((JFrame)null, title, true);
        Scene scn = doOnDraw(this.initial);
        Handler handler = new Handler(this,this.initial, scn,
                new BufferedImage((int)(scn.width()+2*SPACE), (int)(scn.height()+2*SPACE), BufferedImage.TYPE_INT_RGB),
                f, (int)(Math.random()*1000000));
        f.setSize((int)(SPACE*2+Math.max(20, 14+scn.width())),
                (int)(Math.max(20, SPACE*2+31+scn.height())));
        
        f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.getContentPane().add(handler);
        f.setVisible(true);
        handler.finish();
        return handler.w;
    }
    
    /** Gap left around the border of the Window */
    private static int SPACE = 5;
        
    /** Handles the nitty-gritty of world updates and interfacing with Swing */
    class Handler extends javax.swing.JComponent
                         implements MouseListener,KeyListener,MouseMotionListener{
        private static final long serialVersionUID = 1L;
        BigBang<Msg> world;
        World<Msg> w;
        Scene scnBuffer;
        BufferedImage buffer;
        Graphics2D graph;
        Timer run;
        TimerTask ticker;
        boolean isRunning = false;
        boolean isDone = false;

        // Specific to the Universe
        long id = 0; 
        Socket sock = null;
        ObjectInputStream inn = null;
        ObjectOutputStream outt = null;
        Thread receiver;

        /** Create a new Handler for all the World's events */        
        Handler(BigBang<Msg> world, World<Msg> ww, Scene scn, BufferedImage buff, JDialog dia, long id){
            this.world = world;
            this.w = ww;
            this.id = id;
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
            if(BigBang.this.server != null){
                for(int i = 0; this.sock == null && i < 5; i++){
                    try{
                        p("["+i+"] Trying to connect to the universe...");
                        this.sock = new Socket(world.server, UniverseBase.PORT);
                        this.outt = new ObjectOutputStream(this.sock.getOutputStream());
                        this.outt.writeObject(new Connect(world.name, id));
                        this.inn = new ObjectInputStream(this.sock.getInputStream());
                    }catch(IOException e){
                        this.sock = null;
                        this.outt = null;
                        this.inn = null;
                        //p("Exception ["+e+"]");
                        try{ Thread.sleep(200); }catch(Exception ee){}
                    }
                }
                if(this.sock == null){
                    p("** Unable to connect to the universe... running locally");
                }else{
                    p("** Success");
                    this.receiver = new Thread(){
                        public void run(){
                            while(!Handler.this.isDone){
                                try{
                                    Object m = Handler.this.inn.readObject();
                                    if(!(m instanceof Message)){
                                        throw new RuntimeException("Bad Message");
                                    }
                                    Message msg = (Message)m;
                                    if(msg.isTransfer()){
                                        replace(doOnReceive(Handler.this.w, msg.payload()));
                                    }else p("Unknown Message Type");

                                }catch(EOFException e){
                                    return;
                                }catch(Exception e){
                                    if(!Handler.this.isDone)
                                        p("Error Reading Message: "+e);
                                    return;
                                }
                            }
                        }
                    };
                    this.receiver.start();
                }
            }else
                p("** No registration given... running locally");
        }
        /** What to do when we're all done */
        public void finish(){
            this.run.cancel();
            this.isDone = true;
            try{
                this.outt.writeObject(new Disconnect(this.id));
                this.outt.close();
                this.inn.close();
            }catch(Exception e){}
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
        private void addItem(JPopupMenu m, String s, ActionListener l){
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
                replace(this.world.doOnKeyRelease(this.w, convert(e.getKeyCode(), ""+e.getKeyChar())));
        }
        public void keyTyped(KeyEvent e){
            //if(this.isRunning && !this.done)replace(this.world.doOnKeyEvent(this.w, ""+e.getKeyChar()));
        }
        
        private synchronized void replace(Package<Msg> p){
            if(p == null)return;
            // This isn't enough when mutation is involved...
            if(!this.isRunning || this.isDone)return;
            
            if(this.isRunning && this.world.doStopWhen(w)){
                this.isRunning = false;
                this.isDone = true;
                this.run.cancel();
            }
            
            boolean change = !this.w.equals(p.getWorld());
            this.w = p.getWorld();
            if(change)repaint();
            if(p.hasMsg() && this.sock != null){
                // Deliver Message to the universe
                try{
                    this.outt.writeObject(new WithWorld(new Transfer(this.id, p.getMsg()),
                            new WorldShell(this.world.name,this.id)));
                }catch(IOException e){
                    p("Cannot Send Message ["+p.getMsg().getClass().getName()+"]");
                    p("  ** Exception: "+e);
                }
            }
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

