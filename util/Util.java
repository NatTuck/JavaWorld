/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./util/Util.java                                              *
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

package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/** Wrapper class for various helper methods. */
public class Util{
    /** Simple Test for SExp output */
    public static void main(String[] args){
        System.out.println(" Test: "+toSExp(universe.List.create(1,2,3,4,5,6)));
    }
    
    /** Name of Handler Methods */
    public static String funcObjMethName = "apply";
    /** Return a pretty argument string for displaying signatures */
    public static String argsString(Class<?>[] args, int i){
        if(i == args.length)return "";
        if(i == args.length-1)return args[i].getSimpleName();
        return args[i].getSimpleName()+", "+argsString(args,i+1);
    }
    /** For predicting Box/Unboxing and the associated Type differences/subtyping */
    public static Class<?> box(Class<?> c){
        if(c.isPrimitive())return unboxed.get(c);
        return c;
    }
    /** Unboxed -> Boxed primitive types */
    public static HashMap<Class<?>, Class<?>> unboxed = new HashMap<Class<?>, Class<?>>();
    static{
        unboxed.put(short.class, Short.class);
        unboxed.put(int.class, Integer.class);
        unboxed.put(long.class, Long.class);
        unboxed.put(float.class, Float.class);
        unboxed.put(double.class, Double.class);
        unboxed.put(char.class, Character.class);
        unboxed.put(byte.class, Byte.class);
        unboxed.put(boolean.class, Boolean.class);
    }
    /** Subtype comparison */
    public static boolean subtype(Class<?> a, Class<?> b)
    { return box(b).isAssignableFrom(box(a)); }
    /** Array Subtype comparison */
    public static boolean subtypes(Class<?>[] a, Class<?>[] b)
    { return a.length == b.length && subtypes(a,b,0); }
    /** Recursive helper method */
    private static boolean subtypes(Class<?>[] a, Class<?>[] b, int i)
    { return (i >= a.length) || (subtype(a[i],b[i]) && subtypes(a,b,i+1)); }    
    /** Apply the given method using the given Object and arguments */
    public static Object applyFunc(Object f, Method m, Object[] args){
        if(f == null)
            return args[0];
        try{
            if(!m.isAccessible())m.setAccessible(true);
            Object ret = m.invoke(f, args);
            return ret;
        }catch(java.lang.IllegalAccessException iae){
            throw new RuntimeException(iae);
        }catch(java.lang.reflect.InvocationTargetException ite){
            if((ite.getTargetException() instanceof RuntimeException))
                throw (RuntimeException)ite.getCause();
            if((ite.getTargetException() instanceof Error))
                throw (Error)ite.getCause();
            //ite.getTargetException().printStackTrace();
            throw new RuntimeException(ite.getCause());
        }
    }
    /** Return an exception, but drop the top N frames (to hide implementation details) */
    public static RuntimeException exceptionDrop(int n, String msg){
        RuntimeException rte = new RuntimeException(msg);
        StackTraceElement[] st = rte.getStackTrace(), nst = new StackTraceElement[st.length-n-2];
        for(int i = n+2; i < st.length; i++)
            nst[i-n-2] = st[i];
        rte.setStackTrace(nst);
        return rte;
    }
    /** Add an element to the top of a StackTrace, also used to hide implementation details */
    public static RuntimeException addTop(RuntimeException rte, StackTraceElement top){
        StackTraceElement[] st = rte.getStackTrace(), nst = new StackTraceElement[st.length+1];
        for(int i = 0; i < st.length; i++)
            nst[i+1] = st[i];
        nst[0] = top;
        rte.setStackTrace(nst);
        return rte;
    }
    
