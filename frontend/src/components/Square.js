import React from 'react'
import { EMPTY_SQUARE } from '../constants'

const Square = ({ zeroIndexedPosition, value, onClick }) => {
  const position = numberToOneIndex(zeroIndexedPosition)
  return (
    <button
      onClick={getOnClick(value, onClick, position)}
      aria-label={`Square ${position}`}
      className={getClassName(value)}
    >
      {value}
    </button>
  )
}

const getOnClick = (value, onClick, position) =>
  isSquareEmpty(value) ? () => onClick(position) : null

const getClassName = value =>
  isSquareEmpty(value) ? 'square available' : `square player-${value}`

const isSquareEmpty = value => value === EMPTY_SQUARE

const numberToOneIndex = x => x + 1

export default Square
