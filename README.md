# AndroidFileandCameraUpload







Creating a Android JAVA project with
PHP Integration
Colin Gell









 
Contents
1: Creating a new hosting environment .........................................................................Page 3
2: Website framework.....................................................................................................Page 8
3: SQL...............................................................................................................................Page 15
4: Android Build...............................................................................................................Page 18
5: Website Build...............................................................................................................Page 38


 
Creating a new hosting Environment
WHM (Web Host Manager) is a SaaS(Software as a solution) tool for administrative access to cPanel.  By using this tool we are able to focus on the code development and not the server infrastructure. 
 
Figure 1: The above image shows the dashboard view of the WHM tool.
By clicking on ‘Create a New Account’ we are able to generate a new cPanel environment for our project.
 
Figure 2: Domain information for the cPanel account
The above image shows the input of domain details where I have used a subdomain account from my personal website.  	I have recorded these details as I will require these for FTP access which means I can create a local working environment where I can develop the application.
Once the above details have been input I have scrolled down the page to find a ‘blue button ‘ called ‘Create’.  Once this has been clicked it will generate the below screen indicating the creation of the cPanel Account.
 
Figure 3: Success screen showing cPanel creation
The below screen shows the cPanel dashboard
 
Figure 4: cPanel dashboard
The cPanel dashboard environment allows the creation of mySQL databases.
 
Figure 5: cPanel database options
I have selected MySQL ® Databases to create a mySQL account.
This provides a dashboard with the below option to create a new database.
 
Figure 6: Create a new database
The below shows the creation of a basebase user
 
Figure 7: Creation of a database user

Once the database and the user have both been created we need to add the user to the database and define permissions.
 
Figure 8: Adding user to the account
 
Figure 9: Permissions for the database user of the database
The SQL table can now also be seen from within the phpMyAdmin dashboard to test that the database has been created.
 
Figure 10: phpMyAdmin folder tree
I will then access my file environment via WinSCP, which is a preferred FTP application of mine.  This application also support a range of other ‘File protocols’ for deploying to a range of environments, but in this case we will only be using ‘FTP’.
 
Figure 11: WINSCP desktop shortcut
 
Figure 12: Creating a new FTP connection with WinSCP
This then creates a working environment, where I can transfer files between a local folder and my online web development area.
 
Figure 13: WinFTP environment
Website framework
I have followed a normal process of creating a framework environment that I am comfortable development within.
 I firstly creating a folder called ‘Assets’
 
Figure 14: Creating a folder inside WinSCP
Inside this folder I’ve then created an additional 3 folders
 
Figure 15: Inside the Assets folder showing folders created within
Then within the folder ‘imgs’ I have uploaded the Synoptic project images to be used later
 
Figure 16: The dialogue box showing image file uploads
 
Figure 17: Successfully uploaded image files
With the ‘css’ folder I have created a new file called ‘style.css’, and I have uploaded a font script within this folder.  The font script has been created within this folder as the ‘style.css’ will use this file and will become dependent on this file.
 
Figure 18: Created files within 'css' folder
Within the style.css file I have populated this with commented out sections.  These allow me to structure my css to categories my css to avoid confusing css and make my project manageable by others.  The file also shows font import syntax which allows the inclusion of non-websafe fonts.  This defines a name to be used to call font with my css, and utilises the font file within the same directory folder shown above.  
Commented sections within the style.css file:
•	fonts imports
•	text hero colors 
•	borders colors 
•	backgrounds 
•	header css 
•	links 
•	fonts
•	body fonts 
•	headings 
•	nav links 
•	page margins 
•	action-buttons
•	hero-slider 
•	logo section
 
Figure 19: style.css
Libraries
Additionally I have uploaded libraries to the within the ‘Assets’ folder.  A popular and well support library is ‘bootstrap’.  I have used a local version of boostrap I have stored on my PC which and uploaded this into a folder with the version number in the file name:
 
Figure 20: bootstrap folder within 'Assets' folder
Similar to the above I have also uploaded jQuery into the ‘js’ folder from a local version on my PC with the version number included in the file name.
 
Figure 21: jQuery within the 'js' folder
I have then navigated to the ‘fontawesome.com’ website, and downloaded the latest version of font-awesome.
 
Figure 22: Link to download fontawesome
This has then been uploaded into the ‘Assets’ folder which now looks like this.
 
