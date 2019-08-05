import referee from './referee'
import messages from './messages'
import minimax from './ai/minimaxPlayer'

const swapPlayer = currentPlayer => (currentPlayer === 'X' ? 'O' : 'X')

const getEndingMessage = (board, currentPlayer) => {
  if (referee.hasWinner(board)) {
    return messages.winner(currentPlayer)
  }

  return messages.draw
}

const update = (position, options) => {
  const updatedBoard = referee.update(
    options.board,
    position,
    options.currentPlayer
  )

  if (gameIsOver(updatedBoard)) {
    return gameOver(updatedBoard, options.currentPlayer)
  }

  if (options.mode === 'ai' && swapPlayer(options.currentPlayer) === 'O') {
    return update(
      minimax.chooseMove(referee, updatedBoard),
      nextMove(updatedBoard, options)
    )
  }

  return nextMove(updatedBoard, options)
}

const gameIsOver = board =>
  referee.hasWinner(board) || referee.boardIsFull(board)

const nextMove = (board, options) => {
  const nextPlayer = swapPlayer(options.currentPlayer)
  return {
    ...options,
    board,
    currentPlayer: nextPlayer,
    isActive: true,
    messages: {
      turn: messages.turn(nextPlayer),
    },
  }
}

const gameOver = (board, currentPlayer) => ({
  board,
  isActive: false,
  messages: {
    ending: getEndingMessage(board, currentPlayer),
  },
})

export default update
