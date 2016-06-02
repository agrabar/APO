package pl.lab4.mask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaskController {
	
	private MaskView mask_view;
	
	public MaskController(MaskView maskview){
		mask_view = maskview;
		InitListeners();
	}

	private void InitListeners(){
		mask_view.addButtonCancelListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mask_view.dispose();
			}
		});
	}
}
