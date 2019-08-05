import React from 'react'
import Game from './Game'
import { render, fireEvent, cleanup } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

describe('a Game component', () => {
  describe('in human v human mode', () => {
    it('can play a complete game where X wins', () => {
      const { getByText, getByLabelText } = render(<Game mode="human" />)
      const crossMoves = [1, 2, 3]
      const noughtMoves = [4, 5]

      playGame(crossMoves, noughtMoves, getByLabelText)

      expect(getByText('Player X wins!')).toBeInTheDocument()
    })

    it('can play a complete game where O wins', () => {
      const { getByText, getByLabelText } = render(<Game mode="human" />)
      const crossMoves = [1, 2, 4]
      const noughtMoves = [3, 5, 7]

      playGame(crossMoves, noughtMoves, getByLabelText)

      expect(getByText('Player O wins!')).toBeInTheDocument()
    })

    it('can play a complete game that ends in a draw', () => {
      const { getByText, getByLabelText } = render(<Game mode="human" />)
      const crossMoves = [1, 2, 7, 6, 9]
      const noughtMoves = [5, 3, 4, 8]

      playGame(crossMoves, noughtMoves, getByLabelText)

      expect(getByText(/It's a draw!/)).toBeInTheDocument()
    })
  })
})

afterEach(cleanup)

const playGame = (crossMoves, noughtMoves, query) => {
  combineAlternating(crossMoves, noughtMoves).forEach(move =>
    fireEvent.click(query(`Square ${move}`))
  )
}

const combineAlternating = (current, alternate, combined = []) => {
  if (current.length === 0) {
    return combined.concat(alternate)
  }

  return combineAlternating(
    alternate,
    tail(current),
    combined.concat(head(current))
  )
}

const head = array => array.slice(0, 1)

const tail = array => array.slice(1)
