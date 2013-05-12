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
				   }
			});
		});
	</script>
<body>
	<form id="inputForm" action="${ctx}/admin/dept/save" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${dept.id}"/>
		<input type="hidden" name="parent.id" value="${dept.parent.id}"/>
			<div class="control-group">
			</div>	
			<div class="control-group">
				<label for="deptName" class="control-label">机构名称:</label>
				<div class="controls">
					<input type="text" id="deptName" name="deptName" required="true" value="${dept.deptName}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="dispOrderNo" class="control-label">显示序号:</label>
				<div class="controls">
					<input type="text" id="dispOrderNo" name="dispOrderNo" number="true" max="10000" value="${dept.dispOrderNo}" class="input-large"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="remark" class="control-label">备注:</label>
				<div class="controls">
					<textarea id="remark" name="remark" class="input-large">${dept.remark}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label for="enableFlg" class="control-label"></label>
				<label class="checkbox">
              	<form:checkbox path="dept.enableFlg" name="enableFlg" id="enableFlg"/>是否有效
              	
            </label>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="reset" value="取消"/>
			</div>
	</form>
</body>
</html>
