

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
  <title>Filter Example</title>

</head>
<body>
<atmosphere:resources/>

<div id="header"><h3>Atmosphere Chat. Default transport is WebSocket, fallback is long-polling</h3></div>
<div id="content"></div>
<div>
    <span id="status">Connecting...</span>
    <input type="text" id="input"/>
</div>

<div id="pieholder">
    <span id="piestarter">Pie messages will go here</span>

</div>

</body>
</html>