import React, {useState, useEffect} from 'react';
import './App.css';

function App() {
  const [gameState, setGameState] = useState({
      board: Array(9).fill(null),
      currentPlayer: 'X',
      winner: null,
      gameOver:false,
  })

  const API_URL = "http://localhost:8080/api/game";

  const fetchStatus = async () =>{
    try{
      const res = await fetch(`${API_URL}/status`);
      const data = await res.json();
      setGameState(data);
    }catch (err){
      console.error("Error Communicating with spring boot server", err);
    }
  };

  useEffect(() =>{
    fetchStatus();
  },[]);

  //post placement move index

  const handleCellClick = async (index) =>{
    if(gameState.board[index] || gameState.gameOver) return;

    try{
      const res = await fetch(`${API_URL}/move?index=${index}`,{method: 'POST'});
      const data = await res.json();
      setGameState(data);
    }catch(err){
      console.error(err);
    }
  };

  //Reset the server logic tracking matrx
  const handleReset = async () =>{
    try{
      const res = await fetch(`${API_URL}/reset`, {method:'POST'});
      const data = await res.json();
      setGameState(data);
    }catch(err){
      console.error(err);
    }
  }

  return (
    <div className='game-conatiner'>
      <h1>Tic-Tac-Toe</h1>

      <div className='status-message'>
         {gameState.gameOver 
          ? (gameState.winner === 'Draw' ? "It's a Draw! 🤝" : `Winner: ${gameState.winner} 🎉`)
          : `Current Player: ${gameState.currentPlayer}`
        }
      </div>

      <div className='board-grid'>
          {gameState.board.map((cell, idx) => (
          <button 
            key={idx} 
            className={`cell-box ${cell}`} 
            onClick={() => handleCellClick(idx)}
          >
            {cell}
          </button>
        ))}
      </div>
        <button className="reset-btn" onClick={handleReset}>Restart Game</button>
    </div>
  );
}

export default App;
