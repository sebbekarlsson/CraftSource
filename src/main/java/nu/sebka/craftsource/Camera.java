package  nu.sebka.craftsource;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.Random;

import nu.sebka.craftsource.blocks.Block;
import nu.sebka.craftsource.blocks.BlockType;
import nu.sebka.craftsource.core.ModelBank;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;



public class Camera {
	private float x;
	private float y;
	private float z;
	private float rx;
	private float ry;
	private float rz;

	private float fov;
	private float aspect;
	private float near;
	private float far;
	BlockType currentBlockType = BlockType.PLANKS;

	public Camera(float fov, float aspect, float near, float far) {
		x = 0;
		y = 0;
		z = 0;
		rx = 0;
		ry = 0;
		rz = 0;

		this.fov = fov;
		this.aspect = aspect;
		this.near = near;
		this.far = far;
		initProjection();
		
	}

	private void initProjection() {


		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, aspect, near, far);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);


		//...
		// enable face culling


	}


	public void useView() {


		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


		glRotatef(rx, 1, 0, 0);
		glRotatef(ry, 0, 1, 0);
		glRotatef(rz, 0, 0, 1);
		glTranslatef(-x, -y, -z);

		getPlaceLocation();

	}


	public void tick() {
		boolean falling = false;
		boolean canmove = true;
		float fallspeed = 0.01f;



		if (CraftSource.getCurrentWorld().getBlockAt(getX(), getY() + Block.getSize() * 2, getZ()).getType().equals(BlockType.AIR)) {
			falling = true;
		}

		if (falling) {
			fallspeed += 0.00016f;
			setY(getY() + fallspeed);
		} else {
			fallspeed = 0.01f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			currentBlockType = BlockType.PLANKS;
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			currentBlockType = BlockType.LOG;
		}
		
		
		while(Mouse.next()){
			//Destroy the facing block when pressing left mouse button
			if(Mouse.getEventButtonState()){
				if(Mouse.isButtonDown(0)){
					CraftSource.getCurrentWorld().locations.remove(getFacingBlock());
				}
			}

			
			

			//Create a block where the player is looking
			if(Mouse.isButtonDown(1)){
				//1 get facing block
				//2 get the face of the block where the player is looking at
				//create block "ontop / relative" to that face.

				// * -- a test underneath -- * //



				Vector3f pos = getPlaceLocation();
				if(Mouse.getEventButtonState()){
					if(pos != null){
						if(CraftSource.getCurrentWorld().getBlockAtPrecise(pos.x, pos.y, pos.z).getType().equals(BlockType.AIR))
							CraftSource.getCurrentWorld().locations.add(new Block(currentBlockType,CraftSource.getCurrentWorld(),pos.x,pos.y,pos.z));
					}
				}
				//if (!block.getType().equals(BlockType.AIR) && aboveblock.getType().equals(BlockType.AIR)) {
				//CraftSource.getCurrentWorld().locations.add(new Block(BlockType.PLANKS, CraftSource.getCurrentWorld(), block.getX(), block.getY() - Block.getSize(), block.getZ()));
				//}



			}

		}

		//face culling (dont draw faces that is not visible to the eye)
		if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
			Random random = new Random();
			GL11.glCullFace(random.nextInt(5));
			GL11.glEnable(GL11.GL_CULL_FACE);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W) && canmove) {
			move(1, 0.01f);
			try {
				Mouse.setNativeCursor(null);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S) && canmove) {
			move(-1, 0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A) && canmove) {
			move(2, 0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D) && canmove) {
			move(0, 0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			setY(getY() - Block.getSize() / 2);

		}




		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			setRY(getRY() - 3);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			setRY(getRY() + 3);
		}


		setRY(getRY() - Mouse.getDX());
		setRX(getRX() - Mouse.getDY());

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getRX() {
		return rx;
	}

	public float getRY() {
		return ry;
	}

	public float getRZ() {
		return rz;
	}

	public void setRX(float rx) {
		this.rx = rx;
	}

	public void setRY(float ry) {
		this.ry = ry;
	}

	public void setRZ(float rz) {
		this.rz = rz;
	}

	public void move(float dir, float amt) {
		z += amt * Math.sin(Math.toRadians(ry + 90 * dir));
		x += amt * Math.cos(Math.toRadians(ry + 90 * dir));
	}

	//Get the block that the player is looking at
	public Block getFacingBlock() {
		Block block = null;

		for (int i = 0; i < 7; i++) {
			float xpos = (float)(x + i * 0.05f * Math.cos(Math.toRadians(ry + 90)));
			float ypos = (float)(y + i * 0.05f * Math.tan(Math.toRadians(rx)));
			float zpos = (float)(z + i * 0.05f * Math.sin(Math.toRadians(ry + 90)));
			block = CraftSource.getCurrentWorld().getBlockAt(xpos,ypos,zpos);



			if(!block.getType().equals(BlockType.AIR)){
				break;
			}
		}

		return block;
	}

	public Vector3f getFaceHit(){
		float xx = 0;
		float yy = 0;
		float zz = 0;
		for (int i = 0; i < 7; i++) {
			xx = (float) (x + i * 0.05f * Math.cos(Math.toRadians(ry + 90)));
			yy = (float) (y + i * 0.05f * Math.tan(Math.toRadians(rx)));
			zz = (float) (z + i * 0.05f * Math.sin(Math.toRadians(ry + 90)));
			Block block = CraftSource.getCurrentWorld().getBlockAt(xx, yy, zz);


			if (!block.getType().equals(BlockType.AIR)) {
				break;
			}


		}
		return new Vector3f(xx, yy, zz);
	}

	public Vector3f getPlaceLocation(){
		Vector3f hv = getFaceHit();
		float xpos = 0;
		float ypos = 0;
		float zpos = 0;
		for (int i = 0; i < 7; i++) {
			xpos = (float)(x + i * 0.05f * Math.cos(Math.toRadians(ry + 90)));
			ypos = (float)(y + i * 0.05f * Math.tan(Math.toRadians(rx)));
			zpos = (float)(z + i * 0.05f * Math.sin(Math.toRadians(ry + 90)));
			Block block = getFacingBlock();
			hv = getFaceHit();
			boolean xmin = false;
			boolean xmax = false;
			boolean ymin = false;
			boolean ymax = false;
			boolean zmin = false;
			boolean zmax = false;





			if(getFaceHit().y >= block.getY()+Block.getSize()/2 && getFaceHit().y <= block.getY() + Block.getSize()){
				hv = new Vector3f(block.getX(),block.getY()+Block.getSize(),block.getZ());
				ymax = true;
			}
			if(getFaceHit().y <= block.getY() && getFaceHit().y >= block.getY() - Block.getSize()){
				hv = new Vector3f(block.getX(),block.getY()-Block.getSize(),block.getZ());
				ymin = true;
			}


			if(getFaceHit().x <= block.getX() && getFaceHit().x >= block.getX() - Block.getSize() &&!(ymax) && !(ymin)){
				hv = new Vector3f(block.getX()-Block.getSize(),block.getY(),block.getZ());
				xmin = true;
			}
			if(getFaceHit().x >= block.getX() && getFaceHit().x <= block.getX() + Block.getSize()&&!(ymax) && !(ymin)){
				hv = new Vector3f(block.getX()+Block.getSize(),block.getY(),block.getZ());
				xmax = true;
			}

			if(getFaceHit().z >= block.getZ() && getFaceHit().z <= block.getZ() + Block.getSize() && !(xmin) &&!(ymax) && !(ymin) ){
				hv = new Vector3f(block.getX(),block.getY(),block.getZ()+Block.getSize());
				zmax = true;
			}
			if(getFaceHit().z <= block.getZ() && getFaceHit().z >= block.getZ() - Block.getSize()&& !(xmax)&&!(ymax) && !(ymin)){
				hv = new Vector3f(block.getX(),block.getY(),block.getZ()-Block.getSize());
				zmin = true;

			}


			GL11.glColor3f(0f,1f,0f);
			ModelBank.cube(hv.x,hv.y,hv.z,Block.getSize()/2,currentBlockType);
			return hv;

		}


		return null;

	}
}