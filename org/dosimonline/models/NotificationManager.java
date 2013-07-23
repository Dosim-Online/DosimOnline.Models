package org.dosimonline.models;

import java.util.ArrayList;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author gilnaa
 */
public class NotificationManager {
	private int screenWidth;

	ArrayList<Notification> notifications;
	TrueTypeFont font;

	public NotificationManager() {
		notifications = new ArrayList<Notification>();
	}

	public void add(Notification n) {
		n.setOrder(notifications.size());
		n.setScreenWidth(screenWidth);
		notifications.add(n);
	}
	
	public void add(String text) {
		add(text, Color.white);
	}
	
	public void add(String text, Color color) {
		Notification e = new Notification(text, notifications.size(), color,
				screenWidth, font);
		notifications.add(e);
	}

	public void init(GameContainer gc, StateBasedGame sbg) {
		screenWidth = gc.getWidth();
		Font f = new Font("Times New Roman", Font.PLAIN, 14);
		font = new TrueTypeFont(f, true);
	}

	public void update(GameContainer gc, int delta) {
		if (gc.getWidth() != screenWidth) {
			screenWidth = gc.getWidth();
		}

		ArrayList<Notification> dueToRemoval = new ArrayList<Notification>();
		for (Notification e : notifications) {
			e.update(gc, delta);
			if (!e.isValid()) {
				dueToRemoval.add(e);
			}
		}
		if (dueToRemoval.size() > 0) {
			notifications.removeAll(dueToRemoval);
			for (int i = 0; i < notifications.size(); i++) {
				notifications.get(i).setOrder(i);
			}
		}
	}

	public void render(Graphics g) {
		for (Notification e : notifications) {
			e.render(g);
		}
	}
}
