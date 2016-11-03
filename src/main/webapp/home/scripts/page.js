var loginOrRegister = function(){
    var clickSignin = function(){
        $("#signup").show();
        $(".modal-backdrop").show();
        $(".js-registerWrap").hide();
        $(".js-loginWrap").show();
        $("#signin_span").addClass("active-title");
        $("#register_span").removeClass("active-title");
    }
    var clickRegister = function(){
        $("#signup").show();
        $(".modal-backdrop").show();
        $(".js-loginWrap").hide();
        $(".js-registerWrap").show();
        $("#signin_span").removeClass("active-title");
        $("#register_span").addClass("active-title");
    }
    //登录
    $("#js-signin-btn").click(function(){
        clickSignin();
    });
    $("#signin_span").click(function(){
        clickSignin();
    });
    //注册
    $("#js-signup-btn").click(function(){
        clickRegister();
    });
    $("#register_span").click(function(){
        clickRegister();
    });

    //点击遮罩层
    $(".modal-backdrop").click(function(){
        $(".modal-backdrop").hide();
        $("#signup").hide();
    });

    //自定义校验规则
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");
    //    jQuery.validator.addMethod("isEmail", function(value, element) {
    //        var mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    //        return this.optional(element) || ( mail.test(value));
    //    }, "邮箱格式不正确");
    jQuery.validator.addMethod("isPassword", function(value, element) {
        var password = /^[_0-9a-z]{6,16}$/i;
        return this.optional(element) || ( password.test(value));
    }, "密码格式不正确");

    //校验数据格式
    function check(){
        /*关键在此增加了一个return，返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证*/
        return $("#validateform").validate({
            errorPlacement:function(error, element){
                error.insertAfter(element);
            },
            rules : {
                userName : {
                    required : true,
                    maxlength: 8
                },
                mobilePhone : {
                    required : true,
                    minlength : 11,
                    isMobile : true
                },
                password : {
                    required : true,
                    isPassword : true,
                    minlength: 6,
                    maxlength: 12
                }
            },
            messages : {
                userName : {
                    required : "必须填写姓名",
                    maxlength:"姓名最大长度不能超过50个字符"
                },
                mobilePhone : {
                    required : "必须填写手机号码",
                    minlength:"手机号码长度不能小于11位",
                    isMobile:"手机格式不正确"
                },
                password : {
                    required : "必须填写密码",
                    isPassword : "密码为6-12个数字字母组合",
                    minlength:"密码长度不能少于6位",
                    maxlength: "密码长度不能超于12位"
                }
            }
        })
    }
    // 发送验证码事件
    var MAX_TIME = 60;
    var countdown = MAX_TIME;
    var timeout;
    // 更新显示的时间
    function updateText() {
        if (countdown == 0) {
            if($("#mobile").val().length == 11) {
                $("#sendCode").attr("disabled", false);
            }
            $("#sendCode").text("获取验证码");
            countdown = MAX_TIME;
            window.clearInterval(timeout);
        } else {
            $("#sendCode").attr("disabled", true);
            $("#sendCode").text("重新发送(" + countdown + ")");
            countdown--;
        }
    }
    var errorList = [];
    $("#sendCode").click(function() {
        errorList = [];
        //防止多次点击
        if(countdown < 60){
            return;
        }
        $("input").removeClass("change");
        var mobile = $("#mobile").val();
        if(mobile.length != 11) {
            errorList.push({"message":"手机号码格式不正确"});
//             showError(errorList,"joinError");
            return;
        }
        //判断当前手机号是否已注册
        $.post("/system/user/find.api", {"mobile":mobile}, function(data) {
            var result = jQuery.parseJSON(data);
            if (result.errors.length != 0) {
                errorList = result.errors;
//                 showError(errorList, "joinError");
                return;
            }else{
                //发送验证码
                $("#sendCode").attr("disabled","true").css("color","#868383");
                $.post("/system/verification.api", {"mobile":mobile}, function(data) {
                    var result = jQuery.parseJSON(data);

                    if (result.errors.length != 0) {
                        errorList = result.errors;
//                         showError(errorList,"joinError");
                    } else {
                        $("#sendCode").css("color","#333");
                        updateText();
                        timeout = setInterval(updateText,1000);
                    }
                })
            }
        })
    });

    $("#signup-btn").click(function(){
        if(check().form()) {
            var param = {
                name: $("#name").val(),
                mobile: $("#mobile").val(),
                password: hex_md5($("#password").val())
            }
            $.post("/system/register.api", param, function (data) {
                var rasult = jQuery.parseJSON(data);
                if (result.errors.length != 0) {
                    errorList = result.errors;
                    //showError(errorList,"joinError");
                    $("#doJoin").attr("disabled", false);
                } else {
                    store.set("user", result.user);
                    store.set("passport", result.passport);

                    var redirectUrl = getQueryString("redirectUrl");
                    setTimeout(function(){
                        if(redirectUrl) {
                            window.location = redirectUrl;
                        } else {
                            window.location = "index.html";
                        }
                    },3000);
                }
            })
        }
    })
}