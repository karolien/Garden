import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Flower implements Runnable{
	protected String type;
	protected int waterLevel;
	protected int maturity;
	protected Point p;
	protected boolean alive = true;

	public Flower(int x, int y){
		waterLevel = 50;
		maturity = 0;
		p = new Point(x,y);
	}
	@Override
	public abstract void run();
	public abstract boolean contains(Point mousePt);
	
	public void water(){
		waterLevel += 50;
		if(waterLevel > 150){
			alive = false;
		}
	}
	public boolean isAlive(){
		return alive;
	}
	
	public String getType(){
		return type;
	}
	
	public abstract void draw(Graphics g);
	
}
