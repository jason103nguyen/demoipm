<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="com.demoipm.consts.URLConst" %>
<t:template>
    <jsp:attribute name="customCss">
        <style>
            /*custom style*/
        </style>
    </jsp:attribute>
    <jsp:attribute name="title">
        My Page
    </jsp:attribute>
    <jsp:attribute name="header">
        MY PAGE
    </jsp:attribute>
    <jsp:attribute name="content">
        This is the content of my page
    </jsp:attribute>
    <jsp:attribute name="customScript">
        <script>
            console.log('custom-script');
        </script>
    </jsp:attribute>
</t:template>