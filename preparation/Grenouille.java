





class Grenouille{
	int x;
	int	y;
	boolean gagner;
	int score;
	int vie;
	
	carteentiere map;
	
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	public int getScore(){return this.score;}
	public int getVie(){return this.vie;}
	
	public void setX(int x){this.x=x; }
	public void setY(int y){this.y=y; }
	public void setScore(int s){this.score=s;}
	
	public void augmenterScore(int s){
		this.score=this.score+s;
	}
	
	
	public Grenouille(int x, int y,carteentiere m){
		this.x=x;
		this.y=y;
		this.gagner=false;
		this.map=m;
		this.score=0;
		this.vie=3;
	}
	
	public synchronized boolean collision(){
		if(map.getTabC(1).getTabP(y,x)=="1" || map.getTabC(1).getTabP(y,x)=="." || map.getTabC(1).getTabP(y,x)=="6"){
			vie=vie-1;
			return true;
		}
		else
			return false;
	}
	public boolean collisionR(){
		if(map.getTabC(1).getTabP(y,x)=="0" || map.getTabC(1).getTabP(y,x)=="9" || map.getTabC(1).getTabP(y,x)=="8" || map.getTabC(1).getTabP(y,x)=="7")
			return true;
		else
			return false;
	}
	public boolean collisionG(){
		if(map.getTabC(1).getTabP(y,x)=="G"){
			score=score+100;
			return true;
		}
		else
			return false;
	}
	
	
	public void Afficher(){
		System.out.println("  ------------------------------------------");
		System.out.println("  Score : "+score);
		System.out.println("  Vie : "+vie);
	}
}
