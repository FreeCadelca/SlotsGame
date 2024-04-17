package controller.keyboard;

import controller.Animator;
import controller.GameState;

import java.awt.event.KeyEvent;

public class OnKeyboardListener {
    public static void actionPerformed(KeyEvent event){
        String key = event.getKeyText(event.getKeyCode());
        if ((key.equals("Enter") && Animator.getInstance().getGameState() == GameState.START
                || key.equals("Enter") && Animator.getInstance().getGameState() == GameState.RESULT)
                && Animator.getInstance().totalCountOfSpins > 0) {
            Animator.getInstance().setState(GameState.BEGIN_SPIN);
        }
    }
}
