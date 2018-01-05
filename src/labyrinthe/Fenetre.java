package labyrinthe;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame
implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();

	public Fenetre(){        
		this.setTitle("Animation labyrinthe");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
		addKeyListener(pan);
		go();
	}

	private void go(){
		//Dans cet exemple, j'utilise une boucle while
		//Vous verrez qu'elle fonctionne très bien
		while(true){
			//On redessine notre Panneau
			pan.repaint();
			//Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
			try {
				Thread.sleep(10);
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