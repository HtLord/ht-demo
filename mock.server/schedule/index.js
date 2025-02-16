import {renew} from "../service/increasing-rate.js";

function renewBpiSchedule(dataset) {
    return setInterval(
        () => {
            console.log(dataset.data[0])
            renew(dataset)
        },
        10000
    )
}

export {
    renewBpiSchedule,
}