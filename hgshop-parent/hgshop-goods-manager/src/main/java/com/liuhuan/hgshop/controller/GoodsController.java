package com.liuhuan.hgshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.pojo.Brand;
import com.liuhuan.hgshop.pojo.Sku;
import com.liuhuan.hgshop.pojo.Spec;
import com.liuhuan.hgshop.pojo.SpecOption;
import com.liuhuan.hgshop.pojo.Spu;
import com.liuhuan.hgshop.pojo.SpuVo;
import com.liuhuan.hgshop.service.GoodsService;
import com.liuhuan.hgshop.service.SpecService;

@Controller
@RequestMapping("goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	
	@Reference
	private SpecService specService;
	
	/**
	 * spu列表
	 * @param model
	 * @param spuVo
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,SpuVo spuVo,
						@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<Spu> pageInfo = goodsService.listSpu(pageNum, spuVo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("spuVo", spuVo);
		return "/goods/list";
	}
	
	
	
	/**
	 * sku列表
	 * @param model
	 * @param sku
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("skulist")
	public String skulisy(Model model,Sku sku,
			@RequestParam(defaultValue="1")Integer pageNum) {
		PageInfo<Sku> pageInfo = goodsService.listSku(pageNum, sku);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("sku", sku);
		return "sku/list";
	}
	
	//详情查询
	@RequestMapping("skuDetail")
	public String skuDetail(Model model,Integer id) {
		Sku sku = goodsService.getSku(id);
		model.addAttribute("sku", sku);
		return "sku/detail";
	}
	
	//跳转到sku添加页面
	@RequestMapping("toaddSku")
	public String toaddSku(Model model,Integer spuId) {
		// 获取要添加的商品
		Spu spu = goodsService.getSpu(spuId);
		model.addAttribute("spu", spu);
		// 属性名称
		List<Spec> list = specService.listAll();
		model.addAttribute("specs", list);
		return "sku/add";
	}
	
	//添加sku
	@RequestMapping("addSku")
	@ResponseBody
	public String addSku(Sku sku,int[] specIds,@RequestParam(value="specOptionIds") int[] specOptionIds) {
		// 保存给sku的所有的属性以及属性值
		ArrayList<SpecOption> specs = new ArrayList<>();
		for (int i = 0; i < specIds.length && i<specOptionIds.length; i++) {
			SpecOption option = new SpecOption();
			// 规格的id
			option.setSpecId(specIds[i]);
			// 具体的属性值 的id
			option.setId(specOptionIds[i]);
			specs.add(option);
		}
		//存放规格的数据
		sku.setSpecs(specs);
		int i = goodsService.addSku(sku);
		return i>0?"success":"false";
	}
	
	@RequestMapping("toadd")
	public String toadd(HttpServletRequest request ) {
		List<Brand> allBrands = goodsService.getAllBrands();
		request.setAttribute("brands", allBrands);
		return "goods/add";
	}
	
	
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Spu spu,@RequestParam(value="file") MultipartFile file ) throws IllegalStateException, IOException {
		
		/**
		 * 返回的上传文件存放的物理地址
		 */
		String filePath=processFile(file);
		// 可以根据 这个地址下载
		spu.setSmallPic(filePath);
		
		int result = goodsService.addSpu(spu);
		
		return result>0?"success":"failed";
		
	}
	
	/**
	 * 
	 * @param response
	 * @param file
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("down")
	public void downLoad(HttpServletResponse response, String filename) throws FileNotFoundException {
		 /* // 下载本地文件
	    String fileName = "Operator.doc".toString(); // 文件的默认保存名*/	    
		// 读到流中
	    InputStream inStream = new FileInputStream("d:\\pic\\"+filename);// 文件的存放路径
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	   
	    // 循环取出流中的数据
	    byte[] b = new byte[1024];
	    int len;
	    try {
	      while ((len = inStream.read(b)) > 0)
	        response.getOutputStream().write(b, 0, len);
	      inStream.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	
	}
	
	/**
	 *  上传文件
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
		
		
	}
	
}
