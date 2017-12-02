package com.harrydong.cardme;

import android.graphics.Bitmap;

public class Contact {
    public String firstName;
    public String lastName;
    public String phone;
    public String address;
    public String company;
    public String position;
    public String facebook;
    public String linkedin;
    public String github;
    public String twitter;
    public Bitmap image;


    private Contact(ContactBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.phone;
        this.address = builder.address;
        this.company = builder.company;
        this.position = builder.position;
        this.facebook = builder.facebook;
        this.linkedin = builder.linkedin;
        this.github = builder.github;
        this.twitter = builder.twitter;
        this.image = builder.image;
    }

    public static class ContactBuilder {
        public String firstName = "";
        public String lastName= "";
        public String phone = "";
        public String address = "";
        public String facebook = "";
        public String linkedin= "";
        public String github = "";
        public String twitter = "";
        public String company = "";
        public String position = "";
        public Bitmap image;


        public ContactBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ContactBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactBuilder phone(String phone) {
            this.firstName = phone;
            return this;
        }

        public ContactBuilder address(String address) {
            this.address = address;
            return this;
        }

        public ContactBuilder facebook(String facebook) {
            this.facebook = facebook;
            return this;
        }

        public ContactBuilder linkedin(String linkedin) {
            this.linkedin = linkedin;
            return this;
        }

        public ContactBuilder github(String github) {
            this.github = github;
            return this;
        }

        public ContactBuilder twitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        public ContactBuilder company(String company) {
            this.company = company;
            return this;
        }

        public ContactBuilder position(String position) {
            this.position = position;
            return this;
        }

        public ContactBuilder image(Bitmap image) {
            this.image = image;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

    }
}

/*
     ArrayList<Contact> contacts = new ArrayList<Contact>();

    Contact contact = new Contact.Builder()
            .setName("n")
            .build();

        contacts.add(contact);
*/

/*


*/

/* Figuring out GSON
// Serialization
Gson gson = new Gson();
gson.toJson(personal);       // ==> [1]

// Deserialization
int one = gson.fromJson("1", int.class);
Integer one = gson.fromJson("1", Integer.class);
Long one = gson.fromJson("1", Long.class);
Boolean false = gson.fromJson("false", Boolean.class);
String str = gson.fromJson("\"abc\"", String.class);
String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
 */