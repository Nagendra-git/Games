package com.example.tictactoe.service.impl;

import com.example.tictactoe.dto.GameStatus;
import com.example.tictactoe.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private String[] board = new String[9];
    private String currentPlayer = "X";
    private String winner = null;
    private boolean isGameOver = false;

    private final int[][] winlines = {
            {0,1,2}, {3,4,5}, {6,7,8},//rows
            {0,3,6}, {1,4,7}, {2,5,8},//columns
            {0,4,8}, {2,4,6}//Diagonals
    };

    @Override
    public GameStatus getStatus() {
        return new GameStatus(board, currentPlayer, winner, isGameOver);
    }

    @Override
    public GameStatus makeMove(int index) {
        if(isGameOver || index < 0 || index > 8 || board[index] != null ){
            return getStatus();
        }
        board[index] = currentPlayer;
        checkWinner();

        if(!isGameOver){
            currentPlayer = currentPlayer.equals("X")? "O":"X";
        }
        return getStatus();
    }

    @Override
    public GameStatus resetGame() {
        board = new String[9];
        currentPlayer = "X";
        winner = null;
        isGameOver = false;
        return getStatus();
    }

    private void checkWinner(){
        for(int[] line : winlines){
            if (board[line[0]] != null &&
                    board[line[0]].equals(board[line[1]]) &&
                    board[line[0]].equals(board[line[2]])) {
                winner = board[line[0]];
                isGameOver = true;
                return;
            }
        }
        boolean draw = true;
        for (String cell : board) {
            if (cell == null) {
                draw = false;
                break;
            }
        }
        if (draw) {
            winner = "Draw";
            isGameOver = true;
        }
    }
}
