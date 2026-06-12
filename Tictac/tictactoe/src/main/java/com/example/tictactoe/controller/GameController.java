package com.example.tictactoe.controller;

import com.example.tictactoe.dto.GameStatus;
import com.example.tictactoe.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/status")
    public ResponseEntity<GameStatus> getStatus(){
    return new ResponseEntity<>(gameService.getStatus(), HttpStatus.OK);
    }

    @PostMapping("/move")
    public ResponseEntity<GameStatus> makeMove(@RequestParam int index){
        return new ResponseEntity<>(gameService.makeMove(index), HttpStatus.OK);

    }
    @PostMapping("/reset")
    public ResponseEntity<GameStatus> resetGame(){
        return new ResponseEntity<>(gameService.resetGame(),HttpStatus.OK);
    }

}