    /** Return a String representation of the given arbitrary Object */
    public static String display(Object o){ return display(o,""); }
    /** Return a String representation of the given arbitrary Object */
    public static String display(Object o, String idt){
        if(o instanceof Character){ return display(o, char.class,idt); }
        if(o instanceof Short){ return display(o,short.class,idt); }
        if(o instanceof Integer){ return display(o,int.class,idt); }
        if(o instanceof Long){ return display(o,long.class,idt); }
        if(o instanceof Float){ return display(o,float.class,idt); }
        if(o instanceof Double){ return display(o,double.class,idt); }
        if(o instanceof Boolean){ return display(o,boolean.class,idt); }
        return display(o,o.getClass(),idt);
    }
    /** Return a String representation of the given Object of the given Class */
    public static String display(Object o, Class<?> c, String idt){
        if(c.equals(String.class))return "\""+escape(o.toString())+"\"";
        if(c.equals(char.class) || c.equals(Character.class))
            return "\'"+escape(o.toString())+"\'";
        if(c.isPrimitive())return o.toString();
        c = o.getClass();
        String ret = c.getSimpleName()+"(";
        Field[] fs = fields(c);
        if(fs.length > 0)ret += "\n"+idt;
        boolean first = true;
        for(Field f : fs){
            Class<?> fc = f.getType();
            try{
                ret += (!first?(", \n"+idt):"")+f.getName()+" = "+
                       display(f.get(o),fc,idt+repeat(' ',f.getName().length()+3));
                if(first)first = false;
            }catch(Exception e){ throw new RuntimeException(e); }
        }
        return ret+")";
    }
    /** Repeat a character a bunch of times */
    public static String repeat(char c, int times){
        if(times < 0)return "";
        return repeat(c, times-1)+c; 
    }
    /** Return a String with non-printing characters expanded/escaped */
    private static String escape(String s){
        char str[] = s.toCharArray();
        StringBuffer ret = new StringBuffer("");
        for(char c:str)ret.append(escape(c));
        return ret.toString();
    }
    /** Return a String for a single expanded/escaped character*/
    public static String escape(char c){
        switch(c){
        case '\n':return "\\n";  case '\t':return "\\t";
        case '\b':return "\\b";  case '\r':return "\\r";
        case '\f':return "\\f";  case '\\':return "\\\\";
        case '\'':return "\\'"; case '\"':return "\\\"";
        default: return ""+c;
        }
    }
    /** Retrieve the (transitive) fields of the given class */
    public static Field[] fields(Class<?> c){
        java.util.ArrayList<Field> fs = new java.util.ArrayList<Field>();
        while(c != null && !c.equals(Object.class)){
            for(Field f:c.getDeclaredFields()){
                if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
                    if(!f.isAccessible())
                        f.setAccessible(true);
                    fs.add(f);
                }
            }
            c = c.getSuperclass();
        }
        return fs.toArray(new Field[0]);
    }
    /** Return a String representing an SExp of the given arbitrary Object */
    public static String toSExp(Object o){
        if(o instanceof Character){ return toSExp(o, char.class); }
        if(o instanceof Short){ return toSExp(o,short.class); }
        if(o instanceof Integer){ return toSExp(o,int.class); }
        if(o instanceof Long){ return toSExp(o,long.class); }
        if(o instanceof Float){ return toSExp(o,float.class); }
        if(o instanceof Double){ return toSExp(o,double.class); }
        if(o instanceof Boolean){ return toSExp(o,boolean.class); }
        return toSExp(o,o.getClass());
    }
    /** Return a String representing an SExp of the given Object of the given Class */
    private static String toSExp(Object o, Class<?> c){
        if(c.equals(String.class))return "\""+escape(o.toString())+"\"";
        if(c.equals(char.class) || c.equals(Character.class))
            return "\'"+escape(o.toString())+"\'";
        if(c.isPrimitive())return o.toString();
        c = o.getClass();
        String ret = "("+c.getName();
        Field[] fs = fields(c);
        for(Field f : fs){
            try{
                ret += " "+toSExp(f.get(o));
            }catch(Exception e){ throw new RuntimeException(e); }
        }
        return ret+")";
    }
}

