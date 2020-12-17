package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class desert extends Tile {

	public desert(int id) {
		super(Assets.desert, id);
		System.out.println("Set desert to "+id);
	}

}
