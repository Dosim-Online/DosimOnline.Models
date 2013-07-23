/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dosimonline.models;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author gilnaa
 */
public final class Notification {
	private static final int PADDING = 10;
	TrueTypeFont font;
	float height;
	float width;
	float x;
	float y;
	float desiredY;
	String text;
	Color color;
	int dismissDelay;
	int pullOutDelay;

	public Notification(String text, int order, Color color, int screenWidth, TrueTypeFont f) {
		this.text = text;
		this.color = new Color(color);
		this.font = f;
		this.width = font.getWidth(text);
		this.height = font.getHeight(text);
		setOrder(order);
		this.y = desiredY;
		this.setScreenWidth(screenWidth);
		this.dismissDelay = 5000;
		this.pullOutDelay = 500;
	}

	public boolean isValid() {
		return pullOutDelay > 0;
	}

	public void setOrder(int order) {
		desiredY = order * height + PADDING;
	}

	public void update(GameContainer gc, int delta) {
		if (desiredY < y) {
			y -= delta / 10f;
		}
		if (dismissDelay > 0) {
			dismissDelay -= delta;
		} else {
			x += delta / 10f;
			pullOutDelay -= delta;
			color.a -= 0.01f;
		}
	}

	public void render(Graphics g) {
		font.drawString(x, y, text, color);
	}

	void setScreenWidth(int screenWidth) {
		this.x = screenWidth - (width + PADDING);
	}

}