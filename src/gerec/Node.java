package gerec;

public class Node {

	/******************************************************
	 ******************** PUBLIC **************************
	 ******************************************************/

	public Node(int x, int y, int speedX,int speedY, String role, int id)
	{
		this.x=x;
		this.y=y;
		this.speedX=speedX;
		this.speedY=speedY;
		this.role = role;
		this.id=id;
	}
	
	public int getSpeedX() {
		return speedX;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
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
	
	/******************************************************
	 ******************** PRIVATE *************************
	 ******************************************************/
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private String role;
	private int id;

}
