function queryUserStepNumber(challengeId, hasParticipate) {
    $.ajax({
        url: '/challenge/queryUserStepNumber',
        type: 'get',
        data: {challengeId: challengeId},
        dataType: 'json',

        success: function (res) {
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                console.log("queryUserStepNumber result{}", jsData);
                var step = jsData.todayStepNumber;
                var target = jsData.accomplishStandard;
                var lastStep = getCookie("step");
                var lastTarget = getCookie("target");
                // if ((typeof (lastStep) == "undefined") || (typeof (lastTarget) == "undefined") || (lastTarget == null) || (lastStep == null)) {
                //     drawStepProcess(step, target, hasParticipate);
                // } else if (lastStep != step || lastTarget != target) {
                //     drawStepProcess(step, target, hasParticipate);
                // }
                drawStepProcess(step, target, hasParticipate);
                setCookie("step", step);
                setCookie("target", target);
            } else {
                drawStepProcess(0, 8000, hasParticipate);
            }
        },
        error: function (res) {
            drawStepProcess(0, 8000, hasParticipate);
        }
    });
}

function queryUserStep() {
    var hasParticipate = getCookie("hasParticipate");
    var challengeId = getCookie("challengeId");
    if (typeof (hasParticipate) == "undefined" || (typeof (challengeId) == "undefined") || hasParticipate == null || challengeId == null) {
        query();
        return;
    }
    $.ajax({
        url: '/challenge/queryUserStepNumber',
        type: 'get',
        data: {challengeId: challengeId},
        dataType: 'json',

        success: function (res) {
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                console.log("queryUserStep result{}", jsData);
                var step = jsData.todayStepNumber;
                var target = jsData.accomplishStandard;
                var lastStep = getCookie("step");
                var lastTarget = getCookie("target");
                if ((typeof (lastStep) == "undefined") || (typeof (lastTarget) == "undefined") || (lastTarget == null) || (lastStep == null)) {
                    drawStepProcess(step, target);
                } else if (lastStep != step || lastTarget != target) {
                    drawStepProcess(step, target);
                }
                setCookie("step", step);
                setCookie("target", target);
            } else {
                drawStepProcess(0, 8000);
            }
        },
        error: function (res) {
            drawStepProcess(0, 8000);
        }
    });
}

function query() {
    $.ajax({
        url: '/challenge/query',
        type: 'get',
        dataType: 'json',
        data: {authorizationType: "10001"},
        success: function (res) {
            console.log("res result{}", res);
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                var hasAuthorized = jsData.hasAuthorized;
                var iconUrl = jsData.iconUrl;
                console.log("jsData  {}", jsData);
                if ((hasAuthorized == "false") || hasAuthorized == false) {
                    $("#challegeBackgroundModal").modal("show");
                    $('.overlay').fadeIn(200);
                } else if ((hasAuthorized == "true") ||
                    (hasAuthorized == true)) {
                    if (typeof(iconUrl) != "undefined" && iconUrl != null) {
                        var myHead = document.getElementById("myImages");
                        myHead.src = iconUrl;
                    }
                    var todayUserChallenge = jsData.todayUserChallenge;
                    var challengeId;
                    var hasParticipatedChallenge;
                    if (typeof(todayUserChallenge) != "undefined" && todayUserChallenge != null) {
                        challengeId = todayUserChallenge.challengeId;
                        if (challengeId != null && typeof(challengeId) != "undefined") {
                            setCookie("challengeId", challengeId);
                        }
                        hasParticipatedChallenge = todayUserChallenge.hasParticipatedChallenge;
                        console.log("hasParticipatedChallenge result{}", hasParticipatedChallenge);
                        var challengeEndTime = todayUserChallenge.challengeEndTime;
                        if (challengeEndTime != null && (typeof(challengeEndTime) != "undefined")) {
                            setCookie("challengeEndTime", challengeEndTime);
                        }
                        if (hasParticipatedChallenge == true) {
                            setCookie("hasParticipate", true);
                            queryUserStepNumber(challengeId, true);
                        } else {
                            setCookie("hasParticipate", false);
                            queryUserStepNumber(challengeId, false);
                        }
                        queryChallenge(challengeId);
                    } else {
                        drawStepProcess(0, 8000, true);
                        $('#poolValue').text(getMoneyStr(0, 2));
                        $('#participateValue').text(0);
                        document.getElementById("challege-trigger").style.visibility = "visible";//显示
                        var dateTime = new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000;
                        setCookie("challengeEndTime", dateTime);
                    }
                    var payDiv = document.getElementById("challege-trigger");
                    var tagA = document.getElementById("challegeInfo");
                    if (hasParticipatedChallenge == true) {
                        $("#challege-trigger").unbind("click");
                        payDiv.style.background = "#A1BFFF";

                        tagA.innerHTML = "明天4点后开奖";
                        tagA.style.color = "#0035A1";
                        document.getElementById("challege-trigger").style.visibility = "visible";//显示
                    } else {
                        document.getElementById("challege-trigger").style.visibility = "visible";//显示
                    }
                    var lastAccomplishRecord = jsData.lastAccomplishRecord;
                    if (typeof(lastAccomplishRecord) != "undefined" && lastAccomplishRecord != null) {
                        var isAccomplish = lastAccomplishRecord.isAccomplish;
                        var bonus = lastAccomplishRecord.bonus;
                        var accomplishNumber = lastAccomplishRecord.accomplishNumber;
                        var totalBonus = lastAccomplishRecord.totalBonus;
                        var stepNumber = lastAccomplishRecord.stepNumber;
                        if (isAccomplish == "true" || isAccomplish == true) {
                            $("#challegeSuccessModal").modal("show");
                            $('#successMoney').text(getMoneyStr(bonus, 2));
                            $('.overlay').fadeIn(200);
                        } else if (isAccomplish == "false" || isAccomplish == false) {
                            $("#challegeFailedModal").modal("show");
                            $('#FailedNum').text(accomplishNumber);
                            $('#FailedMoney').text(getMoneyStr(totalBonus, 2));
                            $('#FailedStep').text(stepNumber);
                            $('.overlay').fadeIn(200);
                        }
                    }

                }
            } else if (res.resCode == "100000") {
                $("#challegeBackgroundModal").modal("show");
                drawStepProcess(0, 8000, true);
                $('#poolValue').text(getMoneyStr(0, 2));
                $('#participateValue').text(0);
                $('.overlay').fadeIn(200);
                document.getElementById("challege-trigger").style.visibility = "visible";//显示
                var dateTime = new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000;
                setCookie("challengeEndTime", dateTime);
            } else {

                drawStepProcess(0, 8000, true);
                $('#poolValue').text(getMoneyStr(0, 2));
                $('#participateValue').text(0);
                document.getElementById("challege-trigger").style.visibility = "visible";//显示
                var dateTime = new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000;
                setCookie("challengeEndTime", dateTime);
            }
        },
        error: function (res) {
            drawStepProcess(0, 8000, true);
            $('#poolValue').text(getMoneyStr(0, 2));
            $('#participateValue').text(0);
            var dateTime = new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000;
            setCookie("challengeEndTime", dateTime);
            document.getElementById("challege-trigger").style.visibility = "visible";//显示
        }
    });
}

