package pl.histogram.view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pl.workspace.model.ImageModel;

//klasa do wyswietlania histogramu
public class HistogramWindow extends JDialog implements Observer{
	
	private ImageModel image_model;
	private HistogramChart chart;
	
	public HistogramWindow(JFrame parent, ImageModel img_model)
	{
		super(parent);
		image_model = img_model;
		image_model.addObserver(this);
		//JPanel panel = new JPanel();
		//panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		chart = new HistogramChart(image_model);
		//chart.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		add(chart);
		//panel.add(chart);
		//add(panel);
		pack();
		JPanel cp = (JPanel)getContentPane();
		cp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		setTitle("Histogram " + image_model.getName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(parent.getContentPane().getLocationOnScreen());
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		setTitle("Histogram " + image_model.getName());
		repaint();
	}
	
	

}
