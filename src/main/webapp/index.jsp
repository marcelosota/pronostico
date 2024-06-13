<%
	//out.println("\nNew session is " + request.getSession());
	//out.println(request.getContextPath());

	session.invalidate();
    response.sendRedirect(request.getContextPath() + "/index.jsf");
    
%>