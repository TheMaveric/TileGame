package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class badlandsPlateau extends Tile {

	public badlandsPlateau(int id) {
		super(Assets.badlandsPlateau, id);
		System.out.println("Set blandsPlat to "+id);
	}

}