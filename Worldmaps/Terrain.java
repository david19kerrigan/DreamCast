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
	int[][] arr = new int[2][5];
	for (int b=0;b<2;b++) {
	    for (int a=0;a<5;a++) {
		if (rand.nextInt(100) < 50) {
		    arr[b][a] = 1; 
		    System.out.println(1);
		} else {
		    arr[b][a] = 0;
		    System.out.println(0);
		}
	    }
	    System.out.println("space");
	}
	for (int i=0;i<2;i++) {
	    for (int k=0;k<5;k++) {
		if (arr[i][k] == 1) {
		    for (int a=i*20;a<i*20+20;a++) {
			for (int b=k*20;b<k*20+20;b++) {
			    terrain[a][b].setType("water");
			    terrain[a][b].setImage("~");
			}
		    }
		}
	    }
	}

	int c = 0;
	while (c < 2) {
	    for (int a=0;a<maxY-1;a++) {
		for (int b=0;b<maxX-1;b++) {
		    if (terrain[a][b].getType() == "grass"  && 
			rand.nextInt(100) > 40) {  
			if (b-1 > 0) {
			    terrain[a][b-1].setType("grass");
			    terrain[a][b-1].setImage("#");
			    
			}
			if (b+1 < maxX-1) {
			    terrain[a][b+1].setType("grass");
			    terrain[a][b+1].setImage("#");
			    b++;
			}
			if (a-1 > 0) {
			    terrain[a-1][b].setType("grass");
			    terrain[a-1][b].setImage("#");
			}
			if (a+1 < maxY-1) {
			    terrain[a+1][b].setType("grass");
			    terrain[a+1][b].setImage("#");
			}
			
			
		    }
		}
	    }
	    c = c + 1;
	}
	for (int g=0;g<5;g++) {
	    rand = nextInt(100);
	    terrain[rand.nextInt(100)][rand.nextInt(40)].setType("water");
	    terrain[rand
	}
	/*
	int x = 0;
	while (x < 1) {
	    for (int y=0;y<maxY-1;y++) {
		for (int z=0;z<maxX-1;z++) {
		    if (terrain[y][z].getType() == "water"  && 
			rand.nextInt(100) > 10) {  
			if (z-1 > 0) {
			    terrain[y][z-1].setType("water");
			    terrain[y][z-1].setImage("~");
			    
			}
			if (z+1 < maxX-1) {
			    terrain[y][z+1].setType("water");
			    terrain[y][z+1].setImage("~");
			    z++;
			}
			if (y-1 > 0) {
			    terrain[y-1][z].setType("water");
			    terrain[y-1][z].setImage("~");
			}
			if (y+1 < maxY-1) {
			    terrain[y+1][z].setType("water");
			    terrain[y+1][z].setImage("~");
			}
			
			
		    }
		}
	    }
	    x = x + 1;
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
	System.out.println(t);
    }

}
