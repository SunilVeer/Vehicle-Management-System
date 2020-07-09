# Vehicle-Management-System
A Java application that will allow user to hire different types of vehicles.

# Overview
This is a more prescriptive and incremental assignment suitable for those preferring clear
guidance (instead of being open ended designs). You are required to complete Part A before
proceeding to Part B, and similarly Part B before Part C.

Part A: Develop a simple Vehicle class with three states ‘A’ (available), ‘S’ (servicing) and ’H’
(hired) and methods. Your class must implement the required interface and test it with the data
provided. In the second part the Vehicle class must be used for developing a simple application.

Part B: Extend to PremiumVehicle implementing the overridden methods (which introduce
additional conditions for state transitions). You are also required to develop a menu-driven
application where both Vehicle and PremiumVehicle objects are stored in a common array,
which must be manipulated in a polymorphic way when appropriate.

Part C: You are required to extend the menu-driven application developed in section B
incorporating file and exception handling. The customer details must also be included allowing
specific discounts. All Vehicle and subclass objects must be written to a file when program exits
and restored back when program commences. The methods should be made to throw
appropriate exceptions when operation cannot be performed.

# Requirements
# Part A
## Section I : Writing a Vehicle class
You are required to write a class named Vehicle to manage the hiring of vehicles. The
requirements are specified below.

Instance Variables:
Provide private instance variables to store the vehicle-ID, hirer-ID, description, status, dailyrate,
odometer-reading and date/time of hire (use the DateTime class provided). Instance
variable for status of a car must be set to either 'A' (available), 'H'(hired) or 'S'(servicing).

Constructor:
Provide a constructor for the class that takes vehicle-ID, description, daily-rate and odometer
reading to initialize the corresponding instance variables and to set the instance variable for
status to 'A' (available for hire when first created) .
public Vehicle(String vehicleID, String description, double dailyRate, int odometer)

Methods:
Provide methods to hire a vehicle, complete a hire, service a vehicle, return a vehicle back from
service and to print the current state of the vehicle. The hire() must take as argument ID of
hirer. The date/time of hire should be set to current time which can be created using the default
DateTime constructor (DateTime class described in later section). This method should return
false if an attempt is made to hire a vehicle which is currently being serviced (status ‘S’) or
already on hire (status ‘H’), otherwise(status ‘A’) the method should return true after setting the
hirer ID (to the argument passed), the hire date (to current date), and status (to ‘H’ indicating
hired).

public boolean hire(String hirerID) // called when car is hired
The methods below are required for servicing a vehicle and for returning a vehicle back from
service. These operations must return true only when the status of the vehicle are 'A' and 'S'
respectively. Note the method serviceComplete(into odo) must set the current odometer
reading.
public boolean service() // called when car is sent for service
public boolean serviceComplete(int odo) // called when car is back from service

The hireComplete(int odo) must take one argument for odometer reading and return the
charges based on the duration of hire if the operation is successful, and a negative value (-1.0)
otherwise. It must succeed only if the status is'H' and the odometer reading is greater than
odometer reading when car was hired. The duration-based charge can be computed multiplying
daily-rate, by number of days. Assume that minimum rental duration is one day and the number
of days is computed based on 24 hours from the time of hire. For example, if Jack hires a car at
9.00 am Monday morning and returns it on Tuesday 10.00 am, the hire duration should be set as
two days for the purpose of computing the charges.

public double hireComplete(int odo) // called when car hire is completed
The print() must print the current date and time (using the getCurrentTime() method of
DateTime class) and the necessary vehicle details (ID, description, daily-rate, status, odometer
reading) of Vehicle. If the vehicle is currently on hire, the ID of hirer and date/time of hire must
also be printed.

public void print()
Override the toString method to return the String equivalent

public Sting toString()
This class should also provide accessors for all the instance variables (required for the
subclass).

