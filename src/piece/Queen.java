package piece;

import main.GamePanel;
public class Queen extends Piece{
    public Queen(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-queen");
        } else {
            image = getImage("/piece/b-queen");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBounds(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (targetCol == prevCol || targetRow == prevRow) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)) {
                    return true;
                }
            }
            if (Math.abs(targetCol - prevCol) == Math.abs(targetRow - prevRow)) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)) {
                    return true;
                }
            }

        }
        return false;
    }
}
