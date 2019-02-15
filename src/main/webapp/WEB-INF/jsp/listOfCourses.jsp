<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="courses.categories" var="cCategories"/>
    <fmt:message key="courses.categories.show.all" var="allCategories"/>
</fmt:bundle>

<my:designPattern role="guest">

    <div class="row py-5">
        <div class="col-md-3 col-12">
            <h2>${cCategories}</h2>
            <div class="list-group">
                <a href="/kz/changeCategory?categoryID=0" class="list-group-item list-group-item-action">${allCategories}</a>
                <c:forEach items="${categories}" var="category">
                    <a href="/kz/changeCategory?categoryID=${category.id}" class="list-group-item list-group-item-action">${category.categoryName}</a>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-9 col-12 pl-5">
            <c:forEach items="${courses}" var="course">
                <a href="/kz/course?courseID=${course.id}" class="card-link"><h2>${course.courseName}</h2></a>
                <p class="text-info">${course.startDate} - ${course.endDate}</p>
                <p class="text-secondary">${course.description}</p>
            </c:forEach>
        </div>
    </div>

</my:designPattern>