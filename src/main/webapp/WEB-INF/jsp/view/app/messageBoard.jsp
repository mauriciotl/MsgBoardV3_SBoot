<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message Board</title>
</head>
<body>
<h2>Message Board</h2>
<form action="${pageContext.request.contextPath}/messages/add" method="post">
    <label>User ID:</label>
    <input type="number" name="userId" required>
    <label>Message:</label>
    <input type="text" name="content" required>
    <button type="submit">Add Message</button>
</form>
<hr>
<h3>Messages</h3>
<ul>
    <c:forEach var="message" items="${messages}">
        <li>
            <strong>${message.content}</strong> (User: ${message.userId}, Date: ${message.creationDate})
            <form action="${pageContext.request.contextPath}/messages/delete/${message.messageId}" method="post" style="display:inline;">
                <button type="submit">Delete</button>
            </form>
            <a href="${pageContext.request.contextPath}/messages/edit/${message.messageId}">Edit</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>