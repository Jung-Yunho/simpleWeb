<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>카테고리 리스트</title>
</head>
<style type="text/css">
    th, td {
        border-bottom: 1px solid;
        padding: 10px;
    }

    h2{
        text-align: center;
    }

    body{
        margin: 20px auto;
        display: block;
        width: 80%;
    }

    a, a:visited, a:link, a:active{
        display: inline-block;
        text-decoration: none;
        color: inherit;
        width: 100%;
        height: 100%;
    }

    a:hover{
        text-decoration: underline;
        color: blue;
    }

    .category-container{
        margin: 20px auto;
    }

    .category-table {
        border-top: 1px solid;
        border-collapse: collapse;
        width: 35%;
        margin: 0 auto;
        text-align: center;
    }

    .row-color{
        background-color: #F5F5F5;
    }

    .fonts-bold{
        font-weight: bold;
    }
</style>

<body>
<div class="category-container">
    <h2>카테고리 리스트</h2>
    <table class="category-table">
        <tr class="row-color">
            <td class="fonts-bold">번호</td>
            <td class="fonts-bold">카테고리 명</td>
        </tr>
        <c:forEach items="${categoryList}" var="list">
            <tr>
                <td class="row-color"><a href="${list.name_en}/content-list">${list.num}</a></td>
                <td><a href="${list.name_en}/content-list">${list.name}</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
