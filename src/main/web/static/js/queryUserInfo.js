$(function () {
    query();
    queryYesterdayInfo();
    setInterval(queryUserStep, 5000);
});
