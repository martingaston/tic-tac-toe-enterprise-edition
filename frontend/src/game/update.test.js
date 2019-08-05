import update from './update'
import referee from './referee'
import game from './index'

describe('the update function', () => {
  it('updates the board', () => {
    const options = game.init()
    const position = 1

    const { board } = update(position, options)

    expect(referee.get(board, position)).toEqual(options.currentPlayer)
  })

  it('swaps the players', () => {
    const turnZero = game.init()
    const firstMove = 1
    const secondMove = 2

    const turnOne = update(firstMove, turnZero)
    const playerNought = turnOne.currentPlayer

    const turnTwo = update(secondMove, turnOne)
    const playerCross = turnTwo.currentPlayer

    expect(playerNought).toEqual('O')
    expect(playerCross).toEqual('X')
  })

  it('updates the turn message', () => {
    const turnZero = game.init()
    const turnOne = update(1, turnZero)

    expect(turnZero.messages.turn).toMatch(/X/)
    expect(turnOne.messages.turn).toMatch(/O/)
  })

  it('updates isActive in the case of a win', () => {
    const turnZero = game.init()
    const turnOneCross = update(1, turnZero)
    const turnTwoNought = update(7, turnOneCross)
    const turnThreeCross = update(2, turnTwoNought)
    const turnFourNought = update(8, turnThreeCross)
    const turnFiveCross = update(3, turnFourNought)

    expect(turnFourNought.isActive).toEqual(true)
    expect(turnFiveCross.isActive).toEqual(false)
  })

  it('updates isActive in the case of a draw', () => {
    const turnZero = game.init()
    const turnOneCross = update(1, turnZero)
    const turnTwoNought = update(5, turnOneCross)
    const turnThreeCross = update(4, turnTwoNought)
    const turnFourNought = update(7, turnThreeCross)
    const turnFiveCross = update(8, turnFourNought)
    const turnSixNought = update(2, turnFiveCross)
    const turnSevenCross = update(9, turnSixNought)
    const turnEightNought = update(6, turnSevenCross)
    const turnNineCross = update(3, turnEightNought)

    expect(turnNineCross.isActive).toEqual(false)
  })

  it('plays an ai move if ai mode is enabled', () => {
    const turnZero = game.init('ai')
    const turnOneCross = update(1, turnZero)

    expect(turnOneCross.currentPlayer).toEqual('X')
    expect(referee.available(turnOneCross.board).length).toEqual(7)
  })
})
