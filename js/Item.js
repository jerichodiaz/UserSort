class Item {
    constructor(name) {
        this.greater = [];
        this.name = name;
    }

    setName(name) {
        this.name = name
    }

    getName() {
        return this.name;
    }

    addGreater(item) {
        let added = [];
        added.push(item);
        let exists = false;
        console.log(item);
        item.greater.forEach(element => {
            console.log(element);
            exists = false;
            this.greater.forEach(i => {
                if (element.getName() === i.getName()) {
                    exists = true;
                }
            });
            if (!exists) {
                this.greater.push(element);
                added.push(element);
            }
        });
        this.greater.push(item);
        Logs.addLog(this, item, added);
    }
}