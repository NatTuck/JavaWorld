/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/ColorDatabase.java                                    *
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

package image;

import java.awt.Color;
import java.util.HashMap;

/**
 * <style type='text/css'><!--
 *    .com{ font-style: italic; color: #880000; }
 *    .keyw{ font-weight: bold; color: #000088; }
 *    .num{ color: #00AA00; }
 *    .str{ color: #CC00AB; }
 *    .prim{ color: #0000FF; }
 *    .func{ color: #BB7733; }
 *    .colorName{
 *       text-align: right;
 *       padding: 5px 20px 5px 20px;
 *       color: #CC00AB;
 *    }
 *    .colorRow, .colorName, .colorBlock{
 *       border: 1px solid black;
 *       margin: 5px 0px 5px 0px;
 *    }   
 *  --></style>
 *  
 * Manages color names and translation to implementation-dependent Color structures.
 * 
 * <p>
 *    Available colors are listed in the following table.  If an undefined color is used then a RuntimeException 
 *    is thrown.  Custom colors can be created using the static {@link ColorDatabase#makeColor makeColor} methods.
 * </p>
 * <p>
 *    Alternatively clients can specify a 7 character string beginning with a '#' (hash-mark)
 *    followed by a 6 digit hexadecimal number that encodes the requested RGB color
 *    (Red, Green, Blue), e.g., <span class='str'>"#FF0000"</span> for Red, or
 *    <span class='str'>"#00FF00"</span> for Green.
 *    For advanced users, a 9 character string beginning with a '#' followed by an 8 digit
 *    hexadecimal number that encodes the requested ARGB color (Alpha, Red, Green, Blue), e.g., 
 *    <span class='str'>"#FFFF0000"</span> for <i>Opaque</i> Red, or <span class='str'>"#8800FF00"</span>
 *    for <i>Half-Transparent</i> Green.
 * </p>
 * <p>  
 *    Color names are <i>case-insensitive</i>.
 * </p>
 * 
 *  <center>
 *     <table class='colorTable'>
 *       <tr class='colorRow'><td class='colorName'>"orange red"</td><td class='colorBlock' style="background-color: #ff4500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"orangered"</td><td class='colorBlock' style="background-color: #ff4500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"tomato"</td><td class='colorBlock' style="background-color: #ff6347">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkred"</td><td class='colorBlock' style="background-color: #8b0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"red"</td><td class='colorBlock' style="background-color: #ff0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"firebrick"</td><td class='colorBlock' style="background-color: #b22222">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"crimson"</td><td class='colorBlock' style="background-color: #dc143c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"deeppink"</td><td class='colorBlock' style="background-color: #ff1493">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"maroon"</td><td class='colorBlock' style="background-color: #b03060">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"indian red"</td><td class='colorBlock' style="background-color: #cd5c5c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"indianred"</td><td class='colorBlock' style="background-color: #cd5c5c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium violet red"</td><td class='colorBlock' style="background-color: #c71585">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumvioletred"</td><td class='colorBlock' style="background-color: #c71585">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"violet red"</td><td class='colorBlock' style="background-color: #d02090">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"violetred"</td><td class='colorBlock' style="background-color: #d02090">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightcoral"</td><td class='colorBlock' style="background-color: #f08080">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"hotpink"</td><td class='colorBlock' style="background-color: #ff69b4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"palevioletred"</td><td class='colorBlock' style="background-color: #db7093">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightpink"</td><td class='colorBlock' style="background-color: #ffb6c1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"rosybrown"</td><td class='colorBlock' style="background-color: #bc8f8f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"pink"</td><td class='colorBlock' style="background-color: #ffc0cb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"orchid"</td><td class='colorBlock' style="background-color: #da70d6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lavenderblush"</td><td class='colorBlock' style="background-color: #fff0f5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"snow"</td><td class='colorBlock' style="background-color: #fffafa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"chocolate"</td><td class='colorBlock' style="background-color: #d2691e">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"saddlebrown"</td><td class='colorBlock' style="background-color: #8b4513">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"brown"</td><td class='colorBlock' style="background-color: #843c24">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkorange"</td><td class='colorBlock' style="background-color: #ff8c00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"coral"</td><td class='colorBlock' style="background-color: #ff7f50">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"sienna"</td><td class='colorBlock' style="background-color: #a0522d">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"orange"</td><td class='colorBlock' style="background-color: #ffa500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"salmon"</td><td class='colorBlock' style="background-color: #fa8072">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"peru"</td><td class='colorBlock' style="background-color: #cd853f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkgoldenrod"</td><td class='colorBlock' style="background-color: #b8860b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"goldenrod"</td><td class='colorBlock' style="background-color: #daa520">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"sandybrown"</td><td class='colorBlock' style="background-color: #f4a460">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightsalmon"</td><td class='colorBlock' style="background-color: #ffa07a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darksalmon"</td><td class='colorBlock' style="background-color: #e9967a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"gold"</td><td class='colorBlock' style="background-color: #ffd700">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"yellow"</td><td class='colorBlock' style="background-color: #ffff00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"olive"</td><td class='colorBlock' style="background-color: #808000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"burlywood"</td><td class='colorBlock' style="background-color: #deb887">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"tan"</td><td class='colorBlock' style="background-color: #d2b48c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"navajowhite"</td><td class='colorBlock' style="background-color: #ffdead">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"peachpuff"</td><td class='colorBlock' style="background-color: #ffdab9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"khaki"</td><td class='colorBlock' style="background-color: #f0e68c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkkhaki"</td><td class='colorBlock' style="background-color: #bdb76b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"moccasin"</td><td class='colorBlock' style="background-color: #ffe4b5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"wheat"</td><td class='colorBlock' style="background-color: #f5deb3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"bisque"</td><td class='colorBlock' style="background-color: #ffe4c4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"palegoldenrod"</td><td class='colorBlock' style="background-color: #eee8aa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"blanchedalmond"</td><td class='colorBlock' style="background-color: #ffebcd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium goldenrod"</td><td class='colorBlock' style="background-color: #eaeaad">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumgoldenrod"</td><td class='colorBlock' style="background-color: #eaeaad">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"papayawhip"</td><td class='colorBlock' style="background-color: #ffefd5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mistyrose"</td><td class='colorBlock' style="background-color: #ffe4e1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lemonchiffon"</td><td class='colorBlock' style="background-color: #fffacd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"antiquewhite"</td><td class='colorBlock' style="background-color: #faebd7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cornsilk"</td><td class='colorBlock' style="background-color: #fff8dc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightgoldenrodyellow"</td><td class='colorBlock' style="background-color: #fafad2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"oldlace"</td><td class='colorBlock' style="background-color: #fdf5e6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"linen"</td><td class='colorBlock' style="background-color: #faf0e6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightyellow"</td><td class='colorBlock' style="background-color: #ffffe0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"seashell"</td><td class='colorBlock' style="background-color: #fff5ee">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"beige"</td><td class='colorBlock' style="background-color: #f5f5dc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"floralwhite"</td><td class='colorBlock' style="background-color: #fffaf0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"ivory"</td><td class='colorBlock' style="background-color: #fffff0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"green"</td><td class='colorBlock' style="background-color: #00ff00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lawngreen"</td><td class='colorBlock' style="background-color: #7cfc00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"chartreuse"</td><td class='colorBlock' style="background-color: #7fff00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"green yellow"</td><td class='colorBlock' style="background-color: #adff2f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"greenyellow"</td><td class='colorBlock' style="background-color: #adff2f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"yellow green"</td><td class='colorBlock' style="background-color: #9acd32">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"yellowgreen"</td><td class='colorBlock' style="background-color: #9acd32">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium forest green"</td><td class='colorBlock' style="background-color: #6b8e23">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"olivedrab"</td><td class='colorBlock' style="background-color: #6b8e23">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumforestgreen"</td><td class='colorBlock' style="background-color: #6b8e23">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark olive green"</td><td class='colorBlock' style="background-color: #556b2f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkolivegreen"</td><td class='colorBlock' style="background-color: #556b2f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkseagreen"</td><td class='colorBlock' style="background-color: #8fbc8b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lime"</td><td class='colorBlock' style="background-color: #00ff00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark green"</td><td class='colorBlock' style="background-color: #006400">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkgreen"</td><td class='colorBlock' style="background-color: #006400">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lime green"</td><td class='colorBlock' style="background-color: #32cd32">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"limegreen"</td><td class='colorBlock' style="background-color: #32cd32">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"forest green"</td><td class='colorBlock' style="background-color: #228b22">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"forestgreen"</td><td class='colorBlock' style="background-color: #228b22">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"spring green"</td><td class='colorBlock' style="background-color: #00ff7f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"springgreen"</td><td class='colorBlock' style="background-color: #00ff7f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium spring green"</td><td class='colorBlock' style="background-color: #00fa9a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumspringgreen"</td><td class='colorBlock' style="background-color: #00fa9a">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"sea green"</td><td class='colorBlock' style="background-color: #2e8b57">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"seagreen"</td><td class='colorBlock' style="background-color: #2e8b57">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium sea green"</td><td class='colorBlock' style="background-color: #3cb371">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumseagreen"</td><td class='colorBlock' style="background-color: #3cb371">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"aquamarine"</td><td class='colorBlock' style="background-color: #70d890">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightgreen"</td><td class='colorBlock' style="background-color: #90ee90">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"pale green"</td><td class='colorBlock' style="background-color: #98fb98">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"palegreen"</td><td class='colorBlock' style="background-color: #98fb98">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium aquamarine"</td><td class='colorBlock' style="background-color: #66cdaa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumaquamarine"</td><td class='colorBlock' style="background-color: #66cdaa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"turquoise"</td><td class='colorBlock' style="background-color: #40e0d0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightseagreen"</td><td class='colorBlock' style="background-color: #20b2aa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium turquoise"</td><td class='colorBlock' style="background-color: #48d1cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumturquoise"</td><td class='colorBlock' style="background-color: #48d1cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"honeydew"</td><td class='colorBlock' style="background-color: #f0fff0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mintcream"</td><td class='colorBlock' style="background-color: #f5fffa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"royalblue"</td><td class='colorBlock' style="background-color: #4169e1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dodgerblue"</td><td class='colorBlock' style="background-color: #1e90ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"deepskyblue"</td><td class='colorBlock' style="background-color: #00bfff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cornflowerblue"</td><td class='colorBlock' style="background-color: #6495ed">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"steel blue"</td><td class='colorBlock' style="background-color: #4682b4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"steelblue"</td><td class='colorBlock' style="background-color: #4682b4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightskyblue"</td><td class='colorBlock' style="background-color: #87cefa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark turquoise"</td><td class='colorBlock' style="background-color: #00ced1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkturquoise"</td><td class='colorBlock' style="background-color: #00ced1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cyan"</td><td class='colorBlock' style="background-color: #00ffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"aqua"</td><td class='colorBlock' style="background-color: #00ffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkcyan"</td><td class='colorBlock' style="background-color: #008b8b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"teal"</td><td class='colorBlock' style="background-color: #008080">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"sky blue"</td><td class='colorBlock' style="background-color: #87ceeb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"skyblue"</td><td class='colorBlock' style="background-color: #87ceeb">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cadet blue"</td><td class='colorBlock' style="background-color: #60a0a0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cadetblue"</td><td class='colorBlock' style="background-color: #5f9ea0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark slate gray"</td><td class='colorBlock' style="background-color: #2f4f4f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkslategray"</td><td class='colorBlock' style="background-color: #2f4f4f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightslategray"</td><td class='colorBlock' style="background-color: #778899">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"slategray"</td><td class='colorBlock' style="background-color: #708090">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark slate grey"</td><td class='colorBlock' style="background-color: #2f4f4f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkslategrey"</td><td class='colorBlock' style="background-color: #2f4f4f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightslategrey"</td><td class='colorBlock' style="background-color: #778899">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"slategrey"</td><td class='colorBlock' style="background-color: #708090">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"light steel blue"</td><td class='colorBlock' style="background-color: #b0c4de">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightsteelblue"</td><td class='colorBlock' style="background-color: #b0c4de">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"light blue"</td><td class='colorBlock' style="background-color: #add8e6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightblue"</td><td class='colorBlock' style="background-color: #add8e6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"powderblue"</td><td class='colorBlock' style="background-color: #b0e0e6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"paleturquoise"</td><td class='colorBlock' style="background-color: #afeeee">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightcyan"</td><td class='colorBlock' style="background-color: #e0ffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"aliceblue"</td><td class='colorBlock' style="background-color: #f0f8ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"azure"</td><td class='colorBlock' style="background-color: #f0ffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium blue"</td><td class='colorBlock' style="background-color: #0000cd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumblue"</td><td class='colorBlock' style="background-color: #0000cd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkblue"</td><td class='colorBlock' style="background-color: #00008b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"midnight blue"</td><td class='colorBlock' style="background-color: #191970">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"midnightblue"</td><td class='colorBlock' style="background-color: #191970">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"navy"</td><td class='colorBlock' style="background-color: #24248c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"blue"</td><td class='colorBlock' style="background-color: #0000ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"indigo"</td><td class='colorBlock' style="background-color: #4b0082">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"blue violet"</td><td class='colorBlock' style="background-color: #8a2be2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"blueviolet"</td><td class='colorBlock' style="background-color: #8a2be2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium slate blue"</td><td class='colorBlock' style="background-color: #7b68ee">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumslateblue"</td><td class='colorBlock' style="background-color: #7b68ee">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"slate blue"</td><td class='colorBlock' style="background-color: #6a5acd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"slateblue"</td><td class='colorBlock' style="background-color: #6a5acd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"purple"</td><td class='colorBlock' style="background-color: #a020f0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark slate blue"</td><td class='colorBlock' style="background-color: #483d8b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkslateblue"</td><td class='colorBlock' style="background-color: #483d8b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkviolet"</td><td class='colorBlock' style="background-color: #9400d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark orchid"</td><td class='colorBlock' style="background-color: #9932cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkorchid"</td><td class='colorBlock' style="background-color: #9932cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumpurple"</td><td class='colorBlock' style="background-color: #9370db">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"cornflower blue"</td><td class='colorBlock' style="background-color: #44406c">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"medium orchid"</td><td class='colorBlock' style="background-color: #ba55d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"mediumorchid"</td><td class='colorBlock' style="background-color: #ba55d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"magenta"</td><td class='colorBlock' style="background-color: #ff00ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"fuchsia"</td><td class='colorBlock' style="background-color: #ff00ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkmagenta"</td><td class='colorBlock' style="background-color: #8b008b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"violet"</td><td class='colorBlock' style="background-color: #ee82ee">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"plum"</td><td class='colorBlock' style="background-color: #dda0dd">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lavender"</td><td class='colorBlock' style="background-color: #e6e6fa">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"thistle"</td><td class='colorBlock' style="background-color: #d8bfd8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"ghostwhite"</td><td class='colorBlock' style="background-color: #f8f8ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"white"</td><td class='colorBlock' style="background-color: #ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"whitesmoke"</td><td class='colorBlock' style="background-color: #f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"gainsboro"</td><td class='colorBlock' style="background-color: #dcdcdc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"light gray"</td><td class='colorBlock' style="background-color: #d3d3d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightgray"</td><td class='colorBlock' style="background-color: #d3d3d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"light grey"</td><td class='colorBlock' style="background-color: #d3d3d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"lightgrey"</td><td class='colorBlock' style="background-color: #d3d3d3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"silver"</td><td class='colorBlock' style="background-color: #c0c0c0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"gray"</td><td class='colorBlock' style="background-color: #bebebe">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark gray"</td><td class='colorBlock' style="background-color: #a9a9a9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkgray"</td><td class='colorBlock' style="background-color: #a9a9a9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dim gray"</td><td class='colorBlock' style="background-color: #696969">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dimgray"</td><td class='colorBlock' style="background-color: #696969">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"grey"</td><td class='colorBlock' style="background-color: #bebebe">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dark grey"</td><td class='colorBlock' style="background-color: #a9a9a9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"darkgrey"</td><td class='colorBlock' style="background-color: #a9a9a9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dim grey"</td><td class='colorBlock' style="background-color: #696969">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"dimgrey"</td><td class='colorBlock' style="background-color: #696969">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *       <tr class='colorRow'><td class='colorName'>"black"</td><td class='colorBlock' style="background-color: #000000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
 *     </table>
 *   </center>
 *   
 */
