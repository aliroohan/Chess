package piece;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-knight");
        } else {
            image = getImage("/piece/b-knight");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBounds(targetCol, targetRow)) {
            if (Math.abs(targetCol - prevCol) == 2 && Math.abs(targetRow - prevRow) == 1) {
                return isValidSquare(targetCol, targetRow);
            } else if (Math.abs(targetCol - prevCol) == 1 && Math.abs(targetRow - prevRow) == 2) {
                return isValidSquare(targetCol, targetRow);
            }
        }
        return false;
    }
}
