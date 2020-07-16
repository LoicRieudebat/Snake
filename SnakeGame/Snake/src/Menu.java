import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu {

	public static void MenuSnake() throws IOException {
		
		Image backgroundImage = ImageIO.read(new File("img//FondSnake.png"));
		
		JFrame menu = new JFrame("Snake");
		JButton bPlay = new JButton("Play");
		JButton bQuit = new JButton("Quitter");
		JButton bControl = new JButton("Contrôle");
		JButton bAide = new JButton("?");
		bPlay.setBounds(120, 150, 150, 30);
		bPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Lancement();
			}
		});
		bControl.setBounds(120, 200, 150, 30);
		bControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Haut : ▲ \n" + 
													"Bas : ▼ \n" + 
													"Gauche : ◄ \n" + 
													"Droite : ► \n" +
													"Activer/Désactiver Pause : p");
			}
		});
		bQuit.setBounds(120, 250, 150, 30);
		bQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bAide.setBounds(320, 505, 45, 45);
		bAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Le joueur contrôle une longue et fine ligne semblable à un serpent, qui doit slalomer entre\n"
						+ "les bords de l'écran et les obstacles qui parsèment le niveau. Le joueur doit faire manger à son serpent un certain\n"
						+ "nombre de pastilles similaire à de la nourriture, allongeant à chaque\n"
						+ "fois la taille du serpent. Alors que le serpent avance inexorablement, le joueur\n"
						+ "ne peut que lui indiquer une direction à suivre (en haut, en bas, à gauche, à droite)\n"
						+ "afin d'éviter que la tête du serpent ne touche les murs ou son propre corps,\n"
						+ "auquel cas il risque de mourir.");
			}
		});
		menu.setContentPane(new JPanel() {
			@Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(backgroundImage, 0, 0, null);
	         }
	      });
		JLabel Titre = new JLabel("SNAKE GAME");
		Titre.setVisible(true);
		Titre.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,35));
		Titre.setBounds(70, 50, 500, 50);
		menu.add(bAide);
		menu.add(Titre);
		menu.add(bQuit);
		menu.add(bControl);
		menu.add(bPlay);
		menu.setResizable(false);
		menu.setSize(400, 600);
		menu.setLocationRelativeTo(null);
		menu.setLayout(null);
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
		MenuSnake();
		
	}
}