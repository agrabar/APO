package pl.lab3.uol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import pl.lab3.util.FiltracjaUtil;

public class UOLController {
	
	private UOLWindow uol_window;

	public UOLController(UOLWindow uolw){
		uol_window = uolw;
		InitListeners();
	}
	
	private int getScaling(){
		int[][] mask_pom = uol_window.getMaskPanel().getMask();
		boolean opt = true;
		OUTER_LOOP:
		for(int i = 0; i < mask_pom.length; ++i){
			for(int j = 0; j < mask_pom[i].length; ++j){
				if(mask_pom[i][j] < 0){
					opt = false;
					break OUTER_LOOP;
				}
			}
		}
		if(opt)
			return FiltracjaUtil.NONE;
		switch(uol_window.getSelectedRadioButton().getText()){
			case "proporcjonalna":{
				return FiltracjaUtil.PROPORTIONAL;
			}
			case "trojwartosciowa":{
				return FiltracjaUtil.THREE_VALUED;
			}
			case "obcinajaca":{
				return FiltracjaUtil.CUTTING;
			}
			default: return FiltracjaUtil.NONE;
		}
	}
	
	private void maskChanged(){
		uol_window.getPreviewImage().restoreImageModel(uol_window);
		FiltracjaUtil.filter(uol_window.getPreviewImage().getImageModel(), uol_window.getMaskPanel().getMask(), getScaling(), 256);
		uol_window.updateHistogramChart();
	}
	
	private void InitListeners(){
		
		uol_window.addRadioButtonsListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maskChanged();
			}
		});
		
		uol_window.addButtonConfirmListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FiltracjaUtil.filter(uol_window.getImageModel(), uol_window.getMaskPanel().getMask(), getScaling(), 256);
				uol_window.dispose();
			}
		});
		
		uol_window.addButtonCancelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uol_window.dispose();
			}
		});
		
		uol_window.addMaskPanelListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uol_window.getMaskPanel().updateEntries();
				maskChanged();
			}
		});
		
		/*uol_window.addMaskPanelListeners(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				onEntryChanged();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				onEntryChanged();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				onEntryChanged();
			}
		});*/
		
		
	}
	
	private void onEntryChanged(){
		uol_window.getMaskPanel().updateEntries();
		maskChanged();
	}

}
