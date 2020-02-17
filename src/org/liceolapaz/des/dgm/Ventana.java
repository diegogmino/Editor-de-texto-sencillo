package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame {
	
	private JTextArea areaTexto;
	private String ruta;
	
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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

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
				// Mensaje de aviso
				int opcion = JOptionPane.showConfirmDialog(null, "El texto que no se haya guardado se perderá. ¿Quiere salir?", "Salir", JOptionPane.YES_NO_OPTION);
				if (JOptionPane.YES_NO_OPTION == opcion) {
					System.exit(0);
				}
				
			}
		});
		menu.add(salir);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void guardarComo() {
		// Metodo para guardar archivos como
		JFileChooser filechooser = new JFileChooser();
		// Creamos un filtro para los txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto (.txt)", "txt");
		filechooser.setFileFilter(filter);
		int seleccion = filechooser.showSaveDialog(areaTexto);
		// Si pulsamos la opcion de aceptar...
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			// Guardamos el archivo seleccionado en una variable
			File fichero = filechooser.getSelectedFile();
			// Obtenemos la ruta 
			ruta = fichero.getAbsolutePath();
			// Si la ruta termina en txt guardamos
			if (ruta.endsWith("txt")) {
				guardar();	
			} else {
				// Mensaje de error
				JOptionPane.showMessageDialog(null, "Especifique la extensión del archivo");
				// Devolvemos la ruta a null
				ruta = null;
			}
			
		}
		
		setTitle(ruta);
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void guardar() {
		// Metodo para guardar archivos
		
		// Si la ruta existe, guardaremos el archivo en dicha ruta
		if (ruta != null) {
		// Inicializamos el Writer a null
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(ruta);
		} catch (FileNotFoundException e) {}
		
		// Escribimos lo que hay en el areaTexto
		writer.print(areaTexto.getText());
		writer.close();
		
		} else {
			// Llamamos al método GUARDAR COMO
			guardarComo();
			
		}
		
		setTitle(ruta);
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void abrir() {
		// Metodo para abrir archivos
		
		int opcion = JOptionPane.showConfirmDialog(null, "El texto que no se haya guardado se perderá. ¿Quiere abrir un fichero?", "Abrir fichero", JOptionPane.YES_NO_OPTION);
		if (JOptionPane.YES_NO_OPTION == opcion) {
		
		// Creamos una ventana para escribir la ruta del fichero
		ruta = JOptionPane.showInputDialog("Ruta del fichero");
		// Si la ruta es distinta de null
		if (ruta != null) {
		
			if (ruta.endsWith("txt")) {
				setTitle(ruta);
				try {
					// Creamos un lector de archivos
					BufferedReader reader = new BufferedReader(new FileReader(ruta));
					// Inicializamos la variable linea a null
					String linea = null;
					try {
						// Guardamos lo recuperado por el lector en la variable linea
						linea = reader.readLine();
					} catch (IOException e) {}
					// Si linea es distinto de nulo 
					while (linea != null) {
						// Sacamos por pantalla la linea leida
						areaTexto.append(linea);
						// Hacemos un salto de linea
						areaTexto.append(System.getProperty("line.separator"));
						try {
							// Leemos otra linea más
							linea = reader.readLine();
						} catch (IOException e) {}
					}
					try {
						// Cuando termine el WHILE cerramos el archivo
						reader.close();
					} catch (IOException e) {}
				} catch (FileNotFoundException e) {}   
		   
			} else {
				// Sacamos mensaje de error con el icono seleccionado
				JOptionPane.showMessageDialog(null,"La ruta " + ruta + " no es un fichero de texto", "Error", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("/error.png")));
			}
		
		}
		
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void crearTexto() {
		areaTexto = new JTextArea("");
		// Color de letra
		areaTexto.setForeground(Color.GREEN);
		// Color de fondo
		areaTexto.setBackground(Color.BLACK);
		areaTexto.setWrapStyleWord(true);
		// Color del cursor
		areaTexto.setCaretColor(Color.MAGENTA);
		// Para poder hacer scroll
		areaTexto.setLineWrap(true);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(areaTexto);
		getContentPane().add(scroll, BorderLayout.CENTER);
		revalidate();
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	protected void nuevoArchivo() {
		// Mensaje de aviso
		int opcion = JOptionPane.showConfirmDialog(null, "El texto que no se haya guardado se perderá. ¿Quiere crear un nuevo documento?", "Nuevo documento", JOptionPane.YES_NO_OPTION);
		// Si la opcion eds si...
		if (JOptionPane.YES_NO_OPTION == opcion) {
			if (areaTexto != null) {
				// Creamos una nueva area de texto
				areaTexto.setText("");
			}
		}
	}

}
