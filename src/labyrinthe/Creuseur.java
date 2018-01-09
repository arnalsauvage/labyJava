package labyrinthe;
import java.util.ArrayList;
import java.util.Random;

public class Creuseur extends personnage{

	// Orientation du personnage (haut bas gauche ou droite)
	Orientation monOrientation;
	// Le creuseur creuse les valeurs = sa valeur de personnage, 
	// et les remplace par sa valeurCreusage
	int valeurCreusage;
	// Au fur et à mesure de ses déplacements, il mémorise les endroits où il peut encore creuser
	ArrayList<position> listeDesPossibles;
	
	// Indique l'orientation par défaut lors du creusage
	// Ceci devrait donner des labyrinthes plutôt verticaux ou horizontaux
	Random rand = new Random();

	public int getValeurCreusage() {
		return valeurCreusage;
	}

	public void setValeurCreusage(int valeurCreusage) {
		this.valeurCreusage = valeurCreusage;
	}

	public Creuseur(int x, int y, int valeur, int vc, Grille maGrille) {
		super(x, y, valeur, maGrille);
		monOrientation = new Orientation(0);
		vx = 2;
		vy = 2;
		valeurCreusage = vc;
		listeDesPossibles = new ArrayList<position>();
	}

	// Permet de positionner le creuseur dans la grille 
	public boolean poser (int px, int py)	{
		if (maGrille.pointDansGrille(px, py))
			//			if( maGrille.getXY(px,py)==valeur)
		{
			maPosition.setX(px);
			maPosition.setY(py);
			return true;
		}
		return false;
	}

	// Cette méthode compte le nombre de creusages potentiels à partir de la position actuelle
	private int comptePossibles(){
		int nbrePossibles = 0;
		
		// On va tester dans les 4 directions
		for (int direction = 1;  direction<5; direction++){
			monOrientation.pivote();
			
			// si on peut avancer de deux cases...
			if( ( regardeDevant(1) == valeur) &&  ( regardeDevant(2) == valeur))
			{
					nbrePossibles++;
			}
		}
		if (nbrePossibles>0){
			if(ajouteXYdansLaListeDesPossibles(maPosition)==true)
				System.out.println("Nb éléments :" + listeDesPossibles.size() + " ajouté " + maPosition.getX()+","+maPosition.getY());
			else 
				System.out.println("Nb éléments :" + listeDesPossibles.size() + " impossible d'ajouter " + maPosition.getX()+","+maPosition.getY());

		}
		else{
			if (enleveXYdeLaListeDesPossibles(maPosition.getX(), maPosition.getY())==true)
				System.out.println("Nb éléments :" + listeDesPossibles.size()+ " supprimé " + maPosition.getX()+","+maPosition.getY());
			else
				System.out.println("Nb éléments :" + listeDesPossibles.size()+ " Impossible de supprimer " + maPosition.getX()+","+maPosition.getY() + " !!!");
		}
		return nbrePossibles;
	}

	// Regarde la valeur de la case située n cases devant soi
	private int regardeDevant(int nbreCases) {
		int regardeDevant;
		int xchute = maPosition.getX() + nbreCases * monOrientation.getX();
		int ychute = maPosition.getY() + nbreCases * monOrientation.getY();
		regardeDevant = maGrille.getXY(xchute, ychute);
		return regardeDevant;
	}

	// Ajoute une position dans la liste des possibles
	private boolean ajouteXYdansLaListeDesPossibles(position positionTestee){
		int monIndex = listeDesPossibles.size();
		position unePosition;
		boolean trouve = false;
		for (int i = 0; i<monIndex;i++)
		{
			unePosition = (position) listeDesPossibles.get(i);
			if (positionTestee.compare(unePosition))
				trouve = true;
		}
		if (trouve==false){
			unePosition = new position(positionTestee.getX(),positionTestee.getY());
			listeDesPossibles.add(unePosition);
		}
		return (!trouve);
	}

	// Enlève une position de la liste des possibles
	private boolean enleveXYdeLaListeDesPossibles(int monx, int mony){
		int monIndex = listeDesPossibles.size();
		position unePosition;

		for (int i = 0; i<monIndex;i++){
			unePosition = (position) listeDesPossibles.get(i);
			if ((unePosition.x == monx) && (unePosition.y==mony))
			{
				listeDesPossibles.remove(i);
				return true;
			}
		}
		return false;
	}
	
	// Quand le creuseur avance, il laisse la valeur creusage et met à jour ses coordonnées
	private void avance() {
		maPosition.setX(maPosition.getX() + monOrientation.getX());
		maPosition.setY(maPosition.getY() + monOrientation.getY());
		maGrille.setXY(maPosition.getX(),maPosition.getY(),valeurCreusage);
	}
	
	// Décide d'une orientation par défaut pour orienter le style du labyrinthe
	private int orientationParDefaut(){
		int orientation = 0; 
		int coeff0 = this.maGrille.hauteur + this.getY() - (this.maGrille.hauteur/2); 
		int coeff1 = (3*this.maGrille.largeur/2) - this.getX() ;
		int coeff2 = (3*this.maGrille.hauteur/2) - this.getY() ; 
		int coeff3 = this.maGrille.largeur + this.getX() - (this.maGrille.largeur/2);
		coeff0 *= coeff0;
		coeff1 *= coeff1;
		coeff2 *= coeff2;
		coeff3 *= coeff3;
		
		int sommeCoeffs = coeff0 + coeff1 + coeff2 + coeff3;
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(sommeCoeffs );
		if (nombreAleatoire < coeff0)
			orientation = 0;
		else if (nombreAleatoire < coeff1+coeff0)
			orientation = 1;
		else if (nombreAleatoire < coeff1+coeff0+coeff2)
			orientation = 2;
		else 
			orientation = 3;
		return orientation;
	}
	
	// Opère une occurence du processus de creuser le labyrinthe
	public boolean creuser (){
		position unePosition;
		//		System.out.println("Valeur orientation début : "+monOrientation.direction);
		Random rand = new Random();
		// Si on ne peut pas creuser ici, on va creuser ailleurs
		while (comptePossibles()==0)
		{
			// Si la liste des possibles est vide, on ne peut plus creuser
			if (listeDesPossibles.size()==0)
				return false;
			int nombreAleatoire = rand.nextInt(listeDesPossibles.size() ) ;
			unePosition =  (position) listeDesPossibles.get(nombreAleatoire);
			poser(unePosition.x, unePosition.y);
		}
		
		// On prend une orientation au hasard
		int nombreAleatoire = rand.nextInt(4 ) + 1;
		monOrientation.setDirection(nombreAleatoire);
		monOrientation.setDirection(orientationParDefaut());

		// On essaiera de creuser dans les 4 directions
		for (int i = 1;  i<5; i++){		
			if( ( regardeDevant(1) == valeur) &&  ( regardeDevant(2) == valeur))
				{
					// On creuse la destination
					avance();
					avance();
					//					System.out.println("Valeur orientation fin : "+monOrientation.direction);
					return true;
				}
			monOrientation.pivote();
		}
		return false;
	}
}