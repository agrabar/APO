package pl.lab3.filtracja_medianowa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.lab3.util.FiltracjaUtil;

public class MedianController {

	private MedianWindow median_window;
	
	public MedianController(MedianWindow m_window){
		median_window = m_window;
		InitListeners();
	}
	
	private void InitListeners(){
		median_window.addChangeListeners(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				median_window.getPreview().restoreImageModel(median_window);
				FiltracjaUtil.median(median_window.getPreview().getImageModel(), (int)median_window.getMask_height().getValue(),
						(int)median_window.getMask_width().getValue());
				median_window.updateHistogramChart();
			}
		});
		
		median_window.addButtonConfirmListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FiltracjaUtil.median(median_window.getImage_model(), (int)median_window.getMask_height().getValue(),
						(int)median_window.getMask_width().getValue());
				median_window.dispose();
			}
		});
		
		median_window.addButtonCancelListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				median_window.dispose();
			}
		});
	}
	
}
