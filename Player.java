package com.ParsingWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class Player {
   ImageIcon playerRight = new ImageIcon(getClass().getResource("playerRight.png"));
   ImageIcon playerLeft = new ImageIcon(getClass().getResource("playerLeft.png"));
   ImageIcon playerBack = new ImageIcon(getClass().getResource("playerBack.png"));
   ImageIcon playerFront = new ImageIcon(getClass().getResource("playerFront.png"));
   Point pos;
   Point startPos;
   JLabel icon;
   ArrayList<String> map;
   Movement movement;
   final int BlockSize = 50;

    Player(ArrayList<String> mappedLines){
      map = mappedLines;
      pos = new Point();
      pos = generatePosition();
      startPos = pos;
      icon = new JLabel(playerRight);
      movement = new Movement();
   }

   public Point generatePosition(){
      Point generate = new Point();

      generate.x = ((int) (Math.random() * (map.get(0).length() * BlockSize))) + BlockSize;
      generate.y = ((int) (Math.random() * (map.size() * BlockSize))) + BlockSize ;

      if (generate.x >= (map.get(0).length() * BlockSize) - BlockSize ||
              generate.y >= (map.size() * BlockSize) - BlockSize){
          generate = generatePosition();
      }
      if (map.get((generate.y + 10 ) / BlockSize).charAt((generate.x + 10 ) / BlockSize) == '1' ||
              map.get((generate.y - 10 ) / BlockSize).charAt((generate.x - 10 ) / BlockSize) == '1' ||
              map.get((generate.y + 20 ) / BlockSize).charAt((generate.x + 20 ) / BlockSize) == 'E' ||
              map.get((generate.y - 20 ) / BlockSize).charAt((generate.x - 20 ) / BlockSize) == 'E'){
          generate = generatePosition();
       }
      return generate;
   }


   public class Movement extends KeyAdapter {
//      public void handlePosition(){
////         if( )
//
//         if (map.get((pos.y + BlockSize) / BlockSize).charAt((pos.x + 10) / BlockSize) == 'E') {
//            System.out.printf("Tamanho do mapa\nLargura: %d\nAltura: %d\n", map.get(0).length() * BlockSize, map.size() * BlockSize);
//            System.out.printf("Posição da parede:\nX: %d\nY: %d\n", pos.x / BlockSize, pos.y / BlockSize);
//            System.out.println(map.get((pos.y + BlockSize) / BlockSize).charAt((pos.x + 10) / BlockSize));
//
////          JOptionPane.showMessageDialog(JFrame.getWindows()[0], "Wasted");
//            pos.x = BlockSize + 1;
//            pos.y = BlockSize + 1;
//         }
//
//      }
      @Override
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 38 || e.getKeyCode() == 87){// Arrow up 87
               if (map.get((pos.y - 2) / BlockSize).charAt((pos.x + (BlockSize / 2)) / BlockSize) != '1'){
                  icon.setIcon(playerBack);
                  pos.y -= 10;
               }
            } if (e.getKeyCode() == 40|| e.getKeyCode() == 83){ // Arrow down 83
               if (map.get((pos.y + 2 + BlockSize)/ BlockSize).charAt((pos.x + (BlockSize / 2)) / BlockSize) != '1'){
                  icon.setIcon(playerFront);
                  pos.y += 10;
               }
            } if (e.getKeyCode() == 37|| e.getKeyCode() == 65){ // Arrow left 65
               if (map.get((pos.y + (BlockSize / 2)) / BlockSize).charAt((pos.x - 2) / BlockSize) != '1'){
                  icon.setIcon(playerLeft);
                  pos.x -= 10;
               }
            } if (e.getKeyCode() == 39|| e.getKeyCode() == 68){ // Arrow right 68
               if (map.get((pos.y + (BlockSize / 2)) / BlockSize).charAt((pos.x + 2 + BlockSize) / BlockSize) != '1'){
                  icon.setIcon(playerRight);
                  pos.x += 10;
               }
            } if (e.getKeyCode() == 27){
               System.out.println( "Thanks for play!");
               exit(1);
            }
            icon.setBounds(pos.x, pos.y, 50, 50);

//            handlePosition();
         }
   }
}
