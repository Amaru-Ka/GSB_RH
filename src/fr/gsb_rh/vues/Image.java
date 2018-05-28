package fr.gsb_rh.vues;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public  Image() {
		this.setPreferredSize(new Dimension(80,170));
	}
	public void paint (Graphics g){
		try{
			java.awt.Image monImage = ImageIO.read(new File("img/gsbLogo.jpg"));
			g.drawImage(monImage, 0, 0, this.getWidth(),this.getHeight(), this);
		}
		catch (IOException e){
			e.printStackTrace();
		} 
	}
}
