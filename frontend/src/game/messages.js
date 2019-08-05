export default {
  title: 'TIC TAC TOE',
  intro:
    'Win untold glory by successfully placing a complete line in any horizontal, vertical or diagonal direction.',
  instructions:
    'Choose a number between 1 and 9 on alternate turns to place your mark in the 3x3 grid.',
  nan: "Sorry, that doesn't seem to be a valid number. Please try again: ",
  outOfBounds:
    "Sorry, that number isn't available on the board. Please try again: ",
  occupied: 'Dang - your opponent got there before you! Try again: ',
  draw: "How unsatisfying! It's a draw!",
  unknownError: "Well, something's gone wrong somewhere.",
  winner: mark => `Player ${mark} wins!`,
  turn: mark => `Player ${mark}'s turn`,
}
