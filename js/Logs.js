logs = [];
class Logs {
    constructor(parent, child, items) {
        this.parent = parent;
        this.child = child;
        this.items = items;
    }

    static addLog(parent, child, items) {
        logs.push(new Logs(parent, child, items));
    }

    static getRecent() {
        return logs.slice(-1)[0];
    }

    static getRecentAndDelete() {
        return logs.pop();
    }
}