<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>中建投信托数据中心系统</title>


<script type="text/javascript"
	src="/trustbi/core/js/jquery/jquery-1.7.2.min.js">
</script>
	<link href="/trustbi/trust/js/dist/jQueryValidations/css/validationEngine.jquery.css" rel="stylesheet" />
	<script src="/trustbi/trust/js/dist/jQueryValidations/js/languages/jquery.validationEngine-zh_CN.js"></script>	
	<script src="/trustbi/trust/js/dist/jQueryValidations/js/jquery.validationEngine.js"></script>
<script type="text/javascript">
	var info  = 
	$(document).ready(
			function() {
				$.ajax({
					type:"post",
					url:"http://10.68.3.164:9704/analytics/saw.dll?Logoff",
					dataType:"json",
					success:function(data) {}
					});
				$("input[name='submitBtn']").click(function() {
					$("form").submit();
				});
				
				$("#editForm").validationEngine({   
		            inlineValidation: true,
		            success :  true,
		            promptPosition: "bottomLeft",  
		            failure : function() { FrosJquery.dialog.alert("验证失败，请检查。");  }
		     });

				//点击“回车”键
				$(document).keydown(
						function(event) {
							var account = $("input[name='loginId']").val();
							var pwd = $("input[type='pwd']").val();
							/*
							if (event.keyCode == 13 && account != null
									&& account != "" && pwd != null
									&& pwd != "") {
								$("form").submit();
							}
							*/
							if (event.keyCode == "13") {//keyCode=13是回车键
								$("form").submit();
				             }
						});
				if(info == "0"){
					alert("用户名或密码输入错误！");
				}
				
			});
</script>

</head>
<body>
	<center>
		<div style="width: 1024px;height: 627px;background: url(trust/img/login2.png);">
			<form id=editForm action="/trustbi/login_admin" method="post">
				<table style="width: 300px; padding: 240px 0px 50px 68px;">
					<tr>
						<td>
						<input type="text" name="loginId" data-validation-engine="validate[required]" data-errormessage="登录名不能为空" style="width: 310px;height: 38px;border: 0px;font-size: 16px;border: 0px #ccc solid;line-height:38px;vertical-align:middle;"/></td>
					</tr>
					<tr>
						<td> <input type="password" name="pwd" data-validation-engine="validate[required]" data-errormessage="密码不能为空" style="width: 310px;height: 38px;font-size: 18px;line-height:38px;border: 0px #ccc solid;"/></td>
					</tr>
					<tr style="height: 60px;">
					</tr>
					<tr>
						<td align="left">
						<input type="button" name="submitBtn" value="登  录" title="点击登录系统" 
						style="width: 250px;height: 50px;background-color: #DC143C;border: 0;cursor: pointer;font-size: 20px;color: #fff;">&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>


</html>