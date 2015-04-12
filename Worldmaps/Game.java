import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import java.util.*;
import java.io.*;

public class Game extends Canvas implements Runnable{
    
    
    private static final long serialVersionUID = 1L;
    
    public static final int WIDTH = 100;
    public static final int HEIGHT = 40;
    public static final int SCALE = 10;
    public static final String NAME = "Game";
    
    private JFrame frame;
    
    public boolean running = false;
    public int tickCount = 0;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    public InputHandler input;    

    private static final Terrain terrain = new Terrain();
    private int x = 10;
    private int y = 10;
    private Random rand = new Random();
    private double nsPerTick = 7500000000D/60D;

    public Game(){
	setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	
	frame = new JFrame(NAME);
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(new BorderLayout());
	
	frame.add(this, BorderLayout.CENTER);
	frame.pack();
	
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);	

	input = new InputHandler(this);
    }
    
    public synchronized void start() {
	running = true;
	new Thread(this).start();
    }
    
    public synchronized void stop() {
	running = false;
    }
    
    public void run() {	
	long lastTime = System.nanoTime();
	
	int frames = 0;
	int ticks = 0;

	long lastTimer = System.currentTimeMillis();
	double delta = 0;
	
	while (running){
	    long now = System.nanoTime();
	    delta += (now - lastTime) / nsPerTick;
	    lastTime = now;
	    boolean shouldRender = true;
	    while(delta >= 1)
		{
		    ticks++;
		    tick();
		    delta -= 1;
		    shouldRender = true;
		}	    
	    if (shouldRender)
		{
		    frames++;
		    render();
		}
	    
	    if(System.currentTimeMillis() - lastTimer >= 1000){
		lastTimer += 1000;
		System.out.println(ticks + " ticks, " + frames + " frames");
		frames = 0;
		ticks = 0;
	    }
	}
    }
    
    public void tick(){
     
	tickCount++;

	Tile[][] t = terrain.getTerrain();

	for (int y=0;y<terrain.getmaxY();y++) {
	    for (int x=0;x<terrain.getmaxX();x++) {
		if (t[y][x].getCharacter() == "monster") {
		    if (rand.nextInt(100) <25) {
			int direction = rand.nextInt(4);
			if (direction == 0 && y-1 > -1) {
			    t[y][x].setCharacter("");
			    t[y-1][x].setCharacter("monster");
			    y--;
			}
		        if (direction == 1 && y+1  < terrain.getmaxY()) {
			    t[y][x].setCharacter("");
			    t[y+1][x].setCharacter("monster");
			    y++;
			}
			if (direction == 2 && x-1 > -1) {
			    t[y][x].setCharacter("");
			    t[y][x-1].setCharacter("monster");
			    x--;
			}
			if (direction == 3 && x+1 < terrain.getmaxX()) {
			    t[y][x].setCharacter("");
			    t[y][x+1].setCharacter("monster");
			    x++;
			}
		    }
		}
	    }
	}
	
	if (t[y][x].getType() == "water") {
	    nsPerTick = 15000000000D/60D;
	}
	if (t[y][x].getType() == "grass") {
	    nsPerTick = 7500000000D/60D;
	}

	if (input.up.isPressed()  && y > 0){
	    t[y][x].setCharacter("");
	    t[y-1][x].setCharacter("w");
	    y--;
	}
	if (input.down.isPressed() && y+1 < terrain.getmaxY()){
	    t[y+1][x].setCharacter("w");
	    t[y][x].setCharacter("");
	    y++;
	}
	if (input.left.isPressed() && x > 0){
	    t[y][x-1].setCharacter("w");
	    t[y][x].setCharacter("");
	    x--;
	}
	if (input.right.isPressed() && x+1 < terrain.getmaxX()){
	    t[y][x+1].setCharacter("w");
	    t[y][x].setCharacter("");
	    x++;
	}
    }
    
    public void render(){
	
	BufferStrategy bs = getBufferStrategy();
	if (bs == null){
	    createBufferStrategy(3);
	    return;
	}
	
	Graphics g = bs.getDrawGraphics();
	
	Tile[][] t = terrain.getTerrain();
	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	g.dispose();
	bs.show();

	for (int y=0;y<terrain.getmaxY();y++) {
	    for (int x=0;x<terrain.getmaxX();x++) {
		if (t[y][x].getType() == "grass") {
		    image.setRGB(x,y,Color.GREEN.getRGB());
		}
		if (t[y][x].getType() == "water") {
		    image.setRGB(x,y,Color.BLUE.getRGB());
		} 
		if (t[y][x].getCharacter() == "w") {
		    image.setRGB(x,y,Color.RED.getRGB());
		}
		if (t[y][x].getCharacter() == "monster") {
		    image.setRGB(x,y,Color.YELLOW.getRGB());
		}
	    }
	}
    }
    
    public static void main(String[] args){ 
	new Game().start();	
    }
    
}
