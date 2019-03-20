<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<c:url var="profile_url" value="/kz/profile"/>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="registration.login" var="login"/>
    <fmt:message key="registration.enter.login" var="enterLogin"/>
    <fmt:message key="registration.password" var="password"/>
    <fmt:message key="registration.enter.password" var="enterPassword"/>
    <fmt:message key="registration.submit.password" var="submitPassword"/>
    <fmt:message key="registration.enter.submit.password" var="enterSubmitPassword"/>
    <fmt:message key="registration.mname" var="mname"/>
    <fmt:message key="registration.enter.mname" var="enterName"/>
    <fmt:message key="registration.surname" var="surname"/>
    <fmt:message key="registration.enter.surname" var="enterSurname"/>
    <fmt:message key="registration.email" var="email"/>
    <fmt:message key="registration.enter.email" var="enterEmail"/>

    <fmt:message key="registration.error.password" var="password_error_reg"/>
    <fmt:message key="registration.error.password2" var="password2_error_reg"/>
    <fmt:message key="registration.error.name" var="name_error_reg"/>
    <fmt:message key="registration.error.surname" var="surname_error_reg"/>
    <fmt:message key="registration.error.email" var="email_error_reg"/>

    <fmt:message key="profile.edit.success" var="profile_edit_success"/>
    <fmt:message key="profile.capture" var="capture"/>
    <fmt:message key="profile.safe" var="safe"/>
    <fmt:message key="main.page" var="mainPage"/>
    <fmt:message key="profile.role" var="role"/>
</fmt:bundle>

<my:designPattern>

    <div class="row py-5 align-items-center">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h2>${capture}</h2>
            <p class="alert alert-primary"
               style="height: 30px;padding: 5px">${role}: ${sessionScope.role}</p>
            <c:if test="${not empty edit_success}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${profile_edit_success}</p>
            </c:if>
            <form action="${profile_url}" method="POST">
                <div class="form-group">
                    <label for="login">${login}:</label>
                    <input type="text" class="form-control" id="login" placeholder="${enterLogin}" name="login" disabled
                           value="${loginInput}">
                </div>
                <div class="form-group">
                    <label for="password">${password}:</label>
                    <c:if test="${not empty password_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${password_error_reg}</p>
                    </c:if>
                    <input type="password" class="form-control" id="password" placeholder="${enterPassword}"
                           name="password">
                </div>
                <div class="form-group">
                    <label for="password2">${submitPassword}:</label>
                    <c:if test="${not empty password2_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${password2_error_reg}</p>
                    </c:if>
                    <input type="password" class="form-control" id="password2" placeholder="${enterSubmitPassword}"
                           name="password2">
                </div>
                <div class="form-group">
                    <label for="name">${mname}:</label>
                    <c:if test="${not empty name_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${name_error_reg}</p>
                    </c:if>
                    <input type="text" class="form-control" id="name" placeholder="${enterName}" name="name"
                           value="${nameInput}">
                </div>
                <div class="form-group">
                    <label for="surname">${surname}:</label>
                    <c:if test="${not empty surname_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${surname_error_reg}</p>
                    </c:if>
                    <input type="text" class="form-control" id="surname" placeholder="${enterSurname}" name="surname"
                           value="${surnameInput}">
                </div>
                <div class="form-group">
                    <label for="email">${email}:</label>
                    <c:if test="${not empty email_val_error}">
                        <p class="alert alert-warning"
                           style="height: 30px;padding: 5px">${email_error_reg}</p>
                    </c:if>
                    <input type="text" class="form-control" id="email" placeholder="${enterEmail}" name="email"
                           value="${emailInput}">
                </div>
                <button type="submit" class="btn btn-primary">${safe}</button>
                <a href="/kz/listOfCourses" class="btn btn-primary">${mainPage}</a>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>

</my:designPattern>