import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Flower implements Runnable{
	private String type;
	private int waterLevel;
	private int maturity;
	private Point p;
	private int width;
	private int height;

	public Flower(String type, int x, int y){
		this.type = type;
		waterLevel = 50;
		maturity = 0;
		p = new Point(x,y);
		width = 20;
		height = 20;
	}
	@Override
	public void run() {
		try{
			while(waterLevel > 0){
				Thread.sleep(1000);
				waterLevel--;
			}
		}
		catch(Exception e){
			
		}
		finally{
			System.out.println("Your " + type + " died!");
		}
	}
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)	&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));
	}
	
	public void water(){
		waterLevel = 100;
	}
	
	public String getType(){
		return type;
	}
	
	public void draw(Graphics g) {	//very ugly flower at the moment!
		g.setColor(Color.blue);
		g.fillRect(p.x, p.y, width, height);
		g.setColor(Color.black);
		g.drawRect(p.x, p.y, width, height);
	}
	
}
