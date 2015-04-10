import java.io.*;
import java.util.*;
public class Character{
    private ArrayList<String> items;
    private int SPEED;
    private int HP;
    private int ATTH;
    private int ATTL;
    private int HPM;
    private int ATTM;
    private int ALC;
    private int LDEF;
    private int HDEF;
    private int LUCK;
    private int SPEECH;
    private int STEALTH;
    private int PICK;
    private int CHARM;
//item example "1212121212"
 //speed11 health12 heavyweapon13 lightweapon14
    public Character(int level, String specialization){
    	populate(40, level);
    	while(OP()){
		ArrayList<String> blank = new ArrayList<String>();
		items = blank;
		populate(40, level);
	}
	calcstats();
}
    
    public static int variabilize(int variable, int current){
		Random var = new Random();		
		 current += var.nextInt(variable);
		 	return current;
	}
    
	public void populate(int amount, int level){
	while(amount != 0){
		amount = amount - 1;
		int type = 1;
		addone(variabilize(14, type), level, items);
	}
}
	public static void addone(int type , int level, ArrayList<String> current){
	String itemtoadd = "";
	variabilize(3, level);
	while( level != 0){
			level--;
			itemtoadd = itemtoadd + type;
	}
	current.add(itemtoadd);
}


	public void calcstats(){
	SPEECH = count(11);
	CHARM = count(12);
	STEALTH = count(13);
    	PICK = count(14);
	ALC = count(15);
    	ATTH = count (16);
    	ATTL = count (17);
	ATTM = count (18);
	HPM = count (19) ;
	HP = count(20);
	LDEF = count (21);
    	HDEF = count (22);
    	LUCK = count (23);
    	SPEED = count (24);
}
// attack social
	public String category( int type ){
    if( 11 <= type || type <= 15 )
    	return "social" ;	
	if ( 16 <= type || type <= 19)
		return "attack" ;
	return "not a valid interaction type";
}  

	public int count( int type){		
		int place = 0;
		int counter = 0;
		while (place != items.size()){
			counter = counter + countelement(type, items.get(place));
			place += 1;
		}
		return counter;
}

	public static int countelement(int type, String current){
		int howmany = 0;
		String typecheck = "" + type;
		int place = 0;
		while( place != current.length()){
			if ( typecheck.charAt(0) == current.charAt(place) && typecheck.charAt(1) == current.charAt(place + 1)){
				howmany += 1;
				place += 2;
			}
		}
		return howmany;
}


			
	public void calcstatsboth(Character opponent){
		this.calcstats();
		opponent.calcstats();
}

    public String interact( Character ai, int type){
	if( category(type) == "attack"){ return attack(ai, type);}
	if( category(type) == "social"){return social(ai, type);}
		else{return "not an interaction";}
}
//SPEECH = count(11)CHARM = count(12)STEALTH = count(13)PICK = count(14)ALC = count(15)ATTH = count (16)ATTL = count (17)ATTM = count (18)HPM = count (19)HP = count(20)LDEF = count (21)HDEF = count (22)LUCK = count (23)SPEED = count (24)
	public String attack( Character ai, int type){
		String finalHP = "";
		ai.HP = ai.HP - count(type)/(ai.LDEF + ai.HDEF);
		finalHP += ai.HP;
		return finalHP;
}
 
	public String social( Character ai, int type){
	if (type == 11 && variabilize(100, 0) <= SPEECH  )
		return "+11";
	else if (type == 11)
		return "-11";
	if (type == 11 && variabilize(100, 0) <= CHARM  )
		return "+12";
	else if (type == 11)
		return "-12";
	if (type == 11 && variabilize(100, 0) <= STEALTH  )
		return "+13";
	else if (type == 11)
		return "-13";
	if (type == 11 && variabilize(100, 0) <= SPEECH  )
		return "+14";
	else if (type == 11)
		return "-14";
	return "0";
}

	public String deathitem(){
		return items.get(variabilize(40, 0));
}
	public String battlereward(Character ai){
		Scanner input = new Scanner(System.in);
		ArrayList<String> Storage = new ArrayList<String>();
		Storage = items;
		System.out.println("which of your items would you like to replace?");
		int index = input.nextInt();
		String deathitem = ai.deathitem();
		items.add(index + 1, deathitem);
		items.remove(index);
		if(OP()){ 
			items = Storage;
			battlereward(ai);
		calcstats();
		}
		return deathitem;
}

	public void battle(Character ai){
		Scanner typein = new Scanner(System.in);
		int type = typein.nextInt();
		while( ai.HP != 0 && HP != 0){
				System.out.println(" time to attack");				
				interact(ai, type);
				ai.interact(this, type);
		}
		if (HP != 0)
			battlereward(ai);
}

	public boolean OP(){
	int i = 10;
	int low = count(i);
	int high = 0;
		while(i != 24){
			if (count(i) < low)
				low = count(i);
			if (count(i) > high)	
				high = count(i); 
				i = i + 1;
			}
		if ((high / low) > 10)	
			return true;	
		return false;
}
	public static void main(String[]args){
}
}

