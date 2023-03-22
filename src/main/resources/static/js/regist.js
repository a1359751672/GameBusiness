var regUrl="/users/regist"
$(function () {
    //    为用户名输入框添加失去焦点事件
    $("#username").blur(function (){
        checkEmpty("username","用户名不能为空")
        checkLength("username",6,20)
    })
//    为密码输入框添加失去焦点事件
    $("#pwd").blur(function (){
        checkEmpty("pwd","密码不能为空")
    })
    //    为确认密码输入框添加失去焦点事件
    $("#rePwd").blur(function (){
        checkEmpty("rePwd","重复密码不能为空")
        checkRePwd("pwd","rePwd","俩次密码不一致")
    })
    //为提交按钮添加点击事件
    $("#btnSubmit").click(function (){
        //    获取表单数据
        var username = $("#username").val();
        var pwd =$("#pwd").val();
        var rePwd =$("#rePwd").val();

//    判断页面中的input是否都进行了验证
        var divArr = $("div.has-success");
        if (divArr.length != 3){
            return;
        }
        //    提交表单
        var params = { //提交参数，请求参数属性名=属性值（属性名需和后端一致）
            username:username,
            password:pwd,
        }
        //    发送AJAX请求
        $.post(regUrl,params,function (result) {
            //    处理相应数据
            if (result.state == 1000){ //相应成功状态
                alert("注册成功，点击跳转登录页面");
                window.location.href="login.html";
            }else{
                alert(result.msg);
            }
        })
    })
})
//    定义验证是否为空方法
function checkEmpty(name,msg){
    if ($("#"+name).val() ==""){
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text(msg);
        return false;
    }else {
        $("#"+name).parents(".form-group").removeClass("has-error").addClass("has-success");
        //
        $("#"+name).next("span").text("");
        return true;
    }
}
//    定义是否一致
function checkRePwd(name1,name2,msg){
    if ($("#"+name1).val() !=$("#"+name2).val()){
        $("#"+name2).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name2).next("span").text(msg);
        return false;
    }else {
        $("#"+name2).parents(".form-group").removeClass("has-error").addClass("has-success");
        //
        $("#"+name2).next("span").text("");
        return true;
    }
}
//定义验证用户名长度的方法
function checkLength(name,minLength,maxLength){
    if ($("#"+name).val().length<minLength){ //小于长度
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("长度不能小于"+minLength);
        return false;
    }else if ($("#"+name).val().length>maxLength){ //大于长度
        $("#"+name).parents(".form-group").addClass("has-error").removeClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("长度不能大于"+maxLength);
        return false;
    }else {
        $("#"+name).parents(".form-group").removeClass("has-error").addClass("has-success");
        //    给span标签添加错误提示信息
        $("#"+name).next("span").text("");
        return true;
    }
}