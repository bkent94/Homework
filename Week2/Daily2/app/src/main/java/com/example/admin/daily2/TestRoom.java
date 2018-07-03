package com.example.admin.daily2;

public class TestRoom {
    public static void main(String[] args) {

        Room[][] verticalTrue = new Room[][] {  {new Room(false), new Room(false), new Room(false),
                new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                new Room(false) }
                , {new Room(false), new Room(false), new Room(false), new Room(false),
                new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
                }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(true), new Room(false),
                new Room(false), new Room(false), new Room(false)
        }, {new Room(false), new Room(true),
                new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        }, {new Room(false), new Room(false), new Room(false), new Room(false),
                new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
        },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)
                } };

        if( Room.isOutbreak(verticalTrue)){
            System.out.println("Outbreak Found");
        }

    }
}
