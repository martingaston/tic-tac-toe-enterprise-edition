import messages from './messages'

describe('A messages object', () => {
  it('should have a title', () => {
    expect(messages.title).toBeDefined()
    expect(messages.title).not.toHaveLength(0)
  })

  it('should have a intro', () => {
    expect(messages.intro).toBeDefined()
    expect(messages.intro).not.toHaveLength(0)
  })

  it('should have a instructions', () => {
    expect(messages.instructions).toBeDefined()
    expect(messages.instructions).not.toHaveLength(0)
  })

  it('should have a nan', () => {
    expect(messages.nan).toBeDefined()
    expect(messages.nan).not.toHaveLength(0)
  })

  it('should have a outOfBounds', () => {
    expect(messages.outOfBounds).toBeDefined()
    expect(messages.outOfBounds).not.toHaveLength(0)
  })

  it('should have a occupied', () => {
    expect(messages.occupied).toBeDefined()
    expect(messages.occupied).not.toHaveLength(0)
  })

  it('should have a winner', () => {
    expect(messages.winner('X')).toBeDefined()
    expect(messages.winner('O')).not.toHaveLength(0)
  })

  it('should show the inputted mark in the winner output', () => {
    expect(messages.winner('ASDF')).toMatch(/ASDF/)
  })

  it('should have a draw', () => {
    expect(messages.draw).toBeDefined()
    expect(messages.draw).not.toHaveLength(0)
  })

  it('should have a turn', () => {
    expect(messages.turn('X')).toBeDefined()
    expect(messages.turn('O')).not.toHaveLength(0)
  })

  it('should show the inputted mark with the turn message', () => {
    expect(messages.turn('QWERTY')).toMatch(/QWERTY/)
  })

  it('should have a unknownError', () => {
    expect(messages.unknownError).toBeDefined()
    expect(messages.unknownError).not.toHaveLength(0)
  })
})
