package com.ParsingWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Enemy {
    final int BlockSize = 50;
    public class enemyOpt{
        Point position = new Point();
        JLabel enemyLabel;
        enemyOpt(Point p, ImageIcon icon){
            position = p;
            enemyLabel = new JLabel(icon);
            enemyLabel.setBounds(position.x * BlockSize, position.y * BlockSize, 50, 50);
        }
    }
    public ImageIcon icon = new ImageIcon(getClass().getResource("enemy.gif"));
    public Image imgEnemy = icon.getImage();
    public Vector<enemyOpt> enemyCounter = new Vector<enemyOpt>();
    Enemy(ArrayList<String> lines){
        for (int i = 0;i < lines.size() && !lines.get(i).isEmpty(); i++){
            for (int x = 0 ; x < lines.get(i).length() && lines.get(i).charAt(x) != '\0'; x++){
                if(lines.get(i).charAt(x) == 'E'){
                    enemyCounter.add(new enemyOpt(new Point(x, i), icon));
                }
            }
        }
        System.out.println(enemyCounter.size());
    }



}