Figure 23: Assets folder
I have then created a config.php file which uses the ‘SQL user’ details defined earlier which I had recorded.
 
Figure 24: Syntax of config.php
This code uses OOP (Object Oriented Programming) to follow best principles in coding development.
The syntax creates 4 variables to be used for the sqli parameters and creates a connection which can be called later with the $conn variable.  These parameters are the ‘Server Name’, ‘SQL username’, and ‘Password’ required for the connection, and an additional parameter which refers to the database the data will be pulled from. 
The next conditional if statement called the sqli function ‘connect_error’ using the ‘->’ OOP operator which will also be used later in my code.  If this condition is positive this will close the application and display an error ‘Connection failed’
The file also defines constants to be used later in the code ‘ROOT_PATH’ and ‘BASE_URL’.  Constants are similar to variables in that they hold a value, but differ in that the value is fixed and cannot be changed.
header.php
I have then created a header.php file, which contains the <head> tags of the html page
 
Figure 25: header.php
This script adds the javascript files and stylesheets we included in the ‘Assets’ folder. 
 
Figure 26: footer.php
The above is a script to include a footer to be called from PHP ‘include.  This uses a PHP function called date();  which I’ve used to call the current date.
 
Figure 27: navbar.php
The above shows the inclusion of the BASE_URL constant included in the config.php file and uses concatenation to append a string value to make a link address within the href value.
These 4 files (config.php, header.php, navbar.php, footer.php) will be called from each page of the website which ensures a consistent design is carried through the site. 
SQL Database
I have then created several database tables
A users table
 
Figure 28: SQL to create 'users' table
This creates a ‘users’ table which defines the field names and the datatype such as integer or strings types such as varchar.  The syntax also shows the creation of a ‘Primary Key’ which is set to ‘auto increment’.
A posts table
 
Figure 29: SQL to create 'posts' table
Similar to the creation of the ‘users’ table this creates a ’posts table’.  I have set some of the fields to create an automatic ‘timestamp’ value.  This table also references the users table, making this dependent creation of a user.  This is an example of where close coupling has been created deliberately.
I have then created a ‘user’ within the ‘users’ table.
 
Figure 30: INSERT INTO SQL
This uses the INSERT INTO syntax to add data to the fields within the data structure I already created with the CREATE TABLE syntax
I have then created ‘posts’ to go into the ‘posts’ table.  
Due to the creation of the FOREIGN KEY this is created after the creation of the user.   
Figure 31: INSERT INTO 'posts' SQL
Android Images SQL table 
The below shows the creation of an additional table which will be used by an Android device to communicate with the LAMP environment.  In addition to the below create table I have also made use of ‘ALTER TABLE’ to ADD and extra column for category, which will be used to filter images in the website. 
 
Figure 32: Create images SQL table
 
Figure 33: Altering table of existing SQL table

 
Android Development
The below shows some initial screenshots taken from the Android Studio Emulator
 
Figure 34: An initial mock-up of the app generated from XML
 
Figure 35: Show the app usage of CAMERA
 
Figure 36: Uploading image
Screenshots of the eclipse IDE tool, this is an alternative to Android Studio which was used to aide testing syntax but was not used dominantly within the code.
 
Figure 37: eclipse an alternative IDE to Android Studio
 
Figure 38: Creating a JAVA project using Esclipse
 
Figure 39: Creating an eclipse project
 
Figure 40: Dashboard within eclipse project

Below is a screenshot of the IDE which was used dominantly within the development ‘Android Studio’
 
Figure 41: Latest Android Studio Version 3.6
 
Figure 42: Android Studio Dashboard
Below shows steps to create an Android Studio project. File->New->New Project
 
Figure 43: Creating a new project in Android Studio
 
Figure 44: Choosing a Project Template (Android Studio)
Following the above steps to creating a project the above screen will be shown.  In my project I have selected no activity so that I can better implement my project without any confusing syntax for my bespoke project.  However choosing an activity can make building a project easier, and in some cases it will build the majority of components you may need for your project.  
 
Figure 45: Defining the project
This shows options for the project such as the ‘App Name’, the package name and where the file will be stored.  I have in this case chosen Android 4.0 as the minimum Android version I am going to develop for and I have chosen JAVA as  a programming language as my experience tells me this is better supported.
Create MainActivity.java
Import the below dependencies which Android Studio uses as libraries for powerful functionalities.

 
Figure 46: Imports of android libraries
And 
 
