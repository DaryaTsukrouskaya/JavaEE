<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-4">
            <h2>Регистрация</h2>
            <p>Заполните форму для регистрации</p>
            <form id="registrationForm" method="post" action="register" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="name">Имя: </label>
                    <input type="text" class="form-control w-25" id="name" placeholder="Имя"
                           name="newUsername"
                           required>
                    <div class="invalid-feedback">Введите имя!</div>
                </div>
                <div class="form-group">
                    <label for="surname">Фамилия:</label>
                    <input type="text" class="form-control w-25" id="surname" placeholder="Фамилия"
                           name="newUserSurname"
                           required>
                    <div class="invalid-feedback">Введите фамилию!</div>
                </div>
                <div class="form-group">
                    <label>Дата рождения:</label>
                    <select id="birthDays" name="birthDay">
                        <c:forEach var="i" begin="1" end="31">
                            <c:if test="${i > 0 && i <= 9}">
                                <option value="0${i}">0${i}</option>
                            </c:if>
                            <c:if test="${i > 9}">
                                <option value="${i}">${i}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <select id="birthMonths" name="birthMonth">
                        <c:forEach var="i" begin="1" end="12">
                            <c:if test="${i > 0 && i <= 9}">
                                <option value="0${i}">0${i}</option>
                            </c:if>
                            <c:if test="${i > 9}">
                                <option value="${i}">${i}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <select id="birthYears" name="birthYear" oninput="validateBirthday()">
                        <c:forEach var="i" begin="1900" end="2018">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                    <span id="birthErr" style="display: none">*Возрастное ограничение: 16 лет и старше!</span>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control w-25" id="email" placeholder="Email"
                           name="newUserEmail"
                           required oninput="validateEmail()">
                    <div class="invalid-feedback">Введите email!</div>
                    <span id="validationErr" style="display: none">*Неверный формат email адреса!</span>
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input type="text" class="form-control w-25" id="password" placeholder="Введите пароль"
                           name="newUserPassword"
                           required>
                    <div class="invalid-feedback">Введите пароль!</div>
                </div>
                <div class="form-group">
                    <label for="repeatPswd">Повторите пароль:</label>
                    <input type="text" class="form-control w-25" id="repeatPswd" placeholder="Повторите пароль"
                           name="repeatPassword"
                           required oninput="validateRepeatPass()">
                    <span id="matchingError" style="display: none">*Пароли не совпадают!</span>
                    <div class="invalid-feedback">Повторите пароль!</div>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button><br>
                <span>${state}</span>
            </form>
        </div>
    </div>
</div>
</body>
</html>
