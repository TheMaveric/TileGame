package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class jungle extends Tile {

	public jungle(int id) {
		super(Assets.jungle, id);
		System.out.println("Set jungle to "+id);
	}

}
