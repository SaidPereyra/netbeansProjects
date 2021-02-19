function myFunction() {
    var input, filter, card, h5, a, i;
    input = document.getElementById("myFilter");
    filter = input.value.toUpperCase();
    card = document.getElementById("myItems");
    h5 = card.getElementsByTagName("h4");
    for (i = 0; i < h5.length; i++) {
        a = h5[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            h5[i].parentElement.style.display = "";
        } else {
            h5[i].parentElement.style.display = "none";
        }
    }
}