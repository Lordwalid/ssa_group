package Simulation;

import java.util.ArrayList;
import java.util.Random;

/**
 *	Queue that stores products until they can be handled on a machine machine
 *	@author Joel Karel
 *	@version %I%, %G%
 */
public class Queue implements ProductAcceptor
{
	/** List in which the products are kept */
	private ArrayList<Product> A1;
	private ArrayList<Product> A2;
	private ArrayList<Product> B;
	private Random r;

	/** Requests from machine that will be handling the products */
	private ArrayList<Machine> requests;
	
	/**
	*	Initializes the queue and introduces a dummy machine
	*	the machine has to be specified later
	*/
	public Queue(){
		r = new Random();
		A1 = new ArrayList<>();
		A2 = new ArrayList<>();
		B = new ArrayList<>();
		requests = new ArrayList<>();
	}
	
	/**
	*	Asks a queue to give a product to a machine
	*	True is returned if a product could be delivered; false if the request is queued
	*/
	public boolean askProduct(Machine machine)
	{
		// This is only possible with a non-empty queue
		if(A1.size()>0){

			// If the machine accepts the product
			if(machine.giveProduct(A1.get(0))){

				A1.remove(0);// Remove it from the queue
				System.out.println("patient with priority level A1 is taken ");
				return true;
			}
		}	
		else if(B.size()>0){

			if(machine.giveProduct(B.get(0))){

				B.remove(0);// Remove it from the queue
				System.out.println("patient with priority level B is taken ");
				return true;
			}
		}
		else if(A2.size()>0){
			if(machine.giveProduct(A2.get(0))){
				A2.remove(0);
				System.out.println("patient with priority level A2 is taken ");
				return true;
			}
			
		}
		else{
			requests.add(machine);
			return false; // queue request
		}
		return false;
	}
	
	/**
	*	Offer a product to the queue
	*	It is investigated whether a machine wants the product, otherwise it is stored
	*/
	public boolean giveProduct(Product p)
	{
		// Check if the machine accepts it
		if(requests.size()<1){

			int random = r.nextInt(3);

			if(random == 0){
				A1.add(p);
			}	
			else if(random == 1){
				B.add(p); 
			}
			else{
				A2.add(p);
			}
		}  
		else{
			boolean delivered = false;
			while(!delivered & (requests.size()>0)){
				delivered=requests.get(0).giveProduct(p);
				// remove the request regardless of whether or not the product has been accepted
				requests.remove(0);
			}
			if(!delivered){
				int random = r.nextInt(3);

				if(random == 0){
					A1.add(p);
				}	
				else if(random == 1){
					B.add(p); 
				}
				else{
					A2.add(p);
				}
			}
			// Otherwise store it
		}
		return true;
	}
}