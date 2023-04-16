# NewPokemon
Readme pour l'application Poki:

L'application utilise l'API PokéAPI pour afficher une liste de Pokémons. L'utilisateur peut cliquer sur un Pokémon pour obtenir plus d'informations sur celui-ci.
Le fichier MainActivity.java est responsable de l'affichage de la liste des Pokémon dans une RecyclerView. L'API PokeAPI est appelée à chaque fois que l'utilisateur fait défiler la RecyclerView pour charger plus de Pokémon.

Le fichier ListePokiAdapter.java est un adaptateur pour la RecyclerView. Il contient une liste de Pokémon qui est affichée dans la RecyclerView, avec leur nom et leur image.
Fonctionnement
L'application utilise Retrofit pour appeler l'API PokéAPI et récupérer les données des Pokémons. Elle affiche ensuite les Pokémons dans une RecyclerView à l'aide d'un adapter personnalisé appelé ListePokiAdapter. Lorsque l'utilisateur fait défiler la RecyclerView, l'application charge davantage de données de l'API PokéAPI.

Lorsque l'utilisateur clique sur un Pokémon, l'application lance une nouvelle activité appelée Item et affiche plus d'informations sur le Pokémon sélectionné.

Technologies utilisées
L'application est développée en Java et utilise les technologies suivantes :

Retrofit : Pour effectuer des appels API et récupérer les données des Pokémons.
RecyclerView : Pour afficher une liste de Pokémons sous forme de vue en liste défilante.
Glide : Pour charger et afficher des images des Pokémons depuis l'API PokéAPI.
Gson : Pour la sérialisation et la désérialisation des données des Pokémons.
Android Studio : Pour le développement de l'application.
Conclusion
Cette application est basée sur l'utilisation de Retrofit, RecyclerView et Glide pour récupérer, afficher et manipuler des données d'API dans une application Android.
