package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.terrain.Terrain;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.worlds.Chunk;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State {
	
	private Player player;
	private World world;
	
	public GameState(Game game){
		super(game);

		world = new World(game);

		//player = new Player(game, Integer.parseInt(world.loaded.get(4).split(" ")[0])*Terrain.width, Integer.parseInt(world.loaded.get(4).split(" ")[1])*Terrain.height);
		player = new Player(game, (world.getSpawnX()), (world.getSpawnY()));
//		player = new Player(game, -58, -58);
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
	public Player getPlayer()
	{
		return player;
	}
	public World getWorld()
	{
		return world;
	}

}
