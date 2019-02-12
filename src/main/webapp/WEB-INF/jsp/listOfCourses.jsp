<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ page isELIgnored="false" %>

<fmt:bundle basename="i18n">
    <fmt:message key="courses.categories" var="categories"/>
</fmt:bundle>

<my:designPattern role="guest">

    <div class="row py-5 align-items-center">
        <div class="col-md-3 col-12">
            <h2>${categories}</h2>
            <div class="list-group">
                <a href="#" class="list-group-item list-group-item-action">First item</a>
                <a href="#" class="list-group-item list-group-item-action">Second item</a>
                <a href="#" class="list-group-item list-group-item-action">Third item</a>
            </div>
        </div>
        <div class="col-md-9 col-12 pl-5">
            <a href="#" class="card-link"><h2>Name of course</h2></a>
            <p class="text-info">DateFrom-DateTill</p>
            <p class="text-secondary">Desciption</p>
        </div>
    </div>

</my:designPattern>