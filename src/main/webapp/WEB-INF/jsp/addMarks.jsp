<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<c:url var="course_url" value="/kz/addMarks?courseID=${courseID}"/>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="mark.student.name" var="studentName"/>
    <fmt:message key="mark.student.surname" var="studentSurname"/>
    <fmt:message key="mark.student.mark" var="studentMark"/>
    <fmt:message key="my.courses.name" var="MCourseName"/>
    <fmt:message key="profile.safe" var="safe"/>
    <fmt:message key="main.page" var="mainPage"/>
    <fmt:message key="main.back" var="back"/>
    <fmt:message key="course.return" var="backToCoursePage"/>
    <fmt:message key="mark.success.update" var="updateSuccess"/>
    <fmt:message key="mark.success.insert" var="insertSuccess"/>
    <fmt:message key="mark.changes.no" var="noChanges"/>
    <fmt:message key="main.my.courses" var="MCourses"/>
</fmt:bundle>

<my:designPattern role="">
    <div class="row py-5 align-items-center">
        <div class="col-md-1 col-12"></div>
        <div class="col-md-10 col-12 pl-5">
            <h2>${MCourseName}: ${courseName}</h2><br>
            <c:if test="${not empty no_changes}">
                <p class="alert alert-info"
                   style="height: 30px;padding: 5px">${noChanges}</p>
            </c:if>
            <c:if test="${not empty update_success}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${updateSuccess}</p>
            </c:if>
            <c:if test="${not empty insert_success}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${insertSuccess}</p>
            </c:if>
            <form action="${course_url}" method="POST">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>${studentName}</th>
                        <th>${studentSurname}</th>
                        <th>${studentMark}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${marks}" var="mark" varStatus="markLoopCount">
                        <tr>
                            <input type="hidden" value="${mark.idUser}" name="userId${markLoopCount.count}"/>
                            <td>${mark.tempStudentName}</td>
                            <td>${mark.tempStudentSurname}</td>
                            <td>
                                <select class="form-control" id="total" name="total${markLoopCount.count}"
                                        value="${mark.tempMark}">
                                    <option <c:if test="${mark.tempMark eq 0}">selected</c:if>>-</option>
                                    <option <c:if test="${mark.tempMark eq 2}">selected</c:if>>2</option>
                                    <option <c:if test="${mark.tempMark eq 3}">selected</c:if>>3</option>
                                    <option <c:if test="${mark.tempMark eq 4}">selected</c:if>>4</option>
                                    <option <c:if test="${mark.tempMark eq 5}">selected</c:if>>5</option>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary">${safe}</button>
            </form>
            <br>
            <a href="/kz/course?courseID=${courseID}" class="btn btn-primary">${backToCoursePage}</a>
            <a href="/kz/myCourses" class="btn btn-primary">${MCourses}</a><br><br>
            <a href="/kz/listOfCourses" class="btn btn-primary">${mainPage}</a>
        </div>
        <div class="col-md-1 col-12"></div>
    </div>
</my:designPattern>