package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Ventana extends JFrame {
	
	private JTextArea areaTexto;
	
	public Ventana() {
		super();
		setTitle("Documento nuevo");
		setSize(1024,768);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		URL url = getClass().getResource("/icono.png");
		setIconImage(new ImageIcon(url).getImage());
		crearTexto();
		crearMenu();
	}

	private void crearTexto() {
		areaTexto = new JTextArea("");
		areaTexto.setForeground(Color.green);
		areaTexto.setBackground(Color.black);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setLineWrap(true);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(areaTexto);
		getContentPane().add(scroll, BorderLayout.CENTER);
		revalidate();
		
	}

	private void crearMenu() {
		
		// Creamos la barra de menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Archivo");
		menu.setMnemonic(KeyEvent.VK_A);
		// Creamos el boton de nuevo archivo
		JMenuItem nuevoArchivo = new JMenuItem("Nuevo");
		nuevoArchivo.setIcon(new ImageIcon(getClass().getResource("/nuevo.png")));
		nuevoArchivo.setMnemonic(KeyEvent.VK_N);
		nuevoArchivo.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		nuevoArchivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevoArchivo();
				
			}
		});
		menu.add(nuevoArchivo);
		// Creamos el boton de abrir archivo
		JMenuItem abrirArchivo = new JMenuItem("Abrir...");
		abrirArchivo.setIcon(new ImageIcon(getClass().getResource("/abrir.png")));
		abrirArchivo.setMnemonic(KeyEvent.VK_B);
		abrirArchivo.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
		abrirArchivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		menu.add(abrirArchivo);
		// Creamos el boton de guardar
		JMenuItem guardarArchivo = new JMenuItem("Guardar");
		guardarArchivo.setIcon(new ImageIcon(getClass().getResource("/guardar.png")));
		guardarArchivo.setMnemonic(KeyEvent.VK_G);
		guardarArchivo.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		guardarArchivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();	
			}
		});
		menu.add(guardarArchivo);
		// Creamos el boton de guardar como
		JMenuItem guardarArchivoComo = new JMenuItem("Guardar como...");
		guardarArchivoComo.setIcon(new ImageIcon(getClass().getResource("/guardar.png")));
		guardarArchivoComo.setMnemonic(KeyEvent.VK_U);
		guardarArchivoComo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		guardarArchivoComo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		menu.add(guardarArchivoComo);
		// Creamos el boton de salir
		JMenuItem salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/salir.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(salir);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
	}

	protected void guardarComo() {
		// Metodo para guardar archivos como
		
	}

	protected void guardar() {
		// Metodo para guardar archivos
		
	}

	protected void abrir() {
		// Metodo para abrir archivos
		
	}

	protected void nuevoArchivo() {
		
		if (areaTexto != null) {
			areaTexto.setText("");
		}
		
	}

}
