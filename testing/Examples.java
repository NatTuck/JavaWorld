/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./testing/Examples.java                                       *
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

package testing;

import java.lang.reflect.*;
import util.Util;

/** The parent of Example/Test classes.
 * <h3>Use of the Examples Class</h3>
 * <p>
 *    In order to use the {@link testing.Examples Examples} 
 *    class, you simply <tt><b>import</b></tt> it, and create your own examples
 *    class that <tt><b>extends</b></tt> {@link testing.Examples Examples}, and insert the
 *    following <i>stub</i> method at the top:
 *    <pre>
 *        <b>public static void</b> main(String[] args){ test(); }
 *    </pre>
 *    
 *    When you run your examples class in Eclipse, this method will start the
 *    create an instance of your examples class, print
 *    it, and run any test methods (methods who's names begin with <tt>test</tt>).  The
 *    resulting report can be used to verify that your tests pass, or see where any
 *    <tt>checkExpect</tt>s failed.</tt>
 *  </p>
 *  
 *  <p>
 *    In order to test specific classes and methods, you can use the appropriate
 *    <tt>checkExpect</tt> methods.  Specially named test method <i>i.e.</i>,
 *    <tt>testFoo</tt> <tt><b>return</b></tt> a number of <tt>checkExpect</tt>s 
 *    combined with <tt>&amp;&amp;</tt> (<i>and</i>ed together).
 *  </p>
 *  
 *  <p>
 *    Below is a sample, which demonstrates most of the testing features:
 *    <style type='text/css'><!--
 *      .def{ color: #000000; }
 *      .grade{
 *          border: solid #FF0000 1px;
 *          background-color: #FFFF60;
 *       }
 *      .gcom{ font-weight: bold; background-color: #FFC0C0; }
 *      .com{ font-style: italic; color: #880000; }
 *      .keyw{ font-weight: bold; color: #000088; }
 *      .num{ color: #00AA00; }
 *      .func{ color: #BB7733; }
 *      .str{ color: #CC00AB; }
 *      .prim{ color: #0000FF; }
 *    --></style>
 *    <pre>
 *     <span class="keyw">import</span><span class="def"> testing.Examples;
 *     
 *     </span><span class="keyw">public</span><span class="def"> </span><span class="keyw">class</span><span class="def"> FooExamples </span><span class="keyw">extends</span><span class="def"> Examples{
 *         </span><span class="keyw">public</span><span class="def"> </span><span class="keyw">static</span><span class="def"> </span><span class="keyw">void</span><span class="def"> </span><span class="func">main</span><span class="def">(</span><span class="prim">String</span><span class="def">[] args){ </span><span class="func">test</span><span class="def">(); }
 *         
 *         Foo f1 = </span><span class="keyw">new</span><span class="def"> </span><span class="func">Foo</span><span class="def">(</span><span class="str">"hello"</span><span class="def">);
 *         Foo f2 = </span><span class="keyw">new</span><span class="def"> </span><span class="func">Foo</span><span class="def">(</span><span class="str">"Jimmy"</span><span class="def">);
 *         Bar b1 = </span><span class="keyw">new</span><span class="def"> </span><span class="func">Bar</span><span class="def">(f1);
 *         Bar b2 = </span><span class="keyw">new</span><span class="def"> </span><span class="func">Bar</span><span class="def">(f2);
 *         
 *         </span><span class="keyw">boolean</span><span class="def"> </span><span class="func">testFoo</span><span class="def">(){
 *             </span><span class="keyw">return</span><span class="def"> (</span><span class="func">checkExpect</span><span class="def">(f1.s, </span><span class="str">"hello"</span><span class="def">) &&
 *                     </span><span class="func">checkExpect</span><span class="def">(f2, </span><span class="keyw">new</span><span class="def"> </span><span class="func">Foo</span><span class="def">(</span><span class="str">"Jimmy"</span><span class="def">)) &&
 *                     </span><span class="func">checkExpect</span><span class="def">(f2.i, </span><span class="num">5</span><span class="def">));
 *         }
 *         </span><span class="keyw">boolean</span><span class="def"> </span><span class="func">testBar</span><span class="def">(){
 *             </span><span class="keyw">return</span><span class="def"> (</span><span class="func">checkExpect</span><span class="def">(b1.</span><span class="func">getFoo</span><span class="def">().s, </span><span class="str">"hello"</span><span class="def">) &&
 *                     </span><span class="func">checkExpect</span><span class="def">(b2.</span><span class="func">getFoo</span><span class="def">(), </span><span class="keyw">new</span><span class="def"> </span><span class="func">Foo</span><span class="def">(</span><span class="str">"Jimmy"</span><span class="def">)) &&
 *                     </span><span class="func">checkExpect</span><span class="def">(b2.</span><span class="func">getFoo</span><span class="def">().i, </span><span class="num">5</span><span class="def">));
 *         }
 *     }
 *     
 *     </span><span class="keyw">class</span><span class="def"> Foo{
 *         </span><span class="keyw">int</span><span class="def"> i = </span><span class="num">5</span><span class="def">;
 *         </span><span class="prim">String</span><span class="def"> s;
 *         </span><span class="func">Foo</span><span class="def">(</span><span class="prim">String</span><span class="def"> s){
 *             </span><span class="keyw">this</span><span class="def">.s = s;
 *         }
 *     }
 *     
 *     </span><span class="keyw">class</span><span class="def"> Bar{
 *         Foo f;
 *         </span><span class="func">Bar</span><span class="def">(Foo f){
 *             </span><span class="keyw">this</span><span class="def">.f = f;
 *         }
 *         Foo </span><span class="func">getFoo</span><span class="def">(){ </span><span class="keyw">return</span><span class="def"> f; }
 *     }</span>
 *    </pre> 
 *  </p>
 *    
 *  */
