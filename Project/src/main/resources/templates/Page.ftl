<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="names">
    <table>
        <tr>
            <th>
                PAGE
            </th>
        </tr>
        <#list posts as post>
            <tr>
                <td>
                    ${post}
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>