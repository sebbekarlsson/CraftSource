package  nu.sebka.craftsource;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import nu.sebka.craftsource.blocks.Block;
import nu.sebka.craftsource.blocks.BlockType;
import nu.sebka.craftsource.core.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class World {

	public ArrayList<Entity> locations = new ArrayList<Entity>();
	public Chunk[][] chunks;
	public Camera cam = new Camera(80, (float) CraftSource.WIDTH / (float) CraftSource.HEIGHT, 0.03f, 1000);
	float xrot = 0;
	public BufferedImage map = new BufferedImage(16*10,16*10,BufferedImage.TYPE_INT_RGB);
	public BufferedImage biome = new BufferedImage(map.getWidth(),map.getHeight(),BufferedImage.TYPE_INT_RGB);
	Random random = new Random();
	static float rdist = 16;


	public World() {
		cam.setY(20.325558f - Block.getSize()*20);
		//20.325558f - Block.getSize()*5

		chunks = new Chunk[map.getWidth()/16][map.getHeight()/16];



		for(int i = 0; i < map.getWidth(); i++){
			for(int ii = 0; ii < map.getHeight(); ii++){
				int current = map.getRGB(i, ii);
				Color currentcolor = new Color(current);







				Color green = new Color(0,255,0);

				if(currentcolor.getGreen() < 5)
					map.setRGB(i, ii, green.getRGB());



				if(random.nextInt(48) == 0){
					

					int d = random.nextInt(3)+1;
					if(random.nextInt(32) == 0){
						d += random.nextInt(6);
					}


					if(random.nextInt(3) == 0){
						green = new Color(0,255-d,0);
						map.setRGB(i, ii, green.getRGB());
					}

					boolean large = false;
					if(random.nextInt(4) == 0){
						large = random.nextBoolean();
						if(large){
							d += random.nextInt(5);
						}
					}
					
					green = new Color(0,255-d,0);




					double PI = 3.1415926535;
					double angle, x1, y1;


					for(int rr = 0; rr < d; rr++){
						if(255-d < 255)
							if(large == true){
								if(random.nextInt(5) == 0){
									green = new Color(0,(255-d)+rr,0);
								}
							}else{
								green = new Color(0,(255-d)+rr,0);
							}
						for(double j = 0; j < 360; j += 0.1)
						{

							angle = j;
							x1 = rr * Math.cos(angle * PI / 180);
							y1 = rr * Math.sin(angle * PI / 180);
							if( i+(int)x1 >= 0 && ii+(int)y1 >= 0 && i+(int)x1 <=map.getWidth()-d && ii+(int)y1 <= map.getHeight()-d){
								map.setRGB(i+(int)x1, ii+(int)y1, green.getRGB());
							}
						}



					}
				}






				System.out.println("Drawing map...");
			}
		}


		/*Color color = Color.red;
		for(int i = 0; i < biome.getWidth(); i++){
			for(int ii = 0; ii < biome.getHeight(); ii++){
				if(random.nextInt(20) == 0){
					color = Color.blue;
				}
				if(random.nextInt(100) == 0){
					color = Color.red;
				}
				biome.setRGB(i, ii, color.getRGB());
			}

		}*/

		new ImageViewer(map);

		for(int i = 0; i < map.getWidth()/16; i++){
			for(int ii = 0; ii < map.getHeight()/16; ii++){



				BufferedImage image;
				image = map.getSubimage(i*16, ii*16, 16, 16);
				Chunk chunk = new Chunk(this,(i*16)*Block.getSize(),(ii*16)*Block.getSize(),image,biome);

				chunks[i][ii] = chunk;
				System.out.println("ADDED A CHUNK");


			}
		}


		JOptionPane.showMessageDialog(null, "Done");

		//LOADWORLDHERE


	}

	public void tick() {
		cam.tick();


		for(int i = 0; i < map.getWidth()/16; i++){
			for(int ii = 0; ii < map.getHeight()/16; ii++){
				Chunk chunk = chunks[i][ii];
				if(chunk != null)
					if(cam.getX() >= chunk.x-(rdist/4*Block.getSize()) && cam.getX() <= chunk.x+Block.getSize()*rdist+(rdist/4*Block.getSize()) && cam.getZ() >= chunk.z-(rdist/4*Block.getSize()) && cam.getZ() <= chunk.z+Block.getSize()*rdist+(rdist/4*Block.getSize())){
						if(chunk.loaded == false){
							if(chunk.hasBeenLoadedBefore() == false){
								chunk.world = this;
								chunk.initObjects();
							}
							chunk.load();
						}
					}else{
						if(chunk.loaded){
							chunk.saveData();
							chunk.unLoad();
						}
					}
				if(chunk.loaded){
					chunk.tick();
				}
			}
		}


	}

	public void draw() {


		//GL11.glLoadIdentity();

		cam.useView();


		GL11.glPushMatrix();
		int chunkblocks = 0;
		for(int i = 0; i < map.getWidth()/16; i++){
			for(int ii = 0; ii < map.getHeight()/16; ii++){
				Chunk chunk = chunks[i][ii];
				chunkblocks += chunk.locations.size();
				if(chunk != null){

					if(chunk.loaded){
						chunk.draw();
					}

				}
			}
		}
		Display.setTitle("Minecraft "+"Loaded blocks:"+chunkblocks);
		GL11.glPopMatrix();


	}

	public Chunk getCurrentChunk(){
		for(int i = 0; i < map.getWidth()/16; i++){
			for(int ii = 0; ii < map.getHeight()/16; ii++){
				Chunk chunk = chunks[i][ii];
				if(cam.getX() >= chunk.x && cam.getX() <= chunk.x+Block.getSize()*16 && cam.getZ() >= chunk.z && cam.getZ() <= chunk.z+Block.getSize()*rdist){
					return chunk;
				}
			}
		}

		return null;
	}

	public Chunk getChunkAt(float x, float z){
		for(int i = 0; i < map.getWidth()/16; i++){
			for(int ii = 0; ii < map.getHeight()/16; ii++){
				Chunk chunk = chunks[i][ii];
				if(chunk != null){
					if(x >= chunk.x && x <= chunk.x+Block.getSize()*16 && z >= chunk.z && z <= chunk.z+Block.getSize()*16){
						return chunk;
					}
				}
			}
		}

		return null;
	}

	public Block getBlockAtPrecise(double x, double y, double z) {
		Chunk chunk = getChunkAt((float)x,(float)z);
		if(chunk != null){
			for (int i = 0; i < chunk.locations.size(); i++) {
				Entity location = chunk.locations.get(i);
				if (
						x == location.getX() && 
						y == location.getY() && 
						z == location.getZ() 

						) {
					return (Block) location;
				}
			}
		}

		return new Block(BlockType.AIR,this,(float) x, (float) y, (float) z);
	}


	public void destroyBlock(Block block){
		if(block != null){
			getChunkAt(block.getX(),block.getZ()).locations.remove(block);
		}
	}

	public void placeBlock(BlockType type,float x, float y, float z){
		Chunk chunk = getChunkAt(x,z);
		if(chunk != null)
			chunk.locations.add(new Block(type,this,x,y,z));
	}

	public Block getBlockAt(double x, double y, double z) {
		Chunk chunk = getChunkAt((float)x,(float)z);
		if(chunk != null){
			for (int i = 0; i < chunk.locations.size(); i++) {
				Entity location = chunk.locations.get(i);
				if (
						x > location.getX() - (Block.getSize() / 2)-0.035f && x < location.getX() + (Block.getSize()/2)+0.035f &&
						y > location.getY() - (Block.getSize() / 2)-0.035f && y < location.getY() + (Block.getSize()/2)+0.035f &&
						z > location.getZ() - (Block.getSize() / 2)-0.035f	&& z < location.getZ() + (Block.getSize()/2)+0.035f

						) {
					return (Block) location;
				}
			}
		}
		return new Block(BlockType.AIR,this,(float) x, (float) y, (float) z);
	}

}
