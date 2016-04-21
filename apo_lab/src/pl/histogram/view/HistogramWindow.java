package pl.histogram.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JFrame;

import pl.workspace.model.ImageModel;

public class HistogramWindow extends JDialog implements Observer{
	
	private ImageModel image_model;
	private HistogramChart chart;
	
	public HistogramWindow(JFrame parent, ImageModel img_model)
	{
		super(parent);
		image_model = img_model;
		image_model.addObserver(this);
		chart = new HistogramChart(image_model);
		add(chart);
		pack();
		setTitle("Histogram " + image_model.getName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	

}
