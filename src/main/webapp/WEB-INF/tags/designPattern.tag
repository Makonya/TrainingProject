<%@ tag import="static com.epam.training.util.AppConstant.ATT_ROLE" %>
<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="role" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:url var="register_url" value="/kz/register"/>

<fmt:bundle basename="i18n">
    <fmt:message key="main.header" var="MHeader"/>
    <fmt:message key="main.language" var="MLanguage"/>
    <fmt:message key="main.profile" var="MProfile"/>
    <fmt:message key="main.footer.name" var="MName"/>
    <fmt:message key="main.footer.contact" var="MContact"/>
    <fmt:message key="main.footer.logout" var="MLogout"/>
    <fmt:message key="main.footer.edit" var="MEdit"/>
    <fmt:message key="main.my.courses" var="MCourses"/>
</fmt:bundle>
<html lang=${sessionScope.lang}>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <style>
        <jsp:directive.include file="/WEB-INF/css/bootstrap.min.css"/>
    </style>
    <title>Find your course</title>
</head>

<body>
<div class="container-fluid">
    <div class="row my-header bg-light align-items-center py-2">
        <div class="col-md-2 col-6">
            <div class="dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    ${MLanguage}
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="set-language?lang=ru">Русский</a>
                    <a class="dropdown-item" href="set-language?lang=en">English</a>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-12">
            <h1 class="display-4 text-uppercase text-center">${MHeader}</h1>
        </div>
        <div class="col-md-2 col-6">
            <c:if test="${not empty sessionScope.role}">
                <div class="dropdown">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            ${MProfile}
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/kz/myCourses">${MCourses}</a>
                        <a class="dropdown-item" href="/kz/profile">${MEdit}</a>
                        <a class="dropdown-item" href="/kz/logout">${MLogout}</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <div id="body">
        <jsp:doBody/>
    </div>

    <div class="row bg-dark align-items-center">
        <div class="col-md-12 col-12 text-center py-3">
            <h6 class="text-white ">${MName}</h6>
            <a href="mailto:makonya2302@gmail.com" class="btn btn-primary">${MContact}</a>
        </div>
    </div>
</div>
</body>

</html>