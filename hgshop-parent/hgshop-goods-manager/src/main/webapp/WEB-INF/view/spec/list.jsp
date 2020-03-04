<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>



<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
  添加
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">添加规格</h5>
        <button type="button" onclick="addProp()">添加属性
        </button>
        
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
      	<form id="addSpec">
		    <div class="form-group">
		      <label for="specName">规格名称</label>
		      <input type="text" class="form-control" id="specName" name="specName" aria-describedby="specNamelHelp">
		      <small id="specNamelHelp" class="form-text text-muted">请输入规格名称</small>
		    </div>
		    <div class="form-group">
		      <label for="inputAddress">属性值</label>
		      <input type="text" name="options[0].optionName" class="form-control" id="inputAddress">
		      <button onclick="$(this).parent().remove()">删除</button>
		    </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="addSpec()">提交</button>
      </div>
    </div>
  </div>
</div>

<table class="table">
	<tr>
		<th>id</th>
		<th>名称</th>
		<th>详情</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="spec">
		<tr>
			<th>${spec.id}</th>
			<th>${spec.specName}</th>
			<th>
				<c:forEach items="${spec.options}" var="option">
					&nbsp;&nbsp;${option.optionName}
				</c:forEach>
			</th>
		</tr>
	</c:forEach>
	<tr>
		<Td colspan="10">
			<button onclick="fenye(1)">首页</button>
			<button onclick="fenye(${pageInfo.prePage==0?1:pageInfo.prePage})">上一页</button>
			<button onclick="fenye(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</button>
			<button onclick="fenye(${pageInfo.pages})">尾页</button>
			第${pageInfo.pageNum}/${pageInfo.pages},共${pageInfo.total}条
		</Td>
	</tr>
</table>

<script type="text/javascript">
	function fenye(pageNum) {
		var recUrl="/spec/list";
		console.log("准备进入"+recUrl);
		$("#main").load(recUrl+"?pageNum="+pageNum);
	}
	
	var i = 1;
	function addProp() {
		$("#addSpec").append('<div class="form-group">'+
			      '<label for="inputAddress">属性值</label>'+
			      '<input type="text" name="options['+i+'].optionName" class="form-control" id="inputAddress">'+
			      '<button onclick="$(this).parent().remove()">删除</button>'+
			    '</div>')
		i++;
	}
	
	function addSpec() {
		var formData = new FormData($("#addSpec")[0]);
		$.ajax({
			url:"/spec/add",
			data:formData,
			// 让jQuery 不要再提交数据之前进行处理
			processData : false,
			// 提交的数据不能加消息头
			contentType : false,
			// 提交的方式 
			type:"post",
			success:function(data){
				alert(data);
				
			}
		})
	}
	
</script>