<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="profile.safe" var="safe"/>
    <fmt:message key="category.add.title" var="addCategoryTitle"/>
    <fmt:message key="category.add.enter.name" var="enterCategoryName"/>
    <fmt:message key="category.add.error.name" var="errorCategory"/>
</fmt:bundle>

<my:designPattern role="">

    <div class="row py-5 align-items-center">
        <div class="col-md-2 col-12"></div>
        <div class="col-md-8 col-12 pl-5">
            <h2>${addCategoryTitle}</h2><br>
            <form action="/kz/addCategory" method="POST">
                <c:forEach items="${locales}" var="locale" varStatus="localeLoopCount">
                    <div class="form-group">
                        <label for="categoryName${localeLoopCount}">${locale.localeName}:</label>
                        <c:if test="${not empty category_val_error}">
                            <p class="alert alert-warning"
                               style="height: 30px;padding: 5px">${errorCategory}</p>
                        </c:if>
                        <input type="text" class="form-control" id="categoryName${localeLoopCount}" placeholder="${enterCategoryName}"
                               name="categoryName${localeLoopCount}" value="${categoryInput}">
                    </div>
                </c:forEach>
                <button type="submit" class="btn btn-primary">${safe}</button>
            </form>
        </div>
        <div class="col-md-2 col-12"></div>
    </div>

</my:designPattern>