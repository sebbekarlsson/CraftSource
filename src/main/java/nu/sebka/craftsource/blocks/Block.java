package nu.sebka.craftsource.blocks;

import java.awt.Color;
import java.util.Random;

import nu.sebka.craftsource.CraftSource;
import nu.sebka.craftsource.World;
import nu.sebka.craftsource.core.Entity;
import nu.sebka.craftsource.core.ModelBank;
import nu.sebka.craftsource.core.models.BlockModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class Block extends Entity {

	private static float size = 0.04f;
	private BlockType blockType;
	Random random = new Random();
	boolean draw = true;
	//BlockModel model;

	float xrot = 0;

	public Block(BlockType type, World world, float x, float y, float z) {
		super(world, x, y, z);
		blockType = type;
		//model = new BlockModel();

	}



	public void tick() {

		if(blockType == BlockType.GRASS){
			if(CraftSource.getCurrentWorld().getBlockAtPrecise(x, y-Block.getSize(), z).isSolid()){
				blockType=BlockType.DIRT;
			}
		}
		else if(blockType == BlockType.DIRT){
			if(random.nextInt(1000) == 0){
				if(!(CraftSource.getCurrentWorld().getBlockAtPrecise(x, y-Block.getSize(), z).isSolid())&&
				CraftSource.getCurrentWorld().getBlockAtPrecise(x-Block.getSize(), y, z).getType() == BlockType.GRASS ||
				CraftSource.getCurrentWorld().getBlockAtPrecise(x+Block.getSize(), y, z).getType() == BlockType.GRASS ||
				CraftSource.getCurrentWorld().getBlockAtPrecise(x, y, z-Block.getSize()).getType() == BlockType.GRASS ||
				CraftSource.getCurrentWorld().getBlockAtPrecise(x, y, z+Block.getSize()).getType() == BlockType.GRASS 
						
				){
					blockType=BlockType.GRASS;
				}
			}
		}





	}

	public void draw() {
		GL11.glColor3f(1, 1, 1);
		draw = true;

		
		World w = CraftSource.getCurrentWorld();

		if(
				w.getBlockAtPrecise(x+(Block.getSize()), y, z).isSolid() &&
				w.getBlockAtPrecise(x-(Block.getSize()), y, z).isSolid() &&
				w.getBlockAtPrecise(x, y+(Block.getSize()), z).isSolid() &&
				w.getBlockAtPrecise(x, y-(Block.getSize()), z).isSolid() &&
				w.getBlockAtPrecise(x, y, z+(Block.getSize())).isSolid() &&
				w.getBlockAtPrecise(x, y, z-(Block.getSize())).isSolid()


				){
			draw = false;
		}







		if(draw){




			/*
			 * SHADOWING
			 * for(int i = 0; i < 32*2; i ++){
			if(!(CraftSource.getCurrentWorld().getBlockAtPrecise(x, y-Block.getSize()-Block.getSize()*i, z).getType().equals(BlockType.AIR))){
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
				break;
			}
		}*/
			Block facingBlock = CraftSource.getCurrentWorld().cam.getFacingBlock();
			if(this.equals(facingBlock)){
				GL11.glColor3f(1, 0, 1);
			}




			GL11.glPushMatrix();



			ModelBank.cube(x, y, z, Block.getSize()/2, blockType);

			GL11.glPopMatrix();

		}



	}

	public static float getSize() {
		return size * 2;
	}
	public BlockType getType(){
		return blockType;
	}

	public boolean isSolid(){

		if(this.blockType == BlockType.AIR){
			return false;
		}

		return true;
	}



}
