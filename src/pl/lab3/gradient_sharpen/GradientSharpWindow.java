package pl.lab3.gradient_sharpen;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.histogram.view.HistogramChart;
import pl.misc.PreviewImage;
import pl.workspace.model.ImageModel;

public class GradientSharpWindow extends JDialog implements Observer{
	
	private ImageModel image_model;
	private PreviewImage preview;
	private HistogramChart histogram;
	private JButton button_confirm, button_cancel;
	private ButtonGroup buttons_group, buttons_group2;
	private JRadioButton proportional, three_valued, cutting;
	private JRadioButton sobel, roberts, universal;
	
	public GradientSharpWindow(JFrame parent, ImageModel model){
		super(parent, "Wyostrzanie gradientowe");
		
		image_model = model;
		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		
		JPanel panel_top = new JPanel();
		JPanel panel_bot = new JPanel();
		JPanel panel_buttons = new JPanel();
		JPanel panel_mid = new JPanel();
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.X_AXIS));
		panel_bot.setLayout(new BoxLayout(panel_bot, BoxLayout.X_AXIS));
		panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.X_AXIS));
		panel_mid.setLayout(new BoxLayout(panel_mid, BoxLayout.X_AXIS));
		
		//top panel
		proportional = new JRadioButton("proporcjonalna");
		three_valued = new JRadioButton("trojwartosciowa");
		cutting = new JRadioButton("obcinajaca");
		buttons_group = new ButtonGroup();
		buttons_group.add(proportional);
		buttons_group.add(three_valued);
		buttons_group.add(cutting);
		panel_top.add(proportional);
		panel_top.add(three_valued);
		panel_top.add(cutting);
		proportional.setSelected(true);
		
		//mid panel
		sobel = new JRadioButton("sobel");
		roberts = new JRadioButton("roberts");
		buttons_group2 = new ButtonGroup();
		buttons_group2.add(sobel);
		buttons_group2.add(roberts);
		panel_mid.add(sobel);
		panel_mid.add(roberts);	
		roberts.setSelected(true);
		
		//bot panel
		preview = new PreviewImage(model);
		preview.getImageModel().addObserver(this);
		histogram = new HistogramChart(preview.getImageModel());
		histogram.setMaximumSize(new Dimension(300, 300));
		histogram.setMinimumSize(new Dimension(300, 300));
		histogram.setPreferredSize(new Dimension(300, 300));
		
		panel_bot.add(Box.createRigidArea(new Dimension(5, 0)));
		panel_bot.add(preview);
		panel_bot.add(Box.createGlue());
		panel_bot.add(histogram);
		panel_bot.add(Box.createRigidArea(new Dimension(5, 0)));
		
		//buttons panel
		button_confirm = new JButton("OK");
		button_cancel = new JButton("Cancel");
		button_confirm.setPreferredSize(new Dimension(100, 25));
		button_cancel.setPreferredSize(new Dimension(100, 25));
		panel_buttons.add(Box.createRigidArea(new Dimension(5, 0)));
		panel_buttons.add(button_confirm);
		panel_buttons.add(Box.createRigidArea(new Dimension(25, 0)));
		panel_buttons.add(button_cancel);
		panel_buttons.add(Box.createGlue());
		
		cp.add(panel_top);
		cp.add(panel_mid);
		cp.add(panel_bot);
		cp.add(panel_buttons);
		
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(parent.getContentPane().getLocationOnScreen());
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();		
	}
	
	public int getScalingSelected(){
		if(proportional.isSelected()) return 1;
		if(three_valued.isSelected()) return 2;
		return 3;
	}
	
	public int getOperationSelected(){
		if(roberts.isSelected()) return 1;
		else return 2;
	}
	
	public PreviewImage getPreviewImage() {
		return preview;
	}

	public void addOperationItemListeners(ItemListener listener){
		sobel.addItemListener(listener);
		roberts.addItemListener(listener);
	}
	
	public void addScalingItemListeners(ItemListener listener){
		proportional.addItemListener(listener);
		three_valued.addItemListener(listener);
		cutting.addItemListener(listener);
	}
	
	public void addButtonConfirmListener(ActionListener listener){
		button_confirm.addActionListener(listener);
	}
	
	public void addButtonCancelListener(ActionListener listener){
		button_cancel.addActionListener(listener);
	}
	
	public void updateHistogramChart(){
		histogram.setImageModel(preview.getImageModel());
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}

}
