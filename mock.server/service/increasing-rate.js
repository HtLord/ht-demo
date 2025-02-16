
function _updateDataset(dataset) {
    dataset.forEach(
            (value, idx) => {
                _updateBpis(value.bpi)
            }
        )
}

function _updateBpis(bpis) {
    for (let key in bpis) {
        bpis[key]["rate_float"] += 1
    }
}

function renew(source) {
    _updateDataset(source.data)
}


export {
    renew,
}