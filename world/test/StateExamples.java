/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/StateExamples.java                               *
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

package world.test;

import java.util.*;
import world.*;
import image.*;

/** World example with more complex States (Menus, Options, Etc.) */
public class StateExamples extends VoidWorld{
    State state;
    
    StateExamples(){
        this.state = new Loading();
    }
    
    /** Draw the current State of the world */
    public Scene onDraw(){
        return this.state.onDraw();
    }
    /** Tick the current State of the world */
    public void onTick(){
        this.state = this.state.onTick();
    }
    /** React to a Key in the current State of the world */
    public void onKey(String key){
        this.state = this.state.onKey(key);
    }
    /** React to a KeyRelease in the current State of the world */
    public void onRelease(String key){
        this.state = this.state.onRelease(key);
    }
    /** React to a Mouse Event in the current State of the world */
    public void onMouse(int x, int y, String me){
        this.state = this.state.onMouse(x, y, me);
    }
    
    /** Run the application as an Example */
    public static void main(String[] args){
        new StateExamples().bigBang();
    }
}

/** Abstract State: represents a state in the application sequence */
abstract class State{
    int WIDTH = 500, HEIGHT = 400;
    
    /** Do nothing */
    State onTick(){ return this; }
    /** Do nothing */
    State onKey(String key){ return this; }
    /** Do nothing */
    State onRelease(String key){ return this; }
    /** Do nothing */
    State onMouse(int x, int y, String me){ return this; }
    
    /** Return a blank background */
    Scene backGround(){
        return new EmptyScene(this.WIDTH, this.HEIGHT, "black");
    }
    
    /** Draw just the background (we can change the "background" later)*/
    Scene onDraw(){
        return this.backGround();
    }
    
    /** Create an ArrayList of arbitrary things */
    static <X> ArrayList<X> list(X ... xs){
        return new ArrayList<X>(Arrays.asList(xs));
    }
    
    /** Place the given image left-aligned to the given X */
    Scene placeLeft(Image img, int x, int y, Scene scn){
        return scn.placeImage(img, x+img.width()/2.0, y);
    }
}

/** Fake Loading state... */
class Loading extends State{
    String START = "LOADING";
    String msg = START;
    int countdown = 80;
    
    /** Count down, the switch to the Menu State */
    State onTick(){
        this.countdown--;
        if(this.countdown <= 0)
            return new Menu();
        
        if(this.countdown % 10 == 0){
            if(this.msg.length() < this.START.length()+4)
                this.msg = this.msg + ".";
            else
                this.msg = this.START;
        }
        return this;
    }
    
    /** Draw the "Loading" Message */
    Scene onDraw(){
        return placeLeft(new Text(this.msg, 20, "red"),
                this.WIDTH/2-50, this.HEIGHT/2, this.backGround());
    }
}

/** Menu Screen: Allow the user to select a "next" state */
class Menu extends State{
    int selected = 0;
    ArrayList<String> options = list("High Scores","Game Options","Play It!");
    
    /** Draw the menu, with the selected item highlighted */
    Scene onDraw(){
        Scene scn = this.backGround().placeImage(new Text("Menu Screen", 30, "red"), this.WIDTH/2, 60);
        for(int i = 0; i < options.size(); i++){
            scn = placeLeft(new Text(options.get(i), 20, color(i)), 120, 120+i*40, scn);
        }
        return scn;
    }
    
    /** React by selecting a new Option, or moving to the selected State */
    State onKey(String key){
        if(key.equals("up")){
            selected = (selected + options.size()-1) % options.size();
        }else if(key.equals("down")){
            selected = (selected + 1) % options.size(); 
        }else if(key.equals("\n")){
            return getOption();
        }
        return this;
    }
    
    /** Which Option (State) was selected? */
    State getOption(){
        if(selected == 0){
            return new HighScores();
        }else if(selected == 1){
            return new Options();
        }else if(selected == 2){
            return new PlayGame();
        }
        // Should never happen!!
        return this;
    }
    /** Selected Color */
    String color(int i){
        if(i == selected)return "blue";
        return "white";
    }
}

/** Fake Options Screen... Escape to go back */
class Options extends State{
    /** Override this for a different Message */
    String what(){ return "Sorry no Options"; }
    
    /** Draw the Message on the Background */
    Scene onDraw(){
        return this.backGround()
            .placeImage(new Text(what(), 30, "red"), this.WIDTH/2, 60)
            .placeImage(new Text("Hit ESC to go the Menu", 20, "red"), this.WIDTH/2, 100);
    }
    
    /** Only Escape Matters */
    State onKey(String key){
        if(key.equals("escape"))
            return new Menu();
        return this;
    }
}

