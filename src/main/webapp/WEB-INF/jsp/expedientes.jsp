<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <title>Expedientes</title>
    </head>
    <body>
        <h3>Expedientes Abiertos</h3>
    </body>
    <ul>
        <c:forEach items="${expedientes}" var="exp">
            <li>${exp.idExpediente} ${exp.persona.nombres} ${exp.persona.apellidoPaterno}</li>
        </c:forEach>
    </ul>
    Generado en ${fechaGeneracion}
</html>
