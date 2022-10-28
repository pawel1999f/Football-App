# FootballApp
 Android Application to view informations from top football leagues

main tools / concept used:
- MVVC architecture
- view model
- Room to manage local database
- Retrofit and Moshi to get data from football api: https://www.football-data.org
- Repository class as a manager for databases and network. It requests data from network (Retrofit) when asked and gets data from database. If there is no internet connection it just gets data from database. It updates database only with information required (e.g. only players from one team).
- Coil to get images from internet
- Recycler views to display data about leagues, teams and players
- Fragments, navigation component and slidingPane for UI

![Alt text](https://raw.githubusercontent.com/pawel1999f/Football-App/main/screen1.PNG)