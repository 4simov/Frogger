import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;








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
    				
    				if(map.getTabC(k).getTabP(i,j)=="8" || map.getTabC(k).getTabP(i,j)=="G"){
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
	
   	boolean tantque=true;
	carteentiere map;
	Grenouille gre;
	int limitateur;
	boolean gagner;
	int[] ralentir={4,3,2,5,3,1,5,3,2,4,3};
	tortues tortue1;
	tortues tortue2;
	crocodile croco;

	Animation(carteentiere c,Grenouille g){
		this.map=c;
		this.gagner=false;
		this.limitateur=2;
		this.gre=g;
		tortue1=new tortues(3);
		tortue2=new tortues(10);
		croco=new crocodile(3,1);
	}
	
	public void run(){
		
		
		while(map.getNiveau()<3){
			
			
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
						if(i==1){
							croco.setX(croco.getX()+1);
						}
						if(croco.getX()<4 && croco.getEtat()==1){
							for(int w=0;w<croco.getX();w++){
								map.getTabC(1).setTab(1,croco.getX()-w-1,"9");
							}
						}
						if(croco.getX()<4 && croco.getEtat()==0){
							for(int w=0;w<croco.getX();w++){
								map.getTabC(1).setTab(1,croco.getX()-w-1,"0");
							}
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
			
			// changement du crocodile si besoin
			
			if(croco.getX()>19){
				croco.changementEtat();
				croco.setX(0);
				if(croco.getEtat()==1){	
					map.getTabC(1).setTab(1,0,"6");
					
				}
				else{
					map.getTabC(1).setTab(1,0,"0");
				}
			}
			
			
			// on verifie les collisions voitures
			
			if(gre.collision()){
				map.getTabC(2).setTab(gre.getY(),gre.getX()," ");
				gre.setX(10);
				gre.setY(12);
				map.getTabC(2).setTab(gre.getY(),gre.getX(),"4");
				
				if(gre.getVie()<=0){
					map.setNiveau(4);
					map.getTabC(1).initia(4);
					map.setCompteur(0);
				}
			}
			
			//on verifie les collisions rondins
			
			if(gre.collisionR()){
				map.setColiR(true);
				
			}
			if(!gre.collisionR()){
				map.setColiR(false);
			}
			 //on verifie si gagner
			 
			if(gre.collisionG()){
				map.getTabC(1).setTab(gre.getY(),gre.getX(),"A");
				map.augmenterCompteur(1);
				map.getTabC(2).setTab(gre.getY(),gre.getX()," ");
				gre.setX(10);
				gre.setY(12);
				map.getTabC(2).setTab(gre.getY(),gre.getX(),"4");
				if(map.getCompteur()>=5){
					map.setCompteur(0);
					map.augmenterNiveau(1);
					map.getTabC(2).setTab(gre.getY(),gre.getX()," ");
					gre.setX(10);
					gre.setY(12);
					map.getTabC(2).setTab(gre.getY(),gre.getX(),"4");
					map.getTabC(1).initia(map.getNiveau());
				}
			}
				
			//on affiche la carte
			gre.Afficher();
			map.Afficher();
			//repaint();
			
			try{
				this.sleep(150);
			}catch(InterruptedException e){e.printStackTrace();}
			
		}
		repaint();



	}	


} 
    
    
    
	
	
	public static void main ( String args []) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
               
                
            	Carte inter=new Carte();
		Carte map=new Carte();
		map.initia(1);
		Carte gr=new Carte();
		
		carteentiere macarte=new carteentiere(inter,map,gr);
		 Grenouille grenouile=new Grenouille(10,12,macarte);	
				
				
				JFrame frame = new projet(macarte,grenouile);
				frame.setSize(1000,650);   // dim fenetre
				frame.setVisible(true);
				
			}	
		});
	}




	
class TitreKeyListener extends JFrame implements KeyListener{
    private final JLabel label;
    
     carteentiere map;
     Grenouille grenouile;
    public TitreKeyListener(JLabel label_, carteentiere c, Grenouille g) {
        this.label = label_;
        this.grenouile=g;
        this.map=c;
    }
 
    public void keyPressed(KeyEvent e) {
    	    	
    	 if(!grenouile.collision()){
			map.getTabC(2).effacer();
			if (e.getKeyCode() == KeyEvent.VK_UP && grenouile.getY()>0 ){
				grenouile.setY(grenouile.getY()-1);
			}	
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && grenouile.getX()<19)
				grenouile.setX(grenouile.getX()+1);
				
			if (e.getKeyCode() == KeyEvent.VK_DOWN && grenouile.getY()<12)
				grenouile.setY(grenouile.getY()+1);
				
			if (e.getKeyCode() == KeyEvent.VK_LEFT && grenouile.getX()>0)
				grenouile.setX(grenouile.getX()-1);
			
			
			if(grenouile.collision()){
				//grenouile.setX(10);
				//grenouile.setY(12);
			}	
			if(!grenouile.collisionR()){
				map.setColiR(false);
			}
			map.setTab(grenouile.getY(), grenouile.getX() ,2, "4");	
			grenouile.Afficher();
			map.Afficher();
			repaint();
	 }
		
    }
 
    public void keyReleased(KeyEvent e) {
       
    }
 
    public void keyTyped(KeyEvent e) {
        // on ne fait rien
    }
}
}










	
	
	






