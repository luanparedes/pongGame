package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class OtherPlayer implements IPlayers{
	
	public final int W_SCALE = PongGame.WIDTH * PongGame.SCALE;
	public final int H_SCALE = PongGame.HEIGHT * PongGame.SCALE;
	
	private SpriteSheet otherPlayerSheet;
	private BufferedImage otherPlayer;
	
	public boolean up;
	public boolean down;
	public double x;
	public double y;
	public int width;
	public int height;
	public int goals;
	
	public OtherPlayer(int goal) {
		this.width = 15 * PongGame.SCALE;
		this.height = 80 * PongGame.SCALE;
		this.x = W_SCALE - 30;
		this.y = H_SCALE - (H_SCALE / 2) - (height / 2);
		this.goals = goal;
		
		otherPlayerSheet = new SpriteSheet("/mew.png");
		otherPlayer = otherPlayerSheet.getSprite(0, 0, 256, 256);
	}
	
	@Override
	public void tick() {
		if(PongGame.ball.up) {
			y -= PongGame.ball.ballSpeed - 2;
		}
		else if(PongGame.ball.down) {
			y += PongGame.ball.ballSpeed - 2;
		}

	}
	
	@Override
	public void render(Graphics p2) {
		p2.drawImage(otherPlayer, (int)x-130, (int)y, width + 100, height, null);
	}
	
	@Override
	public void goal() {
		this.goals += 1;
		System.out.println("Player: " + PongGame.player.goals + "\nRival: " + this.goals);
	}
}