public class ColorDatabase {

    /** Get the {@link java.awt.Color} represented by the given string */
    public static Color color(String s){
        s = s.toLowerCase();
        if(s.startsWith("#") && (s.length() == 7 || s.length() == 9))
            return parseColor(s.substring(1));
        if(colorTable.containsKey(s))
            return colorTable.get(s);
        throw new InvalidColorException("In valid color: \""+s+"\"");
    }
    
    /** The Hexidecimal Digits as a String (in order) */
    private static String HEX_DIGITS = "0123456789ABCDEF";
    
    /** Create a color String from the given Alpha/Red/Green/Blue intensities in [0..255]. */
    public static String makeColor(int red, int green, int blue){
        return makeColor(255, red, blue, green);
    }
    /** Create a color String from the given Alpha/Red/Green/Blue intensities in [0..255]. */
    public static String makeColor(int alpha, int red, int green, int blue){
        alpha = Math.min(alpha, 255);
        red = Math.min(red, 255);
        green = Math.min(green, 255);
        blue = Math.min(blue, 255);
        return "#"+HEX_DIGITS.charAt(alpha/16%16)+
                   HEX_DIGITS.charAt(alpha%16)+
                   HEX_DIGITS.charAt(red/16%16)+
                   HEX_DIGITS.charAt(red%16)+
                   HEX_DIGITS.charAt(green/16%16)+
                   HEX_DIGITS.charAt(green%16)+
                   HEX_DIGITS.charAt(blue/16%16)+
                   HEX_DIGITS.charAt(blue%16);
    }
    /** Create a color String from the given Red/Green/Blue fractional intensities in [0..1.0]. */
    public static String makeColor(double red, double green, double blue){
        return makeColor(1.0, red, green, blue);
    }
    /** Create a color String from the given Alpha/Red/Green/Blue fractional intensities in [0..1.0]. */
    public static String makeColor(double alpha, double red, double green, double blue){
        return makeColor((int)(alpha*255), (int)(red*255), (int)(green*255), (int)(blue*255));
    }
    /** Create a {@link java.awt.Color} from the given Red/Green/Blue fractional intensities in [0..1.0]. */
    public static Color makeAwtColor(double red, double green, double blue){
        return makeAwtColor(1.0, red, green, blue);
    }
    /** Create a {@link java.awt.Color} from the given Alpha/Red/Green/Blue fractional intensities in [0..1.0]. */
    public static Color makeAwtColor(double alpha, double red, double green, double blue){
        return makeAwtColor((int)(alpha*255), (int)(red*255), (int)(green*255), (int)(blue*255));
    }
    /** Create a {@link java.awt.Color} from the given Alpha/Red/Green/Blue intensities in [0..255]. */
    public static Color makeAwtColor(int red, int green, int blue){
        return makeAwtColor(255, red, blue, green);
    }
    /** Create a {@link java.awt.Color} from the given Alpha/Red/Green/Blue intensities in [0..255]. */
    public static Color makeAwtColor(int alpha, int red, int green, int blue){
        alpha = Math.min(alpha, 255);
        red = Math.min(red, 255);
        green = Math.min(green, 255);
        blue = Math.min(blue, 255);
        return new Color(red, green, blue, alpha);
    }
    /** Convert the given Color String into ARGB integer format. */
    public static int colorToARGB(String c){
        Color awt = color(c);
        return colorToARGB(awt.getAlpha(), awt.getRed(), awt.getGreen(), awt.getBlue());
    }
    /** Convert the given Color intensities into ARGB integer format.
     *     RGB intensities must be between 0 and 1.0, inclusive.*/
    public static int colorToARGB(double red, double green, double blue){
        return colorToARGB(1.0, red, green, blue);
    }
    /** Convert the given Alpha and Color intensities into ARGB integer
     *    format.  ARGB intensities must be between 0 and 1.0, inclusive.*/
    public static int colorToARGB(double alpha, double red, double green, double blue){
        return colorToARGB((int)(alpha*255), (int)(red*255), (int)(green*255), (int)(blue*255));
    }
    
