## Environment 
* Java 8
* Tomcat 8.5


## Setup:

* clone this project.

* cd  to src/main/java/com/voice/common/AppConfig.java update python execute file path and audio file storage path.

* cd to webapp/app.js update following variable values (eg. change the localhost to right ip address and port number etc)
```
var saveFileUrl = "http://localhost:8080/voice/restws/voice/v1/save";
var getTextUrl = "http://localhost:8080/voice/restws/voice/v1/txt";
```

* run following command 
```
rm -rf target

mvn package
```
it will generate war file under voice/target

*  deploy the war file into your tomcat webapp folder

* Call following API

### upload a voice file to your server folder "voice.file.folder.path" which is defined in your app.properties file.

* Method: POST
* URL: http://ip:portnumber/restws/voice/v1/save
#### BODY 
* type: form-data
* key: file
* value: file inputstream eg: my.wav

the voice file my.wav will upload under your "voice.file.folder.path" with a unique file name. eg: uuid.wav
#### response: 

```
{
    "data": "118aa787-11ae-4325-b46a-a305991b84d5",
    "status": 200
}
```

### retrieve the text from voice file by using last API's response

* Method: GET
* URL: http://ip:portnumber/restws/voice/v1/txt?id=<voice file id>

server side will pass the "voice file id" (eg: 118aa787-11ae-4325-b46a-a305991b84d5) as an argument to your python execute file ("you have defiened in app.properties")
the text result will be return back as response data

Response:
```
{
    "data": {
        "content": "hello world",
        "id": "118aa787-11ae-4325-b46a-a305991b84d5"
    },
    "status": 200
}
```
