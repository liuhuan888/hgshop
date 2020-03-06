<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>



<div>
	<input type="text" name="" id="queryName" value="${name}">
	<button type="button" class="btn btn-primary" onclick="query()">
	  查询
	</button>
	<button type="button" class="btn btn-primary" onclick="delBatch()">
	  批量删除
	</button>
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
	  添加
	</button>
</div>

<!-- 添加的Modal -->
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
		    <div class="form-group form-grop-proper">
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
		<th>id <input type="checkbox" id="check" onchange="seleAll()"> 
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(1)">全选</button>
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(2)">全不选</button>
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(3)">反选</button>
		</th>
		<th>名称</th>
		<th>详情</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="spec">
		<tr>
			<th><input type="checkbox" name="check" value="${spec.id}" onchange="selOne()"> ${spec.id}</th>
			<th>${spec.specName}</th>
			<th>
				<c:forEach items="${spec.options}" var="option">
					&nbsp;&nbsp;${option.optionName}
				</c:forEach>
			</th>
			<th>
				<button type="button" class="btn btn-danger" onclick="delSpec(${spec.id})">删除</button>
				<button type="button" class="btn btn-warning">修改</button>
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
	//分页
	function fenye(pageNum) {
		var recUrl="/spec/list";
		console.log("准备进入"+recUrl);
		var name=$("#queryName").val();
		$("#main").load(recUrl+"?pageNum="+pageNum+"&name="+name);
	}
	
	
	//添加模态框中添加属性
	var i = 1;
	function addProp() {
		$("#addSpec").append('<div class="form-group form-grop-proper">'+
			      '<label for="inputAddress">属性值</label>'+
			      '<input type="text" name="options['+i+'].optionName" class="form-control" id="inputAddress">'+
			      '<button onclick="$(this).parent().remove()">删除</button>'+
			    '</div>')
		i++;
	}
	
	//添加
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
				if(data=='success'){
					alert("添加成功");
					$('#staticBackdrop').modal('hide');
				}else{
					alert("添加失败");
				}
			}
		})
	}
	
	//查询
	function query() {
		var url = "/spec/list?name="+$("#queryName").val();
		$("#main").load(url);
	}
	
	//刷新 而且保持查询条件和页码
	function refresh() {
		var url="/spec/list?name=${name}"+'&pageNum=${pageInfo.pageNum}';
		$("#main").load(url);
	}
	
	//添加窗口复位
	function resetAddForm(){
		$(".form-grop-proper").each(function(){
			$(this).remove();
		})
		i=0;
		$("#specName").val("")
	}
	
	//给添加模态框增加关闭以后的事件 
	$('#staticBackdrop').on('hidden.bs.modal', function (e) {
		refresh();
	})
	
	//给添加模态框增加显示成成功后的事件  
	$('#staticBackdrop').on('shown.bs.modal', function (e) {
		resetAddForm();
	})
	
	//删除规格
	function delSpec(id) {
		if(confirm("您确定删除这条数据么？")){
			$.post("/spec/delSpec",{id:id},function(data){
				if(data=='success'){
					alert("删除成功");
					refresh();
				}else{
					alert("删除失败");
				}
			})
		}else{
			return;
		}
	}
	
	//选择按钮
	function selAll(flag) {
		//全选
		if(flag==1){
			$("[name='check']").each(function(){
				$(this).prop("checked",true);
			})
		}
		//全不选
		if(flag==2){
			$("[name='check']").each(function(){
				$(this).prop("checked",false);
			})
		}
		//反选
		if(flag==3){
			$("[name='check']").each(function(){
				$(this).prop("checked",!$(this).prop("checked"));
			})
		}
		//判断是否所有的复选框都被选中
		var allCheck = $("[name='check']").length == $("[name='check']:checked").length;
		$("#check").prop("checked",allCheck);
		
	}
	
	//修改一个复选框
	function selOne() {
		var allCheck = $("[name='check']").length == $("[name='check']:checked").length;
		$("#check").prop("checked",allCheck);
	}
	
	//全选框
	function seleAll() {
		$("[name='check']").each(function(){
			$(this).prop("checked",$("#check").prop("checked"));
		})
	}
	
	//批量删除
	function delBatch() {
		if($("[name='check']:checked").length<=0){
			alert("请选择要删除的数据");
			return;
		}
		
		var ids = new Array();
		$("[name='check']:checked").each(function(){
			ids.push($(this).val());
		})
		
		if(confirm("您确定删除这些数据么？")){
			$.post("/spec/delSpecBatch",{ids:ids},function(data){
				if(data=='success'){
					alert("删除成功");
					refresh();
				}else{
					alert("删除失败");
				}
			})
		}else{
			return;
		}
	}
</script>