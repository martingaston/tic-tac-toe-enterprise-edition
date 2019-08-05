import referee from './referee'

describe('The referee object', () => {
  describe('With referee.create()', () => {
    it('Returns 9 moves by default', () => {
      const board = referee.create()
      expect(referee.available(board)).toEqual([1, 2, 3, 4, 5, 6, 7, 8, 9])
    })
  })

  describe('Using referee.update()', () => {
    it('Can retrieve a mark from a played square', () => {
      const state = referee.create()
      const updatedboard = referee.update(state, 9, 'X')
      expect(referee.get(updatedboard, 9)).toEqual('X')
    })

    it('Returns 8 moves if one has been played', () => {
      const state = referee.create()
      const updatedboard = referee.update(state, 1, 'X')
      expect(referee.available(updatedboard)).toEqual([2, 3, 4, 5, 6, 7, 8, 9])
    })

    it('Throws if the requested position is out of range', () => {
      const board = referee.create()
      const tooLow = 0
      const tooHigh = 10

      expect(() => referee.update(board, tooLow, 'X')).toThrow(RangeError)
      expect(() => referee.update(board, tooHigh, 'X')).toThrow(RangeError)
    })
  })

  describe('Using board.hasWinner()', () => {
    it('Knows an state with no played moves has no winner', () => {
      const board = referee.create()
      expect(referee.hasWinner(board)).toBe(false)
    })

    it('Knows three played moves on a horizontal is a winning move', () => {
      const board = referee.create()
      const firstMove = referee.update(board, 1, 'X')
      const secondMove = referee.update(firstMove, 2, 'X')
      const thirdMove = referee.update(secondMove, 3, 'X')
      expect(referee.hasWinner(thirdMove)).toBe(true)
    })

    it('Knows three played moves on a vertical is a winning move', () => {
      const state = referee.create()
      const firstMove = referee.update(state, 2, 'X')
      const secondMove = referee.update(firstMove, 5, 'X')
      const thirdMove = referee.update(secondMove, 8, 'X')
      expect(referee.hasWinner(thirdMove)).toBe(true)
    })

    it('Knows three played moves on a diagonal is a winning move', () => {
      const state = referee.create()
      const firstMove = referee.update(state, 3, 'X')
      const secondMove = referee.update(firstMove, 5, 'X')
      const thirdMove = referee.update(secondMove, 7, 'X')
      expect(referee.hasWinner(thirdMove)).toBe(true)
    })
  })
})
