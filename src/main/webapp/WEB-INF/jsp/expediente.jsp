<html>
    <head>
        <title>Expediente</title>
    </head>
    <body>
        <h3>Expediente ${expediente.idExpediente}</h3>
        
        <div>
            Creado en: ${expediente.fechaCreacion}
        </div>
        <div>Usuario: ${expediente.persona.nombres} 
        ${expediente.persona.apellidoPaterno} 
        ${expediente.persona.apellidoMaterno}
        </div>
    </body>
</html>
