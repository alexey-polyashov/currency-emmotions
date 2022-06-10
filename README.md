# currency-emmotions

This service that shows the image according to the currency exchange rate statistics
The base currency is USD

### Endpoints:
1. /currency-emotions/emotion/{currency} - (example: http://localhost:8080/currency-emotions/emotion/RUB)
   Shows an image
3. /currency-emotions/direction/{currency} - (example: http://localhost:8080/currency-emotions/direction/RUB)
  Displays the text message FALL or RISE
  
### How to run

1. Clone this repository
2. Open project in IDE
3. Run the command "./gradlew clean build"
4. Run the command "docker build -t currency-emotion ."
5. Run the command "docker run --name=currency-emotions -d -p 8080:8080 -t currency-emotion ."
6. For stop service run the command "docker stop currency-emotions"
