package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.terrain.Biome;
import dev.codenmore.tilegame.terrain.StaticEntityGenerator;
import dev.codenmore.tilegame.terrain.Terrain;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;

import javax.imageio.metadata.IIOMetadataNode;

public class World{

	private Game game;
	private int spawnX, spawnY;
	public ArrayList<String> loaded= new ArrayList<String>();
	public ArrayList<ArrayList<Integer>> entityList= new ArrayList<ArrayList<Integer>>();
	public Terrain t[] = new Terrain[9];
	public World(Game game){
		this.game = game;
		//loadWorld();
		init();
	}
	void init()
	{
		ArrayList<String> load = new ArrayList<String>();
		Scanner in= new Scanner(System.in);
		try {
			String s = Files.readString(Paths.get("D:\\Map\\Output\\Chunk\\loaded.txt"));
			spawnX=Integer.parseInt(s.split(" ")[0])*Chunk.chunkWIDTH;
			spawnY=Integer.parseInt(s.split(" ")[1])*Chunk.chunkHEIGHT;
		}
		catch(Exception e) {
			//Spawning point
			spawnX = 0;
			spawnY = 0;
		}
		int x=spawnX;
		int y=spawnY;
		x=x/Chunk.chunkWIDTH;
		y=y/Chunk.chunkHEIGHT;
		//Set initial chunk
		Player.chunkXPrev=x;
		Player.chunkYPrev=y;
		//Set camera offset
		game.getGameCamera().setxOffset(spawnX);
		game.getGameCamera().setyOffset(spawnY);
		//Load chunk names
			load.add((x - 1) +" "+ (y - 1));
			load.add(x +" "+ (y - 1));
			load.add((x + 1) +" "+ (y - 1));
			load.add((x - 1) +" "+ y);
			load.add(x +" "+ y);
			load.add((x + 1) +" "+ y);
			load.add((x - 1) +" "+ (y + 1));
			load.add(x +" "+ (y + 1));
			load.add((x + 1) +" "+ (y + 1));
		loaded.clear();
		loaded.addAll(load);
		entityList.clear();
		System.out.println(loaded);
		//Load chunks and entities
		for(int i=0 ; i<load.size() ; i++)
		{
			String[] s=load.get(i).split(" ");
			int xpos=Integer.parseInt(s[0]);
			int ypos=Integer.parseInt(s[1]);
			t[i]=new Terrain();
			t[i].init(xpos*Terrain.width,ypos*Terrain.height);
			entityList.addAll(t[i].entityList);
		}
	}
	public void loadChunk(int x,int y)
	{
		ArrayList<String> load = new ArrayList<String>();
		ArrayList<String> fload = new ArrayList<String>();
		//Load chunk names
		if(!loaded.contains((x - 1) +" "+ (y - 1)))
			load.add((x - 1) +" "+ (y - 1));
		fload.add((x - 1) +" "+ (y - 1));
		if(!loaded.contains(x +" "+ (y - 1)))
			load.add(x +" "+ (y - 1));
		fload.add(x +" "+ (y - 1));
		if(!loaded.contains((x + 1) +" "+ (y - 1)))
			load.add((x + 1) +" "+ (y - 1));
		fload.add((x + 1) +" "+ (y - 1));
		if(!loaded.contains((x - 1) +" "+ y))
			load.add((x - 1) +" "+ y);
		fload.add((x - 1) +" "+ y);
		if(!loaded.contains(x +" "+ y))
			load.add(x +" "+ y);
		fload.add(x +" "+ y);
		if(!loaded.contains((x + 1) +" "+ y))
			load.add((x + 1) +" "+ y);
		fload.add((x + 1) +" "+ y);
		if(!loaded.contains((x - 1) +" "+ (y + 1)))
			load.add((x - 1) +" "+ (y + 1));
		fload.add((x - 1) +" "+ (y + 1));
		if(!loaded.contains(x +" "+ (y + 1)))
			load.add(x +" "+ (y + 1));
		fload.add(x +" "+ (y + 1));
		if(!loaded.contains((x + 1) +" "+ (y + 1)))
			load.add((x + 1) +" "+ (y + 1));
		fload.add((x + 1) +" "+ (y + 1));
		loaded.clear();
		loaded.addAll(fload);
		System.out.println(loaded);
		entityList.clear();
		//Load chunks and entities
		for(int i=0 ; i<loaded.size() ; i++) //changed from load to loaded
		{
			String[] s=loaded.get(i).split(" ");
			int xpos=Integer.parseInt(s[0]);
			int ypos=Integer.parseInt(s[1]);
			t[i]=new Terrain();
			t[i].init(xpos*Terrain.width,ypos*Terrain.height);
			entityList.addAll(t[i].entityList);
		}
	}
	public World(Game game, int n){
		this.game = game;
	}
	public void tick(){
		chunkChange();
	}
	//TODO: CHUNK CHANGE FUNCTION SAHI KRO
	public void chunkChange()
	{
		if((int)((game.getGameCamera().getxOffset() +(game.getWidth() / 2))/Chunk.chunkWIDTH)==Player.chunkXPrev && (int)((game.getGameCamera().getyOffset() +(game.getHeight() / 2))/ Chunk.chunkHEIGHT)==Player.chunkYPrev)
			return;
		else {
			System.out.println("Chunk Changed!");
			Player.chunkXPrev=(int)((game.getGameCamera().getxOffset() +(game.getWidth() / 2))/Chunk.chunkWIDTH);
			Player.chunkYPrev=(int)((game.getGameCamera().getyOffset() +(game.getHeight() / 2))/ Chunk.chunkHEIGHT);
			try {
				Files.writeString(Paths.get("D:\\Map\\Output\\Chunk\\loaded.txt"), Player.chunkXPrev+" "+Player.chunkYPrev);
			} catch (Exception e) {
			}
			loadChunk(Player.chunkXPrev,Player.chunkYPrev);
		}
	}
//	public void chunkChange()
//	{
//		if((int)((game.getGameCamera().getxOffset() +(game.getWidth() / 2))/Chunk.chunkWIDTH)==Player.chunkXPrev && (int)((game.getGameCamera().getyOffset() +(game.getHeight() / 2))/ Chunk.chunkHEIGHT)==Player.chunkYPrev)
//			return;
//		else {
//			System.out.println("Chunk Changed!");
//			Player.chunkXPrev=(int)((game.getGameCamera().getxOffset() +(game.getWidth() / 2))/Chunk.chunkWIDTH);
//			Player.chunkYPrev=(int)((game.getGameCamera().getyOffset() +(game.getHeight() / 2))/ Chunk.chunkHEIGHT);
//			//System.out.println("PREV"+((game.getGameCamera().getxOffset() +(game.getWidth() / 2))/Chunk.chunkWIDTH)+" "+((game.getGameCamera().getyOffset() +(game.getHeight() / 2))/ Chunk.chunkHEIGHT));
//			loadChunk(Player.chunkXPrev,Player.chunkYPrev);
//		}
//	}
//	public void chunkChange()
//	{
//		GameState gs= (GameState)State.getState();
//		int xcurr= (int)((gs.getPlayer().xp)/Terrain.width);
//		int ycurr=(int)((gs.getPlayer().yp)/Terrain.height);
//		//System.out.println(gs.getPlayer().xp+" "+gs.getPlayer().yp);
//		if((xcurr)==Player.chunkXPrev && (ycurr)==Player.chunkYPrev)
//			return;
//		else {
//			System.out.println("Chunk Changed!");
//			Player.chunkXPrev=xcurr;
//			Player.chunkYPrev=ycurr;
//			loadChunk(Player.chunkYPrev,Player.chunkXPrev);
//		}
//	}
	public void render(Graphics g) {
		int xStart = (int) game.getGameCamera().getxOffset() / Tile.TILEWIDTH;
		int xEnd = (int)(game.getGameCamera().getxOffset() + game.getWidth()*2) / Tile.TILEWIDTH + 1;
		int yStart = (int)game.getGameCamera().getyOffset() / Tile.TILEHEIGHT;
		int yEnd = (int)(game.getGameCamera().getyOffset() + game.getHeight()*2) / Tile.TILEHEIGHT + 1;
		//System.out.println(xStart+ " "+ xEnd+ " "+ yStart+ " "+yEnd);
		for (int l = 0; l < 9; l++) {
			String[] s = loaded.get(l).split(" ");
			int x = Integer.parseInt(s[0])*Terrain.width;
			int y = Integer.parseInt(s[1])*Terrain.height;
				for (int k = 0; k < Terrain.width; k++) {
					for (int j = 0; j < Terrain.height; j++) {
//						GameState gs= (GameState)State.getState();
//						int xcurr= (int)((game.getGameCamera().getxOffset() +(game.getWidth() / 2)));
//						int ycurr= (int)((game.getGameCamera().getyOffset() +(game.getHeight() / 2)));
//						int xc= (int)((xcurr)/Terrain.width);
//						int yc=(int)((ycurr)/Terrain.height);
//						int xcord= xcurr-(xc*Terrain.width)+15;
//						int ycord=ycurr-(yc*Terrain.height)+15;
//						if(xcord<0)
//						{
//							xcord=Terrain.width+xcord;
//						}
//						if(ycord<0)
//						{
//							ycord=Terrain.height+ycord;
//						}
//						//System.out.println(xcord+ " " + ycord);
//						if(k==ycord%Terrain.height && j==xcord%Terrain.width)
//						{
//							Tile.cactus.render(g, (int) ((j + y) * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() /*- (Tile.TILEHEIGHT * Terrain.height) / 2*/),
//									(int) ((k + x) * Tile.TILEWIDTH - game.getGameCamera().getxOffset() /*- (Tile.TILEWIDTH * Terrain.width) / 2*/));
//						}
//						else {
							Tile.tiles[t[l].biome[j][k]].render(g, (int) ((j + y) * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) ((k + x) * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2));
						//}
					}
				}

			for (int i = 0; i < entityList.size(); i++) {
				int xx = entityList.get(i).get(0);
				int yy = entityList.get(i).get(1);
				if (yy >= xStart && yy <= xEnd && xx >= yStart && xx <= yEnd) {
					int n = entityList.get(i).get(2);
					switch (n) {
						case 1:
							Tile.tree.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2-Tile.TILEWIDTH*3),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2-Tile.TILEHEIGHT*3), Tile.TILEWIDTH*4, Tile.TILEHEIGHT*4);
							break;
						case 2:
							Tile.grass.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2), Tile.TILEWIDTH, Tile.TILEHEIGHT); //currently does cactus but should GRASS
							break;
						case 3:
							Tile.tree.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2), Tile.TILEWIDTH, Tile.TILEHEIGHT); //currently does flower but should FERn
							break;
						case 4:
							Tile.cactus.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2), Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
							break;
						case 5:
							Tile.deadbush.render(g, (int) (xx* Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2), Tile.TILEWIDTH, Tile.TILEHEIGHT);
							break;
						case 6:
							Tile.flower.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2),
									(int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2), Tile.TILEWIDTH, Tile.TILEHEIGHT);
							break;
                        case 7:
                            Tile.snowTree.render(g, (int) (xx * Tile.TILEHEIGHT - game.getGameCamera().getyOffset() - (Tile.TILEHEIGHT * Terrain.height) / 2)-Tile.TILEWIDTH*3,
                                    (int) (yy * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - (Tile.TILEWIDTH * Terrain.width) / 2)-Tile.TILEHEIGHT*3, Tile.TILEWIDTH*4, Tile.TILEHEIGHT*4);
                            break;
