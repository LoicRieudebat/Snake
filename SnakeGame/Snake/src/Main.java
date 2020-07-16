
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final int TAILLE = 50;
	private Deque<Serpent> snake = new ArrayDeque<>();
	private Point pomme = new Point(0, 0);
	private Point mur = new Point(0, 0);
	private Random rand = new Random();
	private Image backgroundImage;

	private boolean grandis = false;
	private boolean perdu = false;
	private boolean pause = false;
	private boolean start = false;
	private boolean murPlace = false;

	private int[][] posMur = new int[10][2];
	private int distance = 0;
	private int direction = 39;
	private static JFrame frame = new JFrame("Snake");

	public static void Lancement() {
		Main panel = new Main();
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				panel.onKeyPressed(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		frame.setContentPane(panel);
		frame.setSize(667, 690);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Main() {
		createMur();
		createPomme();	
		snake.add(new Serpent(2, 6, 39));
		setBackground(Color.WHITE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!perdu) {
					repaint();
					try {
						Thread.sleep(1000/300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (pause) {
						while (pause)
							try {

								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					if (!start) {
						while (!start)
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
		}).start();
	}

	public void createPomme() {
		boolean positionAvailable;
		do {
			pomme.x = rand.nextInt(12);
			pomme.y = rand.nextInt(12);
			positionAvailable = true;
			for (Serpent p : snake) {
				if (p.x == pomme.x && p.y == pomme.y) {
					positionAvailable = false;
					break;
				}
			}
			for (int i = 0; i < posMur.length; i++) {
				if(pomme.x == posMur[i][0] && pomme.y == posMur[i][1]) {
					positionAvailable = false;
					break;
				}
				if(pomme.x == 0 && pomme.y == 12 ) {
					if (posMur[i][0]== 0 && posMur[i][1] == 11 || posMur[i][0]== 1 && posMur[i][1] == 12) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x == 0 && pomme.y == 0 ) {
					if (posMur[i][0]== 1 && posMur[i][1] == 0 || posMur[i][0]== 0 && posMur[i][1] == 1) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x == 12 && pomme.y == 12 ) {
					if (posMur[i][0]== 11 && posMur[i][1] == 12 || posMur[i][0]== 12 && posMur[i][1] == 11) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x == 12 && pomme.y == 0 ) {
					if (posMur[i][0]== 12 && posMur[i][1] == 1 || posMur[i][0]== 11 && posMur[i][1] == 0) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x == 0 && posMur[i][0] == 0) {
					if (posMur[i][1]== pomme.y + 1 && posMur[i][1]== pomme.y - 1) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x == 12 && posMur[i][0] == 12) {
					if (posMur[i][1]== pomme.y + 1 && posMur[i][1]== pomme.y - 1) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.y == 0 && posMur[i][1] == 0) {
					if (posMur[i][0]== pomme.x + 1 && posMur[i][0]== pomme.x - 1) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.y == 12 && posMur[i][1] == 12) {
					if (posMur[i][0]== pomme.x + 1 && posMur[i][0]== pomme.x - 1) {
						positionAvailable = false;
						break;
					}					
				}
				if(pomme.x + 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.x - 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.y + 1 == posMur[i][1] && pomme.x == posMur[i][0]) {
					positionAvailable = false;
					break;
				}
				if(pomme.x + 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.x - 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.y - 1 == posMur[i][1] && pomme.x == posMur[i][0]) {
					positionAvailable = false;
					break;
				}
				if(pomme.x + 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.y - 1 == posMur[i][1] && pomme.x == posMur[i][1] && 
						pomme.y + 1 == posMur[i][1] && pomme.x == posMur[i][0]) {
					positionAvailable = false;
					break;
				}
				if(pomme.x - 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.x - 1 == posMur[i][0] && pomme.y == posMur[i][1] && 
						pomme.y + 1 == posMur[i][1] && pomme.x == posMur[i][0]) {
					positionAvailable = false;
					break;
				}
			}
			
		} while (!positionAvailable);
	}

	public void createMur() {
		boolean positionAvailable;
		int compteur = -1;
		int murX;
		int murY;
		do {
			compteur++;
			mur.x = rand.nextInt(12);
			mur.y = rand.nextInt(12);
			murX = mur.x;
			murY = mur.y;
			for (int j = 0; j < posMur[compteur].length; j++) {
				posMur[compteur][0] = murX;
				posMur[compteur][1] = murY;
			}
			positionAvailable = true;			
			if(murX== 1 && murY == 6) {
				positionAvailable = false;				
			}
			if(murX == 2 && murY == 6) {
				positionAvailable = false;				
			}
			if (murX == 3 && murY == 6) {
				positionAvailable = false;				
			}
			if (murX == 4 && murY == 6) {	
				positionAvailable = false;				
			}
			if (!positionAvailable) {
				compteur--;
			}
			
		} while (compteur != 9);
		murPlace = true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			backgroundImage = ImageIO.read(new File("img//fond2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pause) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", 90, 90));
			g.drawString("PAUSE", 13 * 50 / 2 - g.getFontMetrics().stringWidth("PAUSE") / 2, 13 * 50 / 2);
		}

		if (perdu) {
			JOptionPane.showMessageDialog(null, "Fin de Partie \n" + "Score : " + (snake.size() - 1));
			frame.dispose();
		}
		if (!pause) {
			g.drawImage(backgroundImage, 0, 0, this);
			distance += 5;
			Serpent head = null;
			if (distance == TAILLE) {
				distance = 0;

				try {
					head = (Serpent) snake.getFirst().clone();
					head.move();
					head.direction = direction;
					snake.addFirst(head);
					if (head.x == pomme.x && head.y == pomme.y) {
						grandis = true;
						createPomme();
					}
					if (!grandis)
						snake.pollLast();
					else
						grandis = false;
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			g.setColor(Color.red);
			g.fillOval(pomme.x * TAILLE + TAILLE / 4, pomme.y * TAILLE + TAILLE / 4, TAILLE / 2, TAILLE / 2);

			g.setColor(Color.decode("#12310A"));
			for (Serpent p : snake) {
				if (distance == 0) {
					if (p != head) {
						if (p.x == head.x && p.y == head.y) {
							perdu = true;
						}

					}
				}
				if (p.direction == 37 || p.direction == 39) {
					g.fillOval(p.x * TAILLE + ((p.direction == 37) ? -distance : distance), p.y * TAILLE, TAILLE, TAILLE);
				} else {
					g.fillOval(p.x * TAILLE, p.y * TAILLE + ((p.direction == 38) ? -distance : distance), TAILLE, TAILLE);
				}
			}
			for (int i = 0; i < posMur.length; i++) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(posMur[i][0] * TAILLE, posMur[i][1] * TAILLE, TAILLE, TAILLE);
			}
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 30, 30));
			g.drawString("Score : " + (snake.size() - 1), 10, 30);
			if ((snake.size() - 1) == 12*12-10) {
				JOptionPane.showMessageDialog(null, "FELICITATION vous avez gagné ! \n" + "Score : " + (snake.size() - 1));
				frame.dispose();
			}
		}

	}

	public void onKeyPressed(int keyCode) {
		if (keyCode >= 37 && keyCode <= 40 && !pause) {
			if (keyCode == 39) {
				start = true;
			}
			if (Math.abs(keyCode - direction) != 2) {
				direction = keyCode;
			}
		}
		if (keyCode == 80 && !perdu) {
			pause();
		}
	}

	public boolean pause() {
		if (!pause) {
			pause = true;
			setBackground(Color.DARK_GRAY);
		} else {
			pause = false;
			setBackground(Color.WHITE);
		}
		return pause;
	}

	class Serpent {

		public int x, y, direction;

		public Serpent(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public void move() {		
			if (direction == 37 || direction == 39) {
				x += (direction == 37) ? -1 : 1;
				if (x > 12)
					perdu = true;
				else if (x < 0)
					perdu = true;
			} else {
				y += (direction == 38) ? -1 : 1;
				if (y > 12)
					perdu = true;
				else if (y < 0)
					perdu = true;
			}
			for (int i = 0; i < posMur.length; i++) {
				if(x == posMur[i][0] && y == posMur[i][1]) {
					perdu = true;
				}
			}
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new Serpent(x, y, direction);
		}
	}

}