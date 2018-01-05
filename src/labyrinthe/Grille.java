package labyrinthe;

import java.util.Random;

public class Grille {
	int largeur, hauteur; // largeur et hauteur
	int tableau[][]; // tableau contenant des valeurs

	// Constructeur avec largeur et hauteur seulement
	public Grille (int largeur, int hauteur){
		try {
			setDimensions ( largeur, hauteur);
		} catch (Exception e) {
			System.err.println("Erreur dans le constructeur Grille int int #1");
			e.printStackTrace();
		}
	}

	// Constructeur avec largeur, hauteur et tableau
	public Grille (int largeur, int hauteur, int tab[][]){
		try {
			setDimensions (largeur,hauteur);
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int px = 0; px < largeur - 1; px++){
				for(int py = 0; py < hauteur - 1; py++){
					try{
						tableau[px][py] = tab[px][py];
					}
					catch (Exception e) {
						System.err.println("px : " + px + " py : " + py);
						e.printStackTrace();
					}
				}
			}
		}
	
	// Getters & setters

		public int getX() {
			return largeur;
		}

		public int getY() {
			return hauteur;
		}

		public void setDimensions(int x, int y){
			if (( x > 1 ) && (y> 1))
			{
				this.largeur = x;
				this.hauteur = y;
				tableau = new int [x][y];
			}
		}

		// Remplir une plage de la grille avec une valeur donnée
		public void remplirGrille(int x1,int y1,int x2,int y2, int valeur)
		{
			// On s'assure que la borne x1 sera à gauche de la borne x2 ou on les inverse
			if (x1 > x2){
				int x3 = x1;
				x1 = x2;
				x2 = x3;
			}
			// On s'assure que la borne y1 sera dessous la borne y2 ou on les inverse
			if (y1 > y2){
				int y3 = y1;
				y1 = y2;
				y2 = y3;
			}
			for(int px = x1; px <= x2; px++){
				for(int py = y1; py <= y2; py++){
					tableau[px][py] = valeur;
				}
			}

		}
		
		// Affichage de la grille en mode console pour tests
		public void afficheGrille(){

			for(int py = 0; py < hauteur; py++){
				for(int px = 0; px < largeur; px++){
					System.out.print(tableau[px][py]);
				}
				System.out.println("");
			}
		}

		// renvoie la valeur du pont x, y dans la grille
		public int getXY(int x, int y) {
			// 
			if (pointDansGrille (x, y))
				return tableau[x][y];
			else
				return -1;
		}
		
		// initialise la valeur dans la grille (x,y)
		public boolean setXY(int mvx, int mvy, int valeur) {
			if (pointDansGrille (mvx, mvy)){
				tableau[mvx][mvy] = valeur;
				return true;
			}
			else
				return false;
		}
		
		// Le point passé en x,y est-il dans la grille ?
		public boolean pointDansGrille (int mx, int my){
			if (((mx>=0)&&(mx<largeur))&&(my>=0)&&(my<hauteur))
				return true;
			else
				return false;
		}

		// compte le nombre de valeurs à maVal dans la grille
		private int compteValeursN(int maVal){
			int nbval = 0;
			for(int py = 0; py < hauteur; py++)
			{
				for(int px = 0; px < largeur; px++)
				{
					if (tableau[px][py]==maVal)
						nbval++;
				}
			}
			System.out.println("Valeurs possibles : "+nbval);
			return nbval;
		}

		// Renvoie les coordonnées x, y d'un élément au hasard de valeur maVal dans la grille
		public int[] elementAuHAsard(int maval){
			int nbval;
			int[] resultat = new int[2]; 
			// On compte combien de valeurs maval dans la Grille
			nbval = compteValeursN(maval);

			// On renvoie null si aucune n'est trouvée
			if (nbval==0)
				return null;

			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(nbval ) + 1;
			nbval = 0;
			for(int py = 0; py < hauteur; py++){
				for(int px = 0; px < largeur; px++){
					if (tableau[px][py]==maval){
						nbval++;
						if (nbval==nombreAleatoire){
							resultat[0]= px;
							resultat[1] = py;
							return resultat;
						}
					}
				}
			}
			return null;
		}
	}