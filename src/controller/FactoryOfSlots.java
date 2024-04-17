package controller;

import model.Diamond;
import model.Seven;
import model.Slot;
import model.SlotAdapter;
import model.coins.DollarCoin;
import model.coins.RubleCoin;
import model.ftuits.Apple;
import model.ftuits.Cherry;
import model.ftuits.Watermelon;

import java.util.ArrayList;
import java.util.Random;

public class FactoryOfSlots {
    private static final ArrayList<Slot> allSlots = new ArrayList<>();
    private static final ArrayList<Slot> possibleSlots = new ArrayList<>();

    private static void completionOfPossibleSlots() {
        for (Slot i : allSlots) {
            for (int j = 0; j < 100 / i.value; j++) {
                possibleSlots.add(i);
            }
        }
    }

    private static void completionOfAllSlots() {
        allSlots.add(new Seven());
        allSlots.add(new Diamond());
        allSlots.add(new Apple());
        allSlots.add(new Cherry());
        allSlots.add(new Watermelon());
        allSlots.add(new RubleCoin());
        allSlots.add(new DollarCoin());
        allSlots.add(new Diamond());
    }

    public static ArrayList<SlotAdapter> generateListOfSlots() {
        completionOfAllSlots();
        completionOfPossibleSlots();
        ArrayList<SlotAdapter> newSlots = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            newSlots.add(new SlotAdapter(possibleSlots.get(random.nextInt(possibleSlots.size()))));
        }
        return newSlots;
    }
}
