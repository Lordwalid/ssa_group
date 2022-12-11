package simulation;

import Distribution.Generator;
import Distribution.IVector;

import java.util.ArrayList;
/**
 *	Product that is sent through the system
 *	@author Joel Karel
 *	@version %I%, %G%
 */
class Patient
{
	/** Stamps for the products */
	private ArrayList<Double> times;
	private ArrayList<String> events;
	private ArrayList<String> stations;


	private double[] coordinates;

	private int priority;
	// 0 = A1 (most urgent), 1 = B, 2 = A2 (least urgent)

	/** 
	*	Constructor for the patient
	*	Mark the time at which it is created
	*/
	public Patient(int regionNumber, int prio)
	{
		times = new ArrayList<>();
		events = new ArrayList<>();
		stations = new ArrayList<>();
		priority = prio;
		IVector coordVector = Generator.getPatients(1).get(regionNumber);
		coordinates = new double[] {coordVector.x(), coordVector.y()};
//		System.out.println("patient's coordinates " + coordinates[0] + " " + coordinates[1]);
	}
	
	
	public void stamp(double time,String event,String station)
	{
		times.add(time);
		events.add(event);
		stations.add(station);
	}
	
	public ArrayList<Double> getTimes()
	{
		return times;
	}

	public ArrayList<String> getEvents()
	{
		return events;
	}

	public ArrayList<String> getStations()
	{
		return stations;
	}

	public double[] getTimesAsArray()
	{
		times.trimToSize();
		double[] tmp = new double[times.size()];
		for (int i=0; i < times.size(); i++)
		{
			tmp[i] = (times.get(i)).doubleValue();
		}
		return tmp;
	}

	public String[] getEventsAsArray()
	{
		String[] tmp = new String[events.size()];
		tmp = events.toArray(tmp);
		return tmp;
	}

	public String[] getStationsAsArray()
	{
		String[] tmp = new String[stations.size()];
		tmp = stations.toArray(tmp);
		return tmp;
	}
	 public double[] getCoordinates(){
		return coordinates;
	 }

	 public int getPriority(){
		return priority;
	 }
}