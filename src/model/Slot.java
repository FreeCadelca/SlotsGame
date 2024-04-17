package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Slot {
    public int value;
    protected Image sprite;

    public Slot(int value, String slotName) {
        this.value = value;
        try {
            URL file = getClass().getResource("/" + slotName + ".png");
            assert file != null;
            this.sprite = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
