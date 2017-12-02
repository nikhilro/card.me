package com.harrydong.cardme;

import java.util.ArrayList;

public class Contact {
    private String mName;

    public Contact(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    private static int lastContactId = 0;

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        //for (int i = 0; i <= numContacts; i++) {
       //     contacts.add(new Contact("Person " + ++lastContactId, i <= numContacts / 2));
       // }
        contacts.add(new Contact("Petey Cruiser"));
        contacts.add(new Contact("Anna Sthesia"));
        contacts.add(new Contact("Paul Molive"));
        contacts.add(new Contact("Anna Mull"));
        contacts.add(new Contact("Gail Forcewind"));
        contacts.add(new Contact("Paige Turner"));
        contacts.add(new Contact("Bob Frapples"));
        contacts.add(new Contact("Walter Melon"));
        contacts.add(new Contact("Nick R. Bocker"));
        contacts.add(new Contact("Barb Ackue"));
        contacts.add(new Contact("Buck Kinnear"));
        contacts.add(new Contact("Greta Life"));

        return contacts;
    }
}