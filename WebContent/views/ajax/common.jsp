<%@ page language="java" contentType="text/plain; charset=UTF-8"%>

<%
	boolean istrue = (boolean) request.getAttribute("isTrue");
%>

<% if (istrue) { %>
{
"isTrue" : "true"
}
<% } else { %>
{
"isTrue" : "false"
}
<% } %>