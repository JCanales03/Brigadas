GET 
http://localhost:8084/api/brigadas

GET
http://localhost:8084/api/brigadas/filtro/estado/{estadoBrigada}

GET
http://localhost:8084/api/brigadas/{id}

POST
http://localhost:8084/api/brigadas
{
  "nombre": "Brigada Terrestre Alpha",
  "tipo": "Terrestre",
  "region": "Metropolitana",
  "cantidadMiembros": 15,
  "estadoBrigada": "Disponible"
}

PUT
http://localhost:8084/api/brigadas/{id}

PATCH
http://localhost:8084/api/brigadas/{id}/estado?nuevoEstado=En+Combate
