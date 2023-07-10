/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./testing/test/FooExamples.java                               *
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

package testing.test;
import testing.Examples;

/** A Sample class that uses the Examples class. See
 *  {@link testing.Examples Examples} doc page for the code. */
public class FooExamples extends Examples{
    /** Required method to hook into the Examples/testing */
    public static void main(String[] args){ test(); }
    
    Foo f1 = new Foo("hello");
    Foo f2 = new Foo("Jimmy");
    Bar b1 = new Bar(this.f1);
    Bar b2 = new Bar(this.f2);
    
    boolean testFoo(){
        return (checkExpect(this.f1.s, "hello") &&
                checkExpect(this.f2, new Foo("Jimmy")) &&
                checkExpect(this.f2.i, 5));
    }
    boolean testBar(){
        return (checkExpect(this.b1.getFoo().s, "hello") &&
                checkExpect(this.b2.getFoo(), new Foo("Jimmy")) &&
                checkExpect(this.b2.getFoo().i, 5));
    }
    boolean testDoubles(){
        return (checkExpect(5.5, 11*2/4.0000000001));
    }
}

class Foo{
    int i = 5;
    String s;
    Foo(String s){
        this.s = s;
    }
}

class Bar{
    Foo f;
    Bar(Foo f){
        this.f = f;
    }
    Foo getFoo(){ return this.f; }
}