//						case 1:
//							Tile.tree.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 128, 128);
//							break;
//						case 2:
//							Tile.grass.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32); //currently does cactus but should GRASS
//							break;
//						case 3:
//							Tile.flower.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32); //currently does flower but should FERn
//							break;
//						case 4:
//							Tile.cactus.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 64, 64);
//							break;
//						case 5:
//							Tile.deadbush.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32);
//							break;
//						case 6:
//							Tile.flower.render(g, (int) (xx * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
//									(int) (yy * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32);
//							break;
					}
				}
			}
		}
	}
	/*public void render(Graphics g) {
        int xStart = (int) Math.max(0, game.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (game.getGameCamera().getxOffset() + game.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, game.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (game.getGameCamera().getyOffset() + game.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()));
            }
        }
        for (int i = 0; i < StaticEntityGenerator.entityList.size(); i++)
        {	int x=StaticEntityGenerator.entityList.get(i).get(0);
			int y=StaticEntityGenerator.entityList.get(i).get(1);
			if(x>=xStart && x<=xEnd && y>=yStart && y<=yEnd) {
				int n = StaticEntityGenerator.entityList.get(i).get(2);
				switch (n) {
					case 1:
						Tile.tree.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 128, 128);
						break;
					case 2:
						Tile.grass.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32); //currently does cactus but should GRASS
						break;
					case 3:
						Tile.flower.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32); //currently does flower but should FERn
						break;
					case 4:
						Tile.cactus.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 64, 64);
						break;
					case 5:
						Tile.deadbush.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32);
						break;
					case 6:
						Tile.flower.render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getxOffset() - 32),
								(int) (y * Tile.TILEHEIGHT - game.getGameCamera().getyOffset()), 32, 32);
						break;
				}
			}
        }
	}
	*/
	public Tile getTile(int x, int y){
		/*int xStart = (int) Math.max(0, game.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (game.getGameCamera().getxOffset() + game.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, game.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (game.getGameCamera().getyOffset() + game.getHeight()) / Tile.TILEHEIGHT + 1);
		Terrain tu= new Terrain();*/

		/*if(x<=xStart && y<=yStart)
		{

		}*/
		Terrain tr[]= new Terrain[9];
		for(int i=0 ; i<9 ; i++){
			Tile t = Tile.tiles[tr[i].biome[x][y]];
			if (t == null)
				return Tile.badlands;
			return t;
		}
		return Tile.badlands;
	}
	private void loadWorld(){
		/*String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		//Scanner in=new Scanner(System.in);
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);*/

		/*terrain.main(new String[]{""});
		//Terrain.main();
		width=terrain.width;
		height=terrain.height;*/
		//tiles = new int[width][height];

		/*Thread t1= new Thread(new World(game,1));
		t1.setName("1");
		t1.start();
		Thread t2= new Thread(new World(game,2));
		t2.setName("2");
		t2.start();
		Thread t3= new Thread(new World(game,3));
		t3.setName("3");
		t3.start();
		Thread t4= new Thread(new World(game,4));
		t4.setName("4");
		t4.start();
		Thread t5= new Thread(new World(game,5));
		t5.setName("5");
		t5.start();
		Thread t6= new Thread(new World(game,6));
		t6.setName("6");
		t6.start();
		Thread t7= new Thread(new World(game,7));
		t7.setName("7");
		t7.start();
		Thread t8= new Thread(new World(game,8));
		t8.setName("8");
		t8.start();
		Thread t9= new Thread(new World(game,9));
		t9.setName("9");
		t9.start();
		while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() || t5.isAlive() || t6.isAlive() || t7.isAlive() || t8.isAlive() || t9.isAlive());*/
		/*for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				//tiles[x][y] = in.nextInt();
			}
		}*/
	}
	public int getSpawnX()
	{
		return spawnX;
	}
	public int getSpawnY()
	{
		return spawnY;
	}
}








