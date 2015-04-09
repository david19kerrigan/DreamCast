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
	/*
	Random rand = new Random();
	int[][] arr = new int[2][5];
	for (int b=0;b<2;b++) {
	    for (int a=0;a<5;a++) {
		if (rand.nextInt(100) < 30) {
		    arr[b][a] = 1; 
		    System.out.println(1);
		} else {
		    arr[b][a] = 0;
		    System.out.println(0);
		}
	    }
	    System.out.println("space");
	}
	int xCounter = 0;
	int yCounter = 0;
	while (yCounter<2) {
	    while (xCounter<5) {
		yCounter++;
		for (int y=(yCounter*10);y<y+20;y++) {
		    for (int z=(xCounter*10);z<z+20;z++) {
			terrain[y][z].setType("water");
			terrain[y][z].setImage("~");
		    }
		}
	    }
	    xCounter++;
	}
	*/
       
	Random rand = new Random();
	int i = 0;
	int k = 0;
	while (i<101) {
	    while (k<41) {
		if (rand.nextInt(100) > 0) {
		    for (int y=i;y<i+20;y++) {
			for (int z=k;z<k+20;z++) {
			    terrain[y][z].setType("water");
			    terrain[y][z].setImage("~");
			}
		    }
		}
		k=k+20;
	    }
	    i=i+20;

	}
       
	/*
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
	*/
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
