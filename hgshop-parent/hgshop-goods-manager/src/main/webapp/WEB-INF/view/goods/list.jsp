<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<button type="button" class="btn btn-primary" onclick="toAdd()">添加</button>
	<button type="button" class="btn btn-primary" >批量删除</button>
</div>
<table class="table">
	<tr>
		<th>id</th>
		<th>商品名称</th>
		<th>标题</th>
		<th>是否在售</th>
		<th>品牌</th>
		<th>分类</th>
		<th>图</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="spu">
		<tr>
			<th>${spu.id}</th>
			<th>${spu.goodsName}</th>
			<th>${spu.caption}</th>
			<th>${spu.isMarkertable==1?"在售":"未售"}</th>
			<th>${spu.brand.name}</th>
			<th>${spu.category.name}</th>
			<th><img width="100px" height="100px" src="/pic/${spu.smallPic}"></th>
			<th>
				<button type="button" class="btn btn-primary" >详情</button>
				<button type="button" class="btn btn-danger" >删除</button>
				<button type="button" class="btn btn-warning">修改</button>
				<button type="button" class="btn btn-warning" onclick="addSku(${spu.id})">添加sku</button>
				<a target="_blank"  href="/goods/down?filename=${spu.smallPic}" >下载小图</a>
			</th>
		</tr>
	</c:forEach>
	
</table>

<script type="text/javascript">
	function toAdd(){
		$("#main").load("/goods/toadd");
	}
	
	
	function addSku(id){
		//添加sku
		$("#main").load("/goods/toaddSku?spuId="+id);
	}
</script>