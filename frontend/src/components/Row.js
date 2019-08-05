import React from 'react'
import Square from './Square'

const Row = ({ squares, zeroIndexedRowNumber, onClick }) => (
  <div className="row">
    {generateRowSquares(squares, zeroIndexedRowNumber, onClick)}
  </div>
)

const generateRowSquares = (squares, zeroIndexedRowNumber, onClick) => {
  const numberOfSquaresInRow = squares.length
  return squares.map((square, index) => (
    <Square
      key={index}
      onClick={onClick}
      zeroIndexedPosition={calculatePosition(
        index,
        zeroIndexedRowNumber,
        numberOfSquaresInRow
      )}
      value={square}
    />
  ))
}

const calculatePosition = (
  index,
  zeroIndexedRowNumber,
  numberOfSquaresInRow
) => {
  const multiplier = zeroIndexedRowNumber * numberOfSquaresInRow
  return multiplier + index
}

export default Row
