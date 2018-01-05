package labyrinthe;
import java.awt.Color;

public class personnage 
{
	position maPosition; // Coordonnées
	int valeur; // valeur ou peut évoluer le personnage
	int vx, vy; // vitesse x, y
	Grille maGrille; // Grille où évolue le personnage
	Color couleur; //Couleur du personnage
	int typePerso; // Type perso clavier, souris, robot...

	public   personnage (int x, int y, int laValeur, Grille laGrille){
		if ((x>=0)&&(y>=0))
		{
			if (laGrille.pointDansGrille(x, y)==true)
			{
				maPosition = new position(x,y);
				valeur = laValeur;
				vx = 0;
				vy = 0;
				this.maGrille = laGrille;
			}
		}
	}

	public int getX() {
		return maPosition.x;
	}
	public int getY() {
		return maPosition.y;
	}

	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getTypePerso() {
		return typePerso;
	}

	public void setTypePerso(int typePerso) {
		if (typePerso>0)
			this.typePerso = typePerso;
	}

	public void setCouleur(int r, int g, int b){
		couleur = new Color (r,g,b);	
	}

	public Color getRgb(){
		return couleur;	
	}

	public boolean collision(personnage Autreperso){
		if ((Autreperso.getX() == getX())&&(Autreperso.getY() == getY()))
			return true;
		else
			return false;
	}

	public void melangeCouleurs(personnage Autreperso){
		int r = ( 2*couleur.getRed() + Autreperso.couleur.getRed()) /3;
		int g = ( 2*couleur.getGreen() + Autreperso.couleur.getGreen()) /3;
		int b = ( 2*couleur.getBlue() + Autreperso.couleur.getBlue()) /3;	

		int r2 = ( couleur.getRed() + 2*Autreperso.couleur.getRed()) /3;
		int g2 = ( couleur.getGreen() + 2*Autreperso.couleur.getGreen()) /3;
		int b2 = ( couleur.getBlue() + 2*Autreperso.couleur.getBlue()) /3;	
		couleur = new Color (r,g,b);	
		Autreperso.couleur = new Color (r2,g2,b2);	
	}

	public boolean deplace (int mvx, int mvy)
	{
		mvx = mvx*vx + maPosition.getX();
		mvy = mvy*vy + maPosition.getY();

		// On vérifie que la nouvelle position sera dans la grille
		if (maGrille.pointDansGrille(mvx, mvy)==true){
			// On vérifie que le personnage arrive bien dans une case de sa valeur
			if (maGrille.getXY(mvx,mvy)==valeur)
			{
				maPosition.setX(mvx);
				maPosition.setY(mvy);
				return true;
			}
		}
		return false;		
	}
}