Figure 47: Continued imports of android libraries
 
Figure 48: MainActivity.java
Figure 46 shows the creation of several variables used in the application and assigned them each a ‘type’.  The Strings are created to be used in the data submitted to the web application.
•	The ‘int’ integers have been generated for a switch statement allowing me to use the same class for both the CAMERA and ‘file storage’.
•	Button types have been created to work with listeners
•	A bitmap type has been created to handle the image
•	The Uri type has been created but is not well name and is used later in a switch statement to handle the value of the image from file storage. 
 
Figure 49: Additional variable to be used by the Spinner
NB the above was created after the screenshot of ‘Figure 46’ to handle the selection from the Spinner.
OnCreate method
 
Figure 50: onCreate Method
Figure 48 method assigns the activity_main’ xml sheet to this class, and makes calls to 2 methods within the class which ensure calls to other methods can be used.  Also variables created above are given values which work in conjunction with the values from id fields within the ‘activity_main’ xml file.  The code also assigns variables to listener events i.e the buttons within the application.
 
Figure 51: Action Bar Support
The above was added later to the code within ‘Figure 48’ to allow support to be added for customisation of the ‘Action Bar’ within the Android application.  This has been used for additional functionality within the application.  It maybe seen as bloated code as some lines are not used within the application, however these have been deliberately included as it provides options for later development.
 
Figure 52: Camera Intent, and File Storage Intent
Figure 50 shows 2 methods to call ‘intents’ for ‘File Storage’ and CAMERA.  Although this is an example of dependencies these are calls to applications which are common on most android devices and are essential to the functionality of the application.

 
Figure 53: OnActivity and switch statements
After an activity from either the CAMERA or ‘file storage’ events a switch statement is generated.  Figure 51 shows the case for ‘file storage’, which makes the ‘file upload’ button visible, and retrieves the data and then assigns this value to the ‘bitmap’ variable and set the display image to this value.
 
Figure 54: CAMERA case
Figure 52 calls the CAMERA case and similarly makes the ‘file upload’ button visible, but instead retrieves the data from the CAMERA application and handles the data in the same way assigning its’ value to the display image. 
 
Figure 55: BASE64 encoding of bitmap value
Figure 53 retrieves the value of the ‘bitmap’ variable and encodes the image to base64 so that the image can be stored in a longtext field within an SQL database.  
 
Figure 56: Part 1 upload image
 
Figure 57: Part 2 upload image
 
Figure 58: data.put for the category value
Figure 54 and figure 55 so the class used to upload the image to the web server.  Figure 56 was added to the above script to handle the value from the ‘category’ spinner.  This shows the generation of a ‘toast’ message to provide the end-user with information that the image is being loaded to the server.  The script will then call the image data and category field data and will send a post request to the URL defined earlier.  The url is: http://photography.trepidation.co.uk/Android/upload.php, where this will activate a post script and submit the data to the servers SQL database

 
Figure 59: Calls to methods on event listeners
Figure 57 shows onClick events for the listeners defined earlier in the script.  These call the methods also show in the earlier in the script.
 
Figure 60: Call to another java class
 
Figure 61: Final ‘Ascetic’ screenshot of the app
The above image shows a final ascetic screen shot of the app which includes the ‘Spinner’ category element, and the styled Action bar with logo attached.  This was taken from an emulated view on my mobile phone.
 
Figure 62: upload.php
Within the website I have also created the /Android/upload.php file which the android code makes a call to.  This takes the values of the request POST ‘$_POST[‘image’]’ and ‘$_POST[‘category’]’ and insert them into a SQL command which makes a call to the SQL database and insert the values.  This also generates a message which is used within the Android app TOAST message.
Manifest file
 
Figure 63: Manifest.xml
Within Android development it is mandatory to create a manifest file.  The first lines are used to define permissions to your device functions that this application will use.  I have kept this to just the functions which I require which as to access the devices camera, and have read and write access to the devices file storage.
Within the <application> I have defined the icon to be used on the app when the user wants to activate my app.
 
Figure 64: Application icon
I’ve declared the name of my app and instructions to call the MainActivate JAVA file and launch the app.
XML
 
Figure 65: XML screen of main activity
XML files are used within android studio to lay out the ascetic appearance of the app.  Within the above code I have laid out a LinearLayout which uses the parent window as the basis for design.  This allows application to look neat and tidy with a responsive methodology in mind.
The code sets out 2 buttons to run next to each other.
 
