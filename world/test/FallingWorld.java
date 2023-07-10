/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./world/test/FallingWorld.java                                *
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
import image.*;
import world.World;

/** Falling Balls Demo in World style.  When the mouse is dragged, balls
 *     are created with a random upward velocity and then fall off the bottom of
 *     the screen */
public class FallingWorld extends World{
    public static void main(String[] s){
        new FallingWorld().bigBang();
    }
    
    Random rand = new Random();
    LoB balls;
    
    /** Construct an empty World */
    FallingWorld(){ this(new ELoB()); }
    /** Construct a World with the given list-of Balls */
    FallingWorld(LoB lob){
        this.balls = lob;
    }
    /** Set the tick rate to 25/second */
    public double tickRate(){ return 0.04; }
        
    /** In order to draw the World, draw the balls into an EmptyScene */
    public Scene onDraw(){
        return this.balls.draw(new EmptyScene(400,400));    
    }
    /** When the mouse is dragged, we add a new random Ball */
    public FallingWorld onMouse(int x, int y, String me){
        if(!me.equals("drag"))return this;
        return new FallingWorld(new CLoB(this.rand.randBall(x,y), this.balls));
    }
    /** On each tick, we apply gravity, move them, and filter out
     *    those off screen */
    public FallingWorld onTick(){
        return new FallingWorld(this.balls.gravity().move().filter());    
    }
}

/** Represents a Ball with a location and velocity */
class Ball{
    int x, y, vx, vy;
    String color;
    Ball(int x, int y, int vx, int vy, String color){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
    }
    /** Move this Ball in the direction it's going */
    Ball move(){ return new Ball(this.x+this.vx, this.y+this.vy, this.vx, this.vy, this.color); }
    /** Gravitize (increase Y velocity) of this Ball */
    Ball gravity(){ return new Ball(this.x,this. y, this.vx, this.vy+3, this.color); }
    /** Draw this Ball into the given Scene */
    Scene draw(Scene scn){
        return scn.placeImage(new Circle(10, "solid", this.color)
                        .overlay(new Circle(10, "outline", "black")), this.x, this.y);
    }
    /** Is this Ball off the screen? */
    boolean offscreen(){
        return (this.x > 400 || this.y > 400 || this.x < 0 || this.y < 0);    
    }
}

/** Represents a List of Balls */
abstract class LoB{
    /** Move all the balls in this LoB */
    abstract LoB move();
    /** Gravitize (increase Y velocity) all the balls in this LoB */
    abstract LoB gravity();
    /** Draw all the balls in this LoB into the given Scene */
    abstract Scene draw(Scene scn);
    /** Filter out the offscreen Balls */
    abstract LoB filter();
}
/** Represents an empty List of Balls */
class ELoB extends LoB{
    /** Construct a (the) Empty list of Balls*/
    ELoB(){}
    /** Move all the balls in this LoB (None) */
    LoB move(){ return this; }
    /** Gravitize (increase Y velocity) all the balls (None) */
    LoB gravity(){ return this; }
    /** Draw all the balls in this LoB (None) */
    Scene draw(Scene scn){ return scn; }
    /** Filter out the offscreen Balls (None) */
    LoB filter(){ return this; }
}
/** Represents a non-empty List of Balls */
class CLoB extends LoB{
    Ball first;
    LoB rest;
    /** Construct a Cons LoB (with a first and rest) */
    CLoB(Ball first, LoB rest){
        this.first = first;
        this.rest = rest;
    }
    /** Move all the balls in this LoB */
    LoB move(){ return new CLoB(this.first.move(), this.rest.move()); }
    /** Gravitize (increase Y velocity) all the balls in this LoB */
    LoB gravity(){ return new CLoB(this.first.gravity(), this.rest.gravity()); }
    /** Draw all the balls in this LoB into the given Scene */
    Scene draw(Scene scn){ return this.first.draw(this.rest.draw(scn)); }
    /** Filter out the offscreen Balls */
    LoB filter(){
        if(this.first.offscreen())return this.rest.filter();
        return new CLoB(this.first, this.rest.filter());
    }
}

/** Class for creating random objects (Colors and Balls) */
class Random{
    /** Random Integer */
    int rand(int i){ return (int)(Math.random()*i); }
    /** Color for a given number */
    String colorFor(int i){
        if(i == 0)return "red";
        else if(i == 1)return "blue";
        else if(i == 2)return "green";
        else if(i == 3)return "orange";
        else if(i == 4)return "yellow";
        else if(i == 5)return "purple";
        else return "black";
    }
    /** Random Ball at X/Y */
    Ball randBall(int x, int y){
        return new Ball(x,y,rand(11)-5, -10-rand(10), colorFor(rand(6)));    
    }
}
