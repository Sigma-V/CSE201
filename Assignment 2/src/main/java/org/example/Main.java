
package org.example;
import java.util.*;

class Feedback {
    private static ArrayList<String> feedbackList = new ArrayList<>();

    public static ArrayList<String> getFeedbackList() {
        return feedbackList;
    }

    public static void addFeedback(String feedback) {
        feedbackList.add(feedback);
    }

    public static void viewAllFeedback() {
        for (String feedback : feedbackList) {
            System.out.println(feedback);
        }
    }

}

class discounts {
    private String discount_code;
    private int discount_percantge;

    discounts(String discount_code , int discount_percantge){
        this.discount_code = discount_code;
        this.discount_percantge = discount_percantge;

    }

    public int getDiscount_percantge() {
        return discount_percantge;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }

    public void setDiscount_percantge(int discount_percantge) {
        this.discount_percantge = discount_percantge;
    }

}

class attractions{
    private String Attraction_name;
    private String Attraction_description;
    private int price_ticket;
    private boolean status;
    private int visitor_count;

    attractions(String attraction_name , String attraction_description){
        this.Attraction_name = attraction_name;
        this.Attraction_description = attraction_description;
        this.price_ticket = 0;
        this.status = false;
        this.visitor_count = 0;

    }

    public void setVisitor_count(int visitor_count) {
        this.visitor_count = visitor_count;
    }
    public String getAttraction_description() {
        return Attraction_description;
    }

    public String getAttraction_name() {
        return Attraction_name;
    }

    public void setAttraction_name(String attraction_name) {
        Attraction_name = attraction_name;
    }

    public void setAttraction_description(String attraction_description) {
        Attraction_description = attraction_description;
    }

    public int getVisitor_count() {
        return visitor_count;
    }
    public void add_visitor(){
        this.visitor_count++;
    }

    public boolean checkstatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice_ticket() {
        return price_ticket;
    }

    public void setPrice_ticket(int price_ticket) {
        this.price_ticket = price_ticket;
    }
}

class Admin{
    Scanner scanner = new Scanner(System.in);

    static int attraction_id = 1;
    static HashMap<Integer, attractions> attractionslist = new HashMap<Integer, attractions>();
    static HashMap<Integer, discounts> discountlist = new HashMap<>();

    public void add_attraction() {
        System.out.println("Enter Attraction ID:");
        int newAttractionId = scanner.nextInt();

        if (attractionslist.containsKey(newAttractionId)) {
            System.out.println("Attraction with ID " + newAttractionId + " already exists. Cannot add a new attraction with the same ID.");
        } else {
            System.out.println("Enter Attraction name:");
            String attr_name = scanner.next();
            System.out.println("Enter Attraction description:");
            String attr_desp = scanner.next();

            attractions attractions = new attractions(attr_name, attr_desp);
            attractionslist.put(newAttractionId, attractions);
            System.out.println("Attraction with ID " + newAttractionId + " added successfully.");
        }
    }

    public void view_attraction() {
        for (Map.Entry<Integer, attractions> entry : attractionslist.entrySet()) {
            int id = entry.getKey();
            attractions attr = entry.getValue();
            System.out.println("Attraction ID: " + id);
            System.out.println("Attraction Name: " + attr.getAttraction_name());
            System.out.println("Attraction Description: " + attr.getAttraction_description());
            System.out.println();
        }
    }

    public void remove_attraction() {
        System.out.println("Enter the ID of the attraction to remove:");
        int idToRemove = scanner.nextInt();

        if (attractionslist.containsKey(idToRemove)) {
            attractionslist.remove(idToRemove);
            System.out.println("Attraction with ID " + idToRemove + " removed successfully.");
        } else {
            System.out.println("Attraction with ID " + idToRemove + " not found.");
        }
    }

    public void modify_attraction() {
        System.out.println("Enter the ID of the attraction to modify:");
        int idToModify = scanner.nextInt();

        if (attractionslist.containsKey(idToModify)) {
            attractions attraction = attractionslist.get(idToModify);

            System.out.println("What do you want to modify? (name/description)");
            String modificationType = scanner.next().toLowerCase();

            if (modificationType.equals("name")) {
                System.out.println("Enter the new name:");
                String newName = scanner.next();
                attraction.setAttraction_name(newName);
                System.out.println("Attraction name modified successfully.");
            } else if (modificationType.equals("description")) {
                System.out.println("Enter the new description:");
                String newDescription = scanner.next();
                attraction.setAttraction_description(newDescription);
                System.out.println("Attraction description modified successfully.");
            } else {
                System.out.println("Invalid modification type.");
            }
        } else {
            System.out.println("Attraction with ID " + idToModify + " not found.");
        }
    }
    public void view_attraction_visitor() {
        for (Map.Entry<Integer, attractions> entry : attractionslist.entrySet()) {
            int id = entry.getKey();
            attractions attr = entry.getValue();
            System.out.println(id + " " + attr.getAttraction_name());
        }
    }

