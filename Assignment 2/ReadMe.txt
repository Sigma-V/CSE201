Steps to access and compile the file on Cmd Prompt : -
1. Run the following commands : -
    mvn clean
    mvn compile
    mvn package

2. The previous command should create a target folder
Inside the target folder, you will find a .jar file
In my computer, the file is named : A2_2022562-1.0-SNAPSHOT.jar
Copy the name of the file

3.In the terminal, run the command:
cd target

4.In the terminal, run the command:
java -jar .\name_of_the_.jarfile name_of_the_main_class

In my computer, the command was:
java -jar .\A2_2022562-1.0-SNAPSHOT.jar


About the code : -

The code implements a basic zoo system as instructed in the given assignment . Upon starting the execution , the user is created with the main home page to work according to hus/her will
The menu consists of : 1. Enter as Admin
                       2. Enter as Vistor
                       3. View Special Deals
                       4. Exit

About Admin : -
The admin starts with first login in menu . for this , I have fixed the username as 'admin' and password as 'admin123'
about assumptions :-
1. for all the attractions , I have used the an intger as KeyID and worked throughout the code for same .
2. for the animals , I have divided and created three different hashmaps as Mammals , Reptiles and Amphibians . Please note that all the animal related functionality is working on type which is using string comparsion so pelase fill the type of animal carefully
( I have also assumed the description and type of the animal to be same )
3. I have set special deals as fixed for two different deals : one for 2 more tickets and one for 3 tickets
4. In view stats, i wasnt able to idenifty and make a logic for most viewed attraction

Rest all the funcitonalities are working as per instructed

About Visitor : -
The admin starts with the option of login in as visitor or register as a new visitor. It is important to register first before login in
about assumptions :-
1. having two discounts as MINOR10 and SENIOR20 to be main discounts. Apart from this , any other discount is also valid
2. the assumptions for animals is same as the admin

Rest all the funcitonalities are working as per instructed.

For any further query or doubt , feel free to contact me for same. I would love to help and guide you through the execution of my code
