function queryYesterdayInfo() {

    $.ajax({
        url: '/challenge/queryStatistics',
        type: 'get',
        dataType: 'json',

        success: function (res) {
            if (res.resCode == "200") {
                var jsData = jQuery.parseJSON(res.resData);
                console.log("queryStatistics result{}", jsData);
                var accomplishNumber = jsData.accomplishNumber;
                var nonAccomplishNumber = jsData.nonAccomplishNumber;
                $('#successPeople').text(accomplishNumber);
                $('#failPeople').text(nonAccomplishNumber);

                var theXiaomiIdOfyesterdayMostStepNumber = jsData.theInfoOfYesterdayMostBonus[0];
                $('#stepNumName').text(theXiaomiIdOfyesterdayMostStepNumber.nickname);
                $('#maxStepNumber').text(theXiaomiIdOfyesterdayMostStepNumber.bonus);
                $('.historyPeopleAward').text(theXiaomiIdOfyesterdayMostStepNumber.desc);
                var maxStepPeopleIcon = theXiaomiIdOfyesterdayMostStepNumber.iconUrl;
                if (typeof(maxStepPeopleIcon) != "undefined" && maxStepPeopleIcon != null) {
                    document.getElementById("maxStepPeopleIcon").src = maxStepPeopleIcon;
                }

                var theXiaomiIdOfYesterdayMostBonus = jsData.theInfoOfYesterdayMostBonus[1];
                $('#awardPeople').text(theXiaomiIdOfYesterdayMostBonus.nickname);
                $('#maxAward').text(theXiaomiIdOfYesterdayMostBonus.bonus);
                $('.historyPeopleAward2').text(theXiaomiIdOfYesterdayMostBonus.desc);
                var maxAwardPeopleIcon = theXiaomiIdOfYesterdayMostBonus.iconUrl;
                if (typeof(maxAwardPeopleIcon) != "undefined" && maxAwardPeopleIcon != null) {
                    document.getElementById("maxAwardPeopleIcon").src = maxAwardPeopleIcon;
                }
                var theXiaomiIdOfMostAccomplishTimes = jsData.theInfoOfYesterdayMostBonus[2];
                $('#maxDayPeople').text(theXiaomiIdOfMostAccomplishTimes.nickname);
                $('#maxDay').text(theXiaomiIdOfMostAccomplishTimes.bonus);
                $('.historyPeopleAward3').text(theXiaomiIdOfYesterdayMostBonus.desc);
                var maxDayPeopleIcon = theXiaomiIdOfMostAccomplishTimes.iconUrl;
                if (typeof(maxDayPeopleIcon) != "undefined" && maxDayPeopleIcon != null) {
                    document.getElementById("maxDayPeopleIcon").src = maxDayPeopleIcon;
                }
            } else {
                document.getElementById("hisroryInfo").style.display = "none";//显示
            }
        },
        error: function (res) {
            document.getElementById("hisroryInfo").style.display = "none";//显示

        }
    });
}
