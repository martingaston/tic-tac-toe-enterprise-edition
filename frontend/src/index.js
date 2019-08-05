import React from 'react'
import ReactDOM from 'react-dom'
import './global.css'
import Game from './components/Game'
import { GAME_MODE } from './constants'

ReactDOM.render(<Game mode={GAME_MODE} />, document.getElementById('root'))
