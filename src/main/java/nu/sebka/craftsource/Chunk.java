package nu.sebka.craftsource;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import nu.sebka.craftsource.blocks.Block;
import nu.sebka.craftsource.blocks.BlockType;
import nu.sebka.craftsource.core.Entity;

public class Chunk {


	public ArrayList<Entity> locations = new ArrayList<Entity>();
	Camera cam;
	float rdist = World.rdist;
	public BufferedImage map;
	public float x,z = 0;
	public World world;
	Random random = new Random();
	public boolean loaded = false;
	public boolean hasBeenLoadedBefore = false;


	public Chunk(World world,float x,float z, BufferedImage map, BufferedImage biome){
		this.map = map;
		this.x = x; this.z = z;







	}

	public void tick(){
		cam = CraftSource.getCurrentWorld().cam;

		for (int i = 0; i < locations.size(); i++) {
			Block l = (Block)locations.get(i);


			((Block) l).tick();



		}
	}

	public void draw(){

		for (int i = 0; i < locations.size(); i++) {
			Block l = (Block)locations.get(i);


			((Block) l).draw();



		}
	}

	public void unLoad(){

		locations.clear();
		loaded = false;

	}

	public void load(){
		unLoad();
		String file = "chunks/"+x+"cord"+z+".txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine())!=null){
				String blockname = line.split(",")[0];
				BlockType typ = BlockType.valueOf(blockname);
				float x = Float.parseFloat(line.split(",")[1]);
				float y = Float.parseFloat(line.split(",")[2]);
				float z = Float.parseFloat(line.split(",")[3]);

				Block block = new Block(typ,world,x,y,z);
				locations.add(block);
			}

			reader.close();
			loaded = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveData(){
		try {
			File dir = new File("chunks");
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file = new File("chunks/"+x+"cord"+z+".txt");
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			for(int i = 0; i < locations.size(); i++){
				Block loc = (Block) locations.get(i);



				writer.write(loc.getType()+","+loc.getX()+","+loc.getY()+","+loc.getZ()+"\n");

			}
			writer.close();
			System.out.println("Chunk data saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void initObjects(){

		if(map != null){
			for(int i = 0; i < map.getWidth(); i++){
				for(int ii = 0; ii < map.getHeight(); ii++){
					float y = 0;
					int rgb = map.getRGB(i, ii);
					Color color = new Color(rgb);

					int r = color.getRed();
					int g = color.getGreen();
					int b = color.getBlue();
					
					y = ((g))*Block.getSize();

					BlockType t = BlockType.GRASS;

					Block block = new Block(t,world,x+(i * Block.getSize()), y, z+(ii * Block.getSize()));


					for(float by = block.getY()+Block.getSize(); by < 20.4; by+=Block.getSize()){
						if(world == null){System.out.println("HELLO");}
						if(world.getBlockAtPrecise(block.getX(),by,block.getZ()).getType() == BlockType.AIR){
							BlockType type = BlockType.DIRT;
							if(by > Block.getSize()*5){
								type = BlockType.STONE;
							}
							Block bl = new Block(type,world,block.getX(),by,block.getZ());

							locations.add(bl);
						}
					}

					if(random.nextInt(64) == 0){
						int hei = random.nextInt(2)+4;
						int h = 0;
						for(h = 0; h < hei; h++){
							if(!world.getBlockAt(block.getX(), block.getY()-(Block.getSize()*h)-Block.getSize(), block.getZ()).isSolid())
								locations.add(new Block(BlockType.LOG,world,block.getX(),block.getY()-(Block.getSize()*h)-Block.getSize(),block.getZ()));

						}



						locations.add(new Block(BlockType.LEAF,world,block.getX(),block.getY()-(Block.getSize()*h)-Block.getSize(),block.getZ()));

						locations.add(new Block(BlockType.LEAF,world,block.getX()-Block.getSize(),block.getY()-Block.getSize()*h,block.getZ()));
						locations.add(new Block(BlockType.LEAF,world,block.getX()+Block.getSize(),block.getY()-Block.getSize()*h,block.getZ()));
						locations.add(new Block(BlockType.LEAF,world,block.getX(),block.getY()-Block.getSize()*h,block.getZ()-Block.getSize()));
						locations.add(new Block(BlockType.LEAF,world,block.getX(),block.getY()-Block.getSize()*h,block.getZ()+Block.getSize()));

					}



					locations.add(block);

				}
			}
			hasBeenLoadedBefore = true;
			saveData();
		}
	}

	public boolean hasBeenLoadedBefore(){
		return hasBeenLoadedBefore;
	}
}
