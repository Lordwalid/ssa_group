/**
 *	Example program for using eventlists
 *	@author Joel Karel
 *	@version %I%, %G%
 */

package simulation;

public class Simulation {

    public EventList list;
    public Queue queue;
    public PatientSource patientSource;
    public Hospital hospital;
    public Ambulance mach;
	

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	// Create an eventlist
	EventList l = new EventList();
	// A queue for the machine
	Queue q = new Queue();
	// A source
	PatientSource s = new PatientSource(q,l,"Source 1");
	// A sink
	Hospital si = new Hospital("Sink 1");
	// A machine
	Ambulance m = new Ambulance(q,si,l,"Machine 1");
	// start the eventlist
	l.start(2000); // 2000 is maximum time
    }
    
}
