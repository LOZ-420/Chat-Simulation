package com.example;
import javax.swing.SwingUtilities;

public class App {
	    public static void main(String[] args) throws Exception {
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
       }); 
    }
}