/** Fake HighScore Screen */
class HighScores extends Options{
    /** */
    String what(){ return "Sorry no HighScores"; }
}

/** Chip Died :( Show where he was... and Message over the top */
class Dead extends Options{
    Scene last;
    
    Dead(Scene last){ this.last = last; }
    
    /** Yup... tis-true */
    String what(){ return "Sorry You Died!!"; }
    
    /** Swap in the last background Scene */
    Scene backGround(){ return this.last; }
}

/** Represents Chip the Cheap Sheep */
class Chip{
    // Number of Images
    static int NUM_TICKS = 4;
    // Constants
    static double SPEED = 6, GRAVITY = 1, JUMP = 12;
    
    // Images... to the left and the right
    static ArrayList<Image> chipLeft = State.<Image>list(
            new FromFile("ram-1.png"), new FromFile("ram-2.png"),
            new FromFile("ram-3.png"), new FromFile("ram-4.png")); 
    static ArrayList<Image> chipRight = State.<Image>list(
            new FromFile("ram-1.png").flipHorizontal(), new FromFile("ram-2.png").flipHorizontal(),
            new FromFile("ram-3.png").flipHorizontal(), new FromFile("ram-4.png").flipHorizontal());
    
    static int WIDTH_BY2 = chipRight.get(0).width()/2;
    static int HEIGHT_BY2 = chipRight.get(0).height()/2;
    
    // Is chip going to the right?
    boolean right = true;
    // Which Image are we on? How many Ticks?
    int ticks = 0, img = 0;
    
    // Where is Chip?
    double x, y;
    // Is he falling?
    double vy;
    // Is he Moving?
    boolean moving = false;
    
    Chip(double x, double y, double vy){
        this.x = x;
        this.y = y;
        this.vy = vy;
    }
    
    /** Tick chip... Move him, increase gravity, and change his image. */
    void tick(int width, int base){
        if(moving){
            ticks++;
            if(ticks > NUM_TICKS){
                ticks = 0;
                img = (img+1) % Chip.chipRight.size();
            }
            if(right && x <= width-WIDTH_BY2)
                x += SPEED;
            if(!right && x >= WIDTH_BY2)
                x -= SPEED;
        }else{
            img = 0;
        }
        // Make sure we don't fall off the screen
        if(y+vy >= base){
            y = base;
            vy = 0;
        }else{
            y += vy;
            vy += GRAVITY;
        }
    }
    /** Jump Chip, Jump!! */
    void jump(int base){
        if(y == base && vy == 0)
            vy -= JUMP;
    }
    /** Now he's moving... or is He? */
    void setMoving(boolean m){ this.moving = m; }
    /** Face left buddy */
    void pointLeft(){ this.right = false; }
    /** Face right buddy */
    void pointRight(){ this.right = true; }
    /** Draw the current Image of Chip in the right spot */
    Scene draw(Scene scn){
        Image which = Chip.chipLeft.get(this.img);
        if(this.right)
            which = Chip.chipRight.get(this.img);
        return scn.placeImage(which, this.x, this.y-HEIGHT_BY2);
    }
}

/** The Playing State (the real deal) */
class PlayGame extends State{
    // How many "arrow presses" have we had?
    int moving = 0;
    // Ata'boy Chip
    Chip chip = new Chip(this.WIDTH/2, this.HEIGHT/2, 0);
    
    /** Chip is the only thing we have to draw */
    Scene onDraw(){
        return this.chip.draw(this.backGround());
    }
    /** And he's the only thing we have to tick */
    State onTick(){
        chip.tick(this.WIDTH, this.HEIGHT);
        return this;
    }
    /** Escape kills him, left/right to move, space to jump */
    State onKey(String key){
        if(key.equals("escape"))
            return new Dead(this.onDraw());
        
        // Moving is ++
        if(key.equals("left")){
            chip.pointLeft();
            this.moving++;
            if(this.moving > 0)this.chip.setMoving(true);
        }else if(key.equals("right")){
            chip.pointRight();
            this.moving++;
            if(this.moving > 0)this.chip.setMoving(true);
        }else if(key.equals(" ")){
            chip.jump(this.HEIGHT);
        }
        return this;
    }
    /** */
    State onRelease(String key){
        if(key.equals("left") || key.equals("right")){
            // No longer moving... but only when moving == 0
            this.moving--;
            if(this.moving == 0)
                this.chip.setMoving(false);
        }
        return this;
    }
    /** Nothing to do... included for full generality */
    State onMouse(int x, int y, String me){
        return this;
    }
}    

