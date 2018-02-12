import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;



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






class Grenouille{
	int x;
	int	y;
	
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }
	
	public void setX(int x){this.x=x; }
	public void setY(int y){this.y=y; }
	
	public Grenouille(int x, int y){
		this.x=x;
		this.y=y;
	}
}

	



class Carte{
	 String[][] tab;
	
	String[][] getTab(){ return this.tab; }
	
	String getTabP(int x, int y){ return this.tab[x][y]; }
	
	synchronized void setTab(int i,int j,String val){
		tab[i][j]=val;
	}
	
	public Carte(){
		this.tab=new String[13][20];
		
		for(int i=0; i<13; i++){
			for(int j=0;j<20;j++){
				tab[i][j]=" ";
			}
		}
		
		
	}
	
	
	public void initCarte(){
		
		//initialisation des voitures 
		for(int i=7; i<12; i++){
			for(int j=0;j<20;j++){
				if(i==7){
					if(j==1 || j==2 || j==3 || j==4 || j==13 || j==14 || j==15 || j==16){
						tab[i][j]="1";
					}
				}
				if(i==8){
					if(j==3 || j==4 || j==5 || j==16 || j==17 || j==18){
						tab[i][j]="1";
					}
				}
				if(i==9){
					if(j==2 || j==3 || j==9 || j==10 /*|| j==16 || j==17*/){
						tab[i][j]="1";
					}
				}
				if(i==10){
					if(j==2 || j==3 /*|| j==7 || j==8 */|| j==16 || j==17){
						tab[i][j]="1";
					}
				}
				if(i==11){
					if(j==2 || j==3 || j==4 /*|| j==15 || j==16 || j==17*/){
						tab[i][j]="1";
					}
				}
				
			}
		}
		
		//initialisation des rondins de bois
		
		
		for(int i=1; i<6; i++){
			for(int j=0;j<20;j++){
				
				if(i==1){
					if(j==1 || j==2 || j==3 || j==4 || j==13 || j==14 || j==15 || j==16){
						tab[i][j]="0";
					}
					else
						tab[i][j]=".";
				}
				
				if(i==2){
					if(j==3 || j==4 || j==9 || j==10 || j==16 || j==17){
						if(j==3 || j==4){ 
							tab[i][j]="9";
						}
						else{
							tab[i][j]="0";
						}
					}
					else
						tab[i][j]=".";
				}
				
				if(i==3){
					if(j==2 || j==3 || j==4 || j==5 || j==6 || j==7 || j==11 || j==12|| j==13 || j==14 || j==15 || j==16 ){
						tab[i][j]="0";
					}
					else
						tab[i][j]=".";
				}
				if(i==4){
					if(j==2 || j==3 || j==4 || j==10 || j==11 || j==12 || j==16 || j==17 || j==18){
						tab[i][j]="0";
					}
					else
						tab[i][j]=".";
				}
				if(i==5){
					if(j==2 || j==3 || j==4 || j==10 || j==11 || j==12 || j==16 || j==17 || j==18){
						if(j==10 || j==11 || j==12){
							tab[i][j]="9";
						}
						else{
							tab[i][j]="0";
						}
					}
					else
						tab[i][j]=".";
				}
				
				
			}
		}
		
	}
	
	public void update(int ligne,String gauche){
		String retient;
		if(gauche=="gauche"){
			retient=tab[ligne][19];
			for(int j=19;j>0;j--){
				tab[ligne][j]=tab[ligne][j-1];
			}
			tab[ligne][0]=retient;
		}
		if(gauche=="droite"){
			retient=tab[ligne][0];
			for(int j=1;j<20;j++){
				tab[ligne][j-1]=tab[ligne][j];
			}
			tab[ligne][19]=retient;
		}
	}
	
	synchronized boolean collision(int x, int y, String val){
		
		return((tab[x][y]=="1" && val=="4") || (tab[x][y]=="4" && val=="1"));	
	}
	
	
	synchronized void Afficher(){
		System.out.println("  ------------------------------------------");
		for(int i=0;i<13;i++){
			System.out.print(" | ");
			for(int j=0;j<20;j++){
				System.out.print(tab[i][j]+" ");
			}
			System.out.println(" | ");
		}
		System.out.println("  ------------------------------------------");
	}
	
	
	void effacer(){
		for(int i=0; i<13; i++){
			for(int j=0;j<20;j++){
				tab[i][j]=" ";
			}
		}
	}
}


class carteentiere{
	Carte tab[]=new Carte[3];
	boolean coliR;
	public Carte[] getTab(){return this.tab;}
	public Carte getTabC(int couche){return this.tab[couche];}
	
