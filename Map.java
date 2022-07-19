package com.ParsingWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Map{

    public class Collectables{
        public Point position = new Point();
        public boolean isVisible = true;
        Collectables(Point p){
            position = p;
        }
    }
    public ImageIcon icon = new ImageIcon(getClass().getResource("collect.png"));
    public Image imgCollection = icon.getImage();
    public Vector<Collectables> collectablesCounter = new Vector<Collectables>();

    public int collectRemain;

    Map(ArrayList<String> lines){
        for (int i = 0;i < lines.size() && !lines.get(i).isEmpty(); i++){
            for (int x = 0 ; x < lines.get(i).length() && lines.get(i).charAt(x) != '\0'; x++){
                if(lines.get(i).charAt(x) == 'C'){
                    collectablesCounter.add(new Collectables(new Point(x, i)));
                    lines.get(i).replace('C', '0');
                }
            }
        }
        collectRemain = collectablesCounter.size();
    }
}
