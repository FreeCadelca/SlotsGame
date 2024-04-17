package view;

import controller.Animator;
import controller.GameState;
import model.SlotAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Canvas extends JPanel {
    private static Canvas instance = null;

    public static Canvas getInstance() {
        if (instance == null)
            instance = new Canvas();
        return instance;
    }

    private Canvas() {
        super(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getSize().width, getSize().height);

        if (Animator.getInstance().getGameState() == GameState.SPINNING) {
            for (int i = 0; i < 5; i++) {
                int curNum = Animator.getInstance().currentNumberOfSlot;
                SlotAdapter curSlot = Animator.getInstance().getCurrentSlots().get(
                        (curNum + i) % Animator.getInstance().getCurrentSlots().size()
                );
                curSlot.draw(g, Animator.getInstance().slotsCords[i][0], Animator.getInstance().slotsCords[i][1]);
            }
            Animator.getInstance().currentNumberOfSlot++;
        }

        Image frame = null;
        try {
            URL file = getClass().getResource("/background.png");
            assert file != null;
            frame = ImageIO.read(file);
            g.drawImage(frame, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(new Color(79, 41, 0, 255));
        Font font = new Font(Font.DIALOG, Font.BOLD, 48);
        g.setFont(font);
        g.drawString("Итоговые очки: " + Animator.getInstance().totalSum, 20, 60);
        if (Animator.getInstance().totalCountOfSpins == 0) {
            font = new Font(Font.DIALOG, Font.BOLD, 36);
            g.setFont(font);
            g.drawString("У вас больше не осталось прокруток", 20, 680);
        } else {
            g.drawString("Осталось прокруток: " + Animator.getInstance().totalCountOfSpins, 20, 680);
        }

        if (Animator.getInstance().getGameState() == GameState.RESULT) {
            g.setColor(new Color(166, 58, 0, 255));
            font = new Font(Font.DIALOG, Font.BOLD, 48);
            g.setFont(font);
            int wonValue = Animator.getInstance().getCurrentSlots().get(
                    (Animator.getInstance().finalSlotNumber + 1) % Animator.getInstance().getCurrentSlots().size()
            ).getSlot().value;
            g.drawString("Вы выиграли " + wonValue + " очков!", 1024, 680);
        }
    }

    public void onRepaint() {
        repaint();
    }
}
