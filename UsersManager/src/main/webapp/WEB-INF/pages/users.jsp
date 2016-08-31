<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Таблица юзеров</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

    </style>
</head>
<body>
<br/>
<br/>

<h1>Список юзеров</h1>
<form action="/users{filter}">
    Поиск: <input type="text" name="filter">
    <input type="submit" value="Искать">
</form>
<a>Если хотите увидеть всех пользователей, нажмите "Искать" оставив поле "Поиск:" пустым</a>
<br>
    <c:if test="${!empty listUsers}">
        <table class="tg">
            <tr>
                <th width="80">id</th>
                <th width="120">Имя</th>
                <th width="120">Возраст</th>
                <th width="80">Админ?</th>
                <th width="120">Дата создания</th>
                <th width="60">Изменить</th>
                <th width="60">Удалить</th>
            </tr>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.admin}</td>
                    <td>${user.date}</td>
                    <td><a href="<c:url value="/edit/${user.id}"/>">Изменить</a></td>
                    <td><a href="<c:url value="/remove/${user.id}"/>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br>
    <a href="/fill">Наполнить таблицу 15 юзерами</a>
    <br>
    <h1>Добавить юзера</h1>


    <c:url var="addAction" value="/users/add"/>
    <form:form action="${addAction}" commandName="user">
        <table>
            <c:if test="${!empty user.id}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="Идешник"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Имя"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="age">
                        <spring:message text="Возраст"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="age"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="admin">
                        <spring:message text="Это админ?"/>
                    </form:label>
                </td>
                <td>
                    <form:checkbox path="admin"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty user.name}">
                        <input type="submit" value="<spring:message text="Изменить"/>"/>
                    </c:if>
                    <c:if test="${empty user.name}">
                        <input type="submit" value="<spring:message text="Добавить"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
    </body>
</html>