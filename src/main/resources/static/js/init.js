$(function () {
    intiSize();
    function intiSize() {
        var deviceWidth = document.documentElement.clientWidth;
        if (deviceWidth > 1080) {
            deviceWidth = 1080;
        }
        document.documentElement.style.fontSize = deviceWidth / 37.5 + 'px';
        //获取当前浏览器窗口宽度(这里的实质就是body宽度)
    }

    // $("#challegeBackgroundModal").modal("show")



});