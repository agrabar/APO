package pl.workspace.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar{
	
	//sekcja plik
	private JMenu menuPlik;
	private JMenuItem menuPlikOtworz;
	
	//sekcja obraz
	private JMenu menuObraz;
	private JMenuItem menuObrazDuplikuj;
	private JMenuItem menuObrazHistogram;
	
	//sekcja lab1
	private JMenu menuLab1;
	private JMenu menuLab1Wyrownanie;
	private JMenuItem menuLab1WyrownanieSrednia;
	private JMenuItem menuLab1WyrownanieLosowa;
	private JMenuItem menuLab1WyrownanieSasiedztwa;
	private JMenuItem menuLab1WyrownanieWlasna;
	
	private JMenu menuLab2;
	
	//sekcja lab3
	private JMenu menuLab3;
	private JMenuItem menuLab3Filtracja;
	private JMenuItem menuLab3UOL;
	private JMenuItem menuLab3Medianowa;
	
	private JMenuItem menuLab3Gradient;
	
	public MyMenuBar(){
		InitComponents();
	}
	
	private void InitComponents()
	{
		//sekcja plik
		menuPlik = new JMenu("Plik");
		menuPlikOtworz = new JMenuItem("Otworz");
		menuPlik.add(menuPlikOtworz);
		
		//sekcja obraz
		menuObraz = new JMenu("Obraz");
		menuObrazDuplikuj = new JMenuItem("Duplikuj");
		menuObrazHistogram = new JMenuItem("Histogram");
		menuObraz.add(menuObrazDuplikuj);
		menuObraz.add(menuObrazHistogram);
		
		//sekcja lab1
		menuLab1 = new JMenu("Lab1");
		menuLab1Wyrownanie = new JMenu("Wyrownanie histogramu");
		menuLab1WyrownanieSrednia = new JMenuItem("Metoda sredniej");
		menuLab1WyrownanieLosowa = new JMenuItem("Metoda losowa");
		menuLab1WyrownanieSasiedztwa = new JMenuItem("Metoda sasiedztwa");
		menuLab1WyrownanieWlasna = new JMenuItem("Metoda wlasna");
		menuLab1Wyrownanie.add(menuLab1WyrownanieSrednia);
		menuLab1Wyrownanie.add(menuLab1WyrownanieLosowa);
		menuLab1Wyrownanie.add(menuLab1WyrownanieSasiedztwa);
		menuLab1Wyrownanie.add(menuLab1WyrownanieWlasna);
		menuLab1.add(menuLab1Wyrownanie);
		
		menuLab2 = new JMenu("Lab2");
		
		//sekcja lab3
		menuLab3 = new JMenu("Lab3");
		menuLab3Filtracja = new JMenuItem("Filtracja liniowa");
		menuLab3UOL = new JMenuItem("UOL");
		menuLab3Medianowa = new JMenuItem("Filtracja medianowa");
		
		menuLab3Gradient = new JMenuItem("Gradientowe wyostrzanie");
		menuLab3.add(menuLab3Filtracja);
		menuLab3.add(menuLab3UOL);
		menuLab3.add(menuLab3Medianowa);
		
		menuLab3.add(menuLab3Gradient);
		
		setEnabledSections(false);
		add(menuPlik);
		add(menuObraz);
		add(menuLab1);
		add(menuLab2);
		add(menuLab3);
	}
	
	public void setEnabledSections(boolean opt){
		menuObraz.setEnabled(opt);
		menuLab1.setEnabled(opt);
		menuLab2.setEnabled(opt);
		menuLab3.setEnabled(opt);
	}
	//sekcja plik
	public void addMenuPlikOtworzListener(ActionListener listener){
		menuPlikOtworz.addActionListener(listener);
	}
	//sekcja obraz
	public void addMenuObrazDuplikujListener(ActionListener listener){
		menuObrazDuplikuj.addActionListener(listener);
	}
	
	public void addMenuObrazHistogramListener(ActionListener listener){
		menuObrazHistogram.addActionListener(listener);
	}
	//sekcja lab1
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
	//sekcja lab3
	public void addMenuLab3FiltracjaListener(ActionListener listener){
		menuLab3Filtracja.addActionListener(listener);
	}
	
	public void addMenuLab3UOLListener(ActionListener listener){
		menuLab3UOL.addActionListener(listener);
	}
	
	public void addMenuLab3MedianowaListener(ActionListener listener){
		menuLab3Medianowa.addActionListener(listener);
	}
	
	public void addMenuLab3GradientListener(ActionListener listener){
		menuLab3Gradient.addActionListener(listener);
	}

}
