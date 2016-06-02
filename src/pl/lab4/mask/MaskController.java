package pl.lab4.mask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import pl.lab3.util.FiltracjaUtil;

public class MaskController {
	
	private MaskView mask_view;
	
	public MaskController(MaskView maskview){
		mask_view = maskview;
		InitListeners();
	}
	
	private void maskChanged(){
		mask_view.updateMask();
		mask_view.getPreviewImage().restoreImageModel(mask_view);
		FiltracjaUtil.filter(mask_view.getPreviewImage().getImageModel(), mask_view.getMaskOut(), getScaling(), 256);
		mask_view.updateHistogramChart();
	}
	
	private int getScaling(){
		int[][] mask_pom = mask_view.getMaskOut();
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
		switch(mask_view.getSelectedRadioButton().getText()){
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

	private void InitListeners(){
		mask_view.addButtonCancelListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mask_view.dispose();
			}
		});
		
		mask_view.addMaskListeners(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				maskChanged();
			}
		});
		
		mask_view.addRadioButtonsListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					maskChanged();
			}
		});
		
		mask_view.addButtonConfirmListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FiltracjaUtil.filter(mask_view.getImageModel(), mask_view.getMaskOut(), getScaling(), 256);
				mask_view.dispose();
			}
		});
	}
}
