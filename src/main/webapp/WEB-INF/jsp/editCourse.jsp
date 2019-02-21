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
                    <input type="text" class="form-control" id="startDate" name="startDate" value="${startDateInput}" data-mask="____-__-__">
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
                    <input type="text" class="form-control" id="endDate" name="endDate" value="${endDateInput}"  data-mask="____-__-__">
                </div>
                <button type="submit" class="btn btn-primary">${safe}</button>
                <a href="/kz/listOfCourses" class="btn btn-primary">${MBack}</a>
            </form>
            <script>
                Array.prototype.forEach.call(document.body.querySelectorAll("*[data-mask]"), applyDataMask);

                function applyDataMask(field) {
                    var mask = field.dataset.mask.split('');

                    // For now, this just strips everything that not a number
                    function stripMask(maskedData) {
                        function isDigit(char) {
                            return /\d/.test(char);
                        }
                        return maskedData.split('').filter(isDigit);
                    }

                    // Replace `_` characters with characters from `data`
                    function applyMask(data) {
                        return mask.map(function(char) {
                            if (char != '_') return char;
                            if (data.length == 0) return char;
                            return data.shift();
                        }).join('')
                    }

                    function reapplyMask(data) {
                        return applyMask(stripMask(data));
                    }

                    function changed() {
                        var oldStart = field.selectionStart;
                        var oldEnd = field.selectionEnd;

                        field.value = reapplyMask(field.value);

                        field.selectionStart = oldStart;
                        field.selectionEnd = oldEnd;
                    }

                    field.addEventListener('click', changed)
                    field.addEventListener('keyup', changed)
                }
            </script>
        </div>
        <div class="col-md-2 col-12"></div>
    </div>

</my:designPattern>