	public synchronized boolean getColiR(){return this.coliR; }
	public synchronized void setColiR(boolean bool){this.coliR=bool; }
	
	
	void setTab(int x, int y, int couche, String val){
		this.tab[couche].getTab()[x][y]=val;
		coliR=false;
	}
	
	public carteentiere(Carte inter,Carte best,Carte gre){
		this.tab[0]=inter;
		this.tab[1]=best;
		this.tab[2]=gre;
		this.tab[2].getTab()[12][10]="4";
		
	}
	
	boolean collision(){
		
		for(int i=0; i< 13 ; i++){
			for (int j=0 ; j< 20 ; j++ ) {
				if((this.tab[1].getTabP(i,j)=="1" || this.tab[1].getTabP(i,j)=="." || this.tab[1].getTabP(i,j)=="6") && this.tab[2].getTabP(i,j)=="4") return true;
			}
		}
		return false;
		
	}
	synchronized boolean collisionR(){
		
		for(int i=0; i< 13 ; i++){
			for (int j=0 ; j< 20 ; j++ ) {
				if((this.tab[1].getTabP(i,j)=="0" || this.tab[1].getTabP(i,j)=="9"
					|| this.tab[1].getTabP(i,j)=="8" || this.tab[1].getTabP(i,j)=="7")
					&& this.tab[2].getTabP(i,j)=="4") return true;
			}
		}
		return false;
		
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
			System.out.println(" | ");
		}
		System.out.println("  ------------------------------------------");
			
	}
}








public class projet extends JFrame {	
	
	private static final long serialVersionUID = -5222658361778310082L;
	
	carteentiere map;
	Grenouille gre;
	Animation ani;
	
    private projet(carteentiere c,Grenouille g) {
        super("projet");
         
         this.gre=g;
         this.map=c;
         
         Animation ani =new Animation(map,gre);			
	ani.start();
         
        JLabel label = new JLabel("");
        add(label, BorderLayout.CENTER); 
        // ajoute un écouteur d'événements personnalisé à la fenêtre
        addKeyListener(new TitreKeyListener(label,map,gre));
         
        // réglage des dimensions de la fenêtre
       
        pack();
    }
	
	@Override
    public void paint(Graphics g) {
    	Image vertf=Toolkit.getDefaultToolkit().getImage("vertf.jpg") ;
    	Image marron=Toolkit.getDefaultToolkit().getImage("marron.jpg") ;
    	Image orange=Toolkit.getDefaultToolkit().getImage("orange.jpg") ;
    	Image rouge=Toolkit.getDefaultToolkit().getImage("roue.jpg") ;
    	Image jaune=Toolkit.getDefaultToolkit().getImage("jaune.jpg") ;   
    	Image vert=Toolkit.getDefaultToolkit().getImage("vert.jpg") ;
    	Image bleu=Toolkit.getDefaultToolkit().getImage("bleu.png") ;
    	Image gris=Toolkit.getDefaultToolkit().getImage("gris.jpg") ;
    	Image blanc=Toolkit.getDefaultToolkit().getImage("blanc.jpg") ;
    	Image noir=Toolkit.getDefaultToolkit().getImage("noir.png") ;
    	g.drawImage(blanc, 0,0,1000,650,this);
    	for(int k=0; k<3 ; k++){
    		for(int i=0;i<13;i++){
    			for(int j=0;j<20;j++){
    				if(map.getTabC(k).getTabP(i,j)=="1"){
    					g.drawImage(gris, j*50,i*50,50,50,this);
    				}
    				if(map.getTabC(k).getTabP(i,j)=="."){
    					g.drawImage(bleu, j*50,i*50,50,50,this);
    				}
    				if(map.getTabC(k).getTabP(i,j)=="4"){
    				
    					g.drawImage(vert, j*50,i*50,50,50,this);
    				}
    				if(map.getTabC(k).getTabP(i,j)=="0"){
    					g.drawImage(marron, j*50,i*50,50,50,this);
    				}
    				
    				if(map.getTabC(k).getTabP(i,j)==("9")){
    					g.drawImage(vertf, j*50,i*50,50,50,this);
    				}
    				
    				if(map.getTabC(k).getTabP(i,j)=="8"){
    					g.drawImage(jaune, j*50,i*50,50,50,this);
    				}
    				if(map.getTabC(k).getTabP(i,j)=="7"){
    					g.drawImage(orange, j*50,i*50,50,50,this);
    				}
    				if(map.getTabC(k).getTabP(i,j)=="6"){
    					g.drawImage(rouge, j*50,i*50,50,50,this);
    				}
    				
    			}
    		}
    	}
    	
    }
	

   class Animation extends Thread {
	
	carteentiere map;
	Grenouille gre;
	int limitateur;
	boolean gagner;
	int[] ralentir={4,3,2,5,3,1,5,3,2,4,3};
	tortues tortue1;
	tortues tortue2;
	

