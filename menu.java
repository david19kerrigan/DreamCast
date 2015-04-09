import java.util.*;
    import java.io.*;
public class menu{

    public static void newgame(){
	//#1 load the story from txt files
	//#2 load the turn taker. if time based wait for input
    }

    public static void continuegame(){

	//#1 load the save file
	//#2 run as if it were a new game being played from the beginning
    }

    public static void main(String[]args){

	Scanner inputchoices = new Scanner(System.in);
	System.out.println( "1|New Game    2|Continue");
	String input = inputchoices.nextLine();

	if (input =="1"){
	    newgame();
	}

	if ( input == "2"){
	    continuegame();
	}

    }
}
