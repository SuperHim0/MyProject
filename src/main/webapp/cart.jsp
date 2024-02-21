<%@page import="java.util.HashMap"%>
<%@page import="java.util.Optional"%>
<%@page import="com.example.superhim.pojo.ProductPojo"%>
<%@page import="com.example.superhim.dao.ProductDao"%>
<%@page import="com.example.superhim.pojo.CartPojo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<String> loginData = (ArrayList<String>) session.getAttribute("LoginData");
	if(loginData == null){
		response.sendRedirect("index.jsp");
		return;
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

     <!-- Humberger Begin -->
    <%@include file="humburgur.jsp"%>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <%@include file="header.jsp" %>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
   <%@include file="hero.jsp" %>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Shopping Cart</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                            <h4><%@include file="Message/message.jsp" %></h4>
                                <tr>
                                    <th class="shoping__product">Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            	ApplicationContext ac = GetApplicationContext.getApplicationContext();
                            	int finalprice = 0 ;
                            	int subTotal = 0;
                            	ProductDao productDao = ac.getBean(ProductDao.class);
                        
                            	CartDao cartbean = ac.getBean(CartDao.class);
                        //		deleting cart item
                            	String productsn = request.getParameter("deleteCart");
                            	if(productsn != null ){

                            		Integer.parseInt(productsn);
                                 	int deleteSussOrNot = cartbean.deleteCartItem(Integer.parseInt(productsn)); 
                                 	session.setAttribute("msg", "Item deleted succussfully");
                                 	response.sendRedirect("cart.jsp");
                            	}
                            	System.out.println("product sn form cartjsp:"+productsn);
                            	
                             	List<CartPojo> list = bean.readCart(loginData.get(0), loginData.get(1));
                            	
                             	System.out.println("list size:"+list.size());
								HashMap <ProductPojo,Integer> hm = new HashMap<>();
                             	
                             	for(CartPojo cartpojo :list)
                             	{
                             	 	int productSn = cartpojo.getProductsn();
                             		Optional<ProductPojo> cartList= productDao.findById(productSn);
                             		ProductPojo pp =  cartList.get();
                           
                             		int count = cartpojo.getCount();
                             		int totalMainprice = Integer.parseInt(pp.getMainprice())*count;
                             		finalprice = finalprice+totalMainprice;
                             		int totalCrossprice = Integer.parseInt(pp.getCrossprice())*count;
                             		subTotal = subTotal+totalCrossprice;
                             		
                             		%>
                            
                                <tr>
                                    <td class="shoping__cart__item">
                                        <img src="myown/productimage/<%=pp.getProductimage() %>" alt="" height="100" width="80">
                                        <h5><%=pp.getProducttital() %></h5>
                                    </td>
                                    <td class="shoping__cart__price">
                                        <%=pp.getMainprice() %>
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" value="<%=cartpojo.getCount() %>">
                                            </div>
                                            
                                        </div>
                                    </td>
                                    
                                    
                                    <td class="shoping__cart__total">
                                        &#x20B9; <%=totalMainprice %>
                                    </td>
                                    <td class="shoping__cart__item__close">
                                   <%--  <form action="deleteItemFromCart" method="post" >
                                    <input type="hidden" name="productsn" value="<%=productSn %>">
                                   
                                    </form> --%>
                                    <a href ="cart.jsp?deleteCart=<%=productSn %>" class="icon_close"></a>
                                    </td>
                                </tr>
                               <%
                               hm.put(pp,count);
                             	} 
                               
                               %>
                             
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="shop-grid.jsp" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                        <a href="cart.jsp" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            Upadate Cart</a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            <h5>Discount Codes</h5>
                            <form action="#">
                                <input type="text" placeholder="Enter your coupon code">
                                <button type="submit" class="site-btn">APPLY COUPON</button>
                            </form>
                            
                            <%
                            int discount = subTotal-finalprice;
                            int totalFinalPrice = finalprice;
                            String charge = "free";
                            if (finalprice < 500 ){
                            	charge = "149";
                            	finalprice = finalprice+149;
                            }
                            
                            session.setAttribute("checkoutData", hm);
                            %>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span><del>&#x20B9; <%=subTotal %>/-</del></span></li>
                            <li>Discount <span>&#x20B9; <%=discount %>/-</span></li>
                            <li>Total <span>&#x20B9; <%=totalFinalPrice %>/-</span></li>
                            <li>Delivery charge <span>&#x20B9; <%=charge %>/-</span></li>
                            <li>To Pay <span>&#x20B9; <%=finalprice %>/-</span></li>
                        </ul>
                        <a href="checkout.jsp" class="primary-btn">PROCEED TO CHECKOUT</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
    <%@include file="footer.jsp" %>
    <!-- Footer Section End -->

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>


</body>

</html>