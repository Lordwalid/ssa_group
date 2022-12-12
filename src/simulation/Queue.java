package simulation;

import java.util.ArrayList;

/**
 *	Queue that stores products until they can be handled on a machine
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Queue
{
	/** List in which the products are kept */
	private ArrayList<Patient> patientsInLine;
	/** ambulanceQueue that will be handling the patientsInLine */
	private ArrayList<Ambulance> ambulanceQueue;

	/**
	*	Initializes the queue and introduces
	*/
	public Queue()
	{
		patientsInLine = new ArrayList<>();
		ambulanceQueue = new ArrayList<>();
	}

	/**
	*	Asks a queue to give a product to a machine
	*	True is returned if a product could be delivered; false if the request is queued
	*/

	public boolean checkForNextPatient(Ambulance ambulance){
		if(patientsInLine.size() > 0){
			return true;
		}
		else {
			ambulanceQueue.add(ambulance);
			return false;
		}
	}

	public Patient giveNextPatient(){
		return patientsInLine.remove(0);
	}


	/**
	*	Offer a patient to the ambulance
	*	It is investigated whether a machine wants the product, otherwise it is stored
	*/

	public void offerPatient(Patient p)
	{
		// Check if there is any ambulance in the region that could accept the patient
		if(ambulanceQueue.size() < 1) {
			if (patientsInLine.size() == 0) {
				patientsInLine.add(p); // add the first patient
				//System.out.println("added first patient with priority " + p.getPriority());
			}
			else {
				//System.out.println("patients in line: " + patientsInLine.size());
				//-----------
				// looping through all patients in line, when found one with larger priority (i.e. less important), put new one before them
				for (int i = 0; i < patientsInLine.size(); i++) {
					if (patientsInLine.get(i).getPriority() > p.getPriority()) {
						patientsInLine.add(i, p);
						//System.out.println("patient's priority is " + p.getPriority() + ", adding them before next one with priority " + patientsInLine.get(i + 1).getPriority());
						return; // if we found where to place the patient before the less prioritized one
					}
				}
				patientsInLine.add(p); // if we didn't find anyone less urgent, put them in the end
				//System.out.println("priority is " + p.getPriority() + ", putting in the end");
			}
		}
		else
		{
			ambulanceQueue.get(0).acceptPatient(p);
			ambulanceQueue.remove(0);
		}
	}


}