let colors = ['black', 'yellow','green', 'white'];
let cursor = 0;
let text_color;

function theme(id) {
    let body = document.getElementById('body');
    let element = document.getElementById(id);
    let properties = getComputedStyle(element, null);
    body.style.background = properties.backgroundColor;
    body.style.color = properties.color;
}

function switch_theme() {
    let body = document.getElementById('body');
    cursor++;
    text_color = 0;
    if  (cursor === 4) {
        cursor = 0;
        text_color = 3;
    }
    body.style.color = colors[text_color];
    body.style.background = colors[cursor];
}