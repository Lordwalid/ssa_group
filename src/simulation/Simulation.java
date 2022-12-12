/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

import java.util.ArrayList;

public class Simulation {
    public static ArrayList <EventList> eventLists;
    public static ArrayList <Queue> queues;
    public static ArrayList <PatientSource> patientSources;
    public static Hospital h;
    public static ArrayList<ArrayList <Ambulance>> ambulances;
    public static final int numberAmbulancesPerRegion = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        h = new Hospital("Hospital");
        queues = new ArrayList<>();
        eventLists = new ArrayList<>();
        patientSources = new ArrayList<>();
        ambulances = new ArrayList<>();

        for (int i = 0; i < 7; i++){
            // A queue for the machine
            queues.add(new Queue());
            // Create an eventlist
            eventLists.add(new EventList());
            // A source
            patientSources.add(new PatientSource(queues.get(i), eventLists.get(i), "Region " + i, i));
            ambulances.add(new ArrayList<>());
            for (int j = 0; j < numberAmbulancesPerRegion; j++){
                ambulances.get(i).add(new Ambulance(queues.get(i),h,eventLists.get(i), "Ambulance " + (j+1) + " in region " + i, h.wdcoordinates[i]));
            }
        }

        for (EventList eventList : eventLists) {
            eventList.start(2000);
        }

        for (int i = 0; i < h.getNumberPatients(); i++){
            double callTime = h.getPatients().get(i).getTimes().get(0);
            double deliveryTime = h.getPatients().get(i).getTimes().get(2);
            System.out.println(h.getPatients().get(i).getPriority() + "|" + callTime + "|" + deliveryTime);
        }

//        System.out.println("total number of patients = " + h.getNumberPatients());

    }
    
}
