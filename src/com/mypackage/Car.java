package com.mypackage;

/**
 * The Car class represents a car object generator. It is used to generate
 * a car which has 6 attributes.
 *
 * @author  Zi Sing Lim
 * @version 1.0 (29.Apr.2021)
 */
public class Car
{
    private String registrationNumber;
    private int yearMade;
    private String[] colours;
    private String carMaker;
    private String carModel;
    private int price;

    /**
     * Default constructor for objects of class Car
     */
    public Car()
    {
        registrationNumber = "";
        yearMade = 0;
        colours = new String[3];
        carMaker = "";
        carModel = "";
        price = 0;
    }

    /**
     * Parameterized constructor for objects of class Car
     *
     * @param  newRegistrationNumber  a string represents registration
     *                                number of car
     * @param  newYearMade  an integer represents year made of car
     * @param  newColours  an array of string represents colours of car
     * @param  newCarMaker  a string represents car maker
     * @param  newCarModel  a string represents car model
     * @param  newPrice  an integer represents price of car
     */
    public Car(String newRegistrationNumber, int newYearMade,
               String[] newColours, String newCarMaker, String newCarModel,
               int newPrice)
    {
        if (newRegistrationNumber == null)
            registrationNumber = "";
        else
            registrationNumber = newRegistrationNumber;
        if (newYearMade < 0)
            yearMade = 0;
        else
            yearMade = newYearMade;
        if (newColours == null)
            colours = new String[3];
        else
            colours = newColours;
        if (newCarMaker == null)
            carMaker = "";
        else
            carMaker = newCarMaker;
        if (newCarModel == null)
            carModel = "";
        else
            carModel = newCarModel;
        if (newPrice < 0)
            price = 0;
        else
            price = newPrice;
    }

    /**
     * Display car attributes
     */
    public void displayCar()
    {
        String colourString = "";
        for (int index = 0; index < colours.length; index++)
        {
            String colour = colours[index];
            if (colour != null)
            {
                if (colour.length() != 0)
                {
                    if (colourString.length() > 0)
                        colourString = colourString + ", ";
                    colourString = colourString + colour;
                }
            }
        }

        System.out.println();
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Year Made: " + yearMade);
        System.out.println("Colour: " + colourString);
        System.out.println("Car Maker: " + carMaker);
        System.out.println("Car Model: " + carModel);
        System.out.println("Price: " + price);
    }

    /**
     * Get car maker
     *
     * @return    a string represents car maker
     */
    public String getCarMaker()
    {
        return carMaker;
    }

    /**
     * Get car model
     *
     * @return    a string represents car model
     */
    public String getCarModel()
    {
        return carModel;
    }

    /**
     * Get colours of car
     *
     * @return    an array of string represents colours of car
     */
    public String[] getColours()
    {
        return colours;
    }

    /**
     * Get price of car
     *
     * @return    an integer represents price of car
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Get registration number of car
     *
     * @return    a string represents registration number of car
     */
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    /**
     * Get year made of car
     *
     * @return    an integer represents year made of car
     */
    public int getYearMade()
    {
        return yearMade;
    }

    /**
     * Set car maker
     *
     * @param  aCarMaker  a string represents car maker
     */
    public void setCarMaker(String aCarMaker)
    {
        String newCarMaker = aCarMaker.trim();
        if (newCarMaker.length() == 0)
            System.out.println("Error: car maker must not be blank");
        else
            carMaker = newCarMaker;
    }

    /**
     * Set car model
     *
     * @param  aCarModel  a string represents car model
     */
    public void setCarModel(String aCarModel)
    {
        String newCarModel = aCarModel.trim();
        if (newCarModel.length() == 0)
            System.out.println("Error: car model must not be blank");
        else
            carModel = newCarModel;
    }

    /**
     * Set colours of car
     *
     * @param  newColours  an array of string represents colours of car
     */
    public void setColours(String[] newColours)
    {
        if (newColours.length == 0)
            System.out.println("Error: colour(s) must not be blank");
        else
            colours = newColours;
    }

    /**
     * Set price of car
     *
     * @param  newPrice  an integer represents price of car
     */
    public void setPrice(int newPrice)
    {
        if (newPrice < 0)
            System.out.println("Error: price must be a non negative " +
                    "value");
        else
            price = newPrice;
    }

    /**
     * Set registration number of car
     *
     * @param  aRegistrationNumber  a string represents registration
     *                              number of car
     */
    public void setRegistrationNumber(String aRegistrationNumber)
    {
        String newRegistrationNumber = aRegistrationNumber.trim();
        if (newRegistrationNumber.length() == 0)
            System.out.println("Error: registration number must not be " +
                    "blank");
        else
            registrationNumber = newRegistrationNumber;
    }

    /**
     * Set year made of car
     *
     * @param  newYearMade  an integer represents year made of car
     */
    public void setYearMade(int newYearMade)
    {
        if (newYearMade < 0)
            System.out.println("Error: year made must be a non negative " +
                    "value");
        else
            yearMade = newYearMade;
    }
}

