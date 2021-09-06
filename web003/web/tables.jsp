<%--
  Created by IntelliJ IDEA.
  User: My baby
  Date: 2021/8/2
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<table border="1px" align="center">
    <h1>表格</h1>
    <thead>
    <tr>
        <th >姓名</th>
        <th >年龄</th>
        <th >班级</th>

    </tr>
    </thead>
</table>
<script>
    $(function () {
        //1. ajax 访问 后台
        $.ajax({
            url:'/BiaoGeServlet',
            type:'GET',
            data:'',
            dataType:'JSON',
            success:function (res) {
                $.each(res.rows,function (index, item) {
                    //console.log(index)




                });
            }

        });
    });

</script>
</body>
</html>
