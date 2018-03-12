

class tortues{
	int etat; 
	// 4 etats possibles
	int descente;
	int x;
	int y;
	
	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x=x;
	}
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y=y;
	}
	public String getEtat(){
		if(this.etat==9)
			return "9";
		if(this.etat==8)
			return "8";
		if(this.etat==7)
			return "7";
		if(this.etat==6)
			return "6";
		return "0";
	}
	
	public void setEtat(int et){
		this.etat=et;
	}
	
	public int getDescente(){
		return this.descente;
	}
	
	public void setDescente(int d){
		this.descente=d;
	}
	
	public void Changement(){
		
		if(descente==1 && etat!=6){
			this.etat--;
			if(this.etat==6){
				this.descente=0;
			}
		}
		else{
			this.etat++;
			if(this.etat==9){
				this.descente=1;
			}
		}
		if(this.x==0){
			this.x=19;
		}
		else{
			this.x--;
		}
	}
	
	public tortues(int x){
		this.etat=9;
		this.descente=1;
		this.x=x;
	}
}
