package com.ParsingWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Window extends JFrame {
    final int BlockSize = 50;
    int windowX;
    int windowY;
    ImageIcon bg = new ImageIcon(getClass().getResource("empty.png"));
    ImageIcon wall = new ImageIcon(getClass().getResource("wall.png"));

    public Player player;
    public ArrayList<String> lines;

    public Map map;
    public Enemy enemy;

    public static void main(String[] args) {
        new Window();
    }

    public void handlePlayerPosition(){
//        if (player.pos == map.collectablesCounter.get(0).position){
//            System.out.println("passou pelo coletavel");
//        }
//
//        System.out.printf("Localizacao do player:\nX: %d\nY: %d\n", player.pos.x / BlockSize, player.pos.y /BlockSize);
//        System.out.printf("Localizacao do inimigo:\nX: %d\nY: %d\n", enemy.enemyCounter.get(0).position.x, enemy.enemyCounter.get(0).position.y);

        for (int i = 0; i< map.collectablesCounter.size(); i++){
            var current = map.collectablesCounter.get(i);
            if ((player.pos.x / BlockSize == current.position.x &&
                        player.pos.y / BlockSize == current.position.y) ||
                ((player.pos.x + BlockSize) / BlockSize == current.position.x &&
                        player.pos.y / BlockSize == current.position.y) ||
                ((player.pos.x + BlockSize) / BlockSize == current.position.x &&
                        (player.pos.y + BlockSize) / BlockSize == current.position.y) ||
                ((player.pos.x) / BlockSize == current.position.x &&
                        (player.pos.y + BlockSize) / BlockSize == current.position.y)) {
//                current.isVisible = false;
                map.collectablesCounter.remove(i);
                map.collectRemain--;
            }
        }
    }

    Window(){
        getSizeMap();
        player = new Player(lines);
        Panel panel = new Panel();
        map = new Map(lines);
        enemy = new Enemy(lines);

        player.icon.setBounds(player.pos.x, player.pos.y, 50,50);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(this.windowX * BlockSize, (this.windowY + 1) * BlockSize);
        this.setLocationRelativeTo(null);

        add(player.icon);
        for (int i = 0; i < enemy.enemyCounter.size(); i++){
                add(enemy.enemyCounter.get(i).enemyLabel);
        }
        add(panel);
        this.setVisible(true);
        this.setLayout(null);
        this.addKeyListener(player.movement);
        new Move().start();

    }
    public class Move extends Thread{
        public void run(){
            while(true){
                handlePlayerPosition();
            }
        }
    }
    public void getSizeMap (){
        Path mapFile = Paths.get("C:\\Users\\Gabriel\\Documents\\Java\\HelloWorld\\src\\com\\ParsingWindow\\map.so_long");
        try{
            lines = new ArrayList<>(Files.readAllLines(mapFile));
            this.windowY = lines.size() ;
            Iterator<String> it = lines.iterator();
            while (it.hasNext()) {
                String newLine = it.next();
                if (newLine.length() != lines.get(0).length()){
                    throw (new Exception("Invalid map"));
                }
                this.windowX = newLine.length();
            }
        } catch (Exception erro){
            System.out.println("Invalid map");
        }
    }

    public class Panel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image imgBg = bg.getImage();
            Image imgWall = wall.getImage();
            for (int y = 0 ; y < windowY; y++){
                for (int x = 0; x < windowX; x++) {
                    g.drawImage(imgBg, x * BlockSize, y * BlockSize, this);
                    if (lines.get(y).charAt(x) == '1') {
                        g.drawImage(imgWall, x * BlockSize, y * BlockSize, this);

//                    for (int i = 0; i < map.collectablesCounter.size(); i++){
//                        if (map.collectablesCounter.get(i).isVisible){
//                           g.drawImage(map.imgCollection, map.collectablesCounter.get(i).position.x * BlockSize, map.collectablesCounter.get(i).position.y * BlockSize, this);
                        for (var current : map.collectablesCounter) {
//                        if (current.isVisible)
                            g.drawImage(map.imgCollection, current.position.x * BlockSize, current.position.y * BlockSize, this);
                        }
                    }
                }
            }
        }
    }
}
