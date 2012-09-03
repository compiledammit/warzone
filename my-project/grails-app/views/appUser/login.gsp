<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="bootstrap"/>
    <title><g:message code="default.login.label" default="Login"/></title>
</head>

<body>
<div class="body">
    <content tag="menuItems">

    </content>
    <g:form action="doLogin" method="post">
        <div class="dialog">
            <table class="userForm">
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label for='username'><g:message code="appUser.username.label" default="Username"/></label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="username" type='text' name='username' value='${AppUser?.username}'/></td>
                </tr>
                <tr class='prop'>
                    <td valign='top' style='text-align:left;' width='20%'>
                        <label for='password'><g:message code="appUser.password.label" default="Password"/></label>
                    </td>
                    <td valign='top' style='text-align:left;' width='80%'>
                        <input id="password" type='password' name='password'
                               value='${AppUser?.password}'/>
                    </td>
                </tr>

            </table>
        </div>

        <div class="buttons">
            <span class="formButton">
                <g:actionSubmit class="login" value="${message(code: 'default.button.login.label', default: 'Login')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>