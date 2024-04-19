package piece;

import main.ChessBoard;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Piece {
    public BufferedImage image;
    public int x, y;
    public int row, col, prevRow, prevCol;
    public int color;

    public Piece(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
        x = getX(col);
        y = getY(row);
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public int getX(int col) {
        return col * ChessBoard.TILE_SIZE;
    }

    public int getY(int row) {
        return row * ChessBoard.TILE_SIZE;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, null);
    }
    public int getCol(){
        return (x + ChessBoard.HALF_TILE_SIZE)/ChessBoard.TILE_SIZE;
    }
    public int getRow(){
        return (y + ChessBoard.HALF_TILE_SIZE)/ChessBoard.TILE_SIZE;
    }
    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        prevCol = getCol();
        prevRow = getRow();
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isWithinBounds(int targetCol, int targetRow) {
        if (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }
}

