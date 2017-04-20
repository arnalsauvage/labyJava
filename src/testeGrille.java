public class testeGrille {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grille maGrille;
		maGrille = new Grille(50,20);
		maGrille.afficheGrille();
		System.out.println("");
		
		maGrille.remplirGrille(1, 19, 45, 1, 1);
		maGrille.afficheGrille();
		System.out.println("");		
		
		maGrille.remplirGrille(20, 5, 10, 15,2);
		maGrille.afficheGrille();
		System.out.println("");	
	}
}
