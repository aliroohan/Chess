package piece;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-bishop");
        } else {
            image = getImage("/piece/b-bishop");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBounds(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (Math.abs(targetCol - prevCol) == Math.abs(targetRow - prevRow)) {
                if (!pieceIsOnDiagonalLine(targetCol, targetRow) && isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
