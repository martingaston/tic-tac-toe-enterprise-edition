const nextTurnUrl = 'http://localhost:5000/api/getNextTurn'
const getNextTurnFromServer = (position, game) =>
  converseWithServer(nextTurnUrl, { ...game, position: position })

const converseWithServer = (url, request) => {
  const options = {
    method: 'POST',
    body: JSON.stringify(request),
    headers: {
      'Content-Type': 'application/json',
    },
  }

  return fetch(url, options)
    .then(response => response.json())
    .catch(error =>
      console.error('There was an error talking to the server: ', error)
    )
}

export { getNextTurnFromServer }
