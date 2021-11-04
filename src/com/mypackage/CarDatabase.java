package com.mypackage;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The CarDatabase class represents a car database object generator. It is
 * used to generate a car database which stores Car objects.
 *
 * @author  Zi Sing Lim
 * @version 1.0 (29.Apr.2021)
 */
public class CarDatabase
{
    private ArrayList<Car> cars;

    /**
     * Constructor for objects of class CarDatabase
     */
    public CarDatabase()
    {
        cars = new ArrayList<>();
    }

    /**
     * Add a car to car database
     *
     * @param  newCar  a Car object represents car to be added
     */
    public void addCar(Car newCar)
    {
        cars.add(newCar);
    }

    /**
     * Add car from a string of its attributes
     *
     * @param  carAttributeString  a string represents the attributes of a car
     */
    public void addCarFromFile(String carAttributeString)
    {
        String[] carAttributes = carAttributeString.split(",");

        String registrationNumber = carAttributes[0];
        int yearMade = Integer.parseInt(carAttributes[1]);
        String[] colours = {carAttributes[2], carAttributes[3], carAttributes[4]};
        String carMaker = carAttributes[5];
        String carModel = carAttributes[6];
        int price = Integer.parseInt(carAttributes[7]);

        Car newCar = new Car(registrationNumber, yearMade, colours,
                carMaker, carModel, price);
        addCar(newCar);
    }

    /**
     * Check uniqueness of a car in car database
     *
     * @param  registrationNumber  a string represents registration
     *                             number of car
     * @throws    IllegalArgumentException if the car is duplicate
     */
    public void checkDuplicateCar(String registrationNumber)
    {
        for (Car thisCar: cars)
        {
            if (thisCar.getRegistrationNumber().equalsIgnoreCase
                    (registrationNumber))
            {
                throw new IllegalArgumentException("Error: registration number " +
                        "must be unique. This number " +
                        "is already been registered");
            }
        }
    }

    /**
     * Delete a car from car database
     *
     * @param  car  a Car object represents car to be deleted
     */
    public void deleteCar(Car car)
    {
        cars.remove(car);
        System.out.println();
        System.out.println("This car is deleted successfully");
    }

    /**
     * Display cars in car database
     */
    public void displayCars()
    {
        for (int index = 0; index < getNumberOfCar(); index++)
        {
            int number = index + 1;
            Car thisCar = cars.get(index);
            thisCar.displayCar();
            System.out.println("(Car " + number + " Information)");
        }
    }

    /**
     * Edit colours of a car
     *
     * @param  car  a Car object represents car to be edited
     * @param  newColours  an array of string represents represents new
     *                     colours of car
     */
    public void editCarColour(Car car, String[] newColours)
    {
        car.setColours(newColours);
        System.out.println();
        System.out.println("This car colour is edited successfully");
    }

    /**
     * Edit price of a car
     *
     * @param  car  a Car object represents car to be edited
     * @param  newPrice  an integer represents price of car
     */
    public void editCarPrice(Car car, int newPrice)
    {
        car.setPrice(newPrice);
        System.out.println();
        System.out.println("This car price is edited successfully");
    }

    /**
     * Get a string of a car attributes
     *
     * @param  aCar  a Car object represents the car which its attributes
     *               to be extarcted
     */
    public String getCarAttributeString(Car aCar)
    {
        String registrationNumber = aCar.getRegistrationNumber();
        String yearMade = String.valueOf(aCar.getYearMade());
        String colourString = String.join(",", aCar.getColours());
        String carMaker = aCar.getCarMaker();
        String carModel = aCar.getCarModel();
        String price = String.valueOf(aCar.getPrice());

        String carAttributeString = String.join(",", registrationNumber,
                yearMade, colourString, carMaker, carModel,
                price);
        return carAttributeString;
    }

    /**
     * Get car by registration number of car
     *
     * @param  registrationNumber  a string represents registration
     *                             number of car
     * @return    a Car object represents car found by registration number
     * @throws    IllegalArgumentException if car is not found
     */
    public Car getCarByRegistrationNumber(String registrationNumber)
    {
        boolean found = false;
        int index = 0;
        while (!found && index < cars.size())
        {
            Car thisCar = cars.get(index);
            if (thisCar.getRegistrationNumber().
                    equalsIgnoreCase(registrationNumber))
            {
                found = true;
                return thisCar;
            }
            index++;
        }
        if (found == false)
            throw new IllegalArgumentException("No such car with this " +
                    "Registration Number");
        return null;
    }

