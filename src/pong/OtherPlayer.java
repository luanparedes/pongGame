package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class OtherPlayer implements IPlayers{
	
	public final int W_SCALE = PongGame.WIDTH * PongGame.SCALE;
	public final int H_SCALE = PongGame.HEIGHT * PongGame.SCALE;
	
	private SpriteSheet otherPlayerSheet;
	private BufferedImage otherPlayer;
	
	public boolean up;
	public boolean down;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public OtherPlayer() {
		this.width = 15 * PongGame.SCALE;
		this.height = 80 * PongGame.SCALE;
		this.x = W_SCALE - 30;
		this.y = H_SCALE - (H_SCALE / 2) - (height / 2);
		
		otherPlayerSheet = new SpriteSheet("/mew.png");
		otherPlayer = otherPlayerSheet.getSprite(0, 0, 256, 256);
	}
	
	@Override
	public void tick() {
		if(up) {
			y+=3;
		}
		else if(down) {
			y-=3;
		}
		
		if(y + height > H_SCALE) {
			y = H_SCALE - height;
		}
		else if(y < 0) {
			y = 0;
		}
	}
	
	@Override
	public void render(Graphics p2) {
		p2.drawImage(otherPlayer, x-130, y, width + 100, height, null);
	}
}