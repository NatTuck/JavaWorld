/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/Empty.java                                         *
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

/** Represents the Empty List. You should not use the constructor. To create an
 *    empty list please use List.create<T>().  You Can usually get away without
 *    the type parameter depending on the context. */
public class Empty<X> extends List<X> {
    private static final long serialVersionUID = 1;
    public Empty(){ super(0); }
  
    public X top(){ throw new RuntimeException("Bad Top"); }
    public List<X> pop(){ throw new RuntimeException("Bad Pop"); }
    public boolean isEmpty(){ return true; }
    public boolean equals(Object o){ return (o instanceof Empty<?>); }
    public int hashCode(){ return 11314233; }
    public List<X> append(List<X> l){ return l; }
    public List<X> append(X t){ return this.push(t); }
    public boolean contains(X t){ return false; }
    public boolean contains(Pred<X> p){ return false; }
    public boolean containsAny(List<X> l){ return false; }
    public boolean containsAll(List<X> l){ return l.isEmpty(); }
    public boolean containsAll(List<X> l, Comp<X> c){ return l.isEmpty(); }
    public X find(X t){ throw new RuntimeException("Not Found: "+t); }
    public X find(Pred<X> p){ throw new RuntimeException("No Match Found"); }
    public List<X> remove(X t){ return this; }
    public List<X> remove(Pred<X> p){ return this; }
    public int length(){ return 0; }
    public X lookup(int i){ throw new RuntimeException("Bad Lookup"); }
    public String toString(String sep, String pre){ return ""; }
    public String toString(Stringer<X> s){ return ""; }
    public List<X> filter(Pred<X> p){ return this; }
    public <Y> Y foldl(Fold<X,Y> f, Y b){ return b; }
    public <Y> Y foldr(Fold<X,Y> f, Y b){ return b; }
    public <Y> List<Y> map(Map<X,Y> m){ return new Empty<Y>(); }
    public List<X> add(X t, int i){
        if(i == 0)return push(t); throw new RuntimeException("Bad Add");
    }
    public List<X> remove(int i){ throw new RuntimeException("Bad Remove"); }
    public List<X> insert(X a, Comp<X> c){ return push(a); }
    public List<X> sort(Comp<X> c){ return this; }
    public <Y,Z> List<Z> zip(Zip<X,Y,Z> z, List<Y> l){ return List.<Z>create(); }
    
    public List<X> replace(X t, X s){ throw new RuntimeException("Bad Replace"); }
    public List<X> replace(Pred<X> p, X t){ throw new RuntimeException("Bad Replace"); }
    public List<X> replace(int t, X s){ throw new RuntimeException("Bad Replace"); }
    public List<X> replaceAll(X t, X s){ return this; }
    public List<X> replaceAll(Pred<X> p, X t){ return this; }
}

