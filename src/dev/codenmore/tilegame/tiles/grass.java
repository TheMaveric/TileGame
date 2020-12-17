package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class grass extends Tile {

    public grass(int id) {
        super(Assets.grass, id);
        System.out.println("Set grass to "+id);
    }

}
