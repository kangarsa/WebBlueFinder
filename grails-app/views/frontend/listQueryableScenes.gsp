<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
    <g:each in="${filterlist}" var="p">
      <li>${p}</li>
    </g:each>
  </div>
</body>
</html>