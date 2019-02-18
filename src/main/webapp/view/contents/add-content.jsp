<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 등록</title>
</head>
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8//lang/summernote-ko-KR.js"></script>
<script>
    $(document).ready(function () {
        $('#summernote').summernote({
            height: 400,
            width: 800,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
        });
    });

    function insertContent() {
        var title = $('#title');
        if(title.val().length == 0){
            alert('제목을 한 글자 이상 입력하세요');
            title.focus();
            return false;
        }
        else if(title.val().length > 50){
            alert('제목은 50자를 초과할 수 없습니다.');
            title.focus();
            return false;
        }
        else
            $('#frm').submit();
    }
</script>
<style type="text/css">
    body {
        margin: 20px auto;
        display: block;
    }

    .container {
        width: 800px;
        margin: 0 auto;
        text-align: center;
        font-weight: bold;
    }

    .button-container {
        margin-top: 20px;
    }
</style>
<body>
<div class="container">
    <h2>${category.name}</h2>
    <h3>새 글 작성</h3>
    <br>
    <form method="post" id="frm">
        <div style="width: 800px">제목 : <input type="text" name="title" id="title" size="85"/></div>
        <br>
        <div id="editor"><textarea name="content" id="summernote"></textarea></div>
        <div class="button-container">
            <input type="button" id="submitBtn" onclick="insertContent()" value="작성 완료"/>
            <input type="button" value="취소" onclick="location.href='content-list'"/>
        </div>
    </form>
</div>
</body>
</html>