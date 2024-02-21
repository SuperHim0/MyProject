package com.example.superhim.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.superhim.GetApplicationContext;
import com.example.superhim.dao.AdminLoginDao;
import com.example.superhim.dao.BillingAddressDao;
import com.example.superhim.dao.CartDao;
import com.example.superhim.dao.CategoryDao;
import com.example.superhim.dao.ContactDao;
import com.example.superhim.dao.CustomerSignupDao;
import com.example.superhim.dao.PlaceOrderDao;
import com.example.superhim.dao.ProductDao;
import com.example.superhim.fileio.ProductIO;
import com.example.superhim.pojo.BillingAddressPojo;
import com.example.superhim.pojo.CartPojo;
import com.example.superhim.pojo.PlaceOrderPojo;
import com.example.superhim.pojo.ProductPojo;
import com.example.superhim.pojo.SignUpPojo;

import antlr.CppCodeGenerator;

@Controller
@MultipartConfig
public class Mycontroller {
	
	//creating function that get data from web-page
	@RequestMapping("savecontact")
	public static void getContact(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		
		ApplicationContext	Ac= GetApplicationContext.getApplicationContext();
		ContactDao bean = Ac.getBean(ContactDao.class); //upcasting
		int row = bean.insertContact(name, email, message,LocalDateTime.now().toString());
//		System.out.println(name+""+email+""+message);
		System.out.println(row);
			
		HttpSession session = request.getSession();
		if (row == 1) {
			response.sendRedirect("contact.jsp");
			session.setAttribute("msg", "message sent successfully");
			
		}
	}
	@RequestMapping("deletecontact")
	public static void deletecontact(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		String sn = request.getParameter("sn");
		
		ApplicationContext	Ac= GetApplicationContext.getApplicationContext();
		ContactDao bean = Ac.getBean(ContactDao.class); //upcasting
		int row = bean.deleteContatc(Integer.parseInt(sn));

		System.out.println(row);
			
		HttpSession session = request.getSession();
		if (row == 1) {
			response.sendRedirect("readcontact.jsp");
			session.setAttribute("msg", "message delete successfully");
		}
		
	}
	@PostMapping("addcategory")
	public static void addCategory(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		String department = request.getParameter("department");
		String category = request.getParameter("category"); 
		
		ApplicationContext	Ac= GetApplicationContext.getApplicationContext();
		CategoryDao bean = Ac.getBean(CategoryDao.class); //upcasting
		int row = bean.insertCategory(department,category);

		System.out.println(row);
			
		HttpSession session = request.getSession();
		if (row == 1) {
			response.sendRedirect("1addcategory.jsp");
			session.setAttribute("msg", "category add successfully");
		}
		
	}
	@PostMapping("saveproduct")
	public static void addproduct(HttpServletRequest request,HttpServletResponse response,@RequestParam("productimage")MultipartFile f) throws IOException, ServletException
	{
		//getting the detail from the form and store it.
		String producttital = request.getParameter("producttital");
		String productdescription = request.getParameter("productdescription");
		String crossprice = request.getParameter("crossprice");
		String mainprice = request.getParameter("mainprice");
		String availability = request.getParameter("availability");
		String department = request.getParameter("department");
		String category = request.getParameter("category").trim(); 
//		System.out.println("**"+category+"**");
		
		
		//getting the file name
//		Part part = request.getPart("productimage");

		//getting the local date time and adding to file to make unique name 
		String datetimeString = LocalDateTime.now().toString();
//		String newfileString = part.getSubmittedFileName();
		String newfileString = f.getOriginalFilename();
		String newfileString2 = datetimeString+newfileString;
		
		//split the name by (:) because in window it is recognized as Change directory command
		String[] split = newfileString2.split(":");
		newfileString = split[0]+split[1]+split[2];
		
		ApplicationContext	Ac= GetApplicationContext.getApplicationContext();
		ProductDao bean = Ac.getBean(ProductDao.class); 
		ProductPojo savePojo = bean.save(new ProductPojo(producttital, productdescription, crossprice, mainprice, availability,
				department, category, newfileString, datetimeString));

		//creating the session s
		HttpSession session = request.getSession();
		if (savePojo != null) {
			
			String realpath = request.getServletContext().getRealPath("myown/productimage")+File.separator+newfileString;
			String reString = new ProductIO().writeProduct(realpath, newfileString, f);
			
			if (reString.equals("saved")) {
				response.sendRedirect("1addproduct.jsp");
				session.setAttribute("msg", "product added successfully");	
			}
			else {
				response.sendRedirect("1addproduct.jsp");
				session.setAttribute("msg", "product added unsuccessfully");		
			}
		}
	}
	
