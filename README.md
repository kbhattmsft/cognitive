# Microsoft Cognitive Services API with Spring Boot
Consume Microsoft Cognitive Services API from Spring Boot application
## Face API Demo
* Face API [1] example with age and emotion attributes: 
  - Git clone
  - <code>FaceAPIDemo/mvn clean install</code>
  - <code>java -jar target/FaceAPIDemo-0.0.1-SNAPSHOT.jar --subscription.key=\<key\> --sample.url=\<picture url\></code>
* UI mode: Just hit root deployment url (e.g. http://localhost:8080)
* API mode: 
  - Make sure environment variables <code>subscription.key</code> and <code>sample.url</code> are populated
  - Hit /detect endpoint (e.g.http://localhost:8080/detect)
* Replace the placeholders in application.properties as required
* Deploy to Pivotal Cloud Foundry (PCF)
  - Launch cf cli from e.g. Azure Cloud Shell
  - cf push (sample manifest provided)
* User provided services to bind Congnitive Services to an app running in PCF:
  - create UPSI: 
    - <code>cf cups cognitive -p '{"key":"--- API key goes here ---"}'</code>
  - bind the service to the app. Make sure to use <code>cognitive</code> as the service name for the binding to work:
    - <code>cf bind-service face-api cognitive</code>
  - restage the app
    - <code>cf restage face-api</code>
 
[1] https://docs.microsoft.com/en-us/azure/cognitive-services/face/overview
