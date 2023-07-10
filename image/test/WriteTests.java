/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                                                                       *
 *   JavaWorld Library, Copyright 2011 Bryan Chadwick                    *
 *                                                                       *
 *   FILE: ./image/test/WriteTests.java                                  *
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

package image.test;

import image.*;
import javax.swing.*;

/** Image tests for documentation, examples are similar (almost identical) to those found
 *    in the <a href="http://docs.racket-lang.org/teachpack/2htdpimage.html">
 *    2htdp/image documentation</a>. */
public class WriteTests{
    public static void main(String[] args){
/*        
        new Circle(30, "outline", "red").toWhiteFile("image/test/images/circle-1.png");
        new Circle(20, "solid", "blue").toWhiteFile("image/test/images/circle-2.png");
        
        new Ellipse(40, 20, "outline", "black").toWhiteFile("image/test/images/ellipse-1.png");
        new Ellipse(20, 40, "solid", "blue").toWhiteFile("image/test/images/ellipse-2.png");
        
        new Line(30, 30, "black").toWhiteFile("image/test/images/line-1.png");
        new Line(-30, 20, "red").toWhiteFile("image/test/images/line-2.png");
        new Line(30, -20, "red").toWhiteFile("image/test/images/line-3.png");
        
        new Text("Hello", 24, "olive").toWhiteFile("image/test/images/text-1.png");
        new Text("Goodbye", 36, "indigo").toWhiteFile("image/test/images/text-2.png");
        
        new Triangle(40, "solid", "tan").toWhiteFile("image/test/images/triangle-1.png");
        new Triangle(60, "outline", "purple").toWhiteFile("image/test/images/triangle-2.png");
        
        new Square(40, "solid", "slateblue").toWhiteFile("image/test/images/square-1.png");
        new Square(50, "outline", "darkmagenta").toWhiteFile("image/test/images/square-2.png");
        
        new Rectangle(40, 20, "outline", "black").toWhiteFile("image/test/images/rectangle-1.png");
        new Rectangle(20, 40, "solid", "blue").toWhiteFile("image/test/images/rectangle-2.png");

*/
  
        new RoundRectangle(40, 20, 10, "outline", "black").toWhiteFile("image/test/images/rndrectangle-1.png");
        new RoundRectangle(20, 40, 10, 20, "solid", "blue").toWhiteFile("image/test/images/rndrectangle-2.png");
        
        /*

        new Star(40, 5, "solid", "gray").toWhiteFile("image/test/images/star-1.png");
        new Star(30, 7, "outline", "red").toWhiteFile("image/test/images/star-2.png");
        new Star(40, 30, 10, "solid", "cornflowerblue").toWhiteFile("image/test/images/star-3.png");

        new RegularPolygon(50, 3, "outline", "red").toWhiteFile("image/test/images/polygon-1.png");
        new RegularPolygon(40, 4, "outline", "blue").toWhiteFile("image/test/images/polygon-2.png");
        new RegularPolygon(20, 8, "solid", "red").toWhiteFile("image/test/images/polygon-3.png");
        
        new Overlay(new Rectangle(30, 60, "solid", "orange"),
                new Ellipse(60, 30, "solid", "purple"))
             .toWhiteFile("image/test/images/overlay-1.png");
        
        new Overlay(new Ellipse(10, 10, "solid", "red"),
                new Ellipse(20, 20, "solid", "black"),
                new Ellipse(30, 30, "solid", "red"),
                new Ellipse(40, 40, "solid", "black"),
                new Ellipse(50, 50, "solid", "red"),
                new Ellipse(60, 60, "solid", "black"))
             .toWhiteFile("image/test/images/overlay-2.png");
        
        new Overlay(new RegularPolygon( 20, 5, "solid", "#3232FF"),
                new RegularPolygon(26, 5, "solid", "#6464FF"),
                new RegularPolygon(32, 5, "solid", "#9696FF"),
                new RegularPolygon(38, 5, "solid", "#C8C8FF"),
                new RegularPolygon(44, 5, "solid", "#FAFAFF"))
            .toWhiteFile("image/test/images/overlay-3.png");
        
        new Ellipse(60, 30, "solid", "purple")
            .overlay(new Rectangle(30, 60, "solid", "orange"))
            .toWhiteFile("image/test/images/overlay-1.2.png");
        
        new Ellipse(60, 60, "solid", "black")
            .overlay(new Ellipse(10, 10, "solid", "red"),
                     new Ellipse(20, 20, "solid", "black"),
                     new Ellipse(30, 30, "solid", "red"),
                     new Ellipse(40, 40, "solid", "black"),
                     new Ellipse(50, 50, "solid", "red"))
            .toWhiteFile("image/test/images/overlay-2.2.png");
        
        new RegularPolygon(44, 5, "solid", "#FAFAFF")
            .overlay(new RegularPolygon( 20, 5, "solid", "#3232FF"),
                     new RegularPolygon(26, 5, "solid", "#6464FF"),
                     new RegularPolygon(32, 5, "solid", "#9696FF"),
                     new RegularPolygon(38, 5, "solid", "#C8C8FF"))
            .toWhiteFile("image/test/images/overlay-3.2.png");
        
        new OverlayXY(new Rectangle(20, 20, "outline", "black"),
                20, 0, new Rectangle(20, 20, "outline", "black"))
            .toWhiteFile("image/test/images/overlayxy-1.png");
  
        new OverlayXY(new Rectangle(20, 20, "solid", "red"),
                20, 20,
                new Rectangle(20, 20, "solid", "black"))
           .toWhiteFile("image/test/images/overlayxy-2.png");
  
        new OverlayXY(new Rectangle(20, 20, "solid", "red"),
                -20, -20,
                new Rectangle(20, 20, "solid", "black"))
           .toWhiteFile("image/test/images/overlayxy-3.png");
        
        new OverlayXY(
           new OverlayXY(new Ellipse(40, 40, "outline", "black"),
                 10, 15, new Ellipse(10, 10, "solid", "forestgreen")),
           20, 15, new Ellipse(10, 10, "solid", "forestgreen"))
           .toWhiteFile("image/test/images/overlayxy-4.png");
           
        new Rectangle(20, 20, "outline", "black")
            .overlayxy(new Rectangle(20, 20, "outline", "black"), 20, 0) 
            .toWhiteFile("image/test/images/overlayxy-1.2.png");
  
        new Rectangle(20, 20, "solid", "black")
            .overlayxy(new Rectangle(20, 20, "solid", "red"), 20, 20)
            .toWhiteFile("image/test/images/overlayxy-2.2.png");
  
        new Rectangle(20, 20, "solid", "black")
            .overlayxy(new Rectangle(20, 20, "solid", "red"), -20, -20)
            .toWhiteFile("image/test/images/overlayxy-3.2.png");
  
        new Ellipse(10, 10, "solid", "forestgreen")
            .overlayxy(20, 15, new Ellipse(10, 10, "solid", "forestgreen")
                                   .overlayxy(10, 15, new Ellipse(40, 40, "outline", "black")))
           .toWhiteFile("image/test/images/overlayxy-4.2.png");
        
        new EmptyScene(160, 90).toWhiteFile("image/test/images/empty-scene.png");
        
        new EmptyScene(48, 48).placeImage(new Triangle(32, "solid", "red"), 24, 24)
           .toWhiteFile("image/test/images/scene-1.png");
        
        new EmptyScene(48, 48).placeImage(new Triangle(64, "solid", "red"), 24, 24)
           .toWhiteFile("image/test/images/scene-2.png");
        
        new EmptyScene(48, 48).addLine(0, 0, 48, 48, "blue")
           .toWhiteFile("image/test/images/addline-1.png");

        new EmptyScene(48, 48).addLine(4, 24, 44, 24, "green")
           .toWhiteFile("image/test/images/addline-2.png");
        
        new EmptyScene(50, 50)
                .placeImage(new Overlay(new Circle(20, "outline", "black"),
                                        new Circle(20, "solid", "wheat")), 25, 25)
                .placeImage(new Circle(5, "solid", "lightblue"), 18, 20)
                .placeImage(new Rectangle(10, 3, "solid", "lightblue"), 33, 20)
                .placeImage(new Ellipse(20, 8, "solid", "red"), 25, 35)
           .toWhiteFile("image/test/images/face.png");
        
        new Rectangle(60, 20, "solid", "black").rotate(10)
            .toWhiteFile("image/test/images/rotate-1.png");
        
        new Star(20, "outline", "blue").rotate(30)
            .toWhiteFile("image/test/images/rotate-2.png");
        
        new Star(20, "solid", "chartreuse").rotate(60)
            .toWhiteFile("image/test/images/rotate-3.png");
        
        new Triangle(30, "solid", "tomato").rotate(-30)
            .toWhiteFile("image/test/images/rotate-4.png");
        new OverlayXY(new Text("Up...", 30, "deepskyblue").rotate(90),
                      20, 0,
                      new Text("Down", 30, "magenta").rotate(-90))
            .toWhiteFile("image/test/images/rotate-5.png");
        
        new FromURL("http://maps.google.com/maps/api/staticmap?center=42.358,-71.06&zoom=15&size=200x200&sensor=false")
            .toWhiteFile("image/test/images/url-1.png");

        new FromFile("imagetutor/images/jurassic.png")
            .toWhiteFile("image/test/images/file-1.png");

        new Circle(25, "solid", "#55FF0000")
          .overlayxy(new Circle(25, "solid", "#5500FF00")
                       .overlayxy(new Circle(25, "solid", "#550000FF"), -30, 0),
                     15, -15)
            .toWhiteFile("image/test/images/colors-1.png");

        new Circle(25, "solid", ColorDatabase.makeColor(0.7, 1.0, 0, 0))
          .overlayxy(new Circle(25, "solid", ColorDatabase.makeColor(179, 0, 255, 0))
                       .overlayxy(new Circle(25, "solid", "#B30000FF"), -30, 0),
                     15, -15)
            .toWhiteFile("image/test/images/colors-2.png");
        
        new Text("Horizontal", 20, "blue").flipHorizontal()
            .toWhiteFile("image/test/images/flip-1.png");
        
        new Text("Vertical", 20, "red").flipVertical()
            .toWhiteFile("image/test/images/flip-2.png");
        
       new Square(100, "outline", "deepskyblue").rotate(40)
            .toWhiteFile("image/test/images/rotate-6.png");
       new FromFile("ram-1.png").rotate(45)
            .toWhiteFile("image/test/images/rotate-7.png");
            
      
       new Widget(new javax.swing.JButton("HELLO"), 10, 10)
            .toWhiteFile("image/test/images/widget-1.png");
       
       new Widget(new javax.swing.JCheckBox("Say 'HELLO'?"), 10, 10)
            .toWhiteFile("image/test/images/widget-2.png");

       JPanel p = new JPanel(new java.awt.GridLayout(2,1));
       p.add(new JRadioButton("HELLO"));
       p.add(new JRadioButton("GOODBYE"));
       JFrame f = new JFrame("SAMPLE");
       f.setSize(200, 200);
       f.getContentPane().add(p);
       f.setVisible(true);
       //new Widget(p, 10, 10)
       //    .toWhiteFile("image/test/images/widget-4.png");
      
      /**/

    }
}

