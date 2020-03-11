<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container-fluid">
	<table class="table">
		<tr>
			<th>id</th>
	  		<th>标题</th>
	  		<th>价格</th>
	  		<th>库存</th>
	  		<th>发布时间</th>
	  		<th>成本价</th>
	  		<th>商品名称</th>
	  		<th>品牌</th>
	  		<th>分类</th>
	  		<th>操作</th>
		</tr>
		<c:forEach items="${pageInfo.list}" var="sku">
			<tr>
				<th>${sku.id}</th>
		  		<th>${sku.title}</th>
		  		<th>${sku.price}</th>
		  		<th>${sku.stockCount}</th>
		  		<th><fmt:formatDate value="${sku.createTime}" pattern="yyyy-MM-dd"/></th>
		  		<th>${sku.costPrice}</th>
		  		<th>${sku.spu.goodsName}</th>
		  		<th>${sku.spu.brand.name}</th>
		  		<th>${sku.spu.category.name}</th>
		  		<th>
		  			<button type="button" class="btn btn-success" onclick="goDetail(${sku.id})">详情</button>
					<button type="button" class="btn btn-danger">删除</button>
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
</div>
<script type="text/javascript">
	function fenye(pageNum) {
		$("#main").load("/goods/skulist?pageNum="+pageNum);
	}
	function goDetail(id) {
		$("#main").load("/goods/skuDetail?id="+id)
	}
</script>
