package com.example.tictactoe.service;

import com.example.tictactoe.dto.GameStatus;

public interface GameService {
    GameStatus getStatus();

    GameStatus makeMove(int index);

    GameStatus resetGame();
}
