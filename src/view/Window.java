package view;

import controller.keyboard.OnKeyboardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame {
    private static Window instance = null;

    public static Window getInstance() {
        if (instance == null) instance = new Window();
        return instance;
    }

    private Window() {
        super();
        setTitle("Slots");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1792, 768);
        setLocation(50, 150);

        addKeyListener(new KeyAdapter() { // Передаём нажатые клавиши в функцию их обработки
            public void keyPressed(KeyEvent e) {
                OnKeyboardListener.actionPerformed(e);
            }
        });
    }
}
