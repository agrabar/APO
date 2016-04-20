package pl.workspace.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import pl.workspace.model.ImageModel;

public class ImageWindow extends JInternalFrame implements Observer{
	
	private ImageModel image_model;
	private JLabel image_label;
	
	public ImageWindow(ImageModel img_model){
		image_model = img_model;
		image_model.addObserver(this);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		add(Box.createGlue());		
		image_label = new JLabel();
		image_label.setIcon(new ImageIcon(image_model.getImage()));
		JScrollPane jsp = new JScrollPane(image_label);
		add(jsp);
		add(Box.createGlue());
		setTitle(image_model.getName());
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pack();
		setVisible(true);
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}

	@Override
	public void update(Observable o, Object arg) {
		setTitle(image_model.getName());
		image_label.repaint();		
	}
	
}
