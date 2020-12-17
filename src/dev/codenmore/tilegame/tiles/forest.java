package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class forest extends Tile {

	public forest(int id) {
		super(Assets.forest, id);
		System.out.println("Set forest to "+id);
	}

}
