items = [];
itemClass = [];
leftButton = $("#leftButton");
rightButton = $("#rightButton");
startButton = $("#start");
editButton = $("#edit");
leftButton.attr('disabled', true);
rightButton.attr('disabled', true);
leftItem = new Item();
rightItem = new Item();

function setList(listItems) {
    itemLists = "";
    listItems.forEach(function (element) {
        template = "<li class=\"list-group-item\">" + element + "</li>";
        itemLists += template;
    });

    return "<ul class=\"list-group list-group-flash\">" + itemLists + "</ul>";
}

function saveList() {
    items = ($("#textarea").val());
    items = items.split("\n");
    $("#list").html(setList(items));
    if (items.length > 0) {
        startButton.attr('disabled', false);
    } else {
        startButton.attr('disabled', true);
    }
}

function clearTextArea() {
    $("#textarea").val("");
}

startButton.click(function () {
    if (items.length >= 2) {
        itemClass = [];
        editButton.attr('disabled', true);
        leftButton.attr('disabled', false);
        rightButton.attr('disabled', false);
        items.forEach(function (element) {
            itemClass.push(new Item(element));
        });
        randomizeItems();
        leftButton.text(leftItem.getName());
        rightButton.text(rightItem.getName());
    }
});

function randomizeItems() {
    do {
        r1 = parseInt(Math.random() * itemClass.length);
        r2 = parseInt(Math.random() * itemClass.length);
        leftItem = itemClass[r1];
        rightItem = itemClass[r2];
    } while (r1 === r2);
}

leftButton.click(function () {
    repeat = 0;
    leftItem.addGreater(rightItem);
    console.log(leftItem.getName() + " is greater than " + rightItem.getName());
    do {
        if (repeat > itemClass.length * itemClass.length) {
            finished()
            return;
        }
        randomizeItems();
        repeat++;
    } while (jQuery.inArray(rightItem, leftItem.greater) >= 0 || jQuery.inArray(leftItem, rightItem.greater) >= 0);
    leftButton.text(leftItem.getName());
    rightButton.text(rightItem.getName());
})

rightButton.click(function () {
    repeat = 0;
    console.log(rightItem.getName() + " is greater than " + leftItem.getName());
    rightItem.addGreater(leftItem);
    do {
        if (repeat > itemClass.length * itemClass.length) {
            finished()
            return;
        }
        randomizeItems();
        repeat++;
    } while (jQuery.inArray(rightItem, leftItem.greater) >= 0 || jQuery.inArray(leftItem, rightItem.greater) >= 0);
    leftButton.text(leftItem.getName());
    rightButton.text(rightItem.getName());
})

function finished() {
    console.log('finished!');
    itemClass.sort(compare);
    console.log(itemClass);
    reset();
    sortedItems = [];
    count = 1;
    itemClass.forEach(function (e) {
        sortedItems.push(count + ". " + e.getName());
        count++;
    });
    $("#sortedList").html(setList(sortedItems));
    $("#modal2").modal("show");
}

function compare(a, b) {
    if (Object.keys(a.greater).length < Object.keys(b.greater).length)
        return 1;
    if (Object.keys(a.greater).length > Object.keys(b.greater).length)
        return -1;
    return 0;
}

function reset() {
    editButton.attr('disabled', false);
    leftButton.attr('disabled', true);
    rightButton.attr('disabled', true);
    leftButton.text("");
    rightButton.text("");
}