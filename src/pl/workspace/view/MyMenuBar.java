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
	private JMenuItem menuLab3Logiczna;
	private JMenuItem menuLab3Gradient;
	
	//sekcja lab4
	private JMenu menuLab4;
	private JMenu menuLab4Morf;
	private JMenu menuLab4MorfErozja;
	private JMenu menuLab4MorfDylatacja;
	private JMenu menuLab4MorfOtwarcie;
	private JMenu menuLab4MorfZamkniecie;
	private JMenuItem menuLab4MorfErozja4;
	private JMenuItem menuLab4MorfErozja8;
	private JMenuItem menuLab4MorfDylatacja4;
	private JMenuItem menuLab4MorfDylatacja8;
	private JMenuItem menuLab4MorfOtwarcie4;
	private JMenuItem menuLab4MorfOtwarcie8;
	private JMenuItem menuLab4MorfZakmniecie4;
	private JMenuItem menuLab4MorfZamkniecie8;
	
	private JMenuItem menuLab4maski;
	
	//sekcja lab5
	private JMenu menuLab5;
	private JMenuItem menuLab5RLE;
	private JMenuItem menuLab5READ;
	private JMenuItem menuLab5Huffman;
	private JMenuItem menuLab5blokowa;
	private JMenuItem menuLab5drzewo;
	
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
		menuLab3Logiczna = new JMenuItem("Filtracja logiczna");
		menuLab3Gradient = new JMenuItem("Gradientowe wyostrzanie");
		menuLab3.add(menuLab3Filtracja);
		menuLab3.add(menuLab3UOL);
		menuLab3.add(menuLab3Medianowa);
		menuLab3.add(menuLab3Logiczna);
		menuLab3.add(menuLab3Gradient);
		
		//sekcja lab4
		menuLab4 = new JMenu("Lab4");
		menuLab4Morf = new JMenu("Operacje morfologiczne");
		menuLab4MorfErozja = new JMenu("Erozja");
		menuLab4MorfDylatacja = new JMenu("Dylatacja");
		menuLab4MorfOtwarcie = new JMenu("Otwarcie");
		menuLab4MorfZamkniecie = new JMenu("Zamkniecie");
		menuLab4MorfErozja4 = new JMenuItem("Romb");
		menuLab4MorfErozja8 = new JMenuItem("Kwadrat");
		menuLab4MorfDylatacja4 = new JMenuItem("Romb");
		menuLab4MorfDylatacja8 = new JMenuItem("Kwadrat");
		menuLab4MorfOtwarcie4 = new JMenuItem("Romb");
		menuLab4MorfOtwarcie8 = new JMenuItem("Kwadrat");
		menuLab4MorfZakmniecie4 = new JMenuItem("Romb");
		menuLab4MorfZamkniecie8 = new JMenuItem("Kwadrat");
		menuLab4MorfErozja.add(menuLab4MorfErozja4);
		menuLab4MorfErozja.add(menuLab4MorfErozja8);
		menuLab4MorfDylatacja.add(menuLab4MorfDylatacja4);
		menuLab4MorfDylatacja.add(menuLab4MorfDylatacja8);
		menuLab4MorfOtwarcie.add(menuLab4MorfOtwarcie4);
		menuLab4MorfOtwarcie.add(menuLab4MorfOtwarcie8);
		menuLab4MorfZamkniecie.add(menuLab4MorfZakmniecie4);
		menuLab4MorfZamkniecie.add(menuLab4MorfZamkniecie8);
		menuLab4Morf.add(menuLab4MorfErozja);
		menuLab4Morf.add(menuLab4MorfDylatacja);
		menuLab4Morf.add(menuLab4MorfOtwarcie);
		menuLab4Morf.add(menuLab4MorfZamkniecie);
		menuLab4.add(menuLab4Morf);
		
		menuLab4maski = new JMenuItem("dwie maski");
		menuLab4.add(menuLab4maski);
		
		//sekcja lab5
		menuLab5 = new JMenu("Lab5");
		menuLab5RLE = new JMenuItem("RLE");
		menuLab5READ = new JMenuItem("READ");
		menuLab5Huffman = new JMenuItem("Huffman");
		menuLab5blokowa = new JMenuItem("Blokowa");
		menuLab5drzewo = new JMenuItem("Drzewo czworkowe");
		menuLab5.add(menuLab5RLE);
		menuLab5.add(menuLab5READ);
		menuLab5.add(menuLab5Huffman);
		menuLab5.add(menuLab5blokowa);
		menuLab5.add(menuLab5drzewo);
		
		setEnabledSections(false);
		add(menuPlik);
		add(menuObraz);
		add(menuLab1);
		add(menuLab2);
		add(menuLab3);
		add(menuLab4);
		add(menuLab5);
	}
	
	public void setEnabledSections(boolean opt){
		menuObraz.setEnabled(opt);
		menuLab1.setEnabled(opt);
		menuLab2.setEnabled(opt);
		menuLab3.setEnabled(opt);
		menuLab4.setEnabled(opt);
		menuLab5.setEnabled(opt);
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
	
	public void addMenuLab3LogicznaListener(ActionListener listener){
		menuLab3Logiczna.addActionListener(listener);
	}
	
	public void addMenuLab3GradientListener(ActionListener listener){
		menuLab3Gradient.addActionListener(listener);
	}
	
	//sekcja lab4
	public void addMenuLab4MorfErozja4(ActionListener listener){
		menuLab4MorfErozja4.addActionListener(listener);
	}
	
	public void addMenuLab4MorfErozja8(ActionListener listener){
		menuLab4MorfErozja8.addActionListener(listener);
	}
	
	public void addMenuLab4MorfDylatacja4(ActionListener listener){
		menuLab4MorfDylatacja4.addActionListener(listener);
	}
	
	public void addMenuLab4MorfDylatacja8(ActionListener listener){
		menuLab4MorfDylatacja8.addActionListener(listener);
	}
	
	public void addMenuLab4MorfOtwarcie4(ActionListener listener){
		menuLab4MorfOtwarcie4.addActionListener(listener);
	}
	
	public void addMenuLab4MorfOtwarcie8(ActionListener listener){
		menuLab4MorfOtwarcie8.addActionListener(listener);
	}
	
	public void addMenuLab4MorfZamkniecie4(ActionListener listener){
		menuLab4MorfZakmniecie4.addActionListener(listener);
	}
	
	public void addMenuLab4MorfZamkniecie8(ActionListener listener){
		menuLab4MorfZamkniecie8.addActionListener(listener);
	}
	
	public void addMenuLab4MaskiListener(ActionListener listener){
		menuLab4maski.addActionListener(listener);
	}
	
	//sekcja lab5
	public void addMenuLab5RLEListener(ActionListener listener){
		menuLab5RLE.addActionListener(listener);
	}
	
	public void addMenuLab5READListener(ActionListener listener){
		menuLab5READ.addActionListener(listener);
	}
	
	public void addMenuLab5HuffmanListener(ActionListener listener){
		menuLab5Huffman.addActionListener(listener);
	}
	
	public void addMenuLab5blokowaListener(ActionListener listener){
		menuLab5blokowa.addActionListener(listener);
	}
	
	public void addMenuLab5drzewoListener(ActionListener listener){
		menuLab5drzewo.addActionListener(listener);
	}

}
