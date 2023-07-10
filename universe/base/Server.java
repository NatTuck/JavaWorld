/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/base/Server.java                                   *
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

package universe.base;

import universe.control.*;
import universe.List;
import universe.Mail;
import universe.IWorld;
import util.Util;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class Server<Msg extends Serializable> extends Thread{
    static void p(String s){ System.err.println(s); }
    
    private ServerSocket socket;
    private List<ServantThread> servants = List.create();
    private List<WithWorld> messageQ = List.create();
    private List<WorldImp> worlds = List.create();
    private boolean single;
    private boolean done = false;
    
    /** Create a Server... */
    Server(int port, boolean single, UniverseBase<Msg> u) throws IOException {
        this.socket = new ServerSocket(port);
        this.single = single;
        start();
    }

    /** Run!! */
    public void run(){
        while (!this.done) {
            try{
                Socket req = this.socket.accept();
                p("Connection from: " + req.getInetAddress() + ":" + req.getPort());
                
                ServantThread t = new ServantThread(req, this);
                
                addServant(t);
                t.start();
                try{
                    if(this.single)t.join();
                }catch(InterruptedException ie){ }
                Thread.yield();
            }catch (IOException e){
                if(!this.done){
                    p(" ServerThread Exception: " + e.getMessage());
                    this.done = true;
                }
            }
        }
    }
    /** Add a Servant Thread to the List */
    public synchronized void shutdown(){
        
    }

    /** Add a Servant Thread to the List */
    public synchronized void addServant(ServantThread t){
        this.servants = this.servants.push(t);    
    }
    /** Add a Message to the Queue */
    public synchronized void addMessage(WithWorld m){
        this.messageQ = this.messageQ.append(m);
        this.notifyAll();
    }
    /** Is there a message in the Queue */
    public synchronized boolean hasMessage(){
        return !this.messageQ.isEmpty();
    }
    /** Get the next Message */
    public synchronized WithWorld nextMessage(){
        WithWorld m = this.messageQ.top();
        this.messageQ = this.messageQ.pop();
        return m;
    }
    /** Add a Message to the Queue */
    public synchronized void addWorld(WorldImp w){
        this.worlds = this.worlds.append(w);    
    }
    
    /** Remove a Servant Thread from the List */
    public synchronized int numServants(){
        return this.servants.length();    
    }
    
    public synchronized void processMail(Mail<Msg> m, IWorld from){
        WorldPred pred = new WorldPred(m.to);
        if(this.worlds.contains(pred)){
            WorldImp w = this.worlds.find(new WorldPred(m.to));
            w.sendMessage(m.content, from.name());
        }else
            p("World for \""+m.to.name()+"\" Not Found!!");
    }
    
    public synchronized void removeWorld(final IWorld w){
        this.servants = this.servants.filter(new List.Pred<ServantThread>(){
            public boolean huh(ServantThread t){
                if(t.world != null && t.world.equals(w)){
                    try{
                        t.done = true;
                        t.world.close();
                        t.sock.close();
                    }catch(Exception e){ }
                    return false;
                }
                return true;
            }
        });
        this.worlds = this.worlds.filter(new List.Pred<WorldImp>(){
            public boolean huh(WorldImp wrld){
                return !wrld.equals(w);
            }
        });
    }
    
    /** Handles the dispatch of a Request to a Server Method */
    private class ServantThread extends Thread{
        Socket sock;
        Server<Msg> parent;
        WorldImp world;
        boolean done = false;
        
        ServantThread(Socket s, Server<Msg> p){
            this.sock = s; this.parent = p;
        }

        public void run(){
            Object msg = null;
            try{
                OutputStream outt = this.sock.getOutputStream();
                InputStream inn = this.sock.getInputStream();
                ObjectOutputStream outStr = new ObjectOutputStream(outt);
                ObjectInputStream inStr = new ObjectInputStream(inn);  
                msg = inStr.readObject();
                if(!(msg instanceof Connect)){
                    throw new RuntimeException("First message Not \"Arrive\"!!");
                }
                this.world = new WorldImpObj(((Connect)msg).name(), ((Connect)msg).from(),
                        inStr, outStr);
                this.parent.addWorld(this.world);
                this.parent.addMessage(new WithWorld((Message)msg, this.world));
                while(!this.done){
                    //p("Waiting for Message");
                    msg = null;
                    msg = this.world.receiveMessage();
                    if(!(msg instanceof Connect ||
                         msg instanceof Disconnect ||
                         msg instanceof Transfer ||
                         msg instanceof WithWorld)){
                        p("Unknown Message Type: "+msg.getClass().getCanonicalName());
                    }
                    //p("Message : "+msg.getClass().getCanonicalName());
                    this.parent.addMessage(new WithWorld((Message)msg, this.world));
                }
            }catch(Exception e){
                this.parent.removeWorld(this.world);
                try{ this.sock.close(); }catch(Exception ee){}
                return;
            }
        }
    }
    /** Transfer using Object Streams... requires serializable */
    public static class WorldImpObj extends WorldImp{
        private static final long serialVersionUID = 1;
        ObjectInputStream in;
        ObjectOutputStream out;
        public WorldImpObj(String n, long id, ObjectInputStream in, ObjectOutputStream out){
            super(n,id);
            this.in = in;
            this.out = out;
        }
        public Object receiveMessage() throws Exception{ return this.in.readObject(); }
        public void sendMessage(Object o, String from){
            try{
                WithWorld msg = new WithWorld(new Transfer(this.id, o), new WorldShell(from,this.id));
                this.out.writeObject(msg);
            }catch(IOException e){
                p("Unable To Send Message ["+o.getClass().getName()+"] to \""+this.name+"\"");
            }   
        }
        public void close() throws Exception{
            this.in.close();
            this.out.close();
        }
    }
    /** Transfer with SExpressions... */
    public static class WorldImpSExp extends WorldImp{
        private static final long serialVersionUID = 1;
        InputStream in;
        OutputStream out;
        public WorldImpSExp(String n, long id, InputStream in, OutputStream out){
            super(n,id);
            this.in = in;
            this.out = out;
        }
        public Object receiveMessage() throws Exception{
            return null;    
        }
        public void sendMessage(Object o, String from){
            try{
                WithWorld msg = new WithWorld(new Transfer(this.id, o), new WorldShell(from,this.id));
                this.out.write((Util.toSExp(msg)+"\n").getBytes());
            }catch(IOException e){
                p("Unable To Send Message ["+o.getClass().getName()+"] to \""+this.name+"\"");
            }
        }
        public void close() throws Exception{
            this.in.close();
            this.out.close();
        }
    }
    
    public static abstract class WorldImp extends WorldShell{
        private static final long serialVersionUID = 1;
        WorldImp(String n, long id){ super(n, id); }
        public abstract void sendMessage(Object o, String from);
        public abstract Object receiveMessage() throws Exception;
        public abstract void close() throws Exception;
    }
    
    public static class WorldShell implements IWorld{
        private static final long serialVersionUID = 1;
        String name;
        long id;
        public WorldShell(String n, long id){ this.name = n; this.id = id; }
        public String name(){ return this.name; }
        public long id(){ return this.id; }
        public boolean equals(IWorld w){ return this.equals((Object)w); }
        public boolean equals(Object o){
            return ((o instanceof WorldShell) &&
                    ((WorldShell)o).id() == this.id());
        }
    }
    public static class WorldPred extends List.Pred<WorldImp>{
        long id;
        WorldPred(IWorld w){ this.id = w.id(); }
        public boolean huh(WorldImp w){
            return this.id == w.id();
        }
    }
}

