package simulation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *	Machine in a factory
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Ambulance implements Process, Acceptor
{
	/** Product that is being handled  */
	private Patient patient;
	/** EventList that will manage events */
	private final EventList eventlist;
	/** Queue from which the machine has to take products */
	private Queue queue;
	/** Sink to dump products */
	private Acceptor sink;
	/** Status of the machine (b=busy, i=idle) */
	private char status;
	/** Machine name */
	private final String name;
	/** Mean processing time */
	private double meanProcTime;
	/** Processing times (in case pre-specified) */
	private double[] processingTimes;
	/** Processing time iterator */
	private int procCnt;
	private double [] coordinates;
	private double [] coordWD;


	/**
	*	Constructor
	*        Service times are exponentially distributed with mean 30
	*	@param q	Queue from which the machine has to take products
	*	@param s	Where to send the completed products
	*	@param e	Event list that will manage events
	*	@param n	The name of the machine
	*/
	public Ambulance(Queue q, Acceptor s, EventList e, String n, double[] cwd)
	{
		coordWD = cwd;
		status='i';
		queue=q;
		sink=s;
		eventlist=e;
		name=n;
		coordinates = coordWD;
		meanProcTime=30;
		requestNextPatient();
	}

	public void requestNextPatient(){
		if(queue.checkForNextPatient(this)){
			acceptPatient(queue.giveNextPatient());
		}
		else {
			coordinates = coordWD;
		}

	}

	/**
	*	Method to have this object execute an event
	*	@param type	The type of the event that has to be executed
	*	@param tme	The current time
	*/
	public void execute(int type, double tme)
	{
		// show arrival
//		System.out.println("Patient delivered to hospital at time = " + tme + " by " + name + ", from " + Arrays.toString(patient.getCoordinates()));
		// Remove product from system
		patient.stamp(tme,"Delivery complete",name);
		sink.acceptPatient(patient);
		coordinates = Simulation.h.coordinates;
//		System.out.println(patient.getTimes());
		patient =null;
		// set machine status to idle
		status='i';
		// Ask the queue for products
		requestNextPatient();
	}
	
	/**
	*	Let the machine accept a product and let it start handling it
	*	@param p	The product that is offered
	*	@return	true if the product is accepted and started, false in all other cases
	*/
        @Override
	public boolean acceptPatient(Patient p)
	{
		// Only accept something if the machine is idle
		if(status=='i')
		{
			// accept the product
			patient = p;
			// mark starting time
			patient.stamp(eventlist.getTime(),"Delivery started",name);
			// start production
			startProduction();
			// Flag that the patient
			return true;
		}
		// Flag that the product has been rejected
		else return false;
	}
	
	/**
	*	Starting routine for the production
	*	Start the handling of the current product with an exponentially distributed processing time with average 30
	*	This time is placed in the event list
	*/
	private void startProduction()
	{
		// generate duration
		if(meanProcTime>0)
		{
			double timeToPatient = manhattanDist(coordinates, patient.getCoordinates());
			double timeToHospital = manhattanDist(patient.getCoordinates(), Simulation.h.coordinates);
			double drivingTime = timeToPatient + timeToHospital;

//			// mark starting time
//			patient.stamp(eventlist.getTime() + timeToPatient,"Delivery started",name);

//			System.out.println("patient's coord " + Arrays.toString(patient.getCoordinates()));
//			System.out.println("ambulance's coord " + Arrays.toString(coordinates));

			double processingTime = drawRandomErlang(3,1);
			double duration = drivingTime + processingTime;
			// Create a new event in the event list
			double tme = eventlist.getTime();
			eventlist.add(this,0,tme+duration); //target,type,time
			// set status to busy
			status='b';
		}
/*		else
		{
			if(processingTimes.length>procCnt)
			{
				eventlist.add(this,0,eventlist.getTime()+processingTimes[procCnt]); //target,type,time
				// set status to busy
				status='b';
				procCnt++;
			}
			else
			{
				eventlist.stop();
			}
		}*/
	}

	private double manhattanDist(double[] coord1, double [] coord2 ){
		return Math.abs(coord2[0] - coord1[0]) + Math.abs(coord2[1] - coord1[1]);
	}

	public double drawRandomErlang(int k, double lambda){
		double sigma = 0;
		double u;
		for (int i=0; i<k; i++){
			u = Math.random();
			sigma = sigma + Math.log(u);
		}
		return 1/lambda * sigma;
	}

	public static double drawRandomExponential(double mean)
	{
		// draw a [0,1] uniform distributed number
		double u = Math.random();
		// Convert it into an exponentially distributed random variate with mean 30
		double res = -mean*Math.log(u);
		return res;
	}
}