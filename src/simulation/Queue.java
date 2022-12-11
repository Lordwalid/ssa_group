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

/*
	public void requestNextPatient(Ambulance ambulance)
	{
		// This is only possible with a non-empty queue
		if(patientsInLine.size()>0)
		{
			// If the machine accepts the product
			if(ambulance.acceptPatient(patientsInLine.get(0)))
			{
				patientsInLine.remove(0);// Remove it from the queue
			}
		}
		else
		{
			ambulanceQueue.add(ambulance);
		}
	}
*/

	

	/**
	*	Offer a patient to the ambulance
	*	It is investigated whether a machine wants the product, otherwise it is stored
	*/

	public void offerPatient(Patient p)
	{
		// Check if there is any ambulance in the region that could accept the patient
		if(ambulanceQueue.size() < 1)
			patientsInLine.add(p); // if there is none, add patient to the line
		else
		{
			ambulanceQueue.get(0).acceptPatient(p);
			ambulanceQueue.remove(0);
		}
	}


}