public class Examples{
    /** Run all the tests for the invoking class, <i>i.e.</i>, the outer
     *    Examples class, passin whether or not to show all the examples */
    public static void test(boolean show){ main(2,show); }
    /** Run all the tests for the invoking class, <i>i.e.</i>, the outer
     *    Examples class, show all examples */
    public static void test(){ main(2,true); }
    /** Run all the tests for the invoking class, <i>i.e.</i>, the outer
     *    Examples class, looking up the class at <tt>depth/tt> in the call
     *    stack. */
    private static void main(int depth, boolean show){
        StackTraceElement[] es = new RuntimeException().getStackTrace();
        if(es.length < depth)
            throw new RuntimeException("Incorrect Example Class Layout");
        Examples e;
        try{
            Class<?> c = Class.forName(es[depth].getClassName());
            Constructor<?> con = c.getConstructor(new Class[0]);
            if(!con.isAccessible())con.setAccessible(true);
            e = (Examples)con.newInstance(new Object[0]);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Error Initializing Examples Class: "+es[1].getClassName()+
            "\n   Make sure YourExamples class is 'public'!");
        }
        e.runTests(show);
    }

    /** Run all the tests and show all the instances for this
     *    Examples class */
    public boolean runTests(){ return runTests(this); }

    /** Run all the tests for this Examples class, tell whether
     *    or not the Example instances should be shown */
    public boolean runTests(boolean show){
        return runTests(this,show);
    }

    /** Version number... */
    private static double version = 0.1;

    /** Run all the tests and show all the instances for the
     *    given examples class */
    private boolean runTests(Examples ex){
        return runTests(ex, true);
    }

    /** Run all the tests for the given examples class, tell whether
     *    or not the Example instances should be shown */
    private boolean runTests(Examples ex, boolean show){
        if(!logEmpty())throw new RuntimeException("Already Tested");
        rawlog(" /"+stars("Tester Version "+version, '-', MAX_WIDTH)+"\\");
        String cName = ex.getClass().getSimpleName();
        if(show){
            rawlog(" |"+stars("Class: "+cName, '*', MAX_WIDTH)+"|");
            log(" |");
            try{
                log(" |  "+Util.display(ex));
            }catch(Exception e){
                log(" |  !! Unable to display "+cName+
                ", likely due to uninitialized Examples");
            }
            log(" |");
        }
        rawlog(" |"+stars("Testing", '*', MAX_WIDTH)+"|");
        log(" |");
        for(Method m:ex.getClass().getDeclaredMethods()){
            if(m.getName().startsWith(TEST) && !Modifier.isStatic(m.getModifiers())){
                if(checkSignature(m)){
                    try{
                        if(!m.isAccessible())m.setAccessible(true);
                        boolean res = (Boolean)m.invoke(ex, new Object[0]);
                        if(!res){
                            log(" |");
                        }
                    }catch(Exception e){
                        log(" |  Recieved unexpected Exception ("+e.getClass().getSimpleName()+") "+
                                "running method "+cName+"."+m.getName());
                        fail++;
                        log(" |");
                    }
                }else{
                    log(" |  Method "+cName+"."+m.getName()+" looks like a Test but has the "+
                    "wrong argument/return types");
                    fail++;
                    log(" |");
                }
            }
        }
        boolean fin = (numtests > 0 && fail == 0);
        if(fin){
            rawlog(" \\"+stars(((numtests == 0)?"No available Tests":
                (numtests == 1)?"The Test Passed":
                    (numtests == 2)?"Both Tests Passed":("All "+numtests+" Tests Passed")),
                    '-', MAX_WIDTH)+"/");
        }
        else{
            rawlog(" \\"+stars("!! "+
                    ((numtests == 0)?"No Test Methods Found":(fail+" out of "+numtests+" Tests Failed"))+" !!",'-',MAX_WIDTH)+"/");
        }
        printLog();
        return fin;
    }
    /** Check the Methods signature for testing: <code><b>boolean</b> testSomething(){ ... }</code> */
    private boolean checkSignature(Method m){
        return (m.getReturnType().equals(Boolean.class) ||
                m.getReturnType().equals(boolean.class)) &&
                m.getParameterTypes().length == 0;
    }
    /** Make a bunch of Stars for bordering */
    private String stars(String head, char one, int width){
        int space = width-head.length();
        return Util.repeat(one, (space+1)/2)+" "+head+" "+Util.repeat(one, space/2);
    }
    /** Output from all Tests... */
    private static String output = "";
    /** Counts for tests and errors */
    private static int numtests = 0, fail = 0;
    /** Print-out Width*/
    private static int MAX_WIDTH = 80;
    /** Just add the string to the output */
    private void rawlog(String s){ output += s+"\n"; }
    /** Log a given string/result */
    private void log(String s){
        int newline = s.indexOf('\n');
        if(newline >= 0){
            log(s.substring(0,newline));
            log(" |     "+s.substring(newline+1));
            return;
        }
        if(s.length() <= MAX_WIDTH+4){
            rawlog(s+Util.repeat(' ',MAX_WIDTH-s.length()+5)+"|");
            return;
        }
        int i = MAX_WIDTH+4;
        while(i > 0 && s.charAt(i) != ' ')i--;
        if(i == 0)
            rawlog(s);
        else{
            rawlog(s.substring(0, i)+Util.repeat(' ',MAX_WIDTH-i+5)+"|");
            log(" |     "+s.substring(i));
        }
    }
    /** Log a given string/result */
    private boolean logEmpty(){ return output.length() == 0; }
    /***/
    private void printLog(){
        System.err.println(output);
    }
    /** Maximum difference for floating point values */
    private static double DIFF = 0.000001;
    /** Prefix for testing methods */
    private static String TEST = "test";
    /** The name of our check/expect methods */
    private static String CHECK = "checkExpect";
    /** Should we log check/expect failures? */
    private static boolean LOGFAILS = true;

    /** Check the given boolean against the Expected one  */
    public boolean checkExpect(boolean a, boolean b){
        numtests++;
        boolean ret = a == b;
        if(!ret && LOGFAILS)addFailure(a,b,outerCheckExpect());
        return ret;
    }
    /** Check the given character against the Expected one */
    public boolean checkExpect(char a, char b){ return checkExpect((long)a,b); }
    /** Check the given short-integer against the Expected one */
    public boolean checkExpect(short a, short b){ return checkExpect((long)a,b); }
    /** Check the given integer against the Expected one */
    public boolean checkExpect(int a, int b){ return checkExpect((long)a,b); }
    /** Check the given long-integer against the Expected one  */
    public boolean checkExpect(long a, long b){
        numtests++;
        boolean ret = a == b;
        if(!ret && LOGFAILS)addFailure(a,b,outerCheckExpect());
        return ret;    
    }
    /** Check the given floating-point number against the Expected one */
    public boolean checkExpect(float a, float b){ return checkExpect((double)a,b); }
    /** Check the given double-precision number against the Expected one */
    public boolean checkExpect(double a, double b){
        numtests++;
        boolean ret = Math.abs(((double)a)-b)<DIFF;
        if(!ret && LOGFAILS)addFailure(a,b,outerCheckExpect());
        return ret;
    }
    /** Check the given String against the Expected one  */
    public boolean checkExpect(String a, String b){ return checkExpect(a,b,String.class); }
    
    /** Check the given Object against the Expected one */
    public boolean checkExpect(Object a, Object b){
        numtests++;
        if(a == b)return true;
        if(a == null || b == null){
            if(LOGFAILS)addFailure(a,b,outerCheckExpect());
            return false;
        }
        return checkExpect(a,b,a.getClass());
    }

    /** Find the last checkExpect method. We assume there is at least one... */
    private String outerCheckExpect(){
        StackTraceElement[] es = new RuntimeException().getStackTrace();
        for(int i = 1; i < es.length; i++){
            if(!es[i].getMethodName().equals(CHECK)){
                StackTraceElement e = es[i];
                return "\""+e.getFileName()+"\": Line "+e.getLineNumber()+" in method \""+e.getMethodName()+"()\"";
            }
        }
        throw new RuntimeException("Illegal Testing Entry!");
    } 

    /** Compare two objects of the same (given) Class */
    private boolean checkExpect(Object a, Object b, Class<?> c){
        if(a == b)return true;
        if(a == null || b == null){
            if(LOGFAILS)addFailure(a,b,outerCheckExpect());
            return false;
        }
        if(!(a.getClass().equals(b.getClass()))){
            if(LOGFAILS)addFailure(a,b,outerCheckExpect());
            return false;
        }

        if(c.isPrimitive() || c.equals(String.class)){
            boolean ret = a.equals(b);
            if(!ret && LOGFAILS)addFailure(a,b,outerCheckExpect());
            return ret;
        }

        Field fs[] = Util.fields(a.getClass());
        for(Field f:fs){
            try{
                if(!checkExpect(f.get(a), f.get(b), f.getType()))
                    return false;
            }catch(Exception e){
                if(LOGFAILS)addException(e, outerCheckExpect());
                return false;
            }
        }
        return true;
    }

    /***/
    private void addFailure(Object a, Object b, String location){
        log(" |  ! CheckExpect Failed in "+location+"");
        log(" |     Received: "+Util.display(a,"             "));
        log(" |     Expected: "+Util.display(b,"             "));
        fail++;
    }
    /***/
    private void addException(Exception e, String location){
        log(" |  ! CheckExpect unexpected exception in "+location+"");
        fail++;
    }
}

