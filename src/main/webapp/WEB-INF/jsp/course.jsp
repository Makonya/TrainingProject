<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="course.teacher" var="teacher"/>
    <fmt:message key="course.add.course" var="addCourse"/>
    <fmt:message key="course.comment.title" var="commentTitle"/>
    <fmt:message key="course.add.comment" var="addComment"/>
    <fmt:message key="course.edit" var="courseEdit"/>
    <fmt:message key="course.add.marks" var="courseAddMarks"/>
    <fmt:message key="course.date.start" var="startDate"/>
    <fmt:message key="course.date.end" var="endDate"/>
    <fmt:message key="course.description" var="description"/>
    <fmt:message key="main.back" var="MBack"/>
    <fmt:message key="course.comments" var="courseComments"/>
</fmt:bundle>

<my:designPattern role="guest">

    <div class="row py-5 align-items-center">
        <div class="col-md-1 col-12"></div>
        <div class="col-md-10 col-12 pl-5">
            <h2>${courseName}</h2>
            <p class="text-info">${startDate}: ${courseStartDate}<br>
                    ${endDate}: ${courseEndDate}</p>
            <p class="text-info">${teacher}: ${courseTeacher}</p>
            <p class="text-secondary">${description}: ${courseDescription}</p>
            <c:if test="${sessionScope.role eq 'student'}">
                <c:if test="${not empty student_add_course}">
                    <a href="/kz/addCourse?idCourse=${courseId}" class="btn btn-primary">${addCourse}</a>
                </c:if>
                <c:if test="${not empty student_add_comment}">
                    <div class="form-group">
                        <label for="comment">${commentTitle}</label>
                        <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
                    </div>
                    <a href="/kz/addComment?idCourse=${courseId}" class="btn btn-primary">${addComment}</a>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.role eq 'teacher'}">
                <c:if test="${not empty editCourse}">
                    <a href="/kz/editCourse?idCourse=${courseId}" class="btn btn-primary">${courseEdit}</a>
                </c:if>
                <c:if test="${not empty markStudents}">
                    <a href="/kz/addMarks?idCourse=${courseId}" class="btn btn-primary">${courseAddMarks}</a>
                </c:if>
            </c:if>
            <a href="/kz/listOfCourses" class="btn btn-primary">${MBack}</a><br/><br/>
            <c:if test="${not empty feedback}">
                <div class="jumbotron">
                <h2>${courseComments}:</h2>
                --------------------------------<br/>
                <c:forEach items="${feedback}" var="comment">
                    <p class="text-info">${comment.feedbackDate}</p>
                    <p class="text-secondary">${comment.feedbackText}</p>
                    --------------------------------<br/>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <div class="col-md-1 col-12"></div>
    </div>

</my:designPattern>