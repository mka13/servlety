<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${message}<br/>
<c:out value="$message}"/><br/>
<c:forEach items="${names}" var="name">
${name}<br/>
</c:forEach>

<a href="${url}">
${text}
</a>

<c:if test="${age gt 18}">
<hr4> Nie Jestes pelnoletni</h4>
</c:if>

<c:if test="${age lt 18}">
<hr4> Jestes pelnoletni</h4>
</c:if>