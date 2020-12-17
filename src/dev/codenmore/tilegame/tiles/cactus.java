package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class cactus extends Tile {

    public cactus(int id) {
        super(Assets.cactus, id);
        System.out.println("Set cactus to "+id);
    }

}
