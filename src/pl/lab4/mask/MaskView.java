package pl.lab4.mask;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pl.histogram.view.HistogramChart;
import pl.lab4.util.TwoMasks;
import pl.misc.PreviewImage;
import pl.workspace.model.ImageModel;

public class MaskView extends JDialog implements Observer{

	private JFormattedTextField[][] mask1;
	private JFormattedTextField[][] mask2;
	private JTextField[][] mask_out;
	private ImageModel image_model;
	private HistogramChart histogram;
	private JButton button_confirm, button_cancel;
	private ButtonGroup buttons_group;
	private JRadioButton proportional, three_valued, cutting;
	private PreviewImage preview;
	
	
	public MaskView(JFrame parent, ImageModel model){
		super(parent, "Skladana maska");
		image_model = model;
		
		mask1 = new JFormattedTextField[3][3];
		mask2 = new JFormattedTextField[3][3];
		mask_out = new JTextField[5][5];
		
		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
		
		JPanel panel_left = new JPanel();
		panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
		//panel_left.setPreferredSize(new Dimension(300, 600));
		JPanel panel_right = new JPanel();
		panel_right.setLayout(new BoxLayout(panel_right, BoxLayout.Y_AXIS));
		//panel_right.setPreferredSize(new Dimension(300, 600));
		
		JPanel panel_mask1 = new JPanel();
		panel_mask1.setLayout(new GridLayout(3, 3));
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(0);
		format.setParseIntegerOnly(true);
		/*NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);*/
		for(int i = 0; i < mask1.length; ++i){
			for(int j = 0; j < mask1.length; ++j){
				mask1[i][j] = new JFormattedTextField(format);
				mask1[i][j].setHorizontalAlignment(JTextField.CENTER);
				panel_mask1.add(mask1[i][j]);
				//mask1[i][j].setText("" + (i*mask1.length+j));
				mask1[i][j].setText("1");
			}
		}
		
		JPanel panel_mask2 = new JPanel();
		panel_mask2.setLayout(new GridLayout(3, 3));
		for(int i = 0; i < mask2.length; ++i){
			for(int j = 0; j < mask2.length; ++j){
				mask2[i][j] = new JFormattedTextField(format);
				mask2[i][j].setHorizontalAlignment(JTextField.CENTER);
				panel_mask2.add(mask2[i][j]);
				mask2[i][j].setText("1");
			}
		}
		
		JPanel panel_mask_out = new JPanel();
		panel_mask_out.setLayout(new GridLayout(5, 5));
		for(int i = 0; i < mask_out.length; ++i){
			for(int j = 0; j < mask_out.length; ++j){
				mask_out[i][j] = new JTextField();
				mask_out[i][j].setHorizontalAlignment(JTextField.CENTER);
				mask_out[i][j].setEditable(false);
				panel_mask_out.add(mask_out[i][j]);
			}
		}
		
		setMaskOut(TwoMasks.getMask(getMask1(), getMask2()));
		
		panel_left.add(panel_mask1);
		panel_left.add(panel_mask2);
		panel_left.add(panel_mask_out);
		
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
		//proportional.setSelected(true);
		panel_left.add(panel_radio);
		
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
		
		//right panel
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
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void addButtonConfirmListener(ActionListener listener){
		button_confirm.addActionListener(listener);
	}
	
	public void addButtonCancelListener(ActionListener listener){
		button_cancel.addActionListener(listener);
	}
	
	public void addRadioButtonsListener(ItemListener listener){
		proportional.addItemListener(listener);
		three_valued.addItemListener(listener);
		cutting.addItemListener(listener);
	}
	
	public PreviewImage getPreviewImage(){
		return preview;
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}
	
	public void updateHistogramChart(){
		histogram.setImageModel(preview.getImageModel());
	}
	
	public void addMaskListeners(ActionListener listener){
		for(int i = 0; i < mask1.length; ++i){
			for(int j = 0; j < mask1[i].length; ++j){
				mask1[i][j].addActionListener(listener);
			}
		}
		for(int i = 0; i < mask2.length; ++i){
			for(int j = 0; j < mask2[i].length; ++j){
				mask2[i][j].addActionListener(listener);
			}
		}
	}
	
	public JRadioButton getSelectedRadioButton(){
		if(proportional.isSelected()) return proportional;
		if(three_valued.isSelected()) return three_valued;
		if(cutting.isSelected()) return cutting;
		return null;
	}
	
	public int[][] getMask1(){
		int [][] mask = new int[3][3];
		
		for(int i = 0; i < mask1.length; ++i){
			for(int j = 0; j < mask1[i].length; ++j){
				mask[i][j] = Integer.parseInt(mask1[i][j].getText());
			}
		}
		
		return mask;
	}
	
	public int[][] getMask2(){
		int [][] mask = new int[3][3];
		
		for(int i = 0; i < mask2.length; ++i){
			for(int j = 0; j < mask2[i].length; ++j){
				mask[i][j] = Integer.parseInt(mask2[i][j].getText());
			}
		}
		
		return mask;
	}
	
	public int[][] getMaskOut(){
		int [][] mask = new int[5][5];
		
		for(int i = 0; i < mask_out.length; ++i){
			for(int j = 0; j < mask_out[i].length; ++j){
				mask[i][j] = Integer.parseInt(mask_out[i][j].getText());
			}
		}
		
		return mask;
	}
	
	public void setMaskOut(int[][] mask){
		for(int i = 0; i < mask_out.length; ++i){
			for(int j = 0; j < mask_out[i].length; ++j){
				mask_out[i][j].setText("" + mask[i][j]);
			}
		}
	}
	
	public void updateMask(){
		setMaskOut(TwoMasks.getMask(getMask1(), getMask2()));
	}
}
