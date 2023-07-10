/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./universe/List.java                                          *
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Represents Lisp style Lists. List.create(...) should be used to create lists, and
 *    push/pop/top/etc... to pull them apart.  All methods are functional, meaning a
 *    given list is an immutable data type: methods return a new list, they do not
 *    change the old one.  The methods can be used to write beautiful recursive
 *    functions :)
 *    
 *    The inner classes (<tt>Build</tt>, <tt>Comp</tt>, <tt>GComp</tt>, <tt>Fold</tt>,
 *    <tt>Map</tt>, <tt>Pred</tt>, and <tt>Zip</tt>) are <i>Function Classes</i> that
 *    allow you to implement needed functionalities over basic traversals/iteration.
 *    
 *    This implementation has been updated to eliminate stack usage, which allows
 *    you (the programmer) to create HUGE lists without any problems ;)
 *    */
public abstract class List<X> implements java.lang.Iterable<X>, java.io.Serializable{
    private static final long serialVersionUID = 1;

    static class TestB extends List.Build<Integer>{
        public Integer build(int i){ return (int)(Math.random()*100); }
    }
    static class TestC extends List.Comp<Integer>{
        public boolean comp(Integer i, Integer j){ return i < j; }
    }
    public static void main(String[] args){
        List<Integer> lst = List.buildlist(new TestB(), 3000);
        Comp<Integer> c = new TestC();
        long st = System.currentTimeMillis();
        for(int i = 0; i < 20; i++)lst.mergeSort(c);
        System.out.println("  Merge: "+(System.currentTimeMillis()-st));
                
        st = System.currentTimeMillis();
        for(int i = 0; i < 20; i++)lst.sort(c);
        System.out.println(" Insert: "+(System.currentTimeMillis()-st));
    }
    
    /** Compute a String from a List (Visitor) */
    public static abstract class Stringer<X>{ public abstract String toString(X f, List<X> r); }
    /** Select Elements of a List */
    public static abstract class Pred<X>{
        /** Is this predicate true for the given X? */
        public abstract boolean huh(X x);
        /** Return a Predicate that is the negation of this one */
        public Pred<X> negate(){ return new Negate<X>(this); }
        private static class Negate<X> extends Pred<X>{
            Pred<X> p; Negate(Pred<X> pp){ this.p = pp; }
            public boolean huh(X x){ return !this.p.huh(x); }
        }
        
    }
    /** Compare two List Elements of the same type */
    public static abstract class Comp<X> extends GComp<X,X>{}
    /** (General) Compare two List Elements of possibly different types 
     *    (true is "LessThan" for <tt>sort()</tt>, but "Equal" for <tt>same(...)</tt>) */
    public static abstract class GComp<X,Y>{
        /** Compare these two Xs, returning true/false */
        public abstract boolean comp(X x, Y y);
        //private GComp<X,Y> that;
        
        /** Return a general comparator with flipped comparision (and types) */
        public GComp<Y,X> flip(){ return new Flip(this); }
        private class Flip extends GComp<Y,X>{
            Flip(GComp<X,Y> p){ this.par = p; }
            GComp<X,Y> par;
            public boolean comp(Y y, X x){ return this.par.comp(x, y); }
        }
        
        /** Return a Predicate that uses the comparator (later) using the given X as
         *    its first argument */
        public Pred<X> curry(Y y){ return new Curry<X,Y>(this,y); }
        /** Return a (reversed) Predicate that uses the comparator (later) using the given Y as
         *    its second argument */
        public Pred<Y> revCurry(X x){ return new RCurry<X,Y>(this,x); }
        private static class Curry<X,Y> extends Pred<X>{
            Y y; GComp<X,Y> c; Curry(GComp<X,Y> cc, Y yy){ this.c = cc; this.y = yy; }
            public boolean huh(X x){ return this.c.comp(x, this.y); }
        }
        private static class RCurry<X,Y> extends Pred<Y>{
            X x; GComp<X,Y> c; RCurry(GComp<X,Y> cc, X xx){ this.c = cc; this.x = xx; }
            public boolean huh(Y y){ return this.c.comp(this.x, y); }
        }
    }
    /** Apply a function to each element of the list */
    public static abstract class Map<X,Y>{ public abstract Y map(X x); }
    /** Fold the List into a single Value */
    public static abstract class Fold<X,Y>{ public abstract Y fold(X x, Y y); }
    /** Zip two Lists into a single Value */
    public static abstract class Zip<X,Y,Z>{ public abstract Z zip(X x, Y y); }
    /** Build a List from the sequence of integers [0..len-1] */
    public static abstract class Build<X>{ public abstract X build(int i); }

