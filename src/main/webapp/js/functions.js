function toggle(showHideDiv) {
    var elem = document.getElementById(showHideDiv);
    if(elem.style.display == "block") {
        elem.style.display = "none";
    }
    else {
        elem.style.display = "block";
    }
}

function selectFirstItemRow() {
    sel = document.getElementById("automobileselect");
    sel.fireEvent("onchange");
}

function showItemBySelect(elem) {
    var items = document.getElementsByClassName("item");
    var selectedId = elem.value;
    for (i = 0; i < items.length; i++) {
        if(items[i].id == selectedId) {
            items[i].style.display="";
        } else {
            items[i].style.display="none";
        }
    }
}

function showItemById(id) {
    document.getElementById(id).style.display = "block";
}

function validate_form(errorString){
    var items = document.getElementsByClassName("inputform");
    for (i = 0; i < items.length; i++) {
        if (items[i].value === "") {
            document.getElementById('errorStatus').style.display = "block";
            document.getElementById('errorStatus').getElementsByTagName('a').item(0).textContent = errorString;
            return false;
        }
    }
    document.getElementById('errorStatus').style.display = "none";
    document.getElementById('errorStatus').getElementsByTagName('a').item(0).textContent = '';
    return true;
}
