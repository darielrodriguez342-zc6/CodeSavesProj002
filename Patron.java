public class Patron {
    //Initialize and Declare variables
    String ID = "0";
    String name;
    String address;
    double overdue;

    //Constructor
    public Patron(String ID, String name, String address, double overdue) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.overdue = overdue;
    }

    //Setters and Getters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOverdue() {
        return overdue;
    }

    public void setOverdue(double overdue) {
        this.overdue = overdue;
    }


    //Override to string
    @Override
    public String toString() {
        return "Patron{ " +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", overdue=" + overdue +"$"+
                '}';
    }
}
