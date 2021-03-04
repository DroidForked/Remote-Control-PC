# Why I did this:
 1) The UI of the web interface to controll the espusb is too old
 2) I cannot do thing faster in that webui
 3) Can add anything you want easily by modifying the app in a good IDE faster,without modifieing or touching the esp8266's code.


# Modified to use with [/cnlohr/espusb](https://github.com/cnlohr/espusb)

 1) APK: [app-debug.apk](https://github.com/DroidForked/Remote-Control-PC/blob/master/RemoteControlPC-Studio/app/build/outputs/apk/debug/app-debug.apk)
 2) Python code: [main.py](https://github.com/DroidForked/Remote-Control-PC/blob/master/RemoteControlPC-Studio/espusb_python/main.py). if you want to use with python to control espusb

# How I did this:
  1) Extract the javascript files from the espusb server
  2) Make standalone javascript to control the espusb server
  3) Remove all function in js script,leaving only for keyboard and mouse
  4) Write same code in python
  5) As the original code use too much globale variable,I make a classy python script [main.py](https://github.com/DroidForked/Remote-Control-PC/blob/master/RemoteControlPC-Studio/espusb_python/main.py)
  6) Make same code for java by looking at the python code
  7) Search an open source android app for controlling PC, and found [Remote-Control-PC](https://github.com/varunon9/Remote-Control-PC)
  8) Add the java code in [me/varunon9/remotecontrolpc/espusb/](https://github.com/DroidForked/Remote-Control-PC/tree/master/RemoteControlPC-Studio/app/src/main/java/me/varunon9/remotecontrolpc/espusb)
  9) Modified the app so that it work with the newly added code
  10) Generated app in [app-debug.apk](https://github.com/DroidForked/Remote-Control-PC/blob/master/RemoteControlPC-Studio/app/build/outputs/apk/debug/app-debug.apk)
 


# Following are README.md file of [Remote-Control-PC](https://github.com/varunon9/Remote-Control-PC)

# Remote Control PC

Use your Android phone to control your Laptop.

### Features

1. Control Left Click, Right Click, Mouse Scroll
2. Type text 
3. Transfer files from phone to laptop
4. Download files from laptop to phone
5. Use your laptop as speaker to play mp3 files of phone
6. See images of phone on laptop
7. Control presentation on laptop via phone
8. Suspend, Restart or Shutdown your laptop using phone
9. Fetch your laptop screen to Android (only single click supported)
10. Browse Android files on Desktop (View and Download)

### Android app link
[![Android App](./screenshots/google-play.png)](https://play.google.com/store/apps/details?id=me.varunon9.remotecontrolpc)

### Desktop app link
https://github.com/varunon9/Remote-Control-PC/blob/master/RemoteControlPC-JavaFXML/store/RemoteControlPC.jar

## How does it work?

###### Step 1:
Start hotspot on phone and connect your laptop via wifi

###### Step 2:
Open the desktop app on your laptop (See instructions below)

###### Step 3:
Open Android app and enter connection details provided by desktop app to connect

###### Step 4:
Enjoy :sparkles: :boom: :wink:

### Dependencies
You need Java 8


### How to start desktop app?
1. Clone this Repository / Download zip 
2. Open terminal (Ctrl + Alt + T)
3. Type `cd /path/to/repository/RemoteControlPC-JavaFXML/dist/RemoteControlPC.jar`
4. Type `java -jar RemoteControlPC-Desktop.jar`
5. Lock icon to launcher
6. Make sure you have Java 8 installed (hit `java -version`) 

### How to start android app?
1. Install apk from /Remote-Control-PC/RemoteControlPC-Studio/app/build/outputs/apk/app-debug.apk
2. Connect your laptop with your phone via hotspot (or any local network)
3. Start Desktop app
4. Enter connection details shown on Desktop
5. Tested on Android 4.2.2 (Samsung Duos)

## Screenshots
|  |  |
| --- | --- |
|![Live Screen](./screenshots/live-screen.png) | ![Connection Screen](./screenshots/connect.png)|
|![Touchpad Screen](./screenshots/touchpad.png) | ![Navigation Screen](./screenshots/navigation-drawer.png)|
|![Typing Screen](./screenshots/keyboard.png) | ![Media Player Screen](./screenshots/media-player.png)|
|![Image Viewer Screen](./screenshots/image-viewer.png) | ![File Transfer Screen](./screenshots/file-transfer.png)|
|![Presentation Screen](./screenshots/presentation.png) | ![Power Off Screen](./screenshots/power-off.png)|
|![Downloading File Screen](./screenshots/file-download.png) | ![Music Control Screen](./screenshots/music-control.png)|
|![Desktop App](./screenshots/desktop.png) |![Desktop Image Viewer](./screenshots/desktop-image-viewer.png) |

## How to contribute?
Desktop part of this project has been developed in Netbeans(Java-FXML) while that of Android part in Android-Studio.

#### Steps to deploy app on your machine-
1. There are three folders-
    1. RemoteControlPC-Studio- Android part, Import in Android Studio 
    2. RemoteControlPC-JavaFXML- Desktop part, Import in Netbeans (Use SceneBuilder)
    3. RemoteControlPC-Libraries- Libraries (Jar file) that is being used in android as well as desktop, Import in Netbeans
2. Library has a dist folder that contains jar file.
3. Add this jar file in build path of Desktop (Netbeans) as well as Android part. See steps below.
4. To change/modify the java files inside library folder, import it in Netbeans and 'clean and build project' after changing.
5. Make sure to clean android as well as desktop part after modifying library files.

#### Steps to add jar file to android as well as dekstop part-
1. For Netbeans-
    1. Right click on project 
    2. Click Properties
    3. Click Libraries
    4. Add JAR/Folder -> Select jar from /path/to/repository/RemoteControlPC-Libraries/dist/RemoteControlPC-Libraries.jar

#### To see working demo on youtube watch this video:
* https://youtu.be/6jIQcAVqD24

#### Testing of android app:
* Android 4.2.2 Samsung(GT-S7582)
* Android 6.0.1 Redmi 4A
* Android 5, API 22 (Emulator)
* Android 7, API 24 (Emulator)

### Releases:
* https://github.com/varunon9/Remote-Control-PC/releases

#### For query/issues open Github issue or contact: varunon9@gmail.com