function queryChallenge(challengeId) {
    $.ajax({
        url: '/challenge/queryChallenge',
        type: 'get',
        data: {challengeId: challengeId},
        dataType: 'json',
        success: function (res) {
            console.log("queryChallenge res result{}", res);
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                console.log("jsData  {}", jsData);

                $('#poolValue').text(getMoneyStr(jsData.participateNumber, 2));
                $('#participateValue').text(jsData.participateNumber);
            } else {
                $('#poolValue').text(getMoneyStr(0, 2));
                $('#participateValue').text(0);
            }
        },
        error: function (XMLHttpRequest) {
            $('#poolValue').text(getMoneyStr(0, 2));
            $('#participateValue').text(0);

        }
    });
}

function drawStepProcess(step, target) {
    var width = document.getElementById("circle_step2").clientWidth;
    var value = step / target;
    var startColor = "#ffa353";
    var endColor = "#ff7700";
    if (value > 1) {
        value = 1;
        startColor = "#5dbffb";
        endColor = "#3aa0ff";
    }
    var stepInfo = "今日步数";
    var thickness = 'auto';
    var hasParticipate = getCookie("hasParticipate");
    if (!hasParticipate||hasParticipate=='false') {
        stepInfo = "今日未参加";

    }
    if (step == 0) {
        thickness = 3;
    }
    console.log("drawStepProcess hasParticipate{}", hasParticipate);
    document.getElementById("SuccessStep").style.display = "none";
    $('#circle_step2').circleProgress({

        value: value,//你需要展示的值，值从0.0到1.0，默认值为0
        size: width,//环形图的大小，单位像素，默认值100
        startAngle: -1.57,//初始角度，默认值为-Math.PI
        reverse: false,//是否反向绘制圆弧和动画，默认值为false
        lineCap: 'round',//圆弧的线头样式："butt"、"round"和"square"。默认值为"butt"
        thickness: thickness,//进度条圆弧的宽度。默认它自动为size的1/14大小，你可以设置你需要的值。默认值为auto
        emptyFill: 'rgba(0, 0, 0, .1)',//空圆弧的颜色。默认值为"rgba(0, 0, 0, .1)"
        fill: {
            gradient: [startColor, endColor]
        }
    }).on('circle-animation-progress', function (event, progress, stepValue) {//当图像正在绘制时的监听事件
        $(this).find('a').html(String(stepInfo));
        $(this).find('p').html(String(step));
        $(this).find('span').html(String("目标" + target + "步"));
    });
    if (value >= 1 && hasParticipate) {
        setTimeout(stepSuccess, 1500);
    }

}

function stepSuccess() {
    var div = document.getElementById("SuccessStep");
    div.style.display = "block";//显示
    div.style.backgroundImage = "linear-gradient(-129deg, #5CBEFB 0%, #40A5FF 100%)";

    div.innerHTML = "已达标";
}

function getMoneyStr(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

//
// function stopChallenge() {
//     var yourtime = '2018-08-23 19:45';
//     var d2 = new Date();//取今天的日期
//     var d1 = new Date(yourtime);
//     if(typeof (d1)=="undefined"||d1==null||d1=="Invalid Date"){
//         d1=new Date(yourtime.split('-').join('/'));
//     }
//     // if (d1 < d2) {
//     //     var payDiv = document.getElementById("challege-trigger");
//     //     var tagA = document.getElementById("challegeInfo");
//     //     $("#challege-trigger").unbind("click");
//     //     payDiv.style.background = "#A1BFFF";
//     //
//     //     tagA.innerHTML = "服务升级中";
//     //     tagA.style.color = "#0035A1";
//     //     document.getElementById("challege-trigger").style.visibility = "visible";//显示
//     //     document.getElementById('end').style.display='none';//show的display属性设置为none（隐藏）
//     //
//     // }
// }