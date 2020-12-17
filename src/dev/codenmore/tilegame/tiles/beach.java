package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class beach extends Tile {

	public beach(int id) {
		super(Assets.beach, id);
		System.out.println("Set beach to "+id);
	}

}
