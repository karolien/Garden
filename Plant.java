import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Plant implements Runnable{
	protected String type;
	protected int waterLevel;
	protected int maturity;
	protected int careLevel;
	protected Point p;
	protected boolean alive = true;

	public Plant(int x, int y){
		waterLevel = 50;
		maturity = 0;
		careLevel = 0;
		p = new Point(x,y);
	}
	@Override
	public abstract void run();
	public abstract boolean contains(Point mousePt);
	public abstract void water();
	
	public boolean isAlive(){
		return alive;
	}
	
	public String getType(){
		return type;
	}
	
	public abstract void draw(Graphics g);
	
}
