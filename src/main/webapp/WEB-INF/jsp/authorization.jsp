<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="welcome.registration" var="MRegistration"/>
    <fmt:message key="login.capture" var="MCapture"/>
    <fmt:message key="login.login" var="MLogin"/>
    <fmt:message key="login.password" var="MPassword"/>
    <fmt:message key="login.enter.login" var="MEnterLogin"/>
    <fmt:message key="login.enter.password" var="MEnterPassword"/>
    <fmt:message key="login.signin" var="MSignIn"/>
    <fmt:message key="login.error" var="login_error_aut"/>
</fmt:bundle>

<my:designPattern role="guest">

    <div class="row py-5 align-items-center">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h2>${MCapture}</h2>
            <form action="/kz/authorization" method="post">
                <c:if test="${not empty login_error}">
                    <p class="alert alert-warning"
                       style="height: 30px;padding: 5px">${login_error_aut}</p>
                </c:if>
                <div class="form-group">
                    <label for="login">${MLogin}:</label>
                    <input type="text" class="form-control" id="login" placeholder="${MEnterLogin}" name="login">
                </div>
                <div class="form-group">
                    <label for="password">${MPassword}:</label>
                    <input type="password" class="form-control" id="password" placeholder="${MEnterPassword}" name="password">
                </div>
                <button type="submit" class="btn btn-primary">${MSignIn}</button>
                <a href="/kz/registration" class="btn btn-primary">${MRegistration}</a>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>

</my:designPattern>