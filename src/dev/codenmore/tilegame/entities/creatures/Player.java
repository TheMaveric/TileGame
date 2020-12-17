package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.terrain.Terrain;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.worlds.Chunk;

public class Player extends Creature {
	public float xp,yp;
	public static int chunkXPrev,chunkYPrev;
	private Animation animUp,animDown,animLeft,animRight,animSUp,animSDown,animSLeft,animSRight;
	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		xp=x;
		yp=y;
		animDown = new Animation(250, Assets.player_standing); //player_down asset not made
		animUp = new Animation(250, Assets.player_standing); //player_up asset not made
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		animSDown = new Animation(250, Assets.player_sleft); //player_sdown asset not made
		animSUp = new Animation(250, Assets.player_sright); //player_sup asset not made
		animSLeft = new Animation(250, Assets.player_sleft);
		animSRight = new Animation(250, Assets.player_sright);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animSDown.tick();
		animSUp.tick();
		animSLeft.tick();
		animSRight.tick();
		//Movement
		getInput();
		move();
		game.getGameCamera().centerOnEntity(this);
	}

	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up) {
			yMove = -speed;
			xp-=speed/Tile.TILEWIDTH;
		}
		if(game.getKeyManager().down){
			yMove = speed;
			xp+=speed/Tile.TILEWIDTH;
		}
		if(game.getKeyManager().left){
			xMove = -speed;
			yp-=speed/Tile.TILEHEIGHT;
		}
		if(game.getKeyManager().right){
			xMove = speed;
			yp+=speed/Tile.TILEHEIGHT;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
	}

	private BufferedImage getCurrentAnimationFrame(){
//		int xStart = (int) (game.getGameCamera().getxOffset())/Tile.TILEWIDTH;
//		int yStart = (int) (game.getGameCamera().getyOffset())/Tile.TILEHEIGHT;
//		int xChunk = Player.chunkXPrev;
//		int yChunk = Player.chunkYPrev;
//		int dx=xStart-xChunk;
//		int dy=yStart-yChunk;
//		int py=dx+((game.getWidth()/2)/Tile.TILEWIDTH);
//		int px=dy+((game.getHeight()/2)/Tile.TILEHEIGHT);
//		GameState gs= (GameState) State.getState();
//		int xcord= (int)(gs.getPlayer().xp+Terrain.width/2);
//		int ycord=(int)(gs.getPlayer().yp+Terrain.height/2);
		//System.out.println(px+" "+py);
		//System.out.println((xp+12.5)+" "+(yp+12.5));
		//System.out.println("XCURR="+(xcurr)+" "+(ycurr));
//		GameState gs= (GameState)State.getState();
//		int xcurr= (int)((gs.getPlayer().xp)+Terrain.width/2);
//		int ycurr= (int)((gs.getPlayer().yp)+Terrain.height/2);
//		int xc= (int)((xcurr)/Terrain.width);
//		int yc=(int)((ycurr)/Terrain.height);
//		int xcord= xcurr-(xc*Terrain.width);
//		int ycord= ycurr-(yc*Terrain.height);
		//System.out.println(xcurr+" "+ycurr);
//		Terrain t =new Terrain();
//		t.init(ycord,xcord);
//		int xcurr=(int)(game.getGameCamera().getxOffset() +(game.getWidth() / 2));
//		int ycurr=(int)(game.getGameCamera().getyOffset() +(game.getHeight() / 2));
//		int xc= (int)((xcurr)/Chunk.chunkWIDTH);
//		int yc=(int)((ycurr)/Chunk.chunkHEIGHT);
//		int xcord= xcurr-(xc*Chunk.chunkWIDTH)-4;
//		int ycord= ycurr-(yc*Chunk.chunkHEIGHT)-4;
//		if(xcord<0)
//		{
//			xcord=Terrain.width+xcord;
//		}
//		if(ycord<0)
//		{
//			ycord=Terrain.height+ycord;
//		}
//		System.out.println(ycurr+" "+xcurr);
//		System.out.println(ycord+" "+xcord);
//		System.out.println(yc+" "+xc);
//		System.out.println(Tile.tiles[gs.getWorld().t[4].biome[xcord][ycord]].getId());
//		GameState gs= (GameState)State.getState();
//		if(Tile.tiles[gs.getWorld().t[gs.getWorld().loaded.indexOf(xc+" "+yc)].biome[ycord][xcord]].isSwim())
//		{
//			if (yMove < 0) {
//				return animSLeft.getCurrentFrame();
//			} else if (yMove > 0) {
//				return animSRight.getCurrentFrame();
//			} else if (xMove < 0) {
//				return animSUp.getCurrentFrame();
//			} else {
//				return animSDown.getCurrentFrame();
//			}
//		}
//		else {
			if (yMove < 0) {
				return animLeft.getCurrentFrame();
			} else if (yMove > 0) {
				return animRight.getCurrentFrame();
			} else if (xMove < 0) {
				return animUp.getCurrentFrame();
			} else {
				return animDown.getCurrentFrame();
			}
		//}
	}

}
