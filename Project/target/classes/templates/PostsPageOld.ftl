<#ftl encoding='UTF-8'>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
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
                    ${post.post}
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>