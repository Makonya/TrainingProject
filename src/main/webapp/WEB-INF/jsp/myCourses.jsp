<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="my.courses.list" var="myCourses"/>
    <fmt:message key="my.courses.name" var="courseName"/>
    <fmt:message key="course.date.start" var="startDate"/>
    <fmt:message key="course.date.end" var="endDate"/>
    <fmt:message key="my.courses.mark" var="courseMark"/>
    <fmt:message key="my.courses.delete" var="deleteCourse"/>
    <fmt:message key="my.courses.add.mark" var="addMarks"/>
    <fmt:message key="course.edit" var="courseEdit"/>
    <fmt:message key="course.delete.course" var="deleteCourse"/>
    <fmt:message key="course.add.marks" var="courseAddMarks"/>
    <fmt:message key="main.back" var="MBack"/>
</fmt:bundle>

<my:designPattern role="">

    <div class="row py-5 align-items-center">
        <div class="col-md-1 col-12"></div>
        <div class="col-md-10 col-12 pl-5">
            <h2>${myCourses}</h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>${courseName}</th>
                    <th>${startDate}</th>
                    <th>${endDate}</th>
                    <c:if test="${sessionScope.role eq 'student'}">
                        <th>${courseMark}</th>
                        <th>${deleteCourse}</th>
                    </c:if>
                    <c:if test="${sessionScope.role eq 'teacher'}">
                        <th>${addMarks}</th>
                        <th>${courseEdit}</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseUsers}" var="courseUser">
                    <tr>
                        <td><a href="/kz/course?courseID=${courseUser.idCourse}" class="card-link">${courseUser.templCourseName}</a></td>
                        <td>${courseUser.templStartDate}</td>
                        <td>${courseUser.templEndDate}</td>
                        <c:if test="${sessionScope.role eq 'student'}">
                            <td>${courseUser.templMark}</td>
                            <td>
                                <a href="/kz/deleteCourse?courseID=${courseUser.idCourse}" class="btn btn-primary">${deleteCourse}</a>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope.role eq 'teacher'}">
                            <td>${addMarks}</td>
                            <td>${courseEdit}</td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table><br>
            <a href="/kz/listOfCourses" class="btn btn-primary">${MBack}</a>
        </div>
        <div class="col-md-1 col-12"></div>
    </div>

</my:designPattern>