package com.example.admin.daily1;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.admin.daily1.action.FOO";
    public static final String ACTION_BAZ = "com.example.admin.daily1.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.admin.daily1.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.admin.daily1.extra.PARAM2";


    public static final String KEY="key";
    public static final String TAG= MyIntentService.class.getSimpleName();

    private ArrayList<Parcelable> objectList;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }

        objectList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <10 ; i++) {


            switch (random.nextInt(3)) {
                case 0:
                    Log.d(TAG, "onHandleIntent: Creating a person");
                    objectList.add(new Person("Arnold Schwarzenegger","70","Male","raw/arnold_schwarzenegger_september_2017.jpg"));
                    break;
                case 1:
                    Log.d(TAG, "onHandleIntent: Creating a book");
                    objectList.add(new Book("0-06-202402-7","Divergent","Veronica Roth","raw/divergent_(book)_by_veronica_roth_us_hardcover_2011.jpg"));
                    break;
                case 2:
                    Log.d(TAG, "onHandleIntent: Creating a car");
                    objectList.add(new Car("Toyota","Corolla","White","raw/280px-2017_toyota_corolla_ascent_sedan_(2017-11-18)_01.jpg"));
                    break;
                case 3:
                    Log.d(TAG, "onHandleIntent: Creating a cat");
                    objectList.add(new Cat("Aegean","Ethiopia","Natural","raw/220px-aegean_cat.jpg"));
                    break;
            }
        }

        Intent broadcastIntent=new Intent(MainActivity.Action.POPULATE_RECYCLER_VIEW);
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(KEY,objectList);
        broadcastIntent.putExtra(KEY, bundle);
        sendBroadcast(broadcastIntent);

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static class Person implements Parcelable {
        protected Person(Parcel in) {
            name = in.readString();
            age = in.readString();
            gender = in.readString();
            image = in.readString();
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Person(String name, String age, String gender, String image) {

            this.name = name;
            this.age = age;
            this.gender = gender;
            this.image = image;
        }

        private String name;
        private String age;
        private String gender;
        private String image;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(age);
            parcel.writeString(gender);
            parcel.writeString(image);
        }
    }

    public static class Book implements Parcelable {
        protected Book(Parcel in) {
            ISBN = in.readString();
            Title = in.readString();
            Author = in.readString();
            Image = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ISBN);
            dest.writeString(Title);
            dest.writeString(Author);
            dest.writeString(Image);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Book> CREATOR = new Creator<Book>() {
            @Override
            public Book createFromParcel(Parcel in) {
                return new Book(in);
            }

            @Override
            public Book[] newArray(int size) {
                return new Book[size];
            }
        };

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String author) {
            Author = author;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        private String ISBN;
        private String Title;
        private String Author;
        private String Image;

        public Book(String isbn, String title, String author, String image) {
            ISBN = isbn;
            Title = title;
            Author = author;
            Image = image;
        }
    }

    public static class Car implements Parcelable {

        protected Car(Parcel in) {
            make = in.readString();
            model = in.readString();
            color = in.readString();
            Image = in.readString();
        }

        public static final Creator<Car> CREATOR = new Creator<Car>() {
            @Override
            public Car createFromParcel(Parcel in) {
                return new Car(in);
            }

            @Override
            public Car[] newArray(int size) {
                return new Car[size];
            }
        };

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public Car(String make, String model, String color, String image) {
            this.make = make;
            this.model = model;
            this.color = color;
            Image = image;
        }

        private String make;
        private String model;
        private String color;
        private String Image;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(make);
            parcel.writeString(model);
            parcel.writeString(color);
            parcel.writeString(Image);
        }
    }

    public static class Cat implements Parcelable {

        private String breed;
        private String country;

        protected Cat(Parcel in) {
            breed = in.readString();
            country = in.readString();
            origin = in.readString();
            Image = in.readString();
        }

        public static final Creator<Cat> CREATOR = new Creator<Cat>() {
            @Override
            public Cat createFromParcel(Parcel in) {
                return new Cat(in);
            }

            @Override
            public Cat[] newArray(int size) {
                return new Cat[size];
            }
        };

        public String getBreed() {
            return breed;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public Cat(String breed, String country, String origin, String image) {

            this.breed = breed;
            this.country = country;
            this.origin = origin;
            Image = image;
        }

        private String origin;
        private String Image;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(breed);
            parcel.writeString(country);
            parcel.writeString(origin);
            parcel.writeString(Image);
        }
    }
}
