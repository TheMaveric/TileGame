package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class shallowWater extends Tile {

	public shallowWater(int id) {
		super(Assets.shallowWater, id);
		System.out.println("Set swater to "+id);
	}
	public boolean isSwim()
	{
		return true;
	}

}
