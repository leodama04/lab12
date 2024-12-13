package it.unibo.es3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GUI extends JFrame {
    
    private Map<Pair<Integer, Integer>, JButton> buttons = new HashMap<>();
    private Logics logics;
    
    public GUI(int width) {
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        
        JPanel panel = new JPanel(new GridLayout(width,width));
        this.getContentPane().add(panel);
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    GUI.this.logics.advance();
                    for (Pair<Integer, Integer> pos : GUI.this.logics.values()) {
                        GUI.this.buttons.get(pos).setText("*");
                    }
                    if(GUI.this.buttons.entrySet().stream().allMatch(t -> t.getValue().getText().equals("*"))) {
                        System.exit(1);
                    }
                }
            }
        });

        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton("");
                this.buttons.put(pos, jb);
                panel.add(jb);
            }
        }

        final Set<Pair<Integer, Integer>> randomPos = new HashSet<>();
        while (randomPos.size() < 3) {
            int randomX = (int) (Math.random() * width);
            int randomY = (int) (Math.random() * width);
            final Pair<Integer, Integer> randomPosition = new Pair<Integer,Integer>(randomX, randomY);
            if (randomPos.add(randomPosition)) {
                this.buttons.get(randomPosition).setText("*");
                this.logics.addButton(randomPosition);
            }
        }

        this.setVisible(true);
    }

    
}