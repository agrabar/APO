package pl.lab3.filtracja_medianowa;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import pl.histogram.view.HistogramChart;
import pl.misc.PreviewImage;
import pl.workspace.model.ImageModel;

public class MedianWindow extends JDialog implements Observer{
	
	private JSpinner mask_width, mask_height;
	private ImageModel image_model;
	private PreviewImage preview;
	private HistogramChart histogram;
	private JButton button_confirm, button_cancel;
	
	public MedianWindow(JFrame parent, ImageModel model){
		super(parent, "Filtracja medianowa");
		image_model = model;
		
		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		
		JPanel panel_top = new JPanel();
		JPanel panel_bot = new JPanel();
		JPanel panel_buttons = new JPanel();
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.X_AXIS));
		panel_bot.setLayout(new BoxLayout(panel_bot, BoxLayout.X_AXIS));
		panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.X_AXIS));
		
		mask_width = new JSpinner(new SpinnerNumberModel(3, 3, 9, 2));
		mask_height = new JSpinner(new SpinnerNumberModel(3, 3, 9, 2));
		
		panel_top.add(Box.createGlue());
		panel_top.add(mask_width);
		panel_top.add(Box.createRigidArea(new Dimension(30,10)));
		panel_top.add(mask_height);
		panel_top.add(Box.createGlue());
		
		preview = new PreviewImage(model);
		histogram = new HistogramChart(preview.getImageModel());
		histogram.setMaximumSize(new Dimension(300, 300));
		histogram.setMinimumSize(new Dimension(300, 300));
		histogram.setPreferredSize(new Dimension(300, 300));
		
		panel_bot.add(Box.createRigidArea(new Dimension(5, 0)));
		panel_bot.add(preview);
		panel_bot.add(Box.createGlue());
		panel_bot.add(histogram);
		panel_bot.add(Box.createRigidArea(new Dimension(5, 0)));
		
		button_confirm = new JButton("OK");
		button_confirm.setPreferredSize(new Dimension(100, 25));
		button_cancel = new JButton("Cancel");
		button_cancel.setPreferredSize(new Dimension(100, 25));
		panel_buttons.add(Box.createRigidArea(new Dimension(20,0)));
		panel_buttons.add(button_confirm);
		panel_buttons.add(Box.createRigidArea(new Dimension(20,0)));
		panel_buttons.add(button_cancel);
		panel_buttons.add(Box.createGlue());
		
		cp.add(panel_top);
		cp.add(panel_bot);
		cp.add(panel_buttons);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void addButtonConfirmListener(ActionListener listener){
		button_confirm.addActionListener(listener);
	}
	
	public void addButtonCancelListener(ActionListener listener){
		button_cancel.addActionListener(listener);
	}
	
	public void addChangeListeners(ChangeListener listener){
		mask_width.addChangeListener(listener);
		mask_height.addChangeListener(listener);
	}
	
	public void updateHistogramChart(){
		histogram.setImageModel(preview.getImageModel());
	}

	public JSpinner getMask_width() {
		return mask_width;
	}

	public JSpinner getMask_height() {
		return mask_height;
	}

	public PreviewImage getPreview() {
		return preview;
	}

	public ImageModel getImage_model() {
		return image_model;
	}

	public HistogramChart getHistogram() {
		return histogram;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();		
	}

}
