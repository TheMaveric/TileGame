package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class deepWater extends Tile {

	public deepWater(int id) {
		super(Assets.deepWater, id);
		System.out.println("Set dwater to "+id);
	}
	public boolean isSwim()
	{
		return true;
	}
}
