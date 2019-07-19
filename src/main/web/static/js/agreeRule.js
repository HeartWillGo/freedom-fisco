function agreeRule() {
    $.ajax({
        url: '/challenge/userAuthorization',
        type: 'get',
        dataType: 'json',
        data: {authorizationType:"10001"},
        success: function (res) {
            console.info("res url{}", res);
            var resCode=res.resCode;
            if(resCode=="200"){
                $("#challegeBackgroundModal").modal("hide");
                $('.overlay').fadeOut(200);
                query();
            }else{
                alert("授权失败");
                $("#challegeBackgroundModal").modal("show");
                $('.overlay').fadeIn(200);
            }
        },
        error: function (XMLHttpRequest ) {
            alert("授权失败");
            $("#challegeBackgroundModal").modal("show");
            $('.overlay').fadeIn(200);

        }
    });
}

$(".agreeRule").on("click", agreeRule), function () {

}();

