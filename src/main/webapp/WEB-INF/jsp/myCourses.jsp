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
    <fmt:message key="main.page" var="mainPage"/>
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
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courseUsers}" var="courseUser">
                    <c:if test="${sessionScope.role eq 'student'}">
                        <tr>
                            <td><a href="/kz/course?courseID=${courseUser.idCourse}"
                                   class="card-link">${courseUser.tempCourseName}</a></td>
                            <td>${courseUser.tempStartDate}</td>
                            <td>${courseUser.tempEndDate}</td>
                            <td>${courseUser.tempMark}</td>
                        </tr>
                    </c:if>
                    <c:if test="${sessionScope.role eq 'teacher'}">
                        <tr>
                            <td><a href="/kz/course?courseID=${courseUser.id}"
                                   class="card-link">${courseUser.courseName}</a></td>
                            <td>${courseUser.startDate}</td>
                            <td>${courseUser.endDate}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="/kz/listOfCourses" class="btn btn-primary">${mainPage}</a>
        </div>
        <div class="col-md-1 col-12"></div>
    </div>

</my:designPattern>