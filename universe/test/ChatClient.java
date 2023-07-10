/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/test/ChatClient.java                               *
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

package universe.test;

import image.*;
import universe.world.*;
import universe.List;
import universe.Package;

/** Universe/Parameterized BigBang Chat Client test */
public class ChatClient{
    public static void main(String[] s){
        String nick = "chadwick";
        new Client(nick,List.<String>create(),"")
           .bigBang("127.0.0.1", nick);
    }
}

class Client extends World<String>{
    int WIDTH = 600;
    int HEIGHT = 500;
    int LINEH = 25;
    int FONT = 20;
    int SIDE = 20;
    Scene BASE = new EmptyScene(this.WIDTH,this.HEIGHT)
                        .placeImage(new Line(this.WIDTH,0,"blue"),
                                this.WIDTH/2+2, this.HEIGHT-this.LINEH);
    String nick;
    List<String> lines;
    String editor;
    
    Client(String nick, List<String> lines, String editor){
        this.nick = nick;
        this.lines = lines;
        this.editor = editor;
    }
    public Package<String> onKey(String ke){
        if(ke.equals("\b") && this.editor.length() > 0)
            return new Package<String>(new Client(this.nick,this.lines,
                    this.editor.substring(0,this.editor.length()-1)));
        else if(ke.equals("\n"))
            return new Package<String>(new Client(this.nick,this.lines,""),
                    this.nick+" : "+this.editor);
        else if(ke.length() == 1)
            return new Package<String>(new Client(this.nick,this.lines, this.editor+ke));
        else 
            return new Package<String>(this);
    }
    public Package<String> onReceive(String m){
        return new Package<String>(new Client(this.nick, this.lines.push(m), this.editor));    
    }
    public Scene onDraw(){
        Image txt = new Text(this.nick+" : "+this.editor, this.FONT, "black");
        return drawLines(this.HEIGHT-(3*this.LINEH/2), this.lines, this.BASE)
                 .placeImage(txt, this.SIDE+txt.width()/2, this.HEIGHT-this.LINEH/2+3)
                 .placeImage(new Rectangle(5,this.LINEH-2, "solid", "red"),
                         this.SIDE+txt.width()+2, this.HEIGHT-this.LINEH/2);
    }
    Scene drawLines(int y, List<String> lines, Scene scn){
        if(lines.isEmpty())
            return scn;
        else{
            Image txt = new Text(lines.top(), this.FONT, "black");
            return drawLines(y-this.LINEH, lines.pop(), scn)
                      .placeImage(txt, this.SIDE+txt.width()/2, y-this.LINEH/2);
        }
    }
}

