/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/sound/SoundWorld.java                                 *
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

package world.sound;

import java.util.HashMap;

import world.sound.tunes.*;
import world.BigBang;
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
 *  A Class representing an imperative World with sound/music and the related methods for drawing the 
 *    world and handling various events.  In order to implement a functioning World with sound you
 *    must <i>extend</i> this class, and implement an {@link world.sound.SoundWorld#onDraw onDraw}
 *    method.  Other handler methods ({@link world.sound.SoundWorld#tickRate tickRate}, {@link world.sound.SoundWorld#onTick onTick}, 
 *    {@link world.sound.SoundWorld#onMouse onMouse}, {@link world.sound.SoundWorld#onKey onKey}, {@link world.sound.SoundWorld#onRelease onRelease}, 
 *    {@link world.sound.SoundWorld#stopWhen stopWhen}, and {@link world.sound.SoundWorld#lastScene lastScene}) are optional, and
 *    can be overridden to add new functionality.
 * <p>
 *  Each of the interaction methods can add {@link world.sound.tunes.Note Note}s (sounds) to be played
 *  (e.g., <code>onTick</code>) for a length of time after the event.  There are two <i>tune-collections</i>
 *  for adding sounds, the first {@link world.sound.SoundWorld#tickTunes tickTunes}, should be added
 *  to for notes/sounds that should be played for a specified length of time corresponding to a World
 *  event. The second, {@link world.sound.SoundWorld#keyTunes keyTunes}, should be added to for
 *  notes/sounds that should be played for as long as the key is pressed;  when the key is released
 *  the sound will be removed and playing of the note will stop.
 * </p>
 * <p>
 *  See the <tt>world.sound.tunes</tt> package for more details (<tt>Notes</tt>,
 *  <tt>Chords</tt>, etc.).
 * </p>
 * 
 * <p>
 * <h3>Extending VoidWorld</h3>
 * <blockquote>
 * Below is a simple example of a <code>VoidWorld</code> that adds a new point at each mouse
 * click.  The world contains a {@link image.Scene Scene} and a new {@link image.Circle Circle}
 * is placed for each <code class='str'>"button-down"</code> event received, and a
 * {@link world.sound.tunes.Note Note} is added to the {@link world.sound.SoundWorld#tickTunes tickTunes}
 * to be played at the current <code>pitch</code>, which is incremented.
 * 
 * <pre>   
 *        <span class="keyw">import</span> image.*;
 *        <span class="keyw">import</span> world.sound.SoundWorld;
 *        <span class="keyw">import</span> world.sound.tunes.Note;
 *        
 *        <span class="keyw">public</span> <span class="keyw">class</span> MousePointsSoundWorld <span class="keyw">extends</span> SoundWorld{
 *            <span class="com">// Simple Main Program</span>
 *            <span class="keyw">public</span> <span class="keyw">static</span> <span class="keyw">void</span> <span class="func">main</span>(<span class="prim">String</span>[] args)
 *            { <span class="keyw">new</span> <span class="func">MousePointsSoundWorld</span>().<span class="func">bigBang</span>(); }
 *            
 *            <span class="com">// The inner Scene</span>
 *            Scene scene = <span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">200</span>, <span class="num">200</span>);
 *            <span class="com">// The current pitch to be played</span>
 *            <span class="keyw">int</span> pitch = noteDownC;
 *        
 *            <span class="com">// Create a new World</span>
 *            <span class="func">MousePointsSoundWorld</span>(){}
 *            
 *            <span class="com">// Draw by returning the inner Scene</span>
 *            <span class="keyw">public</span> Scene <span class="func">onDraw</span>(){ <span class="keyw">return</span> <span class="keyw">this</span>.scene; }
 *        
 *            <span class="com">// On a mouse click add a circle to the inner Scene, increment the</span>
 *            <span class="com">//    current pitch and play a short note</span>
 *            <span class="keyw">public</span> <span class="keyw">void</span> <span class="func">onMouse</span>(<span class="keyw">int</span> x, <span class="keyw">int</span> y, <span class="prim">String</span> me){
 *                <span class="keyw">if</span>(me.<span class="func">equals</span>(<span class="str">"button-down"</span>)){
 *                    <span class="keyw">this</span>.pitch++;
 *                    <span class="keyw">this</span>.tickTunes.<span class="func">addNote</span>(WOOD_BLOCK, <span class="keyw">new</span> <span class="func">Note</span>(<span class="keyw">this</span>.pitch, <span class="num">1</span>));
 *                    <span class="keyw">this</span>.scene = <span class="keyw">this</span>.scene.<span class="func">placeImage</span>(
 *                                     <span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"solid"</span>, <span class="str">"red"</span>)
 *                                           .<span class="func">overlay</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">20</span>, <span class="str">"outline"</span>, <span class="str">"black"</span>)), x, y);
 *                }
 *            }
 *        }
 * </pre>
 * 
 * After a few mouse clicks, the window will look something like this, though every
 * mouse click will have a corresponding sound at an increasing pitch:<br/><br/>
 * 
 *   <img class="example" src="test/sound-mouse-points.png" />
 * </blockquote>
 * </p>    
 */
public abstract class SoundWorld implements SoundConstants{
    
    /** Default Tick rate for the world: ~33 frames per second */
    public static double DEFAULT_TICK_RATE = 0.03;

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
    
    /** A representation of the current state of the MIDI synthesizer. */
    public MusicBox musicBox = new MusicBox();    
    
    /** The collection of tunes to play on tick.  Any tunes added after an event will begin
     *  playing as soon as the event is completely processed and will finish playing when
     *  the sound/Note's duration has elapsed. */
    public TuneCollection tickTunes;
    /** The collection of tunes to start playing when a key is
     *    pressed, which will automatically removed when the same
     *    key is released. */
    public TuneCollection keyTunes;  
    
    /** The collection of tunes currently playing on tick */
    private TuneCollection currentTickTunes;
    /** The number of ticks per Tune Tick (so clients can adjust the game speed without
     *  changing the length of Notes/sounds. */
    private int ticksPerTuneTick;
    /** The number of ticks left until the next Tune Tick */
    private int tuneTickJiffies;
    
    /** the collection of tunes currently playing on key event */
    protected HashMap<String,TuneCollection> keyReleasedTunes;
    
    
    /** Default constructor.  Simply initializes the tune/music classes. */
    public SoundWorld(){ 
        this.initMusic();
        // Each tune-tick is about a quarter of a second
        this.ticksPerTuneTick = (int)Math.max(1.0, 0.125/this.tickRate());
    }

    /** Initialize the MIDI synthesizer and the TuneCollections */
    private void initMusic(){
        /** The MIDI synthesizer that plays the notes */
        musicBox = new MusicBox();

        if(musicBox.isReady()){
            this.tickTunes = new TuneCollection(this.musicBox);
            this.currentTickTunes = new TuneCollection(this.musicBox);
            this.keyTunes = new TuneCollection(this.musicBox);  
            this.keyReleasedTunes = new HashMap<String, TuneCollection>();
        }else{
            /** notify the user that music cannot play */
            System.out.println("MIDI synthesizer or the soundbank not available.");
            System.out.println("Tunes will not be played.");
        }
    }
    
    
    /** Return a visualization of this <tt>World</tt> as a {@link image.Scene Scene}.
     *    See {@link image.EmptyScene}, {@link image.Scene#placeImage(Image, int, int)}, and
     *    {@link image.Scene#addLine(int, int, int, int, String)} for documentation on
     *    constructing <tt>Scene</tt>s */
    public abstract Scene onDraw();
    
    /** Return the tick rate for this World in <i>seconds</i>.  For example,
     *  <span class='num'>0.5</span> means two <i>ticks</i> per second.
     *  The rate is only accessed when bigBang() is initially called and the
     *  window is created. */
    public double tickRate(){ return DEFAULT_TICK_RATE; }
    
    /** Change this World based on the Tick of the clock.  This
     *  method is called to get the update the World on each clock tick.
     *  Sounds ({@link world.sound.tunes.Note Note}s) to play starting on the current tick
     *  may be added to the {@link world.sound.SoundWorld#tickTunes tickTunes} tune-collection
     *  to be played for a specified length of time.  Notes will stop playing automatically
     *  when the amount of time corresponding to the note's duration has elapsed.
     */
    public void onTick(){ }
    
    /** Wrapper for sound processing on tick */
    private void processTick(){
        if(musicBox.isReady()){ 
            // advance the tick on current tunes
            // and stop playing those that are done
            this.tuneTickJiffies--;
            if(this.tuneTickJiffies <= 0){
                this.tuneTickJiffies = this.ticksPerTuneTick;
                this.currentTickTunes.nextBeat();
            }
        }

        // process the changes to the world on this tick
        this.onTick();

        if(musicBox.isReady()){
            // play the tunes collected in the tick TuneCollection
            this.tickTunes.playTunes();
            this.currentTickTunes.add(this.tickTunes);
            this.tickTunes.clear();
        }
    }  

    /** Change this World when a mouse event is triggered.
     * <tt>x</tt> and <tt>y</tt> are the location of the event in the window, and 
     * <tt>event</tt> is a <tt>String</tt> that describes what kind of event
     * occurred.
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
     * 
     *  Sounds ({@link world.sound.tunes.Note Note}s) to play starting when a certain mouse event
     *  occurs may be added to the {@link world.sound.SoundWorld#tickTunes tickTunes} tune-collection
     *  to be played for a specified length of time.  Notes will stop playing automatically
     *  when the amount of time corresponding to the note's duration has elapsed.
     */
    public void onMouse(int x, int y, String event){ }

    /** Wrapper for sound processing on mouse */
    private void processMouse(int x, int y, String event){
        this.onMouse(x, y, event);

        if(musicBox.isReady()){
            this.tickTunes.playTunes();
            this.currentTickTunes.add(this.tickTunes);
            this.tickTunes.clearTunes();
        }
    }
    
    /** Change this World when a key event is
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
     * 
     *  Sounds ({@link world.sound.tunes.Note Note}s) to play when the given key is pressed
     *  may be added to the {@link world.sound.SoundWorld#keyTunes keyTunes} tune-collection
     *  to be played until the same key is released.  Notes will not stop playing until the
     *  key is released.
     *
     * <p>
     *  Sounds to be played for a specific length of time after a certain key press (i.e., not
     *  until the key is released) may be added to the {@link world.sound.SoundWorld#tickTunes tickTunes}
     *  tune-collection (instead of {@link world.sound.SoundWorld#keyTunes keyTunes}) played until
     *  amount of time corresponding to the note's duration has elapsed.
     * </p>
     */
    public void onKey(String event){ }
    
    /** Wrapper for sound processing on key press */
    protected void processKey(String ke){
        // empty the key TuneCollection
        if(musicBox.isReady()){
            this.keyTunes.clearTunes();
        }
        
        // process the changes to the world on this key event
        this.onKey(ke);

        // play the tunes collected in the key TuneCollection
        // save what is currently playing so it plays until released
        if(musicBox.isReady()){
            if(!this.keyReleasedTunes.containsKey(ke)){
                this.keyReleasedTunes.put(ke, this.keyTunes.copy());
                this.keyTunes.playTunes();
            }
        }
    }

    /** Change this World when a key is released. The given <tt>event</tt>
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
     * 
     * Sounds ({@link world.sound.tunes.Note Note}s) that were added to the
     * {@link world.sound.SoundWorld#keyTunes keyTunes} tune-collection on a previous
     * key press will be stopped.
     */
    public void onRelease(String event){ }
    
    /** Wrapper for sound processing on key release */
    private void processRelease(String ke){
        if(musicBox.isReady()){
            if(this.keyReleasedTunes.containsKey(ke)){
                this.keyReleasedTunes.remove(ke).clear();
            }
        }
            
        // invoke user-defined onKeyReleased method
        this.onRelease(ke);
    }    
    /** Determine if the World/interaction/animation should be
     * stopped.  Returning a value of <tt class='keyw'>true</tt>
     * discontinues all events (mouse, key, ticks) and causes {@link
     * world.sound.SoundWorld#lastScene} to be used to draw the final
     * <tt>Scene</tt>.
     */
    public boolean stopWhen(){ return false; }
    
    /** Returns the <tt>Scene</tt> that should be displayed when the
     * interaction/animation completes ({@link world.sound.SoundWorld#stopWhen}
     * returns <tt class='keyw'>true</tt>). */
    public Scene lastScene(){ return this.onDraw(); }

    /** Wrapper for the call to LastScene. Allows Tunes to be added to the tick collection. */
    protected Scene processLastScene(){
        Scene ret = this.lastScene();

        if(musicBox.isReady()){
            this.tickTunes.playTunes();
            this.currentTickTunes.add(this.tickTunes);
        }
        return ret;
    }

    
    /** Kick off the interaction/animation.  This method returns the final
     *    state of the world after the user closes the World window. */
    public SoundWorld bigBang(){
        SoundWorld fin = (SoundWorld)new BigBang(this)
            .onDraw(new WorldDraw())
            .onTick(new WorldTick(), tickRate())
            .onMouse(new WorldMouse())
            .onKey(new WorldKey())
            .onRelease(new WorldRelease())
            .stopWhen(new WorldStop())
            .lastScene(new WorldLast())
            .bigBang("SoundWorld");
        
        // Let stuff finish a bit...
        try{ Thread.sleep(500); }catch(Exception e){}
        
        // Kill all the notes
        if(musicBox.isReady()){
            this.currentTickTunes.clear();
            this.currentTickTunes.clearTunes();
            this.tickTunes.clear();
            this.tickTunes.clearTunes();
            this.keyTunes.clear();
            this.keyTunes.clearTunes();
            for(TuneCollection t : this.keyReleasedTunes.values()){
                t.clear();
                t.clearTunes();   
            }
        }
        return fin;
    }
    
    /** Wrapper for OnDraw callback */
    private static class WorldDraw{
        @SuppressWarnings("unused")
        Scene apply(SoundWorld w)
        { return w.onDraw(); }
    }
    /** Wrapper for OnTick callback */
    private static class WorldTick{
        @SuppressWarnings("unused")
        SoundWorld apply(SoundWorld w)
        { w.processTick(); return w; }
    }
    /** Wrapper for OnMouse callback */
    private static class WorldMouse{
        @SuppressWarnings("unused")
        SoundWorld apply(SoundWorld w, int x, int y, String me)
        { w.processMouse(x, y, me); return w; }
    }
    /** Wrapper for OnKey callback */
    private static class WorldKey{
        @SuppressWarnings("unused")
        SoundWorld apply(SoundWorld w, String ke)
        { w.processKey(ke); return w; }
    }
    /** Wrapper for OnRelease callback */
    private static class WorldRelease{
        @SuppressWarnings("unused")
        SoundWorld apply(SoundWorld w, String ke)
        { w.processRelease(ke); return w; }
    }
    /** Wrapper for StopWhen callback */
    private static class WorldStop{
        @SuppressWarnings("unused")
        boolean apply(SoundWorld w)
        { return w.stopWhen(); }
    }
    /** Wrapper for LastScene callback */
    private static class WorldLast{
        @SuppressWarnings("unused")
        Scene apply(SoundWorld w)
        { return w.processLastScene(); }
    }
    /** Overridden equality to method.  Returns <tt>false</tt> to make sure that
     *    changes to the world are redrawn every time. */
    public boolean equals(Object o){ return false; }
}

