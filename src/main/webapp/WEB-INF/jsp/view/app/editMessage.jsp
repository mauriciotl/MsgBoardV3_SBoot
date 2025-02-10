<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Message</title>
</head>
<body>
<h2>Edit Message</h2>
<form action="${pageContext.request.contextPath}/messages/update" method="post">
  <input type="hidden" name="messageId" value="${message.messageId}">
  <label>Message:</label>
  <input type="text" name="content" value="${message.content}" required>
  <button type="submit">Update</button>
</form>
<a href="${pageContext.request.contextPath}/messages/board">Back to Board</a>
</body>
</html>
