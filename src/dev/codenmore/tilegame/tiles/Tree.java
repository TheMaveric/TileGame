package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class Tree extends Tile {

    public Tree(int id) {
        super(Assets.tree, id);
        System.out.println("Set Tree to "+id);
    }

}
