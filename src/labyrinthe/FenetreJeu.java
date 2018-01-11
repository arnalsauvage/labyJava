package labyrinthe;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreJeu extends JFrame
implements ActionListener{
	private static final long serialVersionUID = 1L;
	private PanneauJeu panneauJeu = new PanneauJeu();
	
	// 5 : rapide , 200 lent
	private int delaiAffichage = 5;  

	public FenetreJeu(){        
		this.setTitle("Animation labyrinthe");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panneauJeu);
		this.setVisible(true);
		addKeyListener(panneauJeu);
		go();
	}

	private void go(){
		while(true){
			//On redessine notre Panneau
			panneauJeu.repaint();

			try {
				Thread.sleep(delaiAffichage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}