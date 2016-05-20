package pl.lab3.filtracja_liniowa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import pl.lab3.util.FiltracjaUtil;
import pl.workspace.model.ImageModel;

public class FiltracjaController {
	
	private FiltracjaWindow filtracja_window;

	public FiltracjaController(FiltracjaWindow fw){
		filtracja_window = fw;
		InitListeners();
	}
	
	/*private void rescaleModel(ImageModel model){
		switch(filtracja_window.getSelectedRadioButton().getText()){
			case "proporcjonalna":{
				FiltracjaUtil.rescale(model, FiltracjaUtil.PROPORTIONAL, 256);
				break;
			}
			case "trojwartosciowa":{
				FiltracjaUtil.rescale(model, FiltracjaUtil.THREE_VALUED, 256);
				break;
			}
			case "obcinajaca":{
				FiltracjaUtil.rescale(model, FiltracjaUtil.CUTTING, 256);
				break;
			}
		}
	}*/
	
	private int getScaling(){
		if(filtracja_window.getOperation().getSelectedIndex() == 0)
			return FiltracjaUtil.NONE;
		if(filtracja_window.getOperation().getSelectedIndex() == 3){
			int[][] mask_pom = filtracja_window.getMaskPanel().getMask();
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
		}
		switch(filtracja_window.getSelectedRadioButton().getText()){
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
		filtracja_window.getPreviewImage().restoreImageModel(filtracja_window);
		JComboBox<String> operation = filtracja_window.getOperation();
		JComboBox<String> mask = filtracja_window.getMask();
		MaskPanel mask_panel = filtracja_window.getMaskPanel();
		switch(operation.getSelectedIndex()){
			case 0:{
				switch(mask.getSelectedIndex()){
					case 0:{
						mask_panel.setValues(PredefinedMasks.wygladzanie1);
						break;
					}
					case 1:{
						mask_panel.setValues(PredefinedMasks.wygladzanie2);
						break;
					}
					case 2:{
						mask_panel.setValues(PredefinedMasks.wygladzanie3);
						break;
					}
					case 3:{
						mask_panel.setValues(PredefinedMasks.wygladzanie4);
						break;
					}
				}break;
			}
			case 1:{
				switch(mask.getSelectedIndex()){
					case 0:{
						mask_panel.setValues(PredefinedMasks.wyostrzanie1);
						break;
					}
					case 1:{
						mask_panel.setValues(PredefinedMasks.wyostrzanie2);
						break;
					}
					case 2:{
						mask_panel.setValues(PredefinedMasks.wyostrzanie3);
						break;
					}
					case 3:{
						mask_panel.setValues(PredefinedMasks.wyostrzanie4);
						break;
					}
				}
				break;
			}
			case 2:{
				switch(mask.getSelectedIndex()){
					case 0:{
						mask_panel.setValues(PredefinedMasks.detekcja1);
						break;
					}
					case 1:{
						mask_panel.setValues(PredefinedMasks.detekcja2);
						break;
					}
					case 2:{
						mask_panel.setValues(PredefinedMasks.detekcja3);
						break;
					}
					case 3:{
						mask_panel.setValues(PredefinedMasks.detekcja4);
						break;
					}
				}
				break;
			}
		}
		mask_panel.updateMask();
		FiltracjaUtil.filter(filtracja_window.getPreviewImage().getImageModel(), filtracja_window.getMaskPanel().getMask(), getScaling(), 256);
		filtracja_window.updateHistogramChart();
	}
	
	private void InitListeners(){
		filtracja_window.addOperationListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> operation = filtracja_window.getOperation();
				JComboBox<String> mask = filtracja_window.getMask();
				//mask.setSelectedIndex(0);
				switch(operation.getSelectedIndex()){
					case 0:{
						filtracja_window.enableRadioGroup(false);
						filtracja_window.getMask().setEnabled(true);
						filtracja_window.getMaskPanel().enableMask(false);
						mask.setSelectedIndex(0);
						break;
					}
					case 3:{
						filtracja_window.getMask().setEnabled(false);
						filtracja_window.enableRadioGroup(true);
						filtracja_window.getMaskPanel().enableMask(true);
						filtracja_window.getPreviewImage().restoreImageModel(filtracja_window);
						filtracja_window.getPreviewImage().getImageModel().modelChanged();
						break;
					}
					default:{
						filtracja_window.enableRadioGroup(true);
						filtracja_window.getMask().setEnabled(true);
						filtracja_window.getMaskPanel().enableMask(false);
						mask.setSelectedIndex(0);
						break;
					}
				}
			}
		});
		
		filtracja_window.addMaskListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maskChanged();
			}
		});
		
		filtracja_window.addRadioButtonsListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maskChanged();
			}
		});
		
		filtracja_window.addButtonConfirmListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FiltracjaUtil.filter(filtracja_window.getImageModel(), filtracja_window.getMaskPanel().getMask(), getScaling(), 256);
				filtracja_window.dispose();
			}
		});
		
		filtracja_window.addButtonCancelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filtracja_window.dispose();
			}
		});
		
		filtracja_window.addMaskPanelListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(filtracja_window.getOperation().getSelectedIndex() == 3){
					filtracja_window.getMaskPanel().updateEntries();
					maskChanged();
				}
			}
		});
		
	}
	
}
