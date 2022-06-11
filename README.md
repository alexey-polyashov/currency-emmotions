# currency-emmotions

This service that shows the image according to the currency exchange rate statistics.
The base currency is USD. The exchange rate of the selected currency in relation to USD is used. Then yesterday's course is compared with today's

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
6. The application runs on port 8080. The URL of the application on localhost - http://localhost:8080/currency-emotions/
7. For stop service run the command "docker stop currency-emotions"

### Some details

The 'secret.properties' file contains the access keys. This file is placed in the repository only so that you can run the application for informational purposes.
!!!Please replace them with your own after reviewing!!!

The 'application.properties' file contains the setting 'options.base-currency'. If you need to use a different base currency, you must specify its code in this setting.
