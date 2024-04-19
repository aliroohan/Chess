package piece;

import main.GamePanel;

public class Rook extends Piece{
    public Rook(int row, int col, int color) {
        super(row, col, color);
        if (color == GamePanel.White) {
            image = getImage("/piece/w-rook");
        } else {
            image = getImage("/piece/b-rook");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
    if (isWithinBounds(targetCol, targetRow)) {
        if ((prevRow == targetRow || prevCol == targetCol) && !isSameSquare(targetCol, targetRow)) {
            if (isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)) {
                return true;
            }
        }
    }
    return false;
}
}
