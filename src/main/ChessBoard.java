package main;

import java.awt.*;

public class ChessBoard {
    final int MAX_ROW = 8;
    final int MAX_COL = 8;
    public static final int TILE_SIZE = 100;
    public static final int HALF_TILE_SIZE = TILE_SIZE / 2;

    public void draw(Graphics2D g2){
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                if ((row + col) % 2 == 0) {
                    g2.setColor(new Color(210,165,125));
                } else {
                    g2.setColor(new Color(175,115,70));
                }
                g2.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}
