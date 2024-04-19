package piece;

import main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-pawn");
        } else {
            image = getImage("/piece/b-pawn");
        }
    }
}