    /** Convert the given Color intensities into ARGB integer format.
     *    RGB intensities must be between 0 and 255, inclusive. */
    public static int colorToARGB(int red, int green, int blue){
        return colorToARGB(255, red, green, blue);
    }
    
    private static int ALPHA_SHIFT = 24;
    private static int RED_SHIFT = 16;
    private static int GREEN_SHIFT = 8;
    private static int BLUE_SHIFT = 0;
    
    /** Convert the given Alpha and Color intensities into ARGB integer
     *    format. ARGB intensities must be between 0 and 255, inclusive.*/
    public static int colorToARGB(int alpha, int red, int green, int blue){
        alpha = Math.min(alpha, 255);
        red = Math.min(red, 255);
        green = Math.min(green, 255);
        blue = Math.min(blue, 255);
        return (alpha<<ALPHA_SHIFT)|(red<<RED_SHIFT)|(green<<GREEN_SHIFT)|(blue<<BLUE_SHIFT);
    }
    /** Create a color String from the given integer that encodes Alpha/Red/Green/Blue
     *     intensities.  Each of the low-order bytes encodes an intensity
     *     in [0..255] where the Red/Green/Blue/Alpha are in order from high to low
     *     order bytes. */
    public static String makeColor(int color){
        return makeColor((color>>ALPHA_SHIFT)&0xFF,
                         (color>>RED_SHIFT)&0xFF,
                         (color>>GREEN_SHIFT)&0xFF,
                         (color>>BLUE_SHIFT)&0xFF);
    }
    /** Create a {@link java.awt.Color} from the given integer that encodes Alpha/Red/Green/Blue
     *     intensities.  Each of the low-order bytes encodes an intensity
     *     in [0..255] where the Alpha/Red/Green/Blue/Alpha are in order from high to low
     *     order bytes. */
    public static Color makeAwtColor(int color){
        return makeAwtColor((color>>ALPHA_SHIFT)&0xFF,
                            (color>>RED_SHIFT)&0xFF,
                            (color>>GREEN_SHIFT)&0xFF,
                            (color>>BLUE_SHIFT)&0xFF);
    }
    
