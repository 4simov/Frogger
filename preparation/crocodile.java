

class crocodile{
	int x;
	int y;
	int etat;
	
	public int getX(){
		return this.x;
	}
	public int getEtat(){
		return this.etat;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	
	public crocodile(int x,int y){
		this.x=x;
		this.y=y;
		this.etat=0;
	}
	
	public void changementEtat(){
		if(etat==1)
			etat=0;
		else
			etat=1;
	}
}
