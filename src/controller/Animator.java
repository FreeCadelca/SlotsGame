package controller;

import model.SlotAdapter;
import view.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class Animator extends Thread{
    private static Animator instance = null;
    private boolean isActive;
    private GameState gameState;
    private ArrayList<SlotAdapter> currentSlots;
    public int currentNumberOfSlot;
    public int finalSlotNumber;
    public int[][] slotsCords = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
    private int currentSpeed;
    public int totalSum;
    public int totalCountOfSpins;

    public static Animator getInstance() {
        if (instance == null) instance = new Animator();
        return instance;
    }

    public Animator() {
        this.isActive = true;
        this.gameState = GameState.START;
        currentSlots = new ArrayList<>();
        this.currentNumberOfSlot = 0;
        this.finalSlotNumber = 100;
        for (int i = 0; i < 5; i++) {
            this.slotsCords[i][0] = 256 + i * 256;
            this.slotsCords[i][1] = 256;
        }
        this.currentSpeed = 200;
        this.totalSum = 0;
        this.totalCountOfSpins = 10;
    }

    public void run(){
        while (isActive) {
            try {
                if (this.gameState == GameState.BEGIN_SPIN
                        && this.totalCountOfSpins > 0) {
                    this.totalCountOfSpins--;
                    this.currentSlots = FactoryOfSlots.generateListOfSlots();
                    this.gameState = GameState.SPINNING;
                    Random random = new Random();
                    this.finalSlotNumber = 60 + random.nextInt(30);
                    //три спина точно прокрутятся, а потом как повезёт,
                    // на тот и выпадет из последующих 3 спинов
                    this.currentSpeed = 700;
                    currentNumberOfSlot = 0;
                } else if (this.gameState == GameState.SPINNING) {
                    int dif = Math.abs(this.finalSlotNumber / 2 - currentNumberOfSlot);
                    this.currentSpeed = 150 - 7 * (this.finalSlotNumber / 2 - dif) / 6;
                    if (this.finalSlotNumber / 2 - dif <= 5) {
                        this.currentSpeed = 200;
                    }
                    if (this.finalSlotNumber / 2 - dif <= 3) {
                        this.currentSpeed = 350;
                    }
                    if (this.finalSlotNumber / 2 - dif == 1) {
                        this.currentSpeed = 700;
                    }
                }
                if (this.currentNumberOfSlot == this.finalSlotNumber && this.gameState == GameState.SPINNING) {
                    this.totalSum += this.currentSlots.get((finalSlotNumber + 1) % this.currentSlots.size()).getSlot().value;
                    this.gameState = GameState.RESULT;
                    this.currentSpeed = 100;
                }
                Canvas.getInstance().onRepaint();
                Thread.sleep(currentSpeed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    public ArrayList<SlotAdapter> getCurrentSlots() {
        return currentSlots;
    }
}
