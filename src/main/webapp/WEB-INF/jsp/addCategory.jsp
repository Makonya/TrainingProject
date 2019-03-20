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
    <fmt:message key="category.add.exist.name" var="existCategory"/>
    <fmt:message key="category.add.success" var="successCategory"/>
    <fmt:message key="main.page" var="mainPage"/>
</fmt:bundle>

<my:designPattern>

    <div class="row py-5 align-items-center">
        <div class="col-md-2 col-12"></div>
        <div class="col-md-8 col-12 pl-5">
            <c:if test="${not empty success_add_category}">
                <p class="alert alert-success"
                   style="height: 30px;padding: 5px">${successCategory}</p>
            </c:if>
            <h2>${addCategoryTitle}</h2><br>
            <c:if test="${not empty category_val_error}">
                <p class="alert alert-warning"
                   style="height: 30px;padding: 5px">${errorCategory}</p>
            </c:if>
            <c:if test="${not empty category_exist_error}">
                <p class="alert alert-warning"
                   style="height: 30px;padding: 5px">${existCategory}</p>
            </c:if>
            <form action="/kz/addCategory" method="POST">
                <c:forEach items="${locales}" var="locale" varStatus="localeLoopCount">
                    <div class="form-group">
                        <label for="categoryName${localeLoopCount}">${locale.localeName}:</label>

                        <input type="text" class="form-control" id="categoryName${localeLoopCount.count}"
                               placeholder="${enterCategoryName}"
                               name="categoryName${localeLoopCount.count}" value="${categoryInput}">
                    </div>
                </c:forEach>
                <button type="submit" class="btn btn-primary">${safe}</button>
                <a href="/kz/listOfCourses" class="btn btn-primary">${mainPage}</a><br/><br/>
            </form>
        </div>
        <div class="col-md-2 col-12"></div>
    </div>

</my:designPattern>