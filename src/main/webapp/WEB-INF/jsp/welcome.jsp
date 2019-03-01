<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="welcome.string.first" var="MFString"/>
    <fmt:message key="welcome.string.second" var="MSString"/>
    <fmt:message key="welcome.authorization" var="MAuthorization"/>
    <fmt:message key="welcome.registration" var="MRegistration"/>
</fmt:bundle>

<my:designPattern role="guest">

    <div class="row py-5 align-items-center">
        <div class="col-md-12">

            <p class="display-3 text-center">${MFString}</p>
            <p class="display-4 text-center">${MSString}</p>
            <p class="text-center">
                <a href="/kz/authorization" class="btn btn-primary">${MAuthorization}</a>
                <a href="/kz/registration" class="btn btn-primary">${MRegistration}</a>
            </p>
        </div>
    </div>

</my:designPattern>