    public void schd_events() {
        System.out.println("Available Attractions: ");
        view_attraction_visitor();
        System.out.println("1. Schedule event");
        System.out.println("2. Close event");
        int choice1 = scanner.nextInt();
        scanner.nextLine();

        if (choice1 == 1) {
            System.out.println("Enter the ID of the attraction to be scheduled:");
            int attractionId = scanner.nextInt();
            scanner.nextLine();

            if (attractionslist.containsKey(attractionId)) {
                attractions attraction = attractionslist.get(attractionId);

                if (attraction.checkstatus()) {
                    System.out.println("Event for Attraction ID " + attractionId + " is already scheduled.");
                } else {
                    System.out.println("Enter the price for the event:");
                    int eventPrice = scanner.nextInt();
                    scanner.nextLine();

                    attraction.setStatus(true);
                    attraction.setPrice_ticket(eventPrice);

                    System.out.println("Event for Attraction ID " + attractionId + " has been scheduled.");
                }
            } else {
                System.out.println("Attraction with ID " + attractionId + " not found.");
            }
        } else if (choice1 == 2) {
            System.out.println("Enter the ID of the attraction to be closed:");
            int attractionId = scanner.nextInt();
            scanner.nextLine();

            if (attractionslist.containsKey(attractionId)) {
                attractions attraction = attractionslist.get(attractionId);

                if (attraction.checkstatus()) {
                    attraction.setStatus(false);
                    System.out.println("Event for Attraction ID " + attractionId + " has been closed.");
                } else {
                    System.out.println("Event for Attraction ID " + attractionId + " is already closed.");
                }
            } else {
                System.out.println("Attraction with ID " + attractionId + " not found.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    public void add_discount() {
        System.out.println("Enter Discount ID:");
        int discountId = scanner.nextInt();
        scanner.nextLine();

        if (discountlist.containsKey(discountId)) {
            System.out.println("Discount with ID " + discountId + " already exists. Cannot add a new discount with the same ID.");
        } else {
            System.out.println("Enter Discount name:");
            String discountName = scanner.nextLine();
            System.out.println("Enter Discount percentage:");
            int discountPercentage = scanner.nextInt();
            scanner.nextLine();
            boolean nameExists = discountlist.values().stream().anyMatch(discount -> discount.getDiscount_code().equals(discountName));

            if (nameExists) {
                System.out.println("Discount with name '" + discountName + "' already exists. Cannot add a new discount with the same name.");
            } else {
                discounts discount = new discounts(discountName, discountPercentage);
                discountlist.put(discountId, discount);
                System.out.println("Discount with ID " + discountId + " added successfully.");
            }
        }
    }

    public void modify_discount() {
        System.out.println("Available Discounts:");
        for (Map.Entry<Integer, discounts> entry : discountlist.entrySet()) {
            int id = entry.getKey();
            discounts discount = entry.getValue();
            System.out.println("ID: " + id + ", Name: " + discount.getDiscount_code() + ", Percentage: " + discount.getDiscount_percantge() + "%");
        }

        System.out.println("Enter the ID of the discount to modify:");
        int discountId = scanner.nextInt();
        scanner.nextLine();

        if (discountlist.containsKey(discountId)) {
            discounts discount = discountlist.get(discountId);

            System.out.println("What do you want to modify? (name/percentage)");
            String modificationType = scanner.nextLine().toLowerCase();

            if (modificationType.equals("name")) {
                System.out.println("Enter the new name:");
                String newName = scanner.nextLine();
                discount.setDiscount_code(newName);
                System.out.println("Discount name modified successfully.");
            } else if (modificationType.equals("percentage")) {
                System.out.println("Enter the new percentage:");
                int newPercentage = scanner.nextInt();
                scanner.nextLine();
                discount.setDiscount_percantge(newPercentage);
                System.out.println("Discount percentage modified successfully.");
            } else {
                System.out.println("Invalid modification type.");
            }
        } else {
            System.out.println("Discount with ID " + discountId + " not found.");
        }
    }

    public void remove_discount() {
        System.out.println("Available Discounts:");
        for (Map.Entry<Integer, discounts> entry : discountlist.entrySet()) {
            int id = entry.getKey();
            discounts discount = entry.getValue();
            System.out.println("ID: " + id + ", Name: " + discount.getDiscount_code() + ", Percentage: " + discount.getDiscount_percantge() + "%");
        }

        System.out.println("Enter the ID of the discount to remove:");
        int discountId = scanner.nextInt();
        scanner.nextLine();

        if (discountlist.containsKey(discountId)) {
            discountlist.remove(discountId);
            System.out.println("Discount with ID " + discountId + " removed successfully.");
        } else {
            System.out.println("Discount with ID " + discountId + " not found.");
        }
    }

    public void view_discounts() {
        System.out.println("Available Discounts:");
        for (Map.Entry<Integer, discounts> entry : discountlist.entrySet()) {
            int id = entry.getKey();
            discounts discount = entry.getValue();
            System.out.println("ID: " + id + ", Name: " + discount.getDiscount_code() + ", Percentage: " + discount.getDiscount_percantge() + "%");
        }
    }

    public void give_attraction_detail(int attractionId){
        if (attractionslist.containsKey(attractionId)) {
            attractions attr = attractionslist.get(attractionId);
            System.out.println(attr.getAttraction_description());
        } else {
            System.out.println("Attraction with ID " + attractionId + " not found.");
        }
    }
    public void viewFeedback() {
        Feedback.viewAllFeedback();
    }

    public static HashMap<Integer, attractions> getAttractionslist() {
        return attractionslist;
    }

    public static HashMap<Integer, discounts> getDiscountlist() {
        return discountlist;
    }

    public static int getAttraction_id() {
        return attraction_id;
    }

    public static void setAttraction_id(int attraction_id) {
        Admin.attraction_id = attraction_id;
    }

    public static void setAttractionslist(HashMap<Integer, attractions> attractionslist) {
        Admin.attractionslist = attractionslist;
    }

    public static void setDiscountlist(HashMap<Integer, discounts> discountlist) {
        Admin.discountlist = discountlist;
    }
}

class Amphibians implements animals {
    private static HashMap<Integer, Amphibians> amphibians = new HashMap<>();
    private int animalId;
    private String name;
    private String description;
    private String sound;

    private String type;


    public static boolean checkAnimalStatus(int animalId) {
        return amphibians.containsKey(animalId);
    }

    @Override
    public void modifyAnimal() {
        System.out.println("List of Amphibians:");
        viewAnimals();

        System.out.print("Enter the ID of the amphibian you want to modify: ");
        Scanner scanner = new Scanner(System.in);
        int animalIdToModify = scanner.nextInt();
        scanner.nextLine();

        if (amphibians.containsKey(animalIdToModify)) {
            Amphibians amphibianToModify = amphibians.get(animalIdToModify);

            System.out.println("1. Change name");
            System.out.println("2. Change sound");
            System.out.println("3. Change description");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                amphibianToModify.setName(newName);
            } else if (choice.equals("2")) {
                System.out.print("Enter new sound: ");
                String newSound = scanner.nextLine();
                amphibianToModify.setSound(newSound);
            } else if (choice.equals("3")) {
                System.out.print("Enter new description: ");
                String newDescription = scanner.nextLine();
                amphibianToModify.setDescription(newDescription);
            } else {
                System.out.println("Wrong choice");
            }
            System.out.println("Modified Successfully");
        } else {
            System.out.println("Amphibian with ID " + animalIdToModify + " does not exist.");
        }
    }

    public void removeAnimal(int animalId) {
        amphibians.remove(animalId);
        System.out.println("Animal removed successfully");
    }

    public void addAmphibian(int animalId, String name, String sound, String description) {

        this.animalId = animalId;
        this.name = name;
        this.description = description;
        this.sound = sound;

        Amphibians tempAmphibian = new Amphibians();
        tempAmphibian.animalId = animalId;
        tempAmphibian.name = name;
        tempAmphibian.description = description;
        tempAmphibian.sound = sound;

        amphibians.put(animalId, tempAmphibian);
    }

    public static void viewAnimals() {
        for (Map.Entry<Integer, Amphibians> entry : amphibians.entrySet()) {
            int id = entry.getKey();
            Amphibians amphibian = entry.getValue();
            System.out.println(id + ". " + amphibian.getName());
        }
    }
    public static void viewAnimals_visitor() {
        for (Map.Entry<Integer, Amphibians> entry : amphibians.entrySet()) {
            int id = entry.getKey();
            Amphibians amphibian= entry.getValue();
            System.out.println(id + ".Animal Name : " + amphibian.getName() + ", Animal Type : " + amphibian.getDescription() + ", Sound : " + amphibian.getSound());
        }
    }
    public static void feedAndMakeSounds(int animalId) {
        if (amphibians.containsKey(animalId)) {
            Amphibians amphibians1 = amphibians.get(animalId);
            System.out.println("Feeding " + amphibians1.getName() + "...");
            System.out.println(amphibians1.getName() + " makes sounds: " + amphibians1.getSound());
        } else {
            System.out.println("Amphibian with ID " + animalId + " does not exist.");
        }
    }

    public static void displayDescription(int animalId) {
        if (amphibians.containsKey(animalId)) {
            Amphibians amphibians1 = amphibians.get(animalId);
            System.out.println("Description/Type of " + amphibians1.getName() + " is: " + amphibians1.getDescription());
        } else {
            System.out.println("Amphibian with ID " + animalId + " does not exist.");
        }
    }

    @Override
    public void Soundstatus() {
        System.out.println(this.sound);
    }

    @Override
    public void Description_animals() {
        System.out.println(this.description);
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static HashMap<Integer, Amphibians> getAmphibians() {
        return amphibians;
    }

    public static void setAmphibians(HashMap<Integer, Amphibians> amphibians) {
        Amphibians.amphibians = amphibians;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }
}

class Mammals implements animals {
    private static HashMap<Integer, Mammals> mammals = new HashMap<>();
    private int animalId;
    private String name;
    private String description;
    private String sound;
    private String type;

    public static boolean checkAnimal(int animalId) {
        return mammals.containsKey(animalId);
    }

    @Override
    public void modifyAnimal() {
        System.out.println("List of Mammals:");
        viewAnimals();

        System.out.print("Enter the ID of the mammal you want to modify: ");
        Scanner scanner = new Scanner(System.in);
        int animalIdToModify = scanner.nextInt();
        scanner.nextLine();

        if (mammals.containsKey(animalIdToModify)) {
            Mammals mammalsToModify = mammals.get(animalIdToModify);

            System.out.println("1. Change name");
            System.out.println("2. Change sound");
            System.out.println("3. Change description");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                mammalsToModify.setName(newName);
            } else if (choice.equals("2")) {
                System.out.print("Enter new sound: ");
                String newSound = scanner.nextLine();
                mammalsToModify.setSound(newSound);
            } else if (choice.equals("3")) {
                System.out.print("Enter new description: ");
                String newDescription = scanner.nextLine();
                mammalsToModify.setDescription(newDescription);
            } else {
                System.out.println("Wrong choice");
            }
            System.out.println("Modified Successfully");
        } else {
            System.out.println("Amphibian with ID " + animalIdToModify + " does not exist.");
        }
    }


    public void removeAnimal(int animalId) {
        mammals.remove(animalId);
        System.out.println("Animal removed successfully");
    }

    public void addMammal(int animalId, String name, String sound, String description) {
        this.animalId = animalId;
        this.name = name;
        this.description = description;
        this.sound = sound;

        Mammals tempMammal = new Mammals();
        tempMammal.animalId = animalId;
        tempMammal.name = name;
        tempMammal.description = description;
        tempMammal.sound = sound;
        mammals.put(animalId, tempMammal);
    }

    public static void viewAnimals() {
        for (Map.Entry<Integer, Mammals> entry : mammals.entrySet()) {
            int id = entry.getKey();
            Mammals mammal = entry.getValue();
            System.out.println(id + ". " + mammal.getName());
        }
    }
    public static void viewAnimals_visitor() {
        for (Map.Entry<Integer, Mammals> entry : mammals.entrySet()) {
            int id = entry.getKey();
            Mammals mammal= entry.getValue();
            System.out.println(id + ".Animal Name : " + mammal.getName() + ", Animal Type : " + mammal.getDescription()
                    + ", Sound : " + mammal.getSound());
        }
    }
    public static void feedAndMakeSounds(int animalId) {
        if (mammals.containsKey(animalId)) {
            Mammals mammals1 = mammals.get(animalId);
            System.out.println("Feeding " + mammals1.getName() + "...");
            System.out.println(mammals1.getName() + " makes sounds: " + mammals1.getSound());
        } else {
            System.out.println("Mammal with ID " + animalId + " does not exist.");
        }
    }

    public static void displayDescription(int animalId) {
        if (mammals.containsKey(animalId)) {
            Mammals mammals1 = mammals.get(animalId);
            System.out.println("Description/Type of " + mammals1.getName() + " is: " + mammals1.getDescription());
        } else {
            System.out.println("Mammal with ID " + animalId + " does not exist.");
        }
    }

    @Override
    public void Soundstatus() {
        System.out.println(this.sound);
    }

    @Override
    public void Description_animals() {
        System.out.println(this.description);
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static HashMap<Integer, Mammals> getMammals() {
        return mammals;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public static void setMammals(HashMap<Integer, Mammals> mammals) {
        Mammals.mammals = mammals;
    }
}

class Reptiles implements animals {
    private static HashMap<Integer, Reptiles> reptiles = new HashMap<>();
    private int animalId;
    private String name;
    private String description;
    private String sound;
    private String type;


    public static boolean checkAnimal(int animalId) {
        return reptiles.containsKey(animalId);
    }

    @Override
    public void modifyAnimal() {
        System.out.println("List of Reptiles:");
        viewAnimals();

        System.out.print("Enter the ID of the Reptiles you want to modify: ");
        Scanner scanner = new Scanner(System.in);
        int animalIdToModify = scanner.nextInt();
        scanner.nextLine();

        if (reptiles.containsKey(animalIdToModify)) {
            Reptiles reptileToModify = reptiles.get(animalIdToModify);

            System.out.println("1. Change name");
            System.out.println("2. Change sound");
            System.out.println("3. Change description");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                reptileToModify.setName(newName);
            } else if (choice.equals("2")) {
                System.out.print("Enter new sound: ");
                String newSound = scanner.nextLine();
                reptileToModify.setSound(newSound);
            } else if (choice.equals("3")) {
                System.out.print("Enter new description: ");
                String newDescription = scanner.nextLine();
                reptileToModify.setDescription(newDescription);
            } else {
                System.out.println("Wrong choice");
            }
            System.out.println("Modified Successfully");
        } else {
            System.out.println("Amphibian with ID " + animalIdToModify + " does not exist.");
        }
    }

    public void removeAnimal(int animalId) {
        reptiles.remove(animalId);
        System.out.println("Animal removed successfully");
    }

    public void addReptile(int animalId, String name, String sound, String description) {
        this.animalId = animalId;
        this.name = name;
        this.description = description;
        this.sound = sound;

        Reptiles tempReptile = new Reptiles();
        tempReptile.animalId = animalId;
        tempReptile.name = name;
        tempReptile.description = description;
        tempReptile.sound = sound;
        reptiles.put(animalId, tempReptile);
    }

    public static void viewAnimals() {
        for (Map.Entry<Integer, Reptiles> entry : reptiles.entrySet()) {
            int id = entry.getKey();
            Reptiles reptile = entry.getValue();
            System.out.println(id + ". " + reptile.getName());
        }
    }
    public static void viewAnimals_visitor() {
        for (Map.Entry<Integer, Reptiles> entry : reptiles.entrySet()) {
            int id = entry.getKey();
            Reptiles reptile = entry.getValue();
            System.out.println(id + ".Animal Name : " + reptile.getName() + ", Animal Type : " + reptile.getDescription() + ", Sound : " + reptile.getSound());
        }
    }
    public static void  feedAndMakeSounds(int animalId) {
        if (reptiles.containsKey(animalId)) {
            Reptiles reptile = reptiles.get(animalId);
            System.out.println("Feeding " + reptile.getName() + "...");
            System.out.println(reptile.getName() + " makes sounds: " + reptile.getSound());
        } else {
            System.out.println("Reptile with ID " + animalId + " does not exist.");
        }
    }

    public static void displayDescription(int animalId) {
        if (reptiles.containsKey(animalId)) {
            Reptiles reptile = reptiles.get(animalId);
            System.out.println("Description/Type of " + reptile.getName() + " is: " + reptile.getDescription());
        } else {
            System.out.println("Reptile with ID " + animalId + " does not exist.");
        }
    }



    @Override
    public void Soundstatus() {
        System.out.println(this.sound);
    }

    @Override
    public void Description_animals() {
        System.out.println(this.description);
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static HashMap<Integer, Reptiles> getReptiles() {
        return reptiles;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public static void setReptiles(HashMap<Integer, Reptiles> reptiles) {
        Reptiles.reptiles = reptiles;
    }
}

class visitor {
    private static HashMap<Integer, visitor> visitors = new HashMap<>();
    private ArrayList<Integer> ticketStatus = new ArrayList<>();
    private String name;
    private int age;
    private String Phone_no;
    private int balance;
    private String email;
    private String password;
    static Integer visitorID = 1;

    private String membership = "";

    private static Integer visitorcount = 0;
    private static Integer totalrev = 0;


    public void addvisitor(String name, int age, String Phone_no, int balance, String email, String password) {
        this.name = name;
        this.age = age;
        this.Phone_no = Phone_no;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.membership = "";

        visitor newVisitor = new visitor();
        newVisitor.name = name;
        newVisitor.age = age;
        newVisitor.Phone_no = Phone_no;
        newVisitor.balance = balance;
        newVisitor.email = email;
        newVisitor.password = password;
        newVisitor.membership = "";

        visitors.put(visitorID, newVisitor);
        visitorID++;
    }
    public static visitor check(String emailid, String password) {
        Optional<visitor> visitorOptional = visitors.values().stream()
                .filter(visitor -> emailid.equals(visitor.email) && password.equals(visitor.password))
                .findFirst();
        return visitorOptional.orElse(null);
    }

    public void viewAllVisitors() {
        for (Map.Entry<Integer, visitor> entry : visitors.entrySet()) {
            int id = entry.getKey();
            visitor visitor = entry.getValue();
            System.out.println("Visitor ID: " + id);
            System.out.println("Name: " + visitor.name);
            System.out.println("Age: " + visitor.age);
            System.out.println("Phone Number: " + visitor.Phone_no);
            System.out.println("Balance: " + visitor.balance);
            System.out.println("Membership: " + visitor.membership);
            System.out.println("------------------------------");
        }
    }
    public void setMembership(int value, String code_name) {
        if (value == 1) {
            if (balance >= 20) {
                membership = "Basic";
                if(age <18 && Objects.equals(code_name, "MINOR10")){
                    balance -=18;
                    totalrev +=18;
                }else{
                    balance -= 20;
                    totalrev +=20;
                }
                visitorcount+=1;
                System.out.println("Membership status set to Basic."+balance+" left in your balance.");
            } else {
                System.out.println("Insufficient balance to purchase Basic membership.");
            }
        } else if (value == 2) {
            if (balance >= 50) {
                membership = "Premium";
                if(age >60 && Objects.equals(code_name, "SENIOR20")){
                    balance -=40;
                    totalrev+=40;
                }else{
                    balance -= 50;
                    totalrev+=50;
                }
                visitorcount+=1;
                System.out.println("Membership status set to Premium."+balance+" left in your balance.");
            } else {
                System.out.println("Insufficient balance to purchase Premium membership.");
            }
        }else {
            System.out.println("Invalid value for membership choice.");
        }
    }

    public void getTickets(HashMap<Integer, attractions> attractionslist, Admin admin) {
        Scanner scanner = new Scanner(System.in);

        if (membership.isEmpty()) {
            System.out.println("You need to buy a membership first.");
            return;
        }

        if (membership.equals("Premium")) {
            System.out.println("Your membership is Premium. You don't need to buy tickets.");
            return;
        }

        if (membership.equals("Basic")) {
            System.out.println("Available Attractions:");
            admin.view_attraction_visitor();

            System.out.println("Enter the ID of the attraction you want to buy a ticket for:");
            int attractionId = scanner.nextInt();
            scanner.nextLine();

            if (attractionslist.containsKey(attractionId)) {
                attractions attraction = attractionslist.get(attractionId);

                if (attraction.checkstatus()) {
                    System.out.println("Do you have a discount code? (yes/no)");
                    String hasDiscountCode = scanner.nextLine().toLowerCase();

                    int ticketPrice = attraction.getPrice_ticket();

                    if (hasDiscountCode.equals("yes")) {
                        System.out.println("Enter your discount code:");
                        String discountCode = scanner.nextLine();

                        discounts discount = admin.discountlist.values().stream()
                                .filter(d -> d.getDiscount_code().equals(discountCode))
                                .findFirst()
                                .orElse(null);

                        if (discount != null) {
                            int discountPercentage = discount.getDiscount_percantge();
                            ticketPrice = (int) (ticketPrice - (ticketPrice * (discountPercentage / 100.0)));
                        } else {
                            System.out.println("Invalid discount code. You will be charged the regular ticket price.");
                        }
                    }

                    if (ticketStatus.size() == 1) {
                        ticketPrice = (int) (ticketPrice - (ticketPrice * 0.15));
                    } else if (ticketStatus.size() == 2) {
                        ticketPrice = (int) (ticketPrice - (ticketPrice * 0.30));
                    }

                    totalrev+=ticketPrice;

                    if (balance >= ticketPrice) {
                        balance -= ticketPrice;
                        ticketStatus.add(attractionId);
                        System.out.println("Ticket for Attraction ID " + attractionId + " purchased successfully.");
                    } else {
                        System.out.println("Insufficient balance to purchase the ticket.");
                    }
                } else {
                    System.out.println("Attraction with ID " + attractionId + " is currently closed.");
                }
            } else {
                System.out.println("Attraction with ID " + attractionId + " not found.");
            }
        }
    }
    public void visitAttraction(HashMap<Integer, attractions> attractionslist) {
        Scanner scanner = new Scanner(System.in);

        if (ticketStatus.isEmpty()) {
            System.out.println("You haven't bought any tickets to visit attractions.");
            return;
        }

        System.out.println("Available Attractions to Visit:");
        for (int attractionId : ticketStatus) {
            attractions attraction = attractionslist.get(attractionId);
            System.out.println("Attraction ID: " + attractionId + " - " + attraction.getAttraction_name());
        }

        System.out.println("Enter the ID of the attraction you want to visit:");
        int attractionId = scanner.nextInt();
        scanner.nextLine();

        if (ticketStatus.contains(attractionId)) {
            attractions attraction = attractionslist.get(attractionId);
            System.out.println("Visiting " + attraction.getAttraction_name() + "...");
            // You can add more logic or actions related to the visit here if needed.
            ticketStatus.remove(Integer.valueOf(attractionId)); // Remove the attraction ID from the list
            System.out.println("Visit was successful.");
        } else {
            System.out.println("You haven't bought a ticket for the selected attraction.");
        }
    }
    public void visit_visitstats(){
        System.out.println("Total Visitors: "+ visitorcount);
        System.out.println("Total Revenue: " +totalrev);
    }
    public void addFeedback(String feedback) {
        Feedback.addFeedback(feedback);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Integer> getTicketStatus() {
        return ticketStatus;
    }

    public static HashMap<Integer, visitor> getVisitors() {
        return visitors;
    }

    public int getBalance() {
        return balance;
    }

    public static Integer getTotalrev() {
        return totalrev;
    }

    public static Integer getVisitorcount() {
        return visitorcount;
    }

    public static Integer getVisitorID() {
        return visitorID;
    }

    public String getEmail() {
        return email;
    }

    public String getMembership() {
        return membership;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public void setTicketStatus(ArrayList<Integer> ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public static void setTotalrev(Integer totalrev) {
        visitor.totalrev = totalrev;
    }

    public static void setVisitorcount(Integer visitorcount) {
        visitor.visitorcount = visitorcount;
    }

    public static void setVisitorID(Integer visitorID) {
        visitor.visitorID = visitorID;
    }

    public static void setVisitors(HashMap<Integer, visitor> visitors) {
        visitor.visitors = visitors;
    }
}

public class Main {
    static Admin admin1 = new Admin();
    static Reptiles reptiles = new Reptiles();
    static Mammals mammals = new Mammals();
    static Amphibians amphibians = new Amphibians();

    static void admin(){
        Scanner scanner = new Scanner(System.in);
        visitor newvisitor1 = new visitor();

        while(true){
            System.out.println("-------------------------");
            System.out.println("Logged in as Admin");
            System.out.println("-------------------------");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deals");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Break");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 8){
                break;
            }else if (choice == 7){
                Feedback.viewAllFeedback();
            }
            else if(choice == 1){
                while(true){
                    System.out.println("1. Add Attractions");
                    System.out.println("2. View Attractions");
                    System.out.println("3. Manage Attractions");
                    System.out.println("4. Remove Attractions");
                    System.out.println("5. Back");

                    int val = scanner.nextInt();
                    scanner.nextLine();

                    if(val == 5){
                        break;
                    } else if (val == 1) {
                        admin1.add_attraction();
                    }else if (val == 2) {
                        admin1.view_attraction();
                    }else if (val == 3) {
                        admin1.modify_attraction();
                    }else if (val == 4) {
                        admin1.remove_attraction();
                    }

                }
            }
            else if(choice == 3){
                admin1.schd_events();
            }
            else if(choice == 4){
                while(true){
                    System.out.println("1. Add Discount");
                    System.out.println("2. Modify Discount");
                    System.out.println("3. Remove Discount");
                    System.out.println("4. View Available Discounts");
                    System.out.println("5. Break");

                    int val = scanner.nextInt();
                    scanner.nextLine();

                    if(val == 5){
                        break;

                    } else if (val == 1) {
                        admin1.add_discount();
                    }else if (val == 2) {
                        admin1.modify_discount();
                    }else if (val == 3) {
                        admin1.remove_discount();
                    }else if(val == 4){
                        admin1.view_discounts();
                    }
                }
            } else if ( choice == 2){
                while(true){
                    System.out.println("1. Add Animal");
                    System.out.println("2. Update Animal Details");
                    System.out.println("3. Remove Animals");
                    System.out.println("4. Exit");

                    int val = scanner.nextInt();
                    scanner.nextLine();

                    if(val == 4){
                        break;
                    }
                    else if (val == 1) {
                        System.out.println("Enter Animal ID: ");
                        int animalID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Animal Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter animal Sound: ");
                        String sound = scanner.nextLine();
                        System.out.println("Enter animal Description: ");
                        String Description = scanner.nextLine();

                        if (Objects.equals(Description, "Mammal")){
                            mammals.addMammal(animalID,name,sound,Description);
                        }else if (Objects.equals(Description, "Amphibians")){
                            amphibians.addAmphibian(animalID,name,sound,Description);
                        }else if (Objects.equals(Description, "Reptiles")){
                            reptiles.addReptile(animalID,name,sound,Description);
                        }
                    }else if (val == 2) {
                        while(true){
                            System.out.println("Enter type of Animal you wanna modify: ");
                            String Description = scanner.nextLine();
                            if (Objects.equals(Description, "Mammal")){
                                mammals.modifyAnimal();
                            }else if (Objects.equals(Description, "Amphibians")){
                                amphibians.modifyAnimal();
                            }else if (Objects.equals(Description, "Reptiles")){
                                reptiles.modifyAnimal();
                            }
                            System.out.println("Do you want to modify more animals(Y/N) : ");
                            String ans = scanner.nextLine();
                            if(Objects.equals(ans, "N")){
                                break;
                            }else{
                                continue;
                            }
                        }
                    }else if (val == 3) {
                        while(true){
                            System.out.println("Enter type of Animal you wanna remove: ");
                            String description = scanner.nextLine();
                            if (Objects.equals(description, "Mammal")){
                                Mammals.viewAnimals();
                                int chtemp = scanner.nextInt();
                                scanner.nextLine();
                                mammals.removeAnimal(chtemp);

                            }else if (Objects.equals(description, "Amphibians")){
                                Amphibians.viewAnimals();
                                int chtemp = scanner.nextInt();
                                scanner.nextLine();
                                amphibians.removeAnimal(chtemp);
                            }else if (Objects.equals(description, "Reptiles")){
                                Reptiles.viewAnimals();
                                int chtemp = scanner.nextInt();
                                scanner.nextLine();
                                reptiles.removeAnimal(chtemp);
                            }
                            System.out.println("Do you want to remove more animals(Y/N) : ");
                            String ans = scanner.nextLine();
                            if(Objects.equals(ans, "N")){
                                break;
                            }else{
                                continue;
                            }
                        }
                    }
                }
            }else if(choice == 6){
                newvisitor1.visit_visitstats();
            }

        }

    }

    static void visitor() {
        Scanner scanner = new Scanner(System.in);
        Admin admin2 = new Admin();
        visitor newvisitor1 = new visitor();
        while(true){
            System.out.println("-------------------------");
            System.out.println("Welcome Mr/Mrs. Member");
            System.out.println("-------------------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Return to Main Menu");

            int val = scanner.nextInt();
            scanner.nextLine();

            if ( val == 1){
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Age:");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Phone No:");
                String phone = scanner.nextLine();
                System.out.println("Enter Balance:");
                int balance = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Email ID:");
                String emailid = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                newvisitor1.addvisitor(name,age,phone,balance,emailid,password);
                System.out.println("The visitor has been successfully registered");

            }
            else if(val == 2){
                String name = "";
                String phone = "";
                visitor visitorval1 = null;
                while (true) {
                    if (name.isEmpty() || phone.isEmpty()) {
                        System.out.println("Enter emailid:");
                        try {
                            name = scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                        System.out.println("Enter password.:");
                        try {
                            phone = scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input datatype entered. Enter a valid string");
                            scanner.nextLine();
                        }
                    }
                    if (visitorval1 == null) {
                        visitorval1 = visitor.check(name, phone);
                    }
                    if (visitorval1 == null) {
                        System.out.println("No such person found in the directory. Please enter valid credentials");
                        name = "";
                        phone = "";
                        continue;
                    }
                    System.out.println(" ");

                    System.out.println("1. Explore the zoo");
                    System.out.println("2. Buy Membership");
                    System.out.println("3. Buy Tickets");
                    System.out.println("4. View Discounts");
                    System.out.println("5. View Special Deals");
                    System.out.println("6. Visit Animals");
                    System.out.println("7. Visit Attractions");
                    System.out.println("8. Leave Feedback");
                    System.out.println("9. Log Out");

                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 9) {
                        break;
                    } else if (choice == 8) {
                        System.out.println("Enter your review");
                        String review = scanner.nextLine();
                        Feedback.addFeedback(review);

                    } else if (choice == 1){
                        while(true){
                            System.out.println("1. View Attractions");
                            System.out.println("2. View Animals");
                            System.out.println("3. Exit");

                            int ch1 = scanner.nextInt();
                            scanner.nextLine();

                            if(ch1 == 3){
                                break;
                            }else if(ch1 == 1){
                                admin1.view_attraction_visitor();
                                System.out.println("Enter the ID of attraction you want to view: ");
                                int ch4 = scanner.nextInt();
                                scanner.nextLine();
                                admin1.give_attraction_detail(ch4);

                            }else if (ch1 == 2){
                                System.out.println("Enter type of Animal you wanna View: ");
                                String description = scanner.nextLine();
                                if (Objects.equals(description, "Mammal")){
                                    Mammals.viewAnimals_visitor();

                                }else if (Objects.equals(description, "Amphibians")){
                                    Amphibians.viewAnimals_visitor();
                                }else if (Objects.equals(description, "Reptiles")){
                                    Reptiles.viewAnimals_visitor();
                                }

                            }
                        }
                    } else if ( choice == 4){
                        System.out.println("Std Discounts available: ");
                        System.out.println("1.Minor (10% discount) - MINOR10\n" +
                                "2. Senior Citizen (20% discount) - SENIOR20\n");
                        System.out.println("Other Discounts available:  ");
                        admin1.view_discounts();
                    } else if(choice == 5){
                        System.out.println("Special Deals available: ");
                        System.out.println("1. Buy 2 tickets and get 15% off");
                        System.out.println("2. Buy 3 tickets and get 30% off");
                    } else if (choice == 2){
                        System.out.println("Buy Membership");
                        System.out.println("1. Basic Membership (₹20)");
                        System.out.println("2. Premium Membership (₹50)");
                        System.out.println("Enter your choice ");
                        int ch5 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Apply Discount Code");
                        String code = scanner.nextLine();
                        visitorval1.setMembership(ch5,code);
                    } else if (choice == 6){
                        System.out.println("Enter type of Animal you wanna Visit: ");
                        String description = scanner.nextLine();
                        if (Objects.equals(description, "Mammal")){
                            Mammals.viewAnimals();
                        }else if (Objects.equals(description, "Amphibians")){
                            Amphibians.viewAnimals();
                        }else if (Objects.equals(description, "Reptiles")){
                            Reptiles.viewAnimals();
                        }
                        System.out.println("Enter the Animal id of animal you want to interact with ");
                        int ch6 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("What do you wanna do : ");
                        System.out.println("1. Feed the animal");
                        System.out.println("2. Show Description");
                        int ch5 = scanner.nextInt();
                        scanner.nextLine();


                        if(ch5 == 1){
                            if (Objects.equals(description, "Mammal")){
                                Mammals.feedAndMakeSounds(ch6);
                            }else if (Objects.equals(description, "Amphibians")){
                                Amphibians.feedAndMakeSounds(ch6);
                            }else if (Objects.equals(description, "Reptiles")){
                                Reptiles.feedAndMakeSounds(ch6);
                            }
                        }
                        else if(ch5 == 2){
                            if (Objects.equals(description, "Mammal")){
                                Mammals.displayDescription(ch6);
                            }else if (Objects.equals(description, "Amphibians")){
                                Amphibians.displayDescription(ch6);
                            }else if (Objects.equals(description, "Reptiles")){
                                Reptiles.displayDescription(ch6);
                            }
                        }


                    }else if (choice == 7){
                        visitorval1.visitAttraction(admin1.attractionslist);
                    }else if (choice == 3) {
                        visitorval1.getTickets(admin1.attractionslist, admin1);
                    }
                }
            }else if(val == 3){
                break;
            }

        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("Welcome to ZOOtopia !!");
        System.out.println("-------------------------");
        System.out.println("1. Enter as Admin");
        System.out.println("2. Enter as Visitor");
        System.out.println("3. View Special Deals");
        System.out.println("4. Exit");
        System.out.println("-------------------------");
        while(true){
            try{
                int name = scanner.nextInt();
                scanner.nextLine();
                if (name == 1){
                    while(true){
                        System.out.println("Enter Admin Username:");
                        String adminname =scanner.nextLine();
                        System.out.println("Enter Admin Password:");
                        String adminpass =scanner.nextLine();
                        if (Objects.equals(adminname, "admin") && Objects.equals(adminpass, "admin123")){
                            admin();
                            System.out.println("-------------------------");
                            System.out.println("Welcome to ZOOtopia !!");
                            System.out.println("-------------------------");
                            System.out.println("1. Enter as Admin");
                            System.out.println("2. Enter as Visitor");
                            System.out.println("3. View Special Deals");
                            System.out.println("4. Exit");
                            System.out.println("-------------------------");
                            break;
                        }
                        else{
                            System.out.println("Wrong Admin Credentials. Please try again");
                        }
                    }
                } else if(name == 2){
                    visitor();
                    System.out.println("-------------------------");
                    System.out.println("Welcome to ZOOtopia !!");
                    System.out.println("-------------------------");
                    System.out.println("1. Enter as Admin");
                    System.out.println("2. Enter as Visitor");
                    System.out.println("3. View Special Deals");
                    System.out.println("4. Exit");
                    System.out.println("-------------------------");
                } else if(name == 3){
                    System.out.println("Special Deals available: ");
                    System.out.println("1. Buy 2 tickets and get 15% off");
                    System.out.println("2. Buy 3 tickets and get 30% off");

                } else if (name == 4){
                    System.out.println("Thank you for visiting Zootopia !! Hope to see you again !!");
                    break;
                }
                else{
                    System.out.println("Please enter a valid option from the given choices");
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input datatype entered. Enter a valid integer");
                scanner.nextLine();
            }
        }
    }
}