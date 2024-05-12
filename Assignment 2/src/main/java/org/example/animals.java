package org.example;

public interface animals {
    void Soundstatus();
    void Description_animals();
    void modifyAnimal();
    static void viewAnimals(){}
    static boolean checkAnimal_status(String name) {
        return false;
    }
    static void removeAnimal(String name) {
    };
}