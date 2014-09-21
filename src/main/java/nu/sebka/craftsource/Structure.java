package nu.sebka.craftsource;

public abstract class Structure {

	public World world;
	public float x,y,z;
	
	public Structure(World world, float x, float y, float z){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public abstract void load();
	
}
