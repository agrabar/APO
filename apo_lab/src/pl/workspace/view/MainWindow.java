package pl.workspace.view;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import pl.workspace.controller.WorkspaceController;

public class MainWindow extends JFrame{
	
	private MyMenuBar menu_bar;
	private JDesktopPane desktop_pane;
	
	public MainWindow(){
		super("APO");
		desktop_pane = new JDesktopPane();
		setContentPane(desktop_pane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		menu_bar = new MyMenuBar();
		setJMenuBar(menu_bar);
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Blad przy ustawianiu LookAndFeel");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					WorkspaceController controller = new WorkspaceController(frame);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Blad glownego okna");
				}
			}
		});
	}
	
	public JDesktopPane getDesktopPane(){
		return desktop_pane;
	}
	
	public ImageWindow getSelectedWindow(){
		return (ImageWindow)desktop_pane.getSelectedFrame();
	}
	
	public MyMenuBar getMyMenuBar(){
		return menu_bar;
	}
	

}
