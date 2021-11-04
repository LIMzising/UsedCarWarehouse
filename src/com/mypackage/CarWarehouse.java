package com.mypackage;

import java.util.Scanner;

/**
 * The CarWarehouse class represents a car warehouse object generator. It is
 * used to generate a car warehouse which keeps databases of the used cars
 * and car makers, and produces a report of cars based on some criteria.
 *
 * @author  Zi Sing Lim
 * @version 1.0 (29.Apr.2021)
 */
public class CarWarehouse
{
    private CarDatabase carDatabase;
    private CarMakerDatabase carMakerDatabase;

    /**
     * Constructor for objects of class CarWarehouse
     */
    public CarWarehouse()
    {
        carDatabase = new CarDatabase();
        carMakerDatabase = new CarMakerDatabase();
        start();
    }

    /**
     * Accept input from user
     *
     * @return    a string represents user input
     */
    public String acceptUserInput()
    {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine().trim();
        return input;
    }

    /**
     * Add new car in car warehouse
     */
    public void addNewCar()
    {
        System.out.println("Please enter the car's attributes as per requested");
        System.out.print("Registration Number: ");
        String registrationNumber = acceptUserInput();
        try
        {
            isValidRegistrationNumber(registrationNumber);
            carDatabase.checkDuplicateCar(registrationNumber);

            System.out.print("Year Made: ");
            String yearMadeString = acceptUserInput();
            int yearMade = isValidYearMade(yearMadeString);

            System.out.print("Colours (e.g. White,Blue,Red): ");
            String colourString = acceptUserInput();
            String[] colours = isValidColours(colourString);

            CarMaker carMakerFound = selectCarMaker();
            String carMaker = carMakerFound.getName();

            String carModel = selectCarModel(carMakerFound, "add");

            System.out.print("Price: ");
            String priceString = acceptUserInput();
            int price = isValidPrice(priceString);

            Car newCar = new Car(registrationNumber, yearMade, colours,
                    carMaker, carModel, price);
            carDatabase.addCar(newCar);
            System.out.println();
            System.out.println("This car is added successfully");
            newCar.displayCar();
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Delete existing car in car warehouse
     */
    public void deleteExistingCar()
    {
        Car carFound = searchByRegistrationNumber();
        if (carFound != null)
            carDatabase.deleteCar(carFound);
    }

    /**
     * Display car edit option in car warehouse
     */
    public void displayCarEditOption()
    {
        System.out.println();
        System.out.println("Car attributes that can be edited: ");
        System.out.println("(1) Colour");
        System.out.println("(2) Price");
        System.out.print("Choose a car attribute option: ");
    }

    /**
     * Display car searching menu in car warehouse
     */
    public void displayCarSearchingMenu()
    {
        System.out.println();
        System.out.println("Car Searching Options:");
        System.out.println("======================");
        System.out.println("(1) By Registration Number");
        System.out.println("(2) By Car Make and Car Model");
        System.out.println("(3) By Car Age");
        System.out.println("(4) By Price (range)");
        System.out.println("(5) Back to Main Menu");
        System.out.print("Choose a car searching option: ");
    }

    /**
     * Display main menu in car warehouse
     */
    public void displayMainMenu()
    {
        System.out.println();
        System.out.println("Welcome to Used Car Warehouse Database System");
        System.out.println("=============================================");
        System.out.println("(1) Search Cars");
        System.out.println("(2) Add Car");
        System.out.println("(3) Delete Car");
        System.out.println("(4) Edit Car");
        System.out.println("(5) Exit System");
        System.out.print("Choose an option: ");
    }

    /**
     * Edit car in car warehouse
     */
    public void editCar()
    {
        Car carFound = searchByRegistrationNumber();
        if (carFound != null)
        {
            displayCarEditOption();
            String carEditInput = acceptUserInput();
            try
            {
                int carEditOption = isValidEditOption(carEditInput);
                if (carEditOption == 1)
                    editExistingCarColour(carFound);
                else
                    editExistingCarPrice(carFound);
                carFound.displayCar();
            }
            catch(IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Edit colour of existing car in car warehouse
     *
     * @param  car  a Car object represents car to be edited
     */
    public void editExistingCarColour(Car car)
    {
        System.out.print("Please enter new colours: ");
        String colourString = acceptUserInput();
        String[] colours = isValidColours(colourString);
        carDatabase.editCarColour(car, colours);
    }

    /**
     * Edit price of existing car in car warehouse
     *
     * @param  car  a Car object represents car to be edited
     */
    public void editExistingCarPrice(Car car)
    {
        System.out.print("Please enter new price: ");
        String priceString = acceptUserInput();
        int price = isValidPrice(priceString);
        carDatabase.editCarPrice(car, price);
    }

    /**
     * Validates whether user input is a valid age or not
     *
     * @param  userInput  a string represents age of car
     * @return    an integer represents valid age of car
     * @throws    IllegalArgumentException if age is not valid
     */
    public int isValidAge(String userInput)
    {
        int age = isValidNumber(userInput);
        int currentYear = 2021;
        int maximumAge = currentYear - 1950;
        int minimumAge = currentYear - currentYear;
        if (age < minimumAge || age > maximumAge)
            throw new IllegalArgumentException("Error: age must be between " +
                    minimumAge + " and " +
                    maximumAge + " (both inclusive)");
        return age;
    }

    /**
     * Validates whether user input is a valid colours or not
     *
     * @param  userInput  a string represents colours of car
     * @return    an array of string represents valid colours of car
     * @throws    IllegalArgumentException if colours is not valid
     */
    public String[] isValidColours(String userInput)
    {
        if (userInput.length() == 0)
        {
            throw new IllegalArgumentException("Error: colour(s) must not be " +
                    "blank");
        }
        String[] temporaryColours = userInput.split(",");
        if (temporaryColours.length > 3)
        {
            throw new IllegalArgumentException("Error: a car can only have " +
                    "maximum 3 colours");
        }
        String[] colours = new String[3];
        for (int index = 0; index < temporaryColours.length; index++)
            colours[index] = temporaryColours[index].trim();
        for (int index = 0; index < colours.length; index++)
            if (colours[index] == null)
                colours[index] = "";
        return colours;
    }

    /**
     * Validates whether user input is a valid edit option or not
     *
     * @param  userInput  a string represents user input
     * @return    an integer represents valid user input edit option
     * @throws    IllegalArgumentException if user input is not valid
     */
    public int isValidEditOption(String userInput)
    {
        int option = isValidNumber(userInput);
        if (option < 1 || option > 2)
            throw new IllegalArgumentException("Error: your choice must be " +
                    "between 1-2");
        return option;
    }

    /**
     * Validates whether user input is a valid maximum price or not
     *
     * @param  minimumPrice  an integer represents minimum price of car
     * @param  userInput  a string represents maximum price of car
     * @return    an integer represents valid maximum price of car
     * @throws    IllegalArgumentException if maximum price is not valid
     */
    public int isValidMaximumPrice(int minimumPrice, String userInput)
    {
        int maximumPrice = isValidNumber(userInput);
        if (maximumPrice < minimumPrice || maximumPrice > 30000)
            throw new IllegalArgumentException("Error: maximum price must be " +
                    "between " + minimumPrice +
                    " and 30000 (both inclusive)");
        return maximumPrice;
    }

    /**
     * Validates whether user input is a valid number or not
     *
     * @param  userInput  a string represents user input
     * @return    an integer represents valid user input number
     * @throws    IllegalArgumentException if user input is not valid
     */
    public int isValidNumber(String userInput)
    {
        if (userInput.length() == 0 || userInput.length() > 10)
            throw new IllegalArgumentException("Error: Please enter a valid " +
                    "input");
        int index = 0;
        while (index < userInput.length())
        {
            char thisCharacter = userInput.charAt(index);
            if (!Character.isDigit(thisCharacter))
                throw new IllegalArgumentException("Error: Please enter a " +
                        "valid number");
            index++;
        }
        int inputNumber = Integer.parseInt(userInput);
        return inputNumber;
    }

    /**
     * Validates whether user input is a valid option or not
     *
     * @param  userInput  a string represents user input
     * @return    an integer represents valid user input option
     * @throws    IllegalArgumentException if user input is not valid
     */
    public int isValidOption(String userInput)
    {
        int option = isValidNumber(userInput);
        if (option < 1 || option > 5)
            throw new IllegalArgumentException("Error: your choice must be " +
                    "between 1-5");
        return option;
    }

    /**
     * Validates whether user input is a valid price or not
     *
     * @param  userInput  a string represents price of car
     * @return    an integer represents valid price of car
     * @throws    IllegalArgumentException if price is not valid
     */
    public int isValidPrice(String userInput)
    {
        int price = isValidNumber(userInput);
        if (price < 500 || price > 30000)
            throw new IllegalArgumentException("Error: price must be " +
                    "between 500 and 30000 " +
                    "(both inclusive)");
        return price;
    }

    /**
     * Validates whether user input is a valid registration number or not
     *
     * @param  userInput  a string represents registration
     *                             number of car
     * @return    a boolean represents whether registration number is valid
     *            or not
     * @throws    IllegalArgumentException if registration number is not valid
     */
    public boolean isValidRegistrationNumber(String userInput)
    {
        if (userInput.length() == 0 || userInput.length() > 6)
        {
            throw new IllegalArgumentException("Error: registration number " +
                    "must not be blank and must " +
                    "only have maximum 6 characters");
        }
        int index = 0;
        while (index < userInput.length())
        {
            char thisCharacter = userInput.charAt(index);
            if (!Character.isDigit(thisCharacter) &&
                    !Character.isLetter(thisCharacter))
            {
                throw new IllegalArgumentException("Error: registration " +
                        "number must be numeric/" +
                        "alphabetic (e.g. 8RT2WT)");
            }
            index++;
        }
        return true;
    }

    /**
     * Validates whether user input is a valid year made or not
     *
     * @param  userInput  a string represents year made of car
     * @return    an integer represents valid year made of car
     * @throws    IllegalArgumentException if year made is not valid
     */
    public int isValidYearMade(String userInput)
    {
        int yearMade = isValidNumber(userInput);
        int currentYear = 2021;
        if (yearMade < 1950 || yearMade > currentYear)
            throw new IllegalArgumentException("Error: year made of a car " +
                    "must be between 1950 and " +
                    currentYear + " (both inclusive)");
        return yearMade;
    }

    /**
     * Search cars by age in car warehouse
     */
    public void searchByAge()
    {
        System.out.print("Please enter the maximum age of cars you want " +
                "to search: ");
        String ageString = acceptUserInput();
        try
        {
            int age = isValidAge(ageString);
            CarDatabase carsFound = carDatabase.getCarsByAge(age);
            System.out.println(carsFound.getNumberOfCar() + " car(s) found!");
            carsFound.displayCars();
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Search cars by car maker and car model in car warehouse
     */
    public void searchByCarMakerAndModel()
    {
        try
        {
            CarMaker carMakerFound = selectCarMaker();
            String carModelFound = selectCarModel(carMakerFound, "search");

            String carMakerName = carMakerFound.getName();
            System.out.println(carModelFound + " from " + carMakerName +
                    " is selected");
            CarDatabase carsFound = carDatabase.getCarsByMakerAndModel
                    (carMakerName, carModelFound);
            System.out.println(carsFound.getNumberOfCar() + " car(s) found!");
            carsFound.displayCars();
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Search cars by price range in car warehouse
     */
    public void searchByPrice()
    {
        System.out.print("Please enter the minimum price of cars you want " +
                "to search: ");
        String minimumPriceString = acceptUserInput();
        try
        {
            int minimumPrice = isValidPrice(minimumPriceString);

            System.out.print("Please enter the maximum price of cars you " +
                    "want to search: ");
            String maximumPriceString = acceptUserInput();
            int maximumPrice = isValidMaximumPrice(minimumPrice, maximumPriceString);

            CarDatabase carsFound = carDatabase.getCarsByPrice
                    (minimumPrice, maximumPrice);
            System.out.println(carsFound.getNumberOfCar() + " car(s) found!");
            carsFound.displayCars();
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Search car by registration number in car warehouse
     *
     * @return    a Car object represents car found by registration number
     */
    public Car searchByRegistrationNumber()
    {
        boolean isValidInput = false;
        while(!isValidInput)
        {
            System.out.print("Please enter a valid registration " +
                    "number: ");
            String registrationNumber = acceptUserInput();
            try
            {
                isValidInput = isValidRegistrationNumber(registrationNumber);
                Car carFound = carDatabase.getCarByRegistrationNumber
                        (registrationNumber);
                System.out.println("Car found!");
                carFound.displayCar();
                return carFound;
            }
            catch(IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    /**
     * Search cars in car warehouse
     */
    public void searchCar()
    {
        boolean exit = false;
        while (!exit)
        {
            displayCarSearchingMenu();
            String userInput = acceptUserInput();
            try
            {
                int option = isValidOption(userInput);
                switch (option)
                {
                    case 1:
                        searchByRegistrationNumber();
                        break;
                    case 2:
                        searchByCarMakerAndModel();
                        break;
                    case 3:
                        searchByAge();
                        break;
                    case 4:
                        searchByPrice();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        break;
                }
            }
            catch(IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Select car maker from available car makers in car warehouse
     *
     * @return    a Car Maker object represents car maker selected
     */
    public CarMaker selectCarMaker()
    {
        carMakerDatabase.displayCarMakers();
        System.out.print("Choose a car maker option: ");
        String carMakerInput = acceptUserInput();
        int carMakerNumber = isValidNumber(carMakerInput);
        CarMaker carMakerFound = carMakerDatabase.getCarMaker(carMakerNumber);
        return carMakerFound;
    }

    /**
     * Select car model from available car models from car maker in car
     * warehouse
     *
     * @param  carMaker  a Car Maker object represents car maker that
     *                   available models from
     * @param  mode  a string represents select mode, search mode or add mode
     * @return    a string represents car model selected
     */
    public String selectCarModel(CarMaker carMaker, String mode)
    {
        carMaker.displayAvailableModels();
        if (mode.equalsIgnoreCase("search"))
        {
            System.out.println("Special Option (0) - ANY");
            System.out.println("Choose Option (0) if you want to search all " +
                    carMaker.getName() + " models from the database");
        }
        System.out.print("Choose a car maker option: ");
        String carModelInput = acceptUserInput();
        int carModelNumber = isValidNumber(carModelInput);
        String carModelFound = carMaker.getModel(carModelNumber);
        return carModelFound;
    }

    /**
     * Start to perform operations in car warehouse
     */
    public void start()
    {
        carDatabase.readCarFile("data/usedcars.txt");
        carMakerDatabase.readCarMakerFile("data/carmakers.txt");

        boolean exit = false;
        while (!exit)
        {
            displayMainMenu();
            String userInput = acceptUserInput();
            try
            {
                int option = isValidOption(userInput);
                switch (option)
                {
                    case 1:
                        searchCar();
                        break;
                    case 2:
                        addNewCar();
                        break;
                    case 3:
                        deleteExistingCar();
                        break;
                    case 4:
                        editCar();
                        break;
                    case 5:
                        carDatabase.writeCarFile("data/usedcars.txt");
                        exit = true;
                        break;
                    default:
                        break;
                }
            }
            catch(IllegalArgumentException exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
}