	Animation(carteentiere c,Grenouille g){
		this.map=c;
		this.gagner=false;
		this.limitateur=2;
		this.gre=g;
		tortue1=new tortues(3);
		tortue2=new tortues(10);
	}
	
	public void run(){
		
		
		while(!gagner){
			
			
			// calcule et changement de place les voitures et les rondins
			
			for(int i=1; i<12;i++){
				if((limitateur%ralentir[i-1])==0){
					if(i==1 || i==3 || i==4 || i==7||i==9||i==11){
						map.getTabC(1).update(i,"gauche");
						if(map.getColiR() && gre.getY()==i){
							if(gre.getX()>0)
								gre.setX(gre.getX()+1);
							else
								gre.setX(0);
							map.getTabC(2).update(i,"gauche");
						}
					}
					else{
						map.getTabC(1).update(i,"droite");
						if(map.getColiR() && gre.getY()==i){
							if(gre.getX()>0)
								gre.setX(gre.getX()-1);
							else
								gre.setX(19);
							map.getTabC(2).update(i,"droite");
						}
					}
					
					//on verifie les tortues
					if(i==2){
						if(limitateur%2==0)
							tortue1.Changement();
						else
							tortue1.setX(tortue1.getX()-1);
						map.getTabC(1).setTab(2,tortue1.getX(),tortue1.getEtat());
						if(tortue1.getX()!=19)
							map.getTabC(1).setTab(2,tortue1.getX()+1,tortue1.getEtat());
					}
					
					if(i==5){
						
						if((limitateur+1)%2==0)
							tortue2.Changement();
						else
							tortue2.setX(tortue2.getX()-1);
						map.getTabC(1).setTab(5,tortue2.getX(),tortue2.getEtat());
						if(tortue2.getX()!=19)
							map.getTabC(1).setTab(5,tortue2.getX()+1,tortue2.getEtat());
						if(tortue2.getX()!=19 && tortue2.getX()!=18)
							map.getTabC(1).setTab(5,tortue2.getX()+2,tortue2.getEtat());
					}
					
				}
				
				
			}
			limitateur++;
			
			
			
			// on verifie les collisions voitures
			
			if(map.collision()){
				map.getTabC(2).setTab(gre.getY(),gre.getX()," ");
				gre.setX(10);
				gre.setY(12);
				map.getTabC(2).setTab(gre.getY(),gre.getX(),"4");
			}
			
			//on verifie les collisions rondins
			
			if(map.collisionR()){
				map.setColiR(true);
				
			}
			if(!map.collisionR()){
				map.setColiR(false);
			}
				
				
			//on affiche la carte
			
			map.Afficher();
			repaint();
			
			try{
				this.sleep(150);
			}catch(InterruptedException e){e.printStackTrace();}
			
		}



	}	


} 
    
    
    
	
	
	public static void main ( String args []) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                Grenouille grenouile=new Grenouille(10,12);
                
            	Carte inter=new Carte();
				Carte map=new Carte();
				map.initCarte();
				Carte gr=new Carte();
				
				carteentiere macarte=new carteentiere(inter,map,gr);
				
				
				
				JFrame frame = new projet(macarte,grenouile);
				frame.setSize(1000,650);   // dim fenetre
				frame.setVisible(true);
				
			}	
		});
	}

	
class TitreKeyListener implements KeyListener {
    private final JLabel label;
    
     carteentiere map;
     Grenouille grenouile;
    public TitreKeyListener(JLabel label_, carteentiere c, Grenouille g) {
        label = label_;
        this.grenouile=g;
        this.map=c;
    }
 
    public void keyPressed(KeyEvent e) {
		//map.setTab(grenouile.getY(), grenouile.getX() ,2, " ");
		map.getTabC(2).effacer();
        if (e.getKeyCode() == KeyEvent.VK_UP && grenouile.getY()>0){
			grenouile.setY(grenouile.getY()-1);
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && grenouile.getX()<19)
			grenouile.setX(grenouile.getX()+1);
			
		if (e.getKeyCode() == KeyEvent.VK_DOWN && grenouile.getY()<12)
			grenouile.setY(grenouile.getY()+1);
			
		if (e.getKeyCode() == KeyEvent.VK_LEFT && grenouile.getX()>0)
			grenouile.setX(grenouile.getX()-1);
		
		
		if(map.collision()){
			grenouile.setX(10);
			grenouile.setY(12);
		}	
		if(!map.collisionR()){
			map.setColiR(false);
		}
		map.setTab(grenouile.getY(), grenouile.getX() ,2, "4");	
		map.Afficher();
		repaint();
    }
 
    public void keyReleased(KeyEvent e) {
       
    }
 
    public void keyTyped(KeyEvent e) {
        // on ne fait rien
    }
}








}	
	
	






