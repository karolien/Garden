import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class Rose extends Plant implements Runnable{
	private int width;
	private int height;
	private JTextArea output;


	public Rose(int x, int y, JTextArea outputArea){
		super(x,y);
		width = 20;
		height = 30;
		type = "rose seedling";
		output = outputArea;
	}
	@Override
	public void run() {
		try{
			while(waterLevel > 0 && alive){
				Thread.sleep(200);
				waterLevel--;
			}
		}
		catch(Exception e){
			
		}
		finally{
			output.append("Your " + type + " died!\n");
			alive = false;
		}
	}
	public boolean contains(Point mousePt) {
		return (p.x <= mousePt.x && mousePt.x <= (p.x + width + 1)	&&	p.y <= mousePt.y && mousePt.y <= (p.y + height + 1));
	}
	
	public void water(){
		waterLevel += 50;
		if(waterLevel > 150){
			alive = false;
		}
		careLevel++;
		if(careLevel==10){
			maturity++;
			width = 40;
			height = 60;
			output.append("Your " + type + " matured!\n");
			type = "rose";
		}
	}
	
	public void draw(Graphics g) {
		BufferedImage rose = null;
		switch(maturity){
			case 0:
				try {
					rose = ImageIO.read(GUI.class.getResource("seedling.png"));
				} catch (IOException e) {}
				g.drawImage(rose, p.x, p.y, width, height, null);
				break;
			case 1:
				try {
					rose = ImageIO.read(GUI.class.getResource("rose.png"));
				} catch (IOException e) {}
				g.drawImage(rose, p.x, p.y, width, height, null);
				break;
		}
		g.drawString(Integer.toString(waterLevel), p.x, p.y);
	}
	
}
 
