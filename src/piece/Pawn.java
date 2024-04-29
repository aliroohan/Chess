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

    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBounds(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            int movealue;
            if (color == GamePanel.White) {
                movealue = -1;
            } else {
                movealue = 1;
            }
            hittingPiece = getHittingPiece(targetCol, targetRow);
            if (targetCol == prevCol && targetRow == prevRow + movealue && hittingPiece == null){
                return true;
            }
            if (targetCol == prevCol && targetRow == prevRow + movealue * 2 && hittingPiece == null && !moved &&
                    !pieceIsOnStraightLine(targetCol, targetRow)){
                return true;
            }
            if (Math.abs(targetCol - prevCol) == 1 && targetRow == prevRow + movealue && hittingPiece != null && hittingPiece.color != color){
                return true;
            }

        }
        return false;
    }
}
