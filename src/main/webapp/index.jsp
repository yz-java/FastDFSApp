<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/fileinput.min.js"></script>
    <link href="css/fileinput.min.css" rel="stylesheet"/>
</head>
<script type="application/javascript">
    $(document).ready(function () {
        $("#file").fileinput({
            uploadUrl: '/file/upload', // you must set a valid URL here else you will get an error
            allowedFileExtensions: ['jpg', 'png', 'gif', 'pdf','jpeg','docx','doc','mp3','mp4','apk'],
            overwriteInitial: false,
            fileType: "any"
        }).on("fileuploaded", function (event, data) {
            if (data.response) {
                $("#fileId").html('文件ID：'+data.response.data)
            }
        })
    })
    function deleteByFileId() {
        var fileId = $("input[name='fileId']").val();
        $.post('/file/delete',{'fileId':fileId},function (data) {
            alert(data);
        })
    }
</script>
<body>
<div class="container">
    <div style="height: 201px">
        <form class="form-group">
            <label class="control-label">Select File</label>
            <input id="file" type="file" multiple class="file-loading col-xs-4" data-preview-file-type="any"
                   data-preview-file-icon="">
            <p id="fileId"></p>
        </form>

        <form class="form-inline" action="/file/delete" method="post">
            <div class="form-group">
                <label for="inputFileId">文件ID：</label>
                <input type="text" name="fileId" class="form-control" id="inputFileId" placeholder="文件ID">
                <button type="button" class="btn btn-default" onclick="deleteByFileId();">删除</button>
            </div>
        </form>
    </div>

</div>

</body>
</html>
