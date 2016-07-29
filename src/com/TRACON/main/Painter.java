package com.TRACON.main;

import java.awt.Graphics;
import java.awt.Point;

public class Painter {

    private Point mouse;
    private int frames;
    public Painter() {
    }

    public void render(Graphics g) {
        if (Aircraft.selected != null) {
            if (Aircraft.selected.isBeingDragged()) {
                if (mouse != null) {
                    int dx = (int) mouse.getX() - Aircraft.selected.getX();
                    int dy = (int) mouse.getY() - Aircraft.selected.getY();

                    g.drawLine(Aircraft.selected.getX(), Aircraft.selected.getY(), (int) mouse.getX(), (int) mouse.getY());

                    int newHeading = (int) Math.toDegrees(Math.atan2(dx, -1 * dy));

                    if (dx < 0) {
                        newHeading += 360;
                    } else {
                        if (newHeading > 360) {
                            newHeading -= 360;
                        } else {
                            if (newHeading <= 0) {
                                newHeading += 360;
                            }
                        }
                    }
                    g.drawString(String.valueOf(newHeading), (int) mouse.getX() - 20, (int) mouse.getY() - 20);
                }
            }
        }

        g.drawString("FPS: " + String.valueOf(frames), 30, 15);
    }

    public void updateMouse(Point a) {
        mouse = a;
    }

    public void updateFrames(int frames) {
        this.frames = frames;
    }

}
