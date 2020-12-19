package pong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ball implements IPlayers{
	
	public final int W_SCALE = PongGame.WIDTH * PongGame.SCALE;
	public final int H_SCALE = PongGame.HEIGHT * PongGame.SCALE;
	
	public int x, y;
	public boolean isPlaying;
	private int ballSpeed = 3;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
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
		/*
		if(y + 24 > H_SCALE) {
			y = H_SCALE - 24;
		}
		else if(y < 0) {
			y = 0;
		}
		
		if(x + 24 > W_SCALE) {
			x = W_SCALE - 24;
		}
		else if(x < 0){
			x = 0;
		}*/
	}
	
	public void ballInteligence() {
		if(y + 24 > H_SCALE) {
			downContact();
		}
		
		if(y <= 0) {
			upContact();
		}
		
		if(x + 24 > W_SCALE) {
			rightContact();
		}
		
		if(x <= 0) {
			leftContact();
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

}
