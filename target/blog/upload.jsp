<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/11/21
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图片上传页面</title>
</head>
<style>
    .border{
        width: 120px;
        height: 170px;
        border:2px dashed #bdbdbd;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    @font-face {
        font-family: 'iconfont';  /* project id 1434145 */
        src: url('//at.alicdn.com/t/font_1434145_nugefos8si.eot');
        src: url('//at.alicdn.com/t/font_1434145_nugefos8si.eot?#iefix') format('embedded-opentype'),
        url('//at.alicdn.com/t/font_1434145_nugefos8si.woff2') format('woff2'),
        url('//at.alicdn.com/t/font_1434145_nugefos8si.woff') format('woff'),
        url('//at.alicdn.com/t/font_1434145_nugefos8si.ttf') format('truetype'),
        url('//at.alicdn.com/t/font_1434145_nugefos8si.svg#iconfont') format('svg');
    }
    .iconfont {
        font-family: 'iconfont' !important;
        font-size: 60px;
        font-style: normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
        cursor: pointer;
        color: #616161;
    }
    .select-file{
        position:absolute;
        top:0;
        left: 0;
        width: 120px;
        height: 170px;
        opacity: 0;
        z-index: 9999;
        cursor: pointer;
    }
    .bth{
        margin-top: 10px;
        width: 120px;
        height: 40px;
        background-color: #e0e0e0;
        color: #212121;
        border-radius: 5px;
        font-size: 16px;
        font-weight: 400;
    }
    p{
        color: #bdbdbd;
        font-size: 16px;
        font-weight: 400;
    }
</style>
<body>

    <form action="/upload" method="post" enctype="multipart/form-data">
        <div class="border">
        <i class="iconfont"> &#xe66e;</i>
        <input type="file" name="filename" multiple class="select-file">
        </div>
        <div class="text">
            <input type="submit" value="上传" class="bth">
            <p>（最大单个可上传最大文件为8MB）</p>
            <p>${msg}</p>
            <p>${url}</p>
        </div>
<%--        <input type="submit" value="上传" class="bth">--%>
    </form>
<%--<form action="/upload" method="post" enctype="multipart/form-data">--%>
<%--    <input type="file" name="filename" multiple>--%>
<%--    <input type="submit" value="上传">--%>
<%--</form>--%>
<%--<p>${msg}</p>--%>
<%--<p>${url}</p>--%>
</body>
</html>
