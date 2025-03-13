import java.util.*;

class Room {
    int roomNo;
    String roomType;
    String[] roomSection;
    int price;
    boolean isBooked;
    Customer customer;

    public Room(int roomNo, String roomType,int price,boolean isBooked, String[] roomSection,Customer customer) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomSection = roomSection;
        this.price = price;
        this.isBooked = isBooked;
        this.customer = customer;
    }
    @Override
    public String toString() {
        return roomNo+"";
    }

}

class Customer {
    String name;
    String phone;
    String accountNumber;
    double accountBalance;

    public Customer(String name, String phone, String accountNumber, double accountBalance) {
        this.name = name;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public void viewDetails(){
        System.out.println("Name: "+name);
        System.out.println("Phone: "+phone);
        System.out.println("Account Number: "+accountNumber);
    }
}

class Hotel{
    ArrayList<Room> rooms;
    HashMap<Integer, Room> roomMap;
    double userBalance = 1000;

    public Hotel(){
         rooms = roomsRegistration();
         roomMap = roomsOrganized(rooms);
    }
    public static ArrayList<Room> roomsRegistration(){
        ArrayList<Room> rooms = new ArrayList<Room>(10);
        rooms.add(new Room(101,"Standard",150,false,new String[]{"Kitchen","Bathroom","Standard Bed"},
                new Customer("Johnny","03193084509","13245",1000)));
        rooms.add(new Room(102,"Standard",150,false,new String[]{"Kitchen","Bathroom","Standard Bed"},
                new Customer("Hanny","03296789321","15321",600)));
        rooms.add(new Room(103,"VIP",300,true,new String[]{"Kitchen","Bathroom","King Bed"},
                null));
        rooms.add(new Room(104,"VIP",300,false,new String[]{"Kitchen","Bathroom","King Bed"},
                new Customer("Sunny","03226699321","15691",2000)));
        rooms.add(new Room(105,"VIP",300,false,new String[]{"Kitchen","Bathroom","King Bed"},
                new Customer("Steven","03226699121","12691",300)));
        rooms.add(new Room(106,"Suite",500,false,new String[]{"Kitchen","Bathroom","Queen Bed","Living Area"},
                new Customer("Mohan","03006699321","12391",500)));
        rooms.add(new Room(107,"Suite",500,true,new String[]{"Kitchen","Bathroom","Queen Bed","Living Area"},
                null));
        rooms.add(new Room(108,"Suite",500,true,new String[]{"Kitchen","Bathroom","Queen Bed","Living Area"},
                null));
        rooms.add(new Room(109,"Standard",200,false,new String[]{"Kitchen","Bathroom","Standard Bed"},
                new Customer("Guptil","03222699321","15392",800)));
        rooms.add(new Room(110,"Standard",200,false,new String[]{"Kitchen","Bathroom","Standard Bed"},
                new Customer("Justin","03012699321","10391",90)));
        return rooms;
    }

    public HashMap<Integer,Room> roomsOrganized(ArrayList<Room> rooms){
        HashMap<Integer,Room> roomsOrganized = new HashMap<>();
        for (Room r: rooms){
            roomsOrganized.put(r.roomNo, r);
        }
        return roomsOrganized;
    }

    public void checkStatus(HashMap<Integer,Room> roomsOrganized){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter Room Number : ");
        int roomNo = scanner.nextInt();
        Room room = roomsOrganized.get(roomNo);
        if (room.isBooked){
            System.out.println("Room is Available");
        } else {
            System.out.println("Room is not Available");
        }
    }

    public void availableRooms(HashMap<Integer,Room> roomsOrganized){
        for (Room r: roomsOrganized.values()){
            if (r.isBooked){
                System.out.println("Room Available : "+roomsOrganized.get(r.roomNo));
            }
        }
    }
    public void reservation(HashMap <Integer,Room> roomsOrganized){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an available Room No. : ");
        int roomNo = scanner.nextInt();

        if (roomsOrganized.get(roomNo).isBooked == false){
            System.out.println("Sorry, The room is already booked");
        } else {
            System.out.println("The Room "+ roomsOrganized.get(roomNo).roomNo+" Price is $"+roomsOrganized.get(roomNo).price);
            System.out.print("Do you want to proceed (Y/N) : ");
            String user = scanner.next();
            if (user.equals("Y")){
                System.out.print("Enter your Name : ");
                String name = scanner.next();
                System.out.print("Enter your Phone : ");
                String phone = scanner.next();
                System.out.print("Enter your Account Number : ");
                String accountNumber = scanner.next();
                System.out.println();
                System.out.print("Enter the amount to be deducted from your account : ");
                int amountToBeDeducted = scanner.nextInt();
                if (amountToBeDeducted <= userBalance){
                    if (amountToBeDeducted == roomsOrganized.get(roomNo).price){

                        roomsOrganized.get(roomNo).customer = new Customer(name,phone,accountNumber,amountToBeDeducted);
                        userBalance = userBalance - amountToBeDeducted;
                        roomsOrganized.get(roomNo).isBooked = false;
                        System.out.println("Reservation completed Successfully");
                    } else {
                        System.out.println("Enter valid room's price");
                    }
                } else{
                    System.out.println("Insufficient Balance");
                }
            }

        }
    }

    public void roomCategorization(HashMap<Integer,Room> roomsOrganized) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an available Room No. : ");
        int roomNo = scanner.nextInt();
        Room room = roomsOrganized.get(roomNo);
        System.out.println("Room Number : " + room.roomNo);
        System.out.println("Room Category : " + room.roomType);
        System.out.println("Room Price : " + room.price);
        System.out.println("Room Available : " + room.isBooked);
        System.out.println();
        System.out.println("Room Categorization : ");
        int numbering = 1;
        for (String i : room.roomSection) {
            System.out.println(numbering + ", " + i);
            numbering++;
        }
    }

    public void viewBookingDetails(HashMap<Integer,Room> roomsOrganized){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Room No. : ");
        int roomNo = scanner.nextInt();
        boolean valid = roomsOrganized.containsKey(roomNo);
        if (valid){
            Room room = roomsOrganized.get(roomNo);
            System.out.println();
            System.out.println("Booked By: ");
            try {
                room.customer.viewDetails();
            } catch (NullPointerException e){
                System.out.println("Room is not booked");
            }

        } else {
            System.out.println("Invalid room");
        }
    }

}

public class HotelReservationSystem{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean condition = true;

        Hotel hotel = new Hotel();

        while (condition){
            System.out.println("\n");
            System.out.println("\t\t\tHotel Reservation System\n\n");
            System.out.println("Press \"1\" to check available rooms");
            System.out.println("Press \"2\" to check status of a particular room");
            System.out.println("Press \"3\" to make a reservation");
            System.out.println("Press \"4\" to view room categorization");
            System.out.println("Press \"5\" to view booking details");
            System.out.println("Press \"6\" to exit the program");
            System.out.println();
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice){
                case 1:
                    hotel.availableRooms(hotel.roomMap);
                    break;
                case 2:
                    hotel.checkStatus(hotel.roomMap);
                    break;

                case 3:
                    hotel.reservation(hotel.roomMap);
                    break;
                case 4:
                    hotel.roomCategorization(hotel.roomMap);
                    break;
                case 5:
                    hotel.viewBookingDetails(hotel.roomMap);
                    break;
                case 6:
                    condition = false;
                default:
                    System.out.println("Invalid choice");
            }



        }
    }
}
