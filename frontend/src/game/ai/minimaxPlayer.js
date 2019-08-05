import minimax from './minimax'

export default {
  chooseMove: (board, state) => minimax(state, 'O').position,
}
