/**
 * DrawImageFractal.java
 * Draws an image of a given size with a regex-defined drawing.
 * The regex defines which "quadrants" of the image should be
 * colored black while the rest stay white, displaying a picture.
 *
 * Copyright (c) Jordan Bossman 2014
 */

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

//GUI Panel to display the image after creating it
class DrawFractalGUI extends JFrame
{
    public DrawFractalGUI(int size, BufferedImage image)
    {
        setSize(size, size);
        add(new JLabel(new ImageIcon(image)));

        setVisible(true);
    }
}

public class DrawImageFractal
{
    private BufferedImage image;
    private RegularExpression regex;
    private final static String REGEX_STRING = ".*12.*";
    private final static int SIZE = 1024;

    public static void main(String[] args)
    {
        DrawImageFractal dif = new DrawImageFractal(SIZE, REGEX_STRING);
        dif.buildImage();
    }

    public DrawImageFractal(int size, String regex)
    {
        //Create the new image and init the regex
        image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        this.regex = new RegularExpression(regex);

        //Set all of the pixels to white to start off
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                image.setRGB(x, y, new Color(255, 255, 255).getRGB());
            }
        }
    }

    public void buildImage()
    {
        int width = image.getWidth() / 2;
        int height = image.getHeight() / 2;

        //Grab subimages that represent the 4 quadrants of the big image
        BufferedImage q1 = image.getSubimage(width, 0, width, height);
        BufferedImage q2 = image.getSubimage(0, 0, width, height);
        BufferedImage q3 = image.getSubimage(0, height, width, height);
        BufferedImage q4 = image.getSubimage(width, height, width, height);

        //Recursively go through each pixel giving it a quadrant string to compare to the regex
        labelQuadrant(q1, new StringBuffer("1"));
        labelQuadrant(q2, new StringBuffer("2"));
        labelQuadrant(q3, new StringBuffer("3"));
        labelQuadrant(q4, new StringBuffer("4"));

        //Display the image
        DrawFractalGUI gui = new DrawFractalGUI(width * 2, image);
    }

    private void labelQuadrant(BufferedImage source, StringBuffer str)
    {
        if(source.getWidth() == 1 && source.getHeight() == 1)
        {
            //Down to 1 pixel, compare the quadrant string with the regex
            //if it matches, then change the color to black
            if (regex.matches(str.toString()))
            {
                source.setRGB(0, 0, new Color(0, 0, 0).getRGB());
            }

            return;
        }

        int width = source.getWidth();
        int height = source.getHeight();

        //Add the respective quadrant number to the string
        StringBuffer q1 = new StringBuffer(str).append("1");
        StringBuffer q2 = new StringBuffer(str).append("2");
        StringBuffer q3 = new StringBuffer(str).append("3");
        StringBuffer q4 = new StringBuffer(str).append("4");

        //Recursively go through each of the quadrants
        labelQuadrant(source.getSubimage(width - (width / 2), 0, width / 2, height / 2), q1);
        labelQuadrant(source.getSubimage(0, 0, width / 2, height /2), q2);
        labelQuadrant(source.getSubimage(0, height - (height / 2), width/2, height/2), q3);
        labelQuadrant(source.getSubimage(width - (width / 2), height - (height / 2), width/2, height/2), q4);
    }
}
