package dev.codenmore.tilegame.terrain;
import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Launcher;
import dev.codenmore.tilegame.entities.creatures.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import dev.codenmore.tilegame.terrain.Biome;
import dev.codenmore.tilegame.tiles.Tile;

public class Terrain implements Runnable{
    static Launcher l= new Launcher();
    public static int width=(int)Math.sqrt((l.width*l.width)/(Tile.TILEWIDTH*Tile.TILEWIDTH))+4,height=(int)Math.sqrt((l.height*l.height)/(Tile.TILEHEIGHT*Tile.TILEHEIGHT))+4;
    //public static int width=29,height=29;
    public int x,y;
    //making below static sends over the data to init otherwise 0 filled array
    public static double[][] islandArr= new double[width][height],cloudArr,humidity = new double[width][height],temperature = new double[width][height],riverArr = new double[width][height];
    public int[][] biome=new int[width][height];
    public ArrayList<ArrayList<Integer>> entityList= new ArrayList<ArrayList<Integer>>();
    public void run(){
        if(Thread.currentThread().getName().equals("ISLAND")) {
            islandArr = MakeIsland.init(width, height, "Island Noise", 0.0008, x, y); // make island noise profile
            //System.out.println("ISLAND GENERATED");
            //BufferedImage island = Island.island(islandArr); // give colors to island
            //ImageIO.write(island, "jpg", new File("D:\\Map\\Output\\island.jpg"));
        }
        else if(Thread.currentThread().getName().equals("HUMIDITY")) {

            humidity = MakeHumidity.init(width, height, "Humidity Noise", 0.0001, 0, 100, 5000, x, y); // make humidity noise profile
            //System.out.println("HUMIDITY GENERATED");
        }
        else if(Thread.currentThread().getName().equals("TEMPERATURE")) {

            temperature = MakeTemperature.init(width, height, "Moisture Noise", 0.0001, 0, 100, 1, x, y); // make temperature noise profile
            //System.out.println("TEMPERATURE GENERATED");
        }
        else if(Thread.currentThread().getName().equals("RIVER")) {
            riverArr = RiverGeneration.init(width, height, "River Noise", 0.002, 0, x, y);
            //System.out.println("RIVER GENERATED");
            //BufferedImage river = River.river(riverArr);
            //ImageIO.write(river, "jpg", new File("D:\\Map\\Output\\River.jpg"));
        }
        /*else if(Thread.currentThread().getName().equals("CLOUD")) {
            //System.out.println("CLOUD GENERATED");
            //double[][] cloudArr=new double[width][height];
            //cloudArr=makeNoise.init(width,height,"Cloud Noise",0.007,0,255); // make cloud noise profile
            //BufferedImage cloud=Weather.weather(cloudArr);
            //ImageIO.write(cloud, "jpg", new File("D:\\Map\\Output\\cloud.jpg"));
        }*/
    }


    public void terrain(){
        try {
            //Uncomment below code for use without threads
            /*System.out.println("ISLAND GENERATED");
            islandArr=new double[width][height];
            islandArr=MakeIsland.init(width,height,"Island Noise",0.0008,x,y); // make island noise profile
            //BufferedImage island = Island.island(islandArr); // give colors to island
            //ImageIO.write(island, "jpg", new File("D:\\Map\\Output\\island.jpg"));

            //System.out.println("CLOUD GENERATED");
            //double[][] cloudArr=new double[width][height];
            //cloudArr=makeNoise.init(width,height,"Cloud Noise",0.007,0,255); // make cloud noise profile
            //BufferedImage cloud=Weather.weather(cloudArr);
            //ImageIO.write(cloud, "jpg", new File("D:\\Map\\Output\\cloud.jpg"));

            System.out.println("HUMIDITY GENERATED");
            humidity=new double[width][height];
            humidity=MakeHumidity.init(width,height,"Humidity Noise",0.0001,0,100,10,x,y); // make humidity noise profile

            System.out.println("TEMPERATURE GENERATED");
            temperature=new double[width][height];
            temperature=MakeTemperature.init(width,height,"Moisture Noise",0.0001,0,100,1,x,y); // make temperature noise profile

            System.out.println("RIVER GENERATED");
            riverArr=new double[width][height];
            riverArr=RiverGeneration.init(width,height,"River Noise",0.002,0,x,y);
            BufferedImage river=River.river(riverArr);
            //ImageIO.write(river, "jpg", new File("D:\\Map\\Output\\River.jpg"));*/

            //biome
            //Terrain t=new Terrain();
            Biome b=new Biome();
            //t.biome=b.generateBiome(t.islandArr,t.temperature,t.humidity,t.riverArr);
            biome=b.generateBiome(islandArr,temperature,humidity,riverArr);
            //System.out.println("BIOME GENERATED");
            StaticEntityGenerator c=new StaticEntityGenerator();
            entityList=c.init(width, height, "Tree Noise", 50, 0, 100, 0, x, y,biome);
            //System.out.println("ENTITY GENERATED");
            /*for(int i = 0; i< c.entityList.size() ; i++)
            {
                int x= c.entityList.get(i).get(0);
                int y= c.entityList.get(i).get(1);
                Color newColor1 = new Color(0,0,0);
                b.image.setRGB(x,y,newColor1.getRGB());
            }*/

            //ImageIO.write(b.image, "jpg", new File("D:\\Map\\Output\\Chunk\\"+y+" "+x+".jpg"));
            /*//BufferedImage moistureArr = ImageIO.read(new File("D:\\Map\\Output\\moisture.jpg"));
            BufferedImage imagePixelated = Pixel.pixelate(img, 1);

            findPath() // TO FIND PATH FROM A,B to C,D USING A STAR ALGORITHM

            combine(cloud,imagePixelated); //combine clouds and island*/
        }
        catch(Exception e){
            System.out.println(e);
        }
        //System.out.println("END");
    }

