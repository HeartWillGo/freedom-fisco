$(function () {
    queryScore();

});

function queryScore() {
    $.ajax({
        url: '/challenge/listUserChallenge',
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log("res result{}", res);
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                var userChallengeHistory = jsData.userChallengeHistory;
                $('#dayNum').text(jsData.accomplishTimes);
                $('#moneyCount').text(getMoneyStr(jsData.accumulateEarning, 2));
                $('#challegeCount').text(jsData.participateTimes);

                showScore(userChallengeHistory);
            } else {
                $('#dayNum').text(0);
                $('#moneyCount').text(getMoneyStr(0, 2));
                $('#challegeCount').text(0);
            }

        },

        error: function (XMLHttpRequest) {
            $('#dayNum').text(0);
            $('#moneyCount').text(getMoneyStr(0, 2));
            $('#challegeCount').text(0);

        }
    });
}


function showScore(userChallengeHistory) {
    var div = document.getElementById("result");
    for (var i in  userChallengeHistory) {
        var userScore = userChallengeHistory[i];
        console.log("userScore{}", userScore);
        //获取div
        var divChild = document.createElement("div")
        divChild.style.width = '100%';
        divChild.style.height = '1.9rem';

        var line = document.createElement("div");
        line.style.marginLeft = '0.9rem';
        line.style.height = '1px';
        line.style.background = '#D9D9D9';

        var resultLeft = document.createElement("div");
        resultLeft.style.width = '70%';
        resultLeft.style.cssFloat = 'left';
        resultLeft.style.height = '1.86rem';
        //换行
        var resultTag = document.createElement("div");
        resultTag.style.marginTop = '0.45rem';
        resultTag.style.fontSize = '0.45rem';
        resultTag.style.color = '#000000';
        resultTag.style.lineHeight = '0.45rem';
        resultTag.style.width = '100%';


        var resultValue = document.createElement("a");


        resultValue.style.marginLeft = '0.9rem';
        resultValue.style.fontSize = '0.45rem';
        resultValue.style.color = '#000000';
        resultValue.style.lineHeight = '0.45rem';
        resultValue.style.width = '100%';


        var resultTimeAndStep = document.createElement("div");
        resultTimeAndStep.style.marginTop = '0.15rem';
        resultTimeAndStep.style.width = '100%';
        var resultTime = document.createElement("a");
        resultTime.style.marginTop = '0.15rem';
        resultTime.style.marginLeft = '0.9rem';
        resultTime.style.fontSize = '0.36rem';
        resultTime.style.color = '#9B9B9B';
        resultTime.style.lineHeight = '0.36rem';
        if (typeof(userScore.stepNumber) != "undefined" && userScore.stepNumber != null) {
            var timeAndStep = userScore.challengeDate.substring(0, 10) + "|" + userScore.stepNumber + "步";
        } else {
            var timeAndStep = userScore.challengeDate.substring(0, 10) + "|" + 0 + "步";
        }
        resultTime.innerHTML = timeAndStep;


        var resultRight = document.createElement("div");
        resultRight.style.width = '30%';
        resultRight.style.cssFloat = 'left';
        resultRight.style.textAlign = 'right';
        resultRight.style.height = '1.86rem';
        var resultMoney = document.createElement("a");
        resultMoney.style.textAlign = 'right';
        resultMoney.style.marginRight = '0.9rem';


        resultMoney.style.fontSize = '0.54rem';
        resultMoney.style.color = '#000000';
        resultMoney.style.lineHeight = '1.86rem';


        resultValue.innerHTML = userScore.isAccomplish;
        resultMoney.innerHTML = userScore.bonus;


        resultTag.appendChild(resultValue);
        resultTimeAndStep.appendChild(resultTime);

        resultLeft.appendChild(resultTag);
        resultLeft.appendChild(resultTimeAndStep);
        resultRight.appendChild(resultMoney);

        divChild.appendChild(line);
        divChild.appendChild(resultLeft);
        divChild.appendChild(resultRight);

        div.appendChild(divChild);

    }
    var endLine = document.createElement("div");
    endLine.style.marginLeft = '0.9rem';
    endLine.style.height = '1px';
    endLine.style.background = '#D9D9D9';
    div.appendChild(endLine);
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