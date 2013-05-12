<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/layouts/taglib.jsp" %>
<!DOCTYPE html>
<html>
<script>
		$(document).ready(function() {
			$("#inputForm").validate({
				 submitHandler: function(form)
				   {
					 save();
				   },
				   rules: {
						loginName: {
							remote: "${ctx}/admin/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')
						}
					},
					messages: {
						loginName: {
							remote: "用户登录名已存在"
						}
					}
			});
			 $('#deptId').combotree({
				 url:'${ctx}/admin/dept/allEable',
				 editable:true
			 });  
		});
	</script>
<body>
	<form id="inputForm" action="${ctx}/admin/user/save" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
			<div class="control-group">
			</div>	
			<div class="control-group">
				<label for="deptId" class="control-label">机构：</label>
				<div class="controls">
					<input type="text" id="deptId" name="deptId" value="${user.dept.id}" required="true" class="easyui-combotree input-large required"/>  
				</div>
			</div>	
			<div class="control-group">
				<label for="loginName" class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" required="true" value="${user.loginName}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="name" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="name" name="name" required="true" value="${user.name}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large" placeholder="...Leave it blank if no change"/>
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword" class="input-large" equalTo="#plainPassword" />
				</div>
			</div>
			<div class="control-group">
				<label for="dispOrderNo" class="control-label">显示序号:</label>
				<div class="controls">
					<input type="text" id="dispOrderNo" name="dispOrderNo" number="true" max="10000" value="${user.dispOrderNo}" class="input-large"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="remark" class="control-label">备注:</label>
				<div class="controls">
					<textarea id="remark" name="remark" class="input-large">${user.remark}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label for="enableFlg" class="control-label"></label>
				<label class="checkbox">
              	<form:checkbox path="user.enableFlg" name="enableFlg" id="enableFlg"/>是否有效
              	
            </label>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="reset" value="取消"/>
			</div>
	</form>
</body>
</html>
