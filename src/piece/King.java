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
            if (Math.abs(targetCol - prevCol) + Math.abs(targetRow - prevRow) == 1 ||
                    Math.abs(targetCol - prevCol) * Math.abs(targetRow - prevRow) == 1) {
                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
