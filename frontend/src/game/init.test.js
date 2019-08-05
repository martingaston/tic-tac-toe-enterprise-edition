import init from './init'

describe('the game initialiser', () => {
  const newGame = init()
  it('is an active game', () => {
    expect(newGame.isActive).toEqual(true)
  })

  it('has a board', () => {
    expect(newGame.board).toBeDefined()
  })

  it('contains the game messages', () => {
    const { messages } = newGame
    expect(messages.intro).toBeDefined()
    expect(messages.title).toBeDefined()
    expect(messages.instructions).toBeDefined()
  })

  it('should have the correct player', () => {
    expect(newGame.currentPlayer).toEqual('X')
  })
})
