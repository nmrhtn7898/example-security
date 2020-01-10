$(function () {
   var $form = $('#form');
   $form.validate({
       submitHandler : function (form) {
           if (confirm('가입하시겠습니까?')) {
               $.ajax({
                   url: "/api/users",
                   method: "post",
                   contentType : "application/json",
                   accept : "application/json",
                   data : $form.serializeJSON(),
                   success : function(data) {
                       if (data.status.code === "400") {
                           alert("회원가입실패");
                       } else {
                           alert("회원가입성공");
                           location.replace("/index");
                       }
                   },
                   error : function (jqXHR) {
                       alert(jqXHR.status + "/" + jqXHR.statusText);
                   }
               })
           }
           return false;
       },
       invalidHandler : function(form, validator) {
           if (validator.numberOfInvalids()) {
               validator.errorList[0].element.focus();
           }
       },
       rules : {
           email : {
               required : true,
               email : true,
               remote : "/api/users"
           },
           password : {
               required : true
           },
           confirm_password : {
               required : true,
               equalTo : "#password"
           }
       },
       messages : {
           email : {
               required : "이메일 입력은 필수 입니다.",
               email : "잘못된 이메일 양식입니다.",
               remote : "이미 존재하는 이메일입니다."
           },
           password : {
               required : "비밀번호 입력은 필수 입니다."
           },
           confirm_password : {
               required : "비밀번호 확인 입력은 필수 입니다.",
               equalTo : "입력한 비밀번호가 일치하지 않습니다."
           }
       }
   })
});