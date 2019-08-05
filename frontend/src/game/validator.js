import referee from './referee'
import messages from './messages'

export default (input, board) => {
  const base = 10
  const parsedInput = Number.parseInt(input, base)

  if (Number.isNaN(parsedInput)) {
    return createResponse('notANumber', messages.nan)
  }

  if (!referee.withinBounds(parsedInput, board)) {
    return createResponse('outOfBounds', messages.outOfBounds)
  }

  if (!referee.available(board).includes(parsedInput)) {
    return createResponse('occupied', messages.occupied)
  }

  return createResponse('ok', '')
}

const createResponse = (status, message) => ({
  status,
  message,
})