## Section II : Using the Vehicle class
(i) Declare an array named vehs that can store the references of up to 5 Vehicle objects.
Create 5 Vehicle objects using its constructor passing the values specified below (for ID,
description, daily-rate and odometer reading), storing their references in the array.
SAM134, Toyota Camry 02, 45.00, 140000
QKO156, Honda Accord 04, 65.0, 125000
TUV178, Toyota Starlet 02, 35.00, 180000
SAG132, Toyota Avalon 05, 52.0, 190000
PRE342, Honda Civic 97, 35.00, 145000
(ii) Display the ID and description of all the vehicles stored in the array.
Hint: Access the Vehicle objects by iterating through the array of references vehs
(using a loop). Extract ID and description of these objects using their accessors.
(iii) Allow user to view the vehicles in the required price range (by specifying lower- and
upper limits for daily rate). Display ID, description and daily-rate of all matching
vehicles. If no vehicles are found in the range, print an appropriate message.
Hint: This requires you to loop through the array of references and select the required
objects using the accessor for daily-rate.
(iv) Allow user to hire, complete-hire, service or complete-service a vehicle specifying the
vehicle ID. If a vehicle is being hired prompt the user to enter the hirer-ID, and if
service-complete or hire-complete options are selected prompt the user to enter the
odometer reading as well.
Hint: This requires looping through the array of references to locate the object with
the specified vehicle ID. If such an object is found then invoke the appropriate
method on that object passing the required argument. Print appropriate error
message if the object with specified ID cannot be found or if the required
operation cannot be performed.
(v) Allow user to repeat the step 4, using a do while loop. The loop should be repeated, as
long as user responds positively to the prompt, “Any more transactions?”.
(vi) Print the final details of all the vehicle objects using a for loop and the print() method.

# Part B
## Section I : Writing the subclass PremiumVehicle
BestDeals car-rentals also supplies premium cars, which are newer luxury cars. The charges for
premium cars are based on distance covered and duration of hire. The premium cars are
charged for each additional km beyond the free mileage allowed (based on daily mileage and the
duration of hire). The free mileage allowance varies from one premium car to another. Assume
the charge per km after the daily free mileage is $0.10 per km. All premium cars must be
serviced within a specified duration, which also varies from car to car.
Derive a subclass of Vehicle named PremiumVehicle. This subclass should include three
additional instance variables: free mileage allowance per day, service length (for example after
every 10000 km) and the odometer reading at last service. The constructor for this class should
take three additional arguments corresponding to these three instance variables. This subclass
(PremiumVehicle) must override the following methods which should make use of the
superclass methods.

serviceComplete(into do)
// to update the odometer reading on completion of service

hireComplete(int odo)
// to include mileage based charges in addition to duration based charges.

hire(String hirer)
// to verify whether car service is due for service (in addition to availability)
// For example a premium car with last service 15,000, service length 10,000 and current
// odometer reading 26,000 cannot be hired (even if it is available).

print()
// to display the instance variables mileage, last-service and service duration in addition to
// the superclass instance variables (ID, description, …)

public String toString()
// Override the toString method to return the String representation of the object

## Section II: Manipulating Vehicle and PremiumVehicle objects
(i) Declare an array of 6 Vehicle references named vehs, that can refer up to 6 Vehicle or
PremiumVehicle (subclass) objects. Create the Vehicle and PremiumVehicle objects
specified below storing their references in vehs. Using a for loop and the print() method
display the details of all objects (both Vehicle and PremiumVehicle) stored in vehs.
(Polymorphism ensures correct version of print()is called)

(ii) Write a menu driven program to perform the common operations (see below)
Vehicle Menu
Display Available Cars 1
Hire Vehicle 2
Complete Hire 3
Service Vehicle 4
Complete Service 5
Exit 6
Your Choice : _

## Specification/Guidelines

Display Option: Print the details of all vehicles. The details for all vehicles must include ID,
description and odometer reading. If the vehicle is on hire, the hired data/time and hirer-name
must be displayed. The details for premium vehicles must in addition include service length, last
service and mileage allowance per day.

Hire Option: Prompt the user and read the vehicle ID. Locate the object searching through the
array of Vehicle references vehs. If no such vehicle exist, or it is not available for hire print an
appropriate message and return to menu, otherwise, prompt user to enter ID of hirer and
invoke the hire() method.

Complete Hire Option: Prompt the user and read the vehicle ID. Locate the object by searching
using the array vehs. If no such vehicle exists or the vehicle is not on hire, print an appropriate
error message and return to menu. Otherwise prompt and read the current odometer reading
and invoke the hireComplete() method to end the hire and to compute and display the charges.

Complete Service option: Prompt the user and read the vehicle ID and locate the object. If
operation is not allowed, print error message otherwise prompt and read the current odometer
reading and invoke the serviceComplete() method.