	@PostMapping("adminlogin")
	public void AdminLoginCheck(HttpServletRequest request , HttpServletResponse respone) throws IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		AdminLoginDao bean = ac.getBean(AdminLoginDao.class);
		
		long count = bean.AdminLoginCheck(username, password);
		HttpSession session = request.getSession();
		
		if (count == 1) 
		{
			respone.sendRedirect("admin.jsp");
			session.setAttribute("login", username);
		}
		else 
		{
			respone.sendRedirect("adminlogin.jsp");
			session.setAttribute("msg", "invalid username and password");
		}
	}
	@PostMapping("changecredentials")
	public void ChangeCredentials(HttpServletRequest request , HttpServletResponse respone) throws IOException {
		
		String nuser = request.getParameter("nuser");
		String cnuser = request.getParameter("cnuser");

		String npass = request.getParameter("npass");
		String cnpass = request.getParameter("cnpass");	
		
		
		
		HttpSession session = request.getSession();
		
		if (nuser.equals(cnpass) && npass.equals(cnpass)) 
		{
			ApplicationContext ac = GetApplicationContext.getApplicationContext();
			AdminLoginDao bean = ac.getBean(AdminLoginDao.class);
			int count= bean.UpdateAdminLogin(cnuser, cnpass);
			if (count == 1) {
				respone.sendRedirect("admin.jsp");
				session.setAttribute("msg","Updated successfully");
			}
			else {
				respone.sendRedirect("admin.jsp");
				session.setAttribute("msg","somthing went wrong");
			}
		}
		else 
		{
			respone.sendRedirect("admin.jsp");
			session.setAttribute("msg","username and password are wrong");
		}
	}
	@PostMapping("costumersignup")
	public void CustomerSignup(HttpServletRequest request ,HttpServletResponse respone) throws IOException {
		
		String fullname = request.getParameter("fullname");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");	
		
		HttpSession session = request.getSession();
	
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		CustomerSignupDao bean = ac.getBean(CustomerSignupDao.class);
		
		//checking the mobile number is exist in data base or not
		int mobileNumberExist = bean.userMobileCheck(mobile);
		
		if (mobileNumberExist == 1) {
			respone.sendRedirect("index.jsp");
			session.setAttribute("msg","mobile number is already exist");
		}
		else {
			
		if (password.equals(confirmpassword))
		{
			SignUpPojo count= bean.save(new SignUpPojo(fullname, mobile, confirmpassword,LocalDateTime.now().toString()));
			
			ArrayList<String> arrayList = new ArrayList<>();
			arrayList.add(mobile);
			arrayList.add(confirmpassword);
			
			
			if (count != null) {
				respone.sendRedirect("index.jsp");
				session.setAttribute("msg"," successfully signup");
				session.setAttribute("customerSignup", arrayList);
			}
			else {
				respone.sendRedirect("signup.jsp");
				session.setAttribute("msg","somthing went wrong");
			}
		}
		else{
			respone.sendRedirect("signup.jsp");
			session.setAttribute("msg","password does not match");
			}
		}
		
	}
	@PostMapping("customerlogin")
	public void CustomerLogin(HttpServletRequest request , HttpServletResponse response) throws IOException 
	{
		String username = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
	    CustomerSignupDao customerLogin = ac.getBean(CustomerSignupDao.class);
		int count = customerLogin.CustomerLoginCheck(username, password);
		
		HttpSession session = request.getSession();
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add(username);
		arrayList.add(password);
		
		if (count == 1) 
		{
			response.sendRedirect("home.jsp");
			session.setAttribute("LoginData", arrayList);
		}
		else
		{
			response.sendRedirect("index.jsp");
			session.setAttribute("msg", "username and password are wrong");
		}
	}
	
	
	@PostMapping("cart")
	public void CartDetails(HttpServletRequest request , HttpServletResponse response) throws IOException 
	{
		int productsn =Integer.parseInt(request.getParameter("productsn")) ;
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		int count =Integer.parseInt(request.getParameter("count")) ;
		
		System.out.println(productsn+"**"+mobile+"**"+password+"**"+count);
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		CartDao cd = ac.getBean(CartDao.class);
		HttpSession session = request.getSession();
		int ProductExist = cd.CheckCart(productsn, mobile, password);
		if (ProductExist == 0) {
			
			CartPojo  value = cd.save(new CartPojo(productsn, mobile, password, count));
			if (value != null) {
				response.sendRedirect("cart.jsp");
				session.setAttribute("msg", "product added successfully");
			
			}
			else {
				response.sendRedirect("product-details.jsp");
				session.setAttribute("msg", "someting went wrong");
			}
		}
		else {
			int updateItem = cd.addItemIfExist(count,productsn, mobile, password);
			if (updateItem != 0) {
				response.sendRedirect("cart.jsp");
			}
			else {
				response.sendRedirect("product-details.jsp");
				session.setAttribute("msg", "someting went wrong");
			}
		}
	}
	@PostMapping("billingdetails")
	public void billingaddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String firstname = request.getParameter("firstname");
		String country = request.getParameter("country");
		String address = request.getParameter("address");
		
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postcode = request.getParameter("postcode");
		String phonenumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");
		String notes = request.getParameter("notes");
		String datetime = LocalDateTime.now().toString();
		String charge = request.getParameter("delivery");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		System.out.println("postcode :"+postcode);
		String orderid = datetime+mobile;
		
		HttpSession session = request.getSession();
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		BillingAddressDao bean = ac.getBean(BillingAddressDao.class);
		BillingAddressPojo save = bean.save(new BillingAddressPojo(firstname, country, address, city, state, postcode,
				phonenumber, email, notes, datetime, mobile, password, orderid, charge, "No"));

		System.out.println("mobile:"+mobile+"pass:"+password);
		if (save != null) {
			CartDao cartdao = ac.getBean(CartDao.class);
			List<CartPojo> list = cartdao.readCart(mobile, password);
//			System.out.println("list of mob:"+list.get(1));
			
			for(CartPojo cp : list) {
				int productsn = cp.getProductsn();
				int count = cp.getCount();
				
				ProductDao productdao = ac.getBean(ProductDao.class);
				Optional<ProductPojo> productDetails = productdao.findById(productsn);
				ProductPojo productPojo = productDetails.get();
				System.out.println(productsn);
				
				PlaceOrderDao placeorderdao = ac.getBean(PlaceOrderDao.class);
				PlaceOrderPojo saveDetails = placeorderdao.save(new PlaceOrderPojo(productsn, count, productPojo.getProducttital(), productPojo.getProductdescription(), productPojo.getCrossprice(), productPojo.getMainprice(), 
						productPojo.getAvailability(), productPojo.getDepartment(), productPojo.getCategory(), productPojo.getProductimage(), mobile, password, orderid, datetime));
			
			if (saveDetails!= null) {
				cartdao.removeItem(mobile, password);
			}
			
			}
			session.setAttribute("msg", "order place successfully");
			response.sendRedirect("cart.jsp");
			
		}
		else {
			session.setAttribute("msg", "order place  ");
			response.sendRedirect("cart.jsp");
			
			
		}
	}
	@PostMapping("orderform")
	public void orderDelivered(HttpServletRequest request , HttpServletResponse response) throws IOException 
	{
		int productsn =Integer.parseInt(request.getParameter("sn")) ;
		
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		BillingAddressDao cd = ac.getBean(BillingAddressDao.class);
		HttpSession session = request.getSession();
		int value = cd.updateDelivery(productsn);
		System.out.println(value);
			
			if ( value!= 0 ) {
				response.sendRedirect("1orderdetails.jsp");
				session.setAttribute("msg", "product delivered");
			
			}
			else {
				response.sendRedirect("1orderdetails.jsp");
				session.setAttribute("msg", "someting went wrong");
			}
	}
	@RequestMapping("cancelorder")
	public void cancleOrdr(HttpServletRequest request , HttpServletResponse response) throws IOException 
	{
		int sn =Integer.parseInt(request.getParameter("sn"));
		String orderid = request.getParameter("orderid");
		
		ApplicationContext ac = GetApplicationContext.getApplicationContext();
		PlaceOrderDao cd = ac.getBean(PlaceOrderDao.class);
		HttpSession session = request.getSession();
		System.out.println(sn);
		int value = cd.deleteOrder(sn);
		
			System.out.println();
			if ( value != 0 ) {
				int checkOrderExist = cd.checkOrderExist(orderid);
				if (checkOrderExist==0) {
					BillingAddressDao bean = ac.getBean(BillingAddressDao.class);
					int deleteDetails = bean.deleteDetails(orderid);
					if (deleteDetails==1) {
						response.sendRedirect("customerorderhistory.jsp?status=NO");
						session.setAttribute("msg", "Ordered cancelled");
					}
					else {
						response.sendRedirect("customerorderhistory.jsp?status=NO");
						session.setAttribute("msg", "somthing went wrong");
						
					}
				}else {
					response.sendRedirect("customerorderhistory.jsp?status=NO");
					session.setAttribute("msg", "somthing went wrong1");
					
				}
//				response.sendRedirect("customerhistory.jsp");
//				session.setAttribute("msg", "Ordered cancelled");
			}
			else {
				response.sendRedirect("customerorderhistory.jsp?status=NO");
				session.setAttribute("msg", "someting went wrong");
			}
	}
	
}
	
