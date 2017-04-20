import java.util.ArrayList;
import java.util.Random;

public class Creuseur extends personnage{

	Orientation monOrientation;
	int valeurCreusage;
	ArrayList<position> listeDesPossibles;

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
		// TODO Auto-generated constructor stub
	}

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
		int xchute, ychute;
		position laPosition;
		laPosition = new position(maPosition.getX(),maPosition.getY());
		for (int i = 1;  i<5; i++){
			monOrientation.pivote();
			xchute = maPosition.getX() +  monOrientation.getX();
			ychute = maPosition.getY() +  monOrientation.getY();
			if( maGrille.getXY(xchute, ychute)==valeur)
			{
				xchute = maPosition.getX() + 2 * monOrientation.getX();
				ychute = maPosition.getY() + 2 * monOrientation.getY();
				if( maGrille.getXY(xchute, ychute)==valeur){
					nbrePossibles++;
				}
			}
		}
		if (nbrePossibles>0){
			if(ajouteXYdansLaListeDesPossibles(laPosition)==true)
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

	public boolean creuser (){
		int xchute;
		int ychute;
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

		// On essaiera de creuser dans les 4 directions
		for (int i = 1;  i<5; i++){		
			xchute = maPosition.getX() +  monOrientation.getX();
			ychute = maPosition.getY() +  monOrientation.getY();
			if( maGrille.getXY(xchute, ychute)==valeur)
			{
				xchute = maPosition.getX() + 2 * monOrientation.getX();
				ychute = maPosition.getY() + 2 * monOrientation.getY();
				if( maGrille.getXY(xchute, ychute)==valeur)
				{
					// On creuse la destination
					maGrille.setXY(maPosition.getX(),maPosition.getY(),valeurCreusage);
					maPosition.setX(maPosition.getX() + monOrientation.getX());
					maPosition.setY(maPosition.getY() + monOrientation.getY());
					maGrille.setXY(maPosition.getX(),maPosition.getY(),valeurCreusage);
					maPosition.setX(maPosition.getX() + monOrientation.getX());
					maPosition.setY(maPosition.getY() + monOrientation.getY());
					maGrille.setXY(maPosition.getX(),maPosition.getY(),valeurCreusage);

					// on met à jour le champ des possibles
					//					comptePossibles();

					//					System.out.println("Valeur orientation fin : "+monOrientation.direction);
					return true;
				}
			}
			monOrientation.pivote();
		}
		return false;
	}
}