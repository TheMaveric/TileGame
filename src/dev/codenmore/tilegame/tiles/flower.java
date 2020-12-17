package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class flower extends Tile {

    public flower(int id) {
        super(Assets.flower, id);
        System.out.println("Set flower to "+id);
    }

}
