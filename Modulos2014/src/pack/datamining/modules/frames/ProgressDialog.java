package pack.datamining.modules.frames;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import java.awt.Color;
/*
 * Este fichero forma parte del Cliente @firma.
 * El Cliente @firma es un applet de libre distribucion cuyo codigo fuente puede ser consultado
 * y descargado desde www.ctt.map.es.
 * Copyright 2009,2010 Ministerio de la Presidencia, Gobierno de Espana
 * Este fichero se distribuye bajo licencia GPL version 3 segun las
 * condiciones que figuran en el fichero 'licence' que se acompana.  Si se   distribuyera este
 * fichero individualmente, deben incluirse aqui las condiciones expresadas alli.
 */
public class ProgressDialog {
	
	/**
	 * Diálogo con barra de progreso.
	 */


	    /** Componente padre del Diálogo. */ 
	    private JDialog parent = null;
	    
	    /** Barra de progreso. */
	    private JProgressBar progressBar = null;
	    
	    /** Texto que se muestra sobre la barra de progreso. */
	    private JLabel ltexto = null;
	    
	    /** Diálogo con la barra de progreso. */
	    private JDialog dialog = null;
	    
	    /** valor actual de la barra de progreso. */
	    private int currentValue = 0;
	    
	    /**
	     * Crea un Diálogo pero no lo muestra.
	     * @param parent Componente padre sobre el que se mostrara el Diálogo.
	     * @param maxValue Valor m&aacute;ximo de la barra de progres (maxValue > 0).
	     * @param title Título del Diálogo.
	     */
	    public ProgressDialog(JDialog parent, int maxValue, String title) {
	        
	        if(maxValue <= 0)
	            throw new IllegalArgumentException("El valor maximo de la barra de progreso no puede ser nulo"); 
	         
	        // Barra de progreso
	        this.parent = parent; 
	        this.progressBar = new JProgressBar(0, maxValue);
	        progressBar.setForeground(Color.BLUE);
	        progressBar.setBackground(Color.LIGHT_GRAY);
	        this.ltexto = new JLabel("Progreso"); 
	        
	    }

	    /** Muestra el Diálogo con al barra de progreso. */
	    public void show() {
	        Container panel = new Container();
	        this.progressBar.setValue(0);
	        this.progressBar.setStringPainted(true);
	        panel.setLayout(null);
	        panel.add(this.ltexto);
	        this.ltexto.setBounds(20, 15, 250, 20);
	        panel.add(this.progressBar);
	        this.progressBar.setBounds(20, 35, 260, 19);
	        this.ltexto.setFocusable(true);
	        this.progressBar.setFocusable(true);

	        this.dialog = new JDialog(this.parent, "Titulo", false); //
	        this.dialog.setBounds(this.parent.getX()+(this.parent.getWidth()-300)/2, this.parent.getY()+(this.parent.getHeight()-100)/2, 300, 100);
	        this.dialog.setResizable(false);
	        this.dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        this.dialog.getContentPane().add(panel); //Se añade el panel al diálogo
	        this.dialog.setVisible(true);
	    }
	    
	    /** Cierra el Diálogo. */
	    public void close() {
	        if (this.dialog != null)
	            this.dialog.setVisible(false);
	    }
	    
	    /**
	     * Aumenta en una unidad el valor de la barra de progreso e indica estar procesando el elemento indicado.
	     * @param elementName Nombre del elemento que se esta procesando.
	     */
	    public synchronized void processElement(String elementName) {
	        if(elementName == null) 
	            this.ltexto.setText("Procesando"); //
	        else 
	            this.ltexto.setText("Procesando el elemento, "+ elementName); 	        
	        this.progressBar.setValue(++this.currentValue);
	    }

}
