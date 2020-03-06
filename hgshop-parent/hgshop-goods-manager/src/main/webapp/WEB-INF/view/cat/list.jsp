<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>

<link rel="stylesheet" href="/resource/css/bootstrap.css">
<link rel="stylesheet" href="/resource/bootstrap-treeview/css/bootstrap-treeview.css">
<script type="text/javascript" src="/resource/bootstrap-treeview/js/bootstrap-treeview.js"></script>

<div class="container-fluid">
	<div class="col-md-6" id="catTree">
	
	</div>
	<div class="col-md-6" id="edit">
		<form action="" id="form">
			<div class="form-group">
				<label for="">id</label>
				<input type="text" class="form-control" id="currentId" readonly="readonly" placeholder="节点id">
			</div>
			<div class="form-group">
				<label for="">名称</label>
				<input type="text" class="form-control" id="currentName" placeholder="节点名称">
			</div>
			<div class="form-group">
				<label for="">子集名称</label>
				<input type="text" class="form-control" id="name" placeholder="">
			</div>
			<button type="button" class="btn btn-dark" onclick="addChild()">添加</button>
			<button type="button" class="btn btn-primary" onclick="updateNode()">修改</button>
		</form>
	</div>
</div>    

<script type="text/javascript">
	function initTree() {
		//发送ajax获取树需要的数据
		$.post("/cat/treeData", {},
				function(treeData) {
					//初始化添加的时候分类的树
					$("#catTree").treeview({
						data : treeData,
						levels : 2,
						onNodeSelected : function(event, node) {
							if (node.nodes.length==0) {
								if($("#del").length<=0){
									$("#form").append("<button type='button' id='del' class='btn btn-danger' onclick='delNode()'>删除</button>")
								}
							} else{
								$("#del").remove();
							}
							$("#currentId").val(node.id);
							$("#currentName").val(node.text);
							$("#name").val("")
						}
					});
				}, "json");
	}
	initTree();
	
	//添加节点
	function addChild() {
		$.post("/cat/add",{parentId:$("#currentId").val(),name:$("#name").val()},
			function(data){
				if(data=="success"){
					alert("插入成功")
					refresh();
				}else{
					alert("插入失败")
				}
			})
	}
	
	//删除节点
	function delNode() {
		if(confirm("确定删除该分类？")){
			$.post("/cat/delCategory",{id:$("#currentId").val()},
					function(data){
						if(data=="success"){
							alert("删除成功")
							refresh();
						}else{
							alert("删除失败")
						}
				})
		}
	}
	
	//修改节点
	function updateNode() {
		if(confirm("确定修改该分类？")){
			$.post("/cat/updCategory",{id:$("#currentId").val(),name:$("#currentName").val()},
					function(data){
						if(data=="success"){
							alert("修改成功")
							refresh();
						}else{
							alert("修改失败")
						}
				})
		}
	}
	
	//刷新页面
	function refresh(){
		$("#main").load("/cat/list");
	}
	
</script>