public class Orientation {
	int direction;
	// 0 : haut, 1 : droite, 2 : bas, 3 : gauche
	
	public Orientation (int dir)
	{
		direction = dir %4;
	}
	public void pivote(){
		direction ++;
		if (direction == 4)
			direction = 0;
	}
	
	public void antiPivote(){
		direction --;
		if (direction == -1)
			direction = 3;
	}
	
	public void versLeHaut(){
		direction = 0;
	}

	public void versLeBas(){
		direction = 2;
	}

	public void aGauche(){
		direction = 3;
	}

	public void aDroite(){
		direction = 1;
	}
	
	public void demitour(){
		direction += 2;
		direction = direction %4;
	}
	
	public int getX(){
		if (direction==0||direction==2)
			return 0;
		if (direction==3)
			return -1;
//		if (direction==1)
		return 1;
	}
	
	public int getY(){
		if (direction==1||direction==3)
			return 0;
		if (direction==0)
			return -1;
//		if (direction==2)
		return 1;
	}
	public void setDirection(int newDir)
	{
		direction = newDir %4;
	}
}