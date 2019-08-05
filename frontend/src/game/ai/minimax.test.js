import minimax from './minimax'
import referee from '../referee'

describe('the minimax algorithm', () => {
  let board
  beforeEach(() => {
    board = referee.create()
  })

  it('throws an error if the board is full', () => {
    board = referee.update(board, 1, 'X')
    board = referee.update(board, 2, 'O')
    board = referee.update(board, 3, 'X')
    board = referee.update(board, 4, 'X')
    board = referee.update(board, 5, 'O')
    board = referee.update(board, 6, 'O')
    board = referee.update(board, 7, 'O')
    board = referee.update(board, 8, 'X')
    board = referee.update(board, 9, 'X')

    expect(() => minimax(board, 'X')).toThrow(TypeError)
  })

  it('returns the last move left on the board if one move is available', () => {
    board = referee.update(board, 1, 'X')
    board = referee.update(board, 2, 'O')
    board = referee.update(board, 3, 'X')
    board = referee.update(board, 4, 'X')
    board = referee.update(board, 5, 'O')
    board = referee.update(board, 6, 'O')
    board = referee.update(board, 7, 'O')
    board = referee.update(board, 8, 'X')
    const position = 9

    expect(minimax(board, 'X').position).toEqual(position)
  })

  it('chooses a win over a draw with two squares available', () => {
    board = referee.update(board, 1, 'X')
    board = referee.update(board, 2, 'O')
    board = referee.update(board, 3, 'X')
    board = referee.update(board, 4, 'X')
    board = referee.update(board, 6, 'O')
    board = referee.update(board, 7, 'O')
    board = referee.update(board, 9, 'X')
    const position = 5

    expect(minimax(board, 'X').position).toEqual(position)
  })

  it('avoids a loss', () => {
    board = referee.update(board, 1, 'X')
    board = referee.update(board, 2, 'X')
    board = referee.update(board, 3, 'O')
    board = referee.update(board, 5, 'O')
    const position = 7

    expect(minimax(board, 'X').position).toEqual(position)
  })

  it('works with both X and O marks as the maximising player', () => {
    board = referee.update(board, 1, 'O')
    board = referee.update(board, 2, 'O')
    const noughtWinningValue = 16

    expect(minimax(board, 'O').value).toEqual(noughtWinningValue)
  })
})
