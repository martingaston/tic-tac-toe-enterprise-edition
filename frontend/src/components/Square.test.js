import React from 'react'
import Square from './Square'
import { render, fireEvent, cleanup } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

afterEach(cleanup)

describe('the Square component', () => {
  it('can render an empty square', () => {
    const { getByLabelText } = render(
      <Square zeroIndexedPosition={0} value={null} />
    )

    const square = getByLabelText('Square 1')
    expect(square).toBeEmpty()
  })

  it('will let you click on an empty square', () => {
    const onClick = jest.fn()
    const { getByLabelText } = render(
      <Square zeroIndexedPosition={4} value={null} onClick={onClick} />
    )
    const square = getByLabelText('Square 5')
    const position = 5

    fireEvent.click(square)

    expect(onClick).toHaveBeenCalledTimes(1)
    expect(onClick).toHaveBeenCalledWith(position)
  })

  it('can be rendered with a cross', () => {
    const { getByLabelText } = render(
      <Square zeroIndexedPosition={0} value="X" />
    )

    const square = getByLabelText('Square 1')

    expect(square).toHaveTextContent('X')
  })

  it('will not let you click on an occupied square', () => {
    const onClick = jest.fn()
    const { getByLabelText } = render(
      <Square zeroIndexedPosition={0} value="X" onClick={onClick} />
    )
    const square = getByLabelText('Square 1')

    fireEvent.click(square)

    expect(onClick).not.toHaveBeenCalled()
  })
})