    /** Parse a hex-Color from a 6 digit String (two each for Red, Green, Blue */
    private static Color parseColor(String c){
        String one = c.substring(0,2);
        String two = c.substring(2,4);
        String thr = c.substring(4,6);
        
        try{
            if(c.length() == 6){
                return new Color(Integer.parseInt(one, 16),
                                 Integer.parseInt(two, 16),
                                 Integer.parseInt(thr, 16));
            }else{
                return new Color(Integer.parseInt(two, 16),
                                 Integer.parseInt(thr, 16),
                                 Integer.parseInt(c.substring(6,8), 16),
                                 Integer.parseInt(one, 16));
            }
        }catch(Exception e){
            throw new InvalidColorException("In valid custom color: \"#"+c+"\"");
        }
    }
    
    /** Thrown when an invalid color is used */
    public static class InvalidColorException extends RuntimeException{
        private static final long serialVersionUID = 1;

        private InvalidColorException(String s){ super(s); }
    }
    
    /** Available colors... */
    private static HashMap<String, Color> colorTable = new HashMap<String, Color>();
    static{
        colorTable.put("orangered", new Color(0xff, 0x45, 0x00));
        colorTable.put("tomato", new Color(0xff, 0x63, 0x47));
        colorTable.put("darkred", new Color(0x8b, 0x00, 0x00));
        colorTable.put("red", new Color(0xff, 0x00, 0x00));
        colorTable.put("firebrick", new Color(0xb2, 0x22, 0x22));
        colorTable.put("crimson", new Color(0xdc, 0x14, 0x3c));
        colorTable.put("deeppink", new Color(0xff, 0x14, 0x93));
        colorTable.put("maroon", new Color(0xb0, 0x30, 0x60));
        colorTable.put("indian red", new Color(0xcd, 0x5c, 0x5c));
        colorTable.put("indianred", new Color(0xcd, 0x5c, 0x5c));
        colorTable.put("medium violet red", new Color(0xc7, 0x15, 0x85));
        colorTable.put("mediumvioletred", new Color(0xc7, 0x15, 0x85));
        colorTable.put("violet red", new Color(0xd0, 0x20, 0x90));
        colorTable.put("violetred", new Color(0xd0, 0x20, 0x90));
        colorTable.put("lightcoral", new Color(0xf0, 0x80, 0x80));
        colorTable.put("hotpink", new Color(0xff, 0x69, 0xb4));
        colorTable.put("palevioletred", new Color(0xdb, 0x70, 0x93));
        colorTable.put("lightpink", new Color(0xff, 0xb6, 0xc1));
        colorTable.put("rosybrown", new Color(0xbc, 0x8f, 0x8f));
        colorTable.put("pink", new Color(0xff, 0xc0, 0xcb));
        colorTable.put("orchid", new Color(0xda, 0x70, 0xd6));
        colorTable.put("lavenderblush", new Color(0xff, 0xf0, 0xf5));
        colorTable.put("snow", new Color(0xff, 0xfa, 0xfa));
        colorTable.put("chocolate", new Color(0xd2, 0x69, 0x1e));
        colorTable.put("saddlebrown", new Color(0x8b, 0x45, 0x13));
        colorTable.put("brown", new Color(0x84, 0x3c, 0x24));
        colorTable.put("darkorange", new Color(0xff, 0x8c, 0x00));
        colorTable.put("coral", new Color(0xff, 0x7f, 0x50));
        colorTable.put("sienna", new Color(0xa0, 0x52, 0x2d));
        colorTable.put("orange", new Color(0xff, 0xa5, 0x00));
        colorTable.put("salmon", new Color(0xfa, 0x80, 0x72));
        colorTable.put("peru", new Color(0xcd, 0x85, 0x3f));
        colorTable.put("darkgoldenrod", new Color(0xb8, 0x86, 0x0b));
        colorTable.put("goldenrod", new Color(0xda, 0xa5, 0x20));
        colorTable.put("sandybrown", new Color(0xf4, 0xa4, 0x60));
        colorTable.put("lightsalmon", new Color(0xff, 0xa0, 0x7a));
        colorTable.put("darksalmon", new Color(0xe9, 0x96, 0x7a));
        colorTable.put("gold", new Color(0xff, 0xd7, 0x00));
        colorTable.put("yellow", new Color(0xff, 0xff, 0x00));
        colorTable.put("olive", new Color(0x80, 0x80, 0x00));
        colorTable.put("burlywood", new Color(0xde, 0xb8, 0x87));
        colorTable.put("tan", new Color(0xd2, 0xb4, 0x8c));
        colorTable.put("navajowhite", new Color(0xff, 0xde, 0xad));
        colorTable.put("peachpuff", new Color(0xff, 0xda, 0xb9));
        colorTable.put("khaki", new Color(0xf0, 0xe6, 0x8c));
        colorTable.put("darkkhaki", new Color(0xbd, 0xb7, 0x6b));
        colorTable.put("moccasin", new Color(0xff, 0xe4, 0xb5));
        colorTable.put("wheat", new Color(0xf5, 0xde, 0xb3));
        colorTable.put("bisque", new Color(0xff, 0xe4, 0xc4));
        colorTable.put("palegoldenrod", new Color(0xee, 0xe8, 0xaa));
        colorTable.put("blanchedalmond", new Color(0xff, 0xeb, 0xcd));
        colorTable.put("medium goldenrod", new Color(0xea, 0xea, 0xad));
        colorTable.put("mediumgoldenrod", new Color(0xea, 0xea, 0xad));
        colorTable.put("papayawhip", new Color(0xff, 0xef, 0xd5));
        colorTable.put("mistyrose", new Color(0xff, 0xe4, 0xe1));
        colorTable.put("lemonchiffon", new Color(0xff, 0xfa, 0xcd));
        colorTable.put("antiquewhite", new Color(0xfa, 0xeb, 0xd7));
        colorTable.put("cornsilk", new Color(0xff, 0xf8, 0xdc));
        colorTable.put("lightgoldenrodyellow", new Color(0xfa, 0xfa, 0xd2));
        colorTable.put("oldlace", new Color(0xfd, 0xf5, 0xe6));
        colorTable.put("linen", new Color(0xfa, 0xf0, 0xe6));
        colorTable.put("lightyellow", new Color(0xff, 0xff, 0xe0));
        colorTable.put("seashell", new Color(0xff, 0xf5, 0xee));
        colorTable.put("beige", new Color(0xf5, 0xf5, 0xdc));
        colorTable.put("floralwhite", new Color(0xff, 0xfa, 0xf0));
        colorTable.put("ivory", new Color(0xff, 0xff, 0xf0));
        colorTable.put("green", new Color(0x00, 0xff, 0x00));
        colorTable.put("lawngreen", new Color(0x7c, 0xfc, 0x00));
        colorTable.put("chartreuse", new Color(0x7f, 0xff, 0x00));
        colorTable.put("green yellow", new Color(0xad, 0xff, 0x2f));
        colorTable.put("greenyellow", new Color(0xad, 0xff, 0x2f));
        colorTable.put("yellow green", new Color(0x9a, 0xcd, 0x32));
        colorTable.put("yellowgreen", new Color(0x9a, 0xcd, 0x32));
        colorTable.put("medium forest green", new Color(0x6b, 0x8e, 0x23));
        colorTable.put("olivedrab", new Color(0x6b, 0x8e, 0x23));
        colorTable.put("mediumforestgreen", new Color(0x6b, 0x8e, 0x23));
        colorTable.put("dark olive green", new Color(0x55, 0x6b, 0x2f));
        colorTable.put("darkolivegreen", new Color(0x55, 0x6b, 0x2f));
        colorTable.put("darkseagreen", new Color(0x8f, 0xbc, 0x8b));
        colorTable.put("lime", new Color(0x00, 0xff, 0x00));
        colorTable.put("dark green", new Color(0x00, 0x64, 0x00));
        colorTable.put("darkgreen", new Color(0x00, 0x64, 0x00));
        colorTable.put("lime green", new Color(0x32, 0xcd, 0x32));
        colorTable.put("limegreen", new Color(0x32, 0xcd, 0x32));
        colorTable.put("forest green", new Color(0x22, 0x8b, 0x22));
        colorTable.put("forestgreen", new Color(0x22, 0x8b, 0x22));
        colorTable.put("spring green", new Color(0x00, 0xff, 0x7f));
        colorTable.put("springgreen", new Color(0x00, 0xff, 0x7f));
        colorTable.put("medium spring green", new Color(0x00, 0xfa, 0x9a));
        colorTable.put("mediumspringgreen", new Color(0x00, 0xfa, 0x9a));
        colorTable.put("sea green", new Color(0x2e, 0x8b, 0x57));
        colorTable.put("seagreen", new Color(0x2e, 0x8b, 0x57));
        colorTable.put("medium sea green", new Color(0x3c, 0xb3, 0x71));
        colorTable.put("mediumseagreen", new Color(0x3c, 0xb3, 0x71));
        colorTable.put("aquamarine", new Color(0x70, 0xd8, 0x90));
        colorTable.put("lightgreen", new Color(0x90, 0xee, 0x90));
        colorTable.put("pale green", new Color(0x98, 0xfb, 0x98));
        colorTable.put("palegreen", new Color(0x98, 0xfb, 0x98));
        colorTable.put("medium aquamarine", new Color(0x66, 0xcd, 0xaa));
        colorTable.put("mediumaquamarine", new Color(0x66, 0xcd, 0xaa));
        colorTable.put("turquoise", new Color(0x40, 0xe0, 0xd0));
        colorTable.put("lightseagreen", new Color(0x20, 0xb2, 0xaa));
        colorTable.put("medium turquoise", new Color(0x48, 0xd1, 0xcc));
        colorTable.put("mediumturquoise", new Color(0x48, 0xd1, 0xcc));
        colorTable.put("honeydew", new Color(0xf0, 0xff, 0xf0));
        colorTable.put("mintcream", new Color(0xf5, 0xff, 0xfa));
        colorTable.put("royalblue", new Color(0x41, 0x69, 0xe1));
        colorTable.put("dodgerblue", new Color(0x1e, 0x90, 0xff));
        colorTable.put("deepskyblue", new Color(0x00, 0xbf, 0xff));
        colorTable.put("cornflowerblue", new Color(0x64, 0x95, 0xed));
        colorTable.put("steel blue", new Color(0x46, 0x82, 0xb4));
        colorTable.put("steelblue", new Color(0x46, 0x82, 0xb4));
        colorTable.put("lightskyblue", new Color(0x87, 0xce, 0xfa));
        colorTable.put("dark turquoise", new Color(0x00, 0xce, 0xd1));
        colorTable.put("darkturquoise", new Color(0x00, 0xce, 0xd1));
        colorTable.put("cyan", new Color(0x00, 0xff, 0xff));
        colorTable.put("aqua", new Color(0x00, 0xff, 0xff));
        colorTable.put("darkcyan", new Color(0x00, 0x8b, 0x8b));
        colorTable.put("teal", new Color(0x00, 0x80, 0x80));
        colorTable.put("sky blue", new Color(0x87, 0xce, 0xeb));
        colorTable.put("skyblue", new Color(0x87, 0xce, 0xeb));
        colorTable.put("cadet blue", new Color(0x60, 0xa0, 0xa0));
        colorTable.put("cadetblue", new Color(0x5f, 0x9e, 0xa0));
        colorTable.put("dark slate gray", new Color(0x2f, 0x4f, 0x4f));
        colorTable.put("darkslategray", new Color(0x2f, 0x4f, 0x4f));
        colorTable.put("lightslategray", new Color(0x77, 0x88, 0x99));
        colorTable.put("slategray", new Color(0x70, 0x80, 0x90));
        colorTable.put("dark slate grey", new Color(0x2f, 0x4f, 0x4f));
        colorTable.put("darkslategrey", new Color(0x2f, 0x4f, 0x4f));
        colorTable.put("lightslategrey", new Color(0x77, 0x88, 0x99));
        colorTable.put("slategrey", new Color(0x70, 0x80, 0x90));
        colorTable.put("light steel blue", new Color(0xb0, 0xc4, 0xde));
        colorTable.put("lightsteelblue", new Color(0xb0, 0xc4, 0xde));
        colorTable.put("light blue", new Color(0xad, 0xd8, 0xe6));
        colorTable.put("lightblue", new Color(0xad, 0xd8, 0xe6));
        colorTable.put("powderblue", new Color(0xb0, 0xe0, 0xe6));
        colorTable.put("paleturquoise", new Color(0xaf, 0xee, 0xee));
        colorTable.put("lightcyan", new Color(0xe0, 0xff, 0xff));
        colorTable.put("aliceblue", new Color(0xf0, 0xf8, 0xff));
        colorTable.put("azure", new Color(0xf0, 0xff, 0xff));
        colorTable.put("medium blue", new Color(0x00, 0x00, 0xcd));
        colorTable.put("mediumblue", new Color(0x00, 0x00, 0xcd));
        colorTable.put("darkblue", new Color(0x00, 0x00, 0x8b));
        colorTable.put("midnight blue", new Color(0x19, 0x19, 0x70));
        colorTable.put("midnightblue", new Color(0x19, 0x19, 0x70));
        colorTable.put("navy", new Color(0x24, 0x24, 0x8c));
        colorTable.put("blue", new Color(0x00, 0x00, 0xff));
        colorTable.put("indigo", new Color(0x4b, 0x00, 0x82));
        colorTable.put("blue violet", new Color(0x8a, 0x2b, 0xe2));
        colorTable.put("blueviolet", new Color(0x8a, 0x2b, 0xe2));
        colorTable.put("medium slate blue", new Color(0x7b, 0x68, 0xee));
        colorTable.put("mediumslateblue", new Color(0x7b, 0x68, 0xee));
        colorTable.put("slate blue", new Color(0x6a, 0x5a, 0xcd));
        colorTable.put("slateblue", new Color(0x6a, 0x5a, 0xcd));
        colorTable.put("purple", new Color(0xa0, 0x20, 0xf0));
        colorTable.put("dark slate blue", new Color(0x48, 0x3d, 0x8b));
        colorTable.put("darkslateblue", new Color(0x48, 0x3d, 0x8b));
        colorTable.put("darkviolet", new Color(0x94, 0x00, 0xd3));
        colorTable.put("dark orchid", new Color(0x99, 0x32, 0xcc));
        colorTable.put("darkorchid", new Color(0x99, 0x32, 0xcc));
        colorTable.put("mediumpurple", new Color(0x93, 0x70, 0xdb));
        colorTable.put("cornflower blue", new Color(0x44, 0x40, 0x6c));
        colorTable.put("medium orchid", new Color(0xba, 0x55, 0xd3));
        colorTable.put("mediumorchid", new Color(0xba, 0x55, 0xd3));
        colorTable.put("magenta", new Color(0xff, 0x00, 0xff));
        colorTable.put("fuchsia", new Color(0xff, 0x00, 0xff));
        colorTable.put("darkmagenta", new Color(0x8b, 0x00, 0x8b));
        colorTable.put("violet", new Color(0xee, 0x82, 0xee));
        colorTable.put("plum", new Color(0xdd, 0xa0, 0xdd));
        colorTable.put("lavender", new Color(0xe6, 0xe6, 0xfa));
        colorTable.put("thistle", new Color(0xd8, 0xbf, 0xd8));
        colorTable.put("ghostwhite", new Color(0xf8, 0xf8, 0xff));
        colorTable.put("white", new Color(0xff, 0xff, 0xff));
        colorTable.put("whitesmoke", new Color(0xf5, 0xf5, 0xf5));
        colorTable.put("gainsboro", new Color(0xdc, 0xdc, 0xdc));
        colorTable.put("light gray", new Color(0xd3, 0xd3, 0xd3));
        colorTable.put("lightgray", new Color(0xd3, 0xd3, 0xd3));
        colorTable.put("light grey", new Color(0xd3, 0xd3, 0xd3));
        colorTable.put("lightgrey", new Color(0xd3, 0xd3, 0xd3));
        colorTable.put("silver", new Color(0xc0, 0xc0, 0xc0));
        colorTable.put("gray", new Color(0xbe, 0xbe, 0xbe));
        colorTable.put("dark gray", new Color(0xa9, 0xa9, 0xa9));
        colorTable.put("darkgray", new Color(0xa9, 0xa9, 0xa9));
        colorTable.put("dim gray", new Color(0x69, 0x69, 0x69));
        colorTable.put("dimgray", new Color(0x69, 0x69, 0x69));
        colorTable.put("grey", new Color(0xbe, 0xbe, 0xbe));
        colorTable.put("dark grey", new Color(0xa9, 0xa9, 0xa9));
        colorTable.put("darkgrey", new Color(0xa9, 0xa9, 0xa9));
        colorTable.put("dim grey", new Color(0x69, 0x69, 0x69));
        colorTable.put("dimgrey", new Color(0x69, 0x69, 0x69));
        colorTable.put("black", new Color(0x00, 0x00, 0x00));
    }
}

