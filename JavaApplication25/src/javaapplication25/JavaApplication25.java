
package javaapplication25;

import javax.swing.JFrame;
public class JavaApplication25 {

    public static void main(String[] args) {
      		JFrame window = new JFrame("Tile Map Editor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new MyPanel());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
    }
    
}
