$(function () {
   var $form = $('#form');
   $form.validate({
       submitHandler : function (form) {
           if (confirm('수정하시겠습니까?')) {
               $.ajax({
                   url: "/api/users/" + $("input[name='email']").val(),
                   method: "patch",
                   contentType : "application/json",
                   accept : "application/json",
                   data : $form.serializeJSON(),
                   success : function(data) {
                       if (data.status.code === "400") {
                           alert("회원수정실패");
                       } else {
                           alert("회원수정성공");
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
               email : true
           },
           password : {
               required : true
           }
       },
       messages : {
           email : {
               required : "이메일 입력은 필수 입니다.",
               email : "잘못된 이메일 양식입니다."
           },
           password : {
               required : "비밀번호 입력은 필수 입니다."
           }
       }
   })
});