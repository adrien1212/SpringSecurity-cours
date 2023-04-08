# Introduction

Nous nous intéressons à la mise en place d'une *Authentification Basic*.
- [Video youtube associée](https://youtu.be/iWlLXhdqp-U)

# Architecture
L'architecture mise en place est la suivante. Elle reprend les concepts expliqués dans [cette video](https://youtu.be/L17DACu3qog) auquels nous rajoutons la notion de `SecurityConfig`

![Architecture mise en place](https://i.imgur.com/wHuPaNb.png)


# Fonctionnement Spring Security

La *Security Filter Chain* est une série de filtre qui intercepte toutes les requêtes avant qu'elles appèlent le Contrôleur.

Le `AuthenticationManager` retourne :
- Une exception si l'authentification échoue. L'application se stop, elle n'intéroge pas le Contrôleur.
- Une Authorisation si l'authenfitication réussie.

Le `DaoAuthenticationProvider` :
- Vérifie si les informations envoyées (username et password) sont enregistrées en base de données. Pour ce faire il va appeler la méthode `loadUserByUsername(String username)`.
- Retourne une Authorisation si c'est le cas.

![Fonctionnement](https://i.imgur.com/0igZzkM.png)

# Diagramme de séquence

![Diagramme de séquance authentification Basic](https://i.imgur.com/8UiVud7.png)