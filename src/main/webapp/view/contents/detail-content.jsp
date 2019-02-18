<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>작성된 글 보기</title>
</head>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script>
    function delBtn() {
        var result = confirm('삭제하시겠습니까 ? ');
        if (result == true) {
            var contentID = ${content.contentID};
            $.ajax({
                type: 'POST', async: false, dataType: 'text', url: '${content.num}/delete',
                data: {contentID: contentID},
                success: function (result) {
                    if (result == 1) {
                        location.href = 'content-list';
                    }
                },
                error: function (data, status, err) {
                    alert('오류 발생으로 인해 게시글을 삭제하지 못했습니다. \n 에러코드:' + status + ' : ' + err);
                    location.href = 'content-list';
                }
            });
        } else
            return false;
    }
</script>
<style type="text/css">
    body {
        margin: 20px auto;
        display: block;
        width: 80%;
    }

    .container {
        width: 800px;
        margin: 0 auto;
    }

    .button-container {
        text-align: right;
        margin-bottom: 20px;
    }

    .content-container {
        padding-top: 5px;
        padding-bottom: 5px;
    }

    .content-table {
        border-top: 1px solid;
        border-collapse: collapse;
        width: 100%;
    }

    td {
        border-bottom: 1px solid;
        padding: 7px 0px;
        text-align: center;
    }
    tr > td:nth-child(1){
        width: 15%;
    }

    tr > td:nth-child(2){
        text-align: left;
        padding-left: 20px;
    }
    .row-color{
        background-color: #F5F5F5;
    }
</style>

<body>
<div class="container">
    <div class="content-container">
        <div class="button-container">
            <input type="button" value="목록" onclick="location.href='content-list'"/>
            <input type="button" value="수정" onclick="location.href='${content.num}/edit-content'"/>
            <input type="button" value="삭제" onclick="delBtn()"/>
        </div>
        <table class="content-table">
            <tr>
                <td class="row-color">카테고리</td>
                <td>${content.categoryName}</td>
            </tr>
            <tr>
                <td class="row-color">제목</td>
                <td>${content.title}</td>
            </tr>
            <tr>
                <td class="row-color">작성날짜</td>
                <td>${content.modifyDT}</td>
            </tr>
        </table>
        <div class="row-color" style="width: 800px">${content.content}</div>
    </div>
</div>
</body>
</html>