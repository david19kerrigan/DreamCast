public class Character{
    private Arraylist[] items = new ArrayList;
    private int SP;
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
 //speed11 health12 heavyweapon13 lightweapon14
    public Character(int level){
	items.populate(40, level);
	calcstats();
}

	public void populate(int amount, int level){
	while(amount != 0){
		amount = amount - 1;
		int type = 1;
		items.addone(type.variabilize(14), level);
	}
}
	public String addone(int type , int level){
	String itemtoadd = ""
	level.variabilize(3);
	while( level != 0){
			level--;
			itemtoadd = itemtoadd + type;
	}
	items.add(itemtoadd);
}

	public void variabilize(int variable){
		Random variable = new Random();		
		this = this + variable.nextInt(variable);
	}

	public void poststatcalc(){
	SP;
    	HP;
    ATTH;
    ATTL;
    HPM;
    ATTM;
    ALC;
    LDEF;
    HDEF;
    LUCK;
    SPEECH;
    STEALTH;
    PICK;
    private int CHARM;}

	public int count( int type){		
		int place = 0;
		int counter = 0;
		while (place != items.size()){
			counter = counter + item.get(place).countelement(type);
			place += 1;
		}
		return counter;
}

	public int countelement(int type){
		int howmany = 0;
		String typecheck = "" + type;
		int place = 0;
		while( place != length){
			if ( typecheck.getChar(0) == getChar(place) && typecheck.getChar(1) == getChar(place + 1)){
				howmany += 1;
				place += 2;
			}
		}
		return howmany;
}


			
	public void statcalcphase(Character opponent){
		this.poststatcalc();
		opponent.poststatcalc();
}

    public int interact( Characer ai, int type){
	if( cateory(type) == "attack"){ attack(opponent);}
	if( category(type) == "social"){ social(opponent);}
	if (category(type) =="gift" ){ gift(opponent);}
	if (category(type) == "craft"){craft(opponent)}
    }
    public String category{ int type }
    if type == 
		
		
