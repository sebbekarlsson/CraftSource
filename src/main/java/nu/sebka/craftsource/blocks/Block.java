package nu.sebka.craftsource.blocks;

import java.awt.Color;
import java.util.Random;

import nu.sebka.craftsource.CraftSource;
import nu.sebka.craftsource.World;
import nu.sebka.craftsource.core.Entity;
import nu.sebka.craftsource.core.ModelBank;
import nu.sebka.craftsource.core.models.BlockModel;

import org.lwjgl.opengl.GL11;


public class Block extends Entity {

	private static float size = 0.04f;
	private BlockType blockType;
	Random random = new Random();
	//BlockModel model;



	public Block(BlockType type, World world, float x, float y, float z) {
		super(world, x, y, z);
		blockType = type;
		//model = new BlockModel();

	}



	public void tick() {
		




		
	}

	public void draw() {
		
		GL11.glColor3f(1, 1, 1);


		for(int i = 0; i < 32*2; i ++){
			if(!(CraftSource.getCurrentWorld().getBlockAtPrecise(x, y-Block.getSize()-Block.getSize()*i, z).getType().equals(BlockType.AIR))){
				GL11.glColor3f(0.5f, 0.5f, 0.5f);
				break;
			}
		}
		Block facingBlock = CraftSource.getCurrentWorld().cam.getFacingBlock();
		if(this.equals(facingBlock)){
			GL11.glColor3f(1, 0, 1);
		}
		ModelBank.cube(x, y, z, Block.getSize()/2, blockType);

		
	}

	public static float getSize() {
		return size * 2;
	}
	public BlockType getType(){
		return blockType;
	}



}
