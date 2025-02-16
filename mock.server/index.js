import state from './data/coindesk/data.js'
import express from 'express'
import bodyParser from 'body-parser'
import {renewBpiSchedule} from "./schedule/index.js"

const app = express()
app.use(bodyParser.json({ type: 'application/*+json' }))

app.get('/', function (req, res) {
    res.send(state);
})

renewBpiSchedule(state)

app.listen(3000)

console.log('mock data server start at :3000')