package model;

import java.awt.*;

public class SlotAdapter {
    private final Slot slot;

    public SlotAdapter(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public void draw(Graphics g, int x, int y){
        try {
            g.drawImage(this.slot.sprite, x, y, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
