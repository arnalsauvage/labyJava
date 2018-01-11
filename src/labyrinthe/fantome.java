package labyrinthe;
import java.util.Random;

public class fantome extends personnage {

	Orientation monOrientation;
	int sommeil; // Définit le nombre de frames inactives entre deux déplacements
	int reveilDans; // Définit le nombre de frames restant avant le réveil

	public fantome(int x, int y, int laValeur, Grille laGrille, int vSommeil, int typePerso) {
		super(x, y, laValeur, laGrille);
		monOrientation = new Orientation(0);
		sommeil = vSommeil;
		reveilDans = sommeil;
		this.setTypePerso( typePerso);
	}

	public fantome(Grille laGrille, int laValeur, int vSommeil, int typePerso) {
		super(1, 1, laValeur, laGrille);
		Random Rand = new Random();
		int x, y;
		x = Rand.nextInt(laGrille.largeur/2);
		x = 2*x +1;

		y = Rand.nextInt(laGrille.hauteur/2);
		y = 2*y +1;

		this.getMaPosition().setX(x);
		this.getMaPosition().setY(y);

		int z;
		x = Rand.nextInt(255);
		y = Rand.nextInt(255);
		z = Rand.nextInt(255);
		setCouleur(x,y,z);

		monOrientation = new Orientation(0);
		sommeil = vSommeil;
		reveilDans = sommeil;
		this.setTypePerso(typePerso);
	}

	public void parcoursMainDroite(){
		monOrientation.pivote();
		while (!avance())
			monOrientation.antiPivote();
	}
	public void parcoursMainGauche(){
		monOrientation.antiPivote();
		while (!avance())
			monOrientation.pivote();
	}

	public boolean avance(){
		int xchute, ychute;
		xchute = getMaPosition().getX() +  monOrientation.getX();
		ychute = getMaPosition().getY() +  monOrientation.getY();
		if( maGrille.getValeur(xchute, ychute)==getValeur()){
			maPosition.setX(xchute);
			maPosition.setY(ychute);
			return true;
		}
		else
			return false;
	}

	public boolean tourDeJeu()
	{
		reveilDans--;
		if (reveilDans==0){
			reveilDans = sommeil;
			return true;
		}
		else
			return false;
	}
}