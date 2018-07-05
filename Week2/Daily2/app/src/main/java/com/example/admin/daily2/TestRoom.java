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

        System.out.println("Number of rooms: "+verticalTrue[0].length+","+verticalTrue.length);

        if( Room.isOutbreak(verticalTrue)){
            System.out.println("Outbreak Found");
        }

    }
}
