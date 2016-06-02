package pl.lab3.filtracja_logiczna;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pl.histogram.view.HistogramChart;
import pl.lab3.util.LogicznaUtil;
import pl.misc.PreviewImage;
import pl.workspace.model.ImageModel;

public class LogicznaWindow extends JDialog implements Observer{
	
	private ImageModel image_model;
	private PreviewImage preview;
	private HistogramChart histogram;
	private JButton button_confirm, button_cancel;
	private ButtonGroup buttons_group;
	private JRadioButton direction0, direction1, direction2, direction3;
	
	public LogicznaWindow(JFrame parent, ImageModel model){
		super(parent, "Filtracja logiczna");
		image_model = model;
		
		Container cp = getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		
		JPanel panel_top = new JPanel();
		JPanel panel_bot = new JPanel();
		JPanel panel_buttons = new JPanel();
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.X_AXIS));
		panel_bot.setLayout(new BoxLayout(panel_bot, BoxLayout.X_AXIS));
		panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.X_AXIS));
		
		//radio buttons
		JLabel label = new JLabel("kierunek");
		direction0 = new JRadioButton("0");
		direction1 = new JRadioButton("1");
		direction2 = new JRadioButton("2");
		direction3 = new JRadioButton("3");
		buttons_group = new ButtonGroup();
		buttons_group.add(direction0);
		buttons_group.add(direction1);
		buttons_group.add(direction2);
		buttons_group.add(direction3);
		//direction0.setSelected(true);
		
		//panel top
		panel_top.add(label);
		panel_top.add(direction0);
		panel_top.add(direction1);
		panel_top.add(direction2);
		panel_top.add(direction3);
		
		preview = new PreviewImage(model);
		preview.getImageModel().addObserver(this);
		histogram = new HistogramChart(preview.getImageModel());
		histogram.setMaximumSize(new Dimension(300, 300));
		histogram.setMinimumSize(new Dimension(300, 300));
		histogram.setPreferredSize(new Dimension(300, 300));
		
		//panel bot
		panel_bot.add(preview);
		panel_bot.add(histogram);
		
		button_confirm = new JButton("OK");
		button_cancel = new JButton("Cancel");
		button_confirm.setPreferredSize(new Dimension(100, 25));
		button_cancel.setPreferredSize(new Dimension(100, 25));
		
		//panel buttons
		panel_buttons.add(Box.createRigidArea(new Dimension(10, 0)));
		panel_buttons.add(button_confirm);
		panel_buttons.add(Box.createRigidArea(new Dimension(25, 0)));
		panel_buttons.add(button_cancel);
		panel_buttons.add(Box.createGlue());
		
		cp.add(panel_top);
		cp.add(panel_bot);
		cp.add(panel_buttons);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(parent.getContentPane().getLocationOnScreen());
		setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void addItemListeners(ItemListener listener){
		direction0.addItemListener(listener);
		direction1.addItemListener(listener);
		direction2.addItemListener(listener);
		direction3.addItemListener(listener);
	}
	
	public void addButtonConfirmListener(ActionListener listener){
		button_confirm.addActionListener(listener);
	}
	
	public void addButtonCancelListener(ActionListener listener){
		button_cancel.addActionListener(listener);
	}
	
	public PreviewImage getPreviewImage(){
		return preview;
	}
	
	public void updateHistogramChart(){
		histogram.setImageModel(preview.getImageModel());
	}
	
	public ImageModel getImageModel(){
		return image_model;
	}
	
	public int getSelectedDirection(){
		if(direction3.isSelected()) return LogicznaUtil.DIRECTION3;
		if(direction2.isSelected()) return LogicznaUtil.DIRECTION2;
		if(direction1.isSelected()) return LogicznaUtil.DIRECTION1;
		return LogicznaUtil.DIRECTION0;
	}

}
