/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/package-info.java                                     *
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
 * <style type='text/css'><!--
 *    .com{ font-style: italic; color: #880000; }
 *    .keyw{ font-weight: bold; color: #000088; }
 *    .num{ color: #00AA00; }
 *    .str{ color: #CC00AB; }
 *    .prim{ color: #0000FF; }
 *    img.example{ padding-left: 50px; padding-bottom: 30px; }
 *  --></style>
 *
 * An image creation package mimics the <a
 * href="http://www.racket-lang.org">Racket</a>'s <a
 * href="http://docs.racket-lang.org/teachpack/2htdpimage.html">2htdp/image</a>
 * library.  Instead of <i>functions</i> we have specific instances of
 * {@link image.Image} subclasses that serve the same purpose(s).
 *
 * <p> One of the main differences between this library and
 *    <code>2htdp/image</code> (besides the implementation language)
 *    is that we draw a distinction between normal
 *    <code>Image</code>s, and {@link image.Scene}s. The latter being a
 *    special form of image that is cropped to a specific size when
 *    drawn. But the uses will be clearer with a few examples.
 * </p>
 *
 * <p> The code that generated all the examples presented here is
 * located in the source of {@link image.test.WriteTests} (available
 * in the JAR distribution), and the examples were mostly taken
 * from the original <a
 * href="http://docs.racket-lang.org/teachpack/2htdpimage.html">2htdp/image</a>
 * documentation, which, for the most part, can be used as a
 * supplement, though not all the fancy features are supported.
 * </p>
 *
 *<p><h3>Modes and Colors</h3>
 *<blockquote>
 *
 * <p><h3>Image mode</h3>
 * 
 *   Each closed image (e.g., {@link image.Circle}, or {@link
 *   image.RegularPolygon}, but <i>not</i> {@link image.Line} or
 *   {@link image.Overlay}) has an associated <i>mode</i>
 *   <code>String</code>, which is either <code
 *   class='str'>"outline"</code> or <code class='str'>"solid"</code>.
 *   This refers whether the image is simply drawn as a single stroke
 *   (<i>outline</i>), or is filled (<i>solid</i>).  If an image is
 *   constructed with a mode string other than outline or solid then
 *   an appropriate <code>RuntimeException</code> is thrown.
 *   
 * </p>
 * <p><h3>Colors</h3>
 *
 *   All basic images have an associated <i>color</i>, also given as a
 *   <code>String</code>.  Color names are managed by the {@link
 *   image.ColorDatabase} class, which has a comprehensive list of predefined
 *   colors.  Names are <i>case-insensitive</i>.
 *   
 * </p>
 * 
 * <p><h3>Custom Colors</h3>
 * 
 *   Besides the named colors like <code class='str'>"blue"</code> or
 *   <code class='str'>"goldenrod"</code>, the color string may also
 *   be a custom RGB color (in <a
 *   href="http://en.wikipedia.org/wiki/Web_colors">6-digit
 *   hexidecimal format</a>) by placing a pound/hash character
 *   (<code>#</code>) at the front of the string.  For instance, an
 *   alternative to <code class='str'>"blue"</code> would be <code
 *   class='str'>"#0000FF"</code>, and an alternative to <code
 *   class='str'>"goldenrod"</code> would be <code
 *   class='str'>"#DAA520"</code>.  If transparency is required you may use an
 *   8 digit hexidecimal number where the first component is Alpha and the
 *   following 6 characters are RGB, e.g., <span class='str'>"#FFFF0000"</span>
 *   for <i>Opaque</i> Red, or <span class='str'>"#8800FF00"</span>
 *   for <i>Half-Transparent</i> Green.
 * </p>
 * 
 *</blockquote>
 *</p>
 *
 *<p><h3>Examples</h3> Each of the classes has its own documentation,
 * but it's better to <i>see</i> what kinds of images each one
 * produces.
 * 
 *<blockquote>
 <h4>Shapes</h4>
 
 The <code>image</code> package contains lots of different basic
 shapes.
 
 <pre>
    <span class='keyw'>new</span> Circle(<span class='num'>30</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/circle-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/circle-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 <img class="example" src="test/images/ellipse-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/ellipse-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Line(<span class='num'>30</span>, <span class='num'>30</span>, <span class='str'>"black"</span>)</pre>
 <img class="example" src="test/images/line-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Line(<span class='num'>-30</span>, <span class='num'>20</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/line-2.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Line(<span class='num'>30</span>, <span class='num'>-20</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/line-3.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Text(<span class='str'>"Hello"</span>, <span class='num'>24</span>, <span class='str'>"olive"</span>)</pre>
 <img class="example" src="test/images/text-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Text(<span class='str'>"Goodbye"</span>, <span class='num'>36</span>, <span class='str'>"indigo"</span>)</pre>
 <img class="example" src="test/images/text-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Triangle(<span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"tan"</span>)</pre>
 <img class="example" src="test/images/triangle-1.png" />
 <br />


 <pre>
    <span class='keyw'>new</span> Triangle(<span class='num'>60</span>, <span class='str'>"outline"</span>, <span class='str'>"purple"</span>)</pre>
 <img class="example" src="test/images/triangle-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Square(<span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"slateblue"</span>)</pre>
 <img class="example" src="test/images/square-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Square(<span class='num'>50</span>, <span class='str'>"outline"</span>, <span class='str'>"darkmagenta"</span>)</pre>
 <img class="example" src="test/images/square-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>40</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 <img class="example" src="test/images/rectangle-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/rectangle-2.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> RoundRectangle(<span class='num'>40</span>, <span class='num'>20</span>, <span class='num'>10</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)</pre>
 <img class="example" src="test/images/rndrectangle-1.png" />
 <br />
 
 <pre>
    <span class='keyw'>new</span> RoundRectangle(<span class='num'>20</span>, <span class='num'>40</span>, <span class='num'>10</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/rndrectangle-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Star(<span class='num'>40</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"gray"</span>)</pre>
 <img class="example" src="test/images/star-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Star(<span class='num'>40</span>, <span class='num'>7</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/star-2.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> Star(<span class='num'>40</span>, <span class='num'>30</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"cornflowerblue"</span>)</pre>
 <img class="example" src="test/images/star-3.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> RegularPolygon(<span class='num'>50</span>, <span class='num'>3</span>, <span class='str'>"outline"</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/polygon-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> RegularPolygon(<span class='num'>40</span>, <span class='num'>4</span>, <span class='str'>"outline"</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/polygon-2.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> RegularPolygon(<span class='num'>20</span>, <span class='num'>8</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>)</pre>
 <img class="example" src="test/images/polygon-3.png" />
 <br />
 <br />

 <h4>From Files / URLs</h4>
 <pre>
    <span class='keyw'>new</span> FromUrl(<span class='str'>"http://maps.google.com/maps/api/staticmap?center=42.358,-71.06&zoom=15&size=200x200&sensor=false"</span>)</pre>
 <img class="example" src="test/images/url-1.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> FromFile(<span class='str'>"imagetutor/images/jurassic.png"</span>)</pre>
 <img class="example" src="test/images/file-1.png" />
 <br />

 <h4>Image Combinations</h4>
 <pre>
    <span class='keyw'>new</span> Overlay(<span class='keyw'>new</span> Rectangle(<span class='num'>30</span>, <span class='num'>60</span>, <span class='str'>"solid"</span>, <span class='str'>"orange"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>60</span>, <span class='num'>30</span>, <span class='str'>"solid"</span>, <span class='str'>"purple"</span>))</pre>
 <img class="example" src="test/images/overlay-1.png" />
 <br />

             
 <pre>
    <span class='keyw'>new</span> Overlay(<span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>30</span>, <span class='num'>30</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>50</span>, <span class='num'>50</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                <span class='keyw'>new</span> Ellipse(<span class='num'>60</span>, <span class='num'>60</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>))</pre>
 <img class="example" src="test/images/overlay-2.png" />
 <br />

        
 <pre>
    <span class='keyw'>new</span> Overlay(<span class='keyw'>new</span> RegularPolygon(<span class='num'>20</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#3232FF"</span>),
                <span class='keyw'>new</span> RegularPolygon(<span class='num'>26</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#6464FF"</span>),
                <span class='keyw'>new</span> RegularPolygon(<span class='num'>32</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#9696FF"</span>),
                <span class='keyw'>new</span> RegularPolygon(<span class='num'>38</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#C8C8FF"</span>),
                <span class='keyw'>new</span> RegularPolygon(<span class='num'>44</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#FAFAFF"</span>))</pre>
 <img class="example" src="test/images/overlay-3.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
                <span class='num'>20</span>, <span class='num'>0</span>, <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>))</pre>
 <img class="example" src="test/images/overlayxy-1.png" />
 <br />

  
 <pre>
    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                <span class='num'>20</span>, <span class='num'>20</span>,
                <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>))</pre>
 <img class="example" src="test/images/overlayxy-2.png" />
 <br />

  
 <pre>
    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                <span class='num'>-20</span>, <span class='num'>-20</span>,
                <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>))</pre>
 <img class="example" src="test/images/overlayxy-3.png" />
 <br />

  
 <pre>
    <span class='keyw'>new</span> OverlayXY(
           <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
                 <span class='num'>10</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)),
                 <span class='num'>20</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>))</pre>
 <img class="example" src="test/images/overlayxy-4.png" />
 <br />
 <br />

 Image overlays are also available as methods on <code>Image</code>s.

 <pre>
    <span class='keyw'>new</span> Ellipse(<span class='num'>60</span>, <span class='num'>30</span>, <span class='str'>"solid"</span>, <span class='str'>"purple"</span>)
        .overlay(<span class='keyw'>new</span> Rectangle(<span class='num'>30</span>, <span class='num'>60</span>, <span class='str'>"solid"</span>, <span class='str'>"orange"</span>))</pre>
 <img class="example" src="test/images/overlay-1.2.png" />
 <br />

    
 <pre>
    <span class='keyw'>new</span> Ellipse(<span class='num'>60</span>, <span class='num'>60</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>)
        .overlay(<span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                 <span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>),
                 <span class='keyw'>new</span> Ellipse(<span class='num'>30</span>, <span class='num'>30</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>),
                 <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>),
                 <span class='keyw'>new</span> Ellipse(<span class='num'>50</span>, <span class='num'>50</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>))</pre>
 <img class="example" src="test/images/overlay-2.2.png" />
 <br />

    
 <pre>
    <span class='keyw'>new</span> RegularPolygon(<span class='num'>44</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#FAFAFF"</span>)
        .overlay(<span class='keyw'>new</span> RegularPolygon(<span class='num'>20</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#3232FF"</span>),
                 <span class='keyw'>new</span> RegularPolygon(<span class='num'>26</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#6464FF"</span>),
                 <span class='keyw'>new</span> RegularPolygon(<span class='num'>32</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#9696FF"</span>),
                 <span class='keyw'>new</span> RegularPolygon(<span class='num'>38</span>, <span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"#C8C8FF"</span>))</pre>
 <img class="example" src="test/images/overlay-3.2.png" />
 <br />
 <br />

 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>)
        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>), <span class='num'>20</span>, <span class='num'>0</span>)</pre>
 <img class="example" src="test/images/overlayxy-1.2.png" />
 <br />

    
 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>)
        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>20</span>, <span class='num'>20</span>)</pre>
 <img class="example" src="test/images/overlayxy-2.2.png" />
 <br />

    
 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>)
        .overlayxy(<span class='keyw'>new</span> Rectangle(<span class='num'>20</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>-20</span>, <span class='num'>-20</span>)</pre>
 <img class="example" src="test/images/overlayxy-3.2.png" />
 <br />

    
 <pre>
    <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)
        .overlayxy(<span class='num'>20</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>10</span>, <span class='num'>10</span>, <span class='str'>"solid"</span>, <span class='str'>"forestgreen"</span>)
                               .overlayxy(<span class='num'>10</span>, <span class='num'>15</span>, <span class='keyw'>new</span> Ellipse(<span class='num'>40</span>, <span class='num'>40</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>))</pre>
 <img class="example" src="test/images/overlayxy-4.2.png" />
 <br />
 <br />

 
 <h4>Transparency/Alpha</h4>
 
 <pre>
    <span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, <span class="str">"#55FF0000"</span>)
      .<span class="func">overlayxy</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, <span class="str">"#5500FF00"</span>)
                   .<span class="func">overlayxy</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, <span class="str">"#550000FF"</span>), -<span class="num">30</span>, <span class="num">0</span>),
                 <span class="num">15</span>, -<span class="num">15</span>)</pre>
 <img class="example" src="test/images/colors-1.png" />
 <br/>
 
 <pre>
    <span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, ColorDatabase.<span class="func">makeColor</span>(<span class="num">0</span>.<span class="num">7</span>, <span class="num">1</span>.<span class="num">0</span>, <span class="num">0</span>, <span class="num">0</span>))
      .<span class="func">overlayxy</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, ColorDatabase.<span class="func">makeColor</span>(<span class="num">179</span>, <span class="num">0</span>, <span class="num">255</span>, <span class="num">0</span>))
                   .<span class="func">overlayxy</span>(<span class="keyw">new</span> <span class="func">Circle</span>(<span class="num">25</span>, <span class="str">"solid"</span>, <span class="str">"#B30000FF"</span>), -<span class="num">30</span>, <span class="num">0</span>),
                 <span class="num">15</span>, -<span class="num">15</span>)</pre>
 <img class="example" src="test/images/colors-2.png" />
 <br/>
 <br/>


 <h4>Image Rotation and Transformation</h4>

 <pre>
    <span class='keyw'>new</span> Rectangle(<span class='num'>60</span>, <span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"black"</span>).rotate(<span class='num'>10</span>)</pre>
 <img class="example" src="test/images/rotate-1.png" />
 <br/>
 
 <pre>         
    <span class='keyw'>new</span> Star(<span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"blue"</span>).rotate(<span class='num'>30</span>)</pre>
 <img class="example" src="test/images/rotate-2.png" />
 <br/>
      
 <pre>
    <span class='keyw'>new</span> Star(<span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"chartreuse"</span>).rotate(<span class='num'>60</span>)</pre>
 <img class="example" src="test/images/rotate-3.png" />
 <br/>
 
 <pre>
    <span class='keyw'>new</span> Triangle(<span class='num'>30</span>, <span class='str'>"solid"</span>, <span class='str'>"tomato"</span>).rotate(<span class='num'>-30</span>)</pre>
 <img class="example" src="test/images/rotate-4.png" />
 <br/>
 
 <pre>
    <span class='keyw'>new</span> OverlayXY(<span class='keyw'>new</span> Text(<span class='str'>"Up..."</span>, <span class='num'>30</span>, <span class='str'>"deepskyblue"</span>).rotate(<span class='num'>90</span>),
                  <span class='num'>20</span>, <span class='num'>0</span>,
                  <span class='keyw'>new</span> Text(<span class='str'>"Down"</span>, <span class='num'>30</span>, <span class='str'>"magenta"</span>).rotate(<span class='num'>-90</span>))</pre>
 <img class="example" src="test/images/rotate-5.png" />
 <br/>

 <pre>
    <span class='keyw'>new</span> Text(<span class='str'>"Horizontal"</span>, <span class='num'>20</span>, <span class='str'>"blue"</span>).flipHorizontal()</pre>
 <img class="example" src="test/images/flip-1.png" />
 <br/>
 
 <pre>
    <span class='keyw'>new</span> Text(<span class='str'>"Vertical"</span>, <span class='num'>20</span>, <span class='str'>"red"</span>).flipVertical()</pre>
 <img class="example" src="test/images/flip-2.png" />
 <br/>
 <br/>


 <h4>Scenes</h4>
        
 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>160</span>, <span class='num'>90</span>)</pre>
 <img class="example" src="test/images/empty-scene.png" />
 <br />
        
 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).placeImage(<span class='keyw'>new</span> Triangle(<span class='num'>32</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>24</span>, <span class='num'>24</span>)</pre>
 <img class="example" src="test/images/scene-1.png" />
 <br />
        
 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).placeImage(<span class='keyw'>new</span> Triangle(<span class='num'>64</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>24</span>, <span class='num'>24</span>)</pre>
 <img class="example" src="test/images/scene-2.png" />
 <br />

 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).addLine(<span class='num'>0</span>, <span class='num'>0</span>, <span class='num'>48</span>, <span class='num'>48</span>, <span class='str'>"blue"</span>)</pre>
 <img class="example" src="test/images/addline-1.png" />
 <br/>
 
 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>48</span>, <span class='num'>48</span>).addLine(<span class='num'>4</span>, <span class='num'>24</span>, <span class='num'>44</span>, <span class='num'>24</span>, <span class='str'>"green"</span>)</pre>
 <img class="example" src="test/images/addline-2.png" />
 <br/>
 
 <pre>
    <span class='keyw'>new</span> EmptyScene(<span class='num'>50</span>, <span class='num'>50</span>)
         .placeImage(<span class='keyw'>new</span> Overlay(<span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"outline"</span>, <span class='str'>"black"</span>),
                                 <span class='keyw'>new</span> Circle(<span class='num'>20</span>, <span class='str'>"solid"</span>, <span class='str'>"wheat"</span>)), <span class='num'>25</span>, <span class='num'>25</span>)
         .placeImage(<span class='keyw'>new</span> Circle(<span class='num'>5</span>, <span class='str'>"solid"</span>, <span class='str'>"lightblue"</span>), <span class='num'>18</span>, <span class='num'>20</span>)
         .placeImage(<span class='keyw'>new</span> Rectangle(<span class='num'>10</span>, <span class='num'>3</span>, <span class='str'>"solid"</span>, <span class='str'>"lightblue"</span>), <span class='num'>33</span>, <span class='num'>20</span>)
         .placeImage(<span class='keyw'>new</span> Ellipse(<span class='num'>20</span>, <span class='num'>8</span>, <span class='str'>"solid"</span>, <span class='str'>"red"</span>), <span class='num'>25</span>, <span class='num'>35</span>)</pre>
 <img class="example" src="test/images/face.png" />
 <br/>

 </blockquote></p>
 */

package image;

