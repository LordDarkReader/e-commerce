# Getting Started

1. run docker desktop
2. docker-compose up -d
3. docker-compose down
4. cd /frontend/ ng serve 
5. run config-service
6. run discovery-service
7. run other services


# keycloak
keycloak http://localhost:9098  admin/admin

url: 'http://localhost:9098',
realm: 'project-x',
clientId: 'project-x-clientID'

client id: project-x-clientID, standard-flow, direct access grants
root url: http://localhost:4200, valid redirect URIs: http://localhost:4200/*, valid post logout http://localhost:4200/*, web origins(backend) *,
