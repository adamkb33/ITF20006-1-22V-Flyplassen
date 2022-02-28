public class Plane {

    //Set variables
    private int planeNumber;
    private static int numberOfPlanes = 0;

    //Create plane method
    public Plane() {

        //Increacing number of planes
        this.planeNumber = Plane.numberOfPlanes + 1;
        Plane.numberOfPlanes++;
    }

    //Get number of planes
    public static int totalNumberOfPlanes() {
        return numberOfPlanes;
    }

    //Get plane number
    public int getPlaneNumber() {
        return planeNumber;
    }

    //Override parent method
    @Override
    public String toString() {
        return "Fly nr, " + this.planeNumber;
    }

}
