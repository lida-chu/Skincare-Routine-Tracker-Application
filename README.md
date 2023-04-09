# 🫧🧴 Skincare Routine Tracker 🧴🫧

## Welcome to my project! ##

> ### About my project: ###
> 
>> This application is a **skincare routine tracker**.
>> It lets you enter information about:
>> - The **current** skincare products you are using
>> - Skincare products you want to try in the **future**
>> - Skincare products you want to **avoid**
>
> Information entered for each product will include the type of product 
> (*i.e. purpose*), the brand, the product price, the frequency of usage, 
> and more. 
> 
> The application will track the user’s current skincare routine 
> (*the order of products applied daily and weekly*), as well as statistics on
> the current skincare routine expenses. Thus preventing users from forgetting
> which products they've already tried, which products they liked, and
> how much money they've spent on skincare to avoid spending more than they
> need to.

This application is for **anyone** that wants a way to keep track 
of their skincare routines. Many people, including myself, 
are constantly searching for a routine that works best for themselves. 
Not only do countless products exist, but skincare itself is expensive, 
and it can be discouraging for anyone struggling with their skin.

As such, I decided to make this project because it is an application 
that I, myself would use, and that many others can benefit from. 
With this application, I aim to make the skincare journey just a *bit* 
easier and fun for **everyone**.

> ### User Stories: ###
> 
> - As a user, I want to be able to add a product to my skincare routine.
> - As a user, I want to be able to view the products on my skincare routine.
> - As a user, I want to be able to remove a product from my skincare routine.
> - As a user, I want to be able to select a product from my skincare routine and view its information.
> - As a user, I want to be able to see the total expense of products in my current skincare routine.
> - As a user, I want to have the option of saving my current skincare routine to a file.
> - As a user, I want to have the option of loading a saved skincare routine from file into the application.
> 
> Stretch Goals: *(Not implemented yet)*
> - As a user, I want to be able to see the most expensive and least expensive products in my current skincare routine.
> - As a user, I want to be able to add a product to a list of products that I may want to try in the future.
> - As a user, I want to be able to add a product to a list of products that I want to avoid using.
> - As a user, I want to also be able to save the lists of products I want to avoid/use in the future with my current 
> skincare routine.
> - As a user, I want to also be able to load the lists of products I want to avoid/use in the future from the file with
> my current skincare routine into the application.
>

### Instructions for Grader (GUI)

- You can generate the first required action related to adding Xs to a Y by pressing the **add product
to routine** button on the main screen.
- You can generate the second required action related to adding Xs to a Y (viewing the routine) by
pressing the **view current skincare routine** button on the main screen.
- You can locate my visual component on the main screen, next to the title.
- You can save the state of my application by pressing the **save** button on the main screen.
- You can reload the state of my application by pressing the **load** button on the main screen.


>### Phase 4: Task 2
> *Sample of the events that occur when program runs:*
> 
> Sat Apr 08 16:56:46 PDT 2023\
Added product to routine.\
> Sat Apr 08 16:56:46 PDT 2023\
Added product to routine.\
> Sat Apr 08 16:56:46 PDT 2023\
Loaded previous routine.\
> Sat Apr 08 16:57:01 PDT 2023\
Removed product from routine.\
> Sat Apr 08 16:57:15 PDT 2023\
Added product to routine.\
> Sat Apr 08 16:57:34 PDT 2023\
Saved current routine.\
> Sat Apr 08 16:57:45 PDT 2023\
Removed product from routine.\
> Sat Apr 08 16:57:56 PDT 2023\
Displayed total expenses.\
> Sat Apr 08 16:58:09 PDT 2023\
Saved current routine.\
> Sat Apr 08 16:58:29 PDT 2023\
Removed product from routine.\
> Sat Apr 08 16:58:34 PDT 2023\
Added product to routine.\
> Sat Apr 08 16:58:34 PDT 2023\
Loaded previous routine.\
> Sat Apr 08 16:58:41 PDT 2023\
Removed product from routine.

>### Phase 4: Task 3
> To start, the RoutineTracker has a single instance of RemoveProductForm, 
> AddProductForm, MainScreen, and RoutineDisplay, as shown by the four association
> arrows. The class itself contains many methods that handle setting up the GUI,
> the actions for the buttons, and other GUI-related or visual aspects. However,
> the class’ main purpose is to handle operations on the skincare routine and 
> products. To refactor this, I would create an abstract class called AppGUI 
> or something similar, which has a single instance of each of the aforementioned 
> types, and have RoutineTracker implement the interface. In the class, I would 
> also add the default behavior which handles adding action listeners to all the
> buttons in the instances created. Then I would have the RoutineTracker extend 
> the class, allowing it to use the default behavior and reduce the code necessary
> for handling general app-related operations. This would improve cohesion, as the
> methods in RoutineTracker would all be more focused on dealing with the skincare
> routine in specific, and reduce coupling, as the RoutineTracker would have a single
> inheritance arrow to the abstract class, rather than four association arrows. 
> 
> Next, I would remove the abstract class, AppPanel. The four classes extending 
AppPanel currently do not behave similarly enough for a super class to be fitting
or of use. As such, AppPanel is not necessary and removing it will decrease the 
amount of non-essential code.

> 
> ### References 
> Code used for data persistence (i.e. the persistence package and toJson methods in model package) was primarily 
> designed after the [JsonSerializationDemo](https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
> repository.
> 
> Event and EventLog classes are from the [AlarmSystem](https://github.students.cs.ubc.ca/CPSC210/AlarmSystem)
> repository.


