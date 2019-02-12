<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<c:url var="login_url" value="/kz/login"/>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="registration.capture" var="capture"/>
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
    <fmt:message key="registration.mregister" var="mregister"/>
</fmt:bundle>

<my:designPattern role="guest">
    <div class="row py-5 align-items-center">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h2>${capture}</h2>
            <form action="${login_url}" method="POST">
                <div class="form-group">
                    <label for="login">${login}:</label>
                    <input type="text" class="form-control" id="login" placeholder="${enterLogin}" name="login">
                </div>
                <div class="form-group">
                    <label for="password">${password}:</label>
                    <input type="password" class="form-control" id="password" placeholder="${enterPassword}" name="password">
                </div>
                <div class="form-group">
                    <label for="password2">${submitPassword}:</label>
                    <input type="password" class="form-control" id="password2" placeholder="${enterSubmitPassword}" name="password2">
                </div>
                <div class="form-group">
                    <label for="name">${mname}:</label>
                    <input type="text" class="form-control" id="name" placeholder="${enterName}" name="name">
                </div>
                <div class="form-group">
                    <label for="surname">${surname}:</label>
                    <input type="text" class="form-control" id="surname" placeholder="${enterSurname}" name="surname">
                </div>
                <div class="form-group">
                    <label for="email">${email}:</label>
                    <input type="text" class="form-control" id="email" placeholder="${enterEmail}" name="email">
                </div>
                <button type="submit" class="btn btn-primary">${mregister}</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</my:designPattern>