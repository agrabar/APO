package pl.lab3.uol;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
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
import javax.swing.event.DocumentListener;

import pl.histogram.view.HistogramChart;
import pl.lab3.filtracja_liniowa.MaskPanel;
import pl.lab3.filtracja_liniowa.PredefinedMasks;
import pl.misc.PreviewImage;
import pl.workspace.model.ImageModel;

public class UOLWindow extends JDialog implements Observer{
	
	private MaskPanel mask_panel;
	private ImageModel image_model;
	private PreviewImage preview;
	private HistogramChart histogram;
	private JButton button_confirm, button_cancel;
	private ButtonGroup buttons_group;
	private JRadioButton proportional, three_valued, cutting;
	
	public UOLWindow(JFrame parent, ImageModel model){
		super(parent, "UOL");
		image_model = model;
		//setPreferredSize(new Dimension(600, 600));
		setResizable(false);
		Container cp = getContentPane();
		//cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
		
		JPanel panel_left = new JPanel();
		panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
		panel_left.setPreferredSize(new Dimension(300, 600));
		JPanel panel_right = new JPanel();
		panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));
		panel_right.setPreferredSize(new Dimension(300, 600));
		
		//---------------left panel-----------------------
		
		//MaskPanel
		panel_left.add(Box.createRigidArea(new Dimension(10, 10)));
		mask_panel = new MaskPanel();
		mask_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mask_panel.enableMask(true);
		mask_panel.setValues(PredefinedMasks.wygladzanie1);
		mask_panel.updateMask();
		panel_left.add(mask_panel);
		
		//Radio buttons
		panel_left.add(Box.createRigidArea(new Dimension(10, 10)));
		JPanel panel_radio = new JPanel();
		panel_radio.setLayout(new BoxLayout(panel_radio, BoxLayout.Y_AXIS));
		proportional = new JRadioButton("proporcjonalna");
		three_valued = new JRadioButton("trojwartosciowa");
		cutting = new JRadioButton("obcinajaca");
		buttons_group = new ButtonGroup();
		buttons_group.add(proportional);
		buttons_group.add(three_valued);
		buttons_group.add(cutting);
		panel_radio.add(proportional);
		panel_radio.add(three_valued);
		panel_radio.add(cutting);
		proportional.setSelected(true);
		enableRadioGroup(true);
		panel_left.add(panel_radio);
		
		panel_left.add(Box.createGlue());
		
		//button_confirm + button_cancel
		button_confirm = new JButton("OK");
		button_confirm.setMinimumSize(new Dimension(100, 25));
		button_confirm.setMaximumSize(new Dimension(100, 25));
		button_confirm.setPreferredSize(new Dimension(100, 25));
		button_cancel = new JButton("Cancel");
		button_cancel.setMinimumSize(new Dimension(100, 25));
		button_cancel.setMaximumSize(new Dimension(100, 25));
		button_cancel.setPreferredSize(new Dimension(100, 25));
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
		//button_panel.setPreferredSize(new Dimension(250, 25));
		button_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		button_panel.add(button_confirm);
		button_panel.add(Box.createRigidArea(new Dimension(10, 0)));
		button_panel.add(button_cancel);
		//button_panel.add(Box.createGlue());
		
		panel_left.add(button_panel);
		panel_left.add(Box.createRigidArea(new Dimension(10, 10)));
		
		//----------------------right panel------------------------------
		//preview image + histogram
		preview = new PreviewImage(model);
		preview.getImageModel().addObserver(this);
		panel_right.add(preview);
		histogram = new HistogramChart(preview.getImageModel());
		histogram.setMaximumSize(new Dimension(300, 280));
		histogram.setMinimumSize(new Dimension(300, 280));
		histogram.setPreferredSize(new Dimension(300, 280));
		
		panel_right.add(Box.createGlue());
		panel_right.add(histogram);
		
		cp.add(panel_left);
		cp.add(Box.createGlue());
		cp.add(panel_right);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(parent.getContentPane().getLocationOnScreen());
		pack();
		setVisible(true);
	}
	
	
	public void addButtonConfirmListener(ActionListener listener){
		button_confirm.addActionListener(listener);
	}
	
	public void addButtonCancelListener(ActionListener listener){
		button_cancel.addActionListener(listener);
	}
	
	public void addMaskPanelListeners(ActionListener listener){
		mask_panel.addMaskListeners(listener);
	}
	
	public void addMaskPanelListeners(DocumentListener listener){
		mask_panel.addMaskListeners(listener);
	}
	
	public void addRadioButtonsListener(ActionListener listener){
		proportional.addActionListener(listener);
		three_valued.addActionListener(listener);
		cutting.addActionListener(listener);
	}
	
	public MaskPanel getMaskPanel(){
		return mask_panel;
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}
	
	public void enableRadioGroup(boolean opt){
		proportional.setEnabled(opt);
		three_valued.setEnabled(opt);
		cutting.setEnabled(opt);
	}

	public PreviewImage getPreviewImage(){
		return preview;
	}
	
	public HistogramChart getHistogramChart(){
		return histogram;
	}
	
	public JRadioButton getSelectedRadioButton(){
		if(proportional.isSelected()) return proportional;
		if(three_valued.isSelected()) return three_valued;
		if(cutting.isSelected()) return cutting;
		return null;
	}
	
	public void updateHistogramChart(){
		histogram.setImageModel(preview.getImageModel());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
