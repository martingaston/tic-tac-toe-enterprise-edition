import React from 'react'
import Row from './Row'
import { render, cleanup } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

describe('the Row component', () => {
  describe('will render an empty first row', () => {
    let getByLabelText

    beforeEach(() => {
      const row = [null, null, null]
      const rendered = render(
        <Row squares={row} zeroIndexedRowNumber={0} onClick={null} />
      )
      getByLabelText = rendered.getByLabelText
    })

    it('renders the first square of an empty row', () => {
      const firstSquare = getByLabelText('Square 1')

      expect(firstSquare).toBeInTheDocument()
      expect(firstSquare).toBeEmpty()
    })

    it('renders the second square of an empty row', () => {
      const secondSquare = getByLabelText('Square 2')

      expect(secondSquare).toBeInTheDocument()
      expect(secondSquare).toBeEmpty()
    })

    it('renders the third square of an empty row', () => {
      const thirdSquare = getByLabelText('Square 3')

      expect(thirdSquare).toBeInTheDocument()
      expect(thirdSquare).toBeEmpty()
    })
  })

  describe('will render an occupied second row', () => {
    let getByLabelText

    beforeEach(() => {
      const row = ['X', 'O', 'X']
      const rendered = render(
        <Row squares={row} zeroIndexedRowNumber={1} onClick={null} />
      )
      getByLabelText = rendered.getByLabelText
    })

    it('renders the first square of an occupied row', () => {
      const firstSquare = getByLabelText('Square 4')

      expect(firstSquare).toBeInTheDocument()
      expect(firstSquare).toHaveTextContent('X')
    })

    it('renders the second square of an occupied row', () => {
      const secondSquare = getByLabelText('Square 5')

      expect(secondSquare).toBeInTheDocument()
      expect(secondSquare).toHaveTextContent('O')
    })

    it('renders the third square of an occupied row', () => {
      const thirdSquare = getByLabelText('Square 6')

      expect(thirdSquare).toBeInTheDocument()
      expect(thirdSquare).toHaveTextContent('X')
    })
  })
})

afterEach(cleanup)
