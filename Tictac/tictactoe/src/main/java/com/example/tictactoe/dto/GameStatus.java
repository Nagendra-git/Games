package com.example.tictactoe.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameStatus {

    private String[] board;

    private String currentPlayer;

    private String winner;

    private boolean isGameOver;

    public GameStatus(String[] board, String currentPlayer, String winner, boolean isGameOver) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.isGameOver = isGameOver;
    }
}
