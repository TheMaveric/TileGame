package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class frozenShallowWater extends Tile {

	public frozenShallowWater(int id) {
		super(Assets.frozenShallowWater, id);
		System.out.println("Set fSWater to "+id);
	}

}
