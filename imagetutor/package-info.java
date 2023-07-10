/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./imagetutor/package-info.java                                *
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

/**
    <style>
      .func{ color: #BB7733; }
      .com{ font-style: italic; color: #880000; }
      .keyw{ font-weight: bold; color: #000088; }
      .num{ color: #00AA00; }
      .str{ color: #CC00AB; }
      img.example{ padding-left: 50px; padding-top: 20px;padding-bottom: 30px; }
    </style>

    <center><h3>JavaWorld and Image Tutorial</h3></center>
    <br />
    
    <p>
      We've been developing a new Java library,
      coined <code>JavaWorld</code>, that supports the development of
      visual and interactive programs in the style of
      <a href="http://docs.racket-lang.org/">DrRacket</a>'s
      <a href="http://docs.racket-lang.org/teachpack/2htdpuniverse.html">2htdp/universe</a>
      module/teachpack, with an object-oriented twist.
    </p>

    <p>
      This tutorial will walk you through the setup and some of the
      features of the <code>JavaWorld</code>.
    </p>
    
    <h3>Setup, Jars, and Documentation</h3>
    <blockquote>

      <h4>Jars</h4>

      <p>
        First, you need
        the <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/JavaWorld.jar"><tt>JavaWorld.jar</tt></a>,
        which contains the Image and World packages/libraries.  Make
        sure the Jar is part of your <i>build-path</i> in
        the <i>Project</i> you want to work from.
      </p>

      <p>
        The important parts of the documentation
        for <tt>JavaWorld</tt> are
        <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/image/package-summary.html">Images</a>
        and <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/world/World.html">Worlds</a>.  Though the
        entire documentation <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/">can be found here</a>.
      </p>

      <p>
        When necessary we will reference specific pages.  As you
        become a more accomplished programmer, more and more of the
        library will make sense, and you'll be able to write more
        complex programs.  Feel free to poke around, but don't get
        lost in all the details too quickly.  We'll guide you through
        the basics, then you can explore more on your own as you get
        more experience.
      </p>
    </blockquote>

    <h3>Images, Scenes, and Colors</h3>
    <blockquote>

      The <tt>JavaWorld</tt> library contains lots of Java classes to
      help with the creation of graphical programs.  Here's a review
      of the important ones.

      <h4>Images</h4>

      All of the <code>Image</code> classes can be used (assuming the
      libraries are setup appropriately) simply by importing them:

      <blockquote>
        <pre><span class='keyw'>import</span> image.*;</pre>
      </blockquote>
        
      The classes are similar in design to
      the <tt>Racket</tt> <tt>2htdp/image</tt> module that you may
      already be familiar with.


      <h4>Classes and Methods</h4>
      
      Creating images is as easy as creating instances of classes.
      There is a full list with
      <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/image/package-summary.html">examples here</a>.
      But a few of the important ones are discussed below.

      <blockquote>
      <h5>Class Constructors</h5>
      <table style="padding-left:40px">
          <tr>
          <td style="text-align:right"><code class='func'>EmptyScene</code></td>
            <td><tt>(<span class='keyw'>int</span> width, <span class='keyw'>int</span> height)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>Circle</code></td>
            <td><tt>(<span class='keyw'>int</span> radius, <span>String</span> mode, <span>String</span> color)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>Rectangle</code></td>
            <td><tt>(<span class='keyw'>int</span> width, <span class='keyw'>int</span> height, <span>String</span> mode, <span>String</span> color)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>Text</code></td>
            <td><tt>(<span>String</span> text, <span class='keyw'>int</span> size, <span>String</span> color)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>Star</code></td>
            <td><tt>(<span class='keyw'>int</span> radius, <span class='keyw'>int</span> points, <span>String</span> mode, <span>String</span> color)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>Overlay</code></td>
            <td><tt>(<span>Image</span> top, <span>Image</span> bot)</tt></td>
          </tr>
          <tr>
            <td style="text-align:right"><code class='func'>OverlayXY</code></td>
            <td><tt>(<span>Image</span> top, <span class='keyw'>int</span> x, <span class='keyw'>int</span> y, <span>Image</span> bot)</tt></td>
          </tr>
      </table>
      </blockquote>

      <blockquote>
      <h5>Methods</h5>
      <blockquote>
      <b><tt>Scenes</tt></b>
      <table style="padding-left:20px">
        <tr>
          <td><code>Scene <span class='func'>placeImage</span>(<span>Image</span> image, <span class='keyw'>int</span> x, <span class='keyw'>int</span> y)</code></td>
        </tr>
        <tr>
          <td><code>Scene <span class='func'>addLine</span>(<span class='keyw'>int</span> fromX, <span class='keyw'>int</span> fromY, <span class='keyw'>int</span> toX, <span class='keyw'>int</span> toY, <span>String</span> color)</code></td>
        </tr>
      </table><br />

      <b><tt>Images</tt></b>
      <table style="padding-left:20px">
        <tr>
          <td><code>Image <span class='func'>overlay</span>(<span>Image</span> top)</code></td>
        </tr>
        <tr>
          <td><code>Image <span class='func'>overlayxy</span>(<span>Image</span> top, <span class='keyw'>int</span> x, <span class='keyw'>int</span> y)</code></td>
        </tr>
      </table>
      </blockquote>
      </blockquote>

      See the next section or
      the <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/image/package-summary.html">image
      documentation</a> for more descriptions and examples.
    </blockquote><br/>

    <h3>Using <code>World.display()</code></h3>
    <blockquote>

      <p>
        Images are pretty easy to create and compose, but in order to
        display them we need something a bit more complicated.  To see
        the image(s) you create, we provide a method that pops up a
        window to display an <tt>Image</tt> (or a <tt>Scene</tt>).
      </p>

      <p>
        <h4>Simple Images/Scenes</h4>

        To display our images, we create a simple class that creates
        a <tt>Scene</tt> and displays it from within a <tt>main</tt>
        method, though this could also be done from within a testing
        framework (I.e.,
        the <a href="http://www.ccs.neu.edu/javalib/Tester/index.html">NU
        Tester</a>, or <a href="http://www.junit.org/">JUnit</a>).
        Here's an initial example, <tt>Example1</tt>, that
        uses <tt>Circle</tt>, <tt>Square</tt>, and <tt>OverlayXY</tt>
        to create a <tt>Scene</tt>. The <tt>Scene</tt> is
        then <tt>display</tt>ed when the <tt>main</tt> method is run.

        <pre>
      <span class='keyw'>import</span> image.*;
      <span class='keyw'>import</span> world.*;
      
      <span class='keyw'>public class</span> Example1{
          <span class='func'>Example1</span>(){}
          
          Scene mt = <span class='keyw'>new</span> <span class='func'>EmptyScene</span>(<span class='num'>100</span>, <span class='num'>100</span>);
          Image circ = <span class='keyw'>new</span> <span class='func'>Circle</span>(<span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"blue"</span>);
          Image sqr = <span class='keyw'>new</span> <span class='func'>Square</span>(<span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"palegreen"</span>);
          Scene scn = mt.<span class='func'>placeImage</span>(<span class='keyw'>new</span> <span class='func'>OverlayXY</span>(circ, -<span class='num'>20</span>, <span class='num'>20</span>, sqr), <span class='num'>50</span>, <span class='num'>50</span>);
          
          <span class='keyw'>public static void</span> <span class='func'>main</span>(String[] args){
              World.<span class='func'>display</span>(<span class='keyw'>new</span> <span class='func'>Example1</span>().scn);
          }
      }
        </pre>

        Running this example results in a window similar to the
        following:<br/>

        <img class='example' src="images/WorldExample-1.png" /><br />

        The <tt>World.display</tt> to show the constructed image/scene
        after we've created an instance
        of <tt>Example1</tt>. <tt>World.display</tt> returns a value
        of <tt class='keyw'>true</tt> once the popup window is closed.
      </p><br />


      <p>
        <h4>Natural-Number Recursion</h4>

        Of course, we're not limited to hand
        constructed <tt>Scene</tt>s since we know how to use recursion
        over the <i>natural numbers</i>.  <tt>Example2</tt>
        implements a recursive method, <tt>stars</tt>, that builds
        a <tt>Scene</tt>.  Our base-case returns
        the <tt>EmptyScene</tt>, and our recursive case places
        (<tt>placeImage</tt>) a <tt>Star</tt> of the
        given <tt>size</tt> in the <tt>Scene</tt> returned by the
        recursive call.

        <pre>
      <span class="keyw">public class</span> Example2{
          <span class="func">Example2</span>(){}
          
          <span class="com">// Create a Scene with nested stars of decreasing size</span>
          Scene <span class="func">stars</span>(<span class="keyw">int</span> size){
              <span class="keyw">if</span>(size &lt;= <span class="num">4</span>){
                  <span class="keyw">return</span> <span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">200</span>, <span class="num">200</span>);
              }<span class="keyw">else</span>{
                  <span class="keyw">return</span> <span class="keyw">this</span>.<span class="func">stars</span>(size-<span class="num">15</span>)
                      .<span class="func">placeImage</span>(<span class="keyw">new</span> <span class="func">Star</span>(size, <span class="str">"outline"</span>, <span class="str">"red"</span>), <span class="num">100</span>, <span class="num">100</span>);
              }
          }

          Scene scn = <span class="keyw">this</span>.<span class="func">stars</span>(<span class="num">100</span>);
          
          <span class="keyw">public static void</span> <span class="func">main</span>(String[] args){
              World.<span class="func">display</span>(<span class='keyw'>new</span> <span class='func'>Example2</span>().scn);
          }
      }
        </pre>

        We start with a <tt>size</tt> of <tt class='num'>100</tt>,
        and <tt>display</tt> the result.  Running this example results
        in a window similar to the following:<br />

        <img class='example' src="images/WorldExample-2.png" /><br />
      </p>


      <p>
        <h4>Structural Recursion</h4>

        As you remember (hopefully), natural-number recursion is just
        a special case of <i>structural recursion</i> (which, in turn,
        is just a special case of
        what <a href="http://www.htdp.org/">HtDP</a>
        calls <i>generative recursion</i>).
      </p>

      <p>
        As an example of a recursive structure, below we define an
        interface and classes that together represent a <i>chain</i>
        of arbitrary length.  <tt>Link</tt> and <tt>End</tt>
        implement <tt>IChain</tt>, which contains a single method
        header.  What's important for this example is that we
        can <tt>place</tt> an image of an <tt>IChain</tt> into a
        given <tt>Scene</tt>.

        <pre>
      <span class="com">// Represents a Chain</span>
      <span class="keyw">interface</span> IChain{
          <span class="com">// Place this IChain into the given Scene</span>
          <span class='keyw'>public</span> Scene <span class="func">place</span>(<span class="keyw">int</span> x, Scene scn);
      }
      <span class="com">// Represents a Link in a Chain</span>
      <span class="keyw">class</span> Link <span class="keyw">implements</span> IChain{
          IChain next;
          
          <span class="func">Link</span>(IChain next){
              <span class="keyw">this</span>.next = next;    
          }
          
          <span class="com">// Place this Link into the given Scene</span>
          <span class='keyw'>public</span> Scene <span class="func">place</span>(<span class="keyw">int</span> x, Scene scn){
              <span class="keyw">return</span> <span class="keyw">this</span>.next.<span class="func">place</span>(x+<span class="num">25</span>, scn)
                  .<span class="func">placeImage</span>(<span class="keyw">new</span> <span class="func">Overlay</span>(<span class="keyw">new</span> <span class="func">Ellipse</span>(<span class="num">28</span>, <span class="num">13</span>, <span class="str">"outline"</span>, <span class="str">"gray"</span>),
                                          <span class="keyw">new</span> <span class="func">Ellipse</span>(<span class="num">30</span>, <span class="num">15</span>, <span class="str">"outline"</span>, <span class="str">"black"</span>)),
                              x, <span class="num">50</span>);
          }
      }
      <span class="com">// Represents the end of a Chain</span>
      <span class="keyw">class</span> End <span class="keyw">implements</span> IChain{
          <span class="func">End</span>(){}
          
          <span class="com">// The end of the chain, return the Scene</span>
          <span class='keyw'>public</span> Scene <span class="func">place</span>(<span class="keyw">int</span> x, Scene scn){
              <span class="keyw">return</span> scn;
          }
      }
        </pre>
      </p>

      <p>
        Each of the classes implements the method, <tt>Scene
        place(<span class="keyw">int</span>, Scene)</tt>, which
        returns the appropriate scene.  For the <tt>End</tt> of
        the <tt>IChain</tt> we simply return the given <tt>Scene</tt>.
        For a <tt>Link</tt> we place an <tt>Ellipse</tt> in
        the <tt>Scene</tt> returned by the recursive call on
        the <tt>next</tt> <tt>IChain</tt>.
      </p>

      <p>
        Now all we need to do is create an examples class that creates
        an <tt>IChain</tt> instance and has a test method that
        displays the <tt>IChain</tt> after placing it into
        the <tt>EmptyScene</tt>.

        <pre>
      <span class="keyw">public class</span> Example3{
          <span class="func">Example3</span>(){}
          
          IChain ch = <span class="keyw">new</span> <span class="func">Link</span>(<span class="keyw">new</span> <span class="func">Link</span>(<span class="keyw">new</span> <span class="func">Link</span>(
                         <span class="keyw">new</span> <span class="func">Link</span>(<span class="keyw">new</span> <span class="func">Link</span>(<span class="keyw">new</span> <span class="func">Link</span>(<span class="keyw">new</span> <span class="func">End</span>()))))));
          Scene scn = <span class="keyw">this</span>.ch.<span class="func">place</span>(<span class="num">40</span>, <span class="keyw">new</span> <span class="func">EmptyScene</span>(<span class="num">200</span>, <span class="num">100</span>));

          <span class="keyw">public static void</span> <span class="func">main</span>(String[] args){
              World.<span class="func">display</span>(<span class='keyw'>new</span> <span class='func'>Example3</span>().scn);
          }
      }
        </pre>

        Running this example results in a window similar to the
        following:<br />

        <img class='example' src="images/WorldExample-3.png" /><br />
      </p>
    </blockquote>
    
    <p>
      <h3>More Information</h3>
      <blockquote>
        If this leaves you craving more, have a look through
        the <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/image/package-summary.html">Image</a>
        and <a href="http://www.ccs.neu.edu/home/chadwick/javaworld/doc/world/package-summary.html">World</a> documentation.
      </blockquote>
    </p>
    
  </body>
</html>
*/
package imagetutor;

