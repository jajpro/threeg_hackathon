/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.32
 * Generated at: 2016-06-30 12:50:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import StoreDB.ProductList;
import java.sql.*;

public final class product_005fpurchase_005fdo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("StoreDB.ProductList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");


	HttpSession session = request.getSession(false);
	if(session == null) {
		response.sendRedirect("login.jsp");
			return;
	}
	String store_id = (String)session.getAttribute("login.store_id");

request.setCharacterEncoding("utf-8");

String[] product_id = request.getParameterValues("product_id");
String[] amount = request.getParameterValues("amount");
String card_company = request.getParameter("card_company");
String card_number = request.getParameter("card_number");
String purchase_date = request.getParameter("purchase_date");
String nfc_id = request.getParameter("nfc_id");

int receipt_id = 0;
//ProductList pl = new ProductList();

//pl.setStore_id(Integer.parseInt(store_id));


try {
	
	Class.forName("com.mysql.jdbc.Driver");  
	String DB_URL ="jdbc:mysql://localhost:3306/ereceiptdb?useSSL=false";

	Connection con =  DriverManager.getConnection(DB_URL, "admin", "1234");
	
	String sql = "SELECT COUNT(purchase_date) FROM receipt";
	PreparedStatement pstmt = con.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	if(rs.next()) {
	receipt_id = (rs.getInt(1)+1)*1000+Integer.parseInt(store_id);
	}
	String receipt_sql = "INSERT INTO receipt (receipt_id,store_id, card_company, card_number, purchase_date) VALUES(?,?,?,?,?)";
	String item_sql = "INSERT INTO item (product_id, amount, receipt_id) VALUES(?,?,?)";
	String receipt_user_sql = "INSERT INTO receipt_user (receipt_id, nfc_id) VALUES(?,?)";
	
	PreparedStatement pstmt1 = con.prepareStatement(receipt_sql);
	PreparedStatement pstmt2 = con.prepareStatement(item_sql);
	PreparedStatement pstmt3 = con.prepareStatement(receipt_user_sql);

	pstmt1.setInt(1, receipt_id);
	pstmt1.setInt(2, Integer.parseInt(store_id));
	pstmt1.setString(3, card_company);
	pstmt1.setInt(4, Integer.parseInt(card_number));
	pstmt1.setString(5, purchase_date);
		
	for(int i=0;i<product_id.length;i++) {
	
		pstmt2.setInt(1, Integer.parseInt(product_id[i]));
		pstmt2.setInt(2, Integer.parseInt(amount[i]));
		pstmt2.setInt(3, receipt_id);
		
	}
	
	pstmt3.setInt(1, receipt_id);
	pstmt3.setString(2, nfc_id);
	

	pstmt1.executeUpdate();
	pstmt2.executeUpdate();
	pstmt3.executeUpdate();
	
	rs.close();
	pstmt.close();
	pstmt1.close();
	pstmt2.close();
	pstmt3.close();
	con.close();
	
}catch(SQLException e) {
	out.print(e);
	return;
}


      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("opener.location.reload(true);\r\n");
      out.write("window.close();\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
