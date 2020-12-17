package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class badlands extends Tile {

	public badlands(int id) {
		super(Assets.badlands, id);
		System.out.println("Set blands to "+id);
	}

}
