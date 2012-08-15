<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
    <meta name="layout" content="main">
</head>
<body>

<h1>Bacons</h1>

<g:each in="${bacons}">
    <p>Fiddle with numbers: <g:link action="save" id="${it.id}">${it.id}</g:link> : Salt: ${it.saltPercent}; Fat: ${it.fatPercent}</p>
</g:each>

</body>
</html>