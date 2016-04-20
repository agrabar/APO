package pl.workspace.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar{
	
	private JMenu menuPlik;
	private JMenuItem menuPlikOtworz;
	
	private JMenu menuObraz;
	private JMenuItem menuObrazDuplikuj;
	private JMenuItem menuObrazHistogram;
	
	private JMenu menuLab1;
	private JMenu menuLab1Wyrownanie;
	private JMenuItem menuLab1WyrownanieSrednia;
	private JMenuItem menuLab1WyrownanieLosowa;
	private JMenuItem menuLab1WyrownanieSasiedztwa;
	private JMenuItem menuLab1WyrownanieWlasna;
	
	public MyMenuBar(){
		InitComponents();
	}
	
	private void InitComponents()
	{
		menuPlik = new JMenu("Plik");
		menuPlikOtworz = new JMenuItem("Otworz");
		menuPlik.add(menuPlikOtworz);
		
		menuObraz = new JMenu("Obraz");
		menuObrazDuplikuj = new JMenuItem("Duplikuj");
		menuObrazHistogram = new JMenuItem("Histogram");
		menuObraz.add(menuObrazDuplikuj);
		menuObraz.add(menuObrazHistogram);
		
		menuLab1 = new JMenu("Lab1");
		menuLab1Wyrownanie = new JMenu("Wyrownanie histogramu");
		menuLab1WyrownanieSrednia = new JMenuItem("Metoda œredniej");
		menuLab1WyrownanieLosowa = new JMenuItem("Metoda losowa");
		menuLab1WyrownanieSasiedztwa = new JMenuItem("Metoda s¹siedztwa");
		menuLab1WyrownanieWlasna = new JMenuItem("Metoda w³asna");
		menuLab1Wyrownanie.add(menuLab1WyrownanieSrednia);
		menuLab1Wyrownanie.add(menuLab1WyrownanieLosowa);
		menuLab1Wyrownanie.add(menuLab1WyrownanieSasiedztwa);
		menuLab1Wyrownanie.add(menuLab1WyrownanieWlasna);
		menuLab1.add(menuLab1Wyrownanie);
		
		setEnabledSections(false);
		add(menuPlik);
		add(menuObraz);
		add(menuLab1);
	}
	
	public void setEnabledSections(boolean opt){
		menuObraz.setEnabled(opt);
		menuLab1.setEnabled(opt);
	}
	
	public void addMenuPlikOtworzListener(ActionListener listener){
		menuPlikOtworz.addActionListener(listener);
	}
	
	public void addMenuObrazDuplikujListener(ActionListener listener){
		menuObrazDuplikuj.addActionListener(listener);
	}
	
	public void addMenuObrazHistogramListener(ActionListener listener){
		menuObrazHistogram.addActionListener(listener);
	}
	
	public void addMenuLab1WyrownanieSredniaListener(ActionListener listener){
		menuLab1WyrownanieSrednia.addActionListener(listener);
	}
	
	public void addMenuLab1WyrownanieLosowaListener(ActionListener listener){
		menuLab1WyrownanieLosowa.addActionListener(listener);
	}
	
	public void addMenuLab1WyrownanieSasiedztwaListener(ActionListener listener){
		menuLab1WyrownanieSasiedztwa.addActionListener(listener);
	}
	
	public void addMenuLab1WyrownanieWlasnaListener(ActionListener listener){
		menuLab1WyrownanieWlasna.addActionListener(listener);
	}

}
