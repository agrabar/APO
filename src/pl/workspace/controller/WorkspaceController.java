package pl.workspace.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import pl.histogram.view.HistogramWindow;
import pl.lab1.HistogramUtil;
import pl.lab3.filtracja_liniowa.FiltracjaController;
import pl.lab3.filtracja_liniowa.FiltracjaWindow;
import pl.lab3.filtracja_logiczna.LogicznaController;
import pl.lab3.filtracja_logiczna.LogicznaWindow;
import pl.lab3.filtracja_medianowa.MedianController;
import pl.lab3.filtracja_medianowa.MedianWindow;
import pl.lab3.gradient_sharpen.GradientSharpController;
import pl.lab3.gradient_sharpen.GradientSharpWindow;
import pl.lab3.uol.UOLController;
import pl.lab3.uol.UOLWindow;
import pl.workspace.model.ImageModel;
import pl.workspace.view.ImageWindow;
import pl.workspace.view.MainWindow;
import pl.workspace.view.MyMenuBar;

public class WorkspaceController implements MyMenuBarInterface, InternalFrameListener{
	
	private MainWindow workspace;
	
	public WorkspaceController(MainWindow workspace) {
		this.workspace = workspace;
		InitListeners();
		
		//obraz ladowany na pale
		ImageModel im;
		try {
			File file = new File("Burnher.bmp");
			im = new ImageModel(file);
			ImageWindow iw = new ImageWindow(im);
			workspace.getDesktopPane().add(iw);
			iw.setSelected(true);
			iw.addInternalFrameListener(this);
			workspace.getMyMenuBar().setEnabledSections(true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void menuPlikOtworzClicked() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "bmp");
		chooser.setFileFilter(filter);
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			try {
				ImageModel im = new ImageModel(file);
				ImageWindow iw = new ImageWindow(im);
				workspace.getDesktopPane().add(iw);
				iw.setSelected(true);
				iw.addInternalFrameListener(this);
				workspace.getMyMenuBar().setEnabledSections(true);
			} catch (IOException e) {
				System.out.println("Blad ladowania z pliku");
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				System.out.println("Blad przy setSelected");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void menuObrazDuplikujClicked() {
		ImageWindow iw = new ImageWindow(workspace.getSelectedWindow().getImageModel().duplicate());
		workspace.getDesktopPane().add(iw);
		try {
			iw.setSelected(true);
		} catch (PropertyVetoException e) {
			System.out.println("Blad przy setSelected");
		}
	}
	
	@Override
	public void menuObrazHistogramClicked() {
		ImageModel im_model = workspace.getSelectedWindow().getImageModel();
		new HistogramWindow(workspace, im_model);
	}
	
	@Override
	public void menuLab1WyrownanieSredniaClicked() {
		HistogramUtil.equalizeHistogram(workspace.getSelectedWindow().getImageModel(), HistogramUtil.SREDNIA);
	}

	@Override
	public void menuLab1WyrownanieLosowaClicked() {
		HistogramUtil.equalizeHistogram(workspace.getSelectedWindow().getImageModel(), HistogramUtil.LOSOWA);
	}

	@Override
	public void menuLab1WyrownanieSasiedztwaClicked() {
		HistogramUtil.equalizeHistogram(workspace.getSelectedWindow().getImageModel(), HistogramUtil.SASIEDZTWA);
	}

	@Override
	public void menuLab1WyrownanieWlasnaClicked() {
		HistogramUtil.equalizeHistogram(workspace.getSelectedWindow().getImageModel(), HistogramUtil.WLASNA);
	}
	
	@Override
	public void menuLab3FiltracjaClicked() {
		FiltracjaWindow fw = new FiltracjaWindow(workspace, workspace.getSelectedWindow().getImageModel());
		new FiltracjaController(fw);
	}
	
	@Override
	public void menuLab3UOLClicked() {
		UOLWindow uolw = new UOLWindow(workspace, workspace.getSelectedWindow().getImageModel());
		new UOLController(uolw);
	}
	
	@Override
	public void menuLab3MedianowaClicked() {
		MedianWindow mw = new MedianWindow(workspace, workspace.getSelectedWindow().getImageModel());
		new MedianController(mw);
	}
	
	@Override
	public void menuLab3LogicznaClicked() {
		LogicznaWindow lw = new LogicznaWindow(workspace, workspace.getSelectedWindow().getImageModel());
		new LogicznaController(lw);
	}
	
	@Override
	public void menuLab3GradientClicked() {
		GradientSharpWindow window = new GradientSharpWindow(workspace, workspace.getSelectedWindow().getImageModel());
		new GradientSharpController(window);
	}

	//dodawanie listnerow do menubar
	private void InitListeners(){
		MyMenuBar menubar = (MyMenuBar)workspace.getJMenuBar();
		
		menubar.addMenuPlikOtworzListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuPlikOtworzClicked();
			}
		});
		
		menubar.addMenuObrazDuplikujListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuObrazDuplikujClicked();
			}
		});
		
		menubar.addMenuObrazHistogramListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuObrazHistogramClicked();
			}
		});
		
		menubar.addMenuLab1WyrownanieSredniaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab1WyrownanieSredniaClicked();
			}
		});
		
		menubar.addMenuLab1WyrownanieLosowaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab1WyrownanieLosowaClicked();
			}
		});
		
		menubar.addMenuLab1WyrownanieSasiedztwaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab1WyrownanieSasiedztwaClicked();
			}
		});
		
		menubar.addMenuLab1WyrownanieWlasnaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab1WyrownanieWlasnaClicked();
			}
		});
		
		menubar.addMenuLab3FiltracjaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab3FiltracjaClicked();
			}
		});
		
		menubar.addMenuLab3UOLListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab3UOLClicked();
			}
		});
		
		menubar.addMenuLab3MedianowaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab3MedianowaClicked();
			}
		});
		
		menubar.addMenuLab3LogicznaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab3LogicznaClicked();
			}
		});
		
		menubar.addMenuLab3GradientListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab3GradientClicked();
			}
		});
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		if(workspace.getDesktopPane().getAllFrames().length == 0) workspace.getMyMenuBar().setEnabledSections(false);
	}

	//nieuzywane
	@Override public void internalFrameOpened(InternalFrameEvent e) {}
	@Override public void internalFrameClosing(InternalFrameEvent e) {}
	@Override public void internalFrameIconified(InternalFrameEvent e) {}
	@Override public void internalFrameDeiconified(InternalFrameEvent e) {}
	@Override public void internalFrameActivated(InternalFrameEvent e) {}
	@Override public void internalFrameDeactivated(InternalFrameEvent e) {}


	
}
