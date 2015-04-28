//basic packages to import
import java.io.*;
import java.util.*;

public class Character{
//Name of character in game
	private String name;
//lists to store equipment
    private ArrayList<String> equipped;
    private String[] satchel;
//ints to hold stats of each character for interaction with other characters
    private int SPEED;
    private int HP;
    private int ATT;
    private int ATTMAGIC;
    private int ALC;
    private int DEF;
    private int LUCK;
    private int SPEECH;
    private int STEALTH;
    private int PICK;

//initializes a Character with certain level. populates the characters equipment with non over powered items.Names the character and initializes everything.
    public Character(String nameinput, int level, int specialization){
	name = nameinput;
	satchel = new String[50];
	ArrayList<String> blank = new ArrayList<String>(0);
	equipped = blank;
    	populate(level, specialization);
    	while(OP()){
		equipped = blank;
		populate(level, specialization);
	}		
	calcstats(1);
}


//creates randomness for various situations
    public static int variabilize(int variable, int current){
		Random var = new Random();		
		 current += var.nextInt(variable);
		 	return current;
	}


//populates equipment with a certain amount of equipped at a certain level and specialization. Used to initialize characters
	public void populate(int level, int specialization){
	int apparel = 11;
	while(apparel!= 21){
		addone(level,apparel,specialization,equipped);
		apparel++;
	}
}

//adds an item of certain apparel type, level, and specialization. Used to initialize characters
	public static void addone(int level , int apparel,int specialization, ArrayList<String> current){
	String itemtoadd ="";
		while(itemtoadd.length() < level * 2){
			while(itemtoadd.length() < level)
				itemtoadd += apparel;
			while(itemtoadd.length() < level + level/variabilize(3, 3))
				itemtoadd += specialization;
			itemtoadd += variabilize(11, 10);
		
	}
	current.add(itemtoadd);
}


//calculates stats before interactions occur
	public void calcstats(int choice){
	SPEECH = count(11);
	STEALTH = count(12);
    	PICK = count(13);
	ALC = count(14);
    	ATT = count (15);
	ATTMAGIC = count (16);
	DEF = count(18);
    	LUCK = count (19);
    	SPEED = count (20);
	if(choice != 0)
		HP = count(17);	
}


//proper names of concepts in the game
static String[] Armor ={"Pendant","Bracers","Lockpick","Spoon","Sword", "Staff","Helmet","Chestplate","Ring","Boots"};
	static String[] Types ={"Speech","Stealth","Lockpick","Alchemy","Attack", "Destruction","Health","Defense","Fortune","Speed"};
	public void statprint(){
	int i = 11;
	System.out.println( "||" + name + "||");
	while( i != 21){
		System.out.println(Types[i-11] + " : " + count(i));		
		i = i + 1;
	}
}


//finds the secondary specialization of a piece of apparel
	public int highish(int Apparel){
		int i = 11;
		int holderhigh = 0;
		int retstatement = 0;
		while( i != 21){
			if(countelement(i,equipped.get(Apparel - 11)) > holderhigh && i != Apparel){
				holderhigh = countelement(i,equipped.get(Apparel - 11));
				retstatement = i;
			}
		i++; 
		}
		return retstatement;
}



//prints all items Equipped on the character
	public void itemprint(){
	int i = 11;
	while (i != 21){
		itemspec(i);
		i++;
	}
}

//prints one item on the character
	public void itemspec(int i){
	System.out.println("||ITEM||");
		if(equipped.get(i - 11) == null)
			System.out.println("empty");
		else
		System.out.println(Armor[i-11] + " of " + Types[highish(i) - 11] +" Level "+ equipped.get(i - 11).length() / 2);
}


//counts up stats
	public int count( int type){		
		int place = 0;
		int counter = 0;
		while (place != equipped.size()){
			counter = counter + countelement(type, equipped.get(place));
			place += 1;
		}
		return counter;
}


//helper to the count method. counts instances of stat in a string
	public static int countelement(int type, String current){
		int howmany = 0;
		String typecheck = "" + type;
		int place = 0;
		while( place != current.length()){
			if ( typecheck.charAt(0) == current.charAt(place) && typecheck.charAt(1) == current.charAt(place + 1))
				howmany += 1;
			place += 2;
		}
		return howmany;
}


 
//determines the outcome of chanced social interaction
	public String social( Character ai, int type){
	if (type == 11 && variabilize(100, 0) <= SPEECH  )
		return "+11";
	else if (type == 11)
		return "-11";
	if (type == 12 && variabilize(100, 0) <= STEALTH  )
		return "+12";
	else if (type == 12)
		return "-12";
	if (type == 13 && variabilize(100, 0) <= PICK  )
		return "+13";
	else if (type == 13)
		return "-13";
	if (type == 14 && variabilize(100, 0) <= ALC  )
		return "+14";
	else if (type == 14)
		return "-14";
	return "0";
}





//battle reward process of taking an item from a dead foe
	public void battlereward(Character ai){
		int i = variabilize(10,11);
		String randitem = ai.equipped.get(i - 11);
		Scanner input = new Scanner(System.in);
		itemprint();
		ai.itemspec(i);
		System.out.println("would you like to take this item for your item of the same type?\nnegative number ||no \npositive number || yes");		
		int index = input.nextInt();
		if (index < 0){
			System.out.println("no item taken");
			return;
		}
		equipped.add(i - 11, randitem);
		equipped.remove(index + 1);
		calcstats(0);

		itemprint();
		System.out.println("item taken");
}



//attacking method
public int attack(Character ai){
	System.out.println("AI HP ||" + ai.HP + "\nHP ||" + HP);
	System.out.println("What attack method would you like?\n15||melee\n16||magic||\nAny other numbers to run.");
	Scanner typein = new Scanner(System.in);
	int type = typein.nextInt();
	if (type == 15 && ATT - ai.DEF/2 > 0)
		ai.HP -= ATT - ai.DEF/2;
	if (type == 16)
		ai.HP -= ATTMAGIC/2;
	if (type != 15 && type != 16)
		return 0;
	return 1;
}

//allows player to run		
	public void run(){}

public void aiattack(Character ai){
	if(ai.ATT > ai.ATTMAGIC)
		HP -= ai.ATT - DEF/2;
	else
		HP -= ai.ATTMAGIC/2;
}

//battle method to make battle ongoing
	public void battle(Character ai){
	int stillin = 0;
	while(true){ 
		if(HP > 0)
			stillin = attack(ai);
		if (ai.HP <= 0){
			battlereward(ai);
			return;
		}
		if (stillin == 0){
			System.out.println("ran away");
			return;
		}
		aiattack(ai);
	}
}	


//method to prevent over powered gear collection
	public boolean OP(){
	int i = 11;
	int low = count(i);
	int high = 0;
		while(i != 21){
			if (count(i) < low)
				low = count(i);
			if (count(i) > high)	
				high = count(i); 
			i = i + 1;
			}
		if (high/low > 20)	
			return true;	
		return false;
}
	public static void main(String[]args){
	Character Jacoby149 = new Character("Jacoby149",10, 11);
	Jacoby149.statprint();
	Jacoby149.itemprint();
	Character Nayz = new Character("Nayz", 6, 19);
	Nayz.statprint();
	Nayz.itemprint();
	Jacoby149.battle(Nayz);
	}
}
