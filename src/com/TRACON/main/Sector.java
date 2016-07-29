package com.TRACON.main;

import java.awt.*;

/**
 * Created by Marshall on 7/29/2016.
 */
public class Sector {

    private Point center;
    private Game game;
    private int numRunways, translateRunwaysX, translateRunwaysY;

    private final int boundaryMilesWest = 25, boundaryMilesEast = 30, boundaryMilesNorth = 20, boundaryMilesSouth = 20, bevel = 5;

    private final int mRight = 150;

    private int[] x;
    private int[] y;

    public Sector(Game game)
    {
        this.game = game;

        numRunways = 1;

        translateRunwaysX = -50;

        x = new int[8];
        y = new int[8];

        int[] pointsX = {(game.WIDTH / 2) - (game.getPixelsPerMile() * (boundaryMilesWest - bevel)), (game.WIDTH / 2) + (game.getPixelsPerMile() * (boundaryMilesEast - bevel)), (game.WIDTH / 2) + (game.getPixelsPerMile() * boundaryMilesEast), (game.WIDTH / 2) + (game.getPixelsPerMile() * boundaryMilesEast), (game.WIDTH / 2) + (game.getPixelsPerMile() * (boundaryMilesEast - bevel)), (game.WIDTH / 2) - (game.getPixelsPerMile() * (boundaryMilesWest - bevel)), (game.WIDTH / 2) - (game.getPixelsPerMile() * boundaryMilesWest), (game.WIDTH / 2) - (game.getPixelsPerMile() * boundaryMilesWest)};
        int[] pointsY = {(game.HEIGHT / 2) - (game.getPixelsPerMile() * boundaryMilesNorth), (game.HEIGHT / 2) - (game.getPixelsPerMile() * boundaryMilesNorth), (game.HEIGHT / 2) - (game.getPixelsPerMile() * (boundaryMilesNorth - bevel)), (game.HEIGHT / 2) + (game.getPixelsPerMile() * (boundaryMilesSouth - bevel)), (game.HEIGHT / 2) + (game.getPixelsPerMile() * boundaryMilesSouth), (game.HEIGHT / 2) + (game.getPixelsPerMile() * boundaryMilesSouth), (game.HEIGHT / 2) + (game.getPixelsPerMile() * (boundaryMilesSouth - bevel)), (game.HEIGHT / 2) - (game.getPixelsPerMile() * (boundaryMilesSouth - bevel))};

        System.arraycopy(pointsX, 0, this.x, 0, 8);
        System.arraycopy(pointsY, 0, this.y, 0, 8);

    }

    public void render(Graphics g) {
        g.drawPolygon(x, y, 8);

        g.drawRect((game.WIDTH / 2 + translateRunwaysX) - game.getPixelsPerMile(), (game.HEIGHT / 2 + translateRunwaysY) - (game.getPixelsPerMile() / 2), game.getPixelsPerMile() * 2, game.getPixelsPerMile() / 5);
        g.drawRect((game.WIDTH / 2 + translateRunwaysX) - game.getPixelsPerMile(), (game.HEIGHT / 2 + translateRunwaysY) + (game.getPixelsPerMile() / 2), game.getPixelsPerMile() * 2, game.getPixelsPerMile() / 5);

        for (int rWay = 0; rWay < numRunways; rWay++) {
            for (int n = 0; n <= 3; n++) {
                g.drawLine((game.WIDTH / 2 + translateRunwaysX) + game.getPixelsPerMile() * 4 + (n * game.getPixelsPerMile() * 4), (game.HEIGHT / 2 + translateRunwaysY) - (game.getPixelsPerMile() / 2) + (game.getPixelsPerMile() / 10) + (game.getPixelsPerMile() * rWay),
                        (game.WIDTH / 2 + translateRunwaysX) + game.getPixelsPerMile() * 4 + (n * game.getPixelsPerMile() * 4) + (game.getPixelsPerMile() * 2), game.HEIGHT / 2 + translateRunwaysY - (game.getPixelsPerMile() / 2) + (game.getPixelsPerMile() / 10) + ((game.getPixelsPerMile()) * rWay));
            }
        }
    }

    public void toggleLeftRunway() {
        numRunways = (numRunways == 1) ? 2 : 1;
    }

    public void translate(int dx, int dy) {
        for (int ndx = 0; ndx < x.length; ndx++) {
            x[ndx] += dx;
        }

        for (int ndx = 0; ndx < y.length; ndx++) {
            y[ndx] += dy;
        }

        translateRunwaysX += dx;
        translateRunwaysY += dy;
    }

    public void reCalculateSector()
    {
        int[] pointsX = {(game.WIDTH / 2) - (game.getPixelsPerMile() * (boundaryMilesWest - bevel)), (game.WIDTH / 2) + (game.getPixelsPerMile() * (boundaryMilesEast - bevel)), (game.WIDTH / 2) + (game.getPixelsPerMile() * boundaryMilesEast), (game.WIDTH / 2) + (game.getPixelsPerMile() * boundaryMilesEast), (game.WIDTH / 2) + (game.getPixelsPerMile() * (boundaryMilesEast - bevel)), (game.WIDTH / 2) - (game.getPixelsPerMile() * (boundaryMilesWest - bevel)), (game.WIDTH / 2) - (game.getPixelsPerMile() * boundaryMilesWest), (game.WIDTH / 2) - (game.getPixelsPerMile() * boundaryMilesWest)};
        int[] pointsY = {(game.HEIGHT / 2) - (game.getPixelsPerMile() * boundaryMilesNorth), (game.HEIGHT / 2) - (game.getPixelsPerMile() * boundaryMilesNorth), (game.HEIGHT / 2) - (game.getPixelsPerMile() * (boundaryMilesNorth - bevel)), (game.HEIGHT / 2) + (game.getPixelsPerMile() * (boundaryMilesSouth - bevel)), (game.HEIGHT / 2) + (game.getPixelsPerMile() * boundaryMilesSouth), (game.HEIGHT / 2) + (game.getPixelsPerMile() * boundaryMilesSouth), (game.HEIGHT / 2) + (game.getPixelsPerMile() * (boundaryMilesSouth - bevel)), (game.HEIGHT / 2) - (game.getPixelsPerMile() * (boundaryMilesSouth - bevel))};

        System.arraycopy(pointsX, 0, this.x, 0, 8);
        System.arraycopy(pointsY, 0, this.y, 0, 8);
    }
}


