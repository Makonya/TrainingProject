<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<c:url var="course_url" value="/kz/editCourse?courseID=${courseID}"/>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="course.description" var="description"/>
    <fmt:message key="my.courses.name" var="courseName"/>
    <fmt:message key="course.date.start" var="startDate"/>
    <fmt:message key="course.date.end" var="endDate"/>
    <fmt:message key="course.error.name" var="errorName"/>
    <fmt:message key="course.error.description" var="errorDescription"/>
    <fmt:message key="course.error.date" var="errorDate"/>
    <fmt:message key="course.error.date.start.end" var="errorStartEndDate"/>
    <fmt:message key="profile.safe" var="safe"/>
    <fmt:message key="main.back" var="MBack"/>
    <fmt:message key="course.edit.success" var="courseEditSuccess"/>
</fmt:bundle>

<my:designPattern role="">

    <div class="row py-5 align-items-center">
        <div class="col-md-2 col-12"></div>
        <div class="col-md-8 col-12 pl-5">
            <c:if test="${not empty course_edit_success}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${courseEditSuccess}</p>
            </c:if>
            <form action="${course_url}" method="POST">
                <div class="form-group">
                    <label for="courseName">${courseName}:</label>
                    <c:if test="${not empty name_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${errorName}</p>
                    </c:if>
                    <input type="text" class="form-control" id="courseName" placeholder="${enterCourseName}"
                           name="courseName" value="${nameInput}">
                </div>
                <div class="form-group">
                    <label for="description">${description}</label>
                    <c:if test="${not empty description_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${errorDescription}</p>
                    </c:if>
                    <textarea class="form-control" rows="5" id="description" name="description">${descriptionInput}</textarea>
                </div>
                <div class="form-group">
                    <label for="startDate">${startDate}</label>
                    <c:if test="${not empty start_date_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${errorDate}</p>
                    </c:if>
                    <input type="text" class="form-control" rows="5" id="startDate" name="startDate" value="${startDateInput}">
                </div>
                <div class="form-group">
                    <label for="endDate">${endDate}</label>
                    <c:if test="${not empty end_date_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${errorDate}</p>
                    </c:if>
                    <c:if test="${not empty start_end_date_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${errorStartEndDate}</p>
                    </c:if>
                    <input type="text" class="form-control" rows="5" id="endDate" name="endDate" value="${endDateInput}">
                </div>
                <button type="submit" class="btn btn-primary">${safe}</button>
                <a href="/kz/listOfCourses" class="btn btn-primary">${MBack}</a>
            </form>
        </div>
        <div class="col-md-2 col-12"></div>
    </div>

</my:designPattern>