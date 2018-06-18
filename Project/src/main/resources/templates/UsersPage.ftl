<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="names">
    <table>
        <tr>
            <th>
                Логин
            </th>
        </tr>
        <#list users as user>
            <tr>
                <td>
                    ${user.login}
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>