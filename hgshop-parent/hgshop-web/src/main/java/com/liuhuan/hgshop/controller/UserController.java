package com.liuhuan.hgshop.controller;

import javax.servlet.http.HttpSession;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.liuhuan.hgshop.common.HgShopConstant;
import com.liuhuan.hgshop.pojo.Cart;
import com.liuhuan.hgshop.pojo.Order;
import com.liuhuan.hgshop.pojo.User;
import com.liuhuan.hgshop.service.CartService;
import com.liuhuan.hgshop.service.OrderService;
import com.liuhuan.hgshop.service.WebUserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Reference
	private WebUserService webUserService;
	@Reference
	private CartService cartService;
	@Reference
	private OrderService orderService;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("tologin")
	public String toLogin() {
		return "user/login";
	}

	/**
	 * 用户登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model, String userName, String password, HttpSession session) {
		User user = webUserService.login(userName, password);
		if (user == null) {
			model.addAttribute("userName", userName);
			model.addAttribute("password", password);
			model.addAttribute("error", "用户名或密码错误");
			return "user/login";
		}
		session.setAttribute(HgShopConstant.USERKEY, user);
		return "redirect:/index";
	}

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("toregister")
	public String toRegister() {
		return "user/register";
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("register")
	public String register(User user, Model model) {
		User register = webUserService.register(user);
		if (register == null) {
			model.addAttribute("error", "注册失败");
			return "user/register";
		}
		return "redirect:tologin";
	}

	/**
	 * 进入个人中心
	 * 
	 * @return
	 */
	@RequestMapping("home")
	public String home() {
		return "/user/index";
	}

	/**
	 * 加入购物车
	 * 
	 * @param session
	 * @param skuId
	 * @param buyNum  购买数量
	 * @return
	 */
	@RequestMapping("addCart")
	@ResponseBody
	public String addCart(HttpSession session, Integer skuId, Integer buyNum) {

		// 获取登录用户
		User loginUser = (User) session.getAttribute(HgShopConstant.USERKEY);
		if (loginUser == null) {
			return "亲，您尚未登录，不能加入购物车哦";
		}
		int i = cartService.addCart(loginUser.getUid(), skuId, buyNum);

		return i > 0 ? "success" : "添加失败";
	}

	/**
	 * 购物车列表
	 * 
	 * @param session
	 * @param model
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("cartlist")
	public String cartlist(HttpSession session, Model model, @RequestParam(defaultValue = "1") int pageNum) {

		// 获取登录用户
		User loginUser = (User) session.getAttribute(HgShopConstant.USERKEY);
		if (loginUser == null) {
			model.addAttribute("error", "您尚未登陆");
			return "error";
		}
		PageInfo<Cart> pageInfo = cartService.list(loginUser.getUid(), pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "user/cartlist";
	}

	/**
	 * 添加订单
	 * @param session
	 * @param model
	 * @param cartIds
	 * @param address
	 * @return
	 */
	@RequestMapping("addorder")
	@ResponseBody
	public String addorder(HttpSession session, Model model,
			@RequestParam("cartIds[]") int[] cartIds, String address) {

		// 获取登录用户
		User loginUser = (User) session.getAttribute(HgShopConstant.USERKEY);
		if (loginUser == null) {
			model.addAttribute("error", "您尚未登陆");
			return "error";
		}
		int i = cartService.createOrder(loginUser.getUid(), address, cartIds);
		return i>0?"success":"添加失败";
	}
	
	@RequestMapping("orderlist")
	public String orderlist(HttpSession session,Model model,
			 @RequestParam(defaultValue = "1") int pageNum) {

		// 获取登录用户
		User loginUser = (User) session.getAttribute(HgShopConstant.USERKEY);
		if (loginUser == null) {
			model.addAttribute("error", "您尚未登陆");
			return "error";
		}
		PageInfo<Order> pageInfo = orderService.list(loginUser.getUid(), pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "user/orderlist";
	}
}
