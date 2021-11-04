package com.mypackage;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The CarMakerDatabase class represents a car maker database object
 * generator. It is used to generate a car maker database which stores
 * Car Maker objects.
 *
 * @author  Zi Sing Lim
 * @version 1.0 (29.Apr.2021)
 */
public class CarMakerDatabase
{
    private ArrayList<CarMaker> carMakers;

    /**
     * Constructor for objects of class CarMakerDatabase
     */
    public CarMakerDatabase()
    {
        carMakers = new ArrayList<>();
    }

    /**
     * Add a car maker to car maker database
     *
     * @param  name  a string represents name of car maker
     * @param  models  an array list of string represents available models
     *                 of the car maker
     */
    public void addCarMaker(String name, ArrayList<String> models)
    {
        CarMaker newCarMaker = new CarMaker(name, models);
        carMakers.add(newCarMaker);
    }

    /**
     * Add car maker from a string of its attributes
     *
     * @param  carMakerAttributeString  a string represents the attributes
     *                                  of a car maker
     */
    public void addCarMakerFromFile(String carMakerAttributeString)
    {
        String[] carMakerAttributes = carMakerAttributeString.split(",");

        String name = carMakerAttributes[0];
        ArrayList<String> models = new ArrayList<>();
        for (int index = 1; index < carMakerAttributes.length; index++)
            models.add(carMakerAttributes[index]);

        addCarMaker(name, models);
    }

    /**
     * Display car makers in car maker database
     */
    public void displayCarMakers()
    {
        System.out.println("Available Car Makers:");
        for (int index = 0; index < carMakers.size(); index++)
        {
            CarMaker thisCarMaker = carMakers.get(index);
            int thisNumber = index + 1;
            String carMaker = thisCarMaker.getName();
            System.out.println("(" + thisNumber + ") " + carMaker);
        }
    }

    /**
     * Get a car maker from car maker number
     *
     * @param  carMakerNumber  an integer represents car maker number
     * @return    a CarMaker object represents a particular car maker from
     *            the car maker number
     * @throws    IllegalArgumentException if car maker number is out of
     *                                     valid car maker number range
     */
    public CarMaker getCarMaker(int carMakerNumber)
    {
        int index = carMakerNumber - 1;
        if (index >= 0 && index < carMakers.size())
        {
            CarMaker carMaker = carMakers.get(index);
            return carMaker;
        }
        else
            throw new IllegalArgumentException("Error: your choice must be " +
                    "between 1-" + carMakers.size());
    }

    /**
     * Get car makers in car maker database
     *
     * @return    an array list of Car Maker represents car makers in
     *            car maker database
     */
    public ArrayList<CarMaker> getCarMakers()
    {
        return carMakers;
    }

    /**
     * Get number of car makers in car maker database
     *
     * @return    an integer represents number of car makers in car maker
     *            database
     */
    public int getNumberOfCarMaker()
    {
        return carMakers.size();
    }

    /**
     * Read file which contains car makers information in it
     *
     * @param  filename  a string represents filename of file to be read
     */
    public void readCarMakerFile(String filename)
    {
        try
        {
            FileReader inputFile = new FileReader(filename);
            try
            {
                Scanner parser = new Scanner(inputFile);
                String carMakerAttributeString = "";
                while (parser.hasNextLine())
                {
                    carMakerAttributeString = parser.nextLine();
                    addCarMakerFromFile(carMakerAttributeString);
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
     * Set car makers in car maker database
     *
     * @param  newCarMakers  an array list of Car Maker represents car makers
     *                       in car maker database
     */
    public void setCarMakers(ArrayList<CarMaker> newCarMakers)
    {
        carMakers = newCarMakers;
    }
}

