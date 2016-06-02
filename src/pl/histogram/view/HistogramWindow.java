package pl.histogram.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.workspace.model.ImageModel;

//klasa do wyswietlania histogramu
public class HistogramWindow extends JDialog implements Observer{
	
	private ImageModel image_model;
	private HistogramChart chart;
	private JTextField level, amount, fullsize;
	
	public HistogramWindow(JFrame parent, ImageModel img_model)
	{
		super(parent);
		image_model = img_model;
		image_model.addObserver(this);
		//JPanel panel = new JPanel();
		//panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		chart = new HistogramChart(image_model);
		//chart.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		//add(chart);
		//panel.add(chart);
		//add(panel);
		
		JPanel cp = (JPanel)getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.add(chart);
		level = new JTextField();
		amount = new JTextField();
		fullsize = new JTextField();
		fullsize.setPreferredSize(new Dimension(50, 20));
		fullsize.setMaximumSize(new Dimension(50, 20));
		level.setPreferredSize(new Dimension(50, 20));
		level.setMaximumSize(new Dimension(50, 20));
		amount.setPreferredSize(new Dimension(50, 20));
		amount.setMaximumSize(new Dimension(50, 20));
		level.setEnabled(false);
		amount.setEnabled(false);
		fullsize.setEnabled(false);
		fullsize.setText("" + (image_model.getImage().getWidth()*image_model.getImage().getHeight()));
		cp.add(level);
		cp.add(amount);
		cp.add(fullsize);
		cp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		setTitle("Histogram " + image_model.getName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(parent.getContentPane().getLocationOnScreen());
		
		chart.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int lev = e.getX()*256/chart.getWidth();
				level.setText("" + lev);
				amount.setText("" + (int)chart.getHist()[lev]);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
		//setResizable(false);
		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		setTitle("Histogram " + image_model.getName());
		fullsize.setText("" + (image_model.getImage().getWidth()*image_model.getImage().getHeight()));
		repaint();
	}
	
	

}