    /** Equals Comparator */
    private class EqualComp extends Comp<X>{
        public boolean comp(X x, X y){ return x.equals(y); }
    }
    
    /** Create an Empty List */
    public static <X> List<X> create(){ return new Empty<X>(); }
    /** Create a List from a single argument */
    public static <X> List<X> create(X x){ return List.<X>create().push(x); }
    /** Create a List from an array/variable arguments */
    public static <X> List<X> create(X ... xa){ return create(xa,0); }
    /** Create a List from a fixed array, starting at index 'i' */
    public static <X> List<X> create(X[] xa, int i){
        List<X> res = List.create();
        for(;i < xa.length;i++)res = res.push(xa[i]);
        return res.reverse();
    }
    /** Create a List from an Iterable... */
    public static <X> List<X> create(java.lang.Iterable<X> xs){
        List<X> res = List.create();
        for(X x:xs)res = res.push(x);
        return res.reverse();
    }

    private final int len;
    /** Default Constructor */
    public List(int l){ this.len = l; }
    
    /** Push a X on the front of this List */
    public List<X> push(X x){ return new Cons<X>(x,this); }
    /** Push all Elements of the given List on the front of this List */
    public List<X> push(List<X> xs){ return xs.append(this); }
    /** Reverse this List */
    public List<X> reverse(){ return reverse(length()); }
    /** Reverse the first i elements of this List */
    public List<X> reverse(int i){
        List<X> front = List.create(), back = this;
        while(!back.isEmpty() && i-- > 0){
            X x = back.top();
            back = back.pop();
            front = front.push(x);
        }
        return front;
    }
    /** Canonical ToString */
    public String toString(){ return toString(" ",""); }
    /** Return the Index of the given element */
    public int index(X x){ return index(new EqualComp().curry(x)); }
    /** Return the Index of the first match */
    public int index(List.Pred<X> p){
        int i = 0;
        for(X x:this){
            if(p.huh(x))return i;
            i++;
        }
        return -1;
    }
    /** Is the given list have the same elements as this list? */
    public boolean same(List<X> l){ return containsAll(l) && l.containsAll(this); }
    /** Is the given list have the same elements as this list using the given comparer? */
    public boolean same(List<X> l, Comp<X> c){ return sameG(l,c); }
    /** Is the given list have the same elements as this list using the given comparer? */
    public <Y> boolean sameG(List<Y> l, GComp<X,Y> c){ return containsAllG(l,c) && l.containsAllG(this,c.flip()); }
    /** Return this List without the first k Elements */
    public List<X> pop(int k){ return (k == 0)?this:pop().pop(k-1); }
    
    /** Append another List to the end of this List */
    public List<X> append(List<X> l){
        List<X> rev = reverse();
        return l.revpush(rev);
    }
    /** Append an element to the end of this List */
    public List<X> append(X x){ return append(List.<X>create(x)); }
    /** Return the first Element of this List */
    public abstract X top();
    /** Return this List without the first Element */
    public abstract List<X> pop();
    /** Is this List Empty? */
    public abstract boolean isEmpty();
    
    /** Does this Predicate match any element in this List? */
    public boolean ormap(final List.Pred<X> p){
        for(X x:this)
            if(p.huh(x))return true;
        return false;
    }
    /** Does this Predicate match all the elements in this List? */
    public boolean andmap(final List.Pred<X> p){ return !ormap(p.negate()); }
    
