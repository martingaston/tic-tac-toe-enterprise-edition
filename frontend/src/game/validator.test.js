import validator from './validator'
import referee from './referee'

describe('the validator function', () => {
  let board
  beforeEach(() => {
    board = referee.create()
  })

  it('accepts a number available on the board', () => {
    const input = '1'

    const { status, message } = validator(input, board)

    expect(status).toEqual('ok')
    expect(message).toBeDefined()
  })

  it('does not accept a number for a taken position on the board', () => {
    board = referee.update(board, 1, 'X')
    const input = '1'

    const { status, message } = validator(input, board)

    expect(status).toEqual('occupied')
    expect(message).toBeDefined()
  })

  it('refuses invalid numbers', () => {
    const input = 'cat'
    const { status, message } = validator(input, board)

    expect(status).toEqual('notANumber')
    expect(message).toBeDefined()
  })

  describe('refuses numbers that are out of bounds', () => {
    it('numbers that are too low', () => {
      const input = '0'
      const { status, message } = validator(input, board)

      expect(status).toEqual('outOfBounds')
      expect(message).toBeDefined()
    })

    it('numbers that are too high', () => {
      const input = 10
      const { status, message } = validator(input, board)

      expect(status).toEqual('outOfBounds')
      expect(message).toBeDefined()
    })
  })
})
