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
    public static ArrayList <Ambulance> ambulances;
	

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
/*
        for (int i=0; i<7; i++){
            eventLists.add(new EventList());
            queues.add(new Queue());
            patientSources.add(new PatientSource(queues.get(i), eventLists.get(i),"PSource" + i));
        }

        for (int i=0; i < patientSources.size(); i++){
            ambulances.add(new Ambulance(queues.get(i), h, eventLists.get(i),"Ambulance" + i));
        }
*/
    // Create an eventlist
	EventList l1 = new EventList();
	// A queue for the machine
	Queue q1 = new Queue();
	// A source
	PatientSource region1 = new PatientSource(q1,l1,"Region 1", 5);
	// A sink
	 h = new Hospital("Hospital");
	// A machine
	Ambulance a1 = new Ambulance(q1,h,l1,"Ambulance 1");
	// start the eventlist
	l1.start(2000); // 2000 is maximum time

    }
    
}