    public void init (int xx,int yy) {
        /*MyCanvas m=new MyCanvas();
                JFrame f=new JFrame();
                f.add(m);
                //f.setSize(1400,700);
                f.setPreferredSize(new Dimension(800, 800));
                f.pack();
                f.setVisible(false);
                Pixel obj = new Pixel();*/
        //Scanner in= new Scanner(System.in);
        //System.out.println("Enter Initial Coordinates");
        x=xx;
        y=yy;
        /*x=Integer.parseInt(args[0]);
        y=Integer.parseInt(args[1]);*/
        //System.out.println("Enter dimensions"); //Enter Dimension for image
        /*width=in.nextInt();
        height=in.nextInt();*/
        /*width=200;
        height=200;*/
        /*Thread t1= new Thread(new Terrain());
        t1.setName("ISLAND");
        t1.start();
        Thread t2= new Thread(new Terrain());
        t2.setName("HUMIDITY");
        t2.start();
        Thread t3= new Thread(new Terrain());
        t3.setName("TEMPERATURE");
        t3.start();
        Thread t4= new Thread(new Terrain());
        t4.setName("RIVER");
        t4.start();*/
        Running r1=new Running(x,y,islandArr);
        Thread t1= new Thread(r1);
        t1.setName("ISLAND");
        t1.start();
        Running r2=new Running(x,y,humidity);
        Thread t2= new Thread(r2);
        t2.setName("HUMIDITY");
        t2.start();
        Running r3=new Running(x,y,temperature);
        Thread t3= new Thread(r3);
        t3.setName("TEMPERATURE");
        t3.start();
        Running r4=new Running(x,y,riverArr);
        Thread t4= new Thread(r4);
        t4.setName("RIVER");
        t4.start();
        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive());
        islandArr=r1.getArr();
        humidity=r2.getArr();
        temperature=r3.getArr();
        riverArr=r4.getArr();
        terrain();

    }
    public static void main (String args[]) {
        Scanner in= new Scanner(System.in);
        System.out.println("Enter Initial Coordinates");
        int x=in.nextInt();
        int y=in.nextInt();
        /*width=in.nextInt();
        height=in.nextInt();*/
        /*width=200;
        height=200;*/
        Running r1=new Running(x,y,islandArr);
        Thread t1= new Thread(r1);
        t1.setName("ISLAND");
        t1.start();
        Running r2=new Running(x,y,humidity);
        Thread t2= new Thread(r2);
        t2.setName("HUMIDITY");
        t2.start();
        Running r3=new Running(x,y,temperature);
        Thread t3= new Thread(r3);
        t3.setName("TEMPERATURE");
        t3.start();
        Running r4=new Running(x,y,riverArr);
        Thread t4= new Thread(r4);
        t4.setName("RIVER");
        t4.start();
        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive());
        islandArr=r1.getArr();
        humidity=r2.getArr();
        temperature=r3.getArr();
        riverArr=r4.getArr();
        Terrain t =new Terrain();
        t.terrain();

    }
    /*public void combine(BufferedImage weather, BufferedImage island) throws IOException {
        int c = 0;
        for (int i = 0; i < island.getHeight(); i++) {
            BufferedImage ic = Terrain.deepCopy(island);
            for (int k = 0; k < island.getHeight(); k++) {
                for (int j = 0; j < island.getWidth(); j++) {
                    Color c2 = new Color(weather.getRGB(j, k));
                    int R2 = c2.getRed();
                    int G2 = c2.getRed();
                    int B2 = c2.getRed();
                    if (R2 == 0 && G2 == 0 && B2 == 0) {
                        continue;
                    } else {
                        Color newColor1 = new Color(R2, G2, B2);
                        ic.setRGB(j, k, newColor1.getRGB());
                    }
                }
            }
            Graphics g1 = ic.getGraphics();
            g1.drawImage(ic, 0, 0, 800, 800, null);
            try {
                ImageIO.write(ic, "jpg", new File("D:\\Map\\combined+" + c++ + "+.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g1.dispose();
            weather = MoveCloud.rotate(weather);
        }
    }*/
    /*BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }*/
}  