import java.util.Random;

public class Grille {
	int x, y; // largeur et hauteur
	int tableau[][]; // tableau contenant des valeurs

	// Constructeur avec largeur et hauteur seulement
	public Grille (int x, int y){
		try {
			setDimensions (x,y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Grille (int x, int y, int tab[][]){
		try {
			setDimensions (x,y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int px = 0; px < x; px++){
			for(int py = 1; px < y; py++){
				try {
					tableau[px][py] = tab[x][y];
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setDimensions(int x, int y){
		if (( x > 1 ) && (y> 1))
		{
			this.x = x;
			this.y = y;
			tableau = new int [x][y];
		}
	}

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
		for(int px = x1; px < x2; px++){
			for(int py = y1; py < y2; py++){
				tableau[px][py] = valeur;
			}
		}

	}
	// Affichage de la grille en mode console pour tests
	public void afficheGrille(){

		for(int py = 0; py < y; py++){
			for(int px = 0; px < x; px++){
				System.out.print(tableau[px][py]);
			}
			System.out.println("");
		}
	}

	public int getXY(int mvx, int mvy) {
		// 
		if (pointDansGrille (mvx, mvy))
			return tableau[mvx][mvy];
		else
			return -1;
	}
	public boolean setXY(int mvx, int mvy, int valeur) {
		// 
		if (pointDansGrille (mvx, mvy)){
			tableau[mvx][mvy] = valeur;
			return true;
		}
		else
			return false;
	}
	// Le point passé en x,y est-il dans la grille ?
	public boolean pointDansGrille (int mx, int my){
		if (((mx>=0)&&(mx<x))&&(my>=0)&&(my<y))
			return true;
		else
			return false;
	}

	private int compteValeursN(int maVal){
		int nbval = 0;
		for(int py = 0; py < y; py++)
		{
			for(int px = 0; px < x; px++)
			{
				if (tableau[px][py]==maVal)
					nbval++;
			}
		}
		System.out.println("Valeurs possibles : "+nbval);
		return nbval;
	}

	public int[] elementAuHAsard(int maval){
		int nbval;
		int[] resultat = new int[2]; 
		nbval = compteValeursN(maval);

		if (nbval==0)
			return null;

		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(nbval ) + 1;
		nbval = 0;
		for(int py = 0; py < y; py++){
			for(int px = 0; px < x; px++){
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