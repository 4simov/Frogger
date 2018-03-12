





class carteentiere{
	
	Carte tab[]=new Carte[3];
	boolean coliR;
	int niveau;
	int compteur;
	
	public Carte[] getTab(){return this.tab;}
	public Carte getTabC(int couche){return this.tab[couche];}
	
	public synchronized boolean getColiR(){return this.coliR; }
	public synchronized void setColiR(boolean bool){this.coliR=bool; }
	
	public int getCompteur(){
		return this.compteur;
	}
	public void setCompteur(int c){
		this.compteur=c;
	}
	public void augmenterCompteur(int c){
		this.compteur=this.compteur+c;
	}
	
	public int getNiveau(){
		return this.niveau;
	}
	public void setNiveau(int n){
		this.niveau=n;
	}
	public void augmenterNiveau(int n){
		this.niveau=this.niveau+n;
	}
	
	void setTab(int x, int y, int couche, String val){
		this.tab[couche].getTab()[x][y]=val;
		coliR=false;
	}
	
	public carteentiere(Carte inter,Carte best,Carte gre){
		this.tab[0]=inter;
		this.tab[1]=best;
		this.tab[2]=gre;
		this.tab[2].getTab()[12][10]="4";
		this.niveau=1;
		this.compteur=0;
	}
	
	
		
	synchronized void Afficher(){
		System.out.println("  ------------------------------------------");
		for(int i=0; i<13 ; i++){
			System.out.print(" | ");
			for(int j=0 ; j<20 ; j++){
				if(this.tab[2].getTab()[i][j]!=" "){ 
					System.out.print(this.tab[2].getTabP(i,j)+" ");
				}
				else{
					if(this.tab[1].getTab()[i][j]!=" "){
						System.out.print(this.tab[1].getTabP(i,j)+" ");
	
					}
					else{
						System.out.print(this.tab[0].getTabP(i,j)+" ");
					}
				}
			}
			
			if(i==2){
				System.out.println(" | compteur : "+compteur);
			}
			else{
				if(i==4)
					System.out.println(" | niveau : "+niveau);
				else
					System.out.println(" | ");
			}
		}
		System.out.println("  ------------------------------------------");
			
	}
}
