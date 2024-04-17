import controller.Animator;
import view.Canvas;
import view.Window;

import java.awt.*;

public class Main {
    public static void main(String[] argc) {
        Window.getInstance().setVisible(true);
        Window.getInstance().add(Canvas.getInstance(), BorderLayout.CENTER);
        Animator.getInstance().start();
    }
}
