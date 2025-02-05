<%--@elvariable id="currentUser" type="com.wrox.site.User"--%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
    <head>
        <title>User Home</title>
    </head>
    <body>
        ID: ${currentUser.userId}<br />
        Username: ${currentUser.username}<br />
        Name: ${currentUser.name}<br />
    </body>
</html>
