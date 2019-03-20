<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="courses.name" var="coursesList"/>
    <fmt:message key="my.courses.name" var="courseName"/>
    <fmt:message key="courses.delete" var="deleteCourse"/>
    <fmt:message key="button.delete" var="deleteButton"/>
    <fmt:message key="courses.alert" var="reviewInfo"/>
    <fmt:message key="main.page" var="mainPage"/>
    <fmt:message key="course.delete.success" var="deleteCourseSuccess"/>
    <fmt:message key="course.delete.error" var="deleteCourseError"/>
</fmt:bundle>

<my:designPattern>

    <div class="row py-5 align-items-center">
        <div class="col-md-1 col-12"></div>
        <div class="col-md-10 col-12 pl-5">
            <h2>${coursesList}</h2>
            <p class="display-6">*${reviewInfo}</p>
            <c:if test="${not empty course_delete_success}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${deleteCourseSuccess}</p>
            </c:if>
            <c:if test="${not empty course_delete_error}">
                <p class="alert alert-warning"
                   style="height: 30px;padding: 5px">${deleteCourseError}</p>
            </c:if>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>${courseName}</th>
                    <th>${deleteCourse}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${courses}" var="course">
                    <tr>
                        <td><a href="/kz/course?courseID=${course.id}"
                               class="card-link">${course.courseName}</a></td>
                        <td><a href="/kz/deleteCourse?courseID=${course.id}" class="btn btn-primary">${deleteButton}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="/kz/listOfCourses" class="btn btn-primary">${mainPage}</a>
        </div>
        <div class="col-md-1 col-12"></div>
    </div>

</my:designPattern>