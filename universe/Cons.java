/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/Cons.java                                          *
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

package universe;

/** Represents a Lisp style cons list.  You should not use the constructor, use List.push(...)
 *    to add an element to the front of the list.  It is left public so that the parser
 *    generator can parse different lists if needed. */
public class Cons<X> extends List<X>{
    private static final long serialVersionUID = 1;
    protected final X first;
    protected final List<X> rest;
    //private final int hash;
    public Cons(X f, List<X> r){
        super(r.length()+1);
        this.first = f;
        this.rest = r;
        //hash = first.hashCode()+3*rest.hashCode();
    }
    
    public X top(){ return this.first; }
    public List<X> pop(){ return this.rest; }
    public boolean isEmpty(){ return false; }
    
    public boolean equals(Object o){
        if(! (o instanceof Cons<?>))return false;
        List<?> c = (List<?>)o;
        if(c.length() != length())return false;
        for(X x:this){
            if(!x.equals(c.top()))return false;
            c = c.pop();
        }
        return true;
    }
    
    public int hashCode(){
        //return hash;
        return this.first.hashCode()+3*this.rest.hashCode();  
    }
    
    /** Getter for Entry.key */
    public X getFirst(){ return this.first; }
    /** Getter for Entry.val */
    public List<X> getRest(){ return this.rest; }
    
}

