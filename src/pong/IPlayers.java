package pong;

import java.awt.Graphics;

public interface IPlayers {
	
	public void tick();
	public void render(Graphics g);
	public void goal();
}
