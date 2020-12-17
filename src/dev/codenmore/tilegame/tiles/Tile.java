package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.entities.creatures.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[36];
	public static Tile deepWater = new deepWater(0);
	public static Tile shallowWater = new shallowWater(1);
	public static Tile frozenDeepWater = new frozenDeepWater(2);
	public static Tile frozenShallowWater = new frozenShallowWater(3);

	public static Tile beach = new beach(4);
	public static Tile snowyBeach = new snowyBeach(5);

	public static Tile desert = new desert(6);
	public static Tile desertHills = new desertHills(7);

	public static Tile badlands = new badlands(8);
	public static Tile badlandsPlateau = new badlandsPlateau(9);
	public static Tile badlandsHills = new badlandsHills(10);

	public static Tile taiga = new taiga(11);
	public static Tile taigaHills = new taigaHills(12);
	public static Tile taigaMountains = new taigaMountains(13);
	public static Tile snowyTaiga = new snowyTaiga(14);
	public static Tile snowyTaigaHills = new snowyTaigaHills(15);
	public static Tile snowyTaigaMountains = new snowyTaigaMountains(16);

	public static Tile savanna = new savanna(17);
	public static Tile savannaPlateau = new savannaPlateau(18);

	public static Tile jungle = new jungle(19);
	public static Tile jungleHills = new jungleHills(20);

	public static Tile swamp = new swamp(21);
	public static Tile swampHills = new swampHills(22);

	public static Tile plain = new plain(23);

	public static Tile forest = new forest(24);

	public static Tile rainforest = new rainforest(25);
	public static Tile rainforestHills = new rainforestHills(26);
	public static Tile rainforestMountains = new rainforestMountains(27);

	public static Tile mountains = new mountains(28);

	public static Tile snowyTundra = new snowyTundra(29);
	public static Tile snowyMountains = new snowyMountains(30);
	public static Tile iceSpikes = new iceSpikes(31);
	public static Tile tree = new Tree(32);
	public static Tile cactus = new cactus(33);
	public static Tile deadbush = new deadbush(34);
	public static Tile grass = new grass(33);
	public static Tile flower = new flower(34);
	public static Tile snowTree = new snowTree(35);
	//CLASS
	
	public static final int TILEWIDTH = 8, TILEHEIGHT = 8;

	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	public void render(Graphics g, int x, int y,int width,int height){
		g.drawImage(texture, x, y, width, height, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	public boolean isSwim(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
