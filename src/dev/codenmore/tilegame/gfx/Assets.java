package dev.codenmore.tilegame.gfx;

import dev.codenmore.tilegame.tiles.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static BufferedImage player,cactus,tree,flower,grass,deadbush,deepWater,shallowWater,frozenDeepWater,frozenShallowWater,beach,
	snowyBeach,desert,desertHills,	badlands,badlandsPlateau,badlandsHills,taiga,taigaHills,taigaMountains,snowyTaiga,snowyTaigaHills,snowyTaigaMountains,
	savanna,savannaPlateau,jungle,jungleHills,swamp,swampHills,plain,forest,rainforest,rainforestHills,rainforestMountains,mountains,snowyTundra,snowyMountains,iceSpikes,snowTree;

	public static BufferedImage[] player_attack,player_left,player_right,player_sleft,player_sright,player_standing;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));

		player_attack=new BufferedImage[3];
		player_left=new BufferedImage[3];
		player_right=new BufferedImage[3];
		player_sleft=new BufferedImage[3];
		player_sright=new BufferedImage[3];
		player_standing=new BufferedImage[2]; /*TODO: MAKE UP AND DOWN ANIMATION CUURENTLY IN PLAYER CLASS THEYRE STANDING*/


		deepWater = sheet.crop(0, 0, width, height);
		shallowWater = sheet.crop(width, 0, width, height);
		frozenDeepWater = sheet.crop(0, height, width, height);
		frozenShallowWater = sheet.crop(width, height, width, height);
		beach = sheet.crop(0, height*2, width, height);

		snowyBeach = sheet.crop(width, height*2, width, height);
		desert = sheet.crop(0, height*3, width, height);
		desertHills  = sheet.crop(width, height*3, width, height);
		badlands = sheet.crop(0, height*4, width, height);
		badlandsPlateau = sheet.crop(width, height*4, width, height);

		badlandsHills = sheet.crop(0, height*5, width, height);
		taiga = sheet.crop(width, height*5, width, height);
		taigaHills  = sheet.crop(0, height*6, width, height);
		taigaMountains  = sheet.crop(width, height*6, width, height);
		snowyTaiga = sheet.crop(0, height*7, width, height);

		snowyTaigaHills = sheet.crop(width, height*7, width, height);
		snowyTaigaMountains = sheet.crop(0, height*8, width, height);
		savanna = sheet.crop(width, height*8, width, height);
		savannaPlateau  = sheet.crop(0, height*9, width, height);
		jungle  = sheet.crop(width, height*9, width, height);

		jungleHills = sheet.crop(0, height*10, width, height);
		swamp  = sheet.crop(width, height*10, width, height);
		swampHills = sheet.crop(0, height*11, width, height);
		plain = sheet.crop(width, height*11, width, height);
		forest = sheet.crop(0, height*12, width, height);

		rainforest = sheet.crop(width, height*12, width, height);
		rainforestHills  = sheet.crop(0, height*13, width, height);
		rainforestMountains = sheet.crop(width, height*13, width, height);
		mountains = sheet.crop(0, height*14, width, height);
		snowyTundra = sheet.crop(width, height*14, width, height);

		snowyMountains = sheet.crop(0, height*15, width, height);
		iceSpikes  = sheet.crop(width, height*15, width, height);

		player= sheet.crop(0,height*16,16,16);

		cactus= sheet.crop(16,height*16,64,64);
		deadbush = sheet.crop(0, height * 17, 64, 64);
		flower = sheet.crop(64, height * 17, 64, 64);
		grass = sheet.crop(0, height * 18, 64, 64);
		tree = sheet.crop(64, height * 18, 16, 16);

		player_attack[0] = sheet.crop(80, height * 18, 16, 16);
		player_attack[1] = sheet.crop(96, height * 18, 16, 16);
		player_attack[2] = sheet.crop(112, height * 18, 16, 16);

		player_left[0] = sheet.crop(0, height * 19, 16, 16);
		player_left[1] = sheet.crop(16, height * 19, 16, 16);
		player_left[2] = sheet.crop(32, height * 19, 16, 16);

		player_right[0] = sheet.crop(48, height * 19, 16, 16);
		player_right[1] = sheet.crop(64, height * 19, 16, 16);
		player_right[2] = sheet.crop(80, height * 19, 16, 16);

		player_standing[0] = sheet.crop(96, height * 19, 16, 16);
		player_standing[1] = sheet.crop(112, height * 19, 16, 16);

		player_sleft[0] = sheet.crop(0, (height * 19)+16, 16, 16);
		player_sleft[1] = sheet.crop(16, (height * 19)+16, 16, 16);
		player_sleft[2] = sheet.crop(32, (height * 19)+16, 16, 16);

		player_sright[0] = sheet.crop(48, (height * 19)+16, 16, 16);
		player_sright[1] = sheet.crop(64, (height * 19)+16, 16, 16);
		player_sright[2] = sheet.crop(80, (height * 19)+16, 16, 16);

		snowTree = sheet.crop(96, (height * 19)+16, 16, 16);



	}
	
}
