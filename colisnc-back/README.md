# Outil de CRA RedStone - Serveur REST

Projet [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) avec les _starters_ suivants :
 * `spring-boot-starter-web` pour faire du REST
 * `spring-boot-starter-data-mongodb` pour la persistance dans [MongoDB](https://www.mongodb.com/fr)
 * `spring-boot-starter-security` pour la sécurité des services REST
 
[Gradle](https://gradle.org/) est utilisé pour le build

## Lancement du serveur en dev

## Prérequis

[MongoDB](https://www.mongodb.com/fr) doit-être installé et lancé sur le port par défaut (27017)

## Lancement du serveur

`gradle bootRun`

## Modèle de données MongoDB

La base de données s'appelle `rsCra` (en référence au no de l'appli tout en respectant les conventions de nommage MongoDB)

* `cras` : collection représentant les saisies d'activté mensuel par utiliateur
  ```json
  {
    "_id": "daniel.santos@redstone.nc/2019-01",
    "period": "2019-01",
    "user": {
      "email": "daniel.santos@redstone.nc",
      "firstName": "Daniel",
      "lastName": "Santos"
    },
    "days": [
      {
        "date": "2018-12-31T13:00:00.000+0000",
        "activities": {
          "JT": 0.5,
          "CP": 0.5,
          "CM": 0
        }
      },
      {
        "date": "2019-01-01T13:00:00.000+0000",
        "activities": {
          "JT": 1,
          "CP": 0,
          "CM": 0
        }
      }
    ]
  }
  ```
  _note: la structure `user` est un lien en base de données
  
* `users` : collection des utilisateurs
  ```json
  {
    "email": "daniel.santos@redstone.nc",
    "firstName": "Daniel",
    "lastName": "Santos"
  }
  ```
* `nonWorkingDays` : collection représentant les jours chômés
  ```json
  {
    "_id": "2019-01-01T00:00:00.000+11:00",
    "name": "Jour de l'an"
  }
  ```

### Alimentation avec un utilisateur

```bash
mongo rsCra --eval "db.users.insert({ \
  \"email\" : \"daniel.santos@redstone.nc\", \
  \"password\" : \"`bcrypt-cli mot-de-passe 10`\", \
  \"firstName\": \"Daniel\", \
  \"lastName\": \"Santos\" \
});"
```
P.S: Mode passe protégé via l'algo `Bcrypt` (un utilitaire est dispo en ligne de commande `npm install --global bcrypt-cli`)

### Alimentation des jours chômés d'année (ex: 2019)

```bash
mongo rsCra --eval "db.nonWorkingDays.insert([ \
    {\"_id\": new Date(\"2019-01-01T00:00:00.000+11:00\"), \"name\": \"Jour de l'an\"}, \
    {\"_id\": new Date(\"2019-04-22T00:00:00.000+11:00\"), \"name\": \"Pâques\"}, \
    {\"_id\": new Date(\"2019-05-01T00:00:00.000+11:00\"), \"name\": \"Fête du travail\"}, \
    {\"_id\": new Date(\"2019-06-10T00:00:00.000+11:00\"), \"name\": \"Lundi de Pentecôte\"}, \
    {\"_id\": new Date(\"2019-09-24T00:00:00.000+11:00\"), \"name\": \"Fête locale\"}, \
    {\"_id\": new Date(\"2019-11-11T00:00:00.000+11:00\"), \"name\": \"Armistice\"}, \
    {\"_id\": new Date(\"2019-12-25T00:00:00.000+11:00\"), \"name\": \"Noël\"} \
]);"
```
