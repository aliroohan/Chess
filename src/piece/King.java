package piece;

import main.GamePanel;
public class King extends Piece{
    public King(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-king");
        } else {
            image = getImage("/piece/b-king");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {

        if (isWithinBounds(targetCol, targetRow)) {
            return false;
        }
        if (targetCol == col && targetRow == row) {
            return false;
        }
        if (Math.abs(targetCol - col) <= 1 && Math.abs(targetRow - row) <= 1) {
            return true;
        }
        return false;
    }
}
