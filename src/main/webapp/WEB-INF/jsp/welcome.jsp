<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.03.2017
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
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
        <button type="button" class="btn btn-primary btn-lg">${MAuthorization}</button>
        <button type="button" class="btn btn-primary btn-lg">${MRegistration}</button>
      </p>
    </div>
  </div>
  </div>

</my:designPattern>

</div>