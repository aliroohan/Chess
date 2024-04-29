package main;

import piece.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    ChessBoard board = new ChessBoard();
    Mouse mouse = new Mouse();

    public static ArrayList<Piece> pieces = new ArrayList<Piece>();
    public static ArrayList<Piece> simpieces = new ArrayList<Piece>();
    Piece activePiece;

    public static final int White = 0;
    public static final int Black = 1;
    int currentPlayer = White;

    boolean canMove;
    boolean validSquare;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces, simpieces);
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces(){
        pieces.add(new Pawn(6, 0, White));
        pieces.add(new Pawn(6, 1, White));
        pieces.add(new Pawn(6, 2, White));
        pieces.add(new Pawn(6, 3, White));
        pieces.add(new Pawn(6, 4, White));
        pieces.add(new Pawn(6, 5, White));
        pieces.add(new Pawn(6, 6, White));
        pieces.add(new Pawn(6, 7, White));
        pieces.add(new Rook(7, 0, White));
        pieces.add(new Knight(7, 1, White));
        pieces.add(new Bishop(7, 2, White));
        pieces.add(new Queen(7, 3, White));
        pieces.add(new King(7,4, White));
        pieces.add(new Bishop(7, 5, White));
        pieces.add(new Knight(7, 6, White));
        pieces.add(new Rook(7, 7, White));

        pieces.add(new Pawn(1, 0, Black));
        pieces.add(new Pawn(1, 1, Black));
        pieces.add(new Pawn(1, 2, Black));
        pieces.add(new Pawn(1, 3, Black));
        pieces.add(new Pawn(1, 4, Black));
        pieces.add(new Pawn(1, 5, Black));
        pieces.add(new Pawn(1, 6, Black));
        pieces.add(new Pawn(1, 7, Black));
        pieces.add(new Rook(0, 0, Black));
        pieces.add(new Knight(0, 1, Black));
        pieces.add(new Bishop(0, 2, Black));
        pieces.add(new Queen(0, 3, Black));
        pieces.add(new King(0, 4, Black));
        pieces.add(new Bishop(0, 5, Black));
        pieces.add(new Knight(0, 6, Black));
        pieces.add(new Rook(0, 7, Black));

    }
    @Override
    public void run() {

        // Game Loop
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // Mouse Button Pressed
        if(mouse.pressed){
            if (activePiece == null) {
                // if the mouse is pressed on a piece
                for (Piece piece : simpieces) {
                    // if the piece is the same color as the current player
                    if (piece.color == currentPlayer &&
                            piece.col == mouse.x / ChessBoard.TILE_SIZE &&
                            piece.row == mouse.y / ChessBoard.TILE_SIZE) {
                        activePiece = piece;
                    }
                }
            } else {
                simulate();
            }
        }

        if (mouse.pressed == false) {
            if (activePiece != null) {

                if(validSquare){
                    copyPieces(simpieces, pieces);
                    activePiece.updatePosition();
                    changePlayer();
                } else {
                    copyPieces(pieces, simpieces);
                    activePiece.resetPosition();
                    activePiece = null;
                }
            }
        }
    }
    private void simulate() {

        canMove = false;
        validSquare = false;

        copyPieces(pieces, simpieces);

        // if the mouse is released on a valid tile
        activePiece.x = mouse.x - ChessBoard.HALF_TILE_SIZE;
        activePiece.y = mouse.y - ChessBoard.HALF_TILE_SIZE;
        activePiece.row = activePiece.getRow();
        activePiece.col = activePiece.getCol();


        if(activePiece.canMove(activePiece.col, activePiece.row)){
            canMove = true;
            if (activePiece.hittingPiece != null) {
                simpieces.remove(activePiece.hittingPiece.getIndex());
            }
            validSquare = true;
        }

    }

    public void changePlayer() {
        if (currentPlayer == White) {
            currentPlayer = Black;
        } else {
            currentPlayer = White;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.draw(g2);

        for (Piece simpiece : simpieces) {
            simpiece.draw(g2);
        }
        if (activePiece != null) {
            if(canMove) {
                g2.setColor(Color.CYAN);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activePiece.col * ChessBoard.TILE_SIZE, activePiece.row * ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE, ChessBoard.TILE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            activePiece.draw(g2);
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(Color.WHITE);

        if (currentPlayer == White) {
            g2.drawString("White's Turn", 800, 50);
        } else {
            g2.drawString("Black's Turn", 800, 50);
        }
    }


}

