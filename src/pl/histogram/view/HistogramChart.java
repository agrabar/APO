package pl.histogram.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import pl.workspace.model.ImageModel;

//panel na ktorym rysuje histogram
public class HistogramChart extends JPanel{
	
	private ImageModel image_model;
	private double[] histogram;
	
	public HistogramChart(ImageModel image_model){
		this.image_model = image_model;
		setPreferredSize(new Dimension(768, 500));
		setBackground(Color.LIGHT_GRAY);
		//setBackground(Color.WHITE);
	}
	
	public double[] getHist(){
		return histogram;
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		//double[] histogram = image_model.getHistogram();
		histogram = image_model.getHistogram();
		double max = 1;
		for(double el : histogram){
			if(max < el) max = el;
			//System.out.println(""+el);
		}
		for(int i = 0; i < histogram.length; ++i){
			/*gr.setColor(Color.BLACK);
			gr.fillRect(0 + i*3, getHeight() - (int)((getHeight()*histogram[i])/max), 3, (int)((getHeight()*histogram[i])/max));
			gr.setColor(Color.GRAY);
			gr.drawRect(0 + i*3, getHeight() - (int)((getHeight()*histogram[i])/max), 3, (int)((getHeight()*histogram[i])/max));*/
			
			gr.setColor(Color.BLACK);
			gr.fillRect(0 + i*getWidth()/256, getHeight() - (int)((getHeight()*histogram[i])/max), getWidth()/256, (int)((getHeight()*histogram[i])/max));
			gr.setColor(Color.GRAY);
			gr.drawRect(0 + i*getWidth()/256, getHeight() - (int)((getHeight()*histogram[i])/max), getWidth()/256, (int)((getHeight()*histogram[i])/max));
		}
	}
	
	public void setImageModel(ImageModel image_model){
		this.image_model = image_model;
	}
	
}
