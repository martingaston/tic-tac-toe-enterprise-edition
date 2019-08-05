const create = (totalSquares = '9') => {
  const emptySquare = null
  return Array.from({ length: totalSquares }).fill(emptySquare)
}

const update = (board, position, mark) => {
  if (position < 1 || position > board.length) {
    throw new RangeError(`Position must be between 1 and ${board.length}`)
  }

  return replaceBoardAtPosition(board, mark, position)
}

const replaceBoardAtPosition = (board, replace, position) => [
  ...board.slice(0, position - 1),
  replace,
  ...board.slice(position),
]

const get = (board, position) => board[positionToArrayIndex(position)] || ''

const positionToArrayIndex = position => position - 1

const hasWinner = board => {
  const winningMoves = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
    [1, 4, 7],
    [2, 5, 8],
    [3, 6, 9],
    [1, 5, 9],
    [3, 5, 7],
  ]

  const getFromBoard = i => get(board, i)

  return (
    winningMoves.filter(
      move =>
        getFromBoard(move[0]) !== '' &&
        getFromBoard(move[0]) === getFromBoard(move[1]) &&
        getFromBoard(move[1]) === getFromBoard(move[2])
    ).length > 0
  )
}

const available = board => {
  const initialValue = []
  return board.reduce(
    (available, currentSquare, index) =>
      currentSquare != null ? available : available.concat(index + 1),
    initialValue
  )
}

const boardIsFull = board => available(board).length === 0

const withinBounds = (number, board) => number > 0 && number <= board.length

export default {
  create,
  get,
  update,
  hasWinner,
  withinBounds,
  available,
  boardIsFull,
}
