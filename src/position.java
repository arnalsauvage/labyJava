public class position {
	int x;
	int y;

	public position (int monx, int mony){
		setX(monx);
		setY(mony);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean compare(position autrePosition)
	{
		if ((autrePosition.x == x )&&(autrePosition.y==y))
			return true;
		else
			return false;
	}
}