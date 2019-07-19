function challege_popup() {
    $('.overlay').fadeOut(200);
    var challengeId = getCookie("challengeId");
    $.ajax({
        url: '/challenge/participate',
        type: 'get',
        dataType: 'json',
        data: {challengeId: challengeId},

        success: function (res) {
            console.info("res url{}", res);
            if (res.resCode == "200") {
                var resData = jQuery.parseJSON(res.resData);
                window.location.href = resData.payUrl;
            } else if (res.resCode == "100005") {
                $("#singupFailedModal2").modal("show");
                var singupA = document.getElementById("singupFailedTagMid2");
                singupA.innerHTML = res.resDesc;
                $('.overlay').fadeIn(200);
            }
            else {
                $("#singupFailedModal").modal("show");
                var singupA = document.getElementById("singupFailedTagMid");
                singupA.innerHTML = res.resDesc;
                $('.overlay').fadeIn(200);
            }
        },
        error: function (res) {
            if (res.resCode == "500002") {
                $("#payFailedModal").modal("show");
                $('.overlay').fadeIn(200);
            }
        }
    });
}

function payTomorrow() {
    var challengeId = getCookie("tomorrowChallengeId");
    $.ajax({
        url: '/challenge/participate',
        type: 'get',
        dataType: 'json',
        data: {challengeId: challengeId},

        success: function (res) {
            console.info("res url{}", res);
            if (res.resCode == "200") {
                var resData = jQuery.parseJSON(res.resData);

                window.location.href = resData.payUrl;
            } else if (res.resCode == "100005") {

                $("#singupFailedModal2").modal("show");
                var singupA = document.getElementById("singupFailedTagMid2");
                singupA.innerHTML = res.resDesc;
                $('.overlay').fadeIn(200);
            }
            else {
                $("#singupFailedModal").modal("show");
                var singupA = document.getElementById("singupFailedTagMid");
                singupA.innerHTML = res.resDesc;
                $('.overlay').fadeIn(200);
            }
        },

        error: function (res) {
            if (res.resCode == "500002") {
                $("#payFailedModal").modal("show");
            }

        }
    });
}

$(".challege-trigger").on("click", challege_popup), function () {

}();

$(".rePay").on("click", challege_popup), function () {

}();
$(".rePayTomorrowChallegeFailed").on("click", overlayFadeOut), function () {

}();
$(".rePayTomorrowChallegeSuccess").on("click", overlayFadeOut), function () {

}();
$(".rePayTomorrow2").on("click", openXiaomiVip), function () {

}();

$(".rePayTomorrow").on("click", overlayFadeOut), function () {

}();
$(".rule").on("click", rule_popup), function () {

}();

$(".myImages").on("click", openMyScore), function () {

}();
$(".myScoreReturn").on("click", returnMyCenter), function () {

}();
$(".paySuccessKnowDiv").on("click", overlayFadeOut), function () {

}();
$(".synchronizingStep").on("click", synchronizingStep), function () {

}();
$(".close").on("click", overlayFadeOut), function () {

}();
$(".synchronizingStepFailedKnowDiv").on("click", overlayFadeOut), function () {

}();
$(".synchronizingStepSuccessKnowDiv").on("click", overlayFadeOut), function () {

}();
// $(".picDiv").on("click", openMyScore), function () {
//
// }();


// $('.picDiv').on('click', function(event) {
//     var Eventsource = event.target || event.srcElement;
//     alert(Eventsource.id);
// });
function overlayFadeOut() {
    $('.overlay').fadeOut(200);
}
// function rePayTomorrowChallegeFailed() {
//     $('.overlay').fadeOut(200);
// }
// function rePayTomorrowChallegeSuccess() {
//     $('.overlay').fadeOut(200);
// }
// function rePayTomorrow() {
//     $('.overlay').fadeOut(200);
// }
// function payedReturn() {
//     $('.overlay').fadeOut(200);
// }



function rule_popup() {
    $("#ruleModal").modal("show");
    $('.overlay').fadeIn(200);

}

function openMyScore() {
    window.location.href = 'myInfo.html';


}

function returnMyCenter() {
    window.location.href = '/';


}

function openXiaomiVip() {
    $('.overlay').fadeOut(200);
    window.location.href = 'http://vipaccount.miui.com/event/detail?record=3&actionbarTitle=计步';


}

function synchronizingStep() {
    var challengeId = getCookie("challengeId");
    if (typeof (challengeId)=="undefined"||challengeId==null) {
        query();
    }
    $.ajax({
        url: '/challenge/submitStepNumber',
        type: 'get',
        dataType: 'json',
        data: {challengeId: challengeId},

        success: function (res) {
            if (res.resCode == "200") {
                $("#synchronizingStepSuccessModal").modal("show");
                $('.overlay').fadeIn(200);
            }
            else {
                $("#synchronizingStepFailedModal").modal("show");
                $('.overlay').fadeIn(200);
            }
        },

        error: function (res) {
            $("#synchronizingStepFailedModal").modal("show");
            $('.overlay').fadeIn(200);

        }
    });
}


