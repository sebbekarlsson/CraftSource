package nu.sebka.craftsource;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class ImageViewer extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	int  camx,camy = 0;
	public static float px,py = 0;
	
	JFrame frame = new JFrame();
	
	Image img;

	
	public ImageViewer(BufferedImage img){
		this.img = 	img.getScaledInstance(img.getWidth()*6, img.getHeight()*6, 1);
		this.camx = img.getWidth()/2;
		this.camy = img.getHeight()/2;
		

		frame.add(this);
		frame.setSize(img.getWidth()*6,img.getHeight()*6);
		frame.setVisible(true);
		frame.addKeyListener(this);
		//this.setResizable(false);
	}
	
	public void paint(Graphics g){
		
		camx = (int) (px - img.getWidth(null)/2);
		camy = (int) (py - img.getHeight(null)/2);
		
		g.clearRect(0, 0, img.getWidth(null), img.getHeight(null));
		//g.translate(-camx, -camy);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.red);
		g.fillOval((int)px, (int)py, 16, 16);
		
		//g.translate(camx, camy);
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
