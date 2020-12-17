package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class deadbush extends Tile {

    public deadbush(int id) {
        super(Assets.deadbush, id);
        System.out.println("Set deadbush to "+id);
    }

}
