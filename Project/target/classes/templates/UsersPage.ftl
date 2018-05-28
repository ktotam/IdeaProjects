<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<div class="names">
    <table>
        <tr>
            <th>
                Привет
            </th>
        </tr>
        <#list names as name>
            <tr>
                <td>
                    ${name}
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>