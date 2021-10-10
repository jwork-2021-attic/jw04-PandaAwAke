package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.calabashbros.ComparableMatrix;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private ComparableMatrix<Calabash> bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new ComparableMatrix<>(3, 3, 7);
        bros.setElement(1, 0, new Calabash(new Color(204, 0, 0), 1, world));
        bros.setElement(1, 2, new Calabash(new Color(255, 165, 0), 2, world));
        bros.setElement(0, 1, new Calabash(new Color(252, 233, 79), 3, world));
        bros.setElement(0, 0, new Calabash(new Color(78, 154, 6), 4, world));
        bros.setElement(1, 1, new Calabash(new Color(50, 175, 255), 5, world));
        bros.setElement(2, 0, new Calabash(new Color(114, 159, 207), 6, world));
        bros.setElement(0, 2, new Calabash(new Color(173, 127, 168), 7, world));

        world.put((Calabash) bros.getElement(0, 0), 18, 8);
        world.put((Calabash) bros.getElement(0, 1), 20, 8);
        world.put((Calabash) bros.getElement(0, 2), 22, 8);
        world.put((Calabash) bros.getElement(1, 0), 18, 10);
        world.put((Calabash) bros.getElement(1, 1), 20, 10);
        world.put((Calabash) bros.getElement(1, 2), 22, 10);
        world.put((Calabash) bros.getElement(2, 0), 18, 12);

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(ComparableMatrix<Calabash> bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(ComparableMatrix<Calabash> bros, int rank) {
        for (int i = 0; i < bros.getRows(); i++) {
            for (int j = 0; j < bros.getColumns(); j++) {
                Calabash bro = bros.getElement(i, j);
                if (bro.getRank() == rank) {
                    return bro;
                }
            }

        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
