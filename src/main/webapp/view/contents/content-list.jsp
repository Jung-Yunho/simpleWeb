<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>작성된 글 리스트</title>
</head>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script>

</script>
<style type="text/css">
    h2, h3 {
        text-align: center;
    }

    body {
        margin: 20px auto;
        display: block;
        width: 80%;
    }

    a, a:visited, a:link, a:active {
        display: inline-block;
        text-decoration: none;
        color: inherit;
        width: 100%;
        height: 100%;
    }

    a:hover {
        text-decoration: underline;
        color: blue;
    }

    .content-container {
        margin: 0 auto;
        width: 800px;
    }

    .content-table {
        border-top: 1px solid;
        border-collapse: collapse;
        width: 700px;
        margin: 0 auto;
        text-align: center;
    }

    .numSize{
        width: 10%;
    }

    .dateSize{
        width: 20%;
    }

    th, td {
        border-bottom: 1px solid;
        padding: 10px;
    }

    .button-container {
        margin: 0 auto;
        text-align: right;
        width: 700px;
    }

    .row-color{
        background-color: #F5F5F5;
    }

    .fonts-bold{
        font-weight: bold;
    }
</style>

<body>
<div class="content-container">
    <div class="button-container">
        <input type="button" value="목록" onclick="location.href='../index'"/>
        <input type="button" value="글 작성" onclick="location.href='add-content'"/>
    </div>
    <h2>${category.name}</h2>
    <h3>작성된 글 리스트</h3>
    <table class="content-table">
        <tr class="row-color">
            <td class="numSize fonts-bold">번호</td>
            <td class="fonts-bold">제목</td>
            <td class="dateSize fonts-bold">작성 날짜</td>
        </tr>
        <c:forEach items="${contentList}" var="list">
            <tr>
                <td class="row-color"><a href="${list.num}">${list.num}</a></td>
                <td><a href="${list.num}" style="overflow: hidden">${list.title}</a></td>
                <td><a href="${list.num}">${list.modifyDT}</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>