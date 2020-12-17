package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class snowTree extends Tile {

    public snowTree(int id) {
        super(Assets.snowTree, id);
        System.out.println("Set snowTree to "+id);
    }

}
