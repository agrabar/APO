package pl.lab3.filtracja_logiczna;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

import pl.lab3.util.LogicznaUtil;

public class LogicznaController {
	
	private LogicznaWindow logiczna_window;
	
	public LogicznaController(LogicznaWindow window){
		logiczna_window = window;
		InitListeners();
	}
	
	private int getDirection(JRadioButton but){
		switch(but.getText()){
			case "0":return LogicznaUtil.DIRECTION0;
			case "1":return LogicznaUtil.DIRECTION1;
			case "2":return LogicznaUtil.DIRECTION2;
			case "3":return LogicznaUtil.DIRECTION3;
			default: return LogicznaUtil.DIRECTION0;
		}
	}
	
	private void InitListeners(){
		logiczna_window.addItemListeners(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					logiczna_window.getPreviewImage().restoreImageModel(logiczna_window);
					int method = 0;
					/*switch(((JRadioButton)e.getSource()).getText()){
						case "0":{
							method = LogicznaUtil.DIRECTION0;
							break;
						}
						case "1":{
							method = LogicznaUtil.DIRECTION1;
							break;
						}
						case "2":{
							method = LogicznaUtil.DIRECTION2;
							break;
						}
						case "3":{
							method = LogicznaUtil.DIRECTION3;
							break;
						}
					}*/
					method = getDirection((JRadioButton)e.getSource());
					//System.out.println(method);
					//LogicznaUtil.logical(logiczna_window.getPreviewImage().getImageModel(), method);
					LogicznaUtil.logical(logiczna_window.getPreviewImage().getImageModel(), logiczna_window.getSelectedDirection());
					logiczna_window.updateHistogramChart();
				}
			}
		});
		
		logiczna_window.addButtonConfirmListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogicznaUtil.logical(logiczna_window.getImageModel(), logiczna_window.getSelectedDirection());
				logiczna_window.dispose();
			}
		});
		
		logiczna_window.addButtonCancelListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				logiczna_window.dispose();
			}
		});
	}

}