    /** Does the given X occur in this List? */
    public boolean contains(X x){ return contains(new EqualComp().curry(x)); }
    /** Does this Predicate match anything in this List? */
    public boolean contains(final List.Pred<X> p){ return ormap(p); }
    /** Does the given X occur in this List? */
    public <Y> boolean containsG(Y y, GComp<X,Y> c){ return contains(c.curry(y)); }
    
    private static class AnyP<X,Y> extends List.Pred<X>{
        public AnyP(List<Y> ll, GComp<X,Y> cc){ this.l = ll; this.c  = cc; }
        List<Y> l; GComp<X,Y> c;
        public boolean huh(X x){ return this.l.contains(this.c.revCurry(x)); }
    }
    /** Does this List contain any of the given List's Elements? */
    public boolean containsAny(List<X> l){ return containsAny(l, new EqualComp()); }
    /** Does this List contain any of the given List's Elements using the given comparer? */
    public boolean containsAny(final List<X> l, final Comp<X> c)
    {  return this.ormap(new AnyP<X,X>(l,c)); }
    /** Does this List contain any of the given List's Elements using the given comparer? */
    public <Y> boolean containsAnyG(final List<Y> l, final GComp<X,Y> c){
        return this.ormap(new AnyP<X,Y>(l,c));
    }
    
    /** Does this List contain all of the given List's Elements? */
    public boolean containsAll(List<X> l){ return containsAll(l, new EqualComp()); }
    /** Does this List contain all of the given List's Elements using the given comparer? */
    public boolean containsAll(final List<X> l, final Comp<X> c){
        return l.andmap(new AnyP<X,X>(this,c));
    }
    /** Does this List contain all of the given List's Elements using the given comparer? */
    public <Y> boolean containsAllG(final List<Y> l, final GComp<X,Y> c){
        return l.andmap(new AnyP<Y,X>(this,c.flip()));
    }
    /** Return the given X, throws a RuntimeException if not there */
    public X find(X x){ return find(new EqualComp().curry(x)); }
    /** Return the first matching X, throws a RuntimeException if not there */
    public X find(List.Pred<X> p){
        for(X x:this)
            if(p.huh(x))return x;
        throw new RuntimeException("No Match Found: "+p);
    }
    /** Return the given X, throws a RuntimeException if not there */
    public <Y> X findG(Y y, GComp<X,Y> c){ return find(c.curry(y)); }
    /** Remove the first occurence of the given X */
    public List<X> remove(X x){ return remove(new EqualComp().curry(x)); }
    /** Remove the first occurence of the given X */
    public <Y> List<X> removeG(Y y, GComp<X,Y> c){ return remove(c.curry(y)); }
    
