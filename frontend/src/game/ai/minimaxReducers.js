const bestPositionReducer = (maximum, currentPosition) => {
  const position = currentPosition[0]
  const value = currentPosition[1]

  return value > maximum.value ? { position, value } : maximum
}

const minimumReducer = (acc, x) => (x < acc ? x : acc)

const maximumReducer = (acc, x) => (x > acc ? x : acc)

export { bestPositionReducer, minimumReducer, maximumReducer }
