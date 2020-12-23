package pong;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ball implements IPlayers{
	
	//Constants
	public final int W_SCALE = PongGame.WIDTH * PongGame.SCALE;
	public final int H_SCALE = PongGame.HEIGHT * PongGame.SCALE;
	
	//Variables
	public int x;
	public int y;
	public boolean isPlaying;
	public static int ballSpeed = 3;
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	
	private SpriteSheet sheet;
	private BufferedImage ball;

	
	public Ball() {
		this.x = W_SCALE - (W_SCALE / 2) - 12;
		this.y = H_SCALE - (H_SCALE / 2) - 8;
		
		sheet = new SpriteSheet("/ball.png");
		
		ball = sheet.getSprite(0, 0, 24, 24);
	}
	
	@Override
	public void tick() {
		if(isPlaying) {
			ballColision();
			ballInteligence();
			ballControl();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ball, x, y, null);
	}
	
	public void ballControl() {
		if(up) {
			y -= ballSpeed;
		}
		if(down) {
			y += ballSpeed;
		}
		if(left) {
			x -= ballSpeed;
		}
		if(right) {
			x += ballSpeed;
		}
		
	}
	
	public void ballInteligence() {
		if(y + 40 > H_SCALE) {
			downContact();
		}
		
		if(y <= 12) {
			upContact();
		}
		
		if(x + 75 > W_SCALE) {
			PongGame.player.goal();
			PongGame.ball.goal();
		}
		
		if(x <= 50) {
			PongGame.player2.goal();
			PongGame.ball.goal();
		}
		
	}
	
	public void ballColision() {
		Rectangle rectBall = new Rectangle(x, y, 24, 24);
		Rectangle rectPlayer1 = new Rectangle(PongGame.player.x + 80, PongGame.player.y, PongGame.player.width, PongGame.player.height);
		Rectangle rectPlayer2 = new Rectangle((int)PongGame.player2.x - 85, (int)PongGame.player2.y, PongGame.player2.width, PongGame.player2.height);

		if(rectBall.intersects(rectPlayer1)) {
			System.out.println("Tocou no jogador");
			this.leftContact();
		}
		else if(rectBall.intersects(rectPlayer2)) {
			System.out.println("Tocou no rival");
			this.rightContact();
		}
	}
	
	public void upContact() {
		this.up = false;
		this.down = true;
	}
	
	public void downContact() {
		this.down = false;
		this.up = true;
	}
	
	public void leftContact() {
		this.left = false;
		this.right = true;
	}
	
	public void rightContact() {
		this.right = false;
		this.left = true;
	}
	
	@Override
	public void goal() {
		new PongGame(PongGame.player.goals, PongGame.player2.goals);
	}

	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
}
