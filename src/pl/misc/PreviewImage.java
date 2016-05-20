package pl.misc;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;
import java.util.Observer;

import javax.swing.JPanel;

import pl.workspace.model.ImageModel;

public class PreviewImage extends JPanel{
	
	private ImageModel image_model;
	//private BufferedImage temporary;
	private final ImageModel original_model;
	
	public PreviewImage(ImageModel model){
		original_model = model.duplicate();
		image_model = model.duplicate();
		//BufferedImage image = image_model.getImage();
		//temporary = new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
		setMinimumSize(new Dimension(300, 300));
		setMaximumSize(new Dimension(300, 300));
		setPreferredSize(new Dimension(300, 300));
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		gr.drawImage(image_model.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}
	
	public void restoreImageModel(Observer obs){
		image_model = original_model.duplicate();
		image_model.addObserver(obs);
	}

}
