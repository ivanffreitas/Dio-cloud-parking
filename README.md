# Dio-cloud-parking
Repositorio do projeto desenvolvido no curso da DIO

### Banco de dados POSTGRES
Comando para criar banco de dados utilizando Docker

docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

Comandos basicos
docker ps       listar
docker stop     
docker starde