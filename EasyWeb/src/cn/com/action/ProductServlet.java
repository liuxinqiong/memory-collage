package cn.com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.entity.CartItem;
import cn.com.entity.Product;
import cn.com.entity.ProductCategory;
import cn.com.entity.User;
import cn.com.serviceImp.ProductServiceImp;
import cn.com.serviceInf.ProductServiceInf;
import cn.com.util.ObjectToJson;
import cn.com.util.PageUtil;
import cn.com.util.Util;

public class ProductServlet extends HttpServlet {
	private ProductServiceInf productService;

	public ProductServlet() {
		productService = new ProductServiceImp();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		System.out.println(this.getServletConfig().getInitParameter("test"));
//		System.out.println(this.getServletConfig().getServletContext().getInitParameter("contextTest"));
		String command = req.getParameter("command") != null ? req
				.getParameter("command") : null;
		if ("productView".equals(command)) {
			productView(req, resp);
		} else if ("buy".equals(command)) {
			buy(req, resp);
		} else if ("getCategory".equals(command)) {
			getCategory(req, resp);
		} else if("order".equals(command)){
			order(req, resp);
		}else if(("getProfession").equals(command)){
			getProfession(req, resp);
		}else {
			list(req, resp);
		}
	}

	private void getProfession(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void order(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(true);
		// 购物车：集合对象 map
		Map<Integer, CartItem> cart = null;
		// 检查购物车是否存在:
		if (session.getAttribute("cart") == null) {
			cart = new HashMap<Integer, CartItem>();
			session.setAttribute("cart", cart);
		} else {
			cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		}
		
		User user=(User)session.getAttribute("loginUser");
		//请求业务对象
		boolean isScuss=this.productService.order(cart,user);
		if(isScuss){
			cart.clear();
			req.getRequestDispatcher("shopping-result.jsp").forward(req, resp);
		}else{
			resp.getWriter().print("购买失败");
		}

	}

	private void getCategory(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// json传输方式的内容类型
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Product product = this.wrap(req);
		List<Product> products = this.productService.getAllProducts(product, 0,
				0);
		String sb = null;
		ObjectToJson<Product> objectToJson = new ObjectToJson<Product>();
		try {
			sb = objectToJson.ListToJson(products);
			System.out.println(sb);
			out.print(sb.toString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/*
	 * XML方式 resp.setContentType("text/xml;charset=utf-8");
	 * sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	 * sb.append("<products>"); for (Product p : products) {
	 * sb.append("<product>");
	 * sb.append("<pid>").append(p.getPid()).append("</pid>");
	 * sb.append("<pname>").append(p.getPname()).append("</pname>");
	 * sb.append("<pprice>").append(p.getPprice()).append("</pprice>");
	 * sb.append("<pfileName>").append(p.getPfileName()).append("</pfileName>");
	 * sb.append("</product>"); } sb.append("</products>");
	 * 
	 * 自定义方式 for (Product p : products) {
	 * sb.append(p.getPid()).append("|").append(p.getPname()).append("|")
	 * .append(p.getPprice()).append("|").append(p.getPfileName());
	 * sb.append("||"); }
	 * 
	 * json方式 sb.append("{\"products\":["); for (Product p : products) {
	 * sb.append("{");
	 * sb.append("\"pid\"").append(":").append("\"").append(p.getPid())
	 * .append("\"").append(",");
	 * sb.append("\"pname\"").append(":").append("\"").append(p.getPname())
	 * .append("\"").append(",");
	 * sb.append("\"pprice\"").append(":").append("\"").append(p.getPprice())
	 * .append("\"").append(",");
	 * sb.append("\"pfileName\"").append(":").append("\""
	 * ).append(p.getPfileName()) .append("\""); sb.append("},"); }
	 * sb.deleteCharAt(sb.length()-1); sb.append("]}");
	 */

	private void buy(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 用户选择了那个商品
		Product product = this.wrap(req);
		product = this.productService.getProductByInfo(product);

		// 用户修改的数量
		int num = req.getParameter("num") != null ? Integer.parseInt(req
				.getParameter("num")) : 0;
		// 会话对象
		HttpSession session = req.getSession(true);
		// 产生一个购物车 Map
		Map<Integer, CartItem> cart = null;

		// 处特别注意 需要判断是否存在 否则数据会被覆盖
		if (session.getAttribute("cart") == null) {
			cart = new HashMap<Integer, CartItem>();
			session.setAttribute("cart", cart);
		} else {
			cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		}
		// 不能直接存入购物车 因为可能存在多个相同的商品 需要进行数据逻辑处理
		boolean bool = this.productService.addCart(cart, product, num);
		if (bool) {
			double count = 0;
			Collection<CartItem> values = cart.values();
			for (CartItem item : values) {
				count += item.getProduct().getPprice() * item.getNumber();
			}
			req.setAttribute("count", count);
			req.setAttribute("cart", values);
			// 跳到购物车详细
			req.getRequestDispatcher("shopping.jsp").forward(req, resp);
		}
	}

	private void productView(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product p = this.wrap(req);
		p = productService.getProductByInfo(p);
		req.setAttribute("product", p);
		String historyProduct=productService.historyProduct(req, p);	
		Cookie cookie=new Cookie("historyProduct", historyProduct);
		cookie.setMaxAge(1800);
		resp.addCookie(cookie);
		req.getRequestDispatcher("product-view.jsp").forward(req, resp);
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageSize = Util.pageSize;
		int page = req.getParameter("page") != null ? Integer.parseInt(req
				.getParameter("page")) : 1;
		String url = req.getServletPath().substring(1);
		PageUtil<Product> pageUtil = this.productService.getPageUtil(null,
				pageSize, page, url);
		
		// 封装请求
		req.setAttribute("pageUtil", pageUtil);
		List<ProductCategory> productCategorys = this.productService
				.getAllProductCategory();
		req.setAttribute("productCategorys", productCategorys);
		
		//cookie浏览记录
		Cookie[] cookies=req.getCookies();
		String historyProduct=null;
		List<Product> productsHostory=null;
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("historyProduct")){
					historyProduct=cookie.getValue();
				}
			}
			productsHostory=new ArrayList<Product>();
			if(historyProduct!=null){
				String[] productsIds=historyProduct.split("/");
				for (String string : productsIds) {
					Product p=new Product();
					int pid=Integer.parseInt(string);
					p.setPid(pid);
					p=productService.getProductByInfo(p);
					productsHostory.add(p);
				}
			}
		}	
		req.setAttribute("productsHostory", productsHostory);
		
		// 跳转
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private Product wrap(HttpServletRequest req) {
		// 一、得到用户提交的数据
		int pid = req.getParameter("pid") != null ? Integer.parseInt(req
				.getParameter("pid")) : 0;

		int pcChildId = req.getParameter("pcChildId") != null ? Integer
				.parseInt(req.getParameter("pcChildId")) : 0;
		// 对数据封装
		Product p = new Product();
		p.setPid(pid);
		p.setPcChildId(pcChildId);
		return p;
	}
}
