package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class PongGame extends Canvas implements Runnable, KeyListener {
	
	public static final int WIDTH = 501;
	public static final int HEIGHT = 334;
	public static final int SCALE = 2;
	
	public SpriteSheet fieldSheet;
	public BufferedImage field;
	
	public static Player player;
	public static OtherPlayer player2;
	public static Ball ball;
	
	public PongGame(int p1, int p2) {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		
		fieldSheet = new SpriteSheet("/camp.png");
		field = fieldSheet.getSprite(0, 0, 1440, 900);
		
		player = new Player(p1);
		player2 = new OtherPlayer(p2);
		ball = new Ball();
	}
	
	public static void main(String[] args) {
		
		PongGame game = new PongGame(0, 0);
		
		JFrame jframe = new JFrame("Pong Game"); //Instancing the window.
		jframe.setVisible(true);	
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(game);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		
		new Thread(game).start();
	}
		
		
	public void start() {
		//TODO
	}
	
	public void stop() {
		//TODO
	}
	
	public void update() {
		//TODO
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = field.getGraphics();
		g = bs.getDrawGraphics();
		g.drawImage(field, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		player.render(g);
		player2.render(g);
		ball.render(g);
		
		bs.show();
	}
	
	public void tick() {
		ball.tick();
		player.tick();
		player2.tick();
	}
	
	@Override
	public void run() {
		while(true) {
			requestFocus();
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}	
		
		if(e.getKeyCode() == KeyEvent.VK_0) {
			ball.isPlaying = true;
			ball.up = true;
			ball.left = true;
			ball.right= false;
			ball.down= false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

