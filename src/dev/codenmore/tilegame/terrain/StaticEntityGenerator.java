package dev.codenmore.tilegame.terrain;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

/*TODO:CONVERT OPEN NOISE TO ARRAYLIST*/
public class StaticEntityGenerator {
    //change values below for more/less trees.. higher value for less tress and lower for more
    private static final int desert=8,desertHills=9,badlands=7,badlandsPlateau=7,badlandsHills=8,taiga=3,savanna=3,savannaPlateau=4,forest=2,plain=4,jungle=1,jungleHills=2,rainforest=2,swamp=3,swampHills=3,mountains=5,snowyTundra=5,snowyMountain=5;
    double bluenoise[][]=new double[Terrain.width][Terrain.height];
    int opennoise[][]=new int[Terrain.width][Terrain.height];
    static SecureRandom random;
    static OpenSimplex2F n=new OpenSimplex2F(/*random.nextInt(10000)*/0);
    public ArrayList<ArrayList<Integer>> entityList= new ArrayList<ArrayList<Integer>>();

//    public StaticEntityGenerator(int seed)
//    {
//
//    }
    public void sumOctaves(int numberOfIterations,int x,int y,double persistence,double scale,int k,int j,int low,int high)
    {
        double maxAmp=0;
        double amp=10;
        double frequency=scale;
        double noise=0;
        for(int i=0 ; i<numberOfIterations ; i++)
        {
            noise+=n.noise2(x*frequency,y*frequency)*amp;
            maxAmp+=amp;
            amp*=persistence;
            frequency*=2;
        }
        noise/=maxAmp;
        noise = noise * (high - low) / 2 + (high + low) / 2;
        bluenoise[k][j]=noise;
    }
    public void generate(int k,int j,int R,int width,int height,int[][] biome,int x,int y)
    {

        double max = 0;
        for(int i=k-R ; i<=k+R ; i++)
        {
            for(int l=j-R ; l<=j+R ; l++) {
                if (0 <= l && l < height && 0 <= i && i < width) {
                    double e = bluenoise[l][i];
                    if (e > max) {
                        max = e;
                    }
                }
            }
        }
        if (bluenoise[j][k] == max) {
            ArrayList<Integer> list= new ArrayList<Integer>();
            list.add((y)+j);
            list.add((x)+k);
            list.add(idEntity(biome[j][k]));
            entityList.add(list);
            opennoise[j][k]=0;
        }
        else {
            opennoise[j][k] = 255;
        }
        return;
    }
    public int getR(int x,int y,int[][] biome) {
        switch (biome[x][y]) {
            case 0: return -1;
            case 1: return -1;
            case 2: return -1;
            case 3: return -1;
            case 4: return -1;
            case 5: return -1;
            case 6: return desert;
            case 7: return desertHills;
            case 8: return badlands;
            case 9: return badlandsPlateau;
            case 10: return badlandsHills;
            case 11: return taiga;
            case 12: return taiga;
            case 13: return taiga;
            case 14: return taiga;
            case 15: return taiga;
            case 16: return taiga;
            case 17: return savanna;
            case 18: return savannaPlateau;
            case 19: return jungle;
            case 20: return jungleHills;
            case 21: return swamp;
            case 22: return swampHills;
            case 23: return plain;
            case 24: return forest;
            case 25: return rainforest;
            case 26: return rainforest;
            case 27: return rainforest;
            case 28: return mountains;
            case 29: return snowyTundra;
            case 30: return snowyMountain;
            case 31: return -1;
        }
        return -1;
    }
    public ArrayList<ArrayList<Integer>>  init(int width,int height,String name,double sc,int low,int high,int seed,int x,int y,int[][] biome)
    {
        //random = new SecureRandom();
        double scale=0.1;
        for(int i=0 ; i<width ; i++)
        {
            for(int j=0 ; j<height ; j++) {
                sumOctaves(16,i+x,j+y,0.5,scale,i,j,low,high);
            }
        }
        //greyWriteImage(bluenoise,"Blue");
        for(int i=0 ; i<width ; i++)
        {
            for(int j=0 ; j<height ; j++) {
                int n=getR(j,i,biome);
                if(n==-1) {
                    opennoise[j][i]=255;
                    continue;
                }
                else
                generate(i,j,n,width,height,biome,x,y);
            }
        }
        //greyWriteImage1(opennoise,"Entity");
        return entityList;
    }
    public void main(String[] args)
    {
    }
    public int idEntity(int n)
    {
        switch(n)
        {
            case 0:
                break; //deepWater
            case 1:
                break; //shallowWater
            case 2:
                break; //frozenDeepWater
            case 3:
                break; //frozenShallowWater
            case 4:
                break; //breach
            case 5:
                break; //snowyBreach
            case 6:
                return makeDesert();
            case 7:
                return makeDesertHills();
            case 8:
                return makeBadlands();
            case 9:
                return makeBadlandsPlateau();
            case 10:
                return makeBadlandsHills();
            case 11:
                return makeTaiga();
            case 12:
                return makeTaigaHills();
            case 13:
                return makeTaigaMountains();
            case 14:
                return makeSnowyTaiga();
            case 15:
                return makeSnowyTaigaHills();
            case 16:
                return makeSnowyTaigaMountains();
            case 17:
                return makeSavanna();
            case 18:
                return makeSavannaPlateau();
            case 19:
                return makeJungle();
            case 20:
                return makeJungleHills();
            case 21:
                return makeSwamp();
            case 22:
                return makeSwampHills();
            case 23:
                return makePlain();
            case 24:
                return makeForest();
            /*case 25:
                return makeRainforest();
            case 26:
                return makeRainforestHills();
            case 27:
                return makeRainforestMountains();*/
            case 28:
                return makeMountains();
            case 29:
                return makeSnowyTundra();
            case 30:
                return makeSnowyMountains();
            case 31:
                break; //iceSpikes
        }
        return 0;
    }
    private int makeSnowyMountains()
    {
        double n=Math.random();
        if(n>.75)
            return 7; //snowTrees
        else
            return 2; //grass
    }
    private int makeSnowyTundra()
    {
            return 7;
    }
    private int makeMountains()
    {
            return 1; //trees
    }
    private int makePlain()
    {
        double n=Math.random();
        if(n>.5)
            return 1; //trees
        else
            return 2; //grass
    }
    private int makeForest()
    {
        double n=Math.random();
        if(n>.05)
            return 1; //trees
        else
            return 2; //grass
    }
    private int makeDesert()
    {
        double n=Math.random();
        if(n>.25)
            return 4; //cactus
        else
            return 5; //dead bush
    }
    private int makeDesertHills()
    {
        double n=Math.random();
        if(n>.25)
            return 4; //cactus
        else
            return 5; //dead bush
    }
    private int makeBadlands()
    {
        double n=Math.random();
        if(n>.25)
            return 4; //cactus
        else
            return 5; //deadbush
    }
    private int makeBadlandsHills()
    {
        double n=Math.random();
        if(n>.25)
            return 4; //cactus
        else
            return 5; //deadbush
    }
    private int makeBadlandsPlateau()
    {
        double n=Math.random();
        if(n>.25)
            return 4; //cactus
        else
            return 5; //deadbush
    }
    private int makeTaiga()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeTaigaHills()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeTaigaMountains()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeSnowyTaiga()
    {
        double n=Math.random();
        if(n<.25)
            return 7; //snowTrees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeSnowyTaigaHills()
    {
        double n=Math.random();
        if(n<.25)
            return 7; //snowTrees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeSnowyTaigaMountains()
    {
        double n=Math.random();
        if(n<.25)
            return 7; //snowTrees
        else if(n>=.25 && n<.5)
            return 3; //ferns
        else
            return 2; //grass
    }
    private int makeSavanna()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else
            return 2; //grass
    }
    private int makeSavannaPlateau()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else
            return 2; //grass
    }
    private  int makeJungle()
    {
        return 1; //trees
    }
    private  int makeJungleHills()
    {
        return 1; //trees
    }
    private  int makeSwamp()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else
            return 2; //grass
    }
    private int makeSwampHills()
    {
        double n=Math.random();
        if(n<.25)
            return 1; //trees
        else
            return 2; //grass
    }


    public void greyWriteImage(double[][] data,String name){
        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < data[0].length; y++)
        {
            for (int x = 0; x < data.length; x++)
            {
                int n= (int)(data[x][y]);
                Color col=new Color(n,n,n);
                image.setRGB(x, y, col.getRGB());
            }
        }
        try {
            ImageIO.write(image, "jpg", new File("D:\\Map\\Output\\"+name+".jpg"));
        } catch (IOException e) {
            //o no! Blank catches are bad
            throw new RuntimeException("I didn't handle this very well");
        }
    }
    public void greyWriteImage1(double[][] data,String name){
        BufferedImage image = new BufferedImage(data.length,data[0].length, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < data[0].length; y++)
        {
            for (int x = 0; x < data.length; x++)
            {
                int n= (int)(data[x][y]);
                Color col;
                if(n==0)
                    col=new Color(255,n,n);
                else
                    col=new Color(n,n,n);
                image.setRGB(x, y, col.getRGB());
            }
        }
        try {
            ImageIO.write(image, "jpg", new File("D:\\Map\\Output\\"+name+".jpg"));
        } catch (IOException e) {
            //o no! Blank catches are bad
            throw new RuntimeException("I didn't handle this very well");
        }
    }
}
