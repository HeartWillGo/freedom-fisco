$(function () {
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    if (getQueryString("tradeStatus") == "TRADE_SUCCESS") {
        $("#paySuccessModal").modal("show");
        $('.overlay').fadeIn(200);
        paySuccess();

    } else if ((getQueryString("tradeStatus") == "TRADE_CLOSED") || (getQueryString("tradeStatus") == "TRADE_CANCEL") || (getQueryString("tradeStatus") == "WAIT_PAY")) {
        $("#payFailedModal").modal("show");
        $('.overlay').fadeIn(200);
    }


});

function paySuccess() {

    setCookie("hasParticipate", true);
    var payDiv = document.getElementById("challege-trigger");
    var tagA = document.getElementById("challegeInfo");

    $("#challege-trigger").unbind("click");
    payDiv.style.background = "#A1BFFF";

    tagA.innerHTML = "明天4点后开奖";
    tagA.style.color = "#0035A1";
    document.getElementById("challege-trigger").style.visibility = "visible";//显示
    var challengeId = getCookie("challengeId");
    queryUserStepNumber(challengeId, true);
    setTimeout(query, 120000);


}