    /**
     * Get cars in car database
     *
     * @return    an array list of Car represents cars in car database
     */
    public ArrayList<Car> getCars()
    {
        return cars;
    }

    /**
     * Get cars by age of car
     *
     * @param  age  an integer represents age of car
     * @return    a CarDatabase object represents cars found by age of car
     * @throws    IllegalArgumentException if no car is found
     */
    public CarDatabase getCarsByAge(int age)
    {
        int currentYear = 2021;
        CarDatabase carsFound = new CarDatabase();
        for (Car thisCar: cars)
        {
            if (currentYear - thisCar.getYearMade() <= age)
            {
                carsFound.addCar(thisCar);
            }
        }
        if (carsFound.getNumberOfCar() == 0)
            throw new IllegalArgumentException("There is no car younger " +
                    "than or equal to this age " +
                    "in the warehouse currently");
        return carsFound;
    }

    /**
     * Get cars by car maker and car model
     *
     * @param  carMaker  a string represents car maker
     * @param  carModel  a string represents car model
     * @return    a CarDatabase object represents cars found by car maker and
     *            car model
     * @throws    IllegalArgumentException if no car is found
     */
    public CarDatabase getCarsByMakerAndModel(String carMaker, String carModel)
    {
        CarDatabase carsFound = new CarDatabase();
        for (Car thisCar: cars)
        {
            if (thisCar.getCarMaker().equalsIgnoreCase(carMaker))
            {
                if (carModel.equalsIgnoreCase("ANY"))
                    carsFound.addCar(thisCar);
                else
                if (thisCar.getCarModel().equalsIgnoreCase(carModel))
                    carsFound.addCar(thisCar);
            }
        }
        if (carsFound.getNumberOfCar() == 0)
            throw new IllegalArgumentException("There is no car with this " +
                    "car maker and model in the " +
                    "warehouse currently");
        return carsFound;
    }

    /**
     * Get cars by price of car
     *
     * @param  minimumPrice  an integer represents minimum price of car
     * @param  maximumPrice  an integer represents maximum price of car
     * @return    a CarDatabase object represents cars found by price of car
     * @throws    IllegalArgumentException if no car is found
     */
    public CarDatabase getCarsByPrice(int minimumPrice, int maximumPrice)
    {
        CarDatabase carsFound = new CarDatabase();
        for (Car thisCar: cars)
        {
            if (thisCar.getPrice() >= minimumPrice &&
                    thisCar.getPrice() <= maximumPrice)
            {
                carsFound.addCar(thisCar);
            }
        }
        if (carsFound.getNumberOfCar() == 0)
            throw new IllegalArgumentException("There is no car within " +
                    "this price range");
        return carsFound;
    }

    /**
     * Get number of cars in car database
     *
     * @return    an integer represents number of cars in car database
     */
    public int getNumberOfCar()
    {
        return cars.size();
    }

    /**
     * Read file which contains cars information in it
     *
     * @param  filename  a string represents filename of file to be read
     */
    public void readCarFile(String filename)
    {
        try
        {
            FileReader inputFile = new FileReader(filename);
            try
            {
                Scanner parser = new Scanner(inputFile);
                String carAttributeString = "";
                while (parser.hasNextLine())
                {
                    carAttributeString = parser.nextLine();
                    addCarFromFile(carAttributeString);
                }
            }
            finally
            {
                inputFile.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs");
        }
    }

    /**
     * Set cars in car database
     *
     * @param  newCars  an array list of Car represents cars in car database
     */
    public void setCars(ArrayList<Car> newCars)
    {
        cars = newCars;
    }

    /**
     * Write cars information in car database to file
     *
     * @param  filename  a string represents filename of file to be writed
     */
    public void writeCarFile(String filename)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            try
            {
                ArrayList<Car> cars = getCars();
                for (Car aCar: cars)
                {
                    String carAttributeString = getCarAttributeString(aCar);
                    outputFile.println(carAttributeString);
                }
            }
            finally
            {
                outputFile.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs");
        }
    }
}

