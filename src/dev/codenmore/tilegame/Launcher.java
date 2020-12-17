package dev.codenmore.tilegame;


public class Launcher {
	public static int width=800;
	public static int height=800;
	public static void main(String[] args){
		Launcher l=new Launcher();
		Game game = new Game("Tile Game!",l.width,l.height);
		game.start();
	}

	
}
