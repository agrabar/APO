package pl.histogram.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import pl.workspace.model.ImageModel;

public class HistogramWindow_old_version extends JFrame implements Observer{	//zmien na JDialog
	
	private ImageModel image_model;
	private HistogramChart chart;
	
	public HistogramWindow_old_version(ImageModel img_model)
	{
		image_model = img_model;
		image_model.addObserver(this);
		chart = new HistogramChart(image_model);
		getContentPane().add(chart);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	

}