    /** Push the reverse of the given list on the front of this one */
    private List<X> revpush(List<X> l){
        List<X> ret = this;
        for(X x:l)ret = ret.push(x);
        return ret;
    }
    /** Remove the first matching X */
    public List<X> remove(List.Pred<X> p){
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            back = back.pop();
            if(p.huh(x))return back.revpush(front);
            front = front.push(x);
        }
        return front.reverse();
    }
    /** The Length of This List */
    public int length(){ return this.len; }
    /** Lookup the i^th item in this List */
    public X lookup(int i){
        List<X> l = this; 
        while(i-- > 0)l = l.pop();
        return l.top();
    }
    /** To String, with a separator and prefix */
    public String toString(String sep, String pre){
        String ret = "";
        boolean first = true;
        for(X x:this){
            if(!first)ret += sep;
            else first = false;
            ret += pre+x;
        }
        return ret;
    }
    /** To String using a Stringer (Visitor) */
    public String toString(Stringer<X> s){
        String ret = "";
        List<X> lst = this;
        while(!lst.isEmpty()){
            ret += s.toString(lst.top(),lst.pop());
            lst = lst.pop();
        }
        return ret;
    }
    /** Filter out all the same Elements */
    public List<X> filterout(X x){ return filterout(new EqualComp().curry(x)); }
    /** Filter out all the matching Elements */
    public List<X> filterout(final List.Pred<X> p){
        return filter(p.negate());
    }
    /** Filter out all the non-same Elements */
    public List<X> filter(X x){ return filter(new EqualComp().curry(x)); }
    /** Filter out all the non-matching Elements */
    public List<X> filter(List.Pred<X> p){
        List<X> keep = List.create();
        for(X x:this)
            if(p.huh(x))keep = keep.push(x);
        return keep.reverse();
    }
    /** Count the elements that match the given Predicate */
    public int count(List.Pred<X> p){
        int c = 0;
        for(X x:this)
            if(p.huh(x))c++;
        return c;
    }
    
    static class RMDup<X> extends List.Fold<X, List<X>>{
        Comp<X> c;
        RMDup(Comp<X> cc){ this.c = cc; }
        public List<X> fold(X x, List<X> l){
            if(l.contains(this.c.curry(x)))return l;
            return l.push(x);
        }
    }
    /** Remove duplicate items from this list */
    public List<X> removeDuplicates(){ return removeDuplicates(new EqualComp()); }
    /** Remove duplicate items from this list */
    public List<X> removeDuplicates(Comp<X> c){
        return this.fold(new RMDup<X>(c), List.<X>create());
    }
    /** Fold this List to a single Value (Left to Right)*/
    public <Y> Y fold(List.Fold<X,Y> f, Y b){ return foldl(f,b); }
    /** Fold this List to a single Value (Left to Right)*/
    public <Y> Y foldl(List.Fold<X,Y> f, Y b){
        for(X x:this)b = f.fold(x, b);
        return b;
    }
    /** Fold this List to a single Value (Right to Left)*/
    public <Y> Y foldr(List.Fold<X,Y> f, Y b){ return reverse().foldl(f, b); }
    /** Apply a function to each Element of this List */
    public <Y> List<Y> map(List.Map<X,Y> m){
        List<Y> ret = List.create();
        for(X x:reverse())ret = ret.push(m.map(x));
        return ret;
    }
    /** Add an Element to this list at the given index */
    public List<X> add(X a, int i){
        int j = i;
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            back = back.pop();
            if(i-- == 0)return back.push(x).push(a).revpush(front);
            front = front.push(x);
        }
        if(i == 0)return front.push(a).reverse();
        throw new RuntimeException("Bad Add Index: "+j);
    }
    /** Remove an Element from this list at the given index */
    public List<X> remove(int i){
        int j = i;
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            back = back.pop();
            if(i-- == 0)return back.revpush(front);
            front = front.push(x);
        }
        throw new RuntimeException("Bad Remove Index: "+j);
    }
    /** Insert a number of Elements into this SORTED list */
    public List<X> insert(Iterable<X> xs, List.Comp<X> c){
        List<X> ths = this;
        for(X x:xs)ths.insert(x, c);
        return ths;
    }
    /** Insert an Element into this SORTED list using the given Comparison */
    public List<X> insert(X a, List.Comp<X> c){
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            if(c.comp(a, x))return back.push(a).revpush(front);
            back = back.pop();
            front = front.push(x);
        }
        return front.push(a).reverse();
    }
    /** Sort this List using the given Comparison */
    public List<X> sort(List.Comp<X> c){
        return mergeSort(c);
    }
    /** Sort this List using Insertion Sort */
    public List<X> insertionSort(List.Comp<X> c){
        List<X> srt = List.create();
        for(X x:this)srt = srt.insert(x, c);
        return srt;
    }
    /** Convert this List into an Array, starting at Index 'i' */
    public X[] toArray(X[] arr){
        int i = 0;
        for(X x:this)arr[i++] = x;
        return arr;
    }
    
    /** Return an Iterator for this list */
    public Iterator<X> iterator(){ return new ListIterator<X>(this); }
    /** An Iterator class for functional Lists */
    static class ListIterator<X> implements Iterator<X>{
        List<X> inner;
        ListIterator(List<X> i){ this.inner = i; }
        public boolean hasNext(){ return !this.inner.isEmpty(); }
        public X next(){
            if(!hasNext())throw new NoSuchElementException("next()");
            X t = this.inner.top();
            this.inner = this.inner.pop();
            return t;
        }
        public void remove(){ throw new UnsupportedOperationException("remove()"); }
    }
    /** Zip two lists (this, and 'l') into a single list, one element at a time */
    public <Y,Z> List<Z> zip(List.Zip<X,Y,Z> z, List<Y> ys){
        List<Z> zs = List.create();
        List<X> xs = this;
        while(!xs.isEmpty() && !ys.isEmpty()){
            zs = zs.push(z.zip(xs.top(), ys.top()));
            xs = xs.pop();
            ys = ys.pop();
        }
        return zs.reverse();
    }
    /** Build a list from the numbers 0..(len-1) */
    public static <X> List<X> buildlist(List.Build<X> b, int len){
        List<X> lst = List.create();
        while(len-- > 0)lst = lst.push(b.build(len));
        return lst;
    }
    /** Replace the element at index 'i' with 's' */
    public List<X> replace(int i, X s){
        int j = i;
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            back = back.pop();
            if(i-- == 0)return back.push(s).revpush(front);
            front = front.push(x);
        }
        throw new RuntimeException("Bad Replace Index: "+j);
    }
    /** Replace the first occurrence of 't' with 's' */
    public List<X> replace(X t, X s){ return replace(new EqualComp().curry(t),s); }
    /** Replace the first matching X with 's' */
    public List<X> replace(List.Pred<X> p, X s){
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X x = back.top();
            back = back.pop();
            if(p.huh(x))return back.push(s).revpush(front);
            front = front.push(x);
        }
        throw new RuntimeException("Bad Replace: "+p);
    }
    /** Replace all occurrences of 't' with 's' */
    public List<X> replaceAll(X t, X s){ return replaceAll(new EqualComp().curry(t),s); }
    /** Replace all matching Xs with 't' */
    public List<X> replaceAll(List.Pred<X> p, X x){
        List<X> front = List.create(), back = this;
        while(!back.isEmpty()){
            X xx = back.top();
            back = back.pop();
            front = front.push(p.huh(xx)?x:xx);
        }
        return front.reverse();
    }
    
    /** Equals for Lists... */
    public abstract boolean equals(Object o);
    /** HashCode for Lists... */
    public abstract int hashCode();
    
    private static class FB<X>{
        List<X> front, back;
        FB(List<X> f, List<X> b){ this.front = f; this.back = b; }
        public String toString(){ return this.front+"::"+this.back; }
    } 
    private FB<X> frontBack(int i){
        List<X> front = List.create(),
                back = this;
        while(i-- > 0 && !back.isEmpty()){
            front = front.push(back.top());
            back = back.pop();
        }
        return new FB<X>(front,back);
    }
    private static <X> List<X> merge(List<X> a, List<X> b, List.Comp<X> c){
        List<X> ret = List.create();
        for(X x:a){
            while(!b.isEmpty() && c.comp(b.top(), x)){
                ret = ret.push(b.top());
                b = b.pop();
            }
            ret = ret.push(x);
        }
        for(X x:b)ret = ret.push(x);
        return ret.reverse();
    }
    private List<X> mergeSort(List.Comp<X> c){
        if(length() < 2)return this;
        FB<X> fb = frontBack(length()/2); 
        return merge(fb.front.mergeSort(c),fb.back.mergeSort(c),c);
    }
    
    
    
    /** Convert this List into a java.util.List */
    public java.util.List<X> toJavaList(){
        java.util.List<X> l = new java.util.ArrayList<X>();
        for(X x:this)l.add(x);
        return l;
    }
    
    /** Return a sublist, starting at index i, with length k */
    public List<X> sublist(int i, int k){
        if(length() < i+k)
            return List.create();
        return pop(i).reverse(k).reverse();
    }
    
    /** Shuffle the elements of this list randomly */
    public List<X> shuffle(){
        List<X> lst = List.create();
        for(X x:this)
            lst = lst.add(x, (int)(Math.random()*(lst.length()+1)));
        return lst;
    }
}

