import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Initilize scanner
        Scanner scanner = new Scanner(System.in);

        //Initilize variables
        int landingQueue = 2;

        //Method for formating numbers with 4 decimal places
        DecimalFormat df = new DecimalFormat("0.0000");

        //First user prompt
        System.out.print("Hvor mange tidsenheter skal simuleringen gå? : ");
        int timeInterval = scanner.nextInt();

        //Second user prompt
        System.out.print("Forventet antall ankomster pr. tidsenhet ?   : ");
        double expectedLandingFreq = scanner.nextDouble();

        //Last user prompt
        System.out.print("Forventet antall avganger pr. tidsenhet ?    : ");
        double expectedDepartureFreq = scanner.nextDouble();

        //Create new airport object
        Airport station = new Airport(landingQueue);

        //Functions triggered for every time interval
        for (int i = 1; i <= timeInterval; i++) {

            //Initialize variables
            int departueQueue = Poisson.getPoissonRandom(expectedDepartureFreq);
            int planeLandingQueue = Poisson.getPoissonRandom(expectedLandingFreq);

            //Add new plane to departuring queue
            for (int j = 0; j < departueQueue; j++) {
                station.queuePlaneDeparture(new Plane());
            }

            //Add new plane to landing queue
            for (int j = 0; j < planeLandingQueue; j++) {
                station.queuePlaneLanding(new Plane());
            }

            //Check if landing queue is empty
            if (station.isLandingEmpty()) {
                //If landing queue is empty and departure queue is empty throw message
                if (station.isAvgangEmpty()) {
                    station.emptyAirport();
                }
                //If landing queue is empty and departure queue is not empty add plane to departue queue
                else {
                    station.departuringPlanes();
                }
            }
            //If landing queue is not empty add new plane to landing queue
            else {
                station.landingPlanes();
            }

            //Set new interval
            station.setNextInterval();
        }

        //Print out data
        System.out.println( "\nSimuleringen ferdig etter      : " + timeInterval + " tidsenheter." +
                "\nTotalt antall fly behandlet    : " + Plane.totalNumberOfPlanes() +
                "\nAntall fly landet              : " + station.getNumberOfPlanesLanded() +
                "\nAntall fly tatt av             : " + station.getNumberOfPlanesDeparted() +
                "\nAntall fly avvist              : " + station.getNumberOfPlanesDenied() +
                "\nAntall fly klare for landing   : " + station.getLandingQueueSize() +
                "\nAntall fly klare for avgang    : " + station.getAvgangQueueSize() +
                "\nGj.snitt. ventetid, landing    : " + df.format(station.averageWatingTimeLanding()) +
                "\nGj.snitt. ventetid, avgang     : " + df.format(station.averageWaitingTimeDeparture())
        );

    }
}