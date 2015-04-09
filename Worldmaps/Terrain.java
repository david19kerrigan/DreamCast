import java.util.*;
import java.io.*;

public class Terrain {

    private Tile[][]  terrain;
    private int maxX;
    private int maxY;

    public Terrain() {
	maxX = 20;
	maxY = 40;
	terrain = new Tile[maxY][maxX];
	for (int i=0;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		Tile a = new Tile();
		terrain[i][k] = a;
	    }
	}
	randomize();
    }

    public void randomize() {
	Random rand = new Random();
	for (int i=;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		if (rand.nextInt(1) == 0) {
		    terrain[i][k].setType("water");
		    terrain[i][k].setImage("w");
		}	
	    }
	}
    }

    public String toString() {
	String s = "";
	for (int i=0;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		s = s + terrain[i][k].getImage();
	    }
	    s = s + "\n";
	}
	return s;
    }

    public static void main(String[] args) {
	Terrain t = new Terrain();
	t.randomize();
	System.out.println(t);
    }

}
