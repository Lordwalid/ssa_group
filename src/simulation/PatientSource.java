package simulation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *	A source of products
 *	This class implements CProcess so that it can execute events.
 *	By continuously creating new events, the source keeps busy.
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class PatientSource implements Process
{
	/** Eventlist that will be requested to construct events */
	private EventList list;
	/** Queue that buffers products for the machine */
	private Queue queue;
	/** Name of the source */
	private String name;
	/** Mean interarrival time */
	private double meanArrTime;
	/** Interarrival times (in case pre-specified) */
	private double[] interarrivalTimes;
	/** Interarrival time iterator */
	private int interArrCnt;

	private int regionNumber;

	/**
	*	Constructor, creates objects
	*        Interarrival times are exponentially distributed with mean 33
	*	@param q	The receiver of the products
	*	@param l	The eventlist that is requested to construct events
	*	@param n	Name of object
	*/
	public PatientSource(Queue q, EventList l, String n, int rn)
	{
		list = l;
		queue = q;
		name = n;
		regionNumber = rn;
		meanArrTime=33;
		// put first event in list for initialization
		list.add(this,0,drawRandomExponential(meanArrTime)); //target,type,time
	}

	/**
	*	Constructor, creates objects
	*        Interarrival times are exponentially distributed with specified mean
	*	@param q	The receiver of the products
	*	@param l	The eventlist that is requested to construct events
	*	@param n	Name of object
	*	@param m	Mean arrival time
	*/
	public PatientSource(Queue q, EventList l, String n, double m)
	{
		list = l;
		queue = q;
		name = n;
		meanArrTime=m;
		// put first event in list for initialization
		list.add(this,0,drawRandomExponential(meanArrTime)); //target,type,time
	}

	/**
	*	Constructor, creates objects
	*        Interarrival times are prespecified
	*	@param q	The receiver of the products
	*	@param l	The eventlist that is requested to construct events
	*	@param n	Name of object
	*	@param ia	interarrival times
	*/
	public PatientSource(Queue q, EventList l, String n, double[] ia)
	{
		list = l;
		queue = q;
		name = n;
		meanArrTime=-1;
		interarrivalTimes=ia;
		interArrCnt=0;
		// put first event in list for initialization
		list.add(this,0,interarrivalTimes[0]); //target,type,time
	}
	
        @Override
	public void execute(int type, double tme)
	{
		// show arrival
//		System.out.println("Arrival at time = " + tme);
		int priority = -1;
		priority = (int) Math.floor(Math.random()*3);
		// give arrived patient to queue
		Patient p = new Patient(regionNumber,priority);
		p.stamp(tme,"Creation",name);
//		System.out.println("generated new patient at " + Arrays.toString(p.getCoordinates()));
		queue.offerPatient(p);
		// generate duration

		// first, calculate lambda at this point in time (t is measured in hours)
		double lambda = 3 - 2*Math.sin(5*(Math.PI + tme/60)/(6*Math.PI));
		double duration = drawRandomExponential(lambda);
		// Create a new event in the eventlist
		list.add(this,0,tme+duration); //target,type,time

	}
	
	public static double drawRandomExponential(double mean)
	{
		// draw a [0,1] uniform distributed number
		double u = Math.random();
		// Convert it into a exponentially distributed random variate with mean 33
		double res = -mean*Math.log(u);
		return res;
	}
}