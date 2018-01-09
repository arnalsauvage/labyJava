package labyrinthe;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame
implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Panneau panneau = new Panneau();
	// 5 : rapide , 200 lent
	private int delaiAffichage = 5;  

	public Fenetre(){        
		this.setTitle("Animation labyrinthe");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panneau);
		this.setVisible(true);
		addKeyListener(panneau);
		go();
	}

	private void go(){
		while(true){
			//On redessine notre Panneau
			panneau.repaint();
			//Comme on dit : la pause s'impose ! Ici, 15 millièmes de seconde
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