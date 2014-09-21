package nu.sebka.craftsource.structures;

import java.util.Random;

import nu.sebka.craftsource.Structure;
import nu.sebka.craftsource.World;
import nu.sebka.craftsource.blocks.Block;
import nu.sebka.craftsource.blocks.BlockType;

public class Tree extends Structure {

	BlockType logtype;
	public int height = 4;
	Random random = new Random();
	
	public Tree(World world, BlockType logtype, float x, float y, float z) {
		super(world, x, y, z);
		this.logtype = logtype;
		height += random.nextInt(2);
	}
	
	@Override
	public void load() {
		for(float i = 0; i < height; i++){
			world.placeBlock(logtype, x, y-Block.getSize()-i*Block.getSize(), z);
		}
		
		world.placeBlock(BlockType.LEAF, x, y-Block.getSize()-height*Block.getSize(), z);
		
		world.placeBlock(BlockType.LEAF, x-Block.getSize(), y-Block.getSize()-height*Block.getSize()+Block.getSize(), z);
		world.placeBlock(BlockType.LEAF, x+Block.getSize(), y-Block.getSize()-height*Block.getSize()+Block.getSize(), z);
		world.placeBlock(BlockType.LEAF, x, y-Block.getSize()-height*Block.getSize()+Block.getSize(), z-Block.getSize());
		world.placeBlock(BlockType.LEAF, x, y-Block.getSize()-height*Block.getSize()+Block.getSize(), z+Block.getSize());
		
		
	}

}
