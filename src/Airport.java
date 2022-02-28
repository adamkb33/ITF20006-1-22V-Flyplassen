import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Double.valueOf;

public class Airport {
    //Initialize variables
    private double totalQueueSize;
    private double numberOfPlanesDenied = 0;
    private double numberOfPlanesLanded = 0;
    private double numberOfPlanesDeparted = 0;
    private double availableTimeInterval = 0;
    private double landingQueueSize = 0;
    private double departueQueueSize = 0;

    //Create airport method for queue size
    public Airport(int totalQueueSize) {
        this.totalQueueSize = totalQueueSize;
    }

    //Create queue for landing and departure
    private Queue < Plane > landingQueue = new LinkedList < > ();
    private Queue < Plane > departureQueue = new LinkedList < > ();

    //Create method for landing planes
    public void landingPlanes() {

        //Increace number of planes landed
        this.numberOfPlanesLanded++;

        //Get number of planes in queue
        int planesInQueue = landingQueue.size() + departureQueue.size();

        //Get exact plane
        Plane nextPlane = landingQueue.poll();

        //Print message
        System.out.println(nextPlane + " landet, ventetid " + planesInQueue + " enheter");
    }

    //Create method for departuiring planes
    public void departuringPlanes() {

        //Increace number of departed planes
        this.numberOfPlanesDeparted++;

        //Get number of planes in queue
        int planeInQueue = landingQueue.size() + departureQueue.size();

        //Get next plane
        Plane nextPlane = departureQueue.poll();

        //Print message
        System.out.println(nextPlane + " tatt av, ventetid " + planeInQueue + " enheter.");
    }

    //Create method to increace interval
    public void setNextInterval() {
        this.availableTimeInterval++;
        this.landingQueueSize += this.landingQueue.size();
        this.departueQueueSize += this.departureQueue.size();
    }

    //Check if landing and departue queue is empty
    public boolean isLandingEmpty() {
        return landingQueue.isEmpty();
    }
    public boolean isAvgangEmpty() {
        return departureQueue.isEmpty();
    }

    //Create method if empty airport
    public void emptyAirport() {
        this.availableTimeInterval++;
        System.out.println("Flyplassen er tom.");
    }

    //Method for landing planes
    public void queuePlaneLanding(Plane plane) {

        //If queue is full
        if (landingQueue.size() == totalQueueSize) {
            this.numberOfPlanesDenied++;
            System.out.println(plane + " referert til annen flyplass. Landekø full.");
        }
        //Add plane if queue is not full
        else {
            landingQueue.add(plane);
            System.out.println(plane + " klar for landing.");
        }
    }

    //Method for departuring planes
    public void queuePlaneDeparture(Plane plane) {

        //If departuring queue is full
        if (departureQueue.size() == totalQueueSize) {
            this.numberOfPlanesDenied++;
            System.out.println("Avgangs kø full: " + plane + " har ikke landet, send tilbake i køen.");
        }
        //Add plane if queue is not full
        else {
            departureQueue.add(plane);
            System.out.println(plane + " klar for avgang.");
        }
    }

    //Method for getting average time waited for landing queue
    public double averageWatingTimeLanding() {
        //Devide lading queue with time interval
        return valueOf(landingQueueSize) / valueOf(availableTimeInterval);
    }

    //Method for getting average time waited time for departue queue
    public double averageWaitingTimeDeparture() {
        //Devide departure queue size with time interval
        return valueOf(departueQueueSize) / valueOf(availableTimeInterval);
    }

    //Method for getting landing queue size
    public int getLandingQueueSize() {
        return this.landingQueue.size();
    }

    //Method for getting departure queue size
    public int getAvgangQueueSize() {
        return this.departureQueue.size();
    }

    //Method for getting number of planes landed
    public int getNumberOfPlanesLanded() {
        return (int) numberOfPlanesLanded;
    }

    //Method for getting number of planes departed
    public int getNumberOfPlanesDeparted() {
        return (int) numberOfPlanesDeparted;
    }

    //Method for getting number of planes denied
    public int getNumberOfPlanesDenied() {
        return (int) numberOfPlanesDenied;
    }
}