import messages from './messages'
import referee from './referee'

export default (mode = 'human') => ({
  isActive: true,
  board: referee.create(),
  messages: {
    title: messages.title,
    intro: messages.intro,
    instructions: messages.instructions,
    turn: messages.turn('X'),
  },
  currentPlayer: 'X',
  mode,
})
