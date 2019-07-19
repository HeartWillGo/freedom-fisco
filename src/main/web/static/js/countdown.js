$(function () {
    timedown();

    function timedown() {
        var nowTime = new Date().getTime();
        var time = getCookie("challengeEndTime");
        if (time == null || typeof (time) == "undefined") {
            time = new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000;

        }
        var longTime = parseInt(time);
        var t = longTime - nowTime;
        var d = Math.floor(t / 1000 / 60 / 60 / 24);
        var h = Math.floor(t / 1000 / 60 / 60 % 24);
        var i = Math.floor(t / 1000 / 60 % 60);
        var s = Math.floor(t / 1000 % 60);

        // document.getElementById('d').innerHTML = d;
        document.getElementById('h').innerHTML = fix(h, 2);
        document.getElementById('i').innerHTML = fix(i, 2);
        document.getElementById('s').innerHTML = fix(s, 2);
        setTimeout(timedown, 1000);
    }
});


function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}