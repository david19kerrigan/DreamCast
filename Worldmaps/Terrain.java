import java.util.*;
import java.io.*;

public class Terrain {

    private Tile[][]  terrain;
    private int maxX;
    private int maxY;

    public Terrain() {
	maxX = 100;
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
	for (int i=0;i<maxY;i++) {
	    for (int k=0;k<maxX;k++) {
		if (rand.nextInt(100) > 30) {
		    for (int y=i;y<20;y++) {
			for (int z=k;z<20;z++) {
			    terrain[y][z].setType("water");
			    terrain[y][z].setImage("~");
			}
		    }
		    i = i + 20;
		    k = k + 20;
		}	
	    }
	}
	int c = 0;
	while (c < 1000) {
	    for (int a=0;a<maxY-1;a++) {
		for (int b=0;b<maxX-1;b++) {
		    if (terrain[a][b].getType() == "grass") {
			terrain[a][b+1].setType("grass");
			terrain[a][b+1].setImage("#");
			b = b + 1;
		    }
		}
	    }
	    c = c + 1;
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
