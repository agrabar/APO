package pl.workspace.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import pl.lab4.mask.MaskController;
import pl.lab4.mask.MaskView;
import pl.lab4.util.MorphologicUtil;
import pl.lab5.util.CompressionUtil;
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
	
	@Override
	public void menuLab4MorfErozja4Clicked() {
		MorphologicUtil.erosion(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.ROMB, 256);
	}

	@Override
	public void menuLab4MorfErozja8Clicked() {
		MorphologicUtil.erosion(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.KWADRAT, 256);
	}

	@Override
	public void menuLab4MorfDylatacja4Clicked() {
		MorphologicUtil.dilation(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.ROMB);
	}

	@Override
	public void menuLab4MorfDylatacja8Clicked() {
		MorphologicUtil.dilation(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.KWADRAT);
	}

	@Override
	public void menuLab4MorfOtwarcie4Clicked() {
		MorphologicUtil.opening(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.ROMB, 256);
	}

	@Override
	public void menuLab4MorfOtwarcie8Clicked() {
		MorphologicUtil.opening(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.KWADRAT, 256);
	}

	@Override
	public void menuLab4MorfZamkniecie4Clicked() {
		MorphologicUtil.closing(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.ROMB, 256);
	}

	@Override
	public void menuLab4MorfZamkniecie8Clicked() {
		MorphologicUtil.opening(workspace.getSelectedWindow().getImageModel(), MorphologicUtil.KWADRAT, 256);
	}
	
	@Override
	public void menuLab4MaskiClicked() {
		MaskView maskview = new MaskView(workspace, workspace.getSelectedWindow().getImageModel());
		new MaskController(maskview);
	}
	
	@Override
	public void menuLab5RLEClicked() {
		//System.out.println("RLE");
		ImageModel im = workspace.getSelectedWindow().getImageModel();
		int kp = im.getImage().getHeight() * im.getImage().getWidth();
		int kw = CompressionUtil.RLE(im);
		JOptionPane.showMessageDialog(workspace, "KP = " + kp +"\nKW = " + kw +"\nSK = " + (double)kp/(double)kw);
	}

	@Override
	public void menuLab5READClicked() {
		ImageModel im = workspace.getSelectedWindow().getImageModel();
		int kp = im.getImage().getHeight() * im.getImage().getWidth();
		int kw = CompressionUtil.READ(im);
		JOptionPane.showMessageDialog(workspace, "KP = " + kp +"\nKW = " + kw +"\nSK = " + (double)kp/(double)kw);
	}

	@Override
	public void menuLab5HuffmanClicked() {
		ImageModel im = workspace.getSelectedWindow().getImageModel();
		int kp = im.getImage().getHeight() * im.getImage().getWidth();
		int kw = CompressionUtil.huffman(im);
		JOptionPane.showMessageDialog(workspace, "KP = " + kp +"\nKW = " + kw +"\nSK = " + (double)kp/(double)kw);	
	}

	@Override
	public void menuLab5blokowaClicked() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Wybierz wielkosc bloku");
		ButtonGroup bg = new ButtonGroup();
		JRadioButton but4 = new JRadioButton("4");
		JRadioButton but8 = new JRadioButton("8");
		JRadioButton but16 = new JRadioButton("16");
		JRadioButton but32 = new JRadioButton("32");
		bg.add(but4);
		bg.add(but8);
		bg.add(but16);
		bg.add(but32);
		but4.setSelected(true);
		
		panel.add(label);
		panel.add(but4);
		panel.add(but8);
		panel.add(but16);
		panel.add(but32);
		
		if(JOptionPane.showConfirmDialog(workspace, panel, "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
			Enumeration<AbstractButton> buttons = bg.getElements();
			while(buttons.hasMoreElements()){
				AbstractButton cur = buttons.nextElement();
				if(cur.isSelected())
				{
					if(!CompressionUtil.blocks(workspace.getSelectedWindow().getImageModel(), Integer.parseInt(cur.getText())))
						JOptionPane.showMessageDialog(workspace, "Obrazek o niepoprawnych wymiarach", "", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			//System.out.println(workspace.getSelectedWindow().getImageModel().getImage().getWidth() + " x " + workspace.getSelectedWindow().getImageModel().getImage().getHeight());
		}
	}

	@Override
	public void menuLab5drzewoClicked() {
		System.out.println("drzewo");
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
		
		menubar.addMenuLab4MorfErozja4(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfErozja4Clicked();
			}
		});
		
		menubar.addMenuLab4MorfErozja8(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfErozja8Clicked();
			}
		});
		
		menubar.addMenuLab4MorfDylatacja4(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfDylatacja4Clicked();
			}
		});
		
		menubar.addMenuLab4MorfDylatacja8(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfDylatacja8Clicked();
			}
		});
		
		menubar.addMenuLab4MorfOtwarcie4(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfOtwarcie4Clicked();
			}
		});
		
		menubar.addMenuLab4MorfOtwarcie8(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfOtwarcie8Clicked();
			}
		});
		
		menubar.addMenuLab4MorfZamkniecie4(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfZamkniecie4Clicked();
			}
		});
		
		menubar.addMenuLab4MorfZamkniecie8(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MorfZamkniecie8Clicked();
			}
		});
		
		menubar.addMenuLab4MaskiListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab4MaskiClicked();
			}
		});
		
		menubar.addMenuLab5RLEListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab5RLEClicked();
			}
		});
		
		menubar.addMenuLab5READListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab5READClicked();
			}
		});
		
		menubar.addMenuLab5HuffmanListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab5HuffmanClicked();
			}
		});
		
		menubar.addMenuLab5blokowaListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab5blokowaClicked();
			}
		});
		
		menubar.addMenuLab5drzewoListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuLab5drzewoClicked();
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
