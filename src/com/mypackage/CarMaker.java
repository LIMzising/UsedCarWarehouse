package com.mypackage;

import java.util.ArrayList;

/**
 * The CarMaker class represents a car maker object generator. It is used to
 * generate a car maker which has 2 attrbutes.
 *
 * @author  Zi Sing Lim
 * @version 1.0 (29.Apr.2021)
 */
public class CarMaker
{
    private String name;
    private ArrayList<String> availableModels;

    /**
     * Default constructor for objects of class CarMaker
     */
    public CarMaker()
    {
        name = "";
        availableModels = new ArrayList<>();
    }

    /**
     * Parameterized constructor for objects of class CarMaker
     *
     * @param  newName  a string represents name of car maker
     * @param  newAvailableModels  an array list of string represents
     *                             available models of car maker
     */
    public CarMaker(String newName, ArrayList<String> newAvailableModels)
    {
        if (newName == null)
            name = "";
        else
            name = newName;
        if (newAvailableModels == null)
            availableModels = new ArrayList<>();
        else
            availableModels = newAvailableModels;
    }

    /**
     * Add a model to the array list of available models
     *
     * @param  aModel  a string represents a model of car maker
     * @return    a boolean represents whether the model is added or not
     */
    public boolean addModel(String aModel)
    {
        String newModel = aModel.trim();
        if (newModel.length() == 0)
        {
            System.out.println("Error: car model must not be blank");
            return false;
        }
        if (newModel.contains(","))
        {
            System.out.println("Error: car model must not contain commas");
            return false;
        }
        availableModels.add(newModel);
        return true;
    }

    /**
     * Display available models of car maker
     */
    public void displayAvailableModels()
    {
        System.out.println("Available Car Models:");
        for (int index = 0; index < availableModels.size(); index++)
        {
            int thisNumber = index + 1;
            String thisCarModel = availableModels.get(index);
            System.out.println("(" + thisNumber + ") " + thisCarModel);
        }
    }

    /**
     * Display car maker attributes
     */
    public void displayCarMaker()
    {
        System.out.println("Car Maker Name: " + name);
        System.out.println("Available Models: "+
                String.join(", " , availableModels));
    }

    /**
     * Get available models of car maker
     *
     * @return    an array list of string represents available models of
     *            car maker
     */
    public ArrayList<String> getAvailableModels()
    {
        return availableModels;
    }

    /**
     * Get a model from model number
     *
     * @param  modelNumber  an integer represents model number of car maker
     * @return    a string represents a particular model from the model number
     * @throws    IllegalArgumentException if model number is out of
     *                                     valid model number range
     */
    public String getModel(int modelNumber)
    {
        if (modelNumber == 0)
            return "ANY";
        int index = modelNumber - 1;
        if (index >= 0 && index < availableModels.size())
        {
            String carModel = availableModels.get(index);
            return carModel;
        }
        else
            throw new IllegalArgumentException("Error: your choice must be " +
                    "either “ANY” or between 1-" +
                    availableModels.size());
    }

    /**
     * Get name of car maker
     *
     * @return    a string represents name of car maker
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get number of available models of car maker
     *
     * @return    an integer represents number of available models of car maker
     */
    public int getNumberOfModel()
    {
        return availableModels.size();
    }

    /**
     * Remove a model from the array list of available models
     *
     * @param  index  an integer represents the index of model in the array
     *                list of available models
     */
    public void removeModel(int index)
    {
        if (index >= 0 && index < availableModels.size())
            availableModels.remove(index);
        else
        {
            System.out.println("Error: index must be between 0 (inclusive) ");
            System.out.println("and " + getNumberOfModel());
        }
    }

    /**
     * Set available models of car maker
     *
     * @param  newAvailableModels  an array list of string represents
     *                             available models of car maker
     */
    public void setAvailableModels(ArrayList<String> newAvailableModels)
    {
        if (newAvailableModels.size() == 0)
            System.out.println("Error: available model(s) must not be blank");
        else
            availableModels = newAvailableModels;
    }

    /**
     * Set name of car maker
     *
     * @param  aName  a string represents name of car maker
     * @return    a boolean represents whether the name of car maker is set
     *            or not
     */
    public boolean setName(String aName)
    {
        String newName = aName.trim();
        if (newName.length() == 0)
        {
            System.out.println("Error: car maker name must not be blank");
            return false;
        }
        if (newName.contains(","))
        {
            System.out.println("Error: car maker name must not contain ");
            System.out.println("commas");
            return false;
        }
        name = newName;
        return true;
    }
}

