import referee from '../referee'
import {
  bestPositionReducer,
  maximumReducer,
  minimumReducer,
} from './minimaxReducers'

const WINNING_SCORE = 10
const LOSING_SCORE = -10
const DRAW_SCORE = 0

const minimax = (board, maximisingPlayer) => {
  if (referee.boardIsFull(board)) {
    throw new TypeError('Must supply a board that is not full')
  }

  const players = calculatePlayers(maximisingPlayer)

  return referee
    .available(board)
    .map(position => [
      position,
      scoreMax(referee.update(board, position, players.maximiser), players),
    ])
    .reduce(bestPositionReducer, { position: null, value: -100000 })
}

const scoreMax = (board, players) => {
  if (referee.hasWinner(board)) {
    return WINNING_SCORE + getWeighting(board)
  }

  if (referee.boardIsFull(board)) {
    return DRAW_SCORE
  }

  const scores = referee
    .available(board)
    .map(position =>
      scoreMin(referee.update(board, position, players.minimiser), players)
    )
    .reduce(minimumReducer, +1000)

  return scores
}

const scoreMin = (board, players) => {
  if (referee.hasWinner(board)) {
    return LOSING_SCORE - getWeighting(board)
  }

  if (referee.boardIsFull(board)) {
    return DRAW_SCORE
  }

  const scores = referee
    .available(board)
    .map(position =>
      scoreMax(referee.update(board, position, players.maximiser), players)
    )
    .reduce(maximumReducer, -1000)

  return scores
}

const getWeighting = board => referee.available(board).length

const calculatePlayers = maximisingPlayer => ({
  maximiser: maximisingPlayer === 'X' ? 'X' : 'O',
  minimiser: maximisingPlayer === 'X' ? 'O' : 'X',
})

export default minimax
