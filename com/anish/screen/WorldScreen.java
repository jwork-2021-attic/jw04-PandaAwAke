package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.World;
import com.anish.maze.Maze;
import com.anish.maze.Node;
import com.anish.maze.Player;
import com.anish.maze.Player.Direction;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Maze maze;
    private Player player;

    private int dimension;

    public WorldScreen(int dimension) {
        this.dimension = dimension;
        world = new World(dimension, dimension);

        maze = new Maze(dimension);
        Node entry = maze.getAnEntry();
        assert entry != null;

        Calabash brother = new Calabash(new Color(204, 0, 0), world);
        player = new Player(maze, world, entry.x, entry.y, brother);

        initializeWorldTiles();
        world.put(brother, entry.x, entry.y);

    }

    private void initializeWorldTiles() {
        Wall wall = new Wall(world);
        Floor floor = new Floor(world);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (maze.isFloor(i, j)) {
                    world.put(floor, i, j);
                } else {
                    world.put(wall, i, j);
                }
            }
        }
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                terminal.write(world.get(i, j).getGlyph(), j, i, world.get(i, j).getColor());
            }
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        Direction direction = null;
        switch (key.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                direction = Direction.left;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                direction = Direction.up;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                direction = Direction.right;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                direction = Direction.down;
                break;
        }

        if (direction != null && player.canMove(direction)) {
            player.doMove(direction);
        }

        return this;
    }

}
