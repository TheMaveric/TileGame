package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class mountains extends Tile {

	public mountains(int id) {
		super(Assets.mountains, id);
		System.out.println("Set mountains to "+id);
	}

}
