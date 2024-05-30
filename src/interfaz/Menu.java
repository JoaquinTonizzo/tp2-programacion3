package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		initialize();
	}

	private void initialize() {
		setearVentana();

        ImageIcon imagen = crearImagen("mapaArg.png", 300, 500);
        
		JLabel panelMapa = new JLabel(imagen);
		panelMapa.setBounds(50, 0, 300, 500);
		frame.getContentPane().add(panelMapa);
		
		ImageIcon imagenTitulo = crearImagen("titulo.png",300, 200);
		JLabel titulo = new JLabel(imagenTitulo);
		titulo.setBounds(400, 20, 300, 200);
		frame.getContentPane().add(titulo);
		
		agregarBoton(frame,"Comenzar", new Color(55, 54, 53), Color.white, e -> comenzarAplicacion(), 430, 250, 250, 100);
		agregarBoton(frame,"Salir",  new Color(55, 54, 53), Color.white, e -> salirDelMenu(), 430, 400, 250, 100);
	}

	private void setearVentana() {
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 720, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	
	private void agregarBoton(JFrame frame, String texto, Color colorFondo, Color colorTexto, ActionListener actionListener, int x,int y, int width, int heigth) {
		JButton boton = new JButton(texto);
		boton.setBounds(x, y, width, heigth);
		boton.setBackground(colorFondo);
		boton.setForeground(colorTexto);
		boton.setFont(new Font("Tahoma", Font.BOLD, 20));
		boton.addActionListener(actionListener);
		boton.setBorderPainted(false);
		frame.getContentPane().add(boton);
	}

	private ImageIcon crearImagen(String pathImagen,int width, int heigth) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathImagen));
		Image originalImage = imageIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	private void comenzarAplicacion() {
		UI interfaz = new UI();
        interfaz.verMapa();
        frame.setVisible(false);
	}
	
	private void salirDelMenu() {
		System.exit(0);
	}
}
