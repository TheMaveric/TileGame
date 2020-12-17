package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class frozenDeepWater extends Tile {

	public frozenDeepWater(int id) {
		super(Assets.frozenDeepWater, id);
		System.out.println("Set fDWater to "+id);
	}

}