# Part C: 
## Vehicle Hire System
Overview
You are required to extend the program written in assignments 3 and 4, to manage all the common
operations, taking into consideration discounts applicable to different types of customers.
Customers are classified into two main categories, individual customers and corporate customers
with discounts computed differently. Hence you are required to write an abstract class named
Customer with subclasses ICustomer and CCustomer.

You are also required to enhance the Vehicle and PremiumVehicle class developed in the
assignments 3 and 4. Exceptions must be thrown when an attempt is made to hire or complete a
hire incorrectly (You can use the standard exception class or create your own exception class). The
final part requires you to write a class named ManageHiring that uses Customer (and its subclasses),
and Vehicle (and it subclass) objects to facilitate the common operations. It also requires you to read
and write Vehicle and Customer objects to text files, to allow persistence. (You are free to use array
or any generic classes for storing the elements)
Override toString() to return the String representation of the object in all your classes

## Section I Enhancing Vehicle and PremiumVehicle classes
In this section you are required to modify the methods (hire(), completeHire() …) of Vehicle and
subclass to throw exceptions when invalid calls are made (see below). The exception thrown
must indicate the cause of error, allowing the caller to respond appropriately and recover if
possible.

## Section II Customer and subclasses

Customer class:
All customers have a unique ID and name. All customers are offered discounts based on total
charges though the method of computing discount differs for individual and corporate
customers. In this part you are required to write the abstract class named Customer with:
(i) Instance variables ID, name and phone
(ii) Constructor taking as arguments the value for ID, name and phone
(iii) An abstract method double getDiscount() to take as argument a double value for amount and
to return a double (value for discount offered).
(iv) Appropriate accessors for the instance variables.

Subclass ICustomer:
Most individual customers are offered a discount based on previous mileage. Those with over
100,000 mileage are offered 10% discount, and those with over 200,000 mileage are offered
20% discount. (This past mileage must be updated after each hire) For example, if the customer
has 120,000km of mileage the discount offered for charge of $270 is $27. Individual customers
are allowed only one car for hire at any one time. In this part you are required to write the class
ICustomer (extending Customer) with:
(i) Instance variable for mileage
(ii) Constructor taking as arguments values for ID, name, phone and mileage
(iii) Implementation for the abstract method double getDiscount() declared in the superclass
Customer, which takes a double value (the total charge) and to returns a double (the
discount offered)

Subclass CorporateCustomer:
All corporate customers are offered a discount based on a preset rate that reflects the
relationship with the corporation. Corporate customers are allowed to hire as many vehicles as
required. For example, if the rate 0.08 (8%) then the discount offered on charge of $5000 is
$400 ( 5000 * 0.08 ). In this part you are required to write the class CCustomer extending
Customer with:
(i) Instance variable for rate and an accessor for retrieving it.
(ii) A mutator for changing the rate of discount when needed
(iii) Constructor taking as arguments values for ID, name and the rate.
(iv) Implementation for the abstract method double getDiscount() declared in the superclass
Customer, which takes a double value (the total charge) and to returns a double (the
discount offered)

## Section III Managing the Vehicle Hiring
In this part you are required to write a class named ManageHiring following the steps outlined
below allowing Customer and Vehicle objects to be created, stored in arrays, manipulated,
written and read back from files. All the Customer (and subclass objects) should be stored in a
file named customers.txt and all the Vehicle (and subclass) objects should be stored in a file
named vehicle.txt.

## Overall Requirements
The users should be allowed to perform the following operations. You may write separate
method for each of this activity. You are expected to write a menu-driven program. It should
incorporate necessary input validation and exception handling allowing recovery whenever
possible.
1. Add a new vehicle (Vehicle or PremiumVehicle)
- Input validation must ensure that ID is 6 characters and unique
2. Add a new customer (either of type ICustomer or CCustomer)
- Input validation must ensure ID is 6 characters, unique and starts with C
3. Display details of available vehicles in the specified range (daily-rate)
- Input validation must ensure lower limit of range is less than upper limit
4. Hire a vehicle
- must take into account constraints on individual customer
- Any exceptions must be caught and reported
5. Complete hire of vehicle
- discount must be computed based on customer hiring the vehicle
- Any exceptions must be caught and reported
6. Send a vehicle for service / Return a vehicle from Service
- Any exceptions must be caught and reported
7. Writing all objects to files and restoring them, when program is restarted.
8. Creating a report allowing rental income from each vehicle in the specified period to be
listed.





