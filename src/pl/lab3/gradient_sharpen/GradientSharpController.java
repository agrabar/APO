package pl.lab3.gradient_sharpen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import pl.lab3.util.FiltracjaUtil;
import pl.lab3.util.GradientUtil;
import pl.workspace.model.ImageModel;

public class GradientSharpController {
	
	private GradientSharpWindow gradient_window;
	
	public GradientSharpController(GradientSharpWindow window){
		gradient_window = window;
		InitListeners();
		performOperation(gradient_window.getPreviewImage().getImageModel());
	}
	
	private void performOperation(ImageModel model){
		if(gradient_window.getOperationSelected() == 1){
			//GradientUtil.roberts(gradient_window.getPreviewImage().getImageModel(), gradient_window.getScalingSelected(), 256);
			GradientUtil.roberts(model, gradient_window.getScalingSelected(), 256);
		}
		else
			//GradientUtil.sobel(gradient_window.getPreviewImage().getImageModel(), gradient_window.getScalingSelected(), 256);
			GradientUtil.sobel(model, gradient_window.getScalingSelected(), 256);
	}
	
	private void InitListeners(){
		gradient_window.addOperationItemListeners(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					gradient_window.getPreviewImage().restoreImageModel(gradient_window);
					performOperation(gradient_window.getPreviewImage().getImageModel());
					gradient_window.updateHistogramChart();
				}
				
			}
		});
		
		gradient_window.addScalingItemListeners(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					gradient_window.getPreviewImage().restoreImageModel(gradient_window);
					performOperation(gradient_window.getPreviewImage().getImageModel());
					gradient_window.updateHistogramChart();
				}		
			}
		});
		
		gradient_window.addButtonConfirmListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				performOperation(gradient_window.getImageModel());
				gradient_window.dispose();
			}
		});
		
		gradient_window.addButtonCancelListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				gradient_window.dispose();
			}
		});
	}

}