Figure 66: The 2 buttons
 
Figure 67: xml imageView, Spinner, Buttons
This shows the xml for the imageView using the App logo as a default image the Spinner area and pulls through the entries array.  It then also shows the button to upload the image and the button which will link to another JAVA class.
  
Figure 68: Folder tree
The above shows a folder tree for the files used in the above code.  This includes an additional folder I have not discussed for ‘xml values’ such as colours and styles which can be reused in other xml files.  Additional it shows several JAVA files which have been taken from the tutorial sources.
ViewImage.java and ‘activity_view_image.xml’ demonstrate calling an additional class and xml file from the mainActivity however more work is required on these.  The code for these scripts makes the basis for future development including connection to a getImage.php file to make an SQL connection to read the data submitted to the database.  This functionality is demonstrated in the WEBM video file within this folder (‘Task2).
 
Web Build
Index.php
 
Figure 69: call to includes files
Figure 69 shows the call to the include files that are used in index.php, contact.php, category.php, about.php, login.php
This makes use of require_once() function which is dependent on the files.  If the files do not exist the frontend page will be blank.
getPublishedPosts() pulls the function from functions.php to get the data in the ‘posts’ SQL database.  Each of the pages which also used the same script have the <title> element modified to reflect the content in that page.
 
Figure 70: bootstrap carousel
Figure 70 script takes advantage of bootstrap js libraries to create a javascript carousel which can be customised within the style.css file.  Bootstrap works by defining class names to elements which applies javascript and css in combination to create industry standard dynamic web features.
 
Figure 71: $images array from files in folder
Figure 70 further uses a foreach loop using a variables images from figure 71.  This loops through all the images within the Assets/imgs/ folder with the file extension .jpg.   This echoes an new carousel item with the file name echoed into the src attribute.
 
Figure 72: Carousel navigation
Navigation for the bootstrap carousel.
 
Figure 73: content element
Figure 73 shows a content element with <h2> tags and <p> tags to show page content.
 
Figure 74: include footer.php
 Similar to the require_once function this calls the footer.php script .  This is also called with the following pages index.php, contact.php, category.php, about.php, login.php.
Functions.php
 
Figure 75: functions.php from tutorial source
The above figure shows the functions.php file which copies the tutorial.  The file creates functions to call SQL queries from the main application.
 
Figure 76: functions.php modification
The above figure shows the custom creation of an additional function to call retrieve images from the ‘upload_image_file’ database where the images are stored from the Android app uploads.
 Catalogue.php
This represents the display of the key feature of the application where the images the user has uploaded to the website are visible, and are filterable.
  
Figure 77: filterable navbar
Figure 77 shows a navbar using bootstrap class values to filter the image display based on the values:
•	All
•	Mountains
•	Transport
•	People
•	Nature
•	Architecture
•	Mobile Uploads
This can be activated by the user by selecting the buttons above.
 
Figure 78: foreach of uploaded images
The above code shows a boostrap gallery using the images previously uploaded to ‘/Assets/imgs/’ folder.  Additionally to code will pull through the data using a SQL script within the functions.php page.  The data is shown in the src attribute in string format and is decoaded in BASE64 format from our SQL table structure.
Additional classes are used are used to style the images to display in the gallery from the boostrap library.
Blog.php
 
Figure 79: blog.php
 This page shows the syntax taken from the tutorial.  The $posts variable contains the ‘post’ data from the SQL table which is looped through using a foreach loop as we’ve seen earlier.  This again makes use of the BASE_URL constant.
Contact.php
 
Figure 80: contact form
The above is a simple contact form with an action to ‘mailer.php’
 
Figure 81: mailer.php
Mailer.php makes use of PHP function mail() and shows the attributes required to make the mailer work within my hosting environment.
In the above example the user will see a blank page with the message ‘Your Message has been sent’.
 
Figure 82: single_post.php
Figure 82 shows the syntax from the tutorial without any customisation
 
Figure 83: about.php
Figure 83 shows very basic syntax to display text.
 
Figure 84: login.php
Login.php makes use of the banner.php include file originally in the tutorial home page.  This has been move to a separate tab so that the end-user does not have to interact with this feature.
 
Figure 85: banner.php
This script provides login and logout features within the application
The admin area 
 
Figure 86: admin folder structure
The administration area has not been modified from the tutorial
