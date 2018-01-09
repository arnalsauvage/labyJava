package labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Panneau
extends JPanel
implements KeyListener {
	private static final long serialVersionUID = 1L;
	Grille maGrille;
	personnage pacman;
	Creuseur monCreuseur;
	fantome pinky;
	fantome lefty;
	boolean creuser = false;
	boolean game_on = false;
	listeDePersonnages listePersos;
	int largeurJeu = 53;
	int hauteurJeu = 31;
	
	public Panneau(){
		// Il faut des valeurs impaires ! 53 x 31 est pas mal

		if (largeurJeu%2==0)
			largeurJeu++;
		if (hauteurJeu%2==0)
			hauteurJeu++;
		maGrille = new Grille(largeurJeu,hauteurJeu);
		maGrille.remplirGrille(1, 1, largeurJeu-1, hauteurJeu-1, 0);
		
		listePersos = new listeDePersonnages();
		
		pacman = new personnage( 1, 1, 1, maGrille);
		// Le creuseur se déplace sur du 0 et creuse du 1
		monCreuseur = new Creuseur (1, 1, 0, 1, maGrille);
		maGrille.setXY(1, 1, 1);
		pinky = new fantome(largeurJeu-4,hauteurJeu-4,1,maGrille,15,1);
		lefty = new fantome(largeurJeu-4,hauteurJeu-4,1,maGrille,25,2);
		
		pinky.setCouleur(0, 255, 0);
		lefty.setCouleur(0,0,255);
		pacman.setCouleur(255,0,0);
		
		listePersos.ajoutePersonnage(pinky);
		listePersos.ajoutePersonnage(lefty);
		
		requestFocusInWindow();	
		addKeyListener(this);
	}

	public void paintComponent(Graphics g){
		int maTaillex, maTailley;
		int x,y;

		x = maGrille.getX();
		y = maGrille.getY();
		// On calcule la taille d'un bloc selon la taille de la fenêtre
		maTaillex = maGrille.getX();
		maTaillex =  this.getWidth() / maTaillex;
		maTailley = maGrille.getY();
		maTailley =  this.getHeight() / maTailley;
		// Si le creuseur est en train de creuser
		if (creuser){
			monCreuseur.creuser();
			if (monCreuseur.listeDesPossibles.size()==0)
			{
				creuser = false;
				g.setColor(Color.orange);
				g.fillOval(monCreuseur.getX()*maTaillex, monCreuseur.getY()*maTaillex, maTaillex, maTailley);
			}
		}
		
		dessineLaby(g, maTaillex, maTailley, x, y);

		if (game_on){
			// On affiche le joueur
			g.setColor(pacman.getRgb());
			g.fillOval(pacman.getX()*maTaillex, pacman.getY()*maTailley, maTaillex, maTailley);

			// On affiche le fantome
			for (fantome pinky : listePersos.listePersos){
				
				// Gestion des fantomes
				if ((game_on) && pinky.tourDeJeu() && (pinky.getTypePerso()==1)){
					pinky.parcoursMainDroite();
					if (pinky.collision(pacman)){
						pinky.melangeCouleurs(pacman);
					}
				}
				if ((game_on) && pinky.tourDeJeu() && (pinky.getTypePerso()==2)){
					pinky.parcoursMainDroite();
					if (pinky.collision(pacman)){
						pinky.melangeCouleurs(pacman);
					}
				}
				afficheFantome(g, maTaillex, maTailley, pinky);
			}
		}
		if (creuser){
			g.setColor(Color.BLUE);
			g.fillOval(monCreuseur.getX()*maTaillex, monCreuseur.getY()*maTailley, maTaillex, maTailley);
		}
	}

	private void afficheFantome(Graphics g, int maTaillex, int maTailley,fantome ghost) {
		g.setColor(ghost.getRgb());
		g.fillOval(ghost.getX()*maTaillex, ghost.getY()*maTailley, maTaillex, maTailley);
	}

	private void dessineLaby(Graphics g, int maTaillex, int maTailley, int x, int y) {
		int maValeur;
		for(int py = 0; py < y; py++){
			for(int px = 0; px < x; px++){
				maValeur = maGrille.getXY(px, py);
				//			System.out.println("mavaleur " + maValeur);
				switch (maValeur){
				case 0 : g.setColor(Color.BLACK);break;
				case 1 : g.setColor(Color.WHITE);break;
				case 2 : g.setColor(Color.RED);break;
				case 3 : g.setColor(Color.BLUE);break;
				case 4 : g.setColor(Color.GREEN);break;
				}
				g.fillOval(px*maTaillex, py*maTailley, maTaillex, maTailley);
			}
		}
	}

	public void keyPressed(KeyEvent e)
	{
		int vx = 0;
		int vy = 0;
		fantome unPerso;
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
			vy=-1;
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			vy=1;
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
			vx=-1;
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			vx+=1;
		pacman.setVx(1);
		pacman.setVy(1);
		pacman.deplace(vx, vy);

		if(e.getKeyCode()==KeyEvent.VK_F1)
			game_on = false;
			creuser = true;
		if(e.getKeyCode()==KeyEvent.VK_F2)
			game_on = !game_on;
		if(e.getKeyCode()==KeyEvent.VK_F3){
			unPerso = new fantome(maGrille,1,15,1);
			listePersos.ajoutePersonnage(unPerso);
		}
		if(e.getKeyCode()==KeyEvent.VK_F4){
			unPerso = new fantome(maGrille,1,15,2);
			listePersos.ajoutePersonnage(unPerso);
		}
		if(e.getKeyCode()==KeyEvent.VK_F12){
			game_on = false;
			maGrille.remplirGrille(1, 1, largeurJeu-1, hauteurJeu-1, 0);
			maGrille.setXY(1, 1, 1);
			monCreuseur = new Creuseur (1, 1, 0, 1, maGrille);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}