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
import pl.workspace.model.ImageModel;
import pl.workspace.view.ImageWindow;
import pl.workspace.view.MainWindow;
import pl.workspace.view.MyMenuBar;

public class WorkspaceController implements MyMenuBarInterface, InternalFrameListener{
	
	private MainWindow workspace;
	
	public WorkspaceController(MainWindow workspace) {
		this.workspace = workspace;
		InitListeners();
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
			} catch (PropertyVetoException e) {
				System.out.println("Blad przy setSelected");
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
