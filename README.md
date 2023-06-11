# coding_game_1_template
Coding Game Test 1

Cet exercice permet d'évaluer les connaissances des candidats sur les technologies suivantes : 

- Maven
- Java 17
- Spring Boot 
- Spring WebFlux et WebClient (MVC et RestTemplate si le candidat n'a pas de connaissance accrues sur la programmation réactive)
- Architecture Hexagonale 

Voici l'énoncé : 

- Nous souhaitons exposer une endpoint qui retourne un Flux (ou liste) de produits et qui prend comme pathVariable une size. Ce paramètre size sert à limiter le nombre de produits retournés.
- Un produit est caractérisé par 3 attributs : id (UUID) / creationDate (Date) et imagePath (String)

- id : autoGénéré 
- date : date actuelle (générée coté service)
- imagePath : info récupérée à partir d'un API publique (exemple d'api : GET https://dog.ceo/api/breeds/image/random)


- Le business : constituer x produits (x étant le nombre correspondant au size passé en paramètre)


Consignes : 
- Quand vous uploadez le zip avec le code source, merci de mettre votre nom dans le nom du fichier
- Mettre en place une architecture Hexagonale afin de bien isoler la couche Business (Domain) des autres couches
- Utiliser WebCLient pour les appels Http. Gérez les cas ou l'API retourne des erreurs (4xx et 5xx)
- Utiliser les Record de Java 17 pour les DTO. Vous pouvez aussi utiliser Lombok si vous voulez sinon.
- Si vous n'êtes pas à l'aise avec la programmation réactive, vous pouvez opter pour une programmation plus traditionnelle mais essayer d'utiliser les Stream dans ce cas. 
- Créer des Tests unitaires au moins pour la couche domain

Bonus: 
- Gérez globalement les exceptions pour retourner une réponse structurée si certains types d'exceptions sont émis.
