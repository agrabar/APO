package pl.lab3.filtracja_liniowa;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.NumberFormatter;

public class MaskPanel extends JPanel{
	
	private int[][] mask;
	private JFormattedTextField[][] mask_elements;
	
	public MaskPanel(){
		//setMinimumSize(new Dimension(300, 300));
		setMaximumSize(new Dimension(180, 75));
		setPreferredSize(new Dimension(180, 75));
		setLayout(new GridLayout(3, 3));
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(0);
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);
		mask_elements = new JFormattedTextField[3][3];
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements.length; ++j){
				mask_elements[i][j] = new JFormattedTextField(format);
				mask_elements[i][j].setHorizontalAlignment(JTextField.CENTER);
				add(mask_elements[i][j]);
			}
		}
	}
	
	public void addMaskListeners(ActionListener listener){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask_elements[i][j].addActionListener(listener);
			}
		}
	}
	
	public void addMaskListeners(DocumentListener listener){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask_elements[i][j].getDocument().addDocumentListener(listener);
			}
		}
	}
	
	public void enableMask(boolean opt){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask_elements[i][j].setEnabled(opt);
			}
		}
	}
	
	public int[][] getValues(){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask[i][j] = Integer.parseInt(mask_elements[i][j].getText());
			}
		}
		return mask;
	}
	
	public void updateEntries(){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask[i][j] = Integer.parseInt(mask_elements[i][j].getText());
			}
		}
	}
	
	public void updateMask(){
		for(int i = 0; i < mask_elements.length; ++i){
			for(int j = 0; j < mask_elements[i].length; ++j){
				mask_elements[i][j].setText(mask[i][j]+"");
			}
		}
	}
	
	public int[][] getMask(){
		return mask;
	}
	
	public void setValues(int[][] m){
		mask = new int[m.length][];
		for(int i = 0; i < m.length; ++i){
			mask[i] = Arrays.copyOf(m[i], m[i].length);
		}
	}

}
