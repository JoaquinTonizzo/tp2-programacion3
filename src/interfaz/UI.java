package interfaz;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import logica.Arista;
import logica.GeneradorGrafoArgentina;
import logica.Provincia;
import logica.Tupla;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UI {
	private JMapViewer mapa;
	private JPanel panelEntradaUsuario;
	private GeneradorGrafoArgentina generador;
	private JButton btnGenerarRegiones;
	private JButton btnGenerarAGM;
	private JButton btnGenerarGrafoInicial;
	
	public UI() {
		crearMapa();
        generador = new GeneradorGrafoArgentina(); 
        agregarMarcadoresProvincias();
        crearPanelEntradaUsuario();
	}

	private void crearMapa() {
		mapa = new JMapViewer();
		mapa.setPreferredSize(new Dimension(600, 600));
		mapa.setDisplayPosition(new Coordinate(-41, -64), 4);
		mapa.setZoomControlsVisible(true);
	}

	private void agregarMarcadoresProvincias() {
		for (Provincia provincia : generador.obtenerProvincias()) {
        	Coordinate coordenada = new Coordinate(provincia.obtenerLatitud(), provincia.obtenerLongitud());
			MapMarkerDot marker = new MapMarkerDot(provincia.obtenerNombre(), coordenada);
			marker.getStyle().setBackColor(new Color(255, 255, 153));
			Font font = new Font("Arial", Font.BOLD, 10);
		    marker.getStyle().setFont(font);
			mapa.addMapMarker(marker);
		}
	}

	public void verMapa() {
	    JFrame frame = crearFrame();
	    GridBagConstraints gbc = configurarConstraints();
	    
	    agregarTitulo(gbc);
	    crearCamposPesos(gbc);
	    agregarPanelesGeneradores(gbc);

	    JSplitPane splitPane = setearSplitPanel();
	    frame.getContentPane().add(splitPane, BorderLayout.CENTER);
	}

	private GridBagConstraints configurarConstraints() {
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    return gbc;
	}

	private void agregarTitulo(GridBagConstraints gbc) {
	    ImageIcon imagenTitulo = crearImagen("tituloIngresarIndices.png", 305, 50);
	    JLabel lblInstruccion = new JLabel(imagenTitulo);
	    gbc.gridwidth = 3;
	    panelEntradaUsuario.add(lblInstruccion, gbc);
	    gbc.gridwidth = 1;
	    gbc.gridy++;
	}
	
	private ImageIcon crearImagen(String pathImagen, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(pathImagen));
		Image originalImage = imageIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	private void crearCamposPesos(GridBagConstraints gbc) {
	    crearTextFieldParaPesos(gbc);
	}

	private void agregarPanelesGeneradores(GridBagConstraints gbc) {
	    setearBotonGrafoInicial(gbc);
	    setearBotonAGM(gbc);
	    setearPanelGeneradorDeRegiones(gbc);
	}

	private void crearPanelEntradaUsuario() {
		panelEntradaUsuario = new JPanel(new GridBagLayout());
	    panelEntradaUsuario.setBackground(new Color(170, 211, 223));
	}

	private JFrame crearFrame() {
		JFrame frame = new JFrame("Mapa de Argentina");
		frame.setResizable(false);
	    setearVentana(frame);
		return frame;
	}

	private void setearVentana(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(720, 576));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	private JSplitPane setearSplitPanel() {
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportView(panelEntradaUsuario);
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapa, scrollPane);
	    splitPane.setResizeWeight(0.2);
	    splitPane.setEnabled(false);
	    return splitPane;
	}

	private void crearTextFieldParaPesos(GridBagConstraints gbc) {
	    List<Tupla<Integer, Integer>> conexiones = generador.obtenerConexionesProvincias();
	    for (Tupla<Integer, Integer> arista : conexiones) {
	    	
	        int provinciaOrigen = arista.getPrimero();
	        int provinciaDestino = arista.getSegundo();
	        JLabel lblProvinciaOrigen = crearEtiquetaProvincia(provinciaOrigen);
	        JLabel lblProvinciaDestino = crearEtiquetaProvincia(provinciaDestino);
	        JTextField txtPeso = new JTextField(5);

	        configurarListenerCampoPeso(provinciaOrigen, provinciaDestino, txtPeso);
	        agregarComponentes(gbc, lblProvinciaOrigen, lblProvinciaDestino, txtPeso);
	    }
	}

	private JLabel crearEtiquetaProvincia(int provincia) {
	    JLabel lblProvincia = new JLabel(generador.obtenerProvinciaPorIndice(provincia).obtenerNombre() + " ");
	    lblProvincia.setForeground(Color.black);
	    return lblProvincia;
	}

	private void configurarListenerCampoPeso(int provinciaOrigen, int provinciaDestino, JTextField txtPeso) {
		txtPeso.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) { actualizarPeso(); }
		    @Override
		    public void removeUpdate(DocumentEvent e) { actualizarPeso(); }
		    @Override
		    public void changedUpdate(DocumentEvent e){ actualizarPeso(); }

		    private void actualizarPeso() {
		        String pesoTexto = txtPeso.getText();
		        if (!pesoTexto.isBlank() && esNumeroEntero(pesoTexto)) {
		            int peso = Integer.parseInt(pesoTexto);
		            generador.agregarArista(provinciaOrigen, provinciaDestino, peso);
		            agregarAristasInterfaz(generador.obtenerAristas());
		        }
		        if (pesoTexto.isBlank()) {
		        	generador.eliminarArista(provinciaOrigen, provinciaDestino);
		        	agregarAristasInterfaz(generador.obtenerAristas());
		        }
		    }
		});
	}
	
	private void agregarComponentes(GridBagConstraints gbc, JLabel lblProvinciaOrigen, JLabel lblProvinciaDestino, JTextField txtPeso) {
		panelEntradaUsuario.add(lblProvinciaOrigen, gbc);
		gbc.gridx=1;
		panelEntradaUsuario.add(lblProvinciaDestino, gbc);
		gbc.gridx=2;
		panelEntradaUsuario.add(txtPeso, gbc);
		gbc.gridy++;
		gbc.gridx=0;
	}

	private void agregarAristasInterfaz(ArrayList<Arista> aristas) {
		mapa.removeAllMapPolygons();
		for (Arista arista : aristas) {
			int provinciaOrigen = arista.obtenerVertice1();
			int provinciaDestino = arista.obtenerVertice2();
			Coordinate coordenadasOrigen = new Coordinate(generador.obtenerProvinciaPorIndice(provinciaOrigen).obtenerLatitud(), generador.obtenerProvinciaPorIndice(provinciaOrigen).obtenerLongitud());
			Coordinate coordenadasDestino = new Coordinate(generador.obtenerProvinciaPorIndice(provinciaDestino).obtenerLatitud(), generador.obtenerProvinciaPorIndice(provinciaDestino).obtenerLongitud());
			
			List<Coordinate> puntosArista = Arrays.asList(coordenadasOrigen, coordenadasDestino, coordenadasDestino);
			MapPolygonImpl aristaMapPolygon = new MapPolygonImpl(String.valueOf(arista.obtenerPeso()), puntosArista);
			aristaMapPolygon.setColor(Color.black);
			mapa.addMapPolygon(aristaMapPolygon);
		}
	}

	private void setearBotonGrafoInicial(GridBagConstraints gbc) {
		btnGenerarGrafoInicial = crearBoton("Mostrar Grafo Inicial");
		
		btnGenerarGrafoInicial.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (verificarCamposCompletos()==false) {
	                mostrarMensajeError("Por favor, complete todos los indices de similaridad.");
	                return; 
	            }
	            agregarAristasInterfaz(generador.obtenerAristas());
	        }
	    });
	    
		agregarComponente(btnGenerarGrafoInicial, gbc);
	}

	private void setearBotonAGM(GridBagConstraints gbc) {
	    btnGenerarAGM = crearBoton("Mostrar Árbol Generador Mínimo");
	    
	    btnGenerarAGM.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (verificarCamposCompletos()==false) {
	                mostrarMensajeError("Por favor, complete todos los indices de similaridad.");
	                return;
	            }
	            GeneradorGrafoArgentina generadorAux = generador.clonar();
	            generadorAux.obtenerAGM();
	            agregarAristasInterfaz(generadorAux.obtenerAristas());
	        }
	    });
	    
	    agregarComponente(btnGenerarAGM, gbc);
	}
	
	private JButton crearBoton(String texto) {
	    JButton boton = new JButton(texto);
	    boton.setBackground(new Color(255, 255, 153));
	    darDimensiones(boton);
	    return boton;
	}

	private void darDimensiones(JComponent componente) {
		Dimension dimension = new Dimension(305, 45);
	    componente.setPreferredSize(dimension);
	}

	private void agregarComponente(JComponent componente, GridBagConstraints gbc) {
	    gbc.gridwidth = 3;
	    panelEntradaUsuario.add(componente, gbc);
	    gbc.gridy++;
	}
	
	private void setearPanelGeneradorDeRegiones(GridBagConstraints gbc) {
	    JPanel panelRegiones = new JPanel(new GridBagLayout());
	    panelRegiones.setBackground(new Color(255, 255, 153));

	    btnGenerarRegiones = new JButton("Mostrar");
	    btnGenerarRegiones.setBackground(new Color(255, 255, 200));

	    JLabel lblCantidadRegiones = new JLabel("Cantidad de Regiones:");
	    JTextField txtCantidadRegiones = new JTextField(5);

	    btnGenerarRegiones.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (verificarCamposCompletos()==false) {
	                mostrarMensajeError("Por favor, complete todos los indices de similaridad.");
	                return; 
	            }
	            if (txtCantidadRegiones.getText().isBlank() || !esNumeroEntero(txtCantidadRegiones.getText())) {
	                mostrarMensajeError("Por favor, ingrese la cantidad de regiones.");
	                return; 
	            }
	            int cantidadRegiones = Integer.parseInt(txtCantidadRegiones.getText());
	            if (!generador.verificarCantidadDeRegionesValida(cantidadRegiones)) {
	                mostrarMensajeError("Por favor, ingrese una cantidad de regiones entre 1 y 24.");
	                return; 
	            }
	            GeneradorGrafoArgentina generadorAux = generador.clonar();
	            generadorAux.generarRegiones(cantidadRegiones);
	            agregarAristasInterfaz(generadorAux.obtenerAristas());
	        }
	    });

	    agregarComponentes(panelRegiones, lblCantidadRegiones, txtCantidadRegiones);
	    darDimensiones(panelRegiones);
	    agregarComponente(panelRegiones, gbc);
	}

	private void agregarComponentes(JPanel panelRegiones, JLabel lblCantidadRegiones, JTextField txtCantidadRegiones) {
		GridBagConstraints gbcPanelRegiones = configurarConstraints();
	    gbcPanelRegiones.gridx++;
	    panelRegiones.add(lblCantidadRegiones, gbcPanelRegiones);
	    gbcPanelRegiones.gridx++;
	    panelRegiones.add(txtCantidadRegiones, gbcPanelRegiones);
	    gbcPanelRegiones.gridx++;
	    panelRegiones.add(btnGenerarRegiones, gbcPanelRegiones);
	}

	private boolean verificarCamposCompletos() {
	    Component[] componentes = panelEntradaUsuario.getComponents();
	    for (Component componente : componentes) {
	        if (componente instanceof JTextField) {
	            JTextField campoTexto = (JTextField) componente;
	            if (campoTexto.getText().isBlank() || !esNumeroEntero(campoTexto.getText())) {
	                return false;
	            }
	        }
	    }
	    return true;
	}    

	private boolean esNumeroEntero(String texto) {
	    return texto.matches("-?\\d+");
	}
	
	private void mostrarMensajeError(String mensaje) {
	    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}

