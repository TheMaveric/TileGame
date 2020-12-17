package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class iceSpikes extends Tile {

	public iceSpikes(int id) {
		super(Assets.iceSpikes, id);
		System.out.println("Set iSpikes to "+id);
	}

}
