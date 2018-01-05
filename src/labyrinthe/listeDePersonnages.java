package labyrinthe;
import java.util.ArrayList;

public class listeDePersonnages {

	ArrayList<fantome> listePersos;
	
	public listeDePersonnages (){
		listePersos = new ArrayList<fantome>();
	}
	
	public void ajoutePersonnage(fantome unPerso)
	{
		listePersos.add(unPerso);
	}
	
	public void enlevePersonnage(int index)
	{
		listePersos.remove(index);
	}
}
