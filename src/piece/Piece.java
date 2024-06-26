package piece;

import main.ChessBoard;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Piece {
    public BufferedImage image;
    public int x, y;
    public int row, col, prevRow, prevCol;
    public int color;
    public Piece hittingPiece;
    public boolean moved;

    public Piece(int row, int col, int color) {
        this.row = row;
        this.col = col;
        this.color = color;
        x = getX(col);
        y = getY(row);
        prevCol = col;
        prevRow = row;
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
    public int getIndex(){
        for (int index = 0;index < GamePanel.pieces.size();index++){
            if(GamePanel.pieces.get(index) == this){
                return index;
            }
        }
        return 0;
    }
    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        prevCol = getCol();
        prevRow = getRow();
        moved = true;
    }

    public void resetPosition(){
        col = prevCol;
        row = prevRow;
        x = getX(col);
        y = getY(row);
    }

    public Piece getHittingPiece(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.pieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);
        if (hittingPiece == null) {
            return true;
        } else{
            if (hittingPiece.color != color) {
                return true;
            }else{
               hittingPiece = null;
            }
        }
        return false;
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isSameSquare(int targetCol, int targetRow) {
        if (prevCol == targetCol && prevRow == targetRow) {
            return true;
        }
        return false;
    }
    public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
        // when pice is moving to the left
        for (int c = prevCol - 1; c > targetCol; c--){
            for (Piece piece : GamePanel.simpieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // when pice is moving to the right
        for (int c = prevCol + 1; c < targetCol; c++){
            for (Piece piece : GamePanel.simpieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // when pice is moving up
        for (int r = prevRow - 1; r > targetRow; r--){
            for (Piece piece : GamePanel.simpieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        for (int r = prevRow + 1; r < targetRow; r++){
            for (Piece piece : GamePanel.simpieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // when pice is moving down
        return false;
    }

    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow) {

        if (targetRow < prevRow) {
            // when pice is moving to the left and up
            for (int c = prevCol - 1; c > targetCol; c--) {
                int diff = Math.abs(prevCol - c);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == prevRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
            // when pice is moving to the right and up
            for (int c = prevCol + 1; c < targetCol; c++) {
                int diff = Math.abs(prevCol - c);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == prevRow - diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        if (targetRow > prevRow) {
            // when pice is moving to the left and down
            for (int c = prevCol - 1; c > targetCol; c--) {
                int diff = Math.abs(prevCol - c);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == prevRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            // when pice is moving to the right and down
            for (int c = prevCol + 1; c < targetCol; c++) {
                int diff = Math.abs(prevCol - c);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == prevRow + diff) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isWithinBounds(int targetCol, int targetRow) {
        if (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